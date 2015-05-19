$(document).ready(function() {
	
	var editPanel = $('#configEditPanel');
	
	$('#configClassList').datalist({
		onClickRow:function(index,row){
			editPanel.load(appPath+"/config/ConfigRead.do?classCode="+row.value);
		}
	});
	
});

	 
