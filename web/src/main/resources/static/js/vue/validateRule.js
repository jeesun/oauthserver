var checkAge = (rule, value, callback) => {
    if (!value) {
        //return callback(new Error('年龄不能为空'));
        callback();
    }
    setTimeout(() => {
        if (!Number.isInteger(value)) {
            callback(new Error('请输入数字值'));
        } else {
            if (value < 18) {
                callback(new Error('必须年满18岁'));
            } else {
                callback();
            }
        }
    }, 1000);
};

var checkPhone = (rule, value, callback) => {
    const phoneReg = /^1[3|4|5|7|8][0-9]{9}$/
    if (!value) {
        //return callback(new Error('电话号码不能为空'))
        callback();
    }
    setTimeout(() => {
        // Number.isInteger是es6验证数字是否为整数的方法,但是我实际用的时候输入的数字总是识别成字符串
        // 所以我就在前面加了一个+实现隐式转换

        if (!Number.isInteger(+value)) {
            callback(new Error('请输入数字值'))
        } else {
            if (phoneReg.test(value)) {
                callback()
            } else {
                callback(new Error('电话号码格式不正确'))
            }
        }
    }, 100)
};

var checkEmail = (rule, value, callback) => {
    const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
    if (!value) {
        //return callback(new Error('邮箱不能为空'));
        callback();
    }
    setTimeout(() => {
        if (mailReg.test(value)) {
            callback()
        } else {
            callback(new Error('请输入正确的邮箱格式'));
        }
    }, 100)
};