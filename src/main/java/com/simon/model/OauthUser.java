package com.simon.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class OauthUser implements Serializable {
    private static final long serialVersionUID = 7928736608744438474L;
    @Id
    @GeneratedValue(generator = "sequenceId")
    @GenericGenerator(name = "sequenceId", strategy = "com.simon.common.utils.snowflake.SequenceId")
    private Long id;

    private String username;

    private String password;

    private Boolean enabled;

    private String email;

    private String phone;

    private String address;

    private Integer age;

    private String birth;

    @Column(name = "head_photo")
    private String headPhoto;

    @Column(name = "person_brief")
    private String personBrief;

    private Boolean sex;

    @Column(name = "visit_card")
    private String visitCard;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return birth
     */
    public String getBirth() {
        return birth;
    }

    /**
     * @param birth
     */
    public void setBirth(String birth) {
        this.birth = birth;
    }

    /**
     * @return head_photo
     */
    public String getHeadPhoto() {
        return headPhoto;
    }

    /**
     * @param headPhoto
     */
    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }

    /**
     * @return person_brief
     */
    public String getPersonBrief() {
        return personBrief;
    }

    /**
     * @param personBrief
     */
    public void setPersonBrief(String personBrief) {
        this.personBrief = personBrief;
    }

    /**
     * @return sex
     */
    public Boolean getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    /**
     * @return visit_card
     */
    public String getVisitCard() {
        return visitCard;
    }

    /**
     * @param visitCard
     */
    public void setVisitCard(String visitCard) {
        this.visitCard = visitCard;
    }
}