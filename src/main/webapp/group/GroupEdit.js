$(document).ready(function() {
	$.parser.parse('#groupDetail'); 
	
	var groupEditForm = $('#groupEditForm');
	
	$('#cancelBtn').click(function(){
		var id = $('#id').val();
		$("#groupDetail").load(appPath+"/group/GroupRead.do?id="+id);
	});
	
	$('#saveBtn').click(function(){
		groupEditForm.submit();
	});
	
	groupEditForm.form({
		success : function(data){
			easyutils.ajaxResponseHandler(data,function(repo){
				var currentNode = $('#groupTree').tree('getSelected');
				console.log(repo.data.text);
				$('#groupTree').tree('update', {
					target: currentNode.target,
					text:repo.data.text
			    });
				$("#groupDetail").load(appPath+"/group/GroupRead.do?id="+currentNode.id);
			});
		}
	});
	
});