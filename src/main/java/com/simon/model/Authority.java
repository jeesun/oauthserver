package com.simon.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
* @author SimonSun
* @create 2018-08-17 23:33:01
**/
@ApiModel(description = "Authority")
@Data
@Entity
@Table(name="authorities")
public class Authority implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @ApiModelProperty(value = "user_id")
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ApiModelProperty(value = "authority")
    @Column(name = "authority", nullable = false)
    private String authority;
}