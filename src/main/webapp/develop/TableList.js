$(document).ready(function() {
	
	$("#initProject").on('click',function(){
		$.post(appPath+"/develop/initProject.do",{},function(result){
		     
		});
	});
	
	$("#generateCode").on('click',function(){
		var params = $("#tableListForm").formToObject();
		//获得到选中的表名 
		var rows = $('#tableList').datagrid('getSelections');
		params.tableNameList = easyu.datagrid.row2Ids(rows,'name');
		
		$.stone.post(appPath+"/develop/generateCode.do",params,function(result){
			console.log(result);
		});
	});
	
	$("#query").on('click',function(){
		//查询
		$('#tableList').datagrid('load',{
			name: $("#name").val() 
		});
	});
	
});

	 
