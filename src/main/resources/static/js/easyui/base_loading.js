/**
 *
 * User: simon
 * Date: 2018/10/31
 * Time: 0:00
 **/
//获取浏览器页面可见高度和宽度
var _PageHeight = document.documentElement.clientHeight,
    _PageWidth = document.documentElement.clientWidth;
//计算loading框距离顶部和左部的距离（loading框的宽度为215px，高度为61px）
var _LoadingTop = _PageHeight > 61 ? (_PageHeight - 61) / 2 : 0,
    _LoadingLeft = _PageWidth > 215 ? (_PageWidth - 215) / 2 : 0;
//加载gif地址
var Loadimagerul = "/webjars/easyui/1.6.6/themes/material-teal/images/loading.gif";
//在页面未加载完毕之前显示的loading Html自定义内容
var _LoadingHtml = '<div id="loadingDiv" style="position:absolute;left:0;width:100%;height:' + _PageHeight + 'px;top:0;background:#f3f8ff;opacity:1;filter:alpha(opacity=80);z-index:10000;"><div style="position: absolute; cursor1: wait; left: ' + _LoadingLeft + 'px; top:' + _LoadingTop + 'px; width:215px;; height: 57px; line-height: 57px; padding-left: 50px; padding-right: 5px; background: #fff url(' + Loadimagerul + ') no-repeat scroll 5px 12px; border: 2px solid #95B8E7; color: #696969; font-family:\'Microsoft YaHei\';">玩命加载中...</div></div>';
//呈现loading效果
document.write(_LoadingHtml);
//监听加载状态改变
document.onreadystatechange = completeLoading;

//加载状态为complete时移除loading效果
function completeLoading() {
    if (document.readyState == "complete") {
        var loadingMask = document.getElementById('loadingDiv');
        loadingMask.parentNode.removeChild(loadingMask);
    }
}