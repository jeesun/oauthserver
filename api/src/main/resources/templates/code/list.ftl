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
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:v-bind="http://www.w3.org/1999/xhtml">
<head th:replace="components/vue/vue-list :: head('${tableComment}', 'list')"></head>
<body>
<div id="app">
    <template>
        <div style="margin-left: 6px;margin-bottom: 6px;">
            <el-form :inline="true" :model="searchForm" style="margin: 0; padding: 0;">
                <el-form-item label="英文标签">
                    <el-input size="small" v-model="searchForm.label" placeholder="请输入英文标签" clearable></el-input>
                </el-form-item>
                <el-form-item label="中文标签">
                    <el-input size="small" v-model="searchForm.tags" placeholder="请输入中文标签" clearable></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" size="mini" icon="el-icon-search" @click="doSearch">搜索</el-button>
                    <el-button size="mini" icon="el-icon-refresh" @click="doReset">重置</el-button>
                </el-form-item>
            </el-form>
            <el-button type="primary" size="mini" icon="el-icon-plus" @click="doAdd">新增</el-button>
            <el-button type="primary" size="mini" icon="el-icon-upload2" @click="doImport">导入</el-button>
            <el-button type="primary" size="mini" icon="el-icon-download" @click="doExport">导出</el-button>
        </div>
        <el-table border stripe size="medium" height="320" :data="tableData" highlight-current-row
                  style="font-size: 12px"
                  v-loading="tableLoading" cell-style="padding:1px">
            <el-table-column align="center" type="index" :index="indexSerial" width="50"></el-table-column>
            <#list columns as column>
                <#switch column.uiType>
                    <#default>
                    <el-table-column align="center" prop="${column.name}" label="${column.comment}" width="120"></el-table-column>
                </#switch>
            </#list>
            <#--<el-table-column align="center" prop="id" label="id" width="100"></el-table-column>
            <el-table-column align="center" prop="iconClass" label="图标class" width="180"></el-table-column>
            <el-table-column align="center" prop="iconClass" label="图标预览" width="100">
                <template slot-scope="scope">
                    <i :class="scope.row.iconClass" style="font-size: 1.2em"></i>
                </template>
            </el-table-column>
            <el-table-column align="center" prop="label" label="英文标签" width="160" :show-overflow-tooltip="true"></el-table-column>
            <el-table-column align="center" prop="tags" label="中文标签" width="160" :show-overflow-tooltip="true"></el-table-column>
            <el-table-column align="center" prop="orderNum" label="排序" width="100"></el-table-column>
            <el-table-column align="center" prop="status" label="状态" width="100"></el-table-column>-->
            <el-table-column align="center" label="操作" width="200">
                <template slot-scope="scope">
                    <el-button size="mini" type="primary" icon="el-icon-edit"
                               @click="handleEdit(scope.$index, scope.row)">编辑
                    </el-button>
                    <el-button size="mini" type="danger" icon="el-icon-delete"
                               @click="handleDelete(scope.$index, scope.row)">删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-col class="toolbar" style="padding:10px;">
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageNo"
                           :page-sizes="[10, 20, 30, 40]" :page-size="pageSize"
                           layout="total, sizes, prev, pager, next, jumper" :total="total"
                           style="float:right"></el-pagination>
        </el-col>
    </template>
</div>
</body>
<script th:src="@{/js/vue/common.js}"></script>
<script th:inline="javascript">
    /*<![CDATA[*/
    //Vue.http.options.emulateJSON = true;
    //Vue.http.options.emulateHTTP = true;
    var headerName = [[${r'${_csrf.headerName}'}]];
    axios.defaults.headers.common[headerName] = [[${r'${_csrf.token}'}]];

    var requestUrls = {
        list: "/api/${entityName?uncap_first}s/data",
        add: "/api/${entityName?uncap_first}s/add",
        edit: "/api/${entityName?uncap_first}s/edit?id=",
        delete: "/api/${entityName?uncap_first}s/ids/"
    };

    var app = new Vue({
        el: '#app',
        data: {
            tableData: [],
            pageNo: 1,
            pageSize: 10,
            total: 1000,
            tableLoading: true,
            searchForm: {
                label: "",
                tags: ""
            }
        },
        mounted: function () {
            this.loadData(this.pageNo, this.pageSize);
        },
        methods: {
            indexSerial(index) {
                return index + 1 + (this.pageNo - 1) * this.pageSize;
            },
            loadData(pageNo, pageSize) {
                if (!isNaN(pageNo)) {
                    this.pageNo = pageNo;
                }
                if (!isNaN(pageSize)) {
                    this.pageSize = pageSize;
                }
                var params_ = {
                    pageNo: this.pageNo,
                    pageSize: this.pageSize
                };
                for (let i in this.searchForm) {
                    if (this.searchForm[i] && this.searchForm[i].trim() != "") {
                        params_[i] = this.searchForm[i];
                    }
                }

                this.tableLoading = true;
                this.$http.get(requestUrls.list, {
                    params: params_
                }).then((response) => {
                    this.tableLoading = false;
                    this.total = parseInt(response.data.total);
                    this.tableData = [];
                    for (let key in response.data.rows) {
                        this.$set(this.tableData, key, response.data.rows[key]);
                    }
                }).catch((response) => {
                    this.tableLoading = false;
                    console.error(response);
                    parent.showError("发生错误");
                });
            },
            //每页显示数据量变更
            handleSizeChange(val) {
                //清除上一次请求的缓存，不然字体图标有缓存
                this.tableData = [];

                this.pageSize = val;
                this.loadData(this.pageNo, this.pageSize);
            },
            //页码变更
            handleCurrentChange(val) {
                //清除上一次请求的缓存，不然字体图标有缓存
                this.tableData = [];

                this.pageNo = val;
                this.loadData(this.pageNo, this.pageSize);
            },
            doSearch(event) {
                this.pageNo = 1;
                this.loadData(this.pageNo, this.pageSize);
            },
            doReset(event) {
                for (let i in this.searchForm) {
                    this.searchForm[i] = null;
                }
                this.doSearch(event);
            },
            handleEdit(index, row) {
                parent.showWindow({
                    title: '新增',
                    content: requestUrls.edit + row.id
                });
            },
            handleDelete(index, row) {
                parent.showDelete(index, row);
            },
            doAdd(event) {
                parent.showWindow({
                    title: '新增',
                    content: requestUrls.add
                });
            },
            doEdit(event) {

            },
            doDelete(index, row) {
                this.$http.delete(requestUrls.delete + row.id)
                    .then((response) => {
                        parent.showSuccess("删除成功");
                        this.loadData(this.pageNo, this.pageSize);
                    }).catch((response) => {
                    console.error(response);
                });
            },
            doImport(event) {
                this.$http.get("/api/authorities/import", {}).then(function (response) {
                    this.$notify({
                        title: '提示',
                        message: response.data.message,
                        position: 'bottom-right',
                        duration: 2000
                    });
                }).catch(function (response) {
                    console.error(response);
                });
            },
            doExport(event) {
                window.open("/api/authorities/export");
            }
        }
    });

    function iframeMethond(index, row) {
        console.log('iframeMethond');
        app.doDelete(index, row);
    }

    function iframeUpdateList() {
        app.loadData(app.pageNo, app.pageSize);
    }

    /*]]>*/
</script>
</html>