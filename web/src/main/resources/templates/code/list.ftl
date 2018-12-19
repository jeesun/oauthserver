<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:t="http://www.w3.org/1999/xhtml">
<head th:replace="components/easyui/easyui-list :: head('${tableComment}', 'easyui,upload,ueditor')">
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
        <#default>
        ${column.comment}: <input class="easyui-textbox" style="width: 160px" id="search_${column.name}" name="${column.name}" data-options="required:false">
        </#switch>
        </#if>
    </#list>
        <a href="javascript:void(0)" class="button button-rounded button-small button-primary" onclick="doSearch()" th:text="${r'#{search}'}"></a>
        <a href="javascript:void(0)" class="button button-rounded button-small" onclick="doSearchReset()" th:text="${r'#{reset}'}"></a>
    </div>
    <div class="datagrid-toolbar" style="padding-bottom: 6px">
        <div class="button-group">
            <button type="button" class="button button-rounded button-small button-primary" onclick="doAdd()"><i class="fa fa-plus" aria-hidden="true"></i> <span th:text="${r'#{add}'}"></span></button>
            <button type="button" class="button button-rounded button-small button-action" onclick="doEdit()"><i class="fa fa-pencil" aria-hidden="true"></i> <span th:text="${r'#{edit}'}"></span></button>
            <button type="button" class="button button-rounded button-small button-caution" onclick="doDelete()"><i class="fa fa-trash" aria-hidden="true"></i> <span th:text="${r'#{delete}'}"></span></button>
        </div>
    </div>
</div>
<table id="tt" data-options="url:'/api/${entityName?uncap_first}s/easyui/list',method:'get',animate: true,rownumbers:true,fit:true,toolbar: '#tb', pagination: true,idField:'id', singleSelect: true, selectOnCheck: true, checkOnSelect: true">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <#list columns as column>
        <th data-options="width:200,sortable:true,align:'center',hidden:${column.hidden?c},field:'${column.name}'">${(column.comment)}</th>
        </#list>
    </tr>
    </thead>
</table>
<div id="dlg" class="easyui-dialog" data-options="title:'图片信息',closed:true" style="width:480px;height:480px;padding:10px"></div>
<div id="addModal" class="easyui-window" title="录入" data-options="modal:true,closed:true,collapsible:false" style="width:60%;height:480px;padding:10px;">
    <form id="form_add">
    <#list columns as column>
    <#switch column.easyuiType>
        <#case "easyui-textbox">
        <div>
            <input class="easyui-textbox" style="width: 100%" id="add_${column.name}" name="${column.name}" data-options="label:'${column.comment}:', required:true">
        </div>
        <#break>
        <#case "image">
        <div>
            <label style="width: 80px;float:left;">${column.comment}:</label>
            <div th:replace="components/toolbar :: file-upload (idVal='add_${column.name}',nameVal='${column.name}')" style="width:94%;float:left;"></div>
            <div style="clear:both"></div>
        </div>
        <#break>
        <#case "rich_text">
        <label style="width: 80px;float:left;">${column.comment}:</label>
        <div id="add_editor" type="text/plain" class="easyui-fluid" style="height:500px;float:left;"></div>
        <div style="clear:both"></div>
        <#break>
        <#case "t:select">
        <div>
            <t:select id="add_${column.name}" allow-empty="false" name="${column.name}" order="desc" query="${column.extraInfo}" class="easyui-combobox" style="width:100%" data-options="label:'${column.comment}:'"></t:select>
        </div>
        <#break>
        <#case "t:dict">
        <div>
            <t:dict class="easyui-combobox" id="add_${column.name}" name="${column.name}" dict-name="${column.extraInfo}" style="width:100%" data-options="label:'${column.comment}:', multiple:true"></t:dict>
        </div>
        <#break>
        <#default>
        <div>
            <input class="easyui-textbox" style="width: 100%" id="add_${column.name}" name="${column.name}" data-options="label:'${column.comment}:', required:true">
        </div>
    </#switch>
    </#list>
        <div style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" class="button button-rounded button-small button-primary" onclick="add()" th:text="${r'#{ok}'}"></a>
            <a href="javascript:void(0)" class="button button-rounded button-small" onclick="clearForm()" th:text="${r'#{cancel}'}"></a>
        </div>
    </form>
