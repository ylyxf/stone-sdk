$(document).ready(function() {
	//给每个导航按钮设置响应
    $("#indexNavBar").find("span").each(function(index,element){
    	$(this).click(function(){
    		//加载内容
    		$("#workspace").load($(this).attr("data-url"));
    	});
    });
    //默认选中第一个导航按钮
    $("#indexNavBar").find("a:first").trigger("click");
});

	 
