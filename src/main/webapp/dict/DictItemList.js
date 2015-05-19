$(document).ready(function() {
	
	
	//编辑
	$("#edit").on('click',function(){
		var rows = $('#dictItemList').datagrid('getSelections');
		if(rows.length!=1){
			$.messager.alert('错误','请只选择一条记录');
			return false;
		}
		window.location.href=appPath+"/dict/DictItemEditInit.do?id="+rows[0].id;
	});
	
	//查看
	$("#read").on('click',function(){
		var rows = $('#dictItemList').datagrid('getSelections');
		if(rows.length!=1){
			$.messager.alert('错误','请只选择一条记录');
			return false;
		}
		window.location.href=appPath+"/dict/DictItemRead.do?id="+rows[0].id;
	});
	
	//删除
	$("#remove").on('click',function(){
		$.messager.confirm('提醒', '确认要删除选中的记录吗？', function(confirmed){
			if(!confirmed){
				return false;
			}
			 $("#dictItemListForm").form('submit', {
				url:appPath+"/dict/dictItemDelete.do",
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
					$('#dictItemList').datagrid('load',{});
				}
			});
		});
	});
	
	
});