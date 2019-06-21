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
<#list columns as column>
    <#if column.name == "id" || column.name == "createDate" || column.name == "createBy" || column.name == "updateDate" || column.name == "updateBy" || column.name == "userId">
    <#else>
        <#if (column.allowSearch?string('yes', 'no'))=='yes'>
        <#switch column.uiType>
            <#case "Input">
                <el-form-item label="${column.comment}">
                    <el-input v-model="searchForm.${column.name}" placeholder="请输入${column.comment}"></el-input>
                </el-form-item>
                <#break>
            <#case "Radio">
                <el-form-item label="${column.comment}">
                    <el-input v-model="searchForm.${column.name}" placeholder="请输入${column.comment}"></el-input>
                </el-form-item>
                <#break>
            <#case "Checkbox">
                <el-form-item label="${column.comment}">
                    <el-checkbox v-model="searchForm.${column.name}">${column.comment}</el-checkbox>
                </el-form-item>
                <#break>
            <#case "InputNumber">
                <el-form-item label="${column.comment}">
                    <el-input-number v-model="searchForm.${column.name}"></el-input-number>
                </el-form-item>
                <#break>
            <#case "Select">
                <el-form-item label="${column.comment}">
                    <el-select v-model="searchForm.${column.name}" placeholder="请选择${column.comment}">
                        <el-option
                                v-for="item in ${column.extraInfo}"
                                :key="item.typeCode"
                                :label="item.typeName"
                                :value="item.typeCode">
                        </el-option>
                    </el-select>
                </el-form-item>
                <#break>
            <#case "Cascader">
                <el-form-item label="${column.comment}">
                    <el-input v-model="searchForm.${column.name}" placeholder="请输入${column.comment}"></el-input>
                </el-form-item>
                <#break>
            <#case "Switch">
                <el-form-item label="${column.comment}">
                    <el-switch
                            v-model="searchForm.${column.name}"
                            active-color="#13ce66"
                            inactive-color="#ff4949">
                    </el-switch>
                </el-form-item>
                <#break>
            <#case "Slider">
                <el-form-item label="${column.comment}">
                    <el-slider v-model="searchForm.${column.name}"></el-slider>
                </el-form-item>
                <#break>
            <#case "TimePicker">
                <el-form-item label="${column.comment}">
                    <el-time-picker
                            v-model="searchForm.${column.name}"
                            :picker-options="{selectableRange: '00:00:00 - 23:59:59'}"
                            format="HH:mm:ss"
                            value-format="HH:mm:ss"
                            placeholder="请选择${column.comment}">
                    </el-time-picker>
                </el-form-item>
                <#break>
            <#case "DatePicker">
                <el-form-item label="${column.comment}">
                    <el-date-picker
                            v-model="searchForm.${column.name}"
                            type="date"
                            format="yyyy-MM-dd"
                            value-format="yyyy-MM-dd"
                            placeholder="请选择${column.comment}">
                    </el-date-picker>
                </el-form-item>
                <#break>
            <#case "DateTimePicker">
                <el-form-item label="${column.comment}">
                    <el-date-picker
                            v-model="searchForm.${column.name}"
                            type="datetime"
                            format="yyyy-MM-dd HH:mm:ss"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="请选择${column.comment}">
                    </el-date-picker>
                </el-form-item>
                <#break>
            <#case "Upload">
                <el-form-item>
                    <el-input v-model="searchForm.${column.name}" placeholder="请输入${column.comment}"></el-input>
                </el-form-item>
                <#break>
            <#case "Rate">
                <el-form-item label="${column.comment}">
                    <el-rate
                            v-model="searchForm.${column.name}"
                            show-text>
                    </el-rate>
                </el-form-item>
                <#break>
            <#case "ColorPicker">
                <el-form-item label="${column.comment}">
                    <el-color-picker v-model="searchForm.${column.name}"></el-color-picker>
                </el-form-item>
                <#break>
            <#case "Transfer">
                <el-form-item label="${column.comment}" prop="${column.name}">
                    <el-input v-model="searchForm.${column.name}" placeholder="请输入${column.comment}"></el-input>
                </el-form-item>
                <#break>
            <#case "neditor">
                <el-form-item label="${column.comment}" prop="${column.name}">
                    <el-input v-model="searchForm.${column.name}" placeholder="请输入${column.comment}"></el-input>
                </el-form-item>
                <#break>
            <#default>
                <el-form-item label="${column.comment}" prop="${column.name}">
                    <el-input v-model="searchForm.${column.name}" placeholder="请输入${column.comment}"></el-input>
                </el-form-item>
        </#switch>
        </#if>
    </#if>
