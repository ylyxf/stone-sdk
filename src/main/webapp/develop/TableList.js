$(document).ready(function() {
	
	$("#initProject").on('click',function(){
		$.post(appPath+"/develop/initProject.do",{},function(result){
		     
		});
	});
	
	$("#generateCode").on('click',function(){
		var config={
				columnName:"name",
				submitName:"tableNameList",
				formIdSelector:"#tableListForm",
				confirm:"确定要生成代码吗？"
		}
		stone.postGrid('#tableList',appPath+"/develop/generateCode.do",config)
	});
	
	$("#query").on('click',function(){
		//查询
		$('#tableList').datagrid('load',{
			name: $("#name").val() 
		});
	});
	
});

	 
