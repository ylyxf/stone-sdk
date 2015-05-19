var bsutils = (function(){
	
	var ajaxResponseHandler = function(data,success,error){
		var ajaxResponse = data;
		if(!ajaxResponse.type){
			ajaxResponse = $.parseJSON(data);
		}
		if(ajaxResponse.type=='success'){
			new PNotify({
	            title:ajaxResponse.title,
	            text: ajaxResponse.message,
	            type: ajaxResponse.type,
	            delay:1500,
	        });
			if(success){
				success(ajaxResponse);
			}
		}else if(ajaxResponse.type=='error'){
			bootbox.alert(ajaxResponse.message);
			if(error){
				error(ajaxResponse);
			}
		}else if(ajaxResponse.type=='sessionTimeOut'){
			//跳转到登陆页面
			window.location.href=appPath+"/login/Login.jsp";
		}
	};
	
	return {
		ajaxResponseHandler:ajaxResponseHandler
	}
})();