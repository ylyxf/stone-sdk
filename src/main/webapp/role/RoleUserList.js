$(document).ready(function() {
	$("#add").click(function(){
		$("#roleUserForm").form('submit', {
			url:appPath+"/role/addUserToRole.do",
			success:function(data){
				easyutils.ajaxResponseHandler(data,function(repo){
					//重新加载
					$('#roleUserList').datagrid('load',{roleId:$("#roleId").val()});
				});
			}
		});
	});
	
	$("#remove").click(function(){
		$.messager.confirm('提醒', '确认要删除选中的记录吗？', function(confirmed){
			if(!confirmed){
				return false;
			}
			$("#roleUserForm").form('submit', {
				url:appPath+"/role/removeUserFromRole.do",
				success:function(data){
					easyutils.ajaxResponseHandler(data,function(repo){
						//重新加载
						$('#roleUserList').datagrid('load',{roleId:$("#roleId").val()});
					});
				}
			});
		});
	});
	
});