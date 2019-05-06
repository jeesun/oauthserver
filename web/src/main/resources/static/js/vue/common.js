//关闭layer弹出层
function closeLayer() {
    let index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

Vue.prototype.$http = axios;