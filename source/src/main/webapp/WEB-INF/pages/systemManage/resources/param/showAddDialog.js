var isCheckClassCode = false;
var isCheckClassName = false;
var isCheckParamCode = false;
var isCheckParamName = false;

var flag = null;//1:添加分类，2添加参数
$(function(){
	    $("#add_classCode").on("change", function () {
	    	var add_classCode = $("#add_classCode").val().trim();
	    	
	    	var flag = true;
	    	for (var i = 0; i < paramClassCodeArray.length; i++) {
        		if(add_classCode == paramClassCodeArray[i]){
        			flag = false;//判断classCode是否已存在
        		}
			}
	    	if (isEmptyObj(add_classCode)) {
	    		document.getElementById("warning_add_classCode_text").innerHTML = '请输入分类码！';
	            document.getElementById("warning_add_classCode").style.display = "block";
	            isCheckClassCode = false;
	        } else if (!flag) {
	        	document.getElementById("warning_add_classCode_text").innerHTML = '分类码已存在！';
	            document.getElementById("warning_add_classCode").style.display = "block";
	            isCheckClassCode = false;
	        } else {
        		document.getElementById("warning_add_classCode_text").innerHTML = '';
        		document.getElementById("warning_add_classCode").style.display = "none";
        		isCheckClassCode = true;
	        }
	    });
	    $("#add_className").on("change", function () {
	    	var add_className = $("#add_className").val().trim();
	    	if (isEmptyObj(add_className)) {
	    		document.getElementById("warning_add_className_text").innerHTML = '请输入分类名！';
	    		document.getElementById("warning_add_className").style.display = "block";
	    		isCheckClassName = false;
	    	} else {
	    		document.getElementById("warning_add_className_text").innerHTML = '';
	    		document.getElementById("warning_add_className").style.display = "none";
	    		isCheckClassName = true;
	    	}
	    });
	    $("#add_paramCode").on("change", function () {
	    	var add_paramCode = $("#add_paramCode").val().trim();
	    	
	    	var flag = true;
	    	for (var i = 0; i < paramParamCodeArray.length; i++) {
        		if(add_paramCode == paramParamCodeArray[i].paramCode){
        			flag = false;//判断paramCode是否已存在
        		}
			}
	    	if (isEmptyObj(add_paramCode)) {
	    		document.getElementById("warning_add_paramCode_text").innerHTML = '请输入参数码！';
	    		document.getElementById("warning_add_paramCode").style.display = "block";
	    		isCheckParamCode = false;
	    	/*} else if (!flag) {
	    		document.getElementById("warning_add_paramCode_text").innerHTML = '参数码已存在！';
	    		document.getElementById("warning_add_paramCode").style.display = "block";
	    		isCheckParamCode = false;*/
	        } else {
	    		document.getElementById("warning_add_paramCode_text").innerHTML = '';
	    		document.getElementById("warning_add_paramCode").style.display = "none";
	    		isCheckParamCode = true;
	    	}
	    });
	    $("#add_paramName").on("change", function () {
	    	var add_paramName = $("#add_paramName").val().trim();
	    	if (isEmptyObj(add_paramName)) {
	    		document.getElementById("warning_add_paramName_text").innerHTML = '请输入参数名！';
	    		document.getElementById("warning_add_paramName").style.display = "block";
	    		isCheckParamName = false;
	    	} else {
	    		document.getElementById("warning_add_paramName_text").innerHTML = '';
	    		document.getElementById("warning_add_paramName").style.display = "none";
	    		isCheckParamName = true;
	    	}
	    });
});

function showAddDialog(flag_) {
	flag = flag_;
    currentSelectData = null;
    is_showAddView = true;
    if (flag == 1) {
    	//隐藏新增参数div，显示新增分类div
    	document.getElementById("paramCodeDiv").style.display = 'block';
    	document.getElementById("paramParamDiv").style.display = 'none';
    	document.getElementById("showAddTitle").innerHTML = '添加分类';
    	
	}else if (flag == 2) {
		//隐藏新增分类div，显示新增参数div
		document.getElementById("paramParamDiv").style.display = 'block';
		document.getElementById("paramCodeDiv").style.display = 'none';
		document.getElementById("showAddTitle").innerHTML = '添加参数';
	}
    // 判断是否正在添加
    if (document.getElementsByName("loading_add")[0].style.display == '' || "none") {
    	// 设置画面用户默认信息为空
    	if (flag == 1) {
	        document.getElementById("add_classCode").value = '';
	        document.getElementById("add_className").value = '';
    	}else if (flag == 2) {
    		document.getElementById("add_paramCode").value = '';
            document.getElementById("add_paramName").value = '';
            document.getElementById("add_paramClass_for_param").value = '';
    	}
        // 隐藏loading动画和清空提示内容
        document.getElementsByName("loading_add_text")[0].innerHTML = '';
        document.getElementsByName("loading_add")[0].style.display = 'none';
        // 显示新增画面
        document.getElementById("add_Info").style.display = 'block';
        document.getElementById("add_button").style.display = 'inline-block';
        document.getElementById("add_dialog_div").style.display = 'block';
    }
}

