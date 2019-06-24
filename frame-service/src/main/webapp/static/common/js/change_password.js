/**
 * Created by gary on 15-9-17.
 */
(function () {
    var ChangePassword = window.ChangePassword = {
        html: function () {
            return '<div class="">' +
                '    <form id="change-pw-form" class="form-horizontal">' +
                '        <div class="form-group">' +
                '            <label class="col-md-3 control-label">旧密码：</label>' +
                '            <div class="col-md-8">' +
                '                <input type="password" name="oldPw" class="form-control" placeholder="请输入老密码"/>' +
                '            </div>' +
                '        </div>' +
                '        <div class="form-group">' +
                '            <label class="col-md-3 control-label">新密码：</label>' +
                '            <div class="col-md-8">' +
                '                <input type="password" name="newPw" class="form-control" placeholder="请输入新密码"/>' +
                '            </div>' +
                '        </div>' +
                '        <div class="form-group">' +
                '            <label class="col-md-3 control-label">确认密码：</label>' +
                '            <div class="col-md-8">' +
                '                <input type="password" name="confirmPw" class="form-control" placeholder="请再次输入新密码"/>' +
                '            </div>' +
                '        </div>' +
                '    </form>' +
                '</div>';
        },
        check: function () {
            ChangePassword.$form = $('#change-pw-form');
            var oldPw = ChangePassword.$form.find('[name=oldPw]').val();
            var newPw = ChangePassword.$form.find('[name=newPw]').val();
            var confirmPw = ChangePassword.$form.find('[name=confirmPw]').val();
            var error = null;
            if (!oldPw) {
                error = '旧密码必填！';
            } else if (oldPw === newPw) {
                error = '新旧密码不能相同！';
            } else if (!newPw || newPw !== confirmPw) {
                error = '新密码不能为空且新密码必须与确认密码一致！';
            }
            if (error) {
                alert(error, {type: 'error'});
                return false;
            }
            return true;
        },
        showDialog: function () {
            BootstrapDialog.show({
                title: '修改密码',
                type: 'type-default',
                message: ChangePassword.html(),
                buttons: [
                    {
                        cssClass: 'btn-default',
                        label: '取消',
                        action: function (dialogRef) {
                            dialogRef.close();
                        }
                    },
                    {
                        cssClass: 'btn-success',
                        label: '保存',
                        action: function (dialogRef) {
                            if (ChangePassword.check()) {
                                confirm({
                                    text: '确定修改密码么？',
                                    ok: function () {
                                        $.postAjax({
                                            url: 'admin/changePassword.do',
                                            data: ChangePassword.$form.serializeArray(),
                                            handleSuccess: function (res) {
                                                alert('修改成功！', {type: 'success'});
                                                dialogRef.close();
                                            }
                                        })
                                    }
                                });
                            }
                        }
                    }

                ]
            });
        }
    }
})($);
