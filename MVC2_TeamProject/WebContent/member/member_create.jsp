<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/EMC" prefix="util"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MemberCreate</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/member/create.js"
	charset="UTF-8">
	
</script>
<!--            if(inputed=="" && data=='0') {
                $(".signupbtn").prop("disabled", true);
                $(".signupbtn").css("background-color", "#aaaaaa");
                $("#checkaa").css("background-color", "#FFCECE");
                idCheck = 0;
            } else if (data == '0') {
                $("#checkaa").css("background-color", "#B0F6AC");
                idCheck = 1;
                if(idCheck==1 && pwdCheck == 1) {
                    $(".signupbtn").prop("disabled", false);
                    $(".signupbtn").css("background-color", "#4CAF50");
                } 
            } else if (data == '1') {
                $(".signupbtn").prop("disabled", true);
                $(".signupbtn").css("background-color", "#aaaaaa");
                $("#checkaa").css("background-color", "#FFCECE");
                idCheck = 0;
            }  -->

<!-- <script type="text/javascript">
	$(document).ready(function() {
		$("button").click(function() {
			var id = $("input[name='id']").val();
			$.ajax({
				type : 'get',
				url : 'checkid.do',
				data : {
					id : id
				},
				dataType : 'text',
				success : function(result) {
					$("p").text(result); /* 필요기능 : 사용불가능할 때는 빨간색으로 사용불가라고 나오게 하고, 커서가 아이디 창으로 옮겨가도록 바꿔야한다. */
					/* 필요기능 : 중복체크 안하고 그냥 넘어가면 -> 중복체크하세요라는 값이 뜨도록 설정해야한다. + 값전송안되도록까지 설정 */
				}

			});
		});

	});
</script>
 -->
<!-- <script type="text/javascript">

	$(function() {
		$("#alert-success").hide();
		$("#alert-danger").hide();
		$("input").keyup(function() {
			var pwd1 = $("#pwd1").val();
			var pwd2 = $("#pwd2").val();
			if (pwd1 != "" || pwd2 != "") {
				if (pwd1 == pwd2) {
					$("#alert-success").show();
					$("#alert-danger").hide();
					$("#submit").removeAttr("disabled");
				} else {
					$("#alert-success").hide();
					$("#alert-danger").show();
					$("#submit").attr("disabled", "disabled");
				}
			}
		});
	});
</script> -->


<script type="text/javascript">
	
</script>

<style type="text/css">
h1 {
	text-align: center;
}

table {
	margin: auto;
	width: 400px;
}
</style>

</head>
<body>
	<!-- enctype = "multipart/form-data" -->
	<h1>회원 등록</h1>
	<form action="create.do" method="post">

		<table>
			<tr>
				<td>ID :</td>
				<td><input type="text" required name="m_id"></td>
			</tr>
			<tr>
				<td colspan="2" id="id_check_msg"></td>
			</tr>
			<tr>
				<td>PW :</td>
				<td><input required name="password" type="password" id="pwd1"></td>
			</tr>
			<tr>
				<td colspan="2" id="password_1_check"></td>
			</tr>
			<tr>
				<td>PW확인 :</td>
				<td><input required name="password_check" type="password"
					id="pwd2"></td>
			</tr>
			<tr>
				<td colspan="2" id="password_2_check"></td>
			</tr>
			<!-- 			<tr>
				<td colspan=4 style="text-align: center">
					<div class="alert alert-success" id="alert-success">비밀번호가
						일치합니다.</div>
					<div class="alert alert-danger" id="alert-danger">비밀번호가 일치하지
						않습니다.</div>
				</td>
			</tr>
 -->


			<tr>
				<td>Name :</td>
				<td><input required name="name"></td>
			</tr>

			<tr>
				<td>Birth:</td>
				<td><select name="year" id="year">
						<c:forEach items="${util:getYear() }" var="year">
							<option value="${year }">${year }</option>
						</c:forEach>
				</select>- <select name="month" id="month">
						<c:forEach items="${util:getMonth() }" var="month">
							<option value="${month }">${month}</option>
						</c:forEach>
				</select>- <select name="date" id="date">
						<option value="00">00</option>
				</select></td>
			</tr>

			<%-- 		Age : <input type="number" required name="m_age"><br>  --%>
			<tr>
				<td>Phone:</td>
				<td><input required name="phone"></td>
			</tr>

			<tr>
				<td>Email:</td>
				<td><input required name="email"></td>
			</tr>

			<tr>
				<td>Nickname:</td>
				<td><input required name="m_nickname"></td>
			</tr>
			<tr>
				<td colspan="2" id="nickName_check_msg"></td>
			</tr>
			<tr>
				<td colspan=4 style="text-align: center"><input type="submit"
					value="등록"></td>


			</tr>

		</table>


	</form>
</body>
</html>