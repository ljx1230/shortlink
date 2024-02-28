package edu.ustc.shortlink.admin.dto.resp;

import lombok.Data;

/**
 * @Author: ljx
 * @Date: 2024/2/26 12:30
 */
@Data
public class ShortLinkGroupRespDTO {
    private String gid;
    private String name;
    private Integer sortOrder;
    private Integer shortLinkCount;
}
