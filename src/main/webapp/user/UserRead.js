$(document).ready(function() {
	
	var userDetail = $('#userDetail');
	$.parser.parse(userDetail); 
	
	$('#deleteBtn').click(function(){
		$.messager.confirm("提醒","确定要删除当前用户吗？",function(result){
			if(!result){
				return;
			}
			var groupId = $("#groupTree").tree("getSelected").id;
			$('#userForm').form('submit', {
				url:appPath+"/user/userDelete.do",
				queryParams:{currentGroupId:groupId},
				success:function(data){
					easyutils.ajaxResponseHandler(data,function(repo){
						$("#userDetail").load(appPath+"/user/UserList.do?groupId="+groupId);
					});
				}
			});
		});
	});
	
	$('#removeBtn').click(function(){
		$.messager.confirm("提醒","确定要移除当前用户吗？",function(result){
			if(!result){
				return;
			}
			var groupId = $("#groupTree").tree("getSelected").id;
			$('#userForm').form('submit', {
				url:appPath+"/user/userRemove.do",
				queryParams:{currentGroupId:groupId},
				success:function(data){
					easyutils.ajaxResponseHandler(data,function(repo){
						var groupId = $("#groupTree").tree("getSelected").id;
						$("#userDetail").load(appPath+"/user/UserList.do?groupId="+groupId);
					});
				}
			});
		});
		
	});
	
	$('#editBtn').click(function(){
		var id = $('#id').val();
		$("#userDetail").load(appPath+"/user/UserEditInit.do?id="+id);
	});
	
	$('#cancelBtn').click(function(){
		var currentGroupId = $("#groupTree").tree("getSelected").id;
		$("#userDetail").load(appPath+"/user/UserList.do?groupId="+currentGroupId);
	});
	
});