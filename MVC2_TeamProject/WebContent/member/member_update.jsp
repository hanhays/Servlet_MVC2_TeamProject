<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>MemberUpdate</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript">
  
  $(function(){
	  var password1 =$("input[name='m_password2']");
	  var test = $("input[0]").focus(function(){
		 alert("zz"); 
	  });
  });
  </script>
</head>
<body>
	<h1>회원 정보 수정</h1>
	<form action="member/update.do" method="post">
		ID: <input readonly name="m_id"<%--  value="${ }" --%>><br>
		PW: <input name="m_password" type="password"><br>
			PW: <input name="m_password2" type="password"><br>
		Name: <input name="m_name"><br>
		Birth: <input name="m_birth" readonly><br>
		Age: <input name="m_age" readonly><br>
		Phone: <input name="m_phone"><br>
		Email: <input name="m_email"><br>
		Nickname: <input name="m_nickname"><a><button>중복 확인</button></a><br>
		Image: <img name="m_img">
		<input type="submit" value="수정">
	
	</form>
</body>
</html>