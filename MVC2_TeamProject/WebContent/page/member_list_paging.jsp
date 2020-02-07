<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


	<a href='list.do?currentPage=${l_pv.currentPage -10 }'>&laquo;</a>

	<a href='list.do?currentPage=${l_pv.currentPage -1 }'>&lt; &nbsp;&nbsp; </a>

<c:forEach var="i" begin="${l_pv.beginPageNum }" end="${l_pv.stopPageNum }">
	<a style="font-size: 15px; 
	${i == l_pv.currentPage? 'color:purple':'color:grey'}" 
	href="list.do?currentPage=${i }">${i}</a> &nbsp;&nbsp;
</c:forEach>

	<a href='list.do?currentPage=${l_pv.currentPage +1 }'> &gt; &nbsp;&nbsp; </a>

	<a href='list.do?currentPage=${l_pv.currentPage +10 }'>&raquo;</a>


