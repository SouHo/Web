<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${(pageContext.request.contextPath) eq '/' ? '':(pageContext.request.contextPath)}"/>
<!DOCTYPE HTML>
<html>
  <head>
    <script type="text/javascript">
    function init(){
    	parent.location.href="${ctx}/index";
    };
    </script>
  </head>
  <body onload="init();">
  太长时间没有操作，请重新登录！
  </body>
</html>
