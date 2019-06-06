<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head th:replace="components/vue/vue-list :: head('${tableComment}', 'commit')"></head>
<body>
<div id="app" style="padding-top: 10px">
    <el-row>
        <el-col :xs="{span: 24, offset: 0}" :sm="{span: 12, offset: 6}" :md="{span: 8, offset: 8}"
                :lg="{span: 8, offset: 8}" :xl="{span: 8, offset: 8}">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
<#list columns as column>
    <#if column.name == "id" || column.name == "createDate" || column.name == "createBy" || column.name == "updateDate" || column.name == "updateBy" || column.name == "userId">
    <#else>
    <#if (column.allowInput?string('yes', 'no'))=='yes'>
        <#switch column.uiType>
            <#case "Input">
                <el-form-item label="${column.comment}" prop="${column.name}">
                    <el-input v-model="ruleForm.${column.name}" placeholder="请输入${column.comment}"></el-input>
                </el-form-item>
                <#break>
            <#default>
                <el-form-item label="${column.comment}" prop="${column.name}">
                    <el-input v-model="ruleForm.${column.name}" placeholder="请输入${column.comment}"></el-input>
                </el-form-item>
        </#switch>
    </#if>
    </#if>
</#list>
                <#--<el-form-item label="图标class" prop="iconClass">
                    <el-input v-model="ruleForm.iconClass" placeholder="请输入图标class"></el-input>
                </el-form-item>
                <el-form-item label="英文标签" prop="label">
                    <el-input v-model="ruleForm.label" placeholder="请输入英文标签"></el-input>
                </el-form-item>
                <el-form-item label="中文标签" prop="tags">
                    <el-input v-model="ruleForm.tags" placeholder="请输入中文标签"></el-input>
                </el-form-item>
                <el-form-item label="排序" prop="orderNum">
                    <el-input v-model="ruleForm.orderNum" placeholder="请输入排序"></el-input>
                </el-form-item>
                <el-form-item label="状态" prop="status">
                    <el-select v-model="ruleForm.status" placeholder="请输入状态">
                        <el-option label="可用" value="1"></el-option>
                        <el-option label="不可用" value="0"></el-option>
                    </el-select>
                </el-form-item>-->
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                    <el-button @click="closeWindow">关闭</el-button>
                </el-form-item>
            </el-form>
        </el-col>
    </el-row>
</div>
</body>
<script th:src="@{/js/vue/common.js}"></script>
<script th:src="@{/js/vue/validateRule.js}"></script>
<script th:inline="javascript">
    /*<![CDATA[*/
    //Vue.http.options.emulateJSON = true;
    //Vue.http.options.emulateHTTP = true;
    var headerName = [[${r'${_csrf.headerName}'}]];
    axios.defaults.headers.common[headerName] = [[${r'${_csrf.token}'}]];

    var requestUrls = {
        url: "/api/${entityName?uncap_first}s/edit"
    };

    var app = new Vue({
        el: '#app',
        data: {
            ruleForm: {
    <#list columns as column>
        <#if column.name == "createDate" || column.name == "createBy" || column.name == "updateDate" || column.name == "updateBy" || column.name == "userId">
        <#else>
            <#if (column.allowInput?string('yes', 'no'))=='yes'>
                ${column.name}: [[${r'${entity.' + column.name + '}'}]],
            </#if>
        </#if>
    </#list>
            },
            rules: {
                iconClass: [
                    {required: true, message: '图标class不能为空', trigger: 'blur'}
                ],
                label: [
                    {required: true, message: '英文标签不能为空', trigger: 'blur'}
                ],
                tags: [
                    {required: true, message: '中文标签不能为空', trigger: 'blur'}
                ]
            }
        },
        mounted: function () {

        },
        methods: {
            sendMessage(event) {
                console.log("sendMessage");
                closeLayer();
            },
            submitForm(formName) {
                parent.openLoading();
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$http.patch(requestUrls.url, this.ruleForm).then((response) => {
                            parent.closeLoading();
                            parent.showSuccess("新增成功");
                            parent.updateListData();
                            closeLayer();
                        }).catch((error) => {
                            parent.closeLoading();
                            let errorMessage = "发生错误";
                            if (error.response) {
                                errorMessage = error.response.data.message;
                            }
                            parent.showError(errorMessage);
                        });
                    } else {
                        parent.closeLoading();
                        setTimeout(()=>{
                            let isError= document.getElementsByClassName("is-error");
                            isError[0].querySelector('input').focus();
                        },100);
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            closeWindow(event) {
                closeLayer();
            },
            handleAvatarSuccess(res, file) {
                //this.ruleForm.headPhoto = URL.createObjectURL(file.raw);
                console.log(eval(res));
                res = eval(res);
                this.ruleForm.headPhoto = res.data[0];
            },
            beforeAvatarUpload(file) {
                console.log(file.type);
                const isJPG = file.type === 'image/jpeg';
                const isPNG = file.type === 'image/png';
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isJPG && !isPNG) {
                    this.$message.error('上传头像图片只能是 JPG/PNG 格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB!');
                }
                return isJPG && isLt2M;
            }
        }
    });
    /*]]>*/
</script>
</html>