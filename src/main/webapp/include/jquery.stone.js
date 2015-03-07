(function($){
    $.fn.extend({
    		
    	//序列化form到Object
    	formToObject:function(excludeFields) {

    		if(excludeFields){
    			excludeFields = ','+excludeFields+',';
    		}
    		
    		var o = {};
    		var a = this.serializeArray();
    		$.each(a, function() {
    			
    			if(excludeFields && excludeFields.indexOf(','+this.name+',')!=-1){
    				return;
    			}
    				
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
    	},
    	
    	//设置一些非value的html控件
    	initFormValues:function(){
    		var selectFields = this.find("select");
    		$.each(selectFields,function(){
    				$(this).val($(this).attr("value"));
    		});
    	}
    	
    	
    });  
    
    $.stone={
    	notify:function(data){
			new PNotify({
	            title:data.title,
	            text: data.message,
	            type: data.type,
	            delay:1500,
	        });
		} 
			
    }
     
 })(jQuery);  