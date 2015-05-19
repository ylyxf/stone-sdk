$(document).ready(function() {
	
	$("#dictItemAddForm").on('submit',function(){
		return $(this).form("validate");
	});
	
});