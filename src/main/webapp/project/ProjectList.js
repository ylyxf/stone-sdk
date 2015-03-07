$(document).ready(function() {
	
	//初始化项目
	$(".prepareDatabase").click(function(){
		if(!confirm("stone-sdk会默认您填写的数据库信息指向的是一个空的数据库并对它进行初始化。\r\n如果数据库中有其它数据，可能会被修改或删除，确认要初始化数据库吗？")){
			return false;
		}
		var prepareDbUrl = appPath+"/project/prepareDatabase.do?id="+$(this).attr("projectId");
		$.post(prepareDbUrl,function(data){
			 $.stone.notify(data);
		});
	});
	
	//初始化项目
	$(".initProject").click(function(){
		 
		var initUrl = appPath+"/project/projectInit.do?id="+$(this).attr("projectId");
		$.post(initUrl,function(data){
			 $.stone.notify(data);
		});
	});
	
	
	//删除项目
	$(".deleteProject").click(function(){
		if(!confirm("确定要删除吗?")){
			return false;
		}
		window.location.href = appPath+"/project/projectDelete.do?id="+$(this).attr("projectId");
	});
});

	 
