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

	function change() {
		alert();
	}
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

h1{
	margin: auto;
	text-align: center;
}
</style>
</head>
<body>
	
		<h1>ȸ�� ���� ����</h1>

		<form action="update.do" method="post">
			<table>
				<tr>
					<td>���̵�</td>
					<td><input name="id" value="${dto.m_id }" readonly></td>
				</tr>
				<tr>
					<td>������ ��й�ȣ</td>
					<td><input name="password" type="password"
						value="${dto.m_password }"></td>
				</tr>
				<tr>
					<td>�̸�</td>
					<td><input name="name" value="${dto.m_name }"></td>
				</tr>
				<tr>
					<td>�������</td>
					<td><input name="birth" value="${dto.m_birth }" readonly></td>
				</tr>
				<tr>
					<td>����</td>
					<td><input name="age" value="${dto.m_age }" type="number"
						readonly></td>
				</tr>
				<tr>
					<td>��ȭ��ȣ</td>
					<td><input name="phone" value="${dto.m_phone }" required></td>
				</tr>
				<tr>
					<td>�̸���</td>
					<td><input name="email" value="${dto.m_email }" required></td>
				</tr>
				<tr>
					<td>�г���</td>
					<td><input name="nickname" value="${dto.m_nickname }" required></td>
				</tr>
				<tr>
					<td>ȸ�� ���</td>
					<td><input name="grade" value="${dto.m_grade }" readonly></td>
				</tr>
			</table>
			<input id="btn" type="submit" value="����"
				onclick="javascript:alert('���������� ����Ǿ����ϴ�.')" />
			<input id="btn" type="button" value="���" onclick="javascript:history.back(-1)">
		</form>
	
</body>
</html>