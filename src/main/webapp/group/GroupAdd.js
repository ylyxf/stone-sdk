$(document).ready(function() {
	$.parser.parse('#groupDetail'); 
	
	$('#cancelBtn').click(function(){
		var id = $('#parentId').val();
		$("#groupDetail").load(appPath+"/group/GroupRead.do?id="+id);
	});
	

	$('#saveBtn').click(function(){
		groupAddForm.submit();
	});
	
	
	var groupAddForm = $('#groupAddForm');
	groupAddForm.form({
		success : function(data){
			easyutils.ajaxResponseHandler(data,function(repo){
				var parentNode = $('#groupTree').tree('getSelected');
				var node = repo.data;
				$('#groupTree').tree('append', {
			        parent:parentNode.target,
			        data:[node]
			    });
				$('#groupTree').tree('select',$('#groupTree').tree('find',node.id).target);
				$("#groupDetail").load(appPath+"/group/GroupRead.do?id="+repo.data.id);
			});
		}
	});
	
	
});