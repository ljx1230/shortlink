package edu.ustc.shortlink.project.dto.req;

import lombok.Data;

/**
 * @Author: ljx
 * @Date: 2024/3/1 14:49
 */
@Data
public class RecycleBinRecoverReqDTO {
    private String gid;
    private String fullShortUrl;
}
