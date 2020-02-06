<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${pv.currentPage > 10 }">
	<a href='list.do?currentPage=${pv.currentPage -11 }'>&laquo;</a>
</c:if>

<c:if test="${pv.currentPage > 1 }">
	<a href='list.do?currentPage=${pv.currentPage -1 }'>&lt; &nbsp;&nbsp; </a>
</c:if>

<c:forEach var="i" begin="${pv.beginPageNum }" end="${pv.stopPageNum }">
	<a style="font-size: 20px; ${i == pv.currentPage? 'color:purple':''}" href="list.do?currentPage=${i }">${i +1}</a> &nbsp;&nbsp;
</c:forEach>

<c:if test="${pv.currentPage < pv.totalPage }">
	<a href='list.do?currentPage=${pv.currentPage +1 }'> &gt; &nbsp;&nbsp; </a>
</c:if>

<c:if test="${pv.currentPage < pv.totalPage - 10 }">
	<a href='list.do?currentPage=${pv.currentPage +10 }'>&raquo;</a>
</c:if>

