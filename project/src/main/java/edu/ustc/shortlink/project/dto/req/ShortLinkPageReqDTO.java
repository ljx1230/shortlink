package edu.ustc.shortlink.project.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.ustc.shortlink.project.dao.entity.ShortLinkDO;
import lombok.Data;

/**
 * @Author: ljx
 * @Date: 2024/2/27 20:50
 */
@Data
public class ShortLinkPageReqDTO extends Page<ShortLinkDO> {
    private String gid;
}
