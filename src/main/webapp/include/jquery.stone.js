/**
 * jquery.stone.js 是一个jquery扩展。
 * 它扩展了jquery实例的方法，也扩展了jquery的全局方法。
 * jquery实例方法
 * jquery全局方法放在stone命名空间下
 * @param $
 */
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
    	formInit:function(){
    		var selectFields = this.find("select");
    		$.each(selectFields,function(){
    				$(this).val($(this).attr("value"));
    		});
    	}
    	
    });  
    
    $.stone={
    		post:function(url,data,success,error,dataType){
    			$.post(url, $.param(data,true),function(repoData, textStatus, jqXHR){
    				stone.ajrp(repoData, success,error);
    			},dataType);
    		}
    };
    
 })(jQuery);  