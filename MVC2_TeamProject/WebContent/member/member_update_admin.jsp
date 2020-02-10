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
		$("#alert-wrong").hide();
		$("#alert-success").hide();
		$("input").keyup(function() {
			var grade = $("#grade").val();
			if (grade == 'a' || grade == 'c') {
				$("#alert-wrong").hide();
				$("#alert-success").show();
				$("#submit").removeAttr("disabled");
			} else {
				$("#alert-wrong").show();
				$("#alert-success").hide();
			}
		})
	});

	$(function() {
		$("#btn").click(function() {
			var result = confirm("변경 사항을 취소하시겠습니까?");
			if (result) {
				location.replace("list.do");
			} else {

			}
		});
	});
<%%>
	
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

td {
	width: 384px;
}
</style>
</head>
<body>

	<h1>회원 정보 수정</h1>

	<form action="update_admin.do" method="post">
		<table>
			<tr>
				<td>회원 아이디</td>
				<td><input name="id" value="${dto.m_id }" readonly></td>
			</tr>
			<tr>
				<td>회원 등급</td>
				<td><input id="grade" name="grade" value="${dto.m_grade }"
					required></td>
			</tr>
			<tr id="check_grade">
				<td class="alert alert-danger" id="alert-wrong"
					style="padding: 1px; border: none;">잘못된 회원등급입니다.</td>
				<td class="alert alert-success" id="alert-success"
					style="padding: 1px; border: none;">유효한 회원등급입니다.</td>
			</tr>
		</table>
		<input id="submit" type="submit" value="수정"
			onclick="javascript:alert('성공적으로 변경되었습니다.')" disabled /> <input
			id="btn" type="button" value="취소">
	</form>

</body>
</html>