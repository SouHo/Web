<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML >
<html>
  <head>
    <title>系统登录页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<%@ include file="/_head.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx }/static/css/login/login.css">
	<script type="text/javascript" src="${ctx}/static/js/DD_belatedPNG.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/login/login.js"></script>
 	<!--[if IE 6]>
	<script type="text/javascript" src="js/DD_belatedPNG_0.0.8a-min.js" ></script>
	
	<script type="text/javascript">
	DD_belatedPNG.fix('#login_area_div');
	</script>
	<![endif]-->  
  </head>
  <body id="login_body">
      <div id="container">
	<div id="login_area_div">
		<form id="login" method="post" action="${ctx }/login" onkeydown="Mylogin.formsubmit(event)">
        <table id="login_area" border="0" cellspacing="0" cellpadding="0">
		<col width="80px"></col>
		<col width="260px"></col>
		<tr>
			<td class="input_title">角&nbsp;&nbsp;&nbsp;色</td>
			<td>
				<div class="input_box_div">
					 <select name="useRole" id="user_role" class="input_box easyui-combobox" data-options="editable:false, width:212, panelHeight: 'auto', onSelect: Mylogin.onChange">
                                <option value="1">系统管理员</option>
                                <option value="2">安全管理员</option>
                                <option value="3">审计管理员</option>
                                <option value="4">操作员</option>
                                <!-- <option value="5">多机管理员</option> -->
                        		</select>
				</div>

			</td>
		</tr>
		<tr>
			<td class="input_title">用户名</td>
			<td>
				<div class="input_box_div">
					
					<input class="input_box easyui-validatebox" type="text" id="name" name="name" data-options="required:true" value="SysAdmin"/>
				</div>
			</td>
		</tr>
		<tr>
			<td class="input_title">密&nbsp;&nbsp;&nbsp;码</td>
			<td>
				<div class="input_box_div">
					<input id="password" name="password" class="input_box easyui-validatebox"  type="password" data-options="required:true"/>
				</div>
			</td>
			
		</tr>
		<tr>
			<td colspan="2">
				<div id="login_btn_div" style="padding-left:70px;">
					<a href="#">
						<img id="login_btn" alt="登录" onclick="Mylogin.loginSubmit()"  src="${ctx }/static/image/modify/login_inputbox_login_btn.png" />
					</a>
				</div>
			</td>
		</tr>
	</table>
	</form>
	</div>
    </div>
    <div id="background_midblue"></div>
 
  </body>
</html>