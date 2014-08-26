<%@ page language="java" pageEncoding="UTF-8"%>
</head>
<body class="easyui-layout">
<div data-options="region: 'north', split: false, border: true" style="height: 58px; overflow:hidden;">
	<c:if test="${sessionScope.currentUser !=null }">
	<div id="header" class="header">
				<div style="width:100%;overflow: hidden;" class="fr">
					<div style="height:100%; float: left; margin:5px 0 0 15px;"  class="fr">
						<img src="${ctx}/static/image/modify/header_banner.png" style="border:0px" />
					</div>
					<div style="float:right;width:90%">
					<div id="u_k" style="height:100%; float: left; padding-top: 20px; display: none;">
					 <h2 style="color: rgb(218, 11, 11); font-size:1.3em;">未发现UKey, 请检查服务器上的UKey接入是否正确！</h2>
					</div>
					<div class="fn-clear logout-bg">
						<div id="secLogout" class="fr">
							<a href="${ctx }/logout" class="easyui-linkbutton" data-options="iconCls:'icon-logout', plain: true" style="color: #f6f6f6;">锁定/退出</a>						
						<!--<a href="${ctx }/logout">
							<img src="${ctx}/static/image/modify/header_logoutbar_exit_off.jpg" />
						</a>-->
						</div>
						<div class="fr">
							<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-modify-password', plain: true" onclick="master.showMasterPwd('${sessionScope.currentUser.name}')"  style="color: #f6f6f6;">修改密码</a>
							<!--<img src="${ctx}/static/image/modify/header_logoutbar_password_off.jpg" onclick="master.showMasterPwd('${sessionScope.currentUser.name}')" />-->
						</div>
						<div class="fr uspan" style="color: #3571a5;">
							<span class="fontset">${sessionScope.currentUser.userRole.name}：${sessionScope.currentUser.name	}</span>
						</div>						
					</div>
					<div class="top-nav fn-clear">
					<div style="float:left" class="fn-clear">
						<ul id="top_menus" class="fn-clear" style="float:left">
										
						</ul>
					</div>
					</div>
					</div>
				</div>
	</div>

	<script type="text/javascript" src="${ctx}/static/js/topMenus.js"></script>
	<script type="text/javascript">
		<!--
			topMenus.currentDatas = ${sessionScope.topMenus};
			master.ready.push(function(){
			topMenus.init('${param.topMenusId}');
			$.getJSON(baseUrl+"all/uk", function(data){
				if(data){
					$("#u_k").show();
				}
				});
		});
		-->
	</script>
	</c:if>	
		
	
		<div id="dlgChagePassword" class="csbit-dialog"
		data-options="title:'修改密码', width: 280, modal:true, resizable:true,closed:true,
			buttons: [{	text: '确定',iconCls: 'icon-ok',handler:master.changepwd}]">
			<form id="frm_changePwd" method="post">
				<table class="form-table" width="90%" border="0px" cellpadding="0px" cellspacing="0px">
					<tr>
						<td width='80'>用户名称</td>
						<td><input type="text" name="userName" id="userName" csbit-type="validatebox" readOnly/></td>
					</tr>
					<tr>
						<td>原始密码</td>
						<td><input name="oldPassword" type="password"
							csbit-type="validatebox" validType="pwdStrong[${sessionScope.cfg_min}, ${sessionScope.cfg_max}]" focus="true" /></td>
					</tr>
					<tr>
						<td>更新密码</td>
						<td><input name="newPassword" type="password"
							id='newPassword' csbit-type="validatebox" validType="pwdStrong[${sessionScope.cfg_min}, ${sessionScope.cfg_max}]" /></td>
					</tr>
					<tr>
						<td>确认密码</td>
						<td><input name="reNewPassword" type="password"
							csbit-type="validatebox" validType="equals['#newPassword']" /></td>
					</tr>
					<tr>
						<td colspan="2" class="demo-info"><div
								class="demo-tip icon-friendly-reminder"></div>密码需以字母开头,并要包含数字.长度在${sessionScope.cfg_min}和${sessionScope.cfg_max}之间.</td>
					</tr>
				</table>
			</form>
		</div>
</div>
<div data-options="region: 'center', split: true, border: true" >