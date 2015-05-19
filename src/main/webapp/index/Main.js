$(document).ready(function() {
	$(".iconNav").click(function() {
		var content = '<iframe id="主页_FRAME" src="'+$(this).attr('link')+'" scrolling="no" frameborder="0" class="tab-frame"></iframe>';
		var params = {
				title:$(this).text(),
				content:content,
				closable:true
		};
		
		window.top.index.openTab(params);
	});
});
