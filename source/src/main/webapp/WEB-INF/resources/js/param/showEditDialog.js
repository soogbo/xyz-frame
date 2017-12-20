var flag = null;//1:修改分类，2修改参数
var modCheckClassCode = true;
var modCheckClassName = true;
var modCheckParamCode = true;
var modCheckParamName = true;

$(function(){
    $("#modify_paramCode").on("change", function () {
    	var modify_paramCode = $("#modify_paramCode").val().trim();
    	
    	var flag = true;
    	for (var i = 0; i < paramParamCodeArray.length; i++) {
    		if(modify_paramCode == paramParamCodeArray[i].paramCode){
    			flag = false;//判断paramCode是否已存在
    		}
		}
    	if (modify_paramCode == currentSelectData.paramCode) {
    		flag = true;
		}
    	if (isEmptyObj(modify_paramCode)) {
    		document.getElementById("warning_modify_paramCode_text").innerHTML = '请输入参数码！';
            document.getElementById("warning_modify_paramCode").style.display = "block";
            modCheckParamCode = false;
        /*} else if (!flag) {
        	document.getElementById("warning_modify_paramCode_text").innerHTML = '参数码已存在！';
            document.getElementById("warning_modify_paramCode").style.display = "block";
            modCheckParamCode = false;*/
        } else {
    		document.getElementById("warning_modify_paramCode_text").innerHTML = '';
    		document.getElementById("warning_modify_paramCode").style.display = "none";
    		modCheckParamCode = true;
        }
    });
    $("#modify_paramName").on("change", function () {
    	var modify_paramName = $("#modify_paramName").val().trim();
    	if (isEmptyObj(modify_paramName)) {
    		document.getElementById("warning_modify_paramName_text").innerHTML = '请输入分类名！';
    		document.getElementById("warning_modify_paramName").style.display = "block";
    		modCheckParamName = false;
    	} else {
    		document.getElementById("warning_modify_paramName_text").innerHTML = '';
    		document.getElementById("warning_modify_paramName").style.display = "none";
    		modCheckParamName = true;
    	}
    });
    
    $("#modify_classCode").on("change", function () {
    	var modify_classCode = $("#modify_classCode").val().trim();
    	
    	var flag = true;
    	for (var i = 0; i < paramClassCodeArray.length; i++) {
    		if(modify_classCode == paramClassCodeArray[i]){
    			flag = false;//判断classCode是否已存在
    		}
		}
    	if (modify_classCode == currentSelectData.classCode) {
    		flag = true;
		}
    	if (isEmptyObj(modify_classCode)) {
    		document.getElementById("warning_modify_classCode_text").innerHTML = '请输入分类码！';
    		document.getElementById("warning_modify_classCode").style.display = "block";
    		modCheckClassCode = false;
    	} else if (!flag) {
    		document.getElementById("warning_modify_classCode_text").innerHTML = '分类码已存在！';
    		document.getElementById("warning_modify_classCode").style.display = "block";
    		modCheckClassCode = false;
        } else {
    		document.getElementById("warning_modify_classCode_text").innerHTML = '';
    		document.getElementById("warning_modify_classCode").style.display = "none";
    		modCheckClassCode = true;
    	}
    });
    $("#modify_className").on("change", function () {
    	var modify_className = $("#modify_className").val().trim();
    	if (isEmptyObj(modify_className)) {
    		document.getElementById("warning_modify_className_text").innerHTML = '请输入参数名！';
    		document.getElementById("warning_modify_className").style.display = "block";
    		modCheckClassName = false;
    	} else {
    		document.getElementById("warning_modify_className_text").innerHTML = '';
    		document.getElementById("warning_modify_className").style.display = "none";
    		modCheckClassName = true;
    	}
    });
});

