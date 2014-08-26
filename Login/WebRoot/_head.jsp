<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edege, chrome=1" />

<link rel="stylesheet" type="text/css"  href="${ctx}/static/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"  href="${ctx}/static/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx }/static/css/master.css">

<script type="text/javascript"  src="${ctx}/static/easyui/jquery-1.8.3.min.js"></script>
<script type="text/javascript"  src="${ctx}/static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/static/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/static/js/json2.js"></script>
<script type="text/javascript">
var baseUrl = '${ctx}/'; 
</script>
<script type="text/javascript" src="${ctx}/static/js/master.js"></script>