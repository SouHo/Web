/*给form表单添加获取表单内容并封装成对象的方法*/
$.fn.csbitSerialize = function(){
	var data={};
	var array = this.serializeArray();
	$.each(array, function(i, object){
		if(data[object.name]) {
			if(!$.isArray(data[object.name])) data[object.name] = [data[object.name]];
			data[object.name].push(object.value);
		}else data[object.name] = object.value;
	});
	return data;
};
/**
 * 将object对象转换换成uri串  $.param 方法转换object中的数组有问题
 */
$.csbitParam = function(object){
	var params = "_rand_csbit="+Math.random();
	for(var i in object){
		if($.isArray(object[i])){
			$.each(object[i],function(index, value){
				params += "&"+i+"="+value;
			});
		}else{
			params += "&"+i+"="+object[i];
		}
	}
	return params;
};
/*修改easyui的相关控件的默认设置*/
$.fn.panel.defaults.onClose = function(){
	$("div").remove(".tooltip");
};
$.fn.window.defaults.onClose = function(){
	$("div").remove(".tooltip");
};
$.fn.dialog.defaults.onClose = function(){
	$("div").remove(".tooltip");
};
$.extend($.fn.datagrid.defaults, {striped:true,fit:true});//设置表格行奇偶行
$.fn.tabs.defaults.fit = true;//设在tabs自动充满父容器

$.extend($.fn.panel.methods, {
	init: function(jq, object, options){
		if(object){
			if($.type(object)=='object') options = object;
			else if($.type(object)!='string') object = 'dialog';
		}
		if(!options) options = {};
		if(jq.attr("class")=="csbit-dialog"){
			jq.removeClass("csbit-dialog");
			var types = jq.find("[csbit-type]");
			var size = types.size();
			$.each(types, function(index, value){
				var target = $(value);
				switch(target.attr("csbit-type")){
				case "validatebox": target.validatebox();break;
				case "combobox": target.combobox(); break;
				case "combotree": target.combotree(); break;
				case "numberbox": target.numberbox(); break;
				case "numberspinner": target.numberspinner();break;
				case "datetimebox": target.datetimebox(); break;
				case "datagrid": target.datagrid(); break;
				case "treegrid": target.treegrid(); break;
				case "tree": target.tree(); break;
				case 'panel': target.panel(); break;
				case "searchbox": target.searchbox();break;
				case "linkbutton": target.linkbutton(); break;
				case "combogrid": target.combogrid();break;
				case "tabs": target.tabs();break;
				}
				target.removeAttr("csbit-type");
			});
			switch(object){
			case "dialog": jq.dialog(options); break;
			case "window": jq.window(options); break;
			case "panel": jq.panel(options); break;
			default: jq.dialog(options);
			}
		}
		jq.panel("open");
		return jq;
	}
});

$.extend($.fn.validatebox.defaults.rules, {
		pwdStrong : {
			validator : function(value, param) {
				var reg = /[a-z|A-Z]+\d+/;
				if (reg.test(value)) {
					var len = value.length;
					//指定最大、最小长度时判断 
					if(param && param.length == 2)
						return len >= param[0] && len <= param[1];
					else
						return true;
				}
			},
			message : '密码格式不正确'
		},
		equals : {
			validator : function(value, param) {
				return value == $(param[0]).val();
			},
			message : '密码输入不一致'
		},
	    ip: {   
	        validator: function(value, param){
	        	var reg = /(^25[0-5]|^2[0-4]\d|^1\d{2}|^[1-9]\d|^\d)(\.(25[0-5]|2[0-4]\d|1\d{2}|[1-9]\d|\d)){3}$/;
	        	return reg.test(value);
	            //return /^\d+.\d+.\d+.\d+$/.test(value);
	        },   
	        message: 'IP地址格式错误！'  
		},email :{
	        validator: function(value, param){   
	            return /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(value);   
	        },   
	        message: 'EMAIL地址格式错误!'   
	    },pattern :{
	    	validator: function(value, param){
	    		var reg = new RegExp(param[0]);
	    		return reg.test(value);
	    	},
	    	message: "格式不正确！"
	    },lt: { //小于
	    	validator : function(value, param) {
	    		var test = $(param[0]).val();
				return value.length <=test.length && value < test;
			},
			message : '输入不合法'
	    },name:{
	    	validator:function(value,param){
	    		return /^[\u4E00-\u9FA5\uf900-\ufa2d\w]+/.test(value);
	    	},
	    	message:'格式不正确！'
	    }
	});
	
	$.ajaxSetup({//设置异步请求默认的处理
		complete: function(XMLHttpRequest, textStatus){
			var value = XMLHttpRequest.responseText;
			if(value != undefined && value.indexOf("太长时间没有操作，请重新登录！")>-1){
				parent.location.href = baseUrl;//重定向到登录页面
				return;
			}
		}
	});
