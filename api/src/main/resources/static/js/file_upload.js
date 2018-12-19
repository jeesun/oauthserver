/**
 *
 * User: simon
 * Date: 2018/06/06
 * Time: 14:45
 **/

'use strict';
//必须加这段代码，不然无法上传
/*var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
});*/

function initFileUpload(id, inputName){
    let uploader = $(id);
    uploader.fileupload({
        url: "/fileUploads/upload",
        dataType: 'json',
        type: "post",
        multipart: true,
        acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
        maxFileSize: 1000 * 1024 * 1024,
        maxNumberOfFiles: 50,
        change: function (e, data){
            $(id + '-wrapper .preview').empty();
            $(id + '-wrapper .progress-bar').css('width', '0%');
            $(id + '-wrapper .proportion').html('');
            $(id + '-wrapper input[name="imageUrl"]').remove();
        },
        done: function (e, data) {
            let result = data.result;
            //done方法就是上传完毕的回调函数，其他回调函数可以自行查看api
            //注意data要和jquery的ajax的data参数区分，这个对象包含了整个请求信息
            //返回的数据在data.result中，这里dataType中设置的返回的数据类型为json
            if(200 == result.code) {
                // 上传成功：
                for(let i = 0; i < result.data.length; i++){
                    $(id + '-wrapper .preview').append('<span><img class="image-thumb" src="'+result.data[i] + '" width="30px"><button  class=\'easyui-linkbutton delete_file\' type=\'button\'>删除</button><input type="hidden" name="' + inputName + '" value="' + result.data[i] + '"></span>');
                }
                //重新渲染
                $(".easyui-linkbutton").linkbutton();
                $(".easyui-numberbox").numberbox();
            } else {
                // 上传失败：
                $(id + '-wrapper .upstatus').append("<div style='color:red;'>"+result.msg+"</div>");
            }
        },messages: {
            maxFileSize: '文件大小超过限制',
            acceptFileTypes: '文件格式不支持'
        },progressall: function (e, data) {
            let progress = parseInt(data.loaded / data.total * 100, 10);
            $(id + '-wrapper .progress-bar').css("width", progress + "%");
            $(id + '-wrapper .proportion').html("上传总进度："+progress+"%");
        },processfail: function (e, data) {
            let currentFile = data.files[data.index];
            if (data.files.error && currentFile.error) {
                alert(currentFile.error);
            }
        }
    });

    $(document).on('click', '.delete_file', function () {
        console.log("clicked");
        $(this).parent().remove();
        if (!$(id + '-wrapper .preview').html()) {
            $(id + '-wrapper .progress-bar').css('width', '0%');
            $(id + '-wrapper .proportion').html('');
        }
        //$('input[name="imageUrl"]').remove();
    });
}

function imgPreview(id, inputName, imgUrl) {
    $('#edit_head_photo' + '-wrapper .preview').empty();
    if(imgUrl){
        $('#edit_head_photo' + '-wrapper .preview').append('<span><img class="image-thumb" src="' + imgUrl + '" width="30px"><button  class=\'easyui-linkbutton delete_file\' type=\'button\'>删除</button><input type="hidden" name="' + inputName + '" value="' + imgUrl + '"></span>');
    }
}