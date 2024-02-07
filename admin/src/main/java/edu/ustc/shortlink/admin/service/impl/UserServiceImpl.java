package edu.ustc.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.ustc.shortlink.admin.common.convention.exception.ClientException;
import edu.ustc.shortlink.admin.common.enums.UserErrorCodeEnum;
import edu.ustc.shortlink.admin.dao.entity.UserDO;
import edu.ustc.shortlink.admin.dao.mapper.UserMapper;
import edu.ustc.shortlink.admin.dto.req.UserRegisterReqDTO;
import edu.ustc.shortlink.admin.dto.resp.UserRespDTO;
import edu.ustc.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
                int cnt = baseMapper.insert(BeanUtil.toBean(userRegisterReqDTO, UserDO.class));
                if(cnt < 1) {
                    throw new ClientException(UserErrorCodeEnum.USER_SAVE_ERROR);
                }
                userRegisterCachePenetrationBloomFilter.add(username);
            } else {
                throw new ClientException(UserErrorCodeEnum.User_NAME_EXIST);
            }
        } finally {
            lock.unlock();
        }


    }
}
