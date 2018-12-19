package com.simon.controller;

import com.github.pagehelper.PageInfo;
import com.simon.common.domain.EasyUIDataGridResult;
import com.simon.common.domain.ResultMsg;
import com.simon.dto.EasyUiSideMenuDto;
import com.simon.model.SideMenu;
import com.simon.service.SideMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

/**
 * 菜单
 *
 * @author simon
 * @date 2018-09-27
 **/

@Slf4j
@ApiIgnore
@Api(description = "侧边菜单")
@Controller
@RequestMapping("/sideMenus")
public class SideMenuController {
    @Autowired
    private SideMenuService sideMenuService;

    @GetMapping()
    @ResponseBody
    public Object getAll(
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) String authority,
            @RequestParam(required = false) String name){
        if(null != limit && null != offset){
            Map<String, Object> params = new HashMap<>(2);
            params.put("authority", authority);
            params.put("name", name);
            Map<String, Object> resultMap = new HashMap<>(2);
            PageInfo<SideMenu> pageInfo = sideMenuService.getAll(params, limit, offset);
            resultMap.put("total", pageInfo.getTotal());
            resultMap.put("rows", pageInfo.getList());
            return resultMap;
        }else{
            return sideMenuService.findAll();
        }
    }

    @GetMapping("/easyui")
    @ResponseBody
    public ResultMsg<List<EasyUiSideMenuDto>> getAll(){
        List<SideMenu> list = sideMenuService.getAll();
        List<EasyUiSideMenuDto> easyUiList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
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
            if (null != subSideMenus && subSideMenus.size() > 0){
                for(int j = 0; j < subSideMenus.size(); j++){
                    SideMenu subSideMenu = subSideMenus.get(j);
                    EasyUiSideMenuDto child = new EasyUiSideMenuDto();
                    child.setId(subSideMenu.getId());
                    child.setName(subSideMenu.getName());
                    child.setText("<i class=\"" + subSideMenu.getIcon() + "\" aria-hidden=\"true\"></i> " + subSideMenu.getName());
                    //easyui sidemenu子菜单不能用iconCls属性，会造成和默认的图标重叠。
                    //child.setIconCls(subSideMenu.getIcon());
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
        return ResultMsg.success(easyUiList);
    }

    @DeleteMapping("/id/{id}")
    @ResponseBody
    public ResultMsg deleteById(@PathVariable Long id){
        sideMenuService.delete(id);
        return ResultMsg.success();
    }

    @DeleteMapping("/ids/{ids}")
    @ResponseBody
    public ResultMsg deleteByIds(@PathVariable String ids){
        sideMenuService.deleteByIds(ids);
        return ResultMsg.success();
    }

    @PatchMapping()
    @ResponseBody
    public ResultMsg updateById(@RequestBody SideMenu body){
        sideMenuService.updateByPrimaryKeySelective(body);
        return ResultMsg.success();
    }

    @PostMapping
    @ResponseBody
    public ResultMsg add(@RequestBody SideMenu body){
        body.setCreateDate(new Date());
        boolean isPidValid = (1 == body.getMenuType() && (null == body.getPid() || 0L != body.getPid()));
        if (isPidValid){
            body.setPid(0L);
        }
        sideMenuService.save(body);
        return ResultMsg.success();
    }

    @GetMapping(params = "list")
    public String list(){
        return "side_menu";
    }

    @GetMapping(params = "easyui-list")
    public String easyUiList(){
        return "easyui/side_menu";
    }

    @GetMapping("/easyui/list")
    @ResponseBody
    public EasyUIDataGridResult<EasyUiSideMenuDto> getEasyUiList(
            @RequestParam(required = false) String authority,
            @RequestParam(required = false) String name,
            @ApiParam(value = "页码", defaultValue = "1", required = true) @RequestParam Integer pageNo,
            @ApiParam(value = "每页条数", defaultValue = "10", required = true)@RequestParam Integer pageSize,
            @ApiParam(value = "排序(eg: id desc)")@RequestParam(required = false, defaultValue = "") String orderBy){
        Map<String, Object> params = new HashMap<>(2);
        params.put("authority", authority);
        params.put("name", name);
        PageInfo<SideMenu> sideMenuPageInfo = sideMenuService.getList(params, pageSize, (pageNo - 1) * pageSize, orderBy);
        List<SideMenu> list = sideMenuPageInfo.getList();
        List<EasyUiSideMenuDto> easyUiList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            SideMenu sideMenu = list.get(i);
            EasyUiSideMenuDto dto = new EasyUiSideMenuDto();
            dto.setId(sideMenu.getId());
            dto.setText(sideMenu.getName());
            dto.setName(sideMenu.getName());
            dto.setIcon(sideMenu.getIcon());
            //easyui sidemenu菜单不能用iconCls属性，会造成和treegrid默认的图标重叠。
            //dto.setIconCls("icon-folder");
            dto.setAuthority(sideMenu.getAuthority());
            //一级菜单
            dto.setMenuType(1);
            dto.setOrderNum(sideMenu.getOrderNum());
            dto.setPid(sideMenu.getPid());

            List<SideMenu> subSideMenus = sideMenu.getSubSideMenus();
            if(null != subSideMenus && subSideMenus.size() > 0){
                List<EasyUiSideMenuDto> children = new ArrayList<>();
                for(int j = 0; j < subSideMenus.size(); j++){
                    SideMenu subSideMenu = subSideMenus.get(j);
                    EasyUiSideMenuDto child = new EasyUiSideMenuDto();
                    child.setId(subSideMenu.getId());
                    child.setText("<i class=\"" + subSideMenu.getIcon() + "\" aria-hidden=\"true\"></i> " + subSideMenu.getName());
                    child.setName(subSideMenu.getName());
                    child.setIcon(subSideMenu.getIcon());
                    //easyui sidemenu子菜单不能用iconCls属性，会造成和treegrid默认的图标重叠。
                    //child.setIconCls(subSideMenu.getIcon());
                    child.setAuthority(subSideMenu.getAuthority());
                    //二级菜单
                    child.setMenuType(2);
                    child.setOrderNum(subSideMenu.getOrderNum());
                    child.setPid(subSideMenu.getPid());
                    child.setUrl(subSideMenu.getUrl());

                    children.add(child);
                }
                dto.setChildren(children);
                //easyui treegrid 默认收起
                dto.setState("closed");
            }

            easyUiList.add(dto);
        }
        return new EasyUIDataGridResult<>(sideMenuPageInfo.getTotal(), easyUiList);
    }
}
