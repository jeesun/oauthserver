package com.simon.common.exception;

/**
 * 手机号已注册
 *
 * @author simon
 * @create 2018-07-31 15:31
 **/

public class PhoneRegisteredException extends RuntimeException {
    public PhoneRegisteredException() {
    }

    public PhoneRegisteredException(String message) {
        super(message);
    }
}
