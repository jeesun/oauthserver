<#function dashedToCamel(s)>
    <#return s
    ?replace('(^_+)|(_+$)', '', 'r')
    ?replace('\\_+(\\w)?', ' $1', 'r')
    ?replace('([A-Z])', ' $1', 'r')
    ?capitalize
    ?replace(' ' , '')
    ?uncap_first
    >
</#function>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:t="http://www.w3.org/1999/xhtml">
<head th:replace="components/easyui/easyui-list :: head('${tableComment}', 'easyui')">
<body>
<div id="tb">
    <div class="datagrid-toolbar" style="padding-bottom: 6px">
<#list columns as column>
    <#if column.allowSearch>
        <#switch column.easyuiType>
        <#case "easyui-textbox">
        ${column.comment}: <input class="easyui-textbox" style="width: 160px" id="search_${column.name}" name="${column.name}" data-options="required:false">
        <#break>
        <#case "t:dict">
        ${column.comment}: <t:dict class="easyui-combobox" id="search_${column.name}" name="${column.name}" dict-name="${column.extraInfo}"  style="width:160px" allow-empty="true"></t:dict>
        <#break>
        <#case "easyui-datetimebox">
        ${column.comment}: <input class="easyui-datetimebox" style="width: 160px" id="add_${column.name}" name="${column.name}" data-options="required:false">
        <#break>
        <#case "easyui-datebox">
        ${column.comment}: <input class="easyui-datebox" style="width: 160px" id="add_${column.name}" name="${column.name}" data-options="required:false">
        <#break>
        <#default>
        ${column.comment}: <input class="easyui-textbox" style="width: 160px" id="search_${column.name}" name="${column.name}" data-options="required:false">
        </#switch>
    </#if>
</#list>
        <a href="javascript:void(0)" class="easyui-linkbutton c-primary" style="width:80px" onclick="doSearch()"><i class="fa fa-search" aria-hidden="true"></i> <span th:text="${r'#{search}'}"></span></a>
        <a href="javascript:void(0)" class="easyui-linkbutton c-basic" style="width:80px" onclick="doSearchReset()"><i class="fa fa-repeat" aria-hidden="true"></i> <span th:text="${r'#{reset}'}"></span></a>
    </div>
    <div class="datagrid-toolbar easyui-panel" style="padding:5px;">
        <a th:if="${r'${#authorization.expression('+'\'hasAnyRole(\'\'__' + r'${add}' + '__\'\')\')}'}" href="javascript:void(0)" class="easyui-linkbutton c-primary" style="width:80px" data-options="toggle:true,group:'g1'" onclick="doAdd()"><i class="fa fa-plus" aria-hidden="true"></i> <span th:text="${r'#{add}'}"></span></a>
        <a th:if="${r'${#authorization.expression('+'\'hasAnyRole(\'\'__' + r'${edit}' + '__\'\')\')}'}" href="javascript:void(0)" class="easyui-linkbutton c-warning" style="width:80px" data-options="toggle:true,group:'g1'" onclick="doEdit()"><i class="fa fa-pencil" aria-hidden="true"></i> <span th:text="${r'#{edit}'}"></span></a>
        <a th:if="${r'${#authorization.expression('+'\'hasAnyRole(\'\'__' + r'${delete}' + '__\'\')\')}'}" href="javascript:void(0)" class="easyui-linkbutton c-danger" style="width:80px" data-options="toggle:true,group:'g1'" onclick="doDelete()"><i class="fa fa-trash" aria-hidden="true"></i> <span th:text="${r'#{delete}'}"></span></a>
    </div>
</div>
<table id="tt">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
    <#list columns as column>
    <#switch column.easyuiType>
        <#case "rich_text">
        <th data-options="width:200,sortable:true,align:'center',hidden:${column.hidden?c},field:'${column.name}',formatter:formatContent">${(column.comment)}</th>
        <#break>
        <#case "image">
        <th data-options="width:200,sortable:true,align:'center',hidden:${column.hidden?c},field:'${column.name}',formatter:formatPic">${(column.comment)}</th>
        <#break>
        <#case "t:dict">
        <th data-options="width:200,sortable:true,align:'center',hidden:${column.hidden?c},field:'${column.name}',formatter:format${dashedToCamel(column.extraInfo)?cap_first}">${(column.comment)}</th>
        <#break>
        <#default>
        <th data-options="width:200,sortable:true,align:'center',hidden:${column.hidden?c},field:'${column.name}'">${(column.comment)}</th>
    </#switch>
    </#list>
    </tr>
    </thead>
