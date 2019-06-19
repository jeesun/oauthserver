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
            <#case "Radio">
                <el-form-item label="${column.comment}" prop="${column.name}">
                    <el-input v-model="ruleForm.${column.name}" placeholder="请输入${column.comment}"></el-input>
                </el-form-item>
                <#break>
            <#case "Checkbox">
                <el-form-item label="${column.comment}" prop="${column.name}">
                    <el-checkbox v-model="ruleForm.${column.name}">${column.comment}</el-checkbox>
                </el-form-item>
                <#break>
            <#case "InputNumber">
                <el-form-item label="${column.comment}" prop="${column.name}">
                    <el-input-number v-model="ruleForm.${column.name}"></el-input-number>
                </el-form-item>
                <#break>
            <#case "Select">
                <el-form-item label="${column.comment}" prop="${column.name}">
                    <el-select v-model="ruleForm.${column.name}" placeholder="请选择${column.comment}">
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
                <el-input v-model="ruleForm.${column.name}" placeholder="请输入${column.comment}"></el-input>
                <#break>
            <#case "Switch">
                <el-form-item label="${column.comment}" prop="${column.name}">
                    <el-switch
                            v-model="ruleForm.${column.name}"
                            active-color="#13ce66"
                            inactive-color="#ff4949">
                    </el-switch>
                </el-form-item>
                <#break>
            <#case "Slider">
                <el-form-item label="${column.comment}" prop="${column.name}">
                    <el-slider v-model="ruleForm.${column.name}"></el-slider>
                </el-form-item>
                <#break>
            <#case "TimePicker">
                <el-form-item label="${column.comment}" prop="${column.name}">
                    <el-time-picker
                            v-model="ruleForm.${column.name}"
                            :picker-options="{selectableRange: '00:00:00 - 23:59:59'}"
                            format="HH:mm:ss"
                            value-format="HH:mm:ss"
                            placeholder="请选择${column.comment}">
                    </el-time-picker>
                </el-form-item>
                <#break>
            <#case "DatePicker">
                <el-form-item label="${column.comment}" prop="${column.name}">
                    <el-date-picker
                            v-model="ruleForm.${column.name}"
                            type="date"
                            format="yyyy-MM-dd"
                            value-format="yyyy-MM-dd"
                            placeholder="请选择${column.comment}">
                    </el-date-picker>
                </el-form-item>
                <#break>
            <#case "DateTimePicker">
                <el-form-item label="${column.comment}" prop="${column.name}">
                    <el-date-picker
                            v-model="ruleForm.${column.name}"
                            type="datetime"
                            format="yyyy-MM-dd HH:mm:ss"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            placeholder="请选择${column.comment}">
                    </el-date-picker>
                </el-form-item>
                <#break>
            <#case "Upload">
                <el-form-item label="${column.comment}" prop="${column.name}">
                    <el-input v-model="ruleForm.${column.name}" placeholder="请输入${column.comment}"></el-input>
                </el-form-item>
                <#break>
            <#case "Rate">
                <el-form-item label="${column.comment}" prop="${column.name}">
                    <el-rate
                            v-model="ruleForm.${column.name}"
                            show-text>
                    </el-rate>
                </el-form-item>
                <#break>
            <#case "ColorPicker">
                <el-form-item label="${column.comment}" prop="${column.name}">
                    <el-color-picker v-model="ruleForm.${column.name}"></el-color-picker>
                </el-form-item>
                <#break>
            <#case "Transfer">
                <el-form-item label="${column.comment}" prop="${column.name}">
                    <el-input v-model="ruleForm.${column.name}" placeholder="请输入${column.comment}"></el-input>
                </el-form-item>
                <#break>
            <#case "neditor">
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
        url: "/api/${entityName?uncap_first}s/add"
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
            ruleForm: {
    <#list columns as column>
        <#if column.name == "id" || column.name == "createDate" || column.name == "createBy" || column.name == "updateDate" || column.name == "updateBy" || column.name == "userId">
        <#else>
            <#if (column.allowInput?string('yes', 'no'))=='yes'>
                ${column.name}: "",
            </#if>
        </#if>
    </#list>
            },
            rules: {

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
                        this.$http.post(requestUrls.url, this.ruleForm).then((response) => {
                            parent.closeLoading();
                            parent.showSuccess("新增成功");
                            parent.updateListData();
                            closeLayer();
                        }).catch((error) => {
                            let errorMessage = "发生错误";
                            if (error.response) {
                                errorMessage = error.response.data.message;
                            }
                            parent.closeLoading();
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