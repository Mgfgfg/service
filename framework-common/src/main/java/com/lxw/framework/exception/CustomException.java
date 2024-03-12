package com.lxw.framework.exception;

import com.lxw.framework.model.response.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private ResultCode resultCode;
}
