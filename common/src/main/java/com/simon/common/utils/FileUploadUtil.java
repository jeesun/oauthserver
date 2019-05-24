package com.simon.common.utils;

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

    public FileUploadUtil() {

    }

    public static String[] saveFiles(MultipartFile[] files) {
        if (!Files.exists(Paths.get(ROOT))) {
            try {
                Files.createDirectories(Paths.get(ROOT));
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }

        int len = files.length;
        if (len > 0) {
            String[] imgUrlArr = new String[len];
            for (int i = 0; i < len; i++) {
                if (!files[i].isEmpty()) {
                    try {
                        String originFileName = files[i].getOriginalFilename();
                        String fileType = originFileName.substring(originFileName.lastIndexOf("."));
                        String imgUrl = ROOT + "/" + DateUtil.currentTimeString() + fileType;
                        Path path = Paths.get(imgUrl);
                        if (!Files.exists(path)) {
                            Files.copy(files[i].getInputStream(), path);
                        }
                        imgUrlArr[i] = "/" + imgUrl;
                    } catch (IOException e) {
                        e.printStackTrace();
                        log.error(e.getMessage());
                    }
                }
            }
            return imgUrlArr;
        } else {
            return null;
        }
    }
}
