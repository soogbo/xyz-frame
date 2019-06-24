/**
 * Created by gary on 15-9-16.
 */

(function () {
    window.originalConfirm = confirm;
    window.originalAlert = alert;
    window.eventDefault = false;
    var confirmDefaults = {
        //                id: 'noty_confirm',
        text: '确定操作吗?',
        type: 'confirm',
        dismissQueue: true,
        layout: 'center',
        timeout: false,
        modal: true,
        speed: 100,
        ok: $.noop,
        cancel: $.noop,
        onShown: function () {
            var $noty = $(this);
            $noty.trigger('focus');
            $(document).on('keyup.noty', function(ev){
                //$nody存在,且键盘按键为回车或空格
                if($noty.parent().length && ev.which === 13 || ev.which == 32) {
                    ev.preventDefault();
                    $noty.find('.btn-default-button').trigger('click');
                    console.log(ev.which);
                    return false;
                }
            }).on('keydown.noty', function(ev){
                //$nody存在,且键盘按键为回车或空格
                if($noty.parent().length && ev.which === 13 || ev.which == 32) {
                    ev.preventDefault();
                    return false;
                }
            });
        },
        onClosed: function () {
            $(document).off('keyup.noty keydown.noty');
        }
    };
    var alertDefaults = {
        text: '操作成功',
        layout: 'center',
        type: 'alert',
        timeout: 1000,
        animateOpen: {
            opacity: 'show'
        }
    };
    window.confirm = function (options) {
        var opts = $.extend({}, confirmDefaults, options);
        if (eventDefault) {
            var ret = originalConfirm(opts.text);
            ret ? opts.ok() : opts.cancel();
        } else {
            noty($.extend(opts, {
                buttons: [
                    {
                        type: 'btn btn-primary btn-sm btn-default-button',
                        text: '确定',
                        click: function ($noty) {
                            $noty.close();
                            opts.ok();
                        }
                    },
                    {
                        type: 'btn btn-danger btn-sm',
                        text: '取消',
                        click: function ($noty) {
                            $noty.close();
                            opts.cancel();
                        }
                    }
                ]
            }));
        }
    };


    window.n_alert = window.alert = function (text, options) {
        if (eventDefault) {
            originalAlert(text);
        } else {
            var opts = $.extend({}, alertDefaults, options);
            opts.text = text;
            noty(opts);
        }
    }


})();
