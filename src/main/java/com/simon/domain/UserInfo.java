package com.simon.domain;

import javax.persistence.*;

/**
 * Created by simon on 2016/8/13.
 */
@Table(name = "user_info")
@Entity
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String username;
    private String phone;
    private String email;
    private Boolean sex;
    private Integer age;
    private String birth;

    private String personBrief;

    private String headPhoto;

    private String visitCard;

    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPersonBrief() {
        return personBrief;
    }

    public void setPersonBrief(String personBrief) {
        this.personBrief = personBrief;
    }

    public String getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }

    public String getVisitCard() {
        return visitCard;
    }

    public void setVisitCard(String visitCard) {
        this.visitCard = visitCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", birth='" + birth + '\'' +
                ", personBrief='" + personBrief + '\'' +
                ", headPhoto='" + headPhoto + '\'' +
                ", visitCard='" + visitCard + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
