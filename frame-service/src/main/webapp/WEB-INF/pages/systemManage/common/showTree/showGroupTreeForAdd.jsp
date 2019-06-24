<%--
  Created by IntelliJ IDEA.
  User: along
  Date: 2015/6/7
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="inputDiv">
    <label class="inputLabel">选择所属分组：</label>

    <div class="inputShowDiv" id="add_show_group_div">
        <input type="text" id="add_groupName" name="add_groupName" class="form-control" placeholder="点击我" value=""
               onclick="showGroupTree();" style="display: inline-block; width: 100%;" readonly>

        <div id="warning_add_group" class="has-error">
            <div id="warning_add_group_text" class="control-label"></div>
        </div>
        <div class="box box col-md-0.5" id="add_group_menu">
            <div class="box-inner showTreeBox">
                <div id="loading" class="center" name="loading_add_group">
                    Loading...
                    <div class="center"></div>
                </div>
                <div class="showZTree">
                    <ul id="add_group_tree" class="ztree"></ul>
                </div>
            </div>
        </div>
        <input type="hidden" id="add_groupId" value=""/>
    </div>
</div>