</table>
<div id="dlg" class="easyui-dialog" data-options="title:'图片信息',closed:true,border:false" style="width:480px;height:480px;padding:10px"></div>
<div id="window_content" class="easyui-window" title="内容详情" data-options="modal:true,closed:true,collapsible:false,border:false" style="width:720px;height:480px;padding:10px;"></div>
<div th:replace="components/easyui/easyui-list :: js('easyui')"></div>
<script th:inline="javascript">
    /*<![CDATA[*/
    $(function () {
        $('#tt').datagrid({
            url: '/api/${entityName?uncap_first}s/data',
            method: 'get',
            idField: 'id',
            nowrap: false,
            animate: true,
            rownumbers: true,
            fit: true,
            toolbar: '#tb',
            pagination: true,
            singleSelect: false,
            selectOnCheck: true,
            checkOnSelect: true
        });
    });

    function doSearch() {
        $('#tt').datagrid('load', {
<#list columns as column>
    <#if column.allowSearch>
        <#switch column.easyuiType>
            <#case "easyui-textbox">
            ${column.name}: $('#search_${column.name}').val(),
            <#break>
            <#case "t:dict">
            ${column.name}: $('#search_${column.name}').val(),
            <#break>
            <#default>
            ${column.name}: $('#search_${column.name}').val(),
        </#switch>
    </#if>
</#list>
        });
    }

    function doSearchReset() {
<#list columns as column>
<#if column.allowSearch>
<#switch column.easyuiType>
    <#case "easyui-textbox">
        $('#search_${column.name}').textbox('setValue', '');
    <#break>
    <#case "t:dict">
        $('#search_${column.name}').combobox('select', '');
    <#break>
    <#default>
        $('#search_${column.name}').textbox('setValue', '');
</#switch>
</#if>
</#list>
        doSearch();
    }

    function doAdd() {
        parent.showWindow({
            title:'录入',
            content:'/api/${entityName?uncap_first}s/add'
        });
    }

    function doEdit() {
        //获取选中的第一行数据
        //var row = $('#tt').datagrid('getSelected');
        //获取选中的所有行数据
        let rows = $('#tt').datagrid('getSelections');
        if(rows.length == 1){
            let row = rows[0];
            parent.showWindow({
                title:'编辑',
                content:'/api/${entityName?uncap_first}s/edit?id=' + row.id
            });
        }else{
            $.messager.alert('提示信息','请选择一条数据！','error');
        }
    }

    function doDelete() {
        deleteRequest('/api/${entityName?uncap_first}s/ids/');
    }

    function formatIcon(val, row){
        return '<i class="' + val + '" aria-hidden="true"></i>';
    }

    function formatPic(val, row) {
        if(!val){
            return '';
        }
        return '<img class="image-thumb" src="' + [[${r'${filePathPrefix}'}]] + val + '" alt="头像" width="30px">';
    }

    function formatContent(val, row) {
        console.log(row.id.toString());
        return "<a href=\"javascript:void(0)\" class=\"button button-rounded button-small button-primary\" onclick='showContent(\""  + row.id.toString() + "\")'>查看</a>";
    }

    function showContent(val) {
        let rows = $('#tt').datagrid('getSelections');
        $('#window_content').html(rows[0].content);
        $('#window_content').window('open');
    }

<#list columns as column>
    <#switch column.easyuiType>
        <#case "t:dict">
    function format${dashedToCamel(column.extraInfo)?cap_first}(value, row) {
        let ${dashedToCamel(column.extraInfo)}List = [[${r'${' + dashedToCamel(column.extraInfo) + 'List}'}]];
        for(let i = 0; i < ${dashedToCamel(column.extraInfo)}List.length; i++){
            if(String(value) == String(${dashedToCamel(column.extraInfo)}List[i].typeCode)){
                return ${dashedToCamel(column.extraInfo)}List[i].typeName;
            }
        }
        return value;
    }
            <#break>
        <#default>
    </#switch>
</#list>
    /*]]>*/
</script>
</body>
</html>