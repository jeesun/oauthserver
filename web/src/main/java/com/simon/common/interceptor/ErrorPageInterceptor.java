package com.simon.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 错误页面拦截器
 *
 * @author simon
 * @create 2018-06-13 1:39
 **/
@Slf4j
/*@Component*/
public class ErrorPageInterceptor extends HandlerInterceptorAdapter {
    private List<Integer> errorCodeList = Arrays.asList(404, 403, 500, 501);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        //log.info("status=" + response.getStatus());
        if (errorCodeList.contains(response.getStatus())) {
            response.sendRedirect("error/" + response.getStatus());
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
