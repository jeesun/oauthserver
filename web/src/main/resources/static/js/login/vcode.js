var code = "";

function validate(value) {
    let result = (value.toUpperCase() === code.toUpperCase());
    /*if(!result){
        $("#codeimg").click();//主动触发单击事件，更换验证码
    }*/
    return result;
}
$().ready(function() {
    //将函数返回值赋给code
    code = createCode();

    //点击canvas图片更换验证码
    $("#codeimg").click(function () {
        code = createCode();
    });

    /*随机字符函数*/
    function rand(){
        //去掉i,I,l,o,O等易混淆字母
        var str="abcdefghjkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ0123456789";
        //将字符串分隔为数组
        var arr=str.split("");
        //随机字符在[0,56]之间
        var ranNum=Math.floor(Math.random()*57);
        var captcha=arr[ranNum];
        return captcha;
    }

    /*随机干扰线条函数*/
    function drawline(canvas, context) {
        //若省略beginPath，则每点击一次验证码会累积干扰线的条数
        context.beginPath();
        //起点与终点在canvas宽高内随机
        context.moveTo(Math.floor(Math.random() * canvas.width), Math.floor(Math.random() * canvas.height));
        context.lineTo(Math.floor(Math.random() * canvas.width), Math.floor(Math.random() * canvas.height));
        context.lineWidth = 1;
        context.strokeStyle = '#275DB3';
        context.stroke();
    }

    /*生成验证码*/
    function createCode(){
        //每次生成code先将其清空防止叠加
        code = "";
        var canvas = document.getElementById("codeimg");
        if(!canvas){
            return null;
        }
        var context = canvas.getContext("2d");

        //清空画布
        context.clearRect(0, 0, canvas.width, canvas.height);

        context.strokeStyle = "#FFF";
        context.strokeRect(0, 0, canvas.width, canvas.height);

        //生成干扰线，数量随意
        for (var i = 0; i < 10; i++) {
            drawline(canvas, context);
        }

        //循环生成4位验证码
        for (var k = 0; k < 4; k++) {
            context.font='76px Arial';
            //将初始状态保存
            context.save();
            //获得-1到1的随机数
            var rA = 1-Math.random()*2;
            //获取随机倾斜角
            var angle = rA / 8 ;
            var ranNum = rand();
            //旋转生成的随机字符
            context.rotate(angle);
            //把rand()生成的随机数文本依次填充到canvas中，注意x坐标
            context.fillText(ranNum,20+45*k,100);
            //恢复初始状态，以便下一次循环
            context.restore();
            code += ranNum;
        }
        //返回生成的验证码字符串
        return code;
    }
});