package com.simon.common.exception;

import com.simon.common.domain.ResultCode;

/**
 * 业务异常
 *
 * @author simon
 * @date 2018-12-20
 **/

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -1004796665465965612L;

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(ResultCode resultCode){
        super(resultCode.getMsg());
    }
}
