package edu.ustc.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.ustc.shortlink.admin.common.biz.user.UserContext;

import edu.ustc.shortlink.admin.dao.entity.GroupDO;
import edu.ustc.shortlink.admin.dao.mapper.GroupMapper;
import edu.ustc.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import edu.ustc.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import edu.ustc.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import edu.ustc.shortlink.admin.remote.ShortLinkRemoteService;
import edu.ustc.shortlink.admin.remote.dto.resp.ShortLinkGroupCntQueryRespDTO;
import edu.ustc.shortlink.admin.service.GroupService;
import edu.ustc.shortlink.admin.toolkit.RandomGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ljx
 * @Date: 2024/2/26 11:05
 */
@Service
@Slf4j
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {
    private ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {};
    @Override
    public void saveGroup(String groupName) {
        this.saveGroup(UserContext.getUsername(),groupName);
    }

    @Override
    public void saveGroup(String username, String groupName) {
        String gid = RandomGenerator.generateRandom();
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                .eq(GroupDO::getUsername,username);
        GroupDO hasGroupFlag = baseMapper.selectOne(queryWrapper);
        while(hasGroupFlag != null) {
            gid = RandomGenerator.generateRandom();
            hasGroupFlag = baseMapper.selectOne(queryWrapper);
        }
        GroupDO groupDO = GroupDO.builder()
                .name(groupName)
                .username(username)
                .gid(gid)
                .sortOrder(0)
                .build();

        baseMapper.insert(groupDO);
    }

    @Override
    public List<ShortLinkGroupRespDTO> listGroup() {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getDelFlag,0)
                .orderByDesc(GroupDO::getSortOrder, GroupDO::getUpdateTime);
        List<GroupDO> list = baseMapper.selectList(queryWrapper);

        List<ShortLinkGroupCntQueryRespDTO> groupCntQueryRespDTOList =
                shortLinkRemoteService.listGroupShortLinkCount(list.stream().map(GroupDO::getGid).toList()).getData();

        List<ShortLinkGroupRespDTO> groupRespDTOList = BeanUtil.copyToList(list, ShortLinkGroupRespDTO.class);

        for(var item : groupRespDTOList) {
            for(var item2 : groupCntQueryRespDTOList) {
                if(item2.getGid().equals(item.getGid())) {
                    item.setShortLinkCount(item2.getShortLinkCount());
                    break;
                }
            }
        }
        return groupRespDTOList;
    }

    @Override
    public void updateGroup(ShortLinkGroupUpdateReqDTO requestParam) {
        LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getUsername,UserContext.getUsername())
                .eq(GroupDO::getDelFlag,0)
                .eq(GroupDO::getGid, requestParam.getGid());
        GroupDO groupDO = new GroupDO();
        groupDO.setName(requestParam.getName());
        baseMapper.update(groupDO,updateWrapper);
    }

    @Override
    public void deleteGroup(String gid) {
        LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getUsername,UserContext.getUsername())
                .eq(GroupDO::getDelFlag,0)
                .eq(GroupDO::getGid, gid);
        GroupDO groupDO = new GroupDO();
        groupDO.setDelFlag(1);
        baseMapper.update(groupDO,updateWrapper);
    }

    @Override
    public void sortGroup(List<ShortLinkGroupSortReqDTO> requestParam) {
        requestParam.forEach(item -> {
            GroupDO groupDO = GroupDO.builder()
                    .gid(item.getGid())
                    .sortOrder(item.getSortOrder())
                    .build();
            LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                    .eq(GroupDO::getUsername, UserContext.getUsername())
                    .eq(GroupDO::getGid, item.getGid())
                    .eq(GroupDO::getDelFlag, 0);
            baseMapper.update(groupDO,updateWrapper);
        });
    }
}
