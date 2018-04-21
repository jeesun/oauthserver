package com.simon;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;

public class UtilTests {
    @Test
    public void jasyptTest(){
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword("19941017");//application.yml配置的jasypt.encryptor.password
        String encrypted = encryptor.encrypt("postgres");//要加密的数据（数据库连接的用户名或密码）
        System.out.println(encrypted);
    }
}
