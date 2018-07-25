package com.simon.model;

import javax.persistence.*;

/**
 * @author simon
 * @create 2018-07-25 22:17
 **/

@Entity
@Table(name = "veri_code")
public class VeriCode {
    private long id;
    private Integer code;
    private Long createTime;
    private Integer expires;
    private String phone;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code")
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Basic
    @Column(name = "create_time")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "expires")
    public Integer getExpires() {
        return expires;
    }

    public void setExpires(Integer expires) {
        this.expires = expires;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VeriCode veriCode = (VeriCode) o;

        if (id != veriCode.id) return false;
        if (code != null ? !code.equals(veriCode.code) : veriCode.code != null) return false;
        if (createTime != null ? !createTime.equals(veriCode.createTime) : veriCode.createTime != null) return false;
        if (expires != null ? !expires.equals(veriCode.expires) : veriCode.expires != null) return false;
        if (phone != null ? !phone.equals(veriCode.phone) : veriCode.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (expires != null ? expires.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
