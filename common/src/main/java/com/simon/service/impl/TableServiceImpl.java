package com.simon.service.impl;

import com.google.common.base.CaseFormat;
import com.simon.common.code.Column;
import com.simon.dto.GenCodeDto;
import com.simon.mapper.SideMenuAuthorityMapper;
import com.simon.mapper.SideMenuMapper;
import com.simon.model.ColumnUi;
import com.simon.model.SideMenu;
import com.simon.model.SideMenuAuthority;
import com.simon.repository.ColumnUiRepository;
import com.simon.repository.SideMenuRepository;
import com.simon.service.TableService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据表
 *
 * @author simon
 * @date 2019-05-04
 */
@Slf4j
@Service
@Transactional(rollbackFor = {Exception.class})
public class TableServiceImpl implements TableService {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private ColumnUiRepository columnUiRepository;

    @Autowired
    private SideMenuMapper sideMenuMapper;

    @Autowired
    private SideMenuRepository sideMenuRepository;

    @Autowired
    private SideMenuAuthorityMapper sideMenuAuthorityMapper;

    @Override
    public void saveSettingsAndAuthorities(GenCodeDto body) {
        saveSettings(body);
        saveAuthorities(body);
    }

    /**
     * 保存用户生成代码时的UI属性配置
     *
     * @param body
     */
    private void saveSettings(GenCodeDto body) {
        String tableName = body.getTableName();
        List<Column> columnList = body.getColumns();

        List<ColumnUi> columnUiList = new ArrayList<>();
        for (Column column : columnList) {
            ColumnUi columnUi = new ColumnUi();
            BeanUtils.copyProperties(column, columnUi);
            columnUi.setTableName(tableName);
            columnUi.setId(null);
            columnUiList.add(columnUi);
        }
        columnUiRepository.deleteByTableName(tableName);
        columnUiList = columnUiRepository.save(columnUiList);

    }

    /**
     * 代码生成时，向t_side_menu表添加访问权限数据
     *
     * @param body
     */
    private void saveAuthorities(GenCodeDto body) {
        if (null == body.getPid() || null == body.getAllowedRoles() || body.getAllowedRoles().length <= 0 || null == body.getGenModules() || body.getGenModules().length <= 0) {
            return;
        }
        //只有当生成controllerAndPage时，才添加访问权限数据。
        boolean hasPage = false;
        for (int i = 0, len = body.getGenModules().length; i < len; i++) {
            if ("controllerAndPage".equalsIgnoreCase(body.getGenModules()[i])) {
                hasPage = true;
            }
        }
        if (!hasPage) {
            return;
        }

        String tableName = body.getTableName();
        String entityName = body.getEntityName();
        String tableComment = body.getTableComment();
        Long pid = body.getPid();
        List<Column> columnList = body.getColumns();
        String allowedRoles = StringUtils.join(body.getAllowedRoles(), ",");

        //先从菜单权限表删除之前的权限信息
        sideMenuAuthorityMapper.deleteByEntityName(entityName);

        //再从菜单表删除之前的配置信息
        sideMenuMapper.deleteByEntityName(entityName);


        LocalDateTime createDate = LocalDateTime.now();
        //首字母小写
        entityName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, entityName);
        String baseUrl = "/api/" + entityName + "s/";
        //期望结果：baseUrl下面，有查看、新增、修改、删除、导入、导出权限控制

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
        //2级菜单
        listSideMenu.setMenuType(2);
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
        //3级菜单
        dataSideMenu.setMenuType(3);
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
        //3级菜单
        addSideMenu.setMenuType(3);
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
        //3级菜单
        editSideMenu.setMenuType(3);
        sideMenuMapper.insertSelective(editSideMenu);

