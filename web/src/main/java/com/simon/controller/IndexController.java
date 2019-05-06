package com.simon.controller;

import com.simon.common.controller.BaseController;
import com.simon.common.domain.UserEntity;
import com.simon.dto.EasyUiSideMenuDto;
import com.simon.model.SideMenu;
import com.simon.service.SideMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页
 *
 * @author simon
 * @date 2018-09-26
 **/
@ApiIgnore
@Slf4j
@Controller
public class IndexController extends BaseController {
    @Autowired
    private SideMenuService sideMenuService;

    @GetMapping(value = {"/index", "/"})
    public String vueIndex(Model model, Authentication authentication){
        List<SideMenu> list = sideMenuService.getAll();
        List<EasyUiSideMenuDto> easyUiList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            SideMenu sideMenu = list.get(i);
            EasyUiSideMenuDto dto = new EasyUiSideMenuDto();
            dto.setId(sideMenu.getId());
            dto.setName(sideMenu.getName());
            dto.setText(sideMenu.getName());
            dto.setIconCls(sideMenu.getIcon());
            dto.setAuthority(sideMenu.getAuthority());
            //一级菜单
            dto.setMenuType(1);
            dto.setOrderNum(sideMenu.getOrderNum());
            dto.setPid(sideMenu.getPid());

            List<SideMenu> subSideMenus = sideMenu.getSubSideMenus();
            List<EasyUiSideMenuDto> children = new ArrayList<>();
            if (null != subSideMenus && subSideMenus.size() > 0) {
                for (int j = 0; j < subSideMenus.size(); j++) {
                    SideMenu subSideMenu = subSideMenus.get(j);
                    EasyUiSideMenuDto child = new EasyUiSideMenuDto();
                    child.setId(subSideMenu.getId());
                    child.setName(subSideMenu.getName());
                    child.setText("<i class=\"" + subSideMenu.getIcon() + "\" aria-hidden=\"true\"></i> " + subSideMenu.getName());
                    //easyui sidemenu子菜单不能用iconCls属性，会造成和默认的图标重叠。
                    child.setIconCls(subSideMenu.getIcon());
                    child.setAuthority(subSideMenu.getAuthority());
                    //二级菜单
                    child.setMenuType(2);
                    child.setOrderNum(subSideMenu.getOrderNum());
                    child.setPid(subSideMenu.getPid());
                    child.setUrl(subSideMenu.getUrl());

                    children.add(child);
                }
            }
            dto.setChildren(children);
            easyUiList.add(dto);
        }
        model.addAttribute("menuData", listToMap(easyUiList));

        UserEntity userEntity = getCurrentUser(authentication);
        if (null == userEntity || StringUtils.isEmpty(userEntity.getHeadPhoto())){
            model.addAttribute("headPhoto","/img/photo_s.jpg");
        }else{
            model.addAttribute("headPhoto", userEntity.getHeadPhoto());
        }
        return "vue/index";
    }
}
