$(document).ready(function() {
	
	var userDetail = $('#userDetail');
	$.parser.parse(userDetail); 
	
	var userForm = $('#userForm');
	userForm.form({
		success : function(data){
			//加载产品只读页面
			easyutils.ajaxResponseHandler(data,function(repo){
				userDetail.load(appPath+"/user/UserRead.do?id="+repo.data.id);
			}); 
		}
	});
	
	$('#saveBtn').click(function(){
		if(userForm.form('enableValidation').form('validate')){
			userForm.submit();
		}
	});
	
	$('#cancelBtn').click(function(){
		var id = $('#id').val();
		userDetail.load(appPath+"/user/UserRead.do?id="+id); 
	});
	
});