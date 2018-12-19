/**
 *
 * User: simon
 * Date: 2018/10/31
 * Time: 0:00
 **/
//获取浏览器页面可见高度和宽度
let _PageHeight = document.documentElement.clientHeight,
    _PageWidth = document.documentElement.clientWidth;
//计算loading框距离顶部和左部的距离（loading框的宽度为215px，高度为61px）
let _LoadingTop = _PageHeight > 61 ? (_PageHeight - 61) / 2 : 0,
    _LoadingLeft = _PageWidth > 61 ? (_PageWidth - 61) / 2 : 0;
//加载gif地址
let Loadimagerul = "/img/loading.gif";
//在页面未加载完毕之前显示的loading Html自定义内容
let _LoadingHtml = '<div id="loadingDiv" style="position:absolute;left:0;width:100%;height:' + _PageHeight + 'px;top:0;background:#f3f8ff;opacity:1;filter:alpha(opacity=80);z-index:10000;"><div style="position: absolute; cursor1: wait; left: ' + _LoadingLeft + 'px; top:' + _LoadingTop + 'px; width:61px;; height: 57px; line-height: 57px; padding-left: 50px; padding-right: 5px; background: #f3f8ff url(' + Loadimagerul + ') no-repeat scroll 5px 12px; color: #696969; font-family:\'Microsoft YaHei\';"></div></div>';
//呈现loading效果
document.write(_LoadingHtml);
//监听加载状态改变
document.onreadystatechange = completeLoading;

//加载状态为complete时移除loading效果
function completeLoading() {
    if (document.readyState == "complete") {
        let loadingMask = document.getElementById('loadingDiv');
        loadingMask.parentNode.removeChild(loadingMask);
    }
}