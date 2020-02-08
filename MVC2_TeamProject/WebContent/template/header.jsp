<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty sessionScope.dto }">
	<c:set value="${sessionScope.dto.m_id }" var="s_id" />
	<c:set value="${sessionScope.dto.m_grade}" var="s_grade"/>
</c:if>
<c:choose>
	<c:when test="${not empty s_id}">
	<h1>${s_id }님 환영합니다.</h1> 
	<br>
		<a href="member/logout.do">로그아웃</a>
		<c:choose>
			<c:when test="${s_grade eq 97}">
				<a>글쓰기</a>
				<a href="member/read.do">내정보</a>
			</c:when> 
			<c:otherwise> 
				<a href="${initParam.member_list }">회원리스트</a>
				<a>공지쓰기</a>
			</c:otherwise>
		</c:choose> 

	</c:when>
	<c:otherwise>
	<a href="${initParam.home }">Home</a>
	<a href="${initParam.login }">로그인</a>
	</c:otherwise>
</c:choose>