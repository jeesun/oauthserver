package com.simon.common.exception;

/**
 * 注册异常
 *
 * @author simon
 * @date 2018-12-07
 **/

public class RegisterException extends RuntimeException {
    private static final long serialVersionUID = -2606099856338881866L;

    public RegisterException(){

    }

    public RegisterException(String message){
        super(message);
    }
}