function showEditDialog(data,flag_) {
    var dataTemp = utf8to16(base64decode(data));
    var data_ = eval('(' + dataTemp + ')');
    currentSelectData = data_;
    is_showEditView = true;
    flag = flag_;
    if (flag == 1) {
    	document.getElementById("showEditTitle").innerHTML = '修改分类';
    	//隐藏参数div，显示分类div
    	document.getElementById("modifyParamCodeDiv").style.display = 'block';
    	document.getElementById("modifyParamParamDiv").style.display = 'none';
    	// 设置画面默认信息
        document.getElementById("modify_classCode").value = currentSelectData.classCode;
        document.getElementById("modify_className").value = currentSelectData.className;
	} else if (flag == 2) {
		document.getElementById("showEditTitle").innerHTML = '修改参数';
		//隐藏分类div，显示参数div
    	document.getElementById("modifyParamParamDiv").style.display = 'block';
    	document.getElementById("modifyParamCodeDiv").style.display = 'none';
		// 设置画面默认信息
        document.getElementById("modify_paramCode").value = currentSelectData.paramCode;
        document.getElementById("modify_paramName").value = currentSelectData.paramName;
        document.getElementById("modify_paramValue").value = currentSelectData.paramValue||null;
        document.getElementById("modify_paramExtone").value = currentSelectData.paramExtone||null;
        document.getElementById("modify_paramExttwo").value = currentSelectData.paramExttwo||null;
        document.getElementById("modify_paramExtthree").value = currentSelectData.paramExtthree||null;
        if (currentSelectData.classId && currentSelectData.paramClass) {
        	document.getElementById("modify_paramClass_for_param").value = currentSelectData.classId;
		}else {
			document.getElementById("modify_paramClass_for_param").value = "";
		}
	}
// 判断是否正在更新
    if (document.getElementsByName("loading_modify")[0].style.display == '' || 'none') {

// 隐藏loading动画和清空提示内容
        document.getElementsByName("loading_modify_text")[0].innerHTML = '';
        document.getElementsByName("loading_modify")[0].style.display = 'none';
// 显示修改分类的画面和按钮
        document.getElementById("modify_Info").style.display = 'block';
        document.getElementById("modify_button").style.display = 'inline-block';
        document.getElementById("modify_dialog_div").style.display = 'block';
    }
}

function editButtonClick() {
	if (flag == 1) {
		var classCode = document.getElementById("modify_classCode").value.trim();
	    var className = document.getElementById("modify_className").value.trim();
	    
	    if (!modCheckClassCode) {
	    	document.getElementById("warning_modify_classCode_text").innerHTML = '请正确输入分类码！';
	        document.getElementById("warning_modify_classCode").style.display = 'block';
		}else {
			document.getElementById("warning_modify_classCode_text").innerHTML = '';
			document.getElementById("warning_modify_classCode").style.display = 'none';
		}
	    
	    if (!modCheckClassName) {
	    	document.getElementById("warning_modify_className_text").innerHTML = '请正确输入分类名！';
	    	document.getElementById("warning_modify_className").style.display = "block";
		}else {
			document.getElementById("warning_modify_className_text").innerHTML = '';
			document.getElementById("warning_modify_className").style.display = 'none';
		}
	    
	    if (modCheckClassCode && modCheckClassName) {
		    if (classCode != currentSelectData.classCode || className != currentSelectData.className) {
		        var paramClass = {
		            id: currentSelectData.id,
		            classCode: classCode,
		    		className: className
		        };
		        // 有修改，则发送修改请求
		        editInfo(base64encode(utf16to8(encodeURI(JSON.stringify(paramClass)))));
		    } else {
		    	// 无修改
		        showEditAlert("没有做更改！", false);
		    }
	    }
    } else if (flag == 2) {
    	var paramCode = document.getElementById("modify_paramCode").value.trim();
        var paramName = document.getElementById("modify_paramName").value.trim();
        var classId = document.getElementById("modify_paramClass_for_param").value.trim();
        
        var paramValue = document.getElementById("modify_paramValue").value.trim();
        var paramExtone = document.getElementById("modify_paramExtone").value.trim();
        var paramExttwo = document.getElementById("modify_paramExttwo").value.trim();
        var paramExtthree = document.getElementById("modify_paramExtthree").value.trim();

        if (!modCheckParamCode) {
	    	document.getElementById("warning_modify_paramCode_text").innerHTML = '请正确输入参数码！';
	        document.getElementById("warning_modify_paramCode").style.display = 'block';
		}else {
			document.getElementById("warning_modify_paramCode_text").innerHTML = '';
			document.getElementById("warning_modify_paramCode").style.display = 'none';
		}
	    
	    if (!modCheckParamName) {
	    	document.getElementById("warning_modify_paramName_text").innerHTML = '请正确输入参数名！';
	    	document.getElementById("warning_modify_paramName").style.display = "block";
		}else {
			document.getElementById("warning_modify_paramName_text").innerHTML = '';
			document.getElementById("warning_modify_paramName").style.display = 'none';
		}
        
	    if (modCheckParamCode && modCheckParamName) {
	    	if (!classId || classId == '') {
	    		classId = null;
	    	}
	        if (paramCode != currentSelectData.paramCode
	        		|| paramName != currentSelectData.paramName 
	        		|| paramValue != currentSelectData.paramValue 
	        		|| paramExtone != currentSelectData.paramExtone 
	        		|| paramExttwo != currentSelectData.paramExttwo 
	        		|| paramExtthree != currentSelectData.paramExtthree
	        		|| classId != currentSelectData.classId) {
	            var paramParam = {
	                id: currentSelectData.id,
	                classId: parseInt(classId),
	                paramCode: paramCode,
	                paramName: paramName,
	                paramValue: paramValue,
	                paramExtone: paramExtone,
	                paramExttwo: paramExttwo,
	                paramExtthree: paramExtthree
	            };
	            // 有修改，则发送修改请求
	            editInfo(base64encode(utf16to8(encodeURI(JSON.stringify(paramParam)))));
	        } else {
	        	// 无修改
	            showEditAlert("没有做更改！", false);
	        }
	    }

    }
    
}

