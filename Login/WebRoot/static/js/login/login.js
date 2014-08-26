var Mylogin = {
		loginSubmit:function() {
			master.postJSON({
				url:baseUrl+'login',
				beforeText:'正在登录',
				successText:'登录成功',
				errorText:'登录失败',
				data:$("#login").csbitSerialize(),
				success:function(data){
					location.href = baseUrl+'index.jsp'
				}
			});
		},
		fromsubmit:function(e){
			 e= e || window.event;
			 var code = e.keyCode || e.charCode;
			 if(code==13){return loginSubmit();}
		},
		
		onChange:function(newValue){
			var input = $("#name");
			input.attr("readOnly", true);
			$('#password').focus();
			switch(newValue.value){
				case '1': 
					input.val("SysAdmin");
					break;
				case '2': 
					input.val("SecAdmin");
					break;
				case '3': 
					input.val("Auditor");
					break;
				case '5': 
					input.val("MoreAdmin");
					break;
				case '4': 
					input.attr("readOnly", false);
					input.focus();
					input.val("");
					
			}
			master.cookie('userRole',newValue.value,{expire:1});
		}
};
$(function (){
	var role = master.cookie('userRole');
	if(role==null) role = '1';
	$("#user_role").combobox('setValue',role);
	Mylogin.onChange({value:role});
	$("#name").attr("readOnly", true);
	$('#password').focus();
});