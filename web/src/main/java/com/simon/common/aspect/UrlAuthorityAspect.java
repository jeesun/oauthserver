package com.simon.common.aspect;

import com.simon.common.domain.UserEntity;
import com.simon.model.Authority;
import com.simon.service.SideMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponseWrapper;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 地址授权切面
 * 目的：判断是否有权限访问当前地址
 *
 * @author simon
 * @date 2019-01-10
 **/
@Slf4j
@Aspect
@Component
@Order(1)
public class UrlAuthorityAspect {
    @Autowired
    private SideMenuService sideMenuService;

    /**
     * 创建Pointcut表示式，表示所有controller请求
     */
    @Pointcut("execution(* com..*.controller..*(..))" +
            " && (@annotation(org.springframework.web.bind.annotation.GetMapping))" +
            " && (!@annotation(javax.annotation.security.PermitAll))" +
            " && (!@annotation(javax.annotation.security.RolesAllowed))" +
            " && (!@annotation(javax.annotation.security.DenyAll))" +
            " && (!@annotation(org.springframework.security.access.prepost.PreAuthorize))" +
            " && (!@annotation(org.springframework.security.access.prepost.PostAuthorize))" +
            " && (!@annotation(org.springframework.security.access.prepost.PreFilter))" +
            " && (!@annotation(org.springframework.security.access.annotation.Secured))")
    private void getMappingAspect() {
    }// 请求method前打印内容

    @Pointcut("execution(* com..*.controller..*(..))" +
            " && (@annotation(org.springframework.web.bind.annotation.PostMapping))" +
            " && (!@annotation(javax.annotation.security.PermitAll))" +
            " && (!@annotation(javax.annotation.security.RolesAllowed))" +
            " && (!@annotation(javax.annotation.security.DenyAll))" +
            " && (!@annotation(org.springframework.security.access.prepost.PreAuthorize))" +
            " && (!@annotation(org.springframework.security.access.prepost.PostAuthorize))" +
            " && (!@annotation(org.springframework.security.access.prepost.PreFilter))" +
            " && (!@annotation(org.springframework.security.access.annotation.Secured))")
    private void postMappingAspect() {
    }

    @Pointcut("execution(* com..*.controller..*(..))" +
            " && (@annotation(org.springframework.web.bind.annotation.DeleteMapping))" +
            " && (!@annotation(javax.annotation.security.PermitAll))" +
            " && (!@annotation(javax.annotation.security.RolesAllowed))" +
            " && (!@annotation(javax.annotation.security.DenyAll))" +
            " && (!@annotation(org.springframework.security.access.prepost.PreAuthorize))" +
            " && (!@annotation(org.springframework.security.access.prepost.PostAuthorize))" +
            " && (!@annotation(org.springframework.security.access.prepost.PreFilter))" +
            " && (!@annotation(org.springframework.security.access.annotation.Secured))")
    private void deleteMappingAspect() {
    }

    @Pointcut("execution(* com..*.controller..*(..))" +
            " && (@annotation(org.springframework.web.bind.annotation.PatchMapping))" +
            " && (!@annotation(javax.annotation.security.PermitAll))" +
            " && (!@annotation(javax.annotation.security.RolesAllowed))" +
            " && (!@annotation(javax.annotation.security.DenyAll))" +
            " && (!@annotation(org.springframework.security.access.prepost.PreAuthorize))" +
            " && (!@annotation(org.springframework.security.access.prepost.PostAuthorize))" +
            " && (!@annotation(org.springframework.security.access.prepost.PreFilter))" +
            " && (!@annotation(org.springframework.security.access.annotation.Secured))")
    private void patchMappingAspect() {
    }

    @Pointcut("execution(* com..*.controller..*(..))" +
            " && (@annotation(org.springframework.web.bind.annotation.RequestMapping))" +
            " && (!@annotation(javax.annotation.security.PermitAll))" +
            " && (!@annotation(javax.annotation.security.RolesAllowed))" +
            " && (!@annotation(javax.annotation.security.DenyAll))" +
            " && (!@annotation(org.springframework.security.access.prepost.PreAuthorize))" +
            " && (!@annotation(org.springframework.security.access.prepost.PostAuthorize))" +
            " && (!@annotation(org.springframework.security.access.prepost.PreFilter))" +
            " && (!@annotation(org.springframework.security.access.annotation.Secured))")
    private void requestMappingAspect() {
    }

    @Around(value = "getMappingAspect() || postMappingAspect() || deleteMappingAspect() || patchMappingAspect() || requestMappingAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //通过uuid关联请求参数和返回参数
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //String uuid = "";
        methodBefore(pjp, uuid);
        try {
            Object proceed = pjp.proceed();
            methodAfterReturning(proceed, uuid);
            return proceed;
        } catch (Exception e) {
            log.error("[{}]Response异常内容:{}", uuid, e);
            throw e;
        }
    }

    private void methodBefore(JoinPoint joinPoint, String uuid) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        //log.info("[{}]请求地址:{}", uuid, request.getRequestURI());
        //log.info("[{}]请求方法:{}", uuid, request.getMethod());

        //权限判断
        String requiredAuthority = sideMenuService.findAuthorityByUrlAndRequestMethod(request.getRequestURI(), request.getMethod());
        if (StringUtils.isNotEmpty(requiredAuthority)){
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserEntity userEntity = null;
            if(principal instanceof UserEntity){
                userEntity = (UserEntity)principal;
            }
            List<Authority> userAuthorities = userEntity.getAuthorities();
            boolean flag = false;
            for(int i = 0; i < userAuthorities.size(); i++){
                if (requiredAuthority.contains(userAuthorities.get(i).getAuthority())){
                    flag = true;
                    break;
                }
            }
            if (!flag){
                throw new AccessDeniedException("您没有权限访问");
            }
        }

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
            if (paramMap.size() > 0) {
                //log.info("[{}]方法:{}", uuid, joinPoint.getSignature());
                //log.info("[{}]参数:{}", uuid, JSONObject.toJSONString(paramMap));
            }
        } catch (Exception e) {
            //log.error("[{}]AOP methodBefore:", uuid, e);
        }
    }

    private void methodAfterReturning(Object o, String uuid) {
        try {
            if (o != null){
                //log.info("[{}]返回:{}", uuid, JSONObject.toJSON(o));
            }
        } catch (Exception e) {
            //log.error("[{}]AOP methodAfterReturning:", uuid, e);
        }
    }
}
