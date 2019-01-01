package com.simon.common.wrapper;

import com.google.common.base.CaseFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;

/**
 * orderBy wrapper
 *
 * @author simon
 * @date 2018-11-23
 **/

public class OrderByRequestWrapper extends HttpServletRequestWrapper {
    public OrderByRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return super.getParameterNames();
    }

    @Override
    public String[] getParameterValues(String name) {
        if("orderBy".equals(name)){
            return new String[]{CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, getParameter("orderBy"))};
        }
        return super.getParameterValues(name);
    }
}