</#list>
                <el-form-item>
                    <el-button type="primary" size="mini" icon="el-icon-search" @click="doSearch"><span th:text="${r'#{search}'}"></span></el-button>
                    <el-button size="mini" icon="el-icon-refresh" @click="doReset"><span th:text="${r'#{reset}'}"></span></el-button>
                </el-form-item>
            </el-form>
            <el-button type="primary" size="mini" icon="el-icon-plus" @click="doAdd"><span th:text="${r'#{add}'}"></span></el-button>
            <el-upload
                    class="upload-demo"
                    action="/api/${entityName?uncap_first}s/import"
                    :show-file-list="false"
                    :on-success="handleImportSuccess"
                    :on-error="handleImportError"
                    :before-upload="beforeImportUpload"
                    :file-list="importList">
                <el-button type="primary" size="mini" icon="el-icon-upload2"><span th:text="${r'#{import}'}"></span></el-button>
            </el-upload>
            <el-button type="primary" size="mini" icon="el-icon-download" @click="doExport"><span th:text="${r'#{export}'}"></span></el-button>
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
            <el-table-column align="center" th:label="${r'#{operation}'}" width="200">
                <template slot-scope="scope">
                    <el-button size="mini" type="primary" icon="el-icon-edit"
                               @click="handleEdit(scope.$index, scope.row)"><span th:text="${r'#{edit}'}"></span>
                    </el-button>
                    <el-button size="mini" type="danger" icon="el-icon-delete"
                               @click="handleDelete(scope.$index, scope.row)"><span th:text="${r'#{delete}'}"></span>
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
        delete: "/api/${entityName?uncap_first}s/delete?ids=",
        import: "/api/${entityName?uncap_first}s/import",
        export: "/api/${entityName?uncap_first}s/export"
    };

    var app = new Vue({
        el: '#app',
        data: {
    <#list columns as column>
        <#if column.name == "id" || column.name == "createDate" || column.name == "createBy" || column.name == "updateDate" || column.name == "updateBy" || column.name == "userId">
        <#else>
            <#if (column.allowInput?string('yes', 'no'))=='yes' && (column.extraInfo)??>
            ${column.extraInfo}: [[${r'${' + column.extraInfo + r'}'}]],
            </#if>
        </#if>
    </#list>
            importList: [],
            tableData: [],
            pageNo: 1,
            pageSize: 10,
            total: 1000,
            tableLoading: true,
            searchForm: {
    <#list columns as column>
        <#if column.name == "id" || column.name == "createDate" || column.name == "createBy" || column.name == "updateDate" || column.name == "updateBy" || column.name == "userId">
        <#else>
            <#if (column.allowSearch?string('yes', 'no'))=='yes'>
                ${column.name}: "",
            </#if>
        </#if>
    </#list>
            }
        },
        mounted: function () {
            this.loadData(this.pageNo, this.pageSize);
        },
        methods: {
            handleImportPreview(file) {
                console.log(file);
            },
            handleImportSuccess(res, file) {
                parent.closeLoading();
                this.$message({
                    message: [[${r'#{importSuccess}'}]],
                    type: 'success'
                });
            },
            handleImportError(error, file, fileList) {
                parent.closeLoading();
                let errorMessage = [[${r'#{requestError}'}]];
                if (error.response) {
                    errorMessage = error.response.data.message;
                }
                this.$message.error(errorMessage);
            },
            beforeImportUpload(file) {
                let filename = file.name;
                let fileSuffix = filename.substr(filename.lastIndexOf("."));
                if(".xls" != fileSuffix && ".xlsx" != fileSuffix) {
                    this.$message.error([[${r'#{pleaseChooseExcelFile}'}]]);
                    return false;
                }
                parent.openLoading();
                return true;
            },
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
                    parent.showSuccess([[${r'#{requestError}'}]]);
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
                    title: [[${r'#{add}'}]],
                    content: requestUrls.edit + row.id
                });
            },
            handleDelete(index, row) {
                parent.showDelete(index, row);
            },
            doAdd(event) {
                parent.showWindow({
                    title: [[${r'#{add}'}]],
                    content: requestUrls.add
                });
            },
            doEdit(event) {

            },
            doDelete(index, row) {
                this.$http.delete(requestUrls.delete + row.id)
                    .then((response) => {
                        parent.showSuccess([[${r'#{deleteSuccess}'}]]);
                        this.loadData(this.pageNo, this.pageSize);
                    }).catch((error) => {
                    let errorMessage = [[${r'#{deleteFailed}'}]];
                    if (error.response) {
                        errorMessage = error.response.data.message;
                    }
                    parent.showError(errorMessage);
                });
            },
            doImport(event) {
                this.$http.get(requestUrls.import, {}).then(function (response) {
                    this.$notify({
                        title: [[${r'#{hint}'}]],
                        message: response.data.message,
                        position: 'bottom-right',
                        duration: 2000
                    });
                }).catch(function (error) {
                    let errorMessage = [[${r'#{importFailed}'}]];
                    if (error.response) {
                        errorMessage = error.response.data.message;
                    }
                    parent.showError(errorMessage);
                });
            },
            doExport(event) {
                window.open(requestUrls.export);
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