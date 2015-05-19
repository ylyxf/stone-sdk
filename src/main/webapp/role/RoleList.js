$(document).ready(function() {

	//查看
	$("#read").on('click',function(){
		var row  = easyutils.getGridSelectedRow('#roleList');
		if(!row){
			return false;
		}
		window.location.href=appPath+"/role/RoleRead.do?id="+row.id;
		
	});
	
	//分配权限
	$("#assignOperation").on('click',function(){
		var row  = easyutils.getGridSelectedRow('#roleList');
		if(!row){
			return false;
		}
		window.location.href=appPath+"/role/RoleOperationList.do?id="+row.id;
	});
	
	//分配人员
	$("#assignUser").on('click',function(){
		var row  = easyutils.getGridSelectedRow('#roleList');
		if(!row){
			return false;
		}
		window.location.href=appPath+"/role/RoleUserList.do?id="+row.id;
	});
	
	
	//删除
	$("#remove").on('click',function(){
		$.messager.confirm('提醒', '确认要删除选中的记录吗？', function(confirmed){
			if(!confirmed){
				return false;
			}
			
			$("#roleListForm").form('submit', {
				url:appPath+"/role/roleDelete.do",
				success:function(data){
					easyutils.ajaxResponseHandler(data,function(repo){
						//重新加载
						$('#roleList').datagrid('load',{});
					});
				}
			});
			 
		});
	});
});