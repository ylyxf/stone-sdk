<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>stone-sdk</title>

<%@ include file="/include/include.jsp"%>
<link rel="stylesheet" href="${appPath}/include/highlight.js/default.min.css">
<script type="text/javascript" src="${appPath}/include/highlight.js/highlight.pack.js"></script>
<script type="text/javascript" src="${appPath}/code/ViewCode.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
			<table id="menu" class="table">
			<c:forEach items="${codes}" var="item">
				<tr>
					<td>
						<a href="#${item.key}">${item.key}</a>
					</td>
				</tr>			
			</c:forEach>
			</table>
			</div>
			</div>
		<div class="row">
			<c:forEach items="${codes}" var="item">
			<div class="col-md-12">
				<code id="${item.key}" >${item.key}</code>
				<a href="#menu" class="pull-right">回到顶端</a>
				</div>
			<div class="col-md-12">
				<pre><code >${item.value}</code></pre>
			</div>
			</c:forEach>
		</div> 
		<!-- end  row  -->
	</div>
	<!-- end  container  -->
</body>
</html>
