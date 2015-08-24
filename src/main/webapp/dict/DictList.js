$(document).ready(function() {

	//配置
	$("#items").on('click',function(){
		var rows = $('#dictList').datagrid('getSelections');
		if( rows.length!=1){
			$.messager.alert('错误','请只选择一条记录');
			return false;
		}
		window.location.href=appPath+"/dict/DictItemList.do?dictCode="+rows[0].code;
	});
	
	//编辑
	$("#edit").on('click',function(){
		var rows = $('#dictList').datagrid('getSelections');
		if(rows.length!=1){
			$.messager.alert('错误','请只选择一条记录');
			return false;
		}
		window.location.href=appPath+"/dict/DictEditInit.do?code="+rows[0].code;
	});
	
	
	//查看
	$("#read").on('click',function(){
		var rows = $('#dictList').datagrid('getSelections');
		if(rows.length!=1){
			$.messager.alert('错误','请只选择一条记录');
			return false;
		}
		window.location.href=appPath+"/dict/DictRead.do?code="+rows[0].code;
	});
	
	//删除
	$("#remove").on('click',function(){
		var config = {
			columnName:"code",
			submitName:"codeList",
			confirm:'确实要删除选中的字典吗？'
		};
		stone.postGrid("#dictList",appPath+"/dict/dictDelete.do",config);
	});
	
	
});