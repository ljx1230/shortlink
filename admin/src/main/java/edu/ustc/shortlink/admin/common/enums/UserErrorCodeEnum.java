package edu.ustc.shortlink.admin.common.enums;

import edu.ustc.shortlink.admin.common.convention.errorcode.IErrorCode;

/**
 * @Author: ljx
 * @Date: 2024/2/7 14:22
 */
public enum UserErrorCodeEnum implements IErrorCode {
    USER_TOKEN_FAIL("A000200","用户token验证失败"),
    User_NULL("B000200","用户记录不存在"),
    User_NAME_EXIST("B000201","用户名已存在"),
    USER_EXIST("B000202","用户已存在"),
    USER_SAVE_ERROR("B000203","用户注册失败");
    private final String code;
    private final String message;
    UserErrorCodeEnum(String code,String message) {
        this.code = code;
        this.message = message;
    }
    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}
