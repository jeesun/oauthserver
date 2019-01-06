package com.simon.controller;

import com.simon.common.domain.UserEntity;
import com.simon.common.utils.BuildTree;
import com.simon.dto.MenuDO;
import com.simon.dto.Tree;
import com.simon.model.SideMenu;
import com.simon.service.SideMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 主页
 *
 * @author simon
 * @date 2018-09-26
 **/
@ApiIgnore
@Slf4j
@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private SideMenuService sideMenuService;

    @PreAuthorize("hasRole('ADMIN') OR hasRole('SU')")
    @GetMapping("/index_iframe")
    public String indexIframe(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model,
            Authentication authentication){
        model.addAttribute("sideMenus", sideMenuService.getAll());

        Object principal = authentication.getPrincipal();
        UserEntity userEntity = null;
        if(principal instanceof UserEntity){
            userEntity = (UserEntity)principal;
        }
        if (null == userEntity || StringUtils.isEmpty(userEntity.getHeadPhoto())){
            model.addAttribute("headPhoto","/img/photo_s.jpg");
        }else{
            model.addAttribute("headPhoto", userEntity.getHeadPhoto());
        }

        //设置AdminLTE页面默认的皮肤
        Cookie cookie = new Cookie("theme", "skin-purple");
        response.addCookie(cookie);

        model.addAttribute("theme", "skin-purple");

        return "index_iframe";
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('SU')")
    @GetMapping("/index_v1")
    public String index(Model model, Authentication authentication){
        List<Tree<MenuDO>> trees = new ArrayList<>();
        List<SideMenu> sideMenus = sideMenuService.findAll();
        List<MenuDO> menuDOs = new ArrayList<>();
        for(int i = 0; i < sideMenus.size(); i++){
            SideMenu sideMenu = sideMenus.get(i);
            MenuDO menuDO = new MenuDO();
            menuDO.setMenuId(sideMenu.getId());
            menuDO.setParentId(sideMenu.getPid());
            menuDO.setName(sideMenu.getName());
            menuDO.setUrl(sideMenu.getUrl());
            menuDO.setPerms(sideMenu.getAuthority());
            menuDO.setType((0L == sideMenu.getPid()) ? 0 : 1);
            menuDO.setIcon(sideMenu.getIcon());
            menuDO.setOrderNum(sideMenu.getOrderNum());
            menuDO.setGmtCreate(sideMenu.getCreateDate());
            menuDO.setGmtModified(sideMenu.getUpdateDate());
            menuDOs.add(menuDO);
        }
        //log.info(JSON.toJSONString(menuDOs));
        for (MenuDO sysMenuDO : menuDOs) {
            Tree<MenuDO> tree = new Tree<>();
            tree.setId(sysMenuDO.getMenuId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            Map<String, Object> attributes = new HashMap<>(16);
            attributes.put("url", sysMenuDO.getUrl());
            attributes.put("icon", sysMenuDO.getIcon());
            attributes.put("perms", sysMenuDO.getPerms());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        //默认顶级菜单为0，根据数据库实际情况调整
        List<Tree<MenuDO>> list = BuildTree.buildList(trees, "0");
        //log.info(JSON.toJSONString(list));
        model.addAttribute("menus", list);

        Object principal = authentication.getPrincipal();
        UserEntity userEntity = null;
        if(principal instanceof UserEntity){
            userEntity = (UserEntity)principal;
        }
        if (null == userEntity || StringUtils.isEmpty(userEntity.getHeadPhoto())){
            model.addAttribute("headPhoto","/img/photo_s.jpg");
        }else{
            model.addAttribute("headPhoto", userEntity.getHeadPhoto());
        }

        return "index_v1";
    }
}
