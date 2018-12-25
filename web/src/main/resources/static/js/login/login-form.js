//账号密码登录
$().ready(function() {
    //createCode();

    jQuery.validator.addMethod("password", function (value, element, param) {
        var rex = /^(?![A-Z]+$)(?![a-z]+$)(?!\d+$)(?![\W_]+$)\S{6,16}$/;
        return this.optional(element) || (rex.test(value));
    }, "必须包含数字、字母或特殊字符");

    jQuery.validator.addMethod("vericode", function (value, element, param) {
        return validate(value);
    }, "验证码错误");

    $("#loginForm").validate({
        rules:{
            username: {
                required: true,
                minlength: 1
            },
            password: {
                required: true,
                rangelength: [6,20],
                password: true
            },
            input_vericode: {
                required: true,
                minlength: 4,
                vericode: true
            }
        },
        errorPlacement: function(error, element) {
            error.appendTo(element.parent());
        },
        errorElement: "span"
    });
});