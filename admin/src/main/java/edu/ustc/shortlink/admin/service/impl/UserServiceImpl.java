package edu.ustc.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.ustc.shortlink.admin.common.biz.user.UserContext;
import edu.ustc.shortlink.admin.common.biz.user.UserInfoDTO;
import edu.ustc.shortlink.admin.common.convention.exception.ClientException;
import edu.ustc.shortlink.admin.common.enums.UserErrorCodeEnum;
import edu.ustc.shortlink.admin.dao.entity.UserDO;
import edu.ustc.shortlink.admin.dao.mapper.UserMapper;
import edu.ustc.shortlink.admin.dto.req.UserLoginReqDTO;
import edu.ustc.shortlink.admin.dto.req.UserRegisterReqDTO;
import edu.ustc.shortlink.admin.dto.req.UserUpdateReqDTO;
import edu.ustc.shortlink.admin.dto.resp.UserLoginRespDTO;
import edu.ustc.shortlink.admin.dto.resp.UserRespDTO;
import edu.ustc.shortlink.admin.service.GroupService;
import edu.ustc.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static edu.ustc.shortlink.admin.common.constant.RedisCacheConstant.LOCK_USER_REGISTER_KEY;

/**
 * @Author: ljx
 * @Date: 2024/2/7 13:40
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;
    private final RedissonClient redissonClient;
    private final StringRedisTemplate stringRedisTemplate;
    private final GroupService groupService;
    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if(userDO == null) {
            throw new ClientException(UserErrorCodeEnum.User_NULL);
        }
        UserRespDTO userRespDTO = new UserRespDTO();
        BeanUtils.copyProperties(userDO,userRespDTO);
        return userRespDTO;
    }

    @Override
    public Boolean hasUsername(String username) {
        return !userRegisterCachePenetrationBloomFilter.contains(username);
    }

    @Override
    public void register(UserRegisterReqDTO userRegisterReqDTO) {
        String username = userRegisterReqDTO.getUsername();
        if(!hasUsername(username)) {
            throw new ClientException(UserErrorCodeEnum.User_NAME_EXIST);
        }
        RLock lock = redissonClient.getLock(LOCK_USER_REGISTER_KEY + username);
        try{
            if(lock.tryLock()) {
                try {
                    int cnt = baseMapper.insert(BeanUtil.toBean(userRegisterReqDTO, UserDO.class));
                    if(cnt < 1) {
                        throw new ClientException(UserErrorCodeEnum.USER_SAVE_ERROR);
                    }
                } catch (DuplicateKeyException e) {
                    throw new ClientException(UserErrorCodeEnum.USER_EXIST);
                }
                userRegisterCachePenetrationBloomFilter.add(username);
                groupService.saveGroup(username,"默认分组");
                return;
            }
            throw new ClientException(UserErrorCodeEnum.User_NAME_EXIST);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void update(UserUpdateReqDTO userUpdateReqDTO) {
        // TODO 验证当前用户名是否为登录用户
        LambdaUpdateWrapper<UserDO> wrapper = Wrappers.lambdaUpdate(UserDO.class)
                .eq(UserDO::getUsername, userUpdateReqDTO.getUsername());
        this.baseMapper.update(BeanUtil.toBean(userUpdateReqDTO,UserDO.class),wrapper);
    }

    @Override
    public UserLoginRespDTO login(UserLoginReqDTO userLoginReqDTO) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, userLoginReqDTO.getUsername())
                .eq(UserDO::getPassword, userLoginReqDTO.getPassword())
                .eq(UserDO::getDelFlag, 0);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if(userDO == null) {
            throw new ClientException("用户不存在");
        }
        String key = "login_" + userLoginReqDTO.getUsername();
        if(Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {
            throw new ClientException("用户已经登录");
        }
        String uuid = UUID.randomUUID().toString(true);


        stringRedisTemplate.opsForHash().put(key,uuid,JSON.toJSONString(userDO));
        stringRedisTemplate.expire(key,30L,TimeUnit.DAYS);
        return new UserLoginRespDTO(uuid);
    }

    @Override
    public Boolean checkLogin(String username,String token) {
        return stringRedisTemplate.opsForHash().get("login_" + username,token) != null;
    }

    @Override
    public void logout(String username, String token) {
        if(checkLogin(username,token)) {
            stringRedisTemplate.delete("login_" + username);
            return;
        }
        throw new ClientException("用户未登录");
    }
}
