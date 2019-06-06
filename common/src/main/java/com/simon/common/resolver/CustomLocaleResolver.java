package com.simon.common.resolver;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * 自定义国际化语言解析器
 * @author simon
 * @date 2019-05-24
 */
public class CustomLocaleResolver implements LocaleResolver {
    private static final String I18N_LANGUAGE = "locale";
    private static final String I18N_LANGUAGE_SESSION = "locale";

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String i18n_language = request.getParameter(I18N_LANGUAGE);
        Locale locale = Locale.getDefault();
        if (!StringUtils.isEmpty(i18n_language)) {
            String[] language = i18n_language.split("_");
            locale = new Locale(language[0], language[1]);

            //将国际化语言保存到session
            HttpSession session = request.getSession();
            session.setAttribute(I18N_LANGUAGE_SESSION, locale);
        } else {
            //如果没有带国际化参数，则判断session有没有保存，有保存，则使用保存的，也就是之前设置的，避免之后的请求不带国际化参数造成语言显示不对
            HttpSession session = request.getSession();
            Locale localeInSession = (Locale) session.getAttribute(I18N_LANGUAGE_SESSION);
            if (null != localeInSession) {
                locale = localeInSession;
            }
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
