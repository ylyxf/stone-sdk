$(document).ready(function() {
	
	$('#roleOperationTable').datagrid({
		toolbar: '#tb'
	});
	
	$(".operationCheckbox").click(function(){
		var operationCode = $(this).val();
		var url = appPath+"/role/removeOperationFromRole.do";
		if($(this).is(':checked')){
			url = appPath+"/role/addOperationToRole.do"; 
		} 
		var data = {
			easyuiform:true,
			roleId:$('#roleId').val(),
			operationCode:operationCode	
		};
		$.post(url,data,function(data){
			easyutils.ajaxResponseHandler(data);
		});
	});
	 
});