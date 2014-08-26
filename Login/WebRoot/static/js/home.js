var home = {};

// 加固点表格的字段格式化
// 如下代码有重复，修改时注意查找
home.fmtType = function(value, row, index) {
	switch (value) {
	case 0:
		return "Oracle";
	case 1:
		return "SQL Server";
	case 2:
		return "Db2";
	case 3:
		return "MySQL";
	case 4:
		return "Cache";
	case 5:
		return "Sybase";
	case 6:
		return "达梦";
	case 7:
		return "TELNET";
	case 8:
		return "FTP";
	case 9:
		return "HTTP";
	case 11:
		return "Informix";
	case 12:
		return "Teradata";
	default:
		return "其他";
	}
};
// 动态的操作按钮
home._fmt = function(value, row, name) {
	if (value) {
		/*
		 * if((topMenus.currentDatas.indexOf("2")>=0||topMenus.currentDatas.indexOf("5")>=0))
		 * return "<a href='#' class='enforce-point' onclick=\"home._detail('" +
		 * row.id+"', '"+name+"')\"><span class='icon icon-detail'>详情<span></a>";
		 */
		if ($.inArray("2", topMenus.currentDatas) >= 0
				|| $.inArray("5", topMenus.currentDatas) >= 0)
			return "<a href='#' class='enforce-point' onclick=\"home._detail('"
					+ row.id + "', '" + name
					+ "')\"><span class='icon icon-detail'>详情<span></a>";
		else
			return "已添加";
	} else
		return "-";
};

home.fmtisAudit = function(value, row) {
	return home._fmt(value, row, 'audit');
};
home.fmtisMonitor = function(value, row) {
	return home._fmt(value, row, 'monitor');
};
home.fmtisEncrypt = function(value, row) {
	return home._fmt(value, row, 'encrypt');
};
home.fmtisRiskscan = function(value, row) {
	return home._fmt(value, row, 'riskscan');
};

home._detail = function(id, name) {
	// 操作的action地址
	var ACT_URL = {
		"riskscan" : baseUrl + 'riskscan/index?topMenusId=riskScan&id=',
		"monitor" : baseUrl + 'dbmon/index?topMenusId=monitor&id=',
		"audit" : baseUrl + 'auditFire/index?topMenusId=auditFire&id=',
		"encrypt" : baseUrl + 'encrypt/show?id='
	};

	window.location.href = ACT_URL[name] + id;
	return false;
};

// home.timeFormatter = function(value, row, index) {
// if(value){
// var d = new Date(value.time);
// return d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
// }else{
// return null;
// }
// };

home.download = function(value) {
	window.location = baseUrl + value;
};
home.urlFormatter = function(value, row, index) {
	if (value) {
		// return "<a href='"+baseUrl + value+"'>下载</a>";
		// window.location.origin+baseUrl + value
		return "<a href='" + value + "'>下载</a>";
	} else {
		return "";
	}
};

home.statusFormatter = function(value, row, index) {
	switch (value) {
	case 0:
		return '未处理';
	case 1:
		return '已处理';
	default:
		return '';
	}
};

home.typeFormat = function(value, row, index) {
	switch (value) {
	case 0:
		return '磁盘';
	case 1:
		return 'CPU';
	default:
		return '';
	}
};

home.timeFormat = function(value, row, index) {
	var d = new Date(value.time);
	return d.getFullYear()
			+ "-"
			+ ((d.getMonth() + 1) < 10 ? "0" + (d.getMonth() + 1) : (d
					.getMonth() + 1)) + "-"
			+ (d.getDate() < 10 ? "0" + d.getDate() : d.getDate()) + "&nbsp;"
			+ (d.getHours() < 10 ? "0" + d.getHours() : d.getHours()) + ":"
			+ (d.getMinutes() < 10 ? "0" + d.getMinutes() : d.getMinutes())
			+ ":"
			+ (d.getSeconds() < 10 ? "0" + d.getSeconds() : d.getSeconds());
};

home.levelFormat = function(value, row, index) {
	switch (value) {
	case 0:
		return '警告';
	case 1:
		return '严重';
	case 2:
		return '致命';
	default:
		return '';
	}
};
master.ready.push(function() {
	$("#high_warn_table").datagrid();
}, function() {
	$("#warn_table").datagrid();
}, function() {
	$("#enforcepoint_table").datagrid();
}, function() {
	$("#saved_report_table").datagrid();
}, function() {
	$("#dgWarn").datagrid();
}, function() {
	$("#machine_config").panel();
});
