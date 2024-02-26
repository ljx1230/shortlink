package edu.ustc.shortlink.admin.dto.req;

import lombok.Data;

/**
 * @Author: ljx
 * @Date: 2024/2/12 11:59
 */
@Data
public class UserLoginReqDTO {
    private String username;
    private String password;
}
