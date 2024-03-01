package edu.ustc.shortlink.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import edu.ustc.shortlink.admin.common.biz.user.UserContext;
import edu.ustc.shortlink.admin.common.convention.exception.ServiceException;
import edu.ustc.shortlink.admin.common.convention.result.Result;
import edu.ustc.shortlink.admin.dao.entity.GroupDO;
import edu.ustc.shortlink.admin.dao.mapper.GroupMapper;
import edu.ustc.shortlink.admin.remote.ShortLinkRemoteService;
import edu.ustc.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import edu.ustc.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import edu.ustc.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import edu.ustc.shortlink.admin.service.RecycleBinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: ljx
 * @Date: 2024/3/1 16:12
 */
@Service
@RequiredArgsConstructor
public class RecycleBinServiceImpl implements RecycleBinService {

    private final GroupMapper groupMapper;

    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {};

    @Override
    public Result<IPage<ShortLinkPageRespDTO>> pageRecycleShortLink(ShortLinkRecycleBinPageReqDTO requestParam) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getDelFlag, 0);
        List<GroupDO> groupDOList = groupMapper.selectList(queryWrapper);
        if(CollUtil.isEmpty(groupDOList)) {
            throw new ServiceException("用户无分组信息");
        }
        requestParam.setGidList(groupDOList.stream().map(GroupDO::getGid).collect(Collectors.toList()));
        return shortLinkRemoteService.pageRecycleShortLink(requestParam);
    }
}
