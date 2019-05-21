package com.simon.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author simon
 * @date 2019-01-15
 **/
@ApiModel(description = "侧栏菜单请求")
@Data
@EqualsAndHashCode(callSuper = false)
public class SideMenuAuthorityRequest implements Serializable {
    private static final long serialVersionUID = -6446735244844186753L;

    private List<Long> ids;

    private String authority;
}
