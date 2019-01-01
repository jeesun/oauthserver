package com.simon.common.exception;

/**
 * 用户无效
 *
 * @author simon
 * @create 2018-07-31 10:17
 **/

public class UserNotValidException extends RuntimeException {
    private static final long serialVersionUID = 6379257682308144691L;

    public UserNotValidException() {

    }

    public UserNotValidException(String message) {
        super(message);
    }
}
