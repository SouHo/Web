<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%response.setStatus(500);%>

<!DOCTYPE html>
<html>
<head>
	<title>500 - 系统内部错误</title>
</head>

<body>
<div style="margin-top:20px;">
	<!-- div>出错原因：</div> -->
	<div style="margin-left:20px;">${messager }</div>
</div>
</body>
</html>
