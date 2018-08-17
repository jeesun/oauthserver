package com.simon.common.plugins.qiniu;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.UrlSafeBase64;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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
 * @create 2018-08-15 11:21
 **/
@Slf4j
public class QiNiuUtil {
    /**
     * 上传本地文件
     * @param localFilePath 本地文件完整路径
     * @param key 文件云端存储的名称
     * @param override 是否覆盖同名同位置文件
     * @return
     */
    public static boolean upload(String localFilePath, String key, boolean override){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(QiNiuConfig.getInstance().getZone());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        Auth auth = Auth.create(QiNiuConfig.getInstance().getAccessKey(), QiNiuConfig.getInstance().getSecretKey());
        String upToken;
        if(override){
            upToken = auth.uploadToken(QiNiuConfig.getInstance().getBucket(), key);//覆盖上传凭证
        }else{
            upToken = auth.uploadToken(QiNiuConfig.getInstance().getBucket());
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
     * @param l 图片没经过base64处理的原图字节大小，获取文件大小的时候，切记要通过文件流的方式获取。而不是通过图片标签然后转换后获取。
     * @param file64 图片base64字符串
     * @param key 文件云端存储的名称
     * @param override 是否覆盖同名同位置文件
     * @return
     * @throws IOException
     */
    public static boolean uploadBase64(int l, String file64, String key, boolean override) throws IOException{
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
        if(override){
            upToken = auth.uploadToken(QiNiuConfig.getInstance().getBucket(), key);//覆盖上传凭证
        }else{
            upToken = auth.uploadToken(QiNiuConfig.getInstance().getBucket());
        }

        String url = "http://upload.qiniup.com/putb64/" + l+"/key/"+ UrlSafeBase64.encodeToString(key);
        //非华东空间需要根据注意事项 1 修改上传域名
        RequestBody rb = RequestBody.create(null, file64);
        Request request = new Request.Builder().
                url(url).
                addHeader("Content-Type", "application/octet-stream")
                .addHeader("Authorization", "UpToken " + upToken)
                .post(rb).build();
        //System.out.println(request.headers());
        OkHttpClient client = new OkHttpClient();
        okhttp3.Response response = client.newCall(request).execute();
        //System.out.println(response);
        return response.isSuccessful();
    }

    /**
     * 获取文件访问地址
     * @param fileName 文件云端存储的名称
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String fileUrl(String fileName) throws UnsupportedEncodingException {
        String encodedFileName = URLEncoder.encode(fileName, "utf-8");
        String publicUrl = String.format("%s/%s", QiNiuConfig.getInstance().getDomainOfBucket(), encodedFileName);
        Auth auth = getAuth();
        long expireInSeconds = QiNiuConfig.getInstance().getExpireInSeconds();
        if(-1 == expireInSeconds){
            return auth.privateDownloadUrl(publicUrl);
        }
        return auth.privateDownloadUrl(publicUrl, expireInSeconds);
    }

    /**
     * 上传MultipartFile
     * @param file
     * @param key
     * @param override
     * @return
     * @throws IOException
     */
    public static boolean uploadMultipartFile(MultipartFile file, String key, boolean override) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(QiNiuConfig.getInstance().getZone());
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
                upToken = auth.uploadToken(QiNiuConfig.getInstance().getBucket(), key);//覆盖上传凭证
            }else{
                upToken = auth.uploadToken(QiNiuConfig.getInstance().getBucket());
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

    public static Auth getAuth(){
        Auth auth = Auth.create(QiNiuConfig.getInstance().getAccessKey(), QiNiuConfig.getInstance().getSecretKey());
        return auth;
    }
}
