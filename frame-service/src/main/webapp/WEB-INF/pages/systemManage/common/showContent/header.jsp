<%--
  Created by IntelliJ IDEA.
  User: along
  Date: 2015/6/7
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = "systemManage/";%>
<link href="static/common/images/favicon.ico" rel="shortcut icon" type="image/x-icon">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
<meta name="author" content="Muhammad Usman">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<base href='${"/" == pageContext.request.contextPath ? "" : pageContext.request.contextPath}/'/>

<!-- common css -->
<link href="<%=path%>resources/common/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=path%>resources/common/css/bootstrap-cerulean.min.css" rel="stylesheet" id="bs-css">
<link href="<%=path%>resources/common/css/charisma-app.css" rel="stylesheet">
<link href="<%=path%>resources/common/css/content.css" rel="stylesheet">
<link href="<%=path%>resources/common/css/dialog.css" rel="stylesheet">
<link href="<%=path%>resources/common/css/loading.css" rel="stylesheet">

<!-- common js-->
<script src="static/common/js/jquery-1.12.4.min.js"></script>

<script src="<%=path%>resources/common/js/bootstrap.min.js" type="text/javascript"></script>
<script src="<%=path%>resources/common/js/jquery.cookie.js" type="text/javascript"></script>
<script src="<%=path%>resources/common/js/tools.js" type="text/javascript"></script>
<script src="<%=path%>resources/common/js/base64.js" type="text/javascript"></script>
<script src="<%=path%>resources/common/js/dictionary.js" type="text/javascript"></script>
<script src="static/common/js/charisma-theme.js" type="text/javascript"></script>
<script src="static/common/js/charisma.js" type="text/javascript"></script>
<script src="<%=path%>resources/common/js/checkBoxAction.js" type="text/javascript"></script>
<script src="<%=path%>resources/common/js/menu_collection.js" type="text/javascript"></script>
<!-- plugin js-->
<link href="static/plugin/DataTables-1.10.13/media/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css">
<script src="static/plugin/DataTables-1.10.13/media/js/jquery.dataTables.min.js" type="text/javascript"></script>

<script src="static/plugin/DataTables-1.10.13/media/js/dataTableExt.js" type="text/javascript"></script>
<script src="static/plugin/DataTables-1.10.13/extensions/Buttons/js/dataTables.buttons.min.js" type="text/javascript"></script>
<!-- dataTables固定表头-->
<link href="static/plugin/DataTables-1.10.13/extensions/FixedHeader/css/fixedHeader.bootstrap.min.css" rel="stylesheet" type="text/css">
<script src="static/plugin/DataTables-1.10.13/extensions/FixedHeader/js/dataTables.fixedHeader.min.js" type="text/javascript"></script>

<link href="<%=path%>resources/plugin/jquery-treetable_3.2.0/css/jquery.treetable.css" rel="stylesheet"
      type="text/css">
<link href="<%=path%>resources/plugin/jquery-treetable_3.2.0/css/jquery.treetable.theme.default.css"
      rel="stylesheet" type="text/css">
<script src="<%=path%>resources/plugin/jquery-treetable_3.2.0/js/jquery.treetable.js"
        type="text/javascript"></script>

<!--noty主文件-->
<%--<script type="text/javascript" src="static/plugin/noty/jquery.noty.js"></script>--%>
<%--<!--noty提示信息位置的文件, 需要哪些位置就引入对应的脚本,这里为center,可以添加多个布局文件-->--%>
<%--<script type="text/javascript" src="static/plugin/noty/layouts/center.js"></script>--%>
<%--<!--noty主题样式文件,-->--%>
<%--<script type="text/javascript" src="static/plugin/noty/themes/default.js"></script>--%>

<%--<script type="text/javascript" src="static/plugin/noty-2.4.1/js/noty/jquery.noty.js"></script>--%>
<script type="text/javascript" src="static/plugin/noty-2.4.1/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<%--<script type="text/javascript" src="static/plugin/noty-2.4.1/js/noty/layouts/center.js"></script>--%>

<link href="<%=path%>resources/plugin/zTree-zTree_v3.5.16/zTree_v3/css/zTreeStyle/zTreeStyle.css" rel="stylesheet"
      type="text/css">
<script src="<%=path%>resources/plugin/zTree-zTree_v3.5.16/zTree_v3/js/jquery.ztree.core-3.5.js"
        type="text/javascript"></script>
<script src="<%=path%>resources/plugin/zTree-zTree_v3.5.16/zTree_v3/js/jquery.ztree.excheck-3.5.js"
        type="text/javascript"></script>

<link href="static/plugin/jQuery-tooltip/tooltip.css" rel="stylesheet">
<script src="static/plugin/jQuery-tooltip/tooltip.js"></script>
<script src="<%=path%>resources/common/js/commonFunction.js" type="text/javascript"></script>

<script src="static/common/js/jquery.blockUI.js" type="text/javascript"></script>

<script src="static/plugin/layer/layer.js" type="text/javascript"></script>

<link href="static/plugin/jquery-datetimepicker-master/jquery.datetimepicker.css" type="text/css" rel="stylesheet">
<script src="static/plugin/jquery-datetimepicker-master/jquery.datetimepicker.full.js"></script>

<link href="static/plugin/jQuery-Bootstrap-BootSideMenu/css/BootSideMenu.css" rel="stylesheet">
<script src="static/plugin/jQuery-Bootstrap-BootSideMenu/js/BootSideMenu.js"></script>
