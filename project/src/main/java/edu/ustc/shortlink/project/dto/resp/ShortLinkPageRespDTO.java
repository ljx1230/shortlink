package edu.ustc.shortlink.project.dto.resp;

import lombok.Data;

import java.util.Date;

/**
 * @Author: ljx
 * @Date: 2024/2/27 20:51
 */
@Data
public class ShortLinkPageRespDTO {
    private Long id;
    /**
     * 域名
     */
    private String domain;

    /**
     * 短链接
     */
    private String shortUri;

    /**
     * 完整短链接
     */
    private String fullShortUrl;

    /**
     * 原始链接
     */
    private String originUrl;

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
    private Date validDate;

    /**
     * 描述
     */
    private String describe;

    /**
     * 网站图标
     */
    private String favicon;
}
