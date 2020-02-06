<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri ="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
 
<head>
<title>MemberList</title>
<meta charset="EUC-KR">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(function() {
		$("#ajax_click").click(function(/* event */) {
			//event.preventDefault(); 	
			var category = $("select[name='category']").val();
			var content = $("input[name='content']").val();
			console.log(content);
			$.ajax({
				type : 'post',
				url : 'search.do',
				data : {
					category : category,
					content : content
				},
				dataType : 'text',
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				success : function(result) {
					var arr = JSON.parse(result);
					var str = '';
					$.each(arr, function(i) {
						str += '<tr>';
						str += "<td><a href='read.do?id="+ arr[i].m_id +"'>"+arr[i].m_id +"</a></td>";
						str += '<td>' + arr[i].m_name + '</td>';
						str += '<td>' + arr[i].m_birth + '</td>';
						str += '<td>' + arr[i].m_age + '</td>';
						str += '<td>' + arr[i].m_phone + '</td>';
						str += '<td>' + arr[i].m_email + '</td>';
						str += '<td>' + arr[i].m_nickname + '</td>';
						str += '<td>' + arr[i].m_grade + '</td>';
						str += '</tr>';
					});
					if(str!=''){
						$("#test").html(str);
						}else{
							str+="<td colspan='8'>��ȸ�� ���̵� �����ϴ�.</td>";
							$("#test").html(str);
						}
				}
			});
		});
	});
</script>
</head>

<body>
	<a href="../">Home</a>
	<hr>
	<h1>������������</h1>
	<hr>
		<select name="category">
			<option value="0">��޺�</option>
			<option value="1">���̵�</option>
			<option value="2">�̸�</option>
			<option value="3">�г���</option>
			<option value="4">��ȭ��ȣ</option>
			<option value="5">�̸���</option>
		</select> <input type="text" name="content" />
		<button id="ajax_click">�˻�</button>
	<hr>
	<table border="1">
		<thead>
			<tr>
				<th>���̵�</th>
				<th>�̸�</th>
				<th>�������</th>
				<th>����</th>
				<th>��ȭ��ȣ</th>
				<th>�̸���</th>
				<th>�г���</th>
				<th>���</th>
			</tr>
		</thead> 
		<tbody id="test">
			<c:choose>
				<c:when test="${not empty list }"> 
				<c:forEach items="${list }" var="dto">
					<tr>
						<td><a href="read.do?id=${dto.m_id }">${dto.m_id }</a></td>
						<td>${dto.m_name }</td>
						<%-- <c:set value="${dto.m_birth }" var="birth"/>
						<c:set value="${fn:split(birth,'-')}" var="arr"/>
						<c:set value="${arr[0] }" var="year"/>
						<c:set value="${arr[1] }" var="month"/>
						<c:set value ="${fn:split(arr[2],' ')[0] }" var="date"/>
						<td>${year }��${month }��${date}��</td> --%>
						<c:set value="${fn:split(dto.m_birth,' ')[0]}" var="birth"/>
						<td>${birth }</td>
						<td>${dto.m_age }</td>
						<td>${dto.m_phone }</td>
						<td>${dto.m_email }</td>
						<td>${dto.m_nickname }</td>
						<td>${dto.m_grade }</td>
					</tr>
				</c:forEach>
				</c:when>
			</c:choose>
		</tbody>
	</table>
	<br>
	<jsp:include page="../page/paging.jsp"/> 
</body> 
</html>