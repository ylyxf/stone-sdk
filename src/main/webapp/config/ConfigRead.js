$(document).ready(function() {
	
	var editPanel = $('#configEditPanel');
	$.parser.parse(editPanel); 
	
	
	// 进入更新页面
	$("#editBtn").click(function() {
		var classCode = $("#classCode").attr("value");
		editPanel.load(appPath+"/config/ConfigEditInit.do?classCode="+classCode);
	});
});