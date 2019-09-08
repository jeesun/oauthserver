package com.simon;

import com.google.common.base.CaseFormat;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author simon
 * @date 2018-12-06
 **/
@Slf4j
public class UtilTest {
    //@Test
    public void codeTest(){
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "test-data"));
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "test_data"));

        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "testdata"));
        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "TestData"));
        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "testData"));

        CaseFormat fromFormat = CaseFormat.LOWER_CAMEL;
        CaseFormat toFormat = CaseFormat.UPPER_CAMEL;
        String s = "LowerCamel";
        System.out.println(toFormat.to(fromFormat, s));
    }

    //@Test
    public void strTest(){
        String templateName = "list.ftl";
        System.out.println(templateName.substring(0, templateName.indexOf(".")));
    }

    //@Test
    public void str2Test(){
        String url = "/api/newsInfos/ids/1";
        System.out.println(url.substring(0, url.lastIndexOf("ids") + 3));
    }

    @Test
    public void dirTest(){
        System.out.println("路径为" + UtilTest.class.getResource("/").getPath());
    }

    @Test
    public void messageFormatTest() {
        String msg = MessageFormat.format("尊敬的用户，您的订单已支付成功，我们会尽快安排发货，如果您有任何疑问，欢迎拨打客服热线：4008782968。", "21");
        System.out.println(msg);

        System.out.println(StringUtils.isNumericSpace("15.01"));
    }

    @Test
    public void arrayTest() {
        Map<Object, Object> map = new LinkedHashMap<>();

        System.out.println();
    }

    @Test
    public void demoTest() {
        log.info("{}-{}-{}", 2019, 5, 6);
    }
}
