
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">日志查询界面</h1>
	<table border="1" align="center" bordercolor="red" bgcolor="#14E2DC"
		cellspacing="0">
		<thead>
			<tr>
				<th>id</th>
				<th>username</th>
				<th>operation</th>
				<th>method</th>
				<th>params</th>
				<th>time</th>
				<th>ip</th>
			</tr>
		</thead>
		<tbody id="tbodyid">
			<td colspan="7">數據加載中</td>
		</tbody>
	</table>
	<script type="text/javascript" src="../bower_components/jquery/dist/jquery.min.js"></script>
	<script type="text/javascript">
		$(function() {
			console.log("xxx");
			doGetObjects();
		})
		function doGetObjects() {
			var params = {pageCurrent:1};
			var url = "doFindPageObjects.do";
			$.ajax({
				url : url,
				data : params,
				success : function(result) {
					console.log(result);
					doHandleResponseResult(result);
				}
			});
		}
		function doHandleResponseResult(result) {
			if (result.state == 1) {
				doSetTableBodyRows(result.data.records);
			} else {
				alert(result.message);
			}
		}
		function doSetTableBodyRows(records) {
			var tbody = $("#tbodyid");
			tbody.empty();
			for ( var i in records) {
				var tr = $("<tr></tr>");
				var tds = doCreatTds(records[i]);
				tr.append(tds);
				tbody.append(tr);
			}
		}
		function doCreatTds(row) {
			var tds = "<td>" + row.id + "</td>" +
					  "<td>" + row.username+ "</td>" + 
					  "<td>" + row.operation + "</td>" + 
					  "<td>" + row.method + "</td>" + 
					  "<td>" + row.params + "</td>"+ 
					  "<td>" + row.time + "</td>" + 
					  "<td>" + row.ip + "</td>"
			return tds;
		}
	</script>

</body>
</html>