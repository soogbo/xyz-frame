<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="add_dialog_div" style="display: none;">
    <div id="dialog_bg_div" class="modal fade" style="display: block;"></div>
    <div id="dialog_box" class="modal-dialog modal">
        <div class="modal-content">
            <div class="modal-header" style="border-bottom: 0px;">
                <button type="button" class="close" data-dismiss="modal" onClick="closeButtonClick();">×</button>
                <h3 id="showAddTitle" style="text-align: center;"></h3>
            </div>
            <div class="modal-body" style="padding-top: 0px;">
                <p id="dialog_content" name="loading_add_text"></p>

                <div id="loading" class="center" name="loading_add">
                    <div class="center"></div>
                </div>
                <div id="add_Info">