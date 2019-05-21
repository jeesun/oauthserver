package com.simon.common.aspect;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author simon
 * @date 2018-12-25
 **/

@Slf4j
@Aspect
@Component
@Order(1)
public class ControllerLogInterceptor {

    //创建Pointcut表示式，表示所有controller请求
    @Pointcut("execution(* com..*.controller..*(..))")
    private void controllerAspect() {
    }// 请求method前打印内容

    @Around(value = "controllerAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //通过uuid关联请求参数和返回参数
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //String uuid = "";
        methodBefore(pjp, uuid);
        try {
            Object proceed = pjp.proceed();
            methodAfterReturing(proceed, uuid);
            return proceed;
        } catch (Exception e) {
            log.error("[{}]Response异常内容:{}", uuid, e);
            throw e;
        }
    }

    public void methodBefore(JoinPoint joinPoint, String uuid) {
        // 打印请求内容
        try {
            // 下面两个数组中，参数值和参数名的个数和位置是一一对应的。
            Object[] args = joinPoint.getArgs();
            String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames(); // 参数名
            Map<String, Object> paramMap = new HashMap<>();

            //序列化时过滤掉request和response
            List<Object> objs = Arrays.stream(args)
                    .filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse) && !(arg instanceof HttpSession) && !(arg instanceof ExtendedServletRequestDataBinder) && !(arg instanceof HttpServletResponseWrapper)))
                    .collect(Collectors.toList());

            for (int i = 0; i < objs.size(); i++) {
                paramMap.put(argNames[i], objs.get(i));
            }
            if (paramMap.size() > 0) {
                //log.info("[{}]类名1:{}", uuid, joinPoint.getTarget().getClass().getName());
                //log.info("[{}]类名2:{}", uuid, joinPoint.getTarget().getClass().getSimpleName());
                log.info("[{}]方法:{}", uuid, joinPoint.getSignature());
                log.info("[{}]参数:{}", uuid, JSONObject.toJSONString(paramMap));
            }
        } catch (Exception e) {
            log.error("[{}]AOP methodBefore:", uuid, e);
        }
    }

    public void methodAfterReturing(Object o, String uuid) {
        try {
            if (o != null){
                log.info("[{}]返回:{}", uuid, JSONObject.toJSON(o));
            }
        } catch (Exception e) {
            log.error("[{}]AOP methodAfterReturing:", uuid, e);
        }
    }
}