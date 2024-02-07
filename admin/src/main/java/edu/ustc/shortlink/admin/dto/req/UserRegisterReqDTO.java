package edu.ustc.shortlink.admin.dto.req;

import lombok.Data;

/**
 * @Author: ljx
 * @Date: 2024/2/7 15:47
 */
@Data
public class UserRegisterReqDTO {
    private String username;
    private String password;
    private String realName;
    private String phone;
    private String mail;
}
