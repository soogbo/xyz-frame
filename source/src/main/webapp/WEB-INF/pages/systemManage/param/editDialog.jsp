<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/showEditDialog/editDialogHeader.jsp" %>
<div id = "modifyParamCodeDiv">
	<div class="inputDiv">
	    <label class="inputLabel">请输入分类码：</label>
	        <input type="text" id="modify_classCode" name="modify_classCode" class="form-control inputInput" placeholder="分类码"/>
            <div id="warning_modify_classCode" class="has-error">
                <div id="warning_modify_classCode_text" class="control-label"></div>
            </div>
            	
	</div>
	
	<div class="inputDiv">
	    <label class="inputLabel">请输入分类名：</label>
	        <input type="text" id="modify_className" name="modify_className" class="form-control inputInput" placeholder="分类名"/>
		    <div id="warning_modify_className" class="has-error">
	            <div id="warning_modify_className_text" class="control-label"></div>
	        </div>
	</div>
</div>

<div id = "modifyParamParamDiv">
    <div class="inputDiv">
        <label class="inputLabel">请选择分类：</label>
            <select id="modify_paramClass_for_param"  class="form-control inputInput" placeholder="分类">
                    <option value="" title="请选择">请选择</option>
            </select>           
    
        <div id="warning_modify_paramClass_for_param" class="has-error">
            <div id="warning_modify_paramClass_for_param_text" class="control-label"></div>
        </div>
    </div>
    <div class="inputDiv">
        <label class="inputLabel">请输入参数码：</label>
        <input id="modify_paramCode" type="text" class="form-control inputInput" placeholder="参数码"/>
    
        <div id="warning_modify_paramCode" class="has-error">
            <div id="warning_modify_paramCode_text" class="control-label"></div>
        </div>
    </div>
    <div class="inputDiv">
        <label class="inputLabel">请输入参数名：</label>
        <input id="modify_paramName" type="text" class="form-control inputInput" placeholder="参数名"/>
    
        <div id="warning_modify_paramName" class="has-error">
            <div id="warning_modify_paramName_text" class="control-label"></div>
        </div>
    </div>
    <div class="inputDiv">
        <label class="inputLabel">请输入参数值：</label>
        <input id="modify_paramValue" type="text" class="form-control inputInput" placeholder="参数值"/>
    
        <div id="warning_modify_paramValue" class="has-error">
            <div id="warning_modify_paramValue_text" class="control-label"></div>
        </div>
    </div>
    <div class="inputDiv">
        <label class="inputLabel">请输入扩展字段1：</label>
        <input id="modify_paramExtone" type="text" class="form-control inputInput" placeholder="扩展字段1"/>
    
        <div id="warning_modify_paramExtone" class="has-error">
            <div id="warning_modify_paramExtone_text" class="control-label"></div>
        </div>
    </div>
    <div class="inputDiv">
        <label class="inputLabel">请输入扩展字段2：</label>
        <input id="modify_paramExttwo" type="text" class="form-control inputInput" placeholder="扩展字段2"/>
    
        <div id="warning_modify_paramExttwo" class="has-error">
            <div id="warning_modify_paramExttwo_text" class="control-label"></div>
        </div>
    </div>
    <div class="inputDiv">
        <label class="inputLabel">请输入扩展字段3：</label>
        <input id="modify_paramExtthree" type="text" class="form-control inputInput" placeholder="扩展字段3"/>
    
        <div id="warning_modify_paramExtthree" class="has-error">
            <div id="warning_modify_paramExtthree_text" class="control-label"></div>
        </div>
    </div>
    
</div>


<%@include file="../common/showEditDialog/editDialogFooter.jsp" %>