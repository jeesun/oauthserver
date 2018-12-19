package com.simon.controller;

import com.alibaba.fastjson.JSON;
import com.simon.common.config.AppConfig;
import com.simon.common.controller.BaseController;
import com.simon.common.domain.ResultMsg;
import com.simon.common.plugins.qiniu.QiNiuUtil;
import com.simon.common.utils.FileUploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传
 *
 * @author simon
 * @date 2018-10-05
 **/

@Api(description = "文件上传")
@Slf4j
@RestController
@RequestMapping("/fileUploads")
public class FileUploadController extends BaseController {
    private final ResourceLoader resourceLoader;

    private final String ROOT = AppConfig.FILE_UPLOAD_DIR;
    private final String fileUploadType = AppConfig.FILE_UPLOAD_TYPE;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    public FileUploadController(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
        if(!Files.exists(Paths.get(ROOT))){
            try{
                Files.createDirectories(Paths.get(ROOT));
            }catch (IOException e){
                log.error(e.getMessage());
            }
        }
    }

    @PostMapping(value = "upload")
    public ResultMsg post(@RequestParam("file") MultipartFile[] files) throws UnsupportedEncodingException {
        String[] savedFiles = new String[files.length];
        if(AppConfig.FILE_UPLOAD_TYPE_QINIU.equals(fileUploadType)){
            for(int i = 0; i < files.length; i++){
                MultipartFile file = files[i];
                QiNiuUtil.getInstance().setZoneType(QiNiuUtil.ZoneType.ZONE_PUBLIC).uploadCommonsMultipartFile(file, ROOT + "/" + file.getOriginalFilename(), true);
                //savedFiles[i] = QiNiuUtil.getInstance().setZoneType(QiNiuUtil.ZoneType.ZONE_PUBLIC).fileUrl(ROOT + "/" + file.getOriginalFilename());
                savedFiles[i] = "/" + ROOT + "/" + file.getOriginalFilename();
            }
        }else{
            savedFiles = FileUploadUtil.saveFiles(files);
            /*if(null != savedFiles && savedFiles.length > 0){
                for(int i = 0; i < savedFiles.length; i++){
                    String saveFile = savedFiles[i];
                    savedFiles[i] = "http://localhost:" + serverPort + saveFile;
                }
            }*/
        }
        log.info(JSON.toJSONString(savedFiles));
        if(null == savedFiles || savedFiles.length <= 0){
            return ResultMsg.success(404, "保存文件失败", null);
        }else{
            return ResultMsg.success(200, "保存文件成功", savedFiles);
        }
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST, consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public Map<String, Object> uploadTest(@ApiParam(value = "文件", required = true) @RequestPart("file") MultipartFile[] files){
        Map<String, Object> resultMap = new HashMap<>(1);
        String[] savedFiles = FileUploadUtil.saveFiles(files);
        if(null == savedFiles || savedFiles.length <= 0){
            resultMap.put("link", null);
        }else{
            StringBuffer filePath = new StringBuffer();
            filePath.append(savedFiles[0]);
            resultMap.put("link", filePath);
        }
        log.info(JSON.toJSONString(resultMap));
        return resultMap;
    }

    /*@ApiOperation(value = "文件下载")
    @GetMapping("/fileUpload/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = resourceLoader.getResource("file:" + Paths.get(ROOT, filename));
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }*/

    /*@ApiOperation(value = "文件获取")
    @GetMapping("/fileUpload/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename){
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/octet-stream").body(resourceLoader.getResource("file:" + Paths.get(ROOT, filename).toString()));
    }*/
}
