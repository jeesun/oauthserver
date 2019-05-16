package com.simon.service;

import com.simon.dto.GenCodeDto;

/**
 * 数据表
 * @author simon
 * @date 2019-05-04
 */
public interface TableService {
    /**
     * 保存用户生成代码时的UI属性配置。
     * 代码生成时，向t_side_menu表添加访问权限数据。
     * @param body
     */
    void saveSettingsAndAuthorities(GenCodeDto body);
}
