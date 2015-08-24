$(document).ready(function() {
	
	//定位开始面板到合适位置
	var beginBtn = $("#homeBegin");
	var top = beginBtn.offset().top+beginBtn.height()+4;//留一点空隙  
	var left = beginBtn.offset().left; 
	var beginPanel = $("#beginPanel");
	beginPanel.dialog('move',{left:left,top:top});
	
	//点击开始按钮
	beginBtn.on('click', function() {
		
		if(beginPanel.dialog('options').closed){
			beginPanel.dialog('open');
		}else{
			beginPanel.dialog('close');
		}
	});
	 
	$(document).on('click',function(e){
		if(e.target.id!='homeBegin'){
			beginPanel.dialog('close');
		}
	});
	
});
	 
