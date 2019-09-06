package com.simon.common.plugins.qiniu;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 七牛上传下载工具类
 *
 * @author simon
 * @date 2018-08-15
 **/
@Slf4j
@Data
public class QiNiuUtil {
    public enum ZoneType{
        ZONE_PUBLIC, ZONE_PRIVATE
    }
    
    private static QiNiuConfig qiNiuConfig = QiNiuConfig.getInstance();

    private ZoneType zoneType;

    private String accessKey;
    private String secretKey;
    private String bucket;
    private Zone zone;
    private String domainOfBucket;
    private long expireInSeconds;

    private static QiNiuUtil instance = new QiNiuUtil();
    private static QiNiuUtil privateInstance = new QiNiuUtil();
    private static QiNiuUtil publicInstance = new QiNiuUtil();

    static {
        publicInstance.setZoneType(ZoneType.ZONE_PUBLIC);
        publicInstance.setAccessKey(qiNiuConfig.getPublicAccessKey());
        publicInstance.setSecretKey(qiNiuConfig.getPublicSecretKey());
        publicInstance.setBucket(qiNiuConfig.getPublicBucket());
        publicInstance.setZone(qiNiuConfig.getPublicZone());
        publicInstance.setDomainOfBucket(qiNiuConfig.getPublicDomainOfBucket());
        publicInstance.setExpireInSeconds(qiNiuConfig.getPublicExpireInSeconds());

        privateInstance.setZoneType(ZoneType.ZONE_PRIVATE);
        privateInstance.setAccessKey(qiNiuConfig.getPrivateAccessKey());
        privateInstance.setSecretKey(qiNiuConfig.getPrivateSecretKey());
        privateInstance.setBucket(qiNiuConfig.getPrivateBucket());
        privateInstance.setZone(qiNiuConfig.getPrivateZone());
        privateInstance.setDomainOfBucket(qiNiuConfig.getPrivateDomainOfBucket());
        privateInstance.setExpireInSeconds(qiNiuConfig.getPrivateExpireInSeconds());
    }

    private QiNiuUtil(){

    }

    public static QiNiuUtil getInstance(){
        return instance;
    }

