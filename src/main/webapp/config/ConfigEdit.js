$(document).ready(function() {
	
	var editPanel = $('#configEditPanel');
	$.parser.parse(editPanel); 
	
	
	var configForm = $('#configForm');
	configForm.form({
		success : function(data){
			//加载产品只读页面
			var ajaxResponse = $.parseJSON(data);
			if(ajaxResponse.type=='success'){
				$.messager.show({
	                title:ajaxResponse.title,
	                msg:ajaxResponse.message,
	                timeout:2000
	            });
				editPanel.load(appPath+"/config/ConfigRead.do?classCode="+ajaxResponse.data);
			}else if(ajaxResponse.type='error'){
				$.messager.alert(ajaxResponse.title,ajaxResponse.message);
			}
		}
	});
	
	$('#saveBtn').click(function(){
		if(configForm.form('enableValidation').form('validate')){
			configForm.submit();
		}
	});
	
});