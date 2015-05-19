$(document).ready(function() {
	
	$("#roleAddForm").on('submit',function(){
		return $(this).form("validate");
	});
	
});