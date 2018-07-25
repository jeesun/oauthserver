package com.simon.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author simon
 * @create 2018-07-25 22:17
 **/

@Entity
@Table(name = "reset_password_info")
public class ResetPwdInfo {
    private long id;
    private Timestamp expiresIn;
    private String secretKey;
    private Long userId;
    private boolean valid;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "expires_in")
    public Timestamp getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Timestamp expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Basic
    @Column(name = "secret_key")
    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Basic
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "valid")
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResetPwdInfo that = (ResetPwdInfo) o;

        if (id != that.id) return false;
        if (valid != that.valid) return false;
        if (expiresIn != null ? !expiresIn.equals(that.expiresIn) : that.expiresIn != null) return false;
        if (secretKey != null ? !secretKey.equals(that.secretKey) : that.secretKey != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (expiresIn != null ? expiresIn.hashCode() : 0);
        result = 31 * result + (secretKey != null ? secretKey.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (valid ? 1 : 0);
        return result;
    }
}