        //删除操作
        SideMenu deleteSideMenu = new SideMenu();
        deleteSideMenu.setCreateDate(createDate);
        deleteSideMenu.setPid(listSideMenu.getId());
        deleteSideMenu.setName("删除");
        deleteSideMenu.setUrl(baseUrl + "delete");
        deleteSideMenu.setRequestMethod("DELETE");
        deleteSideMenu.setShowInMenu(false);
        deleteSideMenu.setEntityName(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, entityName));
        deleteSideMenu.setRemark("delete");
        //3级菜单
        deleteSideMenu.setMenuType(3);
        sideMenuMapper.insertSelective(deleteSideMenu);

        //导入操作
        SideMenu importMenu = new SideMenu();
        importMenu.setCreateDate(createDate);
        importMenu.setPid(listSideMenu.getId());
        importMenu.setName("导入");
        importMenu.setUrl(baseUrl + "import");
        importMenu.setRequestMethod("POST");
        importMenu.setShowInMenu(false);
        importMenu.setEntityName(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, entityName));
        importMenu.setRemark("import");
        //3级菜单
        importMenu.setMenuType(3);
        sideMenuMapper.insertSelective(importMenu);

        //导出操作
        SideMenu exportMenu = new SideMenu();
        exportMenu.setCreateDate(createDate);
        exportMenu.setPid(listSideMenu.getId());
        exportMenu.setName("导出");
        exportMenu.setUrl(baseUrl + "export");
        exportMenu.setRequestMethod("GET");
        exportMenu.setShowInMenu(false);
        exportMenu.setEntityName(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, entityName));
        exportMenu.setRemark("export");
        //3级菜单
        exportMenu.setMenuType(3);
        sideMenuMapper.insertSelective(exportMenu);

        //准备添加url权限数据
        if (StringUtils.isEmpty(allowedRoles)) {
            allowedRoles = "ROLE_SU";
        }
        allowedRoles = allowedRoles.replace(" ", "");

        String[] allowedRoleArr = allowedRoles.split(",");
        List<SideMenuAuthority> sideMenuAuthorityList = new ArrayList<>();
        for (int i = 0; i < allowedRoleArr.length; i++) {
            SideMenuAuthority listAuthority = new SideMenuAuthority();
            listAuthority.setCreateDate(createDate);
            listAuthority.setSideMenuId(listSideMenu.getId());
            listAuthority.setAuthority(allowedRoleArr[i]);
            sideMenuAuthorityList.add(listAuthority);

            SideMenuAuthority dataAuthority = new SideMenuAuthority();
            dataAuthority.setCreateDate(createDate);
            dataAuthority.setSideMenuId(dataSideMenu.getId());
            dataAuthority.setAuthority(allowedRoleArr[i]);
            sideMenuAuthorityList.add(dataAuthority);

            SideMenuAuthority addAuthority = new SideMenuAuthority();
            addAuthority.setCreateDate(createDate);
            addAuthority.setSideMenuId(addSideMenu.getId());
            addAuthority.setAuthority(allowedRoleArr[i]);
            sideMenuAuthorityList.add(addAuthority);

            SideMenuAuthority editAuthority = new SideMenuAuthority();
            editAuthority.setCreateDate(createDate);
            editAuthority.setSideMenuId(editSideMenu.getId());
            editAuthority.setAuthority(allowedRoleArr[i]);
            sideMenuAuthorityList.add(editAuthority);

            SideMenuAuthority deleteAuthority = new SideMenuAuthority();
            deleteAuthority.setCreateDate(createDate);
            deleteAuthority.setSideMenuId(deleteSideMenu.getId());
            deleteAuthority.setAuthority(allowedRoleArr[i]);
            sideMenuAuthorityList.add(deleteAuthority);

            SideMenuAuthority importAuthority = new SideMenuAuthority();
            importAuthority.setCreateDate(createDate);
            importAuthority.setSideMenuId(importMenu.getId());
            importAuthority.setAuthority(allowedRoleArr[i]);
            sideMenuAuthorityList.add(importAuthority);

            SideMenuAuthority exportAuthority = new SideMenuAuthority();
            exportAuthority.setCreateDate(createDate);
            exportAuthority.setSideMenuId(exportMenu.getId());
            exportAuthority.setAuthority(allowedRoleArr[i]);
            sideMenuAuthorityList.add(exportAuthority);
        }

        //批量保存
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        SideMenuAuthorityMapper smaMapper = sqlSession.getMapper(SideMenuAuthorityMapper.class);
        if (!CollectionUtils.isEmpty(sideMenuAuthorityList)) {
            for (SideMenuAuthority item : sideMenuAuthorityList) {
                smaMapper.insert(item);
            }
        }
        sqlSession.commit();
    }
}
