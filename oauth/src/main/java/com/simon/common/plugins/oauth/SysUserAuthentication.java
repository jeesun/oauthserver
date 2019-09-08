package com.simon.common.plugins.oauth;

import lombok.Data;

import java.io.Serializable;

/**
 * @author simon
 * @date 2018-11-30
 **/

@Data
public class SysUserAuthentication implements Serializable {
    private static final long serialVersionUID = -7682241372040181443L;

    private Long id;

    private String username;

    private String password;

    private String email;

    private String phoneNumber;

    private String status;

    private String name;

    private String type;
}
