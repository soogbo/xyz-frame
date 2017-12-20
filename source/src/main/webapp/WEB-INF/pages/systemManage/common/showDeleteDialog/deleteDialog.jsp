<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="delete_dialog_div" style="display: none;">
    <div id="dialog_bg_div" class="modal fade" style="display: block;"></div>
    <div id="dialog_box" class="modal-dialog modal">
        <div class="modal-content">
            <div class="modal-header" style="border-bottom: 0px;">
                <button type="button" class="close" data-dismiss="modal" onClick="closeButtonClick();">×</button>
                <h3 id="showDeleteTitle" style="text-align: center;"></h3>
            </div>
            <div class="modal-body" style="padding-top: 0px;">
                <p id="dialog_content" name="loading_delete_text"></p>

                <div id="loading" class="center" name="loading_delete">
                    <div class="center"></div>
                </div>
            </div>
            <div class="modal-footer" style="border-top: 0px;">
                <a href="javascript:void(0);" onclick="javascript:closeButtonClick();" class="btn btn-default"
                   data-dismiss="modal" id="close_button">关闭</a>
                <a href="javascript:void(0);" onclick="javascript:deleteButtonClick();" class="btn btn-primary"
                   data-dismiss="modal" id="delete_button">删除</a>
            </div>
        </div>
    </div>
</div>