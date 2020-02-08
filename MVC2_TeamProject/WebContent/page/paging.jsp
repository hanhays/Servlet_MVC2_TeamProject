<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:choose>
	<c:when test="${not empty l_pv}">
		<a href='list.do?currentPage=${l_pv.currentPage -10 }'>&laquo;</a>

		<a href='list.do?currentPage=${l_pv.currentPage -1 }'>&lt;
			&nbsp;&nbsp; </a>

		<c:forEach var="i" begin="${l_pv.beginPageNum }"
			end="${l_pv.stopPageNum }">
			<a
				style="font-size: 15px; 
	${i == l_pv.currentPage? 'color:purple':'color:grey'}"
				href="list.do?currentPage=${i }">${i}</a> &nbsp;&nbsp;
		</c:forEach>

		<a href='list.do?currentPage=${l_pv.currentPage +1 }'> &gt;
			&nbsp;&nbsp; </a>

		<a href='list.do?currentPage=${l_pv.currentPage +10 }'>&raquo;</a>
	</c:when>
	<c:otherwise>
		<a href='search.do?currentPage=${s_pv.currentPage -10 }'>&laquo;</a>

		<a href='search.do?currentPage=${s_pv.currentPage -1 }'>&lt;
			&nbsp;&nbsp; </a>


		<c:forEach var="i" begin="${s_pv.beginPageNum }"
			end="${s_pv.stopPageNum }">
			<a
				style="font-size: 15px; 
	${i == s_pv.currentPage? 'color:purple':'color:grey'}"
				href="search.do?currentPage=${i }">${i}</a> &nbsp;&nbsp;
</c:forEach>

		<a href='search.do?currentPage=${s_pv.currentPage +1 }'> &gt;
			&nbsp;&nbsp; </a>

		<a href='search.do?currentPage=${s_pv.currentPage +10 }'>&raquo;</a>
	</c:otherwise>
</c:choose>



