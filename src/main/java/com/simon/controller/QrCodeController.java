package com.simon.controller;

import com.simon.common.controller.BaseController;
import com.simon.common.domain.ResultMsg;
import com.simon.repository.QrCodeRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "二维码", description = "网页端扫码登录")
@RestController
@RequestMapping("/api/qrCodes")
public class QrCodeController extends BaseController {
    @Autowired
    private QrCodeRepository qrCodeRepository;
    /**
     * 手机扫描网页端二维码码登录需要访问的接口
     * @param username
     * @param access_token
     * @param sid
     * @return
     */
    @ApiOperation(value = "app扫描网页端二维码登录", notes = "app扫描网页端二维码登录")
    @RequestMapping(method = RequestMethod.POST, value = "/loginByQrCode")
    public ResultMsg postLoginByQrCode(
            @RequestParam String username,
            @RequestParam String access_token,
            @RequestParam String sid){
        var qrCode = qrCodeRepository.findBySid(sid);
        if (null != qrCode){
            qrCode.setUsername(username);
            qrCode.setToken(access_token);
            qrCode.setIsOk(true);
            qrCodeRepository.save(qrCode);
            return ResultMsg.success(200, "扫码成功");
        }else{
            return ResultMsg.fail(404, "扫码失败，未找到该二维码对应的数据");
        }
    }
}
