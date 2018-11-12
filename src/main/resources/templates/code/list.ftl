<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml"  xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Starter</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="_csrf" th:content="${r'${_csrf.token}'}">
    <meta name="_csrf_header" th:content="${r'${_csrf.headerName}'}">
    <link rel="stylesheet" th:href="@{/webjars/bootstrap/3.3.7/css/bootstrap.min.css}">
    <link th:href="@{/webjars/font-awesome/4.7.0/css/font-awesome.min.css}" rel="stylesheet">
    <link th:href="@{/webjars/ionicons/2.0.1/css/ionicons.min.css}" rel="stylesheet">
    <link rel="stylesheet" th:href="@{/webjars/AdminLTE/2.4.2/dist/css/AdminLTE.min.css}">
    <link rel="stylesheet" th:href="@{/webjars/AdminLTE/2.4.2/dist/css/skins/_all-skins.min.css}">
    <link rel="stylesheet" th:href="@{/webjars/bootstrap-table/1.11.1/dist/bootstrap-table.min.css}">
    <link rel="stylesheet" th:href="@{/css/user-manage.css}">
    <!--[if lt IE 9]>
    <script src="/thymelte/webjars/html5shiv/3.7.3/html5shiv-printshiv.min.js"></script>
    <script src="/thymelte/webjars/respond/1.4.2/dest/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" th:href="@{/css/common.css}">
</head>
<body>
<div>
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            用户管理
            <small>封号处理</small>
        </h1>
    </section>

    <!-- Main content -->
    <section class="content container-fluid">
        <div th:replace="components/toolbar :: toolbar"></div>
        <table id="table"></table>
    </section>
    <!-- /.content -->
</div>
<!-- ./wrapper -->
<!-- jQuery 3 -->
<script th:src="@{/webjars/jquery/3.3.1/dist/jquery.min.js}"></script>
<!-- Bootstrap 3.3.7 -->
<script th:src="@{/webjars/bootstrap/3.3.7/js/bootstrap.min.js}"></script>
<!-- AdminLTE App -->
<script th:src="@{/webjars/AdminLTE/2.4.2/dist/js/adminlte.min.js}"></script>
<script th:src="@{/webjars/bootstrap-table/1.11.1/dist/bootstrap-table.min.js}"></script>
<script th:src="@{/webjars/bootstrap-table/1.11.1/dist/locale/bootstrap-table-zh-CN.min.js}"></script>

<!-- tableExport -->
<script th:src="@{/webjars/bootstrap-table/1.11.1/dist/extensions/export/bootstrap-table-export.min.js}"></script>
<script th:src="@{/plug-in/tableExport.jquery.plugin/libs/FileSaver/FileSaver.min.js}"></script>
<script th:src="@{/plug-in/tableExport.jquery.plugin/libs/js-xlsx/xlsx.core.min.js}"></script>
<script th:src="@{/plug-in/tableExport.jquery.plugin/libs/jsPDF/jspdf.min.js}"></script>
<script th:src="@{/plug-in/tableExport.jquery.plugin/libs/jsPDF-AutoTable/jspdf.plugin.autotable.js}"></script>
<script th:src="@{/plug-in/tableExport.jquery.plugin/libs/pdfmake/pdfmake.min.js}"></script>
<script th:src="@{/plug-in/tableExport.jquery.plugin/libs/pdfmake/vfs_fonts.js}"></script>
<script th:src="@{/plug-in/tableExport.jquery.plugin/libs/html2canvas/html2canvas.min.js}"></script>
<script th:src="@{/plug-in/tableExport.jquery.plugin/tableExport.js}"></script>

<script th:src="@{/webjars/jquery-cookie/1.4.1-1/jquery.cookie.js}"></script>

<script th:src="@{/js/util.js}"></script>
<script th:src="@{/js/table.js}"></script>
<script th:src="@{/js/common.js}"></script>
<script>
    initTable('table', {
        url: 'data',
        columns: [{
            field: 'state',
            checkbox: true
        },
    <#list columns as column>
    {
            field: '${(column.name)}',
            title: '${column.comment}',
            align: 'center',
            <#if '${column.type}'=="Date">
            formatter: function formatter(value, row, index, field) {
                if(!value){
                    return value;
                }
                return new Date(value).format("yyyy-MM-dd hh:mm:ss");
            }
            </#if>
        },
    </#list>
        {
            field: 'id',
            title: '操作',
            align: 'center',
            formatter: function formatter(value, row, index, field) {
                return '<button type="button" class="btn btn-primary" onclick="updateObject(' + value + ')"><i class="fa fa-pencil" aria-hidden="true"></i> 编辑</button> <button type="button" class="btn btn-danger" onclick="deleteObject(' + value + ')"><i class="fa fa-trash-o" aria-hidden="true"></i> 删除</button>';
            }
        }],
        ignoreColumn: []
    });

</script>
</body>
</html>