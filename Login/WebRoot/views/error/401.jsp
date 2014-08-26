<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<%response.setStatus(401);%>
<!DOCTYPE html>
<html>
<head>
	<title>401 - 用户权限错误</title>
</head>

<body>
	<h2>401 - 当前登录的用户执行了未被授权的操作.</h2>
	<p><a href="<c:url value="/all/index"/>">返回首页</a></p>
</body>
</html>
