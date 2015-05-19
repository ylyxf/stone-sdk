$(document).ready(function() {
	
	$("#dictAddForm").on('submit',function(){
		return $(this).form("validate");
	});
	
});