//扩展Array的方法，以便兼容ie
Array.prototype.indexOf = function(target, compare){
	var temp;
	var index = -1;
	$.each(this, function(i, object){
		if(compare) temp = object[compare];
		else temp = object;
		if(temp == target) {
			index = i;
			return;
		};
	});
	return index;
};
var master = {
	engine:{run:0,stop:1,notAvailable:2,error:3,
		getText:function(value){
			switch(value){
			case 0: return "正在运行";
			case 1: return "停止";
			case 2: return "异常";
			case 3: return "错误";
			}
		}
	},
	g_pwdUrl : '',
	vflag : 'relogin',
	ajax: {
		beforeText: "正在处理，请稍候......",
		successText: "操作成功！",
		errorText: "操作过程出错，此次操作无效！",
		data:{}
	},
	saveText:{
		beforeText: "正在保存，请稍候......",
		successText: "保存成功！",
		errorText: "保存失败！"
	},
	deleteText:{
		beforeText: "正在删除，请稍候......",
		successText: "删除成功！",
		errorText: "删除失败！"
	},
	addText:{
		beforeText: "正在添加，请稍候......",
		successText: "添加成功！",
		errorText: "添加失败！"
	},
	stopText:{
		beforeText: "正在停止，请稍候......",
		successText: "停止成功！",
		errorText: "引擎未能正常停止！"
	},
	startText:{
		beforeText: "正在启动，请稍候......",
		successText: "启动成功！",
		errorText: "引擎未能正常启动！"
	},
	ready:[function(){
		$("#center").layout({fit:true});
		$("form").attr("autoComplete","off");
	}],
	//计划删除此方法 由$().csbitSerialize()代替
	paramToObject : function(params){
		var data = decodeURIComponent(params).split("&");
		var object = {};
		var value = "";
		$.each(data, function(index, param){
			value = param.split("=");
			if(!object[value[0]]) object[value[0]] = value[1];
			else {
				if($.type(object[value[0]])!='array')  object[value[0]] = [object[value[0]]];
				object[value[0]].push(value[1]);
			}
		});
		return object;
	},
	_ajax:function(data){
		var options = {};
		$.extend(options, master.ajax, data, master[data.textType]);
		if($.type(options.data)=="object"){//将参数解析成url $.param()方法解析属性值为Array时有问题
			var tar = options.data;
			var temp = "_t_=112";
			for(var i in tar){
				var val = tar[i];
				if($.isArray(val)){
					$.each(val, function(index, tem){
						temp += "&"+i+"="+tem;
					});
				}else{
					temp += "&"+i+"="+val;
				}
			}
			options.data = temp;
		}
		$.ajax({
			url: options.url,
			data: options.data,
			type: options.type,
			dataType: 'json',
			beforeSend: function(){
			/*	$("body").append("<div class='datagrid-mask' style='display:block;'>" +
						"<div class='datagrid-mask-msg' style='display: block; left: 50%; margin-left: -84px;'>sdfaf</div></div>");*/
				$.messager.progress({
					text:options.beforeText
				});
			},
			complete: function(XMLHttpRequest, textStatus){
				$.messager.progress("close");
				/*$(".datagrid-mask").remove();*/
				var value = XMLHttpRequest.responseText;
				if(value.indexOf("太长时间没有操作，请重新登录！")>-1){
					parent.location.href = baseUrl;//重定向到登录页面
					return;
				}
				switch(XMLHttpRequest.status){
					case 200:
						if(options.showResponse && value!="success") options.successText = value;
						$.messager.show({
							title: "提示",
							msg: options.successText,
							timeout: 3000,
							showType:'slide'
						});
						if($.isFunction(options.complete)) value = options.complete(value);
						if($.isFunction(options.success)) options.success(value);
						break;
					case 401:
					case 404:
					case 405:
						if($.isFunction(options.complete)) options.complete(value);
						$.messager.alert("警告", value, "warn");
						break;
					case 500: 
						if($.isFunction(options.error)) options.error(value);
						$.messager.alert("错误", "<h2>"+options.errorText+"</h2>"+value, "error");
						break;
				}
			}
		});
	},
	postJSON:function(ajax){
		ajax.type = 'post';
		master._ajax(ajax);
	},
	getJSON:function(ajax){
		ajax.type= 'get';
		master._ajax(ajax);
	},
	cookie:function(name, value, options) {//options{expires:1(天),path:'',domain:'',secure:''}
	    if (typeof value != 'undefined') { // name and value given, set cookie
	        options = options || {};
	        if (value === null) {
	            value = '';
	            options.expires = -1;
	        }
	        var expires = '';
	        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
	            var date;
	            if (typeof options.expires == 'number') {
	                date = new Date();
	                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
	            } else {
	                date = options.expires;
	            }
	            expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
	        }
	        var path = options.path ? '; path=' + options.path : '';
	        var domain = options.domain ? '; domain=' + options.domain : '';
	        var secure = options.secure ? '; secure' : '';
	        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
	    } else { // only name given, get cookie defaul return null
	        var cookieValue = null;
	        if (document.cookie && document.cookie != '') {
	            var cookies = document.cookie.split(';');
	            for (var i = 0; i < cookies.length; i++) {
	                var cookie = jQuery.trim(cookies[i]);
	                // Does this cookie string begin with the name we want?
	                if (cookie.substring(0, name.length + 1) == (name + '=')) {
	                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
	                    break;
	                }
	            }
	        }
	        return cookieValue;
	    }
	},
	resize:function(){
		$("table[data-options]").datagrid('resize');
	},
	documentReady:function(callbacks,index){
		if(!$.isArray(callbacks) || callbacks.length==0){
			return ;
		}
		if(!index) index = 0;
		
		if(!callbacks[index]) {
			callbacks = null;
			return;
		}
		var outId = 0;
		outId = setTimeout(function(){
			callbacks[index]();
			index++;
			master.documentReady(callbacks,index);
			clearTimeout(outId);
		},0);
	},
	confirm:function(options){
		if($.isFunction(options)) options = {sure:options};
		var object = $.extend({title:'提示',text:'确定删除？',sure:function(){},cancel:function(){}},options);
		$.messager.confirm(object.title,object.text,function(r){
			if(r){
				object.sure();
			}else{
				object.cancel();
			}
		});
	},
	//美化<input type="file">
	fileUp:function(){
		var upfile = $("input[type='file']");
		upfile.addClass("fileinput");
		upfile.wrap("<div class='uploader'></div>");
		upfile.after("<span class='filename' style='-moz-user-select: none;'>选择文件</span><span class='action' style='-moz-user-select: none;'>选择文件</span>");
		upfile.bind('change',function(){
			$(this).next("span.filename").html(this.value);
		});
	}
};

