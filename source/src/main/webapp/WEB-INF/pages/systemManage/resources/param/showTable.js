function getTableData() {
	getParamClassTableData();
	getParamParamTableData();
	getParamClass();
}
var paramClassList = null;
var paramClassCodeArray = new Array();//分类码
var paramParamCodeArray = new Array();
function getParamClassTableData() {
    var operation = null;
    var selectparamClassId = $("#selectparamClassCode").val();
    document.getElementById("checkall").checked = false;
    document.getElementById("checkall").style.display = "none";
    customDataTable = $('#customDataTable')
        .dataTable(
            {
                "sDom": "<'row'<'col-md-6'l><'col-md-6'f>r>t<'row'<'col-md-12'i><'col-md-12 center-block'p>>",
                "bAutoWidth": false,
                "bProcessing": false,
                'bStateSave': false,// 开启或者禁用状态储存(刷新前固定保存查询参数)。
                "bJQueryUI": true, // 是否将分页样式应用到表格
                "bPaginate": true, // 是否允许分页
                "bLengthChange": true, // 是否显示每页显示条数
                "bFilter": false, // 是否启用条件查询
                "bSort": false, // 是否启用列排序
                "bInfo": true, // 是否显示分页信息
                "bLengthChange": true,// 每行显示记录数
                "bDestroy": true,
                "bServerSide": true, // 指定从服务器端获取数据
                "sPaginationType": "bootstrap",
                "sAjaxSource": "getParamClass",
                "fnServerData": function (sSource, aoData, fnCallback) {
                    $.ajax({
                        beforeSend: function () {
                            document.getElementsByName("loadingDataTable")[0].style.display = 'block';
                        },
                        complete: function () {
                        	document.getElementsByName("loadingDataTable")[0].style.display = 'none';
                        },
                        type: 'GET',
                        url: 'getParamClass',
                        data: {
                            "aoData": JSON.stringify(aoData),
                            "classIds": selectparamClassId
                        },
                        success: function (responseText) {
                            var result = eval('(' + responseText + ')');
                            if (result != null) {
                                fnCallback(result);
                                paramClassList = result;
                            }else {
								$("#table_body").html('<td colspan="5" style="text-align: center;">没有找到符合条件的数据！</td>');
							}
                            $("#selectparamClassCode").val('');
                        },
                        error: function () {
                        	$("#selectparamClassCode").val('');
                            alert('对不起，获取数据失败!');
                        }
                    });
                },
                "aoColumns": [
                    {
                        "sWidth": "5%",
                        "bSortable": false,
                        "sClass": "center"
                    },
                    {
                        "sWidth": "35%",
                        "bSortable": false,
                        "sClass": "center"
                    },
                    {
                        "sWidth": "25%",
                        "bSortable": false,
                        "sClass": "center"
                    },
                    /*{
                        "sWidth": "13%",
                        "bSortable": false,
                        "sClass": "center"
                    },*/
                    {
                        "sWidth": "35%",
                        "bSortable": false,
                        "sClass": "center"
                    }
                ],
                "aoColumnDefs": [
                    {
                        sDefaultContent: '',
                        aTargets: ['_all']
                    },
                    {
                        "aTargets": [0],
                        "mData": null,
                        "mRender": function (data, type, full) {
                            var inputHtml = '<input type="checkbox" id="' + data.id + '_cb" title="' + data.id + '" name="checkInfo" onchange="selectCheckBox(this);"/>';
                            return inputHtml;
                        }
                    },
                    {
                        "aTargets": [1],
                        "mData": null,
                        "mRender": function (data, type, full) {
                        	return data.classCode || "";
                        }
                    },
                    {
                        "aTargets": [2],
                        "mData": null,
                        "mRender": function (data, type, full) {
                        	return data.className || "";
                        }
                    },
                    /*{
                        "aTargets": [3],
                        "mData": null,
                        "mRender": function (data, type, full) {
                        	if (data.updateAt != null) {
                                var updateAt = new Date(data.updateAt);
                                var date = updateAt.format("yyyy-MM-dd hh:mm:ss");
                                return date;
                            } else {
                            	return "";
                            }
                        }
                    },*/
                    {
                        "aTargets": [3],
                        "mData": null,
                        "mRender": function (data, type, full) {
                        	operation = "update,create,delete,view,review,shelves,handleReport";
                        	if (operation.indexOf("create") < 0) {
                        		document.getElementById("addButton").style.display = "none";
                        	}
                    		var opt = '';
                    		var dataTemp = JSON.stringify(data);
                            dataTemp = base64encode(utf16to8(dataTemp));
                            if (operation.length > 0) {
                                if (operation.indexOf("update") >= 0) {
                            opt += '<a id="' + data.id + '_edit" class="btn btn-success btn-sm" style="margin-top: 2px;margin-bottom: 2px;" href="javascript:void(0);" onclick="javascript:showEditDialog(\'' + dataTemp
                                + '\', 1);"> <i class="glyphicon glyphicon-edit icon-white"></i> 修改</a> ';
                                }
                                if (operation.indexOf("delete") >= 0) {
                            opt += '<a id="' + data.id + '_delete" class="btn btn-danger btn-sm" style="margin-top: 2px;margin-bottom: 2px;" href="javascript:void(0);" onclick="javascript:showDeleteDialog(\'' + dataTemp
                                + '\', 1);"> <i class="glyphicon glyphicon-trash icon-white"></i> 删除</a> ';
                                }
                            } else {
                                var warningHtml = '<div class="has-error"><div class="control-label">正在 ' +
                                    '' +
                                    '登录</div></div>';
                                return warningHtml;
                            }
                               return opt;
                        }
                    }
                ],
                "oLanguage": {
                    "sSearch": "搜索本次查询:",
                    "sLengthMenu": "每页显示 _MENU_ 条",
                    "sZeroRecords": "没有找到符合条件的数据",
                    "sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条 ",
                    "sInfoEmpty": "共 0 条数据，当前页显示0 到 0 条",
                    "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
                    "oPaginate": {
                        "sFirst": "首页",
                        "sPrevious": " 上一页 ",
                        "sNext": " 下一页 ",
                        "sLast": "尾页"
                    }
                },
                "fnInitComplete": function () {
                    this.fnAdjustColumnSizing(true);
                    if (operation != null) {
                        /*if (operation.indexOf("delete") >= 0) {
                            var oDiv = document.createElement("div");
                            oDiv.style.float = "right";
                            oDiv.style.display = 'inline-block';
                            oDiv.style.styleFloat = "right";
                            oDiv.style.cssFloat = "right";
                            oDiv.innerHTML = '<a id="bulk_delete" style="margin-bottom:10px;" class="btn btn-danger" href="javascript:void(0);" onclick="javascript:showBulkDeleteDialog();"> <i class="glyphicon glyphicon-trash icon-white"></i>批量删除</a>';
                            var por = document.getElementById("customDataTable_length").parentNode;
                            por.style.width = "100%";
                            por.appendChild(oDiv);
                            document.getElementById("customDataTable_length").style.display = 'inline-block';
                        }*/
                    }
                }
            });
}
function getParamParamTableData() {
	var operation = null;
	var selectparamClassId = $("#selectparamClassId").val();
	document.getElementById("checkall2").checked = false;
	document.getElementById("checkall2").style.display = "none";
	customDataTable = $('#customDataTable2')
	.dataTable(
			{
				"sDom": "<'row'<'col-md-6'l><'col-md-6'f>r>t<'row'<'col-md-12'i><'col-md-12 center-block'p>>",
				"bAutoWidth": false,
				"bProcessing": false,
				'bStateSave': false,// 开启或者禁用状态储存。当你开启了状态储存，Datatables会存储一个状态到浏览器上，包含分页位置，每页显示的长度，过滤后的结果和排序。涉及到条件查询时设置为false，否则修改条件不会清空查询起始下标
				"bJQueryUI": true, // 是否将分页样式应用到表格
				"bPaginate": true, // 是否允许分页
				"bLengthChange": true, // 是否显示每页显示条数
				"bFilter": false, // 是否启用条件查询
				"bSort": false, // 是否启用列排序
				"bInfo": true, // 是否显示分页信息
				"bLengthChange": true,// 每行显示记录数
				"bDestroy": true,
				"bServerSide": true, // 指定从服务器端获取数据
				"sPaginationType": "bootstrap",
				"sAjaxSource": "getParamParam",
				"fnServerData": function (sSource, aoData, fnCallback) {
					$.ajax({
						beforeSend: function () {
							document.getElementsByName("loadingDataTable2")[0].style.display = 'block';
						},
						complete: function () {
							document.getElementsByName("loadingDataTable2")[0].style.display = 'none';
						},
						type: 'GET',
						url: 'getParamParam',
						data: {
							"aoData": JSON.stringify(aoData),
							"classIds": selectparamClassId
						},
						success: function (responseText) {
							var result = eval('(' + responseText + ')');
							if (result != null) {
								fnCallback(result);
							}else {
								$("#table_body2").html('<td colspan="6" style="text-align: center;">没有找到符合条件的数据！</td>');
							}
							$("#selectparamClassId").val("");
						},
						error: function () {
							$("#selectparamClassId").val("");
							alert('对不起，获取数据失败!');
						}
					});
				},
				"aoColumns": [
				              {
				            	  "sWidth": "4%",
				            	  "bSortable": false,
				            	  "sClass": "center"
				              },
				              {
				            	  "sWidth": "10%",
				            	  "bSortable": false,
				            	  "sClass": "center"
				              },
				              {
				            	  "sWidth": "18%",
				            	  "bSortable": false,
				            	  "sClass": "center"
				              },
				              {
				            	  "sWidth": "18%",
				            	  "bSortable": false,
				            	  "sClass": "center"
				              },
				              {
				            	  "sWidth": "8%",
				            	  "bSortable": false,
				            	  "sClass": "center"
				              },
				              {
				            	  "sWidth": "8%",
				            	  "bSortable": false,
				            	  "sClass": "center"
				              },
				              {
				            	  "sWidth": "8%",
				            	  "bSortable": false,
				            	  "sClass": "center"
				              },
				              {
				            	  "sWidth": "8%",
				            	  "bSortable": false,
				            	  "sClass": "center"
				              },
				              /*{
				            	  "sWidth": "13%",
				            	  "bSortable": false,
				            	  "sClass": "center"
				              },*/
				              {
				            	  "sWidth": "18%",
				            	  "bSortable": false,
				            	  "sClass": "center"
				              }
				              ],
				              "aoColumnDefs": [
				                               {
				                            	   sDefaultContent: '',
				                            	   aTargets: ['_all']
				                               },
				                               {
				                            	   "aTargets": [0],
				                            	   "mData": null,
				                            	   "mRender": function (data, type, full) {
			                            			   var inputHtml = '<input type="checkbox" id="' + data.id + '_cb" title="' + data.id + '" name="checkInfo2" onchange="selectCheckBox(this);"/>';
			                            			   return inputHtml;
				                            	   }
				                               },
				                               {
				                            	   "aTargets": [1],
				                            	   "mData": null,
				                            	   "mRender": function (data, type, full) {
				                            		   if (data.paramClass) {
				                            			   return data.paramClass.className;
				                            		   }
														return "";
				                            	   }
				                               },
				                               {
				                            	   "aTargets": [2],
				                            	   "mData": null,
				                            	   "mRender": function (data, type, full) {
				                            		   return data.paramCode || "";
				                            	   }
				                               },
				                               {
				                            	   "aTargets": [3],
				                            	   "mData": null,
				                            	   "mRender": function (data, type, full) {
				                            		   return data.paramName || "";
													}
				                               },
				                               {
				                            	   "aTargets": [4],
				                            	   "mData": null,
				                            	   "mRender": function (data, type, full) {
				                            		   return data.paramValue || "";
				                            	   }
				                               },
				                               {
				                            	   "aTargets": [5],
				                            	   "mData": null,
				                            	   "mRender": function (data, type, full) {
				                            		   return data.paramExtone || "";
				                            	   }
				                               },
				                               {
				                            	   "aTargets": [6],
				                            	   "mData": null,
				                            	   "mRender": function (data, type, full) {
				                            		   return data.paramExttwo || "";
				                            	   }
				                               },
				                               {
				                            	   "aTargets": [7],
				                            	   "mData": null,
				                            	   "mRender": function (data, type, full) {
				                            		   return data.paramExtthree || "";
				                            	   }
				                               },
				                               /*{
				                            	   "aTargets": [8],
				                            	   "mData": null,
				                            	   "mRender": function (data, type, full) {
				                            		   if (data.updateAt != null) {
				                            			   var updateAt = new Date(data.updateAt);
				                            			   var date = updateAt.format("yyyy-MM-dd hh:mm:ss");
				                            			   return date;
				                            		   } else {
				                            			   return "";
				                            		   }
				                            	   }
				                               },*/
				                               {
				                            	   "aTargets": [8],
				                            	   "mData": null,
				                            	   "mRender": function (data, type, full) {
				                            		   operation = "update,create,delete,view,review,shelves,handleReport";
				                            		   if (operation.indexOf("create") < 0) {
				                                   			document.getElementById("addButton2").style.display = "none";
				                                   		}
				                            		   var opt = '';
				                            		   var dataTemp = JSON.stringify(data);
				                            		   dataTemp = base64encode(utf16to8(dataTemp));
				                            		   if (operation.length > 0) {
				                                           if (operation.indexOf("update") >= 0) {
				                            		   opt += '<a id="' + data.id + '_edit" class="btn btn-success btn-sm" style="margin-top: 2px;margin-bottom: 2px;" href="javascript:void(0);" onclick="javascript:showEditDialog(\'' + dataTemp
				                            		   + '\', 2);"> <i class="glyphicon glyphicon-edit icon-white"></i> 修改</a> ';
				                                           }
				                                           if (operation.indexOf("delete") >= 0) {
				                            		   opt += '<a id="' + data.id + '_delete" class="btn btn-danger btn-sm" style="margin-top: 2px;margin-bottom: 2px;" href="javascript:void(0);" onclick="javascript:showDeleteDialog(\'' + dataTemp
				                            		   + '\', 2);"> <i class="glyphicon glyphicon-trash icon-white"></i> 删除</a> ';
				                                           }
				                                       } else {
				                                           var warningHtml = '<div class="has-error"><div class="control-label">正在 ' +
				                                               '' +
				                                               '登录</div></div>';
				                                           return warningHtml;
				                                       }
				                                          return opt;
				                                    }
				                                }
				                               ],
				                               "oLanguage": {
				                            	   "sSearch": "搜索本次查询:",
				                            	   "sLengthMenu": "每页显示 _MENU_ 条",
				                            	   "sZeroRecords": "没有找到符合条件的数据",
				                            	   "sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条 ",
				                            	   "sInfoEmpty": "共 0 条数据，当前页显示0 到 0 条",
				                            	   "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
				                            	   "oPaginate": {
				                            		   "sFirst": "首页",
				                            		   "sPrevious": " 上一页 ",
				                            		   "sNext": " 下一页 ",
				                            		   "sLast": "尾页"
				                            	   }
				                               },
				                               "fnInitComplete": function () {
				                            	   this.fnAdjustColumnSizing(true);
				                            	   if (operation != null) {
				                            		   /*if (operation.indexOf("delete") >= 0) {
				                            			   var oDiv = document.createElement("div");
				                            			   oDiv.style.float = "right";
				                            			   oDiv.style.display = 'inline-block';
				                            			   oDiv.style.styleFloat = "right";
				                            			   oDiv.style.cssFloat = "right";
				                            			   oDiv.innerHTML = '<a id="bulk_delete" style="margin-bottom:10px;" class="btn btn-danger" href="javascript:void(0);" onclick="javascript:showBulkDeleteDialog();"> <i class="glyphicon glyphicon-trash icon-white"></i>批量删除</a>';
				                            			   var por = document.getElementById("customDataTable_length").parentNode;
				                            			   por.style.width = "100%";
				                            			   por.appendChild(oDiv);
				                            			   document.getElementById("customDataTable_length").style.display = 'inline-block';
				                            		   }*/
				                            	   }
				                               }
			});
}
//获取下拉选项
function getParamClass(){
	$.ajax({
        type: 'GET',
        url: 'getAllParamClassAndParamCode',
        success: function (responseText) {
        	map = eval('(' + responseText + ')');
        	paramClassList = map.paramClassList;
        	paramParamCodeArray = map.allParamCode;
        	$("#selectparamClassCode option:not(:first)").remove();
        	$.each(paramClassList, function(){
        		$("#selectparamClassCode").append('<option value="'+this.id+'" title="'+this.classCode+'"> '+this.classCode+'</option>');
        	});
        	$("#selectparamClassId option:not(:first)").remove();
        	paramClassCodeArray.splice(0, paramClassCodeArray.length);
        	$.each(paramClassList, function(){
        		$("#selectparamClassId").append('<option value="'+this.id+'" title="'+this.className+'"> '+this.className+'</option>');
        		paramClassCodeArray.push(this.classCode);
        	});
        	$("#modify_paramClass_for_param option:not(:first)").remove();
        	$.each(paramClassList, function(){
        		if (!isEmptyObj(this.className)) {
        			$("#modify_paramClass_for_param").append('<option value="'+this.id+'" title="'+this.className+'"> '+this.className+'</option>');
        		}
        	});
        	$("#add_paramClass_for_param option:not(:first)").remove();
        	$.each(paramClassList, function(){
        		$("#add_paramClass_for_param").append('<option value="'+this.id+'" title="'+this.className+'"> '+this.className+'</option>');
        	});
        	
        },
        error: function () {
        	layer.alert("获取分类异常！" + responseText);
        }
    });
}