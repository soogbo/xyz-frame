<%--
  Created by IntelliJ IDEA.
  User: zhengwei
  Date: 2016/7/8
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${result.get('title')}</title>
</head>
<body>
${result.msg}
<div style="display:none">
    <div>${result.actualError}</div>
  <pre>
      ${result.get('trace')}
  </pre>
</div>
</body>
</html>
