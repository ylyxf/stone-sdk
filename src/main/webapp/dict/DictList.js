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
		$.messager.confirm('提醒', '确认要删除选中的记录吗？', function(confirmed){
			if(!confirmed){
				return false;
			}
			 $("#dictListForm").form('submit', {
				url:appPath+"/dict/dictDelete.do",
				success:function(data){
					var data = data.trim();
					var dataObject = $.parseJSON(data);
					if(dataObject.error){
						 $.messager.alert('错误',dataObject.message);
						 return false;
					}else{
						//删除成功
						$.messager.show({
							 title:'提醒',
							 msg:'成功删除'+data+'条数据',
							 timeout:3000,
							 showType:'slide'
						});
					}
					
					//重新加载
					$('#dictList').datagrid('load',{});
				}
			});
		});
	});
	
	
});