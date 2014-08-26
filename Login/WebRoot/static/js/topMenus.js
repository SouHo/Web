var topMenus = {
		datas:[{},
		              {id:"index", iconCls: "icon-index", url: "all/index", text: "首页"},
		              {id:"enforcepoint", iconCls: "icon-enforce-point", url: "enforcepoint/index", text: "加固点"},
		              {id:"auditFire", iconCls: "icon-audit-fire", url: "auditFire/index", text: "审计防火墙"},
		              //利用编号6的权限处理Web审计
		              {id:"waa", iconCls: "icon-web-audit", url: "waa/index", text: "WEB审计"},
		              {id:"monitor", iconCls: "icon-monitor", url: "dbmon/index", text: "状态监控"},
		              {id:"riskScan", iconCls: "icon-risk-scan", url: "riskscan/index", text: "风险扫描"},
		              //{},
		              {},//Sysadmin
		              {id:"alarm", iconCls: "icon-warn", url: "search/index", params:{page:'warn'}, text: "告警"},
		              {id:"search", iconCls: "icon-search", url: "search/index", params:{page:'search'}, text: "检索"},
		              {id:"report", iconCls: "icon-report", url: "report/index", params:{page:'index',pid:20}, text: "报表"},
		              {id:"log", iconCls: "icon-log", url: "log/index", text: "日志"},
		              {id:"archive", iconCls: "icon-archive", url: "archive/index", text: "归档"},
		              {id:"management", iconCls: "icon-management", url: "management/index", text: "管理"},
		              {id:"system", iconCls: "icon-system", url: "system/index", text: "系统"},
		              {},{},{}],
		currentDatas:[]
};

topMenus.init = function(menuId){
	if(!menuId) menuId = 'index';
	var menusHtml = "";
	$.each(topMenus.currentDatas, function(index, object){
		var data = topMenus.datas[object];
		if(data.text){
			var url=baseUrl+data.url;
			var query = {topMenusId:data.id};
			$.extend(query, data.params);
			url += "?"+$.param(query);
			/*if(index>0) menusHtml += "<li id='_1_top_"+data.id+"'><span class='border-left'></span>" +
			"<a href='"+url+"'><span class='"+data.iconCls+"'></span>" + data.text +"</a>";*/
			menusHtml += "<li id='_1_top_"+data.id+"'>" +
			"<a href='"+url+"'><span class='"+data.iconCls+"'></span>" + data.text +"</a>";
		}
		
		
	});
	$("#top_menus").append(menusHtml);
	$("#_1_top_"+menuId).addClass("current");	
};
