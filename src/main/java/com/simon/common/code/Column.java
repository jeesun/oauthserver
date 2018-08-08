package com.simon.common.code;

/**
 * @author simon
 * @create 2018-08-07 21:14
 **/

import lombok.Data;

import java.io.Serializable;

/**
 * 代码生成列实体
 * Created by wangqichang on 2018/5/30.
 */
@Data
public class Column implements Serializable{
    private static final long serialVersionUID = 8051725962502921942L;
    /**
     * 属性注解
     */
    private String annotation;

    /**
     * 属性名
     */
    private String name;

    /**
     * 属性类型
     */
    private String type;

    /**
     * 属性注释
     */
    private String comment;
}