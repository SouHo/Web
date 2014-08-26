var leftMenus = {
		datas:[],
		selectedId: "",
		params:{}
};

leftMenus.init = function(){
	var menusHtml = "";
	$.each(this.datas, function(index, object){
		if(!object.children) {
			var url=baseUrl;
			var query = {};
			query.leftMenusId = object.id;
			$.extend(query, leftMenus.params, object.params);
			url += object.url+"?"+$.param(query);
			menusHtml += "<ul><li id='_123_"+object.id+"'><a href='"+url+"'><span class='icon fontTwo '>"+object.text+"</span></a></li></ul>"+"\n";
		}
		else {
		menusHtml += "<div class='panel-header' id='_123_"+object.id+"'><span class='panel-title icon fontOne '>"+object.text+"</span><span class='panel-tool accordion-collapse icon'></span></div>"+"\n";
		menusHtml += "<ul>"+"\n";
		$.each(object.children, function(i, obj){
			var url=baseUrl;
			var query = {};
			query.leftMenusId = obj.id;
			$.extend(query, leftMenus.params, obj.params);
			url+= obj.url+"?"+$.param(query);
			menusHtml += "<li id='_123_"+obj.id+"'><a href='"+url+"'><span class='icon fontTwo '>"+obj.text+"</span></a></li>"+ "\n";
		});
		menusHtml += "</ul>";
		}
		});
		$(".left-nav").append(menusHtml);
		
		$(".left-nav").children("div").click(function(){
			var ul = $(this).next("ul");
			if(ul.is(":visible")){
				ul.slideUp("slow");
				$(this).find('span.panel-tool').addClass("accordion-expand");
				$(this).find('span.panel-tool').removeClass("accordion-collapse");
			}else{
				ul.slideDown("slow");
				$(this).find('span.panel-tool').removeClass("accordion-expand");
				$(this).find('span.panel-tool').addClass("accordion-collapse");
			}
		});
		
		$("#_123_"+this.selectedId).addClass("current");
};

master.ready.push(function(){
	leftMenus.init();
});