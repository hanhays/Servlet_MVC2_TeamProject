<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>

<head>
	<title>list</title>
	<meta charset="EUC-KR"> <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		var listRequest = new XMLHttpRequest(); 
		function listFunction() {
			listRequest.open("Post", "../list.do?m_id="+encodeURIcomponent(document.getElementById("m_id").value), true);
			listRequest.onreadystatechange = listProcess;
			listRequest.send(null);
		}
		function listProcess() {
			var table = document.getElementById("ajaxTable");
			table.innerHTML = "";
			if(listRequest.readyState == 4 && listRequest.staus == 200){
				var object = eval('('+listRequest.responseText+')');
				var result = object.result;
				for (var i = 0; i <result.length; i++){
					var row = table.insertRow(0);
					for (var j = 0; j< result[i].length; j++){
						var cell = row.insertCell(j);
						cell.innerHTML = result[i][j].value;
					}
				}
			}
		}
		window.onload = function() {
			listFunction();
		}
	</script>
	
</head>

<body>
<a href='createui.do'>글쓰기</a>
<hr>
<h1>게시판</h1>
<br>

<br>
	<div class="container">
	
		<div class="form-group row pull-right">
			<div class="col-xs-8">
				<input class="form-control" id="m_id" onkeyup="listFunction()" type="text" size="20">
			</div>
			
			<div class="col-xs-2">
				<button class="btn btn-primary" onclick="listFunction();" type="button">검색</button>
			</div>
		</div>
		
		<table class="table" style="text-align: center; border: 1px solid; #dddddd">
			<thead>
				<tr>
					<th style="background-color: #fafafa; text-align: center;">글번호</th>
					<th style="background-color: #fafafa; text-align: center;">글제목</th>
					<th style="background-color: #fafafa; text-align: center;">글쓴이</th>
					<th style="background-color: #fafafa; text-align: center;">쓴날짜</th>
					<th style="background-color: #fafafa; text-align: center;">조횟수</th>
				</tr>
			</thead>
			<tbody id="ajaxTable"></tbody>
		</table>
	</div>
	
	

<%-- <table border="1">

	<thead>
		<tr>
			<th>글번호</th>
			<th>글제목</th>
			<th>글쓴이</th>
			<th>쓴날짜</th>
			<th>조횟수</th>
			<th>원글번호</th>
			<th>답글번호</th>
			<th>들여쓰기</th>
		</tr>
	</thead>
	
	<tbody>
	<c:forEach items="${pv.b_list }" var="dto">
		<tr>
			<td>${dto.b_num }</td>
			<td>
				<c:forEach begin="1" end="${dto.b_indent }">
				&nbsp;&nbsp;
				</c:forEach>
				<a href='read.do?num=${dto.b_num}'>${dto.b_title }</a></td>
			<td>${dto.m_id }</td>
			<td>${dto.b_day }</td>
			<td>${dto.b_cnt }</td>
			<td>${dto.b_root }</td>
			<td>${dto.b_step }</td>
			<td>${dto.b_indent }</td>
		<tr>
	</c:forEach>
	</tbody>

</table> --%>
<br>


</body>

</html>