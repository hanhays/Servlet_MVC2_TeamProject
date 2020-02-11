<%@ page language="java" contentType="text/html; charset=UTF-8
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MemberRead</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<c:if test="${not empty sessionScope.dto }">
		<c:set value="${sessionScope.dto.m_grade }" var="s_grade" />
	</c:if>
	<a href="../">홈</a>
	<c:choose>
		<c:when test="${not empty requestScope.dto }">
			<table>
				<tr>
					<th>ID</th>
					<td>${dto.m_id }</td>
				</tr>
				<tr>
					<th>Name</th>
					<td>${dto.m_name }</td>
				</tr>
				<tr>
					<th>Birth</th>
					<c:set value="${fn:split(dto.m_birth,'-') }" var="birth" />
					<c:set value="${birth[0] }" var="year" />
					<c:set value="${birth[1] }" var="month" />
					<c:set value="${fn:split(birth[2],' ') }" var="day" />
					<td>${year}년${month}월${day[0]}일</td>
				</tr>
				<tr>
					<th>Age</th>
					<td>${dto.m_age }</td>
				</tr>
				<tr>
					<th>Phone</th>
					<td>${dto.m_phone }</td>
				</tr>
				<tr>
					<th>Email</th>
					<td>${dto.m_email }</td>
				</tr>
				<tr>
					<th>NickName</th>
					<td>${dto.m_nickname }</td>
				</tr>
				<tr>
					<th>Grade</th>
					<c:set value="${requestScope.dto.m_grade }" var="grade" />
					<c:choose>
						<c:when test="${grade eq 97 }">
							<td>일반회원</td>
						</c:when>
						<c:when test="${grade eq 98 }">
							<td>운영자</td>
						</c:when>
						<c:otherwise>
							<td>나쁜놈</td>
						</c:otherwise>
					</c:choose>
				</tr>
			</table>
			<div>
				<c:choose>
					<c:when test="${s_grade eq 98 }">
						<a href="list.do">관리자리스트</a>
						<a href="updateui.do?id=${dto.m_id }">회원 정보 변경</a>
					</c:when>
					<c:otherwise>
						<a href="updateui.do">회원수정</a>
						<a href="deleteui.do">회원탈퇴</a>
						<a href="../board/list.do">내가쓴글보기</a>
					</c:otherwise>
				</c:choose>
			</div>
		</c:when>
	</c:choose>
	<!-- 	
	<script type="text/javascript">
		$(document).ready(function(){
			$("#iddea").click(function(event) {
				event.preventDefault();
				
				var pw = prompt("비밀번호를 입력하세요");
				location.assign("delete.do?password="+pw);
				
			});
		});
	</script>
	 -->
</body>
</html>