$(function(){
    $(".switchMode").on({
        click:function(e){
            if($(this).attr("src").indexOf('qrcode-40x40.png') != -1){
                //$(this).attr("src", "img/pc-40x40.png");
                //window.location.href = "login?qrcode=true";
                var qrcode = $.cookie('qrcode');
                console.log("qrcode=" + qrcode);
                if(qrcode == 'false' || !qrcode){
                    console.log("准备执行initQrCode");
                    $.cookie('qrcode', true);
                    initQrCode();
                }
                $('#form-qrcode').css('display', 'inline');
                $('#form-normal').css('display', 'none');
            }else {
                //$(this).attr("src", "img/qrcode-40x40.png");
                //window.location.href = "login";
                if(qrcode == true || !qrcode){
                    $.cookie('qrcode', false);
                }
                $('#form-qrcode').css('display', 'none');
                $('#form-normal').css('display', 'inline');
            }
        }
    });
});