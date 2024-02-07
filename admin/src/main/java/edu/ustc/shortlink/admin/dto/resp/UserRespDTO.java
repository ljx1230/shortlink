package edu.ustc.shortlink.admin.dto.resp;

import lombok.Data;

/**
 * @Author: ljx
 * @Date: 2024/2/7 13:44
 */
@Data
public class UserRespDTO {
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

}
