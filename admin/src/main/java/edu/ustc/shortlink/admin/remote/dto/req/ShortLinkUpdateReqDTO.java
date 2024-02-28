package edu.ustc.shortlink.admin.remote.dto.req;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: ljx
 * @Date: 2024/2/27 15:14
 */
@Data
public class ShortLinkUpdateReqDTO {
    /**
     * 原始链接
     */
    private String originUrl;

    /**
     * 短链接
     */
    private String shortUrl;

    /**
     * 完整短链接
     */
    private String fullShortUrl;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 有效期类型 0：永久有效 1：用户自定义
     */
    private int validDateType;

    /**
     * 有效期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date validDate;


    private String describe;
}
