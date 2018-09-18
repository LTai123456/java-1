<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form enctype="application/x-www-form-urlencoded"></form>
	<h1 align="center">日志查询界面</h1> 
	<h1>time:<%=new Date() %></h1>
	<table border="1" align="center" bordercolor="red" bgcolor="#14E2DC" cellspacing="0" >
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
     <script type="text/javascript">
     		window.onload=function(){
    	 		doGetObject();		
     		}
     		function doGetObject(){
     			//1.
     			var xhr=new XMLHttpRequest();
     			console.log(xhr);
     	     	//2.
     	     	xhr.onreadystatechange=function(){
     	     		if(xhr.readyState==4&&xhr.status==200){
     	     			//.console.log(xhr.responseText);
     	     			doHandleResponseResult(xhr.responseText);
     	     		}
     	     	}
     	     	//
     	     	doSendPostRequest(xhr);
     		}
     		function doSendGetRequest(xhr){
     			var url="doFindPageObjects.do?pageCurrent=1";
     	     	//3.
     	     	xhr.open("GET",url,true);
     	     	//4.
     	     	xhr.send(null);
     		}
     		function doSendPostRequest(xhr){
     			var url="doFindPageObjects.do";
     	     	//3.
     	     	xhr.open("POST",url,true);
     	     	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
     	     	//4.
     	     	xhr.send("pageCurrent=1");
     		}
     		function doHandleResponseResult(resultStr){
     			var result=JSON.parse(resultStr);
     			
     			if(result.state==1){
     				//console.log(result);
     				doSetTableBodyRows(result.data.records);
     			}else{
     				alert(result.message);
     			}	
     		}
     		function doSetTableBodyRows(records){
     			var tbody=document.getElementById("tbodyid");
     			tbody.innerHTML="";
     			for (var i = 0; i < records.length; i++) {
     				//console.log(records);
					var tr=document.createElement("tr");
					doSetTableBodyCols(records[i],tr);
					tbody.appendChild(tr);
				}
     		}
     		function doSetTableBodyCols(value,tr){                
     			var td1=document.createElement("td");
     			td1.innerHTML=value.id;
     			tr.appendChild(td1);
     			var td2=document.createElement("td");
     			td2.innerHTML=value.username;
     			tr.appendChild(td2);   			
     			var td3=document.createElement("td");
     			td3.innerHTML=value.operation;
     			tr.appendChild(td3);
     			var td4=document.createElement("td");
     			td4.innerHTML=value.method;
     			tr.appendChild(td4);
     			var td5=document.createElement("td");
     			td5.innerHTML=value.params;
     			tr.appendChild(td5);
     			var td6=document.createElement("td");
     			td6.innerHTML=value.time;
     			tr.appendChild(td6);
     			var td7=document.createElement("td");
     			td7.innerHTML=value.ip;
     			tr.appendChild(td7);
     		}
     </script>
</body>
</html>