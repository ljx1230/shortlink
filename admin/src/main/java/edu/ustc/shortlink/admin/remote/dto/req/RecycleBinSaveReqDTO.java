package edu.ustc.shortlink.admin.remote.dto.req;

import lombok.Data;

/**
 * @Author: ljx
 * @Date: 2024/3/1 14:49
 */
@Data
public class RecycleBinSaveReqDTO {
    private String gid;
    private String fullShortUrl;
}
