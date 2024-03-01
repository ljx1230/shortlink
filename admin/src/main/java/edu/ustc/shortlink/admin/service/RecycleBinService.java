package edu.ustc.shortlink.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.ustc.shortlink.admin.common.convention.result.Result;
import edu.ustc.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import edu.ustc.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import edu.ustc.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;

/**
 * @Author: ljx
 * @Date: 2024/3/1 16:12
 */
public interface RecycleBinService {
    Result<IPage<ShortLinkPageRespDTO>> pageRecycleShortLink(ShortLinkRecycleBinPageReqDTO requestParam);
}
