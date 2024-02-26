package edu.ustc.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.ustc.shortlink.admin.dao.entity.GroupDO;
import edu.ustc.shortlink.admin.dao.mapper.GroupMapper;
import edu.ustc.shortlink.admin.service.GroupService;
import edu.ustc.shortlink.admin.toolkit.RandomGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: ljx
 * @Date: 2024/2/26 11:05
 */
@Service
@Slf4j
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {
    @Override
    public void saveGroup(String groupName) {
        String gid = RandomGenerator.generateRandom();
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                // TODO 设置用户名
                .eq(GroupDO::getUsername,null);
        GroupDO hasGroupFlag = baseMapper.selectOne(queryWrapper);
        while(hasGroupFlag != null) {
            gid = RandomGenerator.generateRandom();
            hasGroupFlag = baseMapper.selectOne(queryWrapper);
        }
        GroupDO groupDO = GroupDO.builder()
                .name(groupName)
                .gid(gid)
                .build();

        baseMapper.insert(groupDO);
    }
}
