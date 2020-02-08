<%@ page contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

<head>
<title>MemberList</title>
<meta charset="EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(function() {
		$("#ajax_click").click(function(/* event */) {
							//event.preventDefault(); 	
							var category = $("select[name='category']").val();
							var content = $("input[name='content']");
							$.ajax({
									type : 'get',
									url : 'search.do',
									data : {
											category : category,
											content :content.serialize().replace(/%/g,'%25'),
											currentPage : 1
											
										},
								dataType : 'text',
								success : function(result) {
											var arr = JSON.parse(result);
											var str = '';
											var birth ='';
											$.each(arr,function(i) {
													birth = arr[i].m_birth.split(' ')[0];
													str += '<tr>';
													str += "<td><a href='read.do?id="+ arr[i].m_id+ "'>"+ arr[i].m_id+ "</a></td>";
													str += '<td>'+ arr[i].m_name+ '</td>';
													str += '<td>'+ birth+ '</td>';
													str += '<td>'
															+ arr[i].m_age
															+ '</td>';
													str += '<td>'
															+ arr[i].m_phone
															+ '</td>';
													str += '<td>'
															+ arr[i].m_email
															+ '</td>';
													str += '<td>'
															+ arr[i].m_nickname
															+ '</td>';
													str += '<td>'
															+ arr[i].m_grade
															+ '</td>';
													str += '</tr>';
															});
											if (str != '') {
												$("#ajaxTable").html(str);
											} else {
												str += "<td colspan='10'>조회하신 정보가 유효하지 않습니다.</td>";
												$("#ajaxTable").html(str);
											}
										}
									});
						});
	});
</script>
<!--  <script type="text/javascript">
	var searchRequest = new XMLHttpRequest();
	function searchFunction() {
		searchRequest.open("Post", "search.do?category="+encodeURIComponent(document.getElementById("category").value)+"&content="+encodeURIComponent(document.getElementById("content").value), true);
		searchRequest.onreadystatechange = searchProcess;
		searchRequest.send(null);
	}
	function searchProcess() {
		var table = document.getElementById("ajaxTable");
		table.innerHTML = "";
		if (searchRequest.readyState == 4 && searchRequest.status == 200) {
			var object = eval('('+searchRequest.responseText+')');
			var result = object.result;
			for (var i = 0 ; i < result.length ; i++) {
				console.log("1.for st");
				var row = table.insertRow(0);
				for (var j = 0 ; j < result[i].length ; j++ ) {
					console.log("2.for st")
					var cell = row.insertCell(j);
					cell.innerHTML = result[i][j].value;
				}
			}
		}
	}
	window.onload = function() {
		searchFunction();
	}
</script> -->
</head>

