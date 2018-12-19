/*A jQuery plugin which add loading indicators into buttons
* By Minoli Perera
* MIT Licensed.
*/
(function ($) {
    $('.has-spinner').attr("disabled", false);
    $.fn.buttonLoader = function (action, message) {
        var self = $(this);
        if (action == 'loading') {
            if ($(self).attr("disabled") == "disabled") {
                return false;
            }
            $('.has-spinner').attr("disabled", true);
            $(self).attr('data-btn-text', $(self).text());
            var text = message;
            console.log($(self).attr('data-load-text'));
            if($(self).attr('data-load-text') != undefined && $(self).attr('data-load-text') != ""){
                text = $(self).attr('data-load-text');
            }
            $(self).html('<span class="spinner"><i class="fa fa-spinner fa-spin" title="button-loader"></i></span> '+text);
            $(self).addClass('active');
        }

        if (action == 'success') {
            $(self).html('<i class="fa fa-check" aria-hidden="true"></i> ' + message);
            $(self).removeClass('active');
            $('.has-spinner').attr("disabled", false);
            $('.has-spinner').removeClass('btn-default btn-primary');
            $('.has-spinner').addClass('btn-success');
        }

        if (action == 'error') {
            $(self).html('<i class="fa fa-close" aria-hidden="true"></i> ' + message);
            $(self).removeClass('active');
            $('.has-spinner').attr("disabled", false);
            $('.has-spinner').removeClass('btn-default btn-primary');
            $('.has-spinner').addClass('btn-danger');
        }
    }
})(jQuery);
