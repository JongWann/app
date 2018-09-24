package com.junwan.demo.exception;

import com.junwan.demo.enums.ResultEnums;

/**
 * 自定义用户异常
 */
public class UserException extends BaseException {

    public UserException(ResultEnums resultEnums) {
        super(resultEnums);
        super.setCode(resultEnums.getCode());
    }
}
