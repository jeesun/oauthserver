//关闭layer弹出层
function closeLayer() {
    let index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

Vue.prototype.$http = axios;

/**
 * 国际化
 * @param locale 固定值
 */
function international(locale) {
    if (locale === "en_US") {
        ELEMENT.locale(ELEMENT.lang.en);
    }else {
        ELEMENT.locale(ELEMENT.lang.zhCN);
    }
}

function isArray(obj) {
    return (typeof obj == 'object') && obj && obj.constructor === Array;
}

function isString(str){
    return (typeof str == 'string') && str.constructor === String;
}