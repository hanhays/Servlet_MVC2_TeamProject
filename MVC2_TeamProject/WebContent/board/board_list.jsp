<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<head>
	<title>Bulletin Board</title>
	<meta charset="EUC-KR"> <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="../css/member/list.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../js/board/list.js" charset="UTF-8"></script> 
</head>

<body>
<br>
<a href='createui.do' class="btn btn-primary">글쓰기</a>
<hr>
<h1 style="text-align: center;">게시판</h1>
<br>

	<div class="container">
		<div class="form-group row">
			<div class="col-xs-5">
				<select id="category" style="font-size: 17px" name="category">
					<option value="0">글번호로</option>
					<option value="1">글제목으로</option>
					<option value="2">글쓴이로</option>
					<option value="3">글쓴날짜로</option>
				</select>
			</div>
			
			<div class="col-xs-5">
				<input class="form-control" name="content" type="text"/>
			</div>
			
			<div class="col-xs-2">
				<button class="btn btn-primary" id="searchList">검색</button>
			</div>
		</div>
		
		<table class="table">
			<thead>
				<tr>
					<th >글번호</th>
					<th >글제목</th>
					<th >글쓴이</th>
					<th >쓴날짜</th>
					<th >조횟수</th>
				</tr>
			</thead>
	
			<tbody id="ajaxTable"/>
			
		</table>
	</div>
	
	<div id="paging"></div>
	<div id="totalList"></div>
	<c:if test="${not empty flag }"> 
		<p style="display: none;" id="getFlag" >${flag}</p>
	</c:if>
</body>
</html>