    public QiNiuUtil setZoneType(ZoneType zoneType){
        this.zoneType = zoneType;

        if(zoneType== ZoneType.ZONE_PUBLIC){
            return publicInstance;
        }else {
            return privateInstance;
        }
    }
    /**
     * 上传文件
     * @param localFilePath 本地文件地址
     * @param key
     * @param override
     * @return
     */
    public boolean upload(String localFilePath, String key, boolean override){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(zone);
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken;
        if(override){
            //覆盖上传凭证
            upToken = auth.uploadToken(bucket, key);
        }else{
            upToken = auth.uploadToken(bucket);
        }
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            return true;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
                return false;
            }
            return false;
        }
    }

    /**
     * 上传Base64图片
     * @param l 图片没经过 base64 处理的原图字节大小
     * @param file64 图片base64字符串
     * @param key 要存储的图片路径
     * @param override
     * @return
     * @throws IOException
     */
    public boolean uploadBase64(String file64, String key, boolean override) throws IOException{
        System.out.println(key);
        //base64图片需要强制变成png，七牛云不支持jpg上传
        file64 = convertToPng(file64);

        /*FileInputStream fis = null;
        int l = (int) (new File(localFilePath).length());
        byte[] src = new byte[l];
        try {
            fis = new FileInputStream(new File(localFilePath));
            fis.read(src);
        }catch (FileNotFoundException e){
            e.printStackTrace();
            log.error(e.getMessage());
            log.error("图片文件读取失败");
            return false;
        }
        String file64 = Base64.encodeToString(src, 0);*/

        Auth auth = getAuth();
        String upToken;
        /*if(override){
            upToken = auth.uploadToken(bucket, key);//覆盖上传凭证
        }else{
            upToken = auth.uploadToken(bucket);
        }*/
        upToken = auth.uploadToken(bucket, null, 3600, new StringMap().put("insertOnly", 1));
        //System.out.println(imageSize(file64));
        file64 = base64Convert(file64);

        String url = "http://upload.qiniup.com/putb64/" + -1 +"/key/"+ UrlSafeBase64.encodeToString(key);
        //非华东空间需要根据注意事项 1 修改上传域名
        RequestBody rb = RequestBody.create(null, file64);
        Request request = new Request.Builder().
                url(url).
                addHeader("Content-Type", "application/octet-stream")
                .addHeader("Authorization", "UpToken " + upToken)
                .post(rb).build();
        System.out.println(request.headers());
        OkHttpClient client = new OkHttpClient();
        okhttp3.Response response = client.newCall(request).execute();
        System.out.println(response);
        return response.isSuccessful();
    }

    /**
     * 获取私密空间文件访问地址
     * @param fileName 文件存储在七牛云上的名称，可能包含路径符号/
     * @return
     * @throws UnsupportedEncodingException
     */
    public String fileUrl(String fileName) throws UnsupportedEncodingException {
        //log.info(fileName);
        if(StringUtils.isEmpty(fileName)){
            return null;
        }

        String encodedFileName = null;
        String publicUrl = null;
        if(fileName.startsWith("http://") || fileName.startsWith("https://")){
            publicUrl = fileName;
        }else{
            encodedFileName = URLEncoder.encode(fileName, "utf-8");
            publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
        }

        if(zoneType.equals(ZoneType.ZONE_PUBLIC)){
            return publicUrl;
        }

        Auth auth = getAuth();
        if(-1 == expireInSeconds){
            return auth.privateDownloadUrl(publicUrl);
        }
        return auth.privateDownloadUrl(publicUrl, expireInSeconds);
    }

    public boolean uploadCommonsMultipartFile(MultipartFile file, String key, boolean override){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(zone);
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);

        //把文件转化为字节数组
        InputStream is = null;
        ByteArrayOutputStream bos = null;

        try {
            is = file.getInputStream();
            bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int len = -1;
            while ((len = is.read(b)) != -1){
                bos.write(b, 0, len);
            }
            byte[] uploadBytes= bos.toByteArray();

            Auth auth = getAuth();
            String upToken;
            if(override){
                upToken = auth.uploadToken(bucket, key);//覆盖上传凭证
            }else{
                upToken = auth.uploadToken(bucket);
            }
            //默认上传接口回复对象
            DefaultPutRet putRet;
            //进行上传操作，传入文件的字节数组，文件名，上传空间，得到回复对象
            Response response = uploadManager.put(uploadBytes, key, upToken);
            putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);//key 文件名
            System.out.println(putRet.hash);//hash 七牛返回的文件存储的地址，可以使用这个地址加七牛给你提供的前缀访问到这个视频。
            return true;
        }catch (QiniuException e){
            e.printStackTrace();
            return false;
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Auth getAuth(){
        Auth auth = Auth.create(accessKey, secretKey);
        return auth;
    }

    /**
     *通过图片base64流判断图片等于多少字节
     *image 图片流
     */
    public Long imageSize(String image){
        String str=image.substring(22); // 1.需要计算文件流大小，首先把头部的data:image/png;base64,（注意有逗号）去掉。
        Integer equalIndex= str.indexOf("=");//2.找到等号，把等号也去掉
        if(str.indexOf("=")>0) {
            str=str.substring(0, equalIndex);
        }
        Integer strLength=str.length();//3.原来的字符流大小，单位为字节
        Integer size=strLength-(strLength/8)*2;//4.计算后得到的文件流大小，单位为字节
        return size.longValue();
    }

    public String base64Convert(String image){
        return image.substring(22);
    }

    /**
     * 解决七牛云只支持png格式base64图片的问题
     * @param base64
     * @return
     */
    public String convertToPng(String base64){
        String type = base64.substring(base64.indexOf("/") + 1, base64.indexOf(";"));
        return base64.replaceFirst(type, "png");
    }
}
