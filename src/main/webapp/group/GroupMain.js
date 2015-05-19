$(document).ready(function() {
	
	$("#groupTree").tree({
		onClick: function(node){
			$("#groupDetail").load(appPath+"/group/GroupRead.do?id="+node.id);
		}
	});

});

