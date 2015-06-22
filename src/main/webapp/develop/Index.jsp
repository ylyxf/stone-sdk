<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<title>stone</title>
<%@ include file="/include/include.jsp"%>
<script type="text/javascript" src="${appPath}/develop/Index.js"></script>
</head>
<body style="overflow: scroll;">

		 <table data-toggle="table" data-url="${appPath}/develop/tableListData.do"
		  	data-sort-name="name" data-sort-order="asc"
		 	data-pagination="true" data-side-pagination="server" data-page-list="[5, 10, 20, 50, 100, 200]">
		    <thead>
		        <tr>
		            <th data-field="name"  data-sortable="true">表名</th>
		            <th data-field="comment">注释</th>
		        </tr>
		    </thead>
		</table>
</body>
</html>
