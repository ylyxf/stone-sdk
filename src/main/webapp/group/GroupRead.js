$(document).ready(function() {
	
	$.parser.parse('#groupDetail');
	var groupReadForm = $('#groupReadForm');
	
	$('#editBtn').click(function(){
		var id = $('#id').val();
		$("#groupDetail").load(appPath+"/group/GroupEditInit.do?id="+id);
	});
	
	$('#addBtn').click(function(){
		var id = $('#id').val();
		$("#groupDetail").load(appPath+"/group/GroupAddInit.do?id="+id);
	});
	
	$('#deleteBtn').click(function(){
		groupReadForm.submit();
	});
	
	groupReadForm.form({
		success : function(data){
			easyutils.ajaxResponseHandler(data,function(repo){
				var currentNode = $('#groupTree').tree('getSelected');
				$('#groupTree').tree('remove', currentNode.target);
				$("#groupDetail").html('');
			});
		}
	});
	
	
});