//首页共享的修改密码处理
master.showMasterPwd = function(data) {
	master.g_pwdUrl = baseUrl+'user/modifyPassword';
	master.vflag = 'relogin';
	$('#frm_changePwd').form('clear');
	$('#userName').val(data);
	$("#dlgChagePassword").dialog("init");
};
//用户管理使用
master.showChangPwd = function(data,flag){
	master.g_pwdUrl = baseUrl + "management/user/change";
	master.vflag = flag;
	$('#frm_changePwd').form('clear');
	$('#userName').val(data);
	$('#dlgChagePassword').dialog("init");
};

//修改密码对话框提交
master.changepwd = function() {
	var f = $("#frm_changePwd");
	if (f.form('validate')) {
		f.form('submit', {
			url : master.g_pwdUrl, // the URL!!!!
			success : function(data) {
				if(data=="密码修改成功！") $("#dlgChagePassword").dialog('close');
				$.messager.alert("提示", data, "info", function(){
					if(data=="密码修改成功！"){ 
						if(master.vflag=='relogin') window.location.href= baseUrl+"logout";
					}
				});
			}
		});
	}
};

$(function(){

	if($(window).height() <600) $("body").height(600);
	if($(window).width() < 1200) $("body").width(1200);
	
	$(window).resize(function() {
		var height = $(window).height();
		var width = $(window).width();
		$("body").width(width<1200 ? 1200:width);
		$("body").height(height<600 ? 600:height);
		master.resize();
	});
	
	$("body.easyui-layout").layout('resize');
	
	master.fileUp();
	master.ready.push(function(){
		$.fn.datagrid.defaults.onResize = function(width, height){
			cosole.log(this);
		if(height>10) $(this).datagrid('resize', {height:10});
		};
	});
	master.documentReady(master.ready);
	master.ready = null;
});

