<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>MemberRead</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
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
					<td>${year }��${month }��${day[0] }��</td>
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
					<th>Img</th>
					<td>
						<%-- <img src="${util:getImg(dto.m_img) }"> --%>${dto.m_img }</td>
				</tr>
				<tr>
					<th>Grade</th>

					<c:set value="${dto.m_grade }" var="grade" />

					<c:choose>
						<c:when test="${grade eq 97 }">
							<td>�Ϲ�ȸ��</td>
						</c:when>
						<c:when test="${grade eq 98 }">
							<td>���</td>
						</c:when>
						<c:otherwise>
							<td>���۳�</td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>

				</tr>
			</table>
			<div>
				<a href="updateui.do">ȸ������</a> 
				<a href="deleteui.do">ȸ��Ż��</a>  
				<a href="../board/list.do">�۸��</a>
				<a href="../">Ȩ</a> 
			</div>
		</c:when>

		<c:otherwise>
			<h1>�α׾ƿ��� �Ǿ����ϴ�.</h1>
			<a href="../">Ȩ</a>
		</c:otherwise>
	</c:choose>
<!-- 	
	<script type="text/javascript">
		$(document).ready(function(){
			$("#iddea").click(function(event) {
				event.preventDefault();
				
				var pw = prompt("��й�ȣ�� �Է��ϼ���");
				location.assign("delete.do?password="+pw);
				
			});
		});
	</script>
	 -->
</body>
</html>