package com.simon.controller;

import com.alibaba.fastjson.JSONException;
import com.baidu.ueditor.ActionEnter;
import com.simon.common.controller.BaseController;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * UEditor
 *
 * @author simon
 * @date 2018-12-11
 **/
@Slf4j
@Api(description = "账号绑定")
@Controller
@RequestMapping("/api/uEditors")
public class UEditorController extends BaseController {
    @RequestMapping("")
    @ResponseBody
    public void getConfigInfo(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext()
                .getRealPath("/");
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
