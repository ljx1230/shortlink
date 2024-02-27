package edu.ustc.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.ustc.shortlink.project.dao.entity.ShortLinkDO;
import edu.ustc.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import edu.ustc.shortlink.project.dto.req.ShortLinkPageReqDTO;
import edu.ustc.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import edu.ustc.shortlink.project.dto.resp.ShortLinkPageRespDTO;

/**
 * @Author: ljx
 * @Date: 2024/2/27 14:34
 */
public interface ShortLinkService extends IService<ShortLinkDO> {
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);

    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);
}
