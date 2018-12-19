package com.simon.common.utils;

import com.alibaba.fastjson.JSON;
import com.simon.common.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件上传工具类
 *
 * @author simon
 * @date 2018-06-06
 **/
@Slf4j
public class FileUploadUtil {
    private static final String ROOT = AppConfig.FILE_UPLOAD_DIR;

    public FileUploadUtil(){

    }

    public static String[] saveFiles(MultipartFile[] files){
        if(!Files.exists(Paths.get(ROOT))){
            try{
                Files.createDirectories(Paths.get(ROOT));
            }catch (IOException e){
                log.error(e.getMessage());
            }
        }

        int len = files.length;
        if (len > 0){
            String[] imgUrlArr = new String[len];
            for(int i = 0; i < len; i++){
                if (!files[i].isEmpty()){
                    try {
                        //SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
                        String originFileName = files[i].getOriginalFilename();
                        String fileType = originFileName.substring(originFileName.lastIndexOf("."));
                        log.info("originFileName=" + originFileName);
                        log.info("fileType=" + fileType);
                        //String imgUrl = ROOT + "/" + fmt.format(new Date()) + fileType;

                        String imgUrl = ROOT + "/" + originFileName;
                        Path path = Paths.get(imgUrl);
                        if (!Files.exists(path)){
                            Files.copy(files[i].getInputStream(), path);
                            log.info(files[i].getName());
                            log.info(files[i].getOriginalFilename());
                            log.info(files[i].getContentType());
                        }
                        imgUrlArr[i] = "/" + imgUrl;
                    } catch (IOException e) {
                        e.printStackTrace();
                        log.error(e.getMessage());
                    }
                }
            }
            log.info(JSON.toJSONString(imgUrlArr));
            return imgUrlArr;
        }else{
            return null;
        }
    }
}
