<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>MemberUpdate</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
	/* $(function() {
		var password1 = $("input[name='m_password2']");
		var test = $("input[0]").focus(function() {
			alert("zz");
		});
	}); */

	$(function() {
		$("#alert-success").hide();
		$("#alert-wrong").hide();
		$("input").keyup(function() {
			var pwd1 = $("#pwd1").val();
			var pwd2 = $("#pwd2").val();
			if (pwd1 != "" || pwd2 != "") {
				if (pwd1 == pwd2) {
					$("#alert-success").show();
					$("#alert-wrong").hide();
					$("#submit").removeAttr("disabled");
				} else {
					$("#alert-success").hide();
					$("#alert-wrong").show();
					/* $("#submit").attr("disabled", "disabled"); */
				}
			}
		});
	});
	
	$(function(){
		$("#btn").click(function(){
			var result = confirm("변경 사항을 취소하시겠습니까?");
			if (result) {
				location.replace("read.do");
			}else{
				
			}
		});
	});
</script>
<style type="text/css">
form {
	margin: auto;
	text-align: center;
}

table {
	margin: auto;
	text-align: center;
	margin: 10px auto 10px;
}

h1 {
	margin: auto;
	text-align: center;
}
</style>
</head>
<body>

	<h1>회원 정보 수정</h1>

	<form action="update.do" method="post">
		<table>
			<tr>
				<td>아이디</td>
				<td><input name="id" value="${dto.m_id }" readonly></td>
			</tr>
			<tr>
				<td>변경할 비밀번호</td>
				<td><input name="password" type="password" id="pwd1"></td>
			</tr>
			<tr>
				<td>비밀번호 재입력</td>
				<td><input name="password2" type="password"	id="pwd2"></td>
			</tr>
		</table>
		<div class="alert alert-success" id="alert-success">비밀번호가 일치합니다.</div>
		<div class="alert alert-danger" id="alert-wrong">비밀번호가 일치하지 않습니다.</div>
		<table>
			<tr>
				<td>이름</td>
				<td><input name="name" value="${dto.m_name }"></td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td><input name="birth" value="${dto.m_birth }" readonly></td>
			</tr>
			<tr>
				<td>나이</td>
				<td><input name="age" value="${dto.m_age }" type="number"
					readonly></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input name="phone" value="${dto.m_phone }" required></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input name="email" value="${dto.m_email }" required></td>
			</tr>
			<tr>
				<td>닉네임</td>
				<td><input name="nickname" value="${dto.m_nickname }" required></td>
			</tr>
			<tr>
				<td>회원 등급</td>
				<td><input name="grade" value="${dto.m_grade }" readonly></td>
			</tr>
		</table>
		<input id="submit" type="submit" value="수정" onclick="javascript:alert('성공적으로 변경되었습니다.')" disabled/> 
		<input id="btn"	type="button" value="취소">
	</form>

</body>
</html>