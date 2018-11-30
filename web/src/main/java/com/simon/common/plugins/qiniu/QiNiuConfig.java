package com.simon.common.plugins.qiniu;

import com.qiniu.common.Zone;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * 七牛云配置
 *
 * @author simon
 * @create 2018-08-15 10:44
 **/
@Slf4j
@Data
public class QiNiuConfig {
    private String privateAccessKey;
    private String privateSecretKey;
    private String privateBucket;
    private Zone privateZone;
    private String privateDomainOfBucket;
    private long privateExpireInSeconds;

    private String publicAccessKey;
    private String publicSecretKey;
    private String publicBucket;
    private Zone publicZone;
    private String publicDomainOfBucket;
    private long publicExpireInSeconds;

    private static QiNiuConfig instance = new QiNiuConfig();

    private QiNiuConfig(){
        Properties prop = new Properties();
        try {
            prop.load(QiNiuConfig.class.getResourceAsStream("/application.properties"));
            privateAccessKey = prop.getProperty("qiniu.private.access-key");
            privateSecretKey = prop.getProperty("qiniu.private.secret-key");
            privateBucket = prop.getProperty("qiniu.private.bucket");
            privateDomainOfBucket = prop.getProperty("qiniu.private.domain-of-bucket");
            privateExpireInSeconds = Long.parseLong(prop.getProperty("qiniu.private.expire-in-seconds"));
            String zoneName = prop.getProperty("qiniu.private.zone");
            if(zoneName.equals("zone0")){
                privateZone = Zone.zone0();
            }else if(zoneName.equals("zone1")){
                privateZone = Zone.zone1();
            }else if(zoneName.equals("zone2")){
                privateZone = Zone.zone2();
            }else if(zoneName.equals("zoneNa0")){
                privateZone = Zone.zoneNa0();
            }else if(zoneName.equals("zoneAs0")){
                privateZone = Zone.zoneAs0();
            }else{
                throw new Exception("Zone对象配置错误！");
            }

            publicAccessKey = prop.getProperty("qiniu.public.access-key");
            publicSecretKey = prop.getProperty("qiniu.public.secret-key");
            publicBucket = prop.getProperty("qiniu.public.bucket");
            publicDomainOfBucket = prop.getProperty("qiniu.public.domain-of-bucket");
            publicExpireInSeconds = Long.parseLong(prop.getProperty("qiniu.public.expire-in-seconds"));
            String publicZoneName = prop.getProperty("qiniu.public.zone");
            if(publicZoneName.equals("zone0")){
                publicZone = Zone.zone0();
            }else if(publicZoneName.equals("zone1")){
                publicZone = Zone.zone1();
            }else if(publicZoneName.equals("zone2")){
                publicZone = Zone.zone2();
            }else if(publicZoneName.equals("zoneNa0")){
                publicZone = Zone.zoneNa0();
            }else if(publicZoneName.equals("zoneAs0")){
                publicZone = Zone.zoneAs0();
            }else{
                throw new Exception("Zone对象配置错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static QiNiuConfig getInstance(){
        return instance;
    }
    public static void main(String[] args) {
        System.out.println(QiNiuConfig.getInstance().getPrivateAccessKey());
    }
}
