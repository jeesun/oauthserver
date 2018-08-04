package com.simon;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Map;

public class UtilTests {
    @Test
    public void jasyptTest(){
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword("19941017");//application.yml配置的jasypt.encryptor.password
        String encrypted = encryptor.encrypt("19941017");//要加密的数据（数据库连接的用户名或密码）
        System.out.println(encrypted);
    }

    @Test
    public void bcryptTest(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
        System.out.println(encoder.encode("1234567890c"));
    }

    @Test
    public void pathTest(){
        System.out.println(System.getProperty("user.dir"));
    }

    @Test
    public void yamlTest(){
        try {
            Yaml yaml = new Yaml();
            URL url = Test.class.getClassLoader().getResource("application.yml");
            if (url != null) {
                //获取test.yaml文件中的配置数据，然后转换为obj，
                Object obj =yaml.load(new FileInputStream(url.getFile()));
                System.out.println(obj);
                //也可以将值转换为Map
                Map<String, Object> map =(Map<String, Object>)yaml.load(new FileInputStream(url.getFile()));
                System.out.println(map.get("spring"));
                Map<String, Object> spring = (Map<String, Object>) map.get("spring");
                System.out.println(spring.get("datasource"));
                Map<String, Object> datasource= (Map<String, Object>) spring.get("datasource");
                System.out.println(datasource.get("url"));
                //通过map我们取值就可以了.

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
