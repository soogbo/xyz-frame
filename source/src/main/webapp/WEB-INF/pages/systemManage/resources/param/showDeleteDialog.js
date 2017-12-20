var flag = null;//1:分类，2参数
function showDeleteDialog(data,flag_) {
	flag = flag_;
    var dataTemp = utf8to16(base64decode(data));
    var data_ = eval('(' + dataTemp + ')');
    currentSelectData = data_;
    if (flag == 1) {
    	document.getElementById("showDeleteTitle").innerHTML = '删除分类';
	} else if (flag == 2) {
		document.getElementById("showDeleteTitle").innerHTML = '删除参数';
	}
    // 判断是否正在删除
    if (document.getElementsByName("loading_delete")[0].style.display == '' || 'none') {
        showDeleteAlert('确定要删除吗？', false);
        // 显示删除按钮
        document.getElementById("delete_button").style.display = 'inline-block';
        // 显示画面
        document.getElementById("delete_dialog_div").style.display = 'block';
    }
}

function showBulkDeleteDialog() {
    selectedCheckbox = null;
    // 判断是否正在删除
    if (document.getElementsByName("loading_delete")[0].style.display == '' || 'none') {
        var checkVBoxs = document.getElementsByName("checkInfo");
        selectedCheckbox = new Array();
        for (var i = 0; i < checkVBoxs.length; i++) {
            if (checkVBoxs[i].checked == true) {
                selectedCheckbox.push(checkVBoxs[i]);
            }
        }
        if (selectedCheckbox.length > 0) {
            showDeleteAlert('确定要删除选中的' + selectedCheckbox.length + '条数据吗？', false);
            // 显示删除按钮
            document.getElementById("delete_button").style.display = 'inline-block';
        } else {
            showDeleteAlert('没有数据被选中', false);
            // 显示删除按钮
            document.getElementById("delete_button").style.display = 'none';
        }
        // 显示画面
        document.getElementById("delete_dialog_div").style.display = 'block';
    }
}

function deleteButtonClick() {
    var ids = "";
    if (selectedCheckbox != null && currentSelectData == null) {
        for (var i = 0; i < selectedCheckbox.length; i++) {
            if (i == selectedCheckbox.length - 1) {
                ids += selectedCheckbox[i].title;
            } else {
                ids += (selectedCheckbox[i].title + ",");
            }
        }
    } else if (selectedCheckbox == null && currentSelectData != null) {
        ids += currentSelectData.id;
    }
    deleteData(ids);
}

function deleteData(data) {
	var deleteUrl = null;
	if (flag == 1) {
		deleteUrl = 'deleteParamClass';
	} else if (flag == 2) {
		deleteUrl = 'deleteParamParam';
	}
	
    $.ajax({
        beforeSend: function () {
            showDeleteAlert('正在删除中，请稍等...', true);
        },
        complete: function () {
        },
        type: 'POST',
        url: deleteUrl,
        data: {
            'data': data
        },
        success: function (responseText) {
            if (responseText == 1) {
                document.getElementById("table_body2").innerHTML = null;
                if (flag == 1) {
                	 $('#customDataTable').dataTable().fnClearTable(false);
                     $('#customDataTable').dataTable().fnDestroy();
            	} else if (flag == 2) {
            		 $('#customDataTable2').dataTable().fnClearTable(false);
                     $('#customDataTable2').dataTable().fnDestroy();
            	}
                getTableData();
                showDeleteAlert('删除成功！', false);
            } else {
                showDeleteAlert('对不起，删除失败！', false);
            }
        },
        error: function () {
            showDeleteAlert('对不起，删除失败！', false);
        }
    });
}

function showDeleteAlert(message, isLoading) {
    // 显示loading动画和提示内容
    document.getElementsByName("loading_delete_text")[0].innerHTML = message;
    if (isLoading == true) {
        document.getElementsByName("loading_delete")[0].style.display = 'block';
    } else {
        document.getElementsByName("loading_delete")[0].style.display = 'none';
    }
    // 隐藏删除按钮
    document.getElementById("delete_button").style.display = 'none';
    // 显示画面
    document.getElementById("delete_dialog_div").style.display = 'block';
}