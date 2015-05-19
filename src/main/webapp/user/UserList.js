$(document).ready(function() {
	
	var editPanel = $('#userDetail');
	$.parser.parse(editPanel); 
	
	//编辑
	$("#read").on('click',function(){
		var row  = easyutils.getGridSelectedRow('#userList');
		if(!row){
			return false;
		}
		editPanel.load(appPath+"/user/UserRead.do?id="+row.id);
		
	});
	
	//新增
	$("#add").on('click',function(){
		var groupId = $("#groupTree").tree("getSelected").id;
		editPanel.load(appPath+"/user/UserAddInit.do?defaultGroupId="+groupId);
		
	});
	
	
});