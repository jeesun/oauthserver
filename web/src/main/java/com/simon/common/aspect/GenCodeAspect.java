package com.simon.common.aspect;

import com.simon.service.SideMenuService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponseWrapper;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 代码生成切面
 * 目的：代码生成后，向地址权限表t_side_menu添加数据
 *
 * @author simon
 * @date 2019-01-10
 **/
@Slf4j
@Aspect
@Component
@Order(2)
public class GenCodeAspect {
    @Autowired
    private SideMenuService sideMenuService;

    /**
     * 创建Pointcut表示式，表示代码生成请求
     */
    @Pointcut("execution(* com..*.controller.TableController.genCode(..))")
    private void controllerAspect() {
    }// 请求method前打印内容

    @Around(value = "controllerAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //通过uuid关联请求参数和返回参数
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            Object proceed = pjp.proceed();
            methodAfterReturning(pjp, uuid);
            return proceed;
        } catch (Exception e) {
            log.error("[{}]Response异常内容:{}", uuid, e);
            throw e;
        }
    }

    private void methodAfterReturning(JoinPoint joinPoint, String uuid) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();


        // 打印请求内容
        try {
            // 下面两个数组中，参数值和参数名的个数和位置是一一对应的。
            Object[] objs = joinPoint.getArgs();
            // 参数名
            String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
            Map<String, Object> paramMap = new LinkedHashMap<>();
            for (int i = 0; i < objs.length; i++) {
                if (!(objs[i] instanceof ExtendedServletRequestDataBinder) && !(objs[i] instanceof HttpServletResponseWrapper)) {
                    paramMap.put(argNames[i], objs[i]);
                }
            }

            //获取请求参数实体类名entityName
            String entityName = String.valueOf(paramMap.get("entityName"));
            //父菜单id
            Long pid = Long.valueOf(paramMap.get("pid").toString());
            //允许访问要生成的页面的角色
            String allowedRoles = String.valueOf(paramMap.get("allowedRoles"));
            //表注释
            String tableComment = String.valueOf(paramMap.get("tableComment"));

            //向地址权限表t_side_menu添加数据
            sideMenuService.insertOrUpdateByEntityName(entityName, pid, allowedRoles, tableComment);

        } catch (Exception e) {
            log.error("[{}]AOP methodBefore:", uuid, e);
        }
    }
}
