var easyutils = (function(){
	var comboboxOptionOnly=function(jqObj){
		jqObj.combobox('textbox').bind('blur',function(){
			var inputV = jqObj.combobox('getText');
			var datas = jqObj.combobox('getData');
			var dataProperty = jqObj.combobox('options').textField;
			var size = datas.length;
			if(inputV==""||size==0){
				return;
			}else{
				for(var i =0;i<size;i++){
					if(inputV==datas[i][dataProperty]){
						return;
					}
				}
				jqObj.combobox('clear');
			}
		});
	};
	
	
	return {
		comboboxOptionOnly:comboboxOptionOnly
	}
})();