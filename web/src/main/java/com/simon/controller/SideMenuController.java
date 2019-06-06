package com.simon.controller;

import com.github.pagehelper.PageInfo;
import com.simon.common.controller.BaseController;
import com.simon.common.domain.EasyUIDataGridResult;
import com.simon.common.domain.ResultMsg;
import com.simon.common.domain.UserEntity;
import com.simon.dto.EasyUiSideMenuDto;
import com.simon.model.SideMenu;
import com.simon.service.DictTypeService;
import com.simon.service.FontAwesomeService;
import com.simon.service.SideMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 菜单
 *
 * @author simon
 * @date 2018-09-27
 **/

@Slf4j
@ApiIgnore
@Api(value = "侧边菜单")
@Controller
@RequestMapping("/api/sideMenus")
public class SideMenuController extends BaseController {
    @Autowired
    private SideMenuService sideMenuService;

    @Autowired
    private FontAwesomeService fontAwesomeService;

    @Autowired
    private DictTypeService dictTypeService;

    @GetMapping("/list")
    public String list(Model model, Locale locale) {
        model.addAttribute("roleTypeList", dictTypeService.getTypeByGroupCode("role_type", locale.toString()));
        return "vue/sideMenu/list";
    }

    @GetMapping("add")
    public String add(Model model, Locale locale) {
        model.addAttribute("icons", fontAwesomeService.getDtos());
        model.addAttribute("roleTypeList", dictTypeService.getTypeByGroupCode("role_type", locale.toString()));
        return "vue/sideMenu/add";
    }

    @GetMapping("edit")
    public String edit(Model model, @RequestParam Long id, Locale locale) {
        model.addAttribute("icons", fontAwesomeService.getDtos());
        model.addAttribute("entity", entityToMap(sideMenuService.findById(id, locale.toString())));
        return "vue/sideMenu/edit";
    }

    @GetMapping("subAdd")
    public String subAdd(Model model, @ApiParam(value = "父菜单id", required = true) @RequestParam Long id, Locale locale) {
        model.addAttribute("entity", entityToMap(sideMenuService.findById(id)));
        model.addAttribute("parentMenus", listToMap(sideMenuService.getLevel1(locale.toString())));
        model.addAttribute("icons", fontAwesomeService.getDtos());
        model.addAttribute("roleTypeList", dictTypeService.getTypeByGroupCode("role_type", locale.toString()));
        return "vue/sideMenu/subAdd";
    }

    @GetMapping("subEdit")
    public String subEdit(Model model, @RequestParam Long id, Locale locale) {
        SideMenu sideMenu = sideMenuService.findById(id, locale.toString());
        model.addAttribute("entity", entityToMap(sideMenu));
        model.addAttribute("parentMenus", listToMap(sideMenuService.getLevel1(locale.toString())));
        model.addAttribute("icons", fontAwesomeService.getDtos());
        model.addAttribute("roleTypeList", dictTypeService.getTypeByGroupCode("role_type", locale.toString()));
        return "vue/sideMenu/subEdit";
    }

/*    @GetMapping()
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
    }*/

    @ApiOperation(value = "侧边栏菜单数据")
    @GetMapping("/data/all")
    @ResponseBody
    public ResultMsg<List<EasyUiSideMenuDto>> getAll(Locale locale) {
        //en_US, zh_CN
        String language = locale.getLanguage() + "_" + locale.getCountry();
        List<SideMenu> list = sideMenuService.getAll(language);
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
        return ResultMsg.success(easyUiList);
    }

    @DeleteMapping("/id/{id}")
    @ResponseBody
    public ResultMsg deleteById(@PathVariable Long id) {
        sideMenuService.delete(id);
        return ResultMsg.success();
    }

    @DeleteMapping("/ids/{ids}")
    @ResponseBody
    public ResultMsg deleteByIds(@PathVariable String ids) {
        sideMenuService.deleteByIds(ids);
        return ResultMsg.success();
    }

    @PatchMapping("edit")
    @ResponseBody
    public ResultMsg updateById(@RequestBody SideMenu body, Authentication authentication, Locale locale) {
        UserEntity userEntity = getCurrentUser(authentication);
        body.setUpdateBy(userEntity.getId());
        body.setUpdateDate(LocalDateTime.now());

        sideMenuService.updateByPrimaryKeySelective(body, locale.toString());
        return ResultMsg.success();
    }

    @PostMapping("add")
    @ResponseBody
    public ResponseEntity<ResultMsg> add(@RequestBody SideMenu body, Authentication authentication, Locale locale) {
        UserEntity userEntity = getCurrentUser(authentication);
        body.setCreateDate(LocalDateTime.now());
        body.setCreateBy(userEntity.getId());

        boolean isPidValid = (1 == body.getMenuType() && (null == body.getPid() || 0L != body.getPid()));
        if (isPidValid) {
            body.setPid(0L);
        }
        sideMenuService.save(body, locale.toString());
        return new ResponseEntity<>(ResultMsg.success(), HttpStatus.CREATED);
    }

