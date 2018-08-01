package com.simon.handler;

import com.simon.annotation.CurrentUser;
import com.simon.model.OauthUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * 参数解析器
 *
 * @author simon
 * @create 2018-07-22 10:49
 **/

public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(OauthUser.class)
                && methodParameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        OauthUser oauthUser = (OauthUser) nativeWebRequest.getAttribute("currentUser", RequestAttributes.SCOPE_REQUEST);
        if(null != oauthUser){
            return oauthUser;
        }
        throw new MissingServletRequestPartException("currentUser");
    }
}
