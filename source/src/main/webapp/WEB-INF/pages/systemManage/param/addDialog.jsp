<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/showAddDialog/addDialogHeader.jsp" %>
<div id = "paramCodeDiv">
	<div class="inputDiv">
	    <label class="inputLabel">请输入分类码：</label>
	    <input id="add_classCode" type="text" class="form-control inputInput" placeholder="分类码"/>
	
	    <div id="warning_add_classCode" class="has-error">
	        <div id="warning_add_classCode_text" class="control-label"></div>
	    </div>
	</div>
	<div class="inputDiv">
	    <label class="inputLabel">请输入分类名：</label>
	    <input id="add_className" type="text" class="form-control inputInput" placeholder="分类名"/>
	
	    <div id="warning_add_className" class="has-error">
	        <div id="warning_add_className_text" class="control-label"></div>
	    </div>
	</div>
</div>
<div id = "paramParamDiv">
    <div class="inputDiv">
        <label class="inputLabel">请选择分类：</label>
            <select id="add_paramClass_for_param"  class="form-control inputInput" placeholder="分类">
                    <option value="" title="请选择">请选择</option>
            </select>
    
        <div id="warning_add_paramClass_for_param" class="has-error">
            <div id="warning_add_paramClass_for_param_text" class="control-label"></div>
        </div>
    </div>
    <div class="inputDiv">
	    <label class="inputLabel">请输入参数码：</label>
	    <input id="add_paramCode" type="text" class="form-control inputInput" placeholder="参数码"/>
	
	    <div id="warning_add_paramCode" class="has-error">
	        <div id="warning_add_paramCode_text" class="control-label"></div>
	    </div>
	</div>
	<div class="inputDiv">
	    <label class="inputLabel">请输入参数名：</label>
	    <input id="add_paramName" type="text" class="form-control inputInput" placeholder="参数名"/>
	
	    <div id="warning_add_paramName" class="has-error">
	        <div id="warning_add_paramName_text" class="control-label"></div>
	    </div>
	</div>
	<div class="inputDiv">
	    <label class="inputLabel">请输入参数值：</label>
	    <input id="add_paramValue" type="text" class="form-control inputInput" placeholder="参数值"/>
	
	    <div id="warning_add_paramValue" class="has-error">
	        <div id="warning_add_paramValue_text" class="control-label"></div>
	    </div>
	</div>
	<div class="inputDiv">
	    <label class="inputLabel">请输入扩展字段1：</label>
	    <input id="add_paramExtone" type="text" class="form-control inputInput" placeholder="扩展字段1"/>
	
	    <div id="warning_add_paramExtone" class="has-error">
	        <div id="warning_add_paramExtone_text" class="control-label"></div>
	    </div>
	</div>
	<div class="inputDiv">
	    <label class="inputLabel">请输入扩展字段2：</label>
	    <input id="add_paramExttwo" type="text" class="form-control inputInput" placeholder="扩展字段2"/>
	
	    <div id="warning_add_paramExttwo" class="has-error">
	        <div id="warning_add_paramExttwo_text" class="control-label"></div>
	    </div>
	</div>
	<div class="inputDiv">
	    <label class="inputLabel">请输入扩展字段3：</label>
	    <input id="add_paramExtthree" type="text" class="form-control inputInput" placeholder="扩展字段3"/>
	
	    <div id="warning_add_paramExtthree" class="has-error">
	        <div id="warning_add_paramExtthree_text" class="control-label"></div>
	    </div>
	</div>
	
</div>
<%@include file="../common/showAddDialog/addDialogFooter.jsp" %>