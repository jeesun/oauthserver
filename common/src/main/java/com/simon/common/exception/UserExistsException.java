package com.simon.common.exception;

/**
 * 用户已存在
 *
 * @author simon
 * @date 2018-12-07
 **/

public class UserExistsException extends RuntimeException {
    private static final long serialVersionUID = 848351055098685051L;

    public UserExistsException(){

    }

    public UserExistsException(String message){
        super(message);
    }
}