<body>
	<a href="../">Home</a>
	<hr>
	<h1>관리자페이지</h1>
	<hr>

	<div class="container">
		<div class="form-group row">
			<div class="col-xs-5">
				<select id="category" style="font-size: 17px;" name="category">
					<option value="0">등급별</option>
					<option value="1">아이디</option>
					<option value="2">이름</option>
					<option value="3">닉네임</option>
					<option value="4">전화번호</option>
					<option value="5">이메일</option>
					
				</select> 
			</div>
			<div class="col-xs-5">
				<input  class="form-control" type="text" name="content" />
			</div>
			<div class="col-xs-2">
				<button class="btn btn-primary" id="ajax_click">검색</button>
			</div>

			<!-- 		<div class="col-xs-5">
				<input class="form-control" id="content" name="content"
					onkeyup="searchFunction()" type="text" size="20">
			</div>

			<div class="col-xs-2">
				<button id="ajaxClick" class="btn btn-primary"
					onclick="searchFunction();" type="button">검색</button>
			</div> -->
		</div>
		<table class="table"
			style="text-align: center; border: 1px solid; #dddddd">
			<thead>
				<tr>
					<th style="background-color: #fafafa; text-align: center;">
						아이디</th>
					<th style="background-color: #fafafa; text-align: center;">이름
					</th>
					<th style="background-color: #fafafa; text-align: center;">
						생년월일</th>
					<th style="background-color: #fafafa; text-align: center;">나이
					</th>
					<th style="background-color: #fafafa; text-align: center;">
						전화번호</th>
					<th style="background-color: #fafafa; text-align: center;">
						이메일</th>
					<th style="background-color: #fafafa; text-align: center;">
						닉네임</th>
					<th style="background-color: #fafafa; text-align: center;">등급
					</th>
				</tr>
			</thead>
			<!-- 	<tbody id="ajaxTable">
			</tbody>  -->
			<tbody id="ajaxTable">
				<c:choose>
					<c:when test="${not empty list}">
						<c:forEach items="${list }" var="dto">
							<tr>
								<td><a href="read.do?id=${dto.m_id }">${dto.m_id }</a></td>
								<td>${dto.m_name }</td>
								<%-- 		<c:set value="${dto.m_birth }" var="birth" />
								<c:set value="${fn:split(birth,'-')}" var="arr" />
								<c:set value="${arr[0] }" var="year" />
								<c:set value="${arr[1] }" var="month" />
								<c:set value="${fn:split(arr[2],' ')[0] }" var="date" />
								<td>${year }년${month }월${date}일</td> --%>
								<c:set value="${fn:split(dto.m_birth,' ')[0]}" var="birth" />
								<td>${birth }</td>
								<td>${dto.m_age }</td>
								<td>${dto.m_phone }</td>
								<td>${dto.m_email }</td>
								<td>${dto.m_nickname }</td>
								<td>${dto.m_grade }</td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</tbody>
		</table>
	</div>
	<!-- 	
		<select name="category">
			<option value="0">등급별</option>
			<option value="1">아이디</option>
			<option value="2">이름</option>
			<option value="3">닉네임</option>
			<option value="4">전화번호</option>
			<option value="5">이메일</option>
		</select> <input type="text" name="content" />
		<button id="ajax_click">검색</button>
		
	<hr>
	 -->
	<%-- 	<table border="1">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>생년월일</th>
				<th>나이</th>
				<th>전화번호</th>
				<th>이메일</th>
				<th>닉네임</th>
				<th>등급</th> 
			</tr>
		</thead> 
		
		<tbody id="test">
			<c:choose>
			<c:when test="${not empty list}"> 
				<c:forEach items="${list }" var="dto">
					<tr>
						<td><a href="read.do?id=${dto.m_id }">${dto.m_id }</a></td>
						<td>${dto.m_name }</td>
						<c:set value="${dto.m_birth }" var="birth"/>
						<c:set value="${fn:split(birth,'-')}" var="arr"/>
						<c:set value="${arr[0] }" var="year"/>
						<c:set value="${arr[1] }" var="month"/>
						<c:set value ="${fn:split(arr[2],' ')[0] }" var="date"/>
						<td>${year }년${month }월${date}일</td>
						<c:set value="${fn:split(dto.m_birth,' ')[0]}" var="birth"/>
						<td>${birth }</td>
						<td>${dto.m_age }</td>
						<td>${dto.m_phone }</td>
						<td>${dto.m_email }</td>
						<td>${dto.m_nickname }</td>
						<td>${dto.m_grade }</td>
					</tr>
				</c:forEach>
			</c:when> 
			</c:choose>
		</tbody>
		</table>
		
		<br>
		<c:choose>
			<c:when test="${empty pv }">
				
			</c:when>
			
			<c:otherwise>	
				<jsp:include page="../page/member_search_paging.jsp"/>
			</c:otherwise>
		</c:choose>
		<br> --%> 
		<div class="table"
			style="text-align: center; border: 1px solid; #dddddd">
		<jsp:include page="../page/paging.jsp"/>
		</div>

</body>
</html>