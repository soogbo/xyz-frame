<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<div class="ch-container">
	<div class="row">--%>
<div>
    <div>
        <%@include file="noScriptWarning.jsp" %>
        <div id="content" style="" <%--class="col-lg-10 col-sm-10" style="margin-left: -20px;margin-top: -10px;"--%>>
            <%--  <div id="content" class="col-lg-10 col-sm-10" style="margin-left: -20px;margin-top: -10px;">--%>
            <div>
                <%--<div class="box col-md-12" style="width: 102.5%;">--%>
                <div class="box-inner">
                    <div class="box-header well" id="content_header_div">
                        <h2>
                            <i id="content_title_i"></i>&nbsp;
                            <div style="float: right;">
                                <span id="content_title_span"></span>
                                <%--<span class="help_content" onmousemove="showHelp()" onmouseout="hideHelp()"><a class="help_icon"></a></span>--%>
                            </div>
                            <script type="text/javascript">
                                document.getElementById("content_title_i").className = childMenuClass;
                                document.getElementById("content_title_span").innerHTML = childMenuName;
                            </script>
                        </h2>
                        <%--<div class="box-icon">--%>
                        <%--<a href="#" class="btn btn-minimize btn-round btn-default">--%>
                        <%--<i class="glyphicon glyphicon-chevron-up"></i>--%>
                        <%--</a>--%>
                        <%--</div>--%>
                        <div class="btn-group btn-group-sm pull-right">
                            <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                <i class="glyphicon glyphicon-user"></i>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><span class="hidden-sm hidden-xs" disabled style="padding: 3px 20px;">
					[<shiro:principal/>]
				</span></li>
                                <li>
                                    <a href="#" onclick="updateCurrentModel()">修改密码</a>
                                </li>
                                <li>
                                    <a href="#">坐席：${serviceNum}</a>
                                </li>
                                <li>
                                    <a href="#" title="谨慎使用" onclick="forceKillServiceNum('${serviceNum}')">软电话重置</a>
                                </li>
                                <li>
                                    <a href="logout">退出</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="box-content">
                        <div class="well">