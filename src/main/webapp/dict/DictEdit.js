$(document).ready(function() {

	$("#dictEditForm").on('submit',function(){
		return $(this).form("validate");
	});
	
});
