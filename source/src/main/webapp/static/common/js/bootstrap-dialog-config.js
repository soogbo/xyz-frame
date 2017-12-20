/**
 * Created by gary on 15-11-4.
 */

$(function () {
    //覆盖默认的onshow方法，随后如果还需要在onshow方法里写逻辑，可以先通过
    //BootstrapDialog.defaultOptions.onshow(dialogRef);调用到这段代码
    $.extend(BootstrapDialog.defaultOptions, {
        type: 'type-default',
        nl2br: false,
        onshow: function (dialogRef) {
            if (dialogRef.options.center === false) {
                return;
            }

            var clonedModal = dialogRef.$modal.clone().appendTo('body').css({
                'visibility': 'hidden',
                'display': 'block'
            });

            var clonedModalHeight = clonedModal.find('.modal-dialog').height();
            clonedModal.remove();
            var $dialog = dialogRef.$modalDialog;

            _centerModal($dialog, clonedModalHeight);
        }
    });

    $(window).on("resize", function () {
        $('.modal-dialog:not(.not-center)').each(function () {
            _centerModal($(this));
        });
    });

    /**
     * 私有方法， 让dialog居中， 先供
     * verticallyAlignBootStrapModal 和 bootstrapModal使用
     * @param $dialog
     * @param clonedModalHeight
     * @private
     */
    function _centerModal($dialog, clonedModalHeight) {
        var offset = ($(window).height() - (clonedModalHeight || $dialog.height())) / 2,
            bottomMargin = parseInt($dialog.css('marginBottom') || 0, 10);

        if (offset < bottomMargin) offset = bottomMargin;
        $dialog.css("margin-top", offset);
    }

    BootstrapDialog.customOptions= {
        type: 'type-default',
        buttons:[
            {
                label: '确定',
                cssClass: 'btn btn-success',
                action: function(dialogDef) {
                    dialogDef.close();
                }
            },
            {
                label: '取消',
                cssClass: 'btn btn-warning',
                action: function(dialogDef) {
                    dialogDef.close();
                }
            }

        ]
    };

});