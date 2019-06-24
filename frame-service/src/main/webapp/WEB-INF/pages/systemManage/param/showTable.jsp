<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@include file="addDialog.jsp" %>
<%@include file="editDialog.jsp" %>
<%@include file="../common/showDeleteDialog/deleteDialog.jsp" %>

<div class="row clearfix">
    
	<div id="showTable" class="col-md-4 column">
	    <div class="page-header" id="addNew">
		    <div class="box-content" align="left">
			    <form class="form-inline" id="statistics-approve-form">
               
                    <a href="javascript:void(0);" onclick="javascript:showAddDialog(1);" id="addButton"
	                   class="btn btn-primary btn-lg">
	                    <i class="glyphicon glyphicon-plus glyphicon-white"></i> 添加分类
	                </a>&nbsp;&nbsp;&nbsp;&nbsp;
                
                    <label >分类码：</label>
                    <div class="form-group">
                        <select id="selectparamClassCode" name="paramClassCode" class="form-control" placeholder="分类码">
                                <option value="" title="所有"> 所有</option>
                        </select>
                    </div>&nbsp;&nbsp;&nbsp;&nbsp;
               
                    <div class="form-group">
                        <button type="button" class="btn btn-success btn-sm" onclick="getTableData()">
                            <i class="glyphicon glyphicon-search"></i> 搜索
                        </button>
                    </div>
                </form>
		    </div>
		</div>
	    <div id="loading" class="center" name="loadingDataTable">
		    Loading...
		    <div class="center"></div>
		</div>
	    <table id="customDataTable" class="table table-striped table-bordered bootstrap-datatable responsive">
	        <thead>
	        <tr id="table_header_tr">
	            <th><label><input type="checkbox" id="checkall" name="checkall" onchange="selectCheckBox(this);"/></label></th>
	            <th>分类码</th>
	            <th>分类名</th>
<!-- 	            <th>更新日期</th> -->
	            <th>操作</th>
	        </tr>
	        </thead>
	        <tbody id="table_body">
	        </tbody>
	    </table>
	</div>
	<div id="showTable2" class="col-md-8 column">
	    <div class="page-header" id="addNew2">
		    <div class="box-content" align="left">
			   <form class="form-inline" id="statistics-approve-form">
			   
	                <a href="javascript:void(0);" onclick="javascript:showAddDialog(2);" id="addButton2"
	                   class="btn btn-primary btn-lg">
	                    <i class="glyphicon glyphicon-plus glyphicon-white"></i> 添加参数
	                </a>&nbsp;&nbsp;&nbsp;&nbsp;
		        
		            <label >参数分类：</label>
		            <div class="form-group">
		                <select id="selectparamClassId" name="paramClassId" class="form-control" placeholder="分类">
		                        <option value="" title="所有"> 所有</option>
		                </select>
		            </div>&nbsp;&nbsp;&nbsp;&nbsp;
		       
			        <div class="form-group">
			            <button type="button" class="btn btn-success btn-sm" onclick="getTableData()">
			                <i class="glyphicon glyphicon-search"></i> 搜索
			            </button>
			        </div>
		        </form>
	        </div>
        </div>
        
	    <div id="loading2" class="center" name="loadingDataTable2">
            Loading...
            <div class="center"></div>
        </div>
	    <table id="customDataTable2" class="table table-striped table-bordered bootstrap-datatable responsive">
	        <thead>
	        <tr id="table_header_tr2">
	            <th><label><input type="checkbox" id="checkall2" name="checkall2" onchange="selectCheckBox2(this);"/></label></th>
	            <th>所属分类名</th>
	            <th>参数码</th>
	            <th>参数名</th>
	            <th>参数值</th>
	            <th>扩展1</th>
	            <th>扩展2</th>
	            <th>扩展3</th>
<!-- 	            <th>更新日期</th> -->
	            <th>操作</th>
	        </tr>
	        </thead>
	        <tbody id="table_body2">
	        </tbody>
	    </table>
	</div>
</div>
