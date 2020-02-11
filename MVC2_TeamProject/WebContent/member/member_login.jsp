<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>MemberLogin</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style type="text/css">
table {
	margin: auto; 
	text-align: center;
}
</style>
<script type="text/javascript" src="../js/member/login.js"></script>
</head>
<body style="margin: auto; text-align: center;">
	<a href="../">Home</a>
	<h1>로그인</h1>
	<form action="login.do" method="post">
		<table>
			<tr>
				<th>ID</th>
				<c:choose>
					<c:when test="${empty id }">
						<td><input name="id" placeholder="아이디를 입력해 주세요." /></td>
					</c:when>
					<c:otherwise>
						<td><input name="id" placeholder="${id }"></td>
					</c:otherwise>
				</c:choose>

			</tr>
			<tr>

			</tr>
			<tr>
				<th>Password</th>
				<td><input type="password" name="password"
					placeholder="비밀번호를 입력해 주세요." /></td>
			</tr>
			<tr>
				<th colspan="2" id="msg" style="padding-bottom: 30 px; color: red;"> 
					&nbsp;
					<c:if test="${not empty msg }">${msg }</c:if>
				</th>

			</tr>
		<tr> 
			<th colspan="3">
				<input type="checkbox" name="autologin">자동로그인
				<button onclick="formSubmit()">로그인</button>
			</th>
		</tr>
		</table>
	</form>
	<table>
		<tr>
			<th colspan="3">
				<button>아이디찾기</button>
				<button>비번찾기</button>
				<button onclick="location.href='createui.do'">회원가입</button>
			</th>

		</tr>
	</table>
</body>
</html>