function addButtonClick() {
	if (flag == 1) {
	    var add_classCode = document.getElementById("add_classCode").value.trim();
	    var add_className = document.getElementById("add_className").value.trim();
	    
	    if (!isCheckClassCode) {
	    	document.getElementById("warning_add_classCode_text").innerHTML = '请正确输入分类码！';
	    	document.getElementById("warning_add_classCode").style.display = "block";
		}else {
			document.getElementById("warning_add_classCode_text").innerHTML = '';
			document.getElementById("warning_add_classCode").style.display = "none";
		}
	    
	    if (!isCheckClassName) {
	    	document.getElementById("warning_add_className_text").innerHTML = '请正确输入分类名！';
			document.getElementById("warning_add_className").style.display = "block";
		}else {
			document.getElementById("warning_add_className_text").innerHTML = '';
			document.getElementById("warning_add_className").style.display = "none";
		}
		
	    if (isCheckClassCode && isCheckClassName) {
	    	var paramClass = {
	    			classCode: add_classCode,
	    			className: add_className
	    	};
	    	isCheckClassCode = false;
	    	isCheckClassName = false;
	    	addInfo(base64encode(utf16to8(encodeURI(JSON.stringify(paramClass)))));
		}
	}else if (flag == 2) {
		var add_paramCode = document.getElementById("add_paramCode").value.trim();
	    var add_paramName = document.getElementById("add_paramName").value.trim();
	    var add_paramClass_for_param = document.getElementById("add_paramClass_for_param").value;
	    
	    var add_paramValue = document.getElementById("add_paramValue").value.trim();
	    var add_paramExtone = document.getElementById("add_paramExtone").value.trim();
	    var add_paramExttwo = document.getElementById("add_paramExttwo").value.trim();
	    var add_paramExtthree = document.getElementById("add_paramExtthree").value.trim();
	    
		if (!isCheckParamCode) {
			document.getElementById("warning_add_paramCode_text").innerHTML = '请正确输入参数码！';
    		document.getElementById("warning_add_paramCode").style.display = "block";
		}
		if (!isCheckParamName) {
			document.getElementById("warning_add_paramName_text").innerHTML = '请正确输入参数名！';
			document.getElementById("warning_add_paramName").style.display = "block";
		}
		if (isCheckParamCode && isCheckParamName) {
			if (isEmptyObj(add_paramClass_for_param)) {
				add_paramClass_for_param = null;
			}
	    	var paramParam = {
	    			paramCode: add_paramCode,
	    			paramName: add_paramName,
	    			classId: add_paramClass_for_param,
	    			paramValue: add_paramValue,
	                paramExtone: add_paramExtone,
	                paramExttwo: add_paramExttwo,
	                paramExtthree: add_paramExtthree
	    	};
	    	isCheckParamCode = false;
	    	isCheckParamName = false;
	    	addInfo(base64encode(utf16to8(encodeURI(JSON.stringify(paramParam)))));
		}
		
	}
}

function addInfo(data) {
	var addParamClassParamUrl = null;
	if (flag == 1) {
		addParamClassParamUrl = 'addParamClass';
	}else if (flag == 2) {
		addParamClassParamUrl = 'addParamParam';
	}
    $.ajax({
        beforeSend: function () {
            showAddAlert('正在添加中，请稍等...', true);
        },
        complete: function () {
        },
        type: 'POST',
        url: addParamClassParamUrl,
        data: {
            'data': data
        },
        success: function (responseText) {
            var result = eval('(' + responseText + ')');
            if (result != null) {
            	if (flag == 1) {
	                document.getElementById("table_body").innerHTML = null;
	                $('#customDataTable').dataTable().fnClearTable(true);
	                $('#customDataTable').dataTable().fnDestroy();
            	}else if (flag == 2) {
            		document.getElementById("table_body2").innerHTML = null;
	                $('#customDataTable2').dataTable().fnClearTable(true);
	                $('#customDataTable2').dataTable().fnDestroy();
            	}
                getTableData();
                showAddAlert('添加成功！', false);
            } else {
                showAddAlert('对不起，添加失败！', false);
            }
        },
        error: function () {
            showAddAlert('对不起，添加失败！', false);
        }
    });
}

function showAddAlert(message, isLoading) {
// 显示loading动画和提示内容
    document.getElementsByName("loading_add_text")[0].innerHTML = message;
    if (isLoading == true) {
        document.getElementsByName("loading_add")[0].style.display = 'block';
    } else {
        document.getElementsByName("loading_add")[0].style.display = 'none';
    }
// 隐藏分类信息画面和添加按钮
    document.getElementById("add_Info").style.display = 'none';
    document.getElementById("add_button").style.display = 'none';
// 显示画面
    document.getElementById("add_dialog_div").style.display = 'block';
}
