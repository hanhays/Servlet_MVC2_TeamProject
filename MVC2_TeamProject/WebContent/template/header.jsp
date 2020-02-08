<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty sessionScope.dto }">
	<c:set value="${sessionScope.dto.m_id }" var="s_id" />
	<c:set value="${sessionScope.dto.m_grade}" var="s_grade"/>
</c:if>
<c:choose>
	<c:when test="${not empty s_id}">
	<h1>${s_id }�� ȯ���մϴ�.</h1> 
	<br>
		<a href="member/logout.do">�α׾ƿ�</a>
		<c:choose>
			<c:when test="${s_grade eq 97}">
				<a>�۾���</a>
				<a href="member/read.do">������</a>
			</c:when> 
			<c:otherwise> 
				<a href="${initParam.member_list }">ȸ������Ʈ</a>
				<a>��������</a>
			</c:otherwise>
		</c:choose> 

	</c:when>
	<c:otherwise>
	<a href="${initParam.home }">Home</a>
	<a href="${initParam.login }">�α���</a>
	</c:otherwise>
</c:choose>