package com.junwan.demo.exception;

import com.junwan.demo.enums.ResultEnums;

public class BaseException extends RuntimeException {
    private Integer code;

    public BaseException(ResultEnums resultEnums) {
        super(resultEnums.getMsg());
        this.code = resultEnums.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