function editInfo(data) {
	var editParamClassParamUrl = null;
	if (flag == 1) {
		editParamClassParamUrl = 'updateParamClass';
    } else if (flag == 2) {
    	editParamClassParamUrl = 'updateParamParam';
    }
	
    $.ajax({
        beforeSend: function () {
            showEditAlert('正在更新中，请稍等...', true);
        },
        complete: function () {
        },
        type: 'POST',
        url: editParamClassParamUrl,
        data: {
            'data': data
        },
        success: function (responseText) {
            var result = eval('(' + responseText + ')');
            if (result != null) {
                //editSuccess(result);
                showEditAlert('更新成功！', false);
                window.location.reload();
            } else {
                showEditAlert('对不起，更新失败！', false);
            }
        },
        error: function () {
            showEditAlert('对不起，更新失败！', false);
        }
    });
}

function editSuccess(data) {
// 修改成功后，更新画面显示信息
    var current_checkBox = data.id + '_cb';
    current_checkBox = document.getElementById(current_checkBox);
    if (flag == 1) {
    	// 修改 classCode
        var td_class_code = $(current_checkBox.parentNode).next()[0];
        td_class_code.innerHTML = data.classCode;
        // 修改 className
        var td_class_name = $(current_checkBox.parentNode).next().next()[0];
        td_class_name.innerHTML = data.className;
        // 修改 updateAt
        var td_update_at = $(current_checkBox.parentNode).next().next().next()[0];
        var updateAt = new Date(data.updateAt);
        var date = updateAt.format("yyyy-MM-dd hh:mm:ss");
        td_update_at.innerHTML = date;
    } else if (flag == 2) {
    	// 修改 paramCode
    	var td_param_code = $(current_checkBox.parentNode).next()[0];
    	td_param_code.innerHTML = data.paramCode;
    	// 修改 paramName
    	var td_param_name = $(current_checkBox.parentNode).next().next()[0];
    	td_param_name.innerHTML = data.paramName;
    	// 修改 className
    	var td_class_name = $(current_checkBox.parentNode).next().next().next()[0];
    	if (data.paramClass) {
    		td_class_name.innerHTML = data.paramClass.className;
		}
    	// 修改 updateAt
    	var td_update_at = $(current_checkBox.parentNode).next().next().next().next()[0];
    	var updateAt = new Date(data.updateAt);
    	var date = updateAt.format("yyyy-MM-dd hh:mm:ss");
    	td_update_at.innerHTML = date;
    }

    

// 修改成功后，更新每个按钮事件，使传递的参数保持最新
    var paramClassParamInfoJson = base64encode(utf16to8(JSON.stringify(data)));
    var td_edit = data.id + '_edit';
    td_edit = document.getElementById(td_edit);
    var td_delete = data.id + '_delete';
    td_delete = document.getElementById(td_delete);
    if (td_edit != null) {
        td_edit.onclick = function onclick(event) {
            javascript: showEditDialog(paramClassParamInfoJson, flag);
        };
    }
    if (td_delete != null) {
        td_delete.onclick = function onclick(event) {
            javascript: showDeleteDialog(paramClassParamInfoJson, flag);
        };
    }
    
}

function showEditAlert(message, isLoading) {
// 显示loading动画和提示内容
    document.getElementsByName("loading_modify_text")[0].innerHTML = message;
    if (isLoading == true) {
        document.getElementsByName("loading_modify")[0].style.display = 'block';
    } else {
        document.getElementsByName("loading_modify")[0].style.display = 'none';
    }
// 隐藏修改按钮
    document.getElementById("modify_Info").style.display = 'none';
    document.getElementById("modify_button").style.display = 'none';
// 显示画面
    document.getElementById("modify_dialog_div").style.display = 'block';
}
