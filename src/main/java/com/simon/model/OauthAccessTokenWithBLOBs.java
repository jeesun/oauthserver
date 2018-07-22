package com.simon.model;

public class OauthAccessTokenWithBLOBs extends OauthAccessToken {
    private byte[] token;

    private byte[] authentication;

    public byte[] getToken() {
        return token;
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    public byte[] getAuthentication() {
        return authentication;
    }

    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }
}