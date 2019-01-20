
package com.simon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;
import com.simon.common.config.AppConfig;
import com.simon.dto.ButtonAuthorityDto;
import com.simon.dto.EasyUiTreeDto;
import com.simon.mapper.SideMenuAuthorityMapper;
import com.simon.mapper.SideMenuMapper;
import com.simon.model.SideMenu;
import com.simon.model.SideMenuAuthority;
import com.simon.repository.SideMenuRepository;
import com.simon.service.SideMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author SimonSun
 * @date 2018-09-26
 **/
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class SideMenuServiceImpl implements SideMenuService {
    @Autowired
    private SideMenuMapper sideMenuMapper;

    @Autowired
    private SideMenuRepository sideMenuRepository;

    @Autowired
    private SideMenuAuthorityMapper sideMenuAuthorityMapper;

    @Override
    public long count() {
        return sideMenuRepository.count();
    }

    @Override
    public SideMenu save(SideMenu sideMenu) {
        return sideMenuRepository.save(sideMenu);
    }

    @Override
    public List<SideMenu> save(List<SideMenu> sideMenuList) {
        return sideMenuRepository.save(sideMenuList);
    }

    @Override
    public PageInfo<SideMenu> findAll(Integer pageNo, Integer pageSize, String orderBy) {
        if (null == pageSize) {
            pageSize = AppConfig.DEFAULT_PAGE_SIZE;
        }
        orderBy = orderBy.trim();
        if (org.apache.commons.lang3.StringUtils.isEmpty(orderBy)) {
            PageHelper.startPage(pageNo, pageSize);
        } else {
            PageHelper.startPage(pageNo, pageSize, orderBy);
        }
        List<SideMenu> list = sideMenuMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Page<SideMenu> findAll(Pageable pageable) {
        return sideMenuRepository.findAll(pageable);
    }

    @Override
    public List<SideMenu> findAll() {
        return sideMenuMapper.findAll();
    }

    @Override
    public void delete(Long id) {
        sideMenuRepository.delete(id);
    }

    @Override
    public int deleteByIds(String ids) {
        return sideMenuMapper.deleteByIds(ids);
    }

    @Override
    public SideMenu findById(Long id) {
        return sideMenuRepository.findOne(id);
    }

    @Override
    public int insertList(List<SideMenu> list) {
        return sideMenuMapper.insertList(list);
    }

    @Override
    public int insert(SideMenu sideMenu) {
        return sideMenuMapper.insert(sideMenu);
    }

    @Override
    public int insertSelective(SideMenu sideMenu) {
        return sideMenuMapper.insertSelective(sideMenu);
    }

    @Override
    public int updateByPrimaryKey(SideMenu sideMenu) {
        return sideMenuMapper.updateByPrimaryKey(sideMenu);
    }

    @Override
    public int updateByPrimaryKeySelective(SideMenu sideMenu) {
        return sideMenuMapper.updateByPrimaryKeySelective(sideMenu);
    }

    @Override
    public PageInfo<SideMenu> getAll(Map<String, Object> params, Integer limit, Integer offset) {
        PageHelper.startPage(offset / limit + 1, limit);
        List<SideMenu> list = sideMenuMapper.selectLevel1(params);
        PageInfo<SideMenu> pageInfo = new PageInfo<>(list);
        List<Long> pids = new ArrayList<>();
        List<SideMenu> resultList = pageInfo.getList();
        for (SideMenu sideMenu : resultList) {
            pids.add(sideMenu.getId());
        }
        resultList.addAll(sideMenuMapper.selectByPidList(pids));
        pageInfo.setList(resultList);
        return pageInfo;
    }

    @Override
    public List<SideMenu> getAll() {
        return sideMenuMapper.selectTreeGrid();
    }

    @Override
    public String findAuthorityByUrlAndRequestMethod(String url, String requestMethod) {
        if ("DELETE".equalsIgnoreCase(requestMethod) && url.contains("ids")) {
            url = url.substring(0, url.lastIndexOf("ids") + 3);
        }
        return sideMenuMapper.findAuthorityByUrl(url);
    }

    @Override
    public int insertOrUpdateByEntityName(String entityName, Long pid, String allowedRoles, String tableComment) {
        //先从菜单权限表删除之前的权限信息
        sideMenuAuthorityMapper.deleteByEntityName(entityName);

        //再从菜单表删除之前的配置信息
        sideMenuMapper.deleteByEntityName(entityName);


        Date createDate = new Date();
        //首字母小写
        entityName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, entityName);
        String baseUrl = "/api/" + entityName + "s/";
        //期望结果：baseUrl下面，有查看、新增、修改、删除权限控制

        //列表页面
        SideMenu listSideMenu = new SideMenu();
        listSideMenu.setCreateDate(createDate);
        listSideMenu.setPid(pid);
        listSideMenu.setName(tableComment + "管理");
        listSideMenu.setUrl(baseUrl + "list");
        listSideMenu.setRequestMethod("GET");
        listSideMenu.setShowInMenu(true);
        listSideMenu.setEntityName(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, entityName));
        listSideMenu.setRemark("list");
        sideMenuMapper.insertSelective(listSideMenu);

        //列表数据
        SideMenu dataSideMenu = new SideMenu();
        dataSideMenu.setCreateDate(createDate);
        dataSideMenu.setPid(listSideMenu.getId());
        dataSideMenu.setName("查看");
        dataSideMenu.setUrl(baseUrl + "data");
        dataSideMenu.setRequestMethod("GET");
        dataSideMenu.setShowInMenu(false);
        //列表数据必须设置关联id，关联id是listSideMenu的id
        //因为在设置角色权限时用的到。设置角色权限的tree勾选了列表数据，说明要插入两条权限数据，一条列表数据的，一条列表页面的。
        dataSideMenu.setLinkId(listSideMenu.getId());
        dataSideMenu.setEntityName(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, entityName));
        dataSideMenu.setRemark("data");
        sideMenuMapper.insertSelective(dataSideMenu);

        //新增页面和新增操作
        SideMenu addSideMenu = new SideMenu();
        addSideMenu.setCreateDate(createDate);
        addSideMenu.setPid(listSideMenu.getId());
        addSideMenu.setName("新增");
        addSideMenu.setUrl(baseUrl + "add");
        addSideMenu.setRequestMethod("GET,POST");
        addSideMenu.setShowInMenu(false);
        addSideMenu.setEntityName(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, entityName));
        addSideMenu.setRemark("add");
        sideMenuMapper.insertSelective(addSideMenu);

        //编辑页面和编辑操作
        SideMenu editSideMenu = new SideMenu();
        editSideMenu.setCreateDate(createDate);
        editSideMenu.setPid(listSideMenu.getId());
        editSideMenu.setName("修改");
        editSideMenu.setUrl(baseUrl + "edit");
        editSideMenu.setRequestMethod("GET,PATCH");
        editSideMenu.setShowInMenu(false);
        editSideMenu.setEntityName(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, entityName));
        editSideMenu.setRemark("edit");
        sideMenuMapper.insertSelective(editSideMenu);

        //删除操作
        SideMenu deleteSideMenu = new SideMenu();
        deleteSideMenu.setCreateDate(createDate);
        deleteSideMenu.setPid(listSideMenu.getId());
        deleteSideMenu.setName("删除");
        deleteSideMenu.setUrl(baseUrl + "ids");
        deleteSideMenu.setRequestMethod("DELETE");
        deleteSideMenu.setShowInMenu(false);
        deleteSideMenu.setEntityName(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, entityName));
        deleteSideMenu.setRemark("delete");
        sideMenuMapper.insertSelective(deleteSideMenu);

        //准备添加url权限数据
        if(StringUtils.isEmpty(allowedRoles)){
            allowedRoles = "ROLE_SU";
        }
        allowedRoles = allowedRoles.replace(" ", "");

        String[] allowedRoleArr = allowedRoles.split(",");
        for(int i = 0; i < allowedRoleArr.length; i++){
            SideMenuAuthority listAuthority = new SideMenuAuthority();
            listAuthority.setSideMenuId(listSideMenu.getId());
            listAuthority.setAuthority(allowedRoleArr[i]);
            sideMenuAuthorityMapper.insertSelective(listAuthority);

            SideMenuAuthority dataAuthority = new SideMenuAuthority();
            dataAuthority.setSideMenuId(dataSideMenu.getId());
            dataAuthority.setAuthority(allowedRoleArr[i]);
            sideMenuAuthorityMapper.insertSelective(dataAuthority);

            SideMenuAuthority addAuthority = new SideMenuAuthority();
            addAuthority.setSideMenuId(addSideMenu.getId());
            addAuthority.setAuthority(allowedRoleArr[i]);
            sideMenuAuthorityMapper.insertSelective(addAuthority);

            SideMenuAuthority editAuthority = new SideMenuAuthority();
            editAuthority.setSideMenuId(editSideMenu.getId());
            editAuthority.setAuthority(allowedRoleArr[i]);
            sideMenuAuthorityMapper.insertSelective(editAuthority);

            SideMenuAuthority deleteAuthority = new SideMenuAuthority();
            deleteAuthority.setSideMenuId(deleteSideMenu.getId());
            deleteAuthority.setAuthority(allowedRoleArr[i]);
            sideMenuAuthorityMapper.insertSelective(deleteAuthority);
        }

        return 1;
    }

    @Override
    public List<ButtonAuthorityDto> findButtonAuthorityDtoByEntityName(String entityName) {
        return sideMenuMapper.findButtonAuthorityDtoByEntityName(entityName);
    }

    @Override
    public PageInfo<SideMenu> getList(Map<String, Object> params, Integer limit, Integer offset, String orderBy) {
        orderBy = orderBy.trim();
        if (StringUtils.isEmpty(orderBy)) {
            PageHelper.startPage(offset / limit + 1, limit);
        } else {
            PageHelper.startPage(offset / limit + 1, limit, orderBy);
        }

        List<SideMenu> list = sideMenuMapper.selectLevel1(params);
        return new PageInfo<>(list);
    }

    @Override
    public List<EasyUiTreeDto> getAuth(String typeCode) {
        List<EasyUiTreeDto> easyUiTreeDtoList = sideMenuMapper.findEasyUiTreeDtoByAuthority(typeCode);
        if (null == easyUiTreeDtoList || easyUiTreeDtoList.size() <= 0) {
            return easyUiTreeDtoList;
        }

        for (EasyUiTreeDto item : easyUiTreeDtoList) {
            if (StringUtils.isNotEmpty(item.getAuthority()) && item.getAuthority().contains(typeCode)) {
                item.setChecked(true);
            }
        }
        //log.info(JSON.toJSONString(easyUiTreeDtoList));
        List<EasyUiTreeDto> result = new ArrayList<>();
        //先取第一层
        Iterator<EasyUiTreeDto> it = easyUiTreeDtoList.iterator();
        while (it.hasNext()) {
            EasyUiTreeDto dto = it.next();
            if (dto.getPid() == 0L) {
                result.add(dto);
                it.remove();
            }
        }
        //排序（正序）
        result.sort(Comparator.comparing(EasyUiTreeDto::getOrderNum));
        //排序（逆序）
        //result.sort(Comparator.comparing(EasyUiTreeDto::getId).reversed());
        //log.info(JSON.toJSONString(result));
        //取第二层
        for (int i = 0; i < result.size(); i++) {
            EasyUiTreeDto parent = result.get(i);
            List<EasyUiTreeDto> children = new ArrayList<>();
            Iterator<EasyUiTreeDto> iter = easyUiTreeDtoList.iterator();
            while (iter.hasNext()) {
                EasyUiTreeDto child = iter.next();
                if (parent.getId().equals(child.getPid())) {
                    children.add(child);
                    iter.remove();
                }
            }
            children.sort(Comparator.comparing(EasyUiTreeDto::getOrderNum));
            parent.setChildren(children);
        }
        //log.info(JSON.toJSONString(result));
        //取第三层（最后一层）
        for (int i = 0; i < result.size(); i++) {
            EasyUiTreeDto grandParent = result.get(i);
            List<EasyUiTreeDto> parentList = grandParent.getChildren();
            int grandParentChecked = 1;
            if (null != parentList && parentList.size() > 0) {
                //flag标识parentChecked是否发生了变化，如果没有变化，则不设置
                for (int j = 0; j < parentList.size(); j++) {
                    boolean flag = false;
                    EasyUiTreeDto parent = parentList.get(j);
                    List<EasyUiTreeDto> children = new ArrayList<>();
                    int parentChecked = 1;
                    Iterator<EasyUiTreeDto> iter = easyUiTreeDtoList.iterator();
                    while (iter.hasNext()) {
                        EasyUiTreeDto child = iter.next();
                        if (parent.getId().equals(child.getPid())) {
                            flag = true;
                            children.add(child);
                            parentChecked *= (child.getChecked() ? 1 : 0);
                            iter.remove();
                        }
                    }
                    if(flag){
                        parent.setChecked(1 == parentChecked);
                    }
                    children.sort(Comparator.comparing(EasyUiTreeDto::getOrderNum));
                    parent.setChildren(children);
                    grandParentChecked *= (parent.getChecked() ? 1 : 0);
                }
                grandParent.setChecked(1 == grandParentChecked);
            }
        }
        //log.info(JSON.toJSONString(result));
        return result;
    }

    @Override
    public int updateAuth(String ids) {
        return 0;
    }
}