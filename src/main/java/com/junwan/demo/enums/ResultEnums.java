package com.junwan.demo.enums;

/**
 * 定义返回枚举
 */
public enum ResultEnums {
    UNKNOW_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    USER_NAME_NOT_NULL(10001, "用户名不能为空"),
    USER_NOT_FIND(10002, "查询不到用户"),
    WX_TOKEN_ERR(20001, "获取不到token");


    private Integer code;
    private String msg;

    ResultEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
