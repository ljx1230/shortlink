package edu.ustc.shortlink.admin.dto.req;

import lombok.Data;

/**
 * @Author: ljx
 * @Date: 2024/2/26 15:19
 */
@Data
public class ShortLinkGroupSortReqDTO {
    private String gid;
    private Integer sortOrder;
}
