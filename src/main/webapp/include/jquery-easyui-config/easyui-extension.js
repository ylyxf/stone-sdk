var easyutils = (function() {

	var ajaxResponseHandler = function(data, success, error) {
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
	};

	/**
	 * datagrid的工具
	 */
	var datagrid = {
		/**
		 * 将rows的id取出为数组
		 */
		row2Ids : function(rows,field) {
			var ids = Array();
			for ( var i in rows) {
				if(field){
					ids[i] = rows[i][field];
				}else{
					ids[i] = rows[i].id;
				}
				
			}
			return ids;
		},

		selectedIds : function(gridSelector) {
			var rows = $(gridSelector).datagrid('getSelections');
			return this.row2Ids(rows);
		},
		singleRow : function(gridSelector) {
			var grid = $(gridSelector);
			var rows = grid.datagrid('getSelections');
			if (rows.length == 0) {
				$.messager.alert('错误', '请至少选择一条记录');
				return false;
			} else if (rows.length != 1) {
				$.messager.alert('错误', '请只选择一条记录');
				return false;
			}
			return rows[0];
		}
	};

	var combobox = {
		optionOnly : function(jqObj) {
			jqObj.combobox('textbox').bind('blur', function() {
				var inputV = jqObj.combobox('getText');
				var datas = jqObj.combobox('getData');
				var dataProperty = jqObj.combobox('options').textField;
				var size = datas.length;
				if (inputV == "" || size == 0) {
					return;
				} else {
					for (var i = 0; i < size; i++) {
						if (inputV == datas[i][dataProperty]) {
							return;
						}
					}
					jqObj.combobox('clear');
				}
			});
		},

	}

	return {
		ajrp : ajaxResponseHandler,
		combobox : combobox,
		datagrid : datagrid
	}
})();

var easyu = easyutils;