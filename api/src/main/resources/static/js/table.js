/**
 *
 * User: simon
 * Date: 2018/06/07
 * Time: 12:34
 **/
function initTable(tableId, option) {
    $('#' + tableId).bootstrapTable({
        height: $(window).height(),
        url: option.url,
        toolbar: '#toolbar',
        toolbarAlign: 'left',
        cache: false,//是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        undefinedText: '',
        search: false,
        showRefresh: true,
        searchAlign: 'right',
        showToggle: false,
        showColumns: true,
        showHeader: true,
        showFooter: false,
        showFullscreen: false,
        pagination: true,
        paginationPreText: '上一页',
        paginationNextText: '下一页',
        sidePagination: option.sidePagination ? option.sidePagination : 'server',
        pageNumber: 1,
        pageSize: 10,
        pageList: [10, 25, 50, 100],
        showPaginationSwitch: true,
        minimumCountColumns: 1,
        smartDisplay: true,
        clickToSelect: true,
        sortable: true,
        striped: false,
        rowStyle: function rowStyle(row, index) {
            return {
                classes: 'text-nowrap another-class',
                css: {"color": "black"}
            };
        },
        showExport: true,
        exportDataType: 'all',
        exportTypes: ['json', 'xml', 'png', 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf'],
        exportOptions: {
            //pdf格式导出显示不全，只能先忽略列
            ignoreColumn: ((!option.ignoreColumn) ? [] : option.ignoreColumn),  //忽略某一列的索引
            fileName: "报表",  //文件名称设置
            worksheetName: 'sheet1',  //表格工作区名称
            tableName: "报表",
            excelstyles: ['background-color', 'color', 'font-size', 'font-weight']
        },
        columns: option.columns,
        detailView: option.detailView ? option.detailView : false,
        onExpandRow: function (index, row, $detail) {
            loadDetail(index, row, $detail);
        },
        resizable: true,
        queryParams: option.queryParams,// 传递参数（*）
    });
}

function initTableTreegrid(tableId, option) {
    $('#' + tableId).bootstrapTable({
        height: $(window).height(),
        url: option.url,
        toolbar: '#toolbar',
        toolbarAlign: 'left',
        cache: false,//是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        undefinedText: '',
        search: false,
        showRefresh: true,
        searchAlign: 'right',
        showToggle: false,
        showColumns: true,
        showHeader: true,
        showFooter: false,
        showFullscreen: false,
        pagination: true,
        paginationPreText: '上一页',
        paginationNextText: '下一页',
        sidePagination: 'server',
        pageNumber: 1,
        pageSize: 10,
        pageList: [10, 25, 50, 100],
        showPaginationSwitch: true,
        minimumCountColumns: 1,
        smartDisplay: true,
        clickToSelect: false,
        sortable: true,
        striped: false,
        rowStyle: function rowStyle(row, index) {
            return {
                classes: 'text-nowrap another-class',
                css: {"color": "black"}
            };
        },
        showExport: true,
        exportDataType: 'all',
        exportTypes: ['json', 'xml', 'png', 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf'],
        exportOptions: {
            //pdf格式导出显示不全，只能先忽略列
            ignoreColumn: ((!option.ignoreColumn) ? [] : option.ignoreColumn),  //忽略某一列的索引
            fileName: "报表",  //文件名称设置
            worksheetName: 'sheet1',  //表格工作区名称
            tableName: "报表",
            excelstyles: ['background-color', 'color', 'font-size', 'font-weight']
        },
        columns: option.columns,
        detailView: false,
        resizable: false,
        idField: option.idField ? option.idField : 'id',
        // bootstrap-table-tree-column.js 插件配置
        // treeShowField: 'name',
        // parentIdField: 'pid'
        // bootstrap-table-tree-column.js 插件配置

        // bootstrap-table-treegrid.js 插件配置
        treeShowField: option.treeShowField ? option.treeShowField : 'name',
        parentIdField: option.parentIdField ? option.parentIdField : 'pid',
        onLoadSuccess: function(data) {
            console.log('load');
            // jquery.treegrid.js
            $table.treegrid({
                initialState: 'collapsed',
                treeColumn: 1,
                expanderExpandedClass: 'fa fa-folder-open-o',
                expanderCollapsedClass: 'fa fa-folder-o',
                onChange: function() {
                    $table.bootstrapTable('resetWidth');
                }
            });
        },
        // bootstrap-table-treetreegrid.js 插件配置
        queryParams: option.queryParams,// 传递参数（*）
    });
}

//树形结构-子菜单
function childMenuFormatter(value, row, index, field) {
    if(row.pid){
        return '<i class="fa fa-file-o" aria-hidden="true"></i> ' + value;
    }else{
        return value;
    }
}

/**
 * 刷新table
 * @param tableId
 */
function refreshTable(tableId) {
    $('#' + tableId).bootstrapTable('refresh', {silent: false});
}