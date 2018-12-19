/**
 *
 * User: simon
 * Date: 2018/10/30
 * Time: 9:54
 **/
document.write('<script src="/js/easyui/dropdown.js"></script>');

function setTokenInHeader() {
    let token = $("meta[name='_csrf']").attr("content");
    let header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}

$(function(){
    setTokenInHeader();

    //只用一种初始化方法来声明easyUI组件以避免重复的提交请求，即删除html中的class声明(class="easyui-datagrid")
    $('#tt').treegrid({
        onBeforeLoad: function (row, param) {
            let pageNo = param.page;
            delete param.page;
            param.pageNo = pageNo;
            let pageSize = param.rows;
            delete param.rows;
            param.pageSize = pageSize;
            let sort = param.sort;
            delete param.sort;
            let order = param.order;
            delete param.order;
            let orderBy = ((!sort) ? "" : sort) + " " + ((!order) ? "" : order);
            orderBy = orderBy.trim();
            param.orderBy = orderBy;
        },
        onLoadSuccess: function (data) {
            //重新渲染
            $(".easyui-linkbutton").linkbutton();
            $(".easyui-numberbox").numberbox();
        }
    });

    $('body').on('click', 'img.image-thumb',function (event) {
        $('#dlg').html('<img src="' + $(this).attr('src') + '" alt="头像" width="100%">');
        $('#dlg').dialog('open');
    });

});

function initEditor() {
    let token = $("meta[name='_csrf']").attr("content");
    $('textarea').froalaEditor({
        width: '100%',
        language: 'zh_cn',
        height: 300,
        heightMax: 500,
        heightMin: 200,
        fileUploadParam: 'file',
        fileUploadURL: '/fileUploads/uploadFile',
        fileUploadParams: {},
        fileUploadMethod: 'POST',
        fileMaxSize: 20 * 1024 * 1024,
        fileAllowedTypes: ['*'],
        imageAllowedTypes: ['jpeg', 'jpg', 'png'],
        imageDefaultWidth: 600,
        imageMaxSize: 1024 * 1024 * 3,
        imageMinWidth: 600,
        imageUploadParam: 'file',
        imageUploadRemoteUrls: false,
        imageUploadURL: '/fileUploads/uploadFile',
        requestHeaders: {
            'X-CSRF-TOKEN':token
        }
    });

    $('textarea').on('froalaEditor.contentChanged', function (e, editor) {
        console.log($(this).froalaEditor('html.get', true));
        $('input[name="content"]').val($(this).froalaEditor('html.get', true));
    });

    $('textarea').on('froalaEditor.file.uploaded', function (e, editor, response) {
        console.log("uploaded=" + response);
    }).on('froalaEditor.file.error', function (e, editor, error, response) {
        console.log("error=" + JSON.stringify(error));
        console.log("response" + response);
        // Bad link.
        if (error.code == 1) {
            console.log("bad link");
        }

        // No link in upload response.
        else if (error.code == 2) {
            console.log("No link in upload response.");
        }

        // Error during file upload.
        else if (error.code == 3) {
            console.log("Error during file upload.");
        }

        // Parsing response failed.
        else if (error.code == 4) {
            console.log("Parsing response failed.");
        }

        // File too text-large.
        else if (error.code == 5) {
            console.log("File too text-large.");
        }

        // Invalid file type.
        else if (error.code == 6) {
            console.log("Invalid file type.");
        }

        // File can be uploaded only to same domain in IE 8 and IE 9.
        else if (error.code == 7) {
            console.log("File can be uploaded only to same domain in IE 8 and IE 9.");
        }

        // Response contains the original server response to the request if available.
    });

    $('textarea').on('froalaEditor.image.uploaded', function (e, editor, response) {
        console.log("uploaded=" + response);
        var json = jQuery.parseJSON(response);
        var img_URL = json['link'];
        editor.image.insert(img_URL, false, {'class': 'image-thumb'}, editor.image.get(), null);
        return false;
    });

    //$('textarea').off('froalaEditor.file.uploaded');
    $('textarea').off('froalaEditor.contentChanged');
    //$('textarea').off('froalaEditor.image.uploaded');
    $('textarea').off('froalaEditor.file.uploaded');
}

function getLocalTime(timestamp) {
    return new Date(parseInt(timestamp)).toLocaleString().replace(/:\d{1,2}$/, ' ');
}

