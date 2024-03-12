package com.lxw.framework.exception;

import com.lxw.framework.model.response.ResponseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CatchException {
    @ExceptionHandler(CustomException.class)
    public ResponseResult doneCustomException(CustomException customException) {
        return new ResponseResult(customException.getResultCode());
    }
}
