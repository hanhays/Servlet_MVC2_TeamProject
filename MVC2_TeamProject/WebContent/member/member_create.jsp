<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<script type="text/javascript">
	$(function() {
		var old_year = $("#year").val();
		var old_month = $("#month").val();
		var new_year = null;
		var new_month = null;
		var date = null;
		$("#year").change(function() {
			new_year = $("#year").val();
			getDate();
		});
		$("#month").change(function() {
			new_month = $("#month").val();
			getDate();
		});
		function getDate() {
			if (new_year == null && new_month == null) {
				year_month_change(old_year, old_month);
			} else if (new_year == null && new_month != null) {
				year_month_change(old_year, new_month);
			} else if (new_year != null && new_month == null) {
				year_month_change(new_year, old_month);
			} else {
				year_month_change(new_year, new_month);
			}
		}
		function year_month_change(year, month) {
			date = new Date(year, month, 0).getDate();
			$("#date").html("");
			getDates(date);
		}
		function getDates(date) {
			for (var i = 1; i <= date; i++) {
				if (String(i).toString().length < 2) {
					$("#date").append(
							"<option value='0" + String(i) + "'>0" + String(i)
									+ "</oprion>");
				} else {
					$("#date").append(
							"<option value='" + i + "'>" + i + "</oprion>");
				}
			}
		}
		getDate();
	});
</script>

<script type="text/javascript">
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

<script type="text/javascript">

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
				<td><input required name="id"><a><button>중복체크</button></a></td>
			</tr>
			
			<tr>
				<td>PW :</td>
				<td><input required name="password" type="password" id="pwd1" ></td>
			</tr>

			<tr>
				<td>PW확인 :</td>
				<td><input required name="password2" type="password" id="pwd2" ></td>
			</tr>
			<tr><td colspan=4>
				<div class="alert alert-success" id="alert-success">비밀번호가 일치합니다.</div> 
				<div class="alert alert-danger" id="alert-danger">비밀번호가 일치하지 않습니다.</div>
			</td></tr>



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
				<td><input required name="nickname"><a><button>중복확인</button></a></td>
			</tr>
			<tr>
				<td colspan=4 style="text-align: center"><input type="submit"
					value="등록"></td>


			</tr>

		</table>

				<span id="alert-success" style="display: none;">비밀번호가 일치합니다.</span> 
				<span id="alert-danger" style="display: none; color: #d92742; font-weight: bold;">비밀번호가 일치하지 않습니다.</span>
	
	</form>
</body>
</html>