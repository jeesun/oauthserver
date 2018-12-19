<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:t="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="_csrf" th:content="${_csrf.token}">
    <meta name="_csrf_header" th:content="${_csrf.headerName}">
    <title>${tableComment}</title>
    <script th:src="@{/js/easyui/base_loading.js}"></script>
    <link rel="stylesheet" type="text/css" th:href="@{/webjars/easyui/1.6.7/themes/super/css/font-awesome.min.css}">
    <link rel="stylesheet" type="text/css" th:href="@{/webjars/easyui/1.6.7/themes/super/superBlue.css}" id="themeCss"/>
    <!--<link rel="stylesheet" type="text/css" th:href="@{/webjars/easyui/1.6.7/themes/material/easyui.css}">
    <link rel="stylesheet" type="text/css" th:href="@{/webjars/easyui/1.6.7/themes/icon.css}">
    <link rel="stylesheet" type="text/css" th:href="@{/webjars/easyui/1.6.7/themes/color.css}">
    <link rel="stylesheet" type="text/css" th:href="@{/webjars/easyui/1.6.7/demo/demo.css}">-->

    <link rel="stylesheet" th:href="@{/plug-in/buttons.jquery.plugin/buttons.css}">

    <link rel="stylesheet" th:href="@{/css/easyui/common.css}">
</head>
<body>
<div id="tb">
    <div class="datagrid-toolbar" style="padding-bottom: 6px">

        <a href="javascript:void(0)" class="button button-rounded button-small button-primary" onclick="doSearch()" th:text="#{search}"></a>
        <a href="javascript:void(0)" class="button button-rounded button-small" onclick="doSearchReset()" th:text="#{reset}"></a>
    </div>
    <div class="datagrid-toolbar" style="padding-bottom: 6px">
        <div class="button-group">
            <button type="button" class="button button-rounded button-small button-primary" onclick="doAdd()"><i class="fa fa-plus" aria-hidden="true"></i> <span th:text="#{add}"></span></button>
            <button type="button" class="button button-rounded button-small button-action" onclick="doEdit()"><i class="fa fa-pencil" aria-hidden="true"></i> <span th:text="#{edit}"></span></button>
            <button type="button" class="button button-rounded button-small button-caution" onclick="doDelete()"><i class="fa fa-trash" aria-hidden="true"></i> <span th:text="#{delete}"></span></button>
        </div>
    </div>
</div>
<table id="tt" data-options="url:'/api/${entityName?uncap_first}s/easyui/list',method:'get',animate: true,rownumbers:true,fit:true,toolbar: '#tb', pagination: true,idField:'userId', singleSelect: false, selectOnCheck: true, checkOnSelect: true">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
    <#list columns as column>
        <th data-options="field:'${column.name}',width:200,sortable:true,align:'center'">${(column.annotation)}</th>
    </#list>
    </tr>
    </thead>
</table>
<div id="addModal" class="easyui-window" title="录入" data-options="modal:true,closed:true,collapsible:false" style="width:640px;height:480px;padding:10px;">
    <form id="form_add">

        <div style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" class="button button-rounded button-small button-primary" onclick="add()" th:text="#{ok}"></a>
            <a href="javascript:void(0)" class="button button-rounded button-small" onclick="clearForm()" th:text="#{cancel}"></a>
        </div>
    </form>
</div>

<div id="editModal" class="easyui-window" title="编辑" data-options="modal:true,closed:true,collapsible:false" style="width:640px;height:480px;padding:10px;">
    <form id="form_edit">

        <div style="text-align:center;">
            <a href="javascript:void(0)" class="button button-rounded button-small button-primary" onclick="edit()" th:text="#{ok}"></a>
            <a href="javascript:void(0)" class="button button-rounded button-small" onclick="clearForm()" th:text="#{cancel}"></a>
        </div>
    </form>
</div>
<script type="text/javascript" th:src="@{/webjars/easyui/1.6.7/jquery.min.js}"></script>
<script type="text/javascript" th:src="@{/webjars/easyui/1.6.7/jquery.easyui.min.js}"></script>
<script type="text/javascript" charset="utf-8" th:src="@{/webjars/easyui/1.6.7/themes/super/super.js}"></script>
<script th:if="__${#locale}__ eq 'zh_CN'" th:src="@{/webjars/easyui/1.6.7/locale/easyui-lang-zh_CN.js}"></script>
<script th:src="@{/js/easyui/common.js}"></script>
<script>
    function doSearch() {
        $('#tt').treegrid('load', {

        });
    }

    function doSearchReset() {

        doSearch();
    }

    function doAdd() {
        $('#addModal').window('open');
    }

    function doEdit() {
        //获取选中的第一行数据
        //var row = $('#tt').datagrid('getSelected');
        //获取选中的所有行数据
        let rows = $('#tt').datagrid('getSelections');
        if(rows.length == 1){
            let row = rows[0];
            console.log(row);
            $('#editModal').window('open');
        }else{
            $.messager.alert('提示信息','请选择一条数据！','error');
        }
    }

    function doDelete() {

    }

    function add() {
        doRequest({
            formId: '#form_add',
            url: '/api/${entityName?uncap_first}s',
            type: 'POST'
        });
    }

    function edit() {
        doRequest({
            formId: '#form_edit',
            url: '/api/${entityName?uncap_first}s',
            type: 'PATCH'
        });
    }

    function clearForm() {
        $('#form_add').form('clear');
        $('#addModal').window('close');

        $('#form_edit').form('clear');
        $('#editModal').window('close');
    }

    function formatIcon(val, row){
        return '<i class="' + val + '" aria-hidden="true"></i>';
    }
</script>
</body>
</html>