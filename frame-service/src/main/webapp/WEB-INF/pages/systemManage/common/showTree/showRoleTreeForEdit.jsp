<%--
  Created by IntelliJ IDEA.
  User: along
  Date: 2015/6/7
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="inputDiv">
    <label class="inputLabel">选择角色：</label>

    <div class="inputShowDiv" id="modify_show_role_div">
        <input type="text" id="modify_roleName" name="modify_roleName" class="form-control showTreeDiv"
               placeholder="点击我" value="" onclick="showRoleTree();" readonly>

        <div id="warning_modify_role" class="has-error">
            <div id="warning_modify_role_text" class="control-label"></div>
        </div>
        <div class="box box col-md-0.5" id="modify_role_menu">
            <div class="box-inner showTreeBox">
                <div id="loading" class="center" name="loading_modify_role">
                    Loading...
                    <div class="center"></div>
                </div>
                <ul id="modify_role_tree" class="ztree"></ul>
            </div>
        </div>
        <input type="hidden" id="modify_roleIds" value=""/>
    </div>
</div>