$(document).ready(function() {
	$("#saveBtn").click(function(){
		$('#tableConfigForm').ajaxSubmit({
			url:appPath+"/code/saveCode.do",
			success:function(ajaxResponse){
				$.stone.notify(ajaxResponse);
			}
		});
		return false;
	});
	
});

	 
