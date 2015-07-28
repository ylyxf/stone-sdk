/*
 * the stone object must impl the methods below:
 * 
 * ajrp:ajax repos 
 * 
 * */
var stone = {

	ajrp : function(data, success, error) {
		var ajaxResponse = data;
		if (!ajaxResponse.type) {
			ajaxResponse = $.parseJSON(data);
		}
		if (ajaxResponse.type == 'success') {
			$.messager.show({
				title : ajaxResponse.title,
				msg : ajaxResponse.message,
				timeout : 2000
			});
			if (success) {
				success(ajaxResponse);
			}
		} else if (ajaxResponse.type == 'error') {
			$.messager.alert(ajaxResponse.title, ajaxResponse.message);
			if (error) {
				error(ajaxResponse);
			}
		} else if (ajaxResponse.type == 'sessionTimeOut') {
			// 跳转到登陆页面
			window.location.href = appPath + "/login/Login.jsp";
		}
	}
};