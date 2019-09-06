package com.simon;

/**
 * 测试
 *
 * @author simon
 * @date 2019-01-30
 **/

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ApiApplicationTest {
    /*@Autowired
    private JavaMailSender mailSender;*/

    /*@Value("${spring.mail.username}")
    private String sender; //读取配置文件中的参数*/

    //@Test
    public void contextLoads() {
    }

    //@Test
    /*public void sendEmail(){
        StringBuffer emailContent = new StringBuffer();
        emailContent.append("请勿回复本邮件.点击下面的链接,重设密码,本邮件超过30分钟,链接将会失效，需要重新申请找回密码。");
        emailContent.append("http://localhost:8081" + "/users/resetPassword?sid=" + "1" + "&id=" + 1);

        //发送邮件
        //此处耗时很长，可以考虑使用MQ异步，立即返回发送成功的默认结果。
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo("18860902711@163.com");
        message.setSubject("主题：密码重置");
        message.setText(emailContent.toString());
        mailSender.send(message);
    }*/
}
