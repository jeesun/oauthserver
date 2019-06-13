package com.simon.service;

import com.simon.common.service.BasicService;
import com.simon.dto.FontAwesomeDto;
import com.simon.model.FontAwesome;

import java.util.List;

/**
* @author SimonSun
* @date 2019-04-29
**/
public interface FontAwesomeService extends BasicService<FontAwesome, Integer> {
    /**
     * 获取Font Awesome字体dto列表
     * @param language 语言
     * @return Font Awesome字体dto列表
     */
    List<FontAwesomeDto> getDtos(String language);

    /**
     * 根据图标class统计记录个数
     * @param iconClass 图标class
     * @return 记录个数
     */
    int countByIconClass(String iconClass);
}