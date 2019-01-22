<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:t="http://www.w3.org/1999/xhtml">
<head th:replace="components/easyui/easyui-list :: head('${tableComment}', 'easyui,upload,ueditor')">
<body>
<form id="form_edit">
<#list columns as column>
    <#if column.name == "createDate" || column.name == "createBy" || column.name == "updateDate" || column.name == "updateBy" || column.name == "userId">
    <#else>
        <#if (column.allowInput?string('yes', 'no'))=='yes'>
        <#switch column.easyuiType>
            <#case "easyui-textbox">
                <div>
                    <input class="easyui-textbox" id="edit_${column.name}" name="${column.name}" data-options="label:'${column.comment}:', width:300, required:true" th:value="${r'${' + entityName?uncap_first + "." + column.name + r'}'}">
                </div>
                <#break>
            <#case "easyui-numberbox">
                <div>
                    <input class="easyui-numberbox" id="edit_${column.name}" name="${column.name}" data-options="label:'${column.comment}:', width:300, required:true" th:value="${r'${' + entityName?uncap_first + "." + column.name + r'}'}">
                </div>
                <#break>
            <#case "easyui-datebox">
                <div>
                    <input class="easyui-datebox" id="edit_${column.name}" name="${column.name}" data-options="label:'${column.comment}:', width:300, required:true" th:value="${r'${' + entityName?uncap_first + "." + column.name + r'}'}">
                </div>
                <#break>
            <#case "easyui-datetimebox">
                <div>
                    <input class="easyui-datetimebox" id="edit_${column.name}" name="${column.name}" data-options="label:'${column.comment}:', width:300, required:true" th:value="${r'${' + entityName?uncap_first + "." + column.name + r'}'}">
                </div>
                <#break>
            <#case "image">
                <div>
                    <label style="width: 80px;float:left;">${column.comment}:</label>
                    <div th:replace="components/toolbar :: file-upload (idVal='edit_${column.name}',nameVal='${column.name}')" style="width:94%;float:left;"></div>
                    <div style="clear:both"></div>
                </div>
                <#break>
            <#case "rich_text">
                <div>
                    <label style="width: 80px;float:left;">${column.comment}:</label>
                    <div id="edit_${column.name}_editor" type="text/plain" style="width:80%;height:400px;float:left;"></div>
                    <div style="clear:both"></div>
                </div>
                <#break>
            <#case "t:select">
                <div>
                    <t:select id="edit_${column.name}" allow-empty="false" name="${column.name}" order="desc" query="${column.extraInfo}" class="easyui-combobox" data-options="label:'${column.comment}:', width:300"></t:select>
                </div>
                <#break>
            <#case "t:dict">
                <div>
                    <t:dict class="easyui-combobox" id="edit_${column.name}" name="${column.name}" dict-name="${column.extraInfo}" data-options="label:'${column.comment}:', width:300, multiple:false"></t:dict>
                </div>
                <#break>
            <#default>
                <div>
                    <input class="easyui-textbox" id="edit_${column.name}" name="${column.name}" data-options="label:'${column.comment}:', width:300, required:true" th:value="${r'${' + entityName?uncap_first + "." + column.name + r'}'}">
                </div>
        </#switch>
        </#if>
    </#if>
</#list>
    <div style="text-align:center;pediting:5px 0">
        <a href="javascript:void(0)" class="button button-rounded button-small button-primary" onclick="edit()" th:text="${r'#{ok}'}"></a>
        <a href="javascript:void(0)" class="button button-rounded button-small" onclick="clearForm()" th:text="${r'#{cancel}'}"></a>
    </div>
</form>
<div id="dlg" class="easyui-dialog" data-options="title:'图片信息',closed:true" style="width:480px;height:480px;pediting:10px"></div>
<div id="window_content" class="easyui-window" title="内容详情" data-options="modal:true,closed:true,collapsible:false" style="width:720px;height:480px;pediting:10px;"></div>
<div th:replace="components/easyui/easyui-list :: js('easyui,upload,ueditor')"></div>
<script th:inline="javascript">
    /*<![CDATA[*/
    $(function () {
        //实例化编辑器
        //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
        /*var editEditor = UE.getEditor('edit_editor', {
            autoHeight: false
        });*/
<#list columns as column>
    <#switch column.easyuiType>
        <#case "image">
            //初始化图片上传按钮
            initFileUpload('#edit_${column.name}', '${column.name}');
            //显示预览图片
            imgPreview('#edit_${column.name}', '${column.name}', [[${r'${' + entityName?uncap_first + "." + column.name + r'}'}]]);
            <#break>
        <#case "rich_text">
        var ${column.name}Editor = UE.getEditor('edit_${column.name}_editor', {
            autoHeight: false
        });
        ${column.name}Editor.ready(function () {
            //设置编辑器的内容
            ${column.name}Editor.setContent([[${r'${' + entityName?uncap_first + "." + column.name + r'}'}]]);
        });
        <#break>
    </#switch>
</#list>
    });

    function edit() {
        doRequest({
            formId: '#form_edit',
            url: '/api/${entityName?uncap_first}s',
            type: 'PATCH',
            extraData: {
    <#list columns as column>
        <#switch column.easyuiType>
            <#case "rich_text">
                ${column.name}: UE.getEditor('edit_${column.name}_editor').getContent(),
                <#break>
            <#default>
        </#switch>
    </#list>
    }
    });
    }

    function clearForm() {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }

    /*]]>*/
</script>
</body>
</html>