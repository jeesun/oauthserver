/**
 *
 * User: simon
 * Date: 2018/06/07
 * Time: 13:01
 **/
function setTokenInHeader() {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}

//左侧菜单栏选中事件初始化
$('.sidebar-menu li').each(function () {
    $(this).removeClass("active");
});
var pathValue = window.location.href;
var pathName = pathValue.substring(pathValue.lastIndexOf('/') + 1);
$('.sidebar-menu li').each(function () {
    if(pathName == '' && $(this).children('a').attr('href').indexOf('index') != -1){
        $(this).addClass("active");
        $(this).parents(".treeview").addClass("active");
    }else if(pathName != '' && $(this).children('a').attr('href') && $(this).children('a').attr('href').indexOf(pathName) != -1){
        $(this).addClass("active");
        $(this).parents(".treeview").addClass("active");
    }
});

/*var theme = $.cookie('theme');
console.log(theme);
if(!theme || null == theme){
    theme = 'blue';
    $.cookie('theme', theme);
}
changeTheme(theme);*/

function changeTheme(themeName) {
    /*$('body').removeClass('skin-blue skin-blue-light skin-green skin-green-light skin-red skin-red-light skin-black skin-black-light skin-purple skin-purple-light skin-yellow skin-yellow-light');*/
    $('body').removeClass($.cookie('theme'));
    $('body').addClass(themeName);
    $.cookie('theme', themeName, {expires: 30});
}

function doRequest(options) {
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
            console.log('请稍候......');
        },
        complete: function(){
            console.log('close');
        },
        success:function (data) {
            console.log(data);
            if(data.code == 200){
                console.log('操作成功！');
            }
        }
    });
}