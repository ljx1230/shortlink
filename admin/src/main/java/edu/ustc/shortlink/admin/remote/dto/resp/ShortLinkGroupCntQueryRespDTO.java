package edu.ustc.shortlink.admin.remote.dto.resp;

import lombok.Data;

@Data
public class ShortLinkGroupCntQueryRespDTO {
    private String gid;
    private Integer shortLinkCount;
}