<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%response.setStatus(404);%>

<!DOCTYPE html>
<html>
<head>
	<title>404 - 页面不存在</title>
</head>

<body>
	<h2>404 - 页面不存在.</h2>
	<p><a href="<c:url value="/all/index"/>">返回首页</a></p>
</body>
</html>