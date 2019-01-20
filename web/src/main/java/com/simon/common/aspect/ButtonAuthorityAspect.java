package com.simon.common.aspect;

import com.simon.dto.ButtonAuthorityDto;
import com.simon.service.SideMenuService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.List;

/**
 * 按钮权限切面
 * 目的：向list页面Model添加按钮权限信息，用于显示或者隐藏新增、修改、删除按钮
 *
 * @author simon
 * @date 2019-01-12
 **/
@Slf4j
@Aspect
@Component
@Order(3)
public class ButtonAuthorityAspect {
    @Autowired
    private SideMenuService sideMenuService;

    @Pointcut("execution(public * com..*.*Controller.list(..))")
    public void listMethod() {
    }

    @Around("listMethod()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        for(Object object : pjp.getArgs()){
            if(object instanceof Model){
                Model model = (Model) object;
                List<ButtonAuthorityDto> buttonAuthorityDtoList = sideMenuService.findButtonAuthorityDtoByEntityName("NewsInfo");
                if (null != buttonAuthorityDtoList && buttonAuthorityDtoList.size() > 0){
                    buttonAuthorityDtoList.forEach(item -> model.addAttribute(item.getRemark(), item.getAuthority()));
                }
            }
        }

        return pjp.proceed();
    }
}
