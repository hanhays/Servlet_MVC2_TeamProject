<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri ="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
 
<head>
<title>MemberList</title>
<meta charset="EUC-KR">
<meta charset="utf-8">
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
			var content = $("input[name='content']").val();
			console.log(content);
			$.ajax({
				type : 'post',
				url : 'search.do',
				data : {
					category : category,
					content : content
				},
				dataType : 'text',
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				success : function(result) {
					var arr = JSON.parse(result);
					var str = '';
					$.each(arr, function(i) {
						str += '<tr>';
						str += "<td><a href='read.do?id="+ arr[i].m_id +"'>"+arr[i].m_id +"</a></td>";
						str += '<td>' + arr[i].m_name + '</td>';
						str += '<td>' + arr[i].m_birth + '</td>';
						str += '<td>' + arr[i].m_age + '</td>';
						str += '<td>' + arr[i].m_phone + '</td>';
						str += '<td>' + arr[i].m_email + '</td>';
						str += '<td>' + arr[i].m_nickname + '</td>';
						str += '<td>' + arr[i].m_grade + '</td>';
						str += '</tr>';
					});
					if(str!=''){
						$("#test").html(str);
						}else{
							str+="<td colspan='8'>조회할 아이디가 없습니다.</td>";
							$("#test").html(str);
						}
				}
			});
		});
	});
</script>
</head>

<body>
	<a href="../">Home</a>
	<hr>
	<h1>관리자페이지</h1>
	<hr>
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
	<table border="1">
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
				<c:when test="${not empty list }"> 
				<c:forEach items="${list }" var="dto">
					<tr>
						<td><a href="read.do?id=${dto.m_id }">${dto.m_id }</a></td>
						<td>${dto.m_name }</td>
						<%-- <c:set value="${dto.m_birth }" var="birth"/>
						<c:set value="${fn:split(birth,'-')}" var="arr"/>
						<c:set value="${arr[0] }" var="year"/>
						<c:set value="${arr[1] }" var="month"/>
						<c:set value ="${fn:split(arr[2],' ')[0] }" var="date"/>
						<td>${year }년${month }월${date}일</td> --%>
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
	<jsp:include page="../page/paging.jsp"/> 
</body> 
</html>