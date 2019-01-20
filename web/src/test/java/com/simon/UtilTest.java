package com.simon;

import com.google.common.base.CaseFormat;
import org.junit.Test;

/**
 * @author simon
 * @date 2018-12-06
 **/

public class UtilTest {
    @Test
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

    @Test
    public void strTest(){
        String templateName = "list.ftl";
        System.out.println(templateName.substring(0, templateName.indexOf(".")));
    }

    @Test
    public void str2Test(){
        String url = "/api/newsInfos/ids/1";
        System.out.println(url.substring(0, url.lastIndexOf("ids") + 3));
    }
}
