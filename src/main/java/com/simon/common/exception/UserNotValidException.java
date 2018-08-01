package com.simon.common.exception;

/**
 * 用户无效
 *
 * @author simon
 * @create 2018-07-31 10:17
 **/

public class UserNotValidException extends RuntimeException {
    public UserNotValidException() {

    }

    public UserNotValidException(String message) {
        super(message);
    }
}
