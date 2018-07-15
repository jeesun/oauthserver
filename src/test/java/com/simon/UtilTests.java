package com.simon;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UtilTests {
    @Test
    public void jasyptTest(){
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword("19941017");//application.yml配置的jasypt.encryptor.password
        String encrypted = encryptor.encrypt("postgres");//要加密的数据（数据库连接的用户名或密码）
        System.out.println(encrypted);
    }

    @Test
    public void bCryptPasswordEncoderTest(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
        String passEncode = encoder.encode("secret");
        System.out.println(passEncode);
    }
}
