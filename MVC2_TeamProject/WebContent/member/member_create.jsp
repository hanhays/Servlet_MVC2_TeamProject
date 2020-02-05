<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
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
				url : 'member/checkid.do',
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
</head>
<body>
	<h1>회원 등록</h1>
	<form action="member/create.do" method="post">
		ID : <input required name="id">
		<button>중복체크</button>
		<br> NAME : <input required name="name"><br> AGE : <input
			type="number" name="age"><br> <input type="submit"
			value="등록">
	</form>
</body>
</html>