Date.prototype.format = function(format) {
    let date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (let k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
};

function formatDate(val, row){
    return new Date(parseInt(val)).format('yyyy-MM-dd hh:mm:ss');
}

function doRequest(options) {
    if($(options.formId).form('validate')){
        let requestData = $(options.formId).serializeArray();
        let sideMenuGroup = {};
        let i;
        for(i = 0; i < requestData.length; i++){
            //如果存在相同的属性，则该属性值是数组类型。
            if(sideMenuGroup[requestData[i].name]){
                sideMenuGroup[requestData[i].name] += ',' + requestData[i].value;
            }else{
                sideMenuGroup[requestData[i].name] = requestData[i].value;
            }
        }
        $.ajax({
            url: options.url,
            type: options.type,
            data: JSON.stringify(sideMenuGroup),
            contentType: "application/json;charset=UTF-8",
            beforeSend: function(){
                $.messager.progress({
                    title: '提示信息',
                    msg: '请稍候......'
                });
            },
            complete: function(){
                $.messager.progress('close');
            },
            success:function (data) {
                console.log(data);
                if(data.code == 200){
                    //清空表单
                    $(options.formId).form('clear');

                    $('#addModal').window('close');
                    $('#editModal').window('close');
                    $('#tt').treegrid('reload');
                    $.messager.show({
                        title:'提示信息',
                        msg:'操作成功！',
                        timeout:3000,
                        showType:'slide'
                    });
                }
            }
        });
    }else{
        $.messager.alert('提示信息','存在校验项未通过！',"warning");
    }
}

/**
 * 清空
 * @param urlPrefix
 */
function emptyRequest(url) {
    $.ajax({
        url: url,
        type: 'DELETE',
        beforeSend: function(){
            $.messager.progress({
                title: '提示信息',
                msg: '请稍候......'
            });
        },
        complete: function(){
            $.messager.progress('close');
        },
        success:function (data) {
            console.log(data);
            if(data.code == 200){
                $('#addModal').window('close');
                $('#editModal').window('close');
                $('#tt').treegrid('reload');
                $.messager.show({
                    title:'提示信息',
                    msg:'操作成功！',
                    timeout:3000,
                    showType:'slide'
                });
            }
        }
    });
}

function deleteRequest(urlPrefix){
    if(!urlPrefix.endsWith("/")){
        urlPrefix += '/';
    }
    //获取选中的所有行数据
    let rows = $('#tt').datagrid('getSelections');
    if (rows.length <= 0){
        $.messager.alert('提示信息','请至少选择一条数据！','error');
    }else{
        $.messager.confirm('提示信息', '你确认删除吗？', function(r){
            if (r){
                let ids = [];
                for(let i = 0; i < rows.length; i++){
                    ids.push(rows[i].id);
                }
                $.ajax({
                    url: urlPrefix + ids.join(','),
                    type: 'DELETE',
                    beforeSend: function(){
                        $.messager.progress({
                            title: '提示信息',
                            msg: '请稍候......'
                        });
                    },
                    complete: function(){
                        $.messager.progress('close');
                    },
                    success:function (data) {
                        console.log(data);
                        if(data.code == 200){
                            $('#addModal').window('close');
                            $('#editModal').window('close');
                            $('#tt').treegrid('reload');
                            $.messager.show({
                                title:'提示信息',
                                msg:'操作成功！',
                                timeout:3000,
                                showType:'slide'
                            });
                        }
                    }
                });
            }
        });
    }
}

function deleteRequestByUserId(urlPrefix){
    if(!urlPrefix.endsWith("/")){
        urlPrefix += '/';
    }
    //获取选中的所有行数据
    let rows = $('#tt').datagrid('getSelections');
    if (rows.length <= 0){
        $.messager.alert('提示信息','请至少选择一条数据！','error');
    }else{
        $.messager.confirm('提示信息', '你确认删除吗？', function(r){
            if (r){
                let ids = [];
                for(let i = 0; i < rows.length; i++){
                    ids.push(rows[i].userId);
                }
                $.ajax({
                    url: urlPrefix + ids.join(','),
                    type: 'DELETE',
                    beforeSend: function(){
                        $.messager.progress({
                            title: '提示信息',
                            msg: '请稍候......'
                        });
                    },
                    complete: function(){
                        $.messager.progress('close');
                    },
                    success:function (data) {
                        console.log(data);
                        if(data.code == 200){
                            $('#addModal').window('close');
                            $('#editModal').window('close');
                            $('#tt').treegrid('reload');
                            $.messager.show({
                                title:'提示信息',
                                msg:'操作成功！',
                                timeout:3000,
                                showType:'slide'
                            });
                        }
                    }
                });
            }
        });
    }
}

//自定义EasyUI校验规则
$.extend($.fn.validatebox.defaults.rules, {
    CHS: {
        validator: function (value, param) {
            return /^[\u0391-\uFFE5]+$/.test(value);
        },
        message: '请输入汉字'
    },
    english : {// 验证英语
        validator : function(value) {
            return /^[A-Za-z]+$/i.test(value);
        },
        message : '请输入英文'
    },
    ip : {// 验证IP地址
        validator : function(value) {
            return /\d+\.\d+\.\d+\.\d+/.test(value);
        },
        message : 'IP地址格式不正确'
    },
    ZIP: {
        validator: function (value, param) {
            return /^[0-9]\d{5}$/.test(value);
        },
        message: '邮政编码不存在'
    },
    QQ: {
        validator: function (value, param) {
            //QQ号正则，5至11位
            return /^[1-9]\d{4,10}$/.test(value);
        },
        message: 'QQ号码不正确'
    },
    mobile: {
        validator: function (value, param) {
            return /^(?:13\d|15\d|18\d)-?\d{5}(\d{3}|\*{3})$/.test(value);
        },
        message: '手机号码不正确'
    },
    tel:{
        validator:function(value,param){
            return /^(\d{3}-|\d{4}-)?(\d{8}|\d{7})?(-\d{1,6})?$/.test(value);
        },
        message:'电话号码不正确'
    },
    mobileAndTel: {
        validator: function (value, param) {
            return /(^([0\+]\d{2,3})\d{3,4}\-\d{3,8}$)|(^([0\+]\d{2,3})\d{3,4}\d{3,8}$)|(^([0\+]\d{2,3}){0,1}13\d{9}$)|(^\d{3,4}\d{3,8}$)|(^\d{3,4}\-\d{3,8}$)/.test(value);
        },
        message: '请正确输入电话号码'
    },
    number: {
        validator: function (value, param) {
            return /^[0-9]+.?[0-9]*$/.test(value);
        },
        message: '请输入数字'
    },
    money:{
        validator: function (value, param) {
            return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/).test(value);
        },
        message:'请输入正确的金额'

    },
    mone:{
        validator: function (value, param) {
            return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/).test(value);
        },
        message:'请输入整数或小数'

    },
    integer:{
        validator:function(value,param){
            return /^[+]?[1-9]\d*$/.test(value);
        },
        message: '请输入最小为1的整数'
    },
    integ:{
        validator:function(value,param){
            return /^[+]?[0-9]\d*$/.test(value);
        },
        message: '请输入整数'
    },
    range:{
        validator:function(value,param){
            if(/^[1-9]\d*$/.test(value)){
                return value >= param[0] && value <= param[1]
            }else{
                return false;
            }
        },
        message:'输入的数字在{0}到{1}之间'
    },
    minLength:{
        validator:function(value,param){
            return value.length >=param[0]
        },
        message:'至少输入{0}个字'
    },
    maxLength:{
        validator:function(value,param){
            return value.length<=param[0]
        },
        message:'最多{0}个字'
    },
    //select即选择框的验证
    selectValid:{
        validator:function(value,param){
            console.log('selectValid' + value + '-' + param[0]);
            if(value == param[0]){
                return false;
            }else{
                return true ;
            }
        },
        message:'请选择'
    },
    idCode:{
        validator:function(value,param){
            return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value);
        },
        message: '请输入正确的身份证号'
    },
    loginName: {
        validator: function (value, param) {
            return /^[\u0391-\uFFE5\w]+$/.test(value);
        },
        message: '登录名称只允许汉字、英文字母、数字及下划线。'
    },
    equalTo: {
        validator: function (value, param) {
            return value == $(param[0]).val();
        },
        message: '两次输入的字符不一至'
    },
    englishOrNum : {// 只能输入英文和数字
        validator : function(value) {
            return /^[a-zA-Z0-9_ ]{1,}$/.test(value);
        },
        message : '请输入英文、数字、下划线或者空格'
    },
    xiaoshu:{
        validator : function(value){
            return /^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/.test(value);
        },
        message : '最多保留两位小数！'
    },
    ddPrice:{
        validator:function(value,param){
            if(/^[1-9]\d*$/.test(value)){
                return value >= param[0] && value <= param[1];
            }else{
                return false;
            }
        },
        message:'请输入1到100之间正整数'
    },
    jretailUpperLimit:{
        validator:function(value,param){
            if(/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)){
                return parseFloat(value) > parseFloat(param[0]) && parseFloat(value) <= parseFloat(param[1]);
            }else{
                return false;
            }
        },
        message:'请输入0到100之间的最多俩位小数的数字'
    },
    rateCheck:{
        validator:function(value,param){
            if(/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)){
                return parseFloat(value) > parseFloat(param[0]) && parseFloat(value) <= parseFloat(param[1]);
            }else{
                return false;
            }
        },
        message:'请输入0到1000之间的最多俩位小数的数字'
    },
    wx:{
        validator:function(value, param){
            //微信号正则，6至20位，以字母开头，字母，数字，减号，下划线
            return /^[a-zA-Z]([-_a-zA-Z0-9]{5,19})+$/.test(value);
        },
        message: '微信号不正确'
    },
    identityCard: {
        validator:function(value, param){
            //身份证号（18位）正则
            return /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/.test(value);
        },
        message: '身份证号不正确'
    },
    username: {
        validator:function(value, param){
            //用户名正则，4到16位（字母，数字，下划线，减号）
            return /^[a-zA-Z0-9_-]{4,16}$/.test(value);
        },
        message: '用户名格式不正确，4到16位（字母，数字，下划线，减号）'
    },
    color: {
        validator:function(value, param) {
            //十六进制颜色正则
            return /^#?([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$/.test(value);
        },
        message: '颜色格式不正确'
    }
});