</div>

<div id="editModal" class="easyui-window" title="编辑" data-options="modal:true,closed:true,collapsible:false" style="width:640px;height:480px;padding:10px;">
    <form id="form_edit">

        <div style="text-align:center;">
            <a href="javascript:void(0)" class="button button-rounded button-small button-primary" onclick="edit()" th:text="${r'#{ok}'}"></a>
            <a href="javascript:void(0)" class="button button-rounded button-small" onclick="clearForm()" th:text="${r'#{cancel}'}"></a>
        </div>
    </form>
</div>
<div id="window_content" class="easyui-window" title="内容详情" data-options="modal:true,closed:true,collapsible:false" style="width:720px;height:480px;padding:10px;"></div>
<div th:replace="components/easyui/easyui-list :: js('easyui,upload,ueditor')"></div>
<script th:inline="javascript">
    /*<![CDATA[*/
    $(function () {
        //实例化编辑器
        //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
        var addEditor = UE.getEditor('add_editor');
        var editEditor = UE.getEditor('edit_editor');
    });

    function doSearch() {
        $('#tt').treegrid('load', {
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
<#list columns as column>
    <#switch column.easyuiType>
        <#case "image">
        //初始化图片上传按钮
        initFileUpload('#edit_${column.name}', '${column.name}');
        <#break>
    </#switch>
</#list>
        $('#addModal').window('open');
    }

    function doEdit() {
        //获取选中的第一行数据
        //var row = $('#tt').datagrid('getSelected');
        //获取选中的所有行数据
        let rows = $('#tt').datagrid('getSelections');
        if(rows.length == 1){
            let row = rows[0];
<#list columns as column>
    <#switch column.easyuiType>
        <#case "easyui-textbox">
            $('#edit_${column.name}').textbox('setValue', row.${column.name});
        <#break>
        <#case "easyui-combobox">
            $('#edit_${column.name}').combobox('setValue', row.${column.name});
        <#break>
        <#case "easyui-numberbox">
            $('#edit_${column.name}').numberbox('setValue', '' + row.${column.name});
        <#break>
        <#case "image">
            //初始化图片上传按钮
            initFileUpload('#edit_${column.name}', '${column.name}');
            //显示预览图片
            imgPreview('#edit_${column.name}', '${column.name}', row.${column.name});
        <#break>
        <#case "rich_text">
            //解决ueditor被easyui-window遮盖问题
            ueditorAdapter('#editModal');
            UE.getEditor('edit_editor').setContent(row.${column.name});
        <#break>
        <#default>
            $('#edit_${column.name}').textbox('setValue', row.${column.name});
    </#switch>
</#list>
            $('#editModal').window('open');
        }else{
            $.messager.alert('提示信息','请选择一条数据！','error');
        }
    }

    function doDelete() {
        deleteRequest('/api/${entityName?uncap_first}s/ids/');
    }

    function add() {
        doRequest({
            formId: '#form_add',
            url: '/api/${entityName?uncap_first}s',
            type: 'POST',
            extraData: {
    <#list columns as column>
        <#switch column.easyuiType>
            <#case "rich_text">
                ${column.name}: UE.getEditor('add_editor').getContent(),
            <#break>
            <#default>
        </#switch>
    </#list>
            }
        });
    }

    function edit() {
        doRequest({
            formId: '#form_edit',
            url: '/api/${entityName?uncap_first}s',
            type: 'PATCH',
            extraData: {
    <#list columns as column>
        <#switch column.easyuiType>
            <#case "rich_text">
                ${column.name}: UE.getEditor('edit_editor').getContent(),
                <#break>
            <#default>
        </#switch>
    </#list>
            }
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
    /*]]>*/
</script>
</body>
</html>