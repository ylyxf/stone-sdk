/**
 * @module stone-easyui
 *
 */

//在stone使用easyui作为表现层时，可以使用这个模块提供的功能，以简化操作。
/**
 * @class stone
 * @static
 */
var stone = {};

/**
 * @method form2Object
 * @description 将表单转换为Object对象
 * @param formIdSelector
 *            {String} form的Id选择器
 * @return {Object} form对象
 */
stone.form2Object = function(formIdSelector) {
	var form = $(formIdSelector);
	var o = {};
	var a = form.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
}

/**
 * @method responseHandler
 * @description 响应处理器，后台处理成功，则notify，并调用传入的操作成功函数；后台处理失败，则alert，并调用传入的操作失败函数。特别的，当后台传回的数据有_quietOnSuccess属性，则不notify,直接调用success
 * @param data
 *            {Object||String} 返回的数据
 * @param data.type
 *            {String} success -处理成功 error-处理失败
 * @param data.notifyOnSuccess
 *            {Boolean} true-给出notify,false-不要noitify
 * @param data.title
 *            {String} 后台返回的消息标题
 * @param data.message
 *            {String} 后台返回的消息
 * @param data.data
 *            {Object} 后台返回的数据对象
 * @param success
 *            {Function} 后台处理成功，则调用此函数
 * @param error
 *            {Function} 后台处理失败，则调用此函数
 * 
 */
stone.responseHandler = function(data, success, error) {
	var ajaxResponse = data;
	if (typeof ajaxResponse == 'string') {
		ajaxResponse = $.parseJSON(data);
	}
	
	if (ajaxResponse.type == 'success') {
		// 提醒
		if (ajaxResponse.notifyOnSuccess) {
			$.messager.show({
				title : ajaxResponse.title,
				msg : ajaxResponse.message,
				timeout : 2000
			});
		}
		return success ? success(ajaxResponse) : true;
	} else if (ajaxResponse.type == 'error') {
		// 警告
		$.messager.alert(ajaxResponse.title, ajaxResponse.message);
		return error ? error(ajaxResponse) : false;
	} else if (ajaxResponse.type == 'sessionTimeOut') {
		// 跳转到登陆页面
		$.messager.alert("登录超时", "您长时间未操作系统导致超时，请重新登录系统。", function() {
			window.location.href = appPath + "/login/Login.jsp";
		});
	}
};

/**
 * @method post
 * @description 对jquery的$.post封装，能对ajax的返回值有预处理的功能（调用responseHandler）实现
 * @param url {String} 请求的url路径
 * @param data {Object} 要传送到后台的数据
 * @param success {Function} 后台处理成功时，调用的函数
 * @param error {Function} 后台处理失败时，调用的函数 
 * @param notifyOnSuccess {Boolean} 后台处理成功时，是否给出提醒Notify
 */
stone.post = function(url, data, success, error, notifyOnSuccess) {
	return $.post(url, $.param(data, true), function(repoData) {
		if (notifyOnSuccess) {// 如果成功,则安静地调用success函数
			repoData.notifyOnSuccess = true;
		}
		stone.responseHandler(repoData, success, error);
	});
};

/**
 * @method postForm
 * @description 使用ajax的方式提交表单,如果成功则提示.
 * @param formIdSelector {String} form选择器
 * @param url {String} 请求的后台路径
 * @param success {Function} 后台处理成功时，调用的函数
 * @param error {Function} 后台处理失败时，调用的函数 
 *  
 */
stone.postForm = function(formIdSelector,url,success,error){
	var data = stone.form2Object(formIdSelector);
	return stone.post(url,data,success,error,true);
};

/**
 * @method postFormQuiet
 * @description 使用ajax的方式提交表单,即使成功也不提示.
 * @param formIdSelector {String} form选择器
 * @param url {String} 请求的后台路径
 * @param success {Function} 后台处理成功时，调用的函数
 * @param error {Function} 后台处理失败时，调用的函数 
 *  
 */
stone.postFormQuiet = function(formIdSelector,url,success,error){
	var data = stone.form2Object(formIdSelector);
	return stone.post(url,data,success,error,false);
};

/**
 * @method postGrid
 * @description 发送表格数据到后台
 * @param gridIdSelector {String} 表格选择器
 * @param config {Object} 参数配置
 * @param [config.columnName=id] {String} 取哪一列的值
 * @param [config.submitName=config.columnName] {String} 用什么名字提交
 * @param [config.formIdSelector=undefined] {String} 表单选择器
 * @param [config.confirm=undefined] {String} 提交前提醒
 * @param [config.singleSelect=false] {Boolean} 仅能选择一条记录
 * @param [config.notifyOnSuccess=true] {String},成功时给出提醒
 * @param [config.reloadOnSuccess=true] {String},成功时刷新grid
 * @param url {String} 后台路径
 * @param success {Function} 后台处理成功时，调用的函数
 * @param error {Function} 后台处理失败时，调用的函数 
 */
stone.postGrid=function(gridIdSelector,url,config,success,error){
	var defaultConfig = {
			columnName:'id',
			submitName:undefined,
			formIdSelector:undefined,
			confirm:undefined,
			singleSelect:false,
			notifyOnSuccess:true,
			reloadOnSuccess:true
	}
	$.extend(defaultConfig,config);
	config = defaultConfig

	var data = {};
	if(config.formIdSelector){//如果有表单选择器，则带上
		data = stone.form2Object(config.formIdSelector);
	}
	
	var fieldName = config.submitName?config.submitName:config.columnName;
	data[fieldName]=stone.gridSelectedValue(gridIdSelector,config.columnName,config.singleSelect);
	if(config.confirm){
		$.messager.confirm('确认', config.confirm, function(r){
			if (r){
				return stone.post(url,data,function(repoData){
					if(config.reloadOnSuccess){
						$(gridIdSelector).datagrid('reload');
					}
				},error,config.notifyOnSuccess);
			}else{
				return false;
			}
			
		});
	}else{
		return stone.post(url,data,function(repoData){
			if(config.reloadOnSuccess){
				$(gridIdSelector).datagrid('reload');
			}
		},error,config.notifyOnSuccess);
	}
};

/**
 * @method gridSelectedValue
 * @description 获得Grid选中的值
 * @param gridIdSelector {String} 表格选择器
 * @param columnName {String} 取哪一列的值
 * @param singleSelected {String} 是否单选
 * */
stone.gridSelectedValue=function(gridIdSelector,columnName,singleSelected){
	var rows = $(gridIdSelector).datagrid('getSelections');
	
	if (rows.length == 0) {
		$.messager.alert('错误', '没选择任何记录！');
		return false;
	}
	if(singleSelected&&rows.length > 1){
		$.messager.alert('错误', '请只选择一条记录！');
		return false;
	}
	if(singleSelected){
		return rows[0][columnName];
	}else{
		var returnValue = Array();
		for ( var i in rows) {
			returnValue[i] = rows[i][columnName];
		}
		return returnValue;
	}
};

/**
 * @method mvcAcceptable
 * @description 去掉object中的Array格式，换为array[1]、array[2]...等Key
 * @param obj {Object} 对象
 * */
stone.mvcAcceptable = function(obj){
	var toAppend = {};
	$.each(obj,function(key,val){
		if($.isArray(val)){
			$.each(val,function(subKey,subVal){
				toAppend[key+'['+subKey+']']=subVal;
			});
			//从obj中移除
			delete obj[key];
		}
	});
	$.extend(obj,toAppend);
	return obj;
}