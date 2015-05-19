$(document).ready(function() {
	//给每个导航按钮设置响应
	/*
    $("#mainTabs").tabs({
    	onSelect:function(title,index){
    		var frame = $('#'+title+'_FRAME');
    		frame.attr('src',frame.attr('initUrl'));
    	}
    }); 
    $("#mainTabs").tabs('select','主页');*/
});

var index ={
	openTab :function(params){
		$("#mainTabs").tabs('add',params);	
	} 	
}

	 
