package com.simon.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.simon.common.handler.CurrentUserMethodArgumentResolver;
import com.simon.common.interceptor.AuthInterceptor;
import com.simon.common.resolver.CustomLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.math.BigInteger;
import java.util.List;

/**
 * 静态资源拦截
 *
 * @author simon
 * @create 2018-07-21 19:20
 **/
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    /*@Autowired
    private ErrorPageInterceptor errorPageInterceptor;*/

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        //资源国际化变化拦截
        registry.addInterceptor(localeChangeInterceptor()).addPathPatterns("/**");
        //registry.addInterceptor(errorPageInterceptor);//.addPathPatterns("/action/**", "/mine/**");默认所有
        //registry.addInterceptor(authInterceptor()).addPathPatterns("/**").excludePathPatterns("**/swagger-ui.html");//token登录拦截
        //registry.addInterceptor(authInterceptor());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        //argumentResolvers.add(currentUserMethodArgumentResolver());
    }

    private MappingJackson2HttpMessageConverter jackson2HttpMessageConverter(){
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        //定义对象映射器
        ObjectMapper objectMapper = new ObjectMapper();
        //解决jackson java8时间类型格式化问题
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        //对于Long 类型的数据，如果在Controller层通过@ResponseBody将返回数据自动转换成json时，不做任何处理，而直接传给前端的话，在Long长度大于17位时会出现精度丢失的问题。
        //将Long类型的数据转换成字符串，解决Long类型数据传入前端精度丢失的问题
        //定义对象模型
        SimpleModule simpleModule = new SimpleModule();
        //添加对长整型的转换关系
        simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        //将对象模型添加至对象映射器
        objectMapper.registerModule(simpleModule);
        //将对象映射器添加至Json转换器
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        return jackson2HttpMessageConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jackson2HttpMessageConverter());
    }

    @Bean
    public LocaleResolver localeResolver() {
        /*SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return slr;*/
        return new CustomLocaleResolver();
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        //默认值就是locale
        lci.setParamName("locale");
        return lci;
    }

    @Bean
    public AuthInterceptor authInterceptor(){
        return new AuthInterceptor();
    }

    @Bean
    public CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver(){
        return new CurrentUserMethodArgumentResolver();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addViewController("/login").setViewName("login");
        //registry.addViewController("/").setViewName("index");
        //registry.addViewController("/index").setViewName("index");
        registry.addViewController("/index_iframe").setViewName("index_iframe");
        registry.addViewController("/demo/file_upload_demo").setViewName("demo/file_upload_demo");
        registry.addViewController("/demo/bootstrap_demo").setViewName("demo/bootstrap_demo");
        registry.addViewController("/demo/froala_editor_demo").setViewName("demo/froala_editor_demo");
        registry.addViewController("/demo/roll_demo").setViewName("demo/roll_demo");
        registry.addViewController("/easyui/index").setViewName("easyui/index");
        registry.addViewController("/easyui/tab1").setViewName("easyui/tab1");
        registry.addViewController("/easyui/tab2").setViewName("easyui/tab2");
        registry.addViewController("/easyui/tab3").setViewName("easyui/tab3");
        registry.addViewController("/easyui/oauth_user").setViewName("easyui/oauth_user");
        registry.addViewController("/easyui/home.html").setViewName("easyui/home");

        //app用户隐私协议
        registry.addViewController("/protocol.html").setViewName("protocol");

        registry.addViewController("/vue/home").setViewName("vue/home");
        registry.addViewController("/vue/demo/basic").setViewName("vue/demo/basic");
        registry.addViewController("/vue/demo/ueditor").setViewName("vue/demo/ueditor");
        registry.addViewController("/vue/demo/layer-ueditor").setViewName("vue/demo/layer-ueditor");
        registry.addViewController("/vue/demo/vue-ueditor").setViewName("vue/demo/vue-ueditor");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/fileUpload/**").addResourceLocations("file:fileUpload/");
        super.addResourceHandlers(registry);
    }
}
