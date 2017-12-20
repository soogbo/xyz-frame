<%@ page contentType="text/html;charset=UTF-8" language="java"%>
	<div id="edit_top_password_div" class="modal fade" style="display: block;">
	</div>
	<div id="dialog_box" class="modal-dialog modal" style="margin-top: 15%;">
		<div class="modal-content">
			<div class="modal-header" style="border-bottom: 0px;">
				<button type="button" class="close" data-dismiss="modal"
					onClick="cancelTopPassowrd();">×</button>
			</div>
			<div class="modal-body" style="padding-top: 0px;">
                <div class="box-content">
                            <div class="row">
                                <div class="col-md-8" style="margin-left: 10%;">
                                    <div class="row" style="margin-top: 20px;">
                                        <div class="col-md-4" style="text-align: right;margin-top: 10px;">
                                            <label>原密码：</label>
                                        </div>
                                        <div class="col-md-8">
                                            <input id="oldTopPassword" class="form-control" onblur="checkPasswordTop(this)" type="password" value=""/>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <div class="row" id="oldPasswordTopInfo" style="color: red;display: none;margin-top: 10px;margin-left:20px;text-align: center;">
                                原密码不正确
                            </div>
                            <div class="row">
                                <div class="col-md-8" style="margin-left: 10%;">
                                    <div class="row" style="margin-top: 20px;">
                                        <div class="col-md-4" style="text-align: right;margin-top: 10px;">
                                            <label>新密码：</label>
                                        </div>
                                        <div class="col-md-8">
                                            <input id="updateTopPassword" class="form-control" type="password" value=""/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-8" style="margin-left: 10%;">
                                    <div class="row" style="margin-top: 20px;">
                                        <div class="col-md-4" style="text-align: right;margin-top: 10px;">
                                            <label>确认密码：</label>
                                        </div>
                                        <div class="col-md-8">
                                            <input id="updateTopPassword1" class="form-control" type="password" value=""/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row" id="passwordTopInfo" style="display: none;margin-left: 60px;color: red">
                                <div class="col-md-8" style="margin-left: 10%;">
                                    <div class="row" style="margin-top: 20px;">
                                        <div class="col-md-4" style="text-align: right;margin-top: 10px;">

                                        </div>
                                        <div class="col-md-8">
                                            <label>两次密码输入不一致</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <p name="loading_top_passowrd_text"></p>
                            <div id="loading" class="center" name="loading_top_password" style="display:none;">
                                <div class="center"></div>
                            </div>
                            <div class="modal-footer" style="border-top: 0px;margin-top: 10px" id="dialog_top_password_button">
                                <a href="javascript:void(0);" style="float: right;"
                                   onclick="javascript:updateCurrentAccountPassword();"
                                   class="btn btn-success" data-dismiss="modal">保存</a>
                                <a href="javascript:void(0);" style="float: right; margin-right: 30px"
                                   onclick="javascript:cancelTopPassowrd();"
                                   class="btn btn-info" data-dismiss="modal">取消</a>

                            </div>
                        </div>
                    </div>
		</div>
	</div>