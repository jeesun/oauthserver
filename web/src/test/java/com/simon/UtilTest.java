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
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "birth_day".toLowerCase()));
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "birth_day".toLowerCase()));
    }
}
