package com.junwan.demo.handle;

import com.junwan.demo.entity.Result;
import com.junwan.demo.exception.UserException;
import com.junwan.demo.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常捕获
 */
@ControllerAdvice
public class ExceptionHandle {

    private Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof UserException){
            UserException userException = (UserException) e;
            return ResultUtil.fail(userException.getCode(), e.getMessage());
        } else {
            logger.error("系统异常:{}", e);
            return ResultUtil.fail(-1,"未知错误");
        }
    }
}
