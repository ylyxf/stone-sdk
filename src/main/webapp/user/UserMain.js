$(document).ready(function() {
	
	$("#groupTree").tree({
		onClick: function(node){
			$("#userDetail").load(appPath+"/user/UserList.do?groupId="+node.id);
		}
	});

});

