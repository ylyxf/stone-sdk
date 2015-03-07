$.fn.serializeToObject = function(excludeFields) {

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
};