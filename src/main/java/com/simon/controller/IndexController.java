package com.simon.controller;

import com.alibaba.fastjson.JSON;
import com.simon.common.utils.BuildTree;
import com.simon.dto.MenuDO;
import com.simon.dto.Tree;
import com.simon.model.SideMenu;
import com.simon.service.SideMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
@Slf4j
@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private SideMenuService sideMenuService;

    @GetMapping("/index_iframe")
    public String indexIframe(Model model){
        model.addAttribute("sideMenus", sideMenuService.getAll());
        return "index_iframe";
    }

    @GetMapping("/index_v1")
    public String index(Model model){
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
        log.info(JSON.toJSONString(menuDOs));
        for (MenuDO sysMenuDO : menuDOs) {
            Tree<MenuDO> tree = new Tree<>();
            tree.setId(sysMenuDO.getMenuId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            Map<String, Object> attributes = new HashMap<>(16);
            attributes.put("url", sysMenuDO.getUrl());
            attributes.put("icon", sysMenuDO.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        //默认顶级菜单为0，根据数据库实际情况调整
        List<Tree<MenuDO>> list = BuildTree.buildList(trees, "0");
        model.addAttribute("menus", list);
        model.addAttribute("picUrl","/img/photo_s.jpg");
        return "index_v1";
    }
}
