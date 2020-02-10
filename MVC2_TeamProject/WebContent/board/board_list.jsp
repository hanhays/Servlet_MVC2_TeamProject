<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<head>
	<title>Bulletin Board</title>
	<meta charset="EUC-KR"> <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		var currentPage = null;
		function searchFunction() {
			var category = $("select[name='category']").val();
			var content = $("input[name='content']");
			if (currentPage == null) {
				currentPage = 1;
			}
		$.ajax({
			type : 'post',
			url  : 'list.do',
			date : {
				currentPage : currentPage,
				category    : category, 
				content		: content.serialize().replace(/%/g,'%25')
			},
			dataType		:'text',
			success			:function(result) {
				var arr = JSON.parse(result);
				var str = '';
				var writtenday = '';
				$.each(arr[0],function(i) {
					writtenday = arr[0][i].b_day.split(' ')[0];
					str += '<tr>';
					str += '<td>'+ arr[0][i].b_num	+'</td>';
					str += "<td>"
					for(i=1; i<=arr[0][i].b_indent; i++){
						str +="&nbsp;&nbsp;"
					}
					str += "<a href='read.do?b_num="+arr[0][i].b_num+"'>"+arr[0][i].b_title+"</a>"
					str += "</td>";
					str += '<td>'+ arr[0][i].m_id	+'</td>';
					str += '<td>'+ writtenday		+'</td>';
					str += '<td>'+ arr[0][i].m_cnt	+'</td>';
					str += '</tr>';
				});
				
				if (str != '') {
					$("#ajaxTable").html(str);
				} else {
					str += "<td colspan='10'>조회하신 정보가 유효하지 않습니다.</td>"
				}
				
				var amount = arr[1][0].amount;
				var totalPage = arr[1][0].totalPage;
				var currentPage = arr[1][0].currentPage;
				var starNum = arr[1][0].startNum;
				var endNum = arr[1][0].endNum;
				var beginPage = arr[1][0].beginPageNum;
				var stopPage = arr[1][0].stopPageNum;
				
				$("#paging").html(" ");
				var msg = "";
				for(i=beginPage; i<=stopPage; i++){
					if (i == currentPage) {
						msg += "<strong onclick='getCurrentPage("+i+")' style='color:red; font-size:15px;'>"+i+"</strong>";
					} else {
						msg += "<strong onclick='getCurrentPage("+i+")' style='color:black; font-size:15px;'>"+i+"</strong>";
					}
				}
				$("#paging").append(msg);
			}
		});
	}
	function getCurrentPage(currentPages) {
		currentPage=currentPages;
		searchFunction();
	}
	</script>


</head>

<body>
<br>
<a href='createui.do' class="btn btn-primary">글쓰기</a>
<hr>
<h1 style="text-align: center">게시판</h1>
<br>

	<div class="container">
		<div class="form-group row">
			<div class="col-xs-5">
				<select id="category" name="category">
					<option value="0">글번호로</option>
					<option value="1">글제목으로</option>
					<option value="2">글쓴이로</option>
					<option value="3">글쓴날짜로</option>
				</select>
			</div>
			
			<div class="col-xs-5">
				<input class="form-control" id="content" name="content" type="text"/>
			</div>
			
			<div class="col-xs-2">
				<button class="btn btn-primary" onclick="searchFunction();" type="button">검색</button>
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
			
			<tbody id="ajaxTable">
			<c:choose>
				<c:when test="${not empty b_list }">
					<c:forEach 	items="${b_list }" var="dto">
						<tr>
							<td>${dto.b_num }</td>
							<td>
								<c:forEach begin="1" end="${dto.b_indent }">&nbsp;&nbsp;</c:forEach>
								<a href="read.do?b_num=${dto.b_num }">${dto.b_title }</a>
							</td>
							<td>${dto.m_id }</td>
							<c:set value="${fn:split(dto.b_day, ' ')[0] }" var="writtenday"/>
							<td>${writtenday}</td>
							<td>${dto.b_cnt }</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
			</tbody>
		</table>
	</div>

<br>

</body>

</html>