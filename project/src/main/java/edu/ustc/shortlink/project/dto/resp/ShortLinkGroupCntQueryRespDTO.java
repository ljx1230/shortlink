package edu.ustc.shortlink.project.dto.resp;

import lombok.Data;

/**
 * @Author: ljx
 * @Date: 2024/2/28 11:39
 */
@Data
public class ShortLinkGroupCntQueryRespDTO {
    private String gid;
    private Integer shortLinkCount;
}