    @ApiOperation(value = "table数据")
    @GetMapping("/data")
    @ResponseBody
    public EasyUIDataGridResult<EasyUiSideMenuDto> getEasyUiList(
            Locale locale,
            @RequestParam(required = false) String authority,
            @RequestParam(required = false) String name,
            @ApiParam(value = "页码", defaultValue = "1", required = true) @RequestParam Integer pageNo,
            @ApiParam(value = "每页条数", defaultValue = "10", required = true) @RequestParam Integer pageSize,
            @ApiParam(value = "排序(eg: id desc)") @RequestParam(required = false, defaultValue = "") String orderBy) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("authority", authority);
        params.put("name", name);
        params.put("language", locale.toString());
        PageInfo<SideMenu> sideMenuPageInfo = sideMenuService.getList(params, pageSize, (pageNo - 1) * pageSize, orderBy);
        List<SideMenu> list = sideMenuPageInfo.getList();
        List<EasyUiSideMenuDto> easyUiList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            SideMenu sideMenu = list.get(i);
            EasyUiSideMenuDto dto = new EasyUiSideMenuDto();
            dto.setId(sideMenu.getId());
            dto.setText(sideMenu.getName());
            dto.setName(sideMenu.getName());
            dto.setIcon(sideMenu.getIcon());
            //easyui sidemenu菜单不能用iconCls属性，会造成和treegrid默认的图标重叠。
            //dto.setIconCls("icon-folder");
            dto.setAuthority(sideMenu.getAuthority());
            dto.setAuthorityName(sideMenu.getAuthorityName());
            //一级菜单
            dto.setMenuType(1);
            dto.setOrderNum(sideMenu.getOrderNum());
            dto.setPid(sideMenu.getPid());
            dto.setRequestMethod(sideMenu.getRequestMethod());

            List<SideMenu> subSideMenus = sideMenu.getSubSideMenus();
            if (null != subSideMenus && subSideMenus.size() > 0) {
                List<EasyUiSideMenuDto> children = new ArrayList<>();
                for (int j = 0; j < subSideMenus.size(); j++) {
                    SideMenu subSideMenu = subSideMenus.get(j);
                    EasyUiSideMenuDto child = new EasyUiSideMenuDto();
                    child.setId(subSideMenu.getId());
                    child.setText("<i class=\"" + subSideMenu.getIcon() + "\" aria-hidden=\"true\"></i> " + subSideMenu.getName());
                    child.setName(subSideMenu.getName());
                    child.setIcon(subSideMenu.getIcon());
                    //easyui sidemenu子菜单不能用iconCls属性，会造成和treegrid默认的图标重叠。
                    child.setIconCls(subSideMenu.getIcon());
                    child.setAuthority(subSideMenu.getAuthority());
                    child.setAuthorityName(subSideMenu.getAuthorityName());
                    //二级菜单
                    child.setMenuType(2);
                    child.setOrderNum(subSideMenu.getOrderNum());
                    child.setPid(subSideMenu.getPid());
                    child.setUrl(subSideMenu.getUrl());

                    List<EasyUiSideMenuDto> grandchildren = new ArrayList<>();
                    List<SideMenu> grandchildrenMenus = sideMenuService.selectByPid(subSideMenu.getId(), locale.toString());
                    for(int k = 0; k < grandchildrenMenus.size(); k++){
                        SideMenu grandchildMenu = grandchildrenMenus.get(k);
                        EasyUiSideMenuDto grandchild = new EasyUiSideMenuDto();
                        grandchild.setId(grandchildMenu.getId());
                        grandchild.setText("<i class=\"" + grandchildMenu.getIcon() + "\" aria-hidden=\"true\"></i> " + grandchildMenu.getName());
                        grandchild.setName(grandchildMenu.getName());
                        grandchild.setIcon(grandchildMenu.getIcon());
                        //easyui sidemenu子菜单不能用iconCls属性，会造成和treegrid默认的图标重叠。
                        grandchild.setIconCls(grandchildMenu.getIcon());
                        grandchild.setAuthority(grandchildMenu.getAuthority());
                        grandchild.setAuthorityName(grandchildMenu.getAuthorityName());
                        //三级菜单
                        grandchild.setMenuType(3);
                        grandchild.setOrderNum(grandchildMenu.getOrderNum());
                        grandchild.setPid(grandchildMenu.getPid());
                        grandchild.setUrl(grandchildMenu.getUrl());
                        grandchild.setRequestMethod(grandchildMenu.getRequestMethod());
                        grandchildren.add(grandchild);
                    }
                    child.setChildren(grandchildren);
                    child.setRequestMethod(subSideMenu.getRequestMethod());

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
