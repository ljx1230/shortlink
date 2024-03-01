package edu.ustc.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.ustc.shortlink.project.dao.entity.ShortLinkDO;
import edu.ustc.shortlink.project.dto.req.*;
import edu.ustc.shortlink.project.dto.resp.ShortLinkPageRespDTO;

/**
 * @Author: ljx
 * @Date: 2024/3/1 14:51
 */
public interface RecycleBinService extends IService<ShortLinkDO>{
    void saveRecycleBin(RecycleBinSaveReqDTO requestParam);

    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkRecycleBinPageReqDTO requestParam);

    void recover(RecycleBinRecoverReqDTO requestParam);

    void removeRecycleBin(RecycleBinRemoverReqDTO requestParam);
}
