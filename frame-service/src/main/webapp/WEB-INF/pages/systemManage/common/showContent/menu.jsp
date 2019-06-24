<%--
  Created by IntelliJ IDEA.
  User: fual
  Date: 2015/6/3
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@include file="top.jsp" %>

<div id="showMenuDiv">
    <div style="width: 100%; text-align: center; height: 38px;">
        <span style="font-size: 22px;">催收系统</span>
    </div>
    <div class="list-group">
        <c:forEach var="parentMenu" items="${menus}">
            <c:choose>
                <c:when test="${parentMenu.id==menuID}">
                    <a href="#${parentMenu.id}" class="list-group-item active" data-toggle="collapse">${parentMenu.name}</a>
                    <div class="list-group" style="padding-left: 18px;" id="${parentMenu.id}">
                        <c:forEach items="${parentMenu.children}" var="childMenu">
                            <c:if test="${childMenu.id==childMenuID}">
                                <script type="text/javascript">
                                    parentMenuName = '${parentMenu.name}';
                                    childMenuName = '${childMenu.name}';
                                    childMenuClass = '${childMenu.iconcssclass}';
                                    currentParentMenu = '${parentMenu.id}';
                                    currentChildMenu = '${childMenu.id}';
                                    currentMenuUrl = '${childMenu.url}';
                                    currentAppId = '${appId}';
                                    currentAppName = '${appName}';
                                    currentAppOperation = '${operation}';
                                </script>
                            </c:if>
                            <c:choose>
                                <c:when test="${childMenu.id==childMenuID}">
                                    <a href="javascript:void(0);"
                                       onclick="javascript: window.location.href = '${childMenu.url}';" class="list-group-item active">
                                        <i class="${childMenu.iconcssclass}"></i>
                                        <span> ${childMenu.name}</span>
                                        <span id="menu_warning_${childMenu.id}" class="label label-warning"
                                              style="float: right;"></span>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="javascript:void(0);"
                                       onclick="javascript: window.location.href = '${childMenu.url}';" class="list-group-item">
                                        <i class="${childMenu.iconcssclass}"></i>
                                        <span> ${childMenu.name}</span>
                                        <span id="menu_warning_${childMenu.id}" class="label label-warning"
                                              style="float: right;"></span>
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach></div>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${parentMenu.hasChild == true}">
                            <a href="#${parentMenu.id}" class="list-group-item" data-toggle="collapse">${parentMenu.name}</a>
                            <div class="list-group collapse" style="padding-left: 18px;" id="${parentMenu.id}">
                                <c:forEach items="${parentMenu.children}" var="childMenu">
                                    <a href="javascript:void(0);"
                                       onclick="javascript: window.location.href = '${childMenu.url}';" class="list-group-item">
                                        <i class="${childMenu.iconcssclass}"></i>
                                        <span> ${childMenu.name}</span>
                                    </a>
                                </c:forEach></div>
                        </c:when>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
</div>

<script>
    $(function () {
        $('#showMenuDiv').BootSideMenu({
            side: "left", // left or right
            autoClose: false, // auto close when page loads
            width: '160px',
            closeOnClick: false,
            onOpen: function () {
                $(window).trigger('resize');
            },
            onClose: function () {
                $(window).trigger('resize');
            }
        });
    });
</script>