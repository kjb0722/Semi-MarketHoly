<%@page import="com.market.review.dto.ReviewDto"%>
<%@page import="com.market.review.dao.ReviewDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<!-- 뷰포트 -->
	<meta name="viewport" content="width=device-width" initial-scale="1">
	<!-- 스타일시트 참조  -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.css">
	<title>jsp 게시판 웹사이트</title>
</head>
<body>
	<div class="container">
		<table class="table table-striped">
		<thead>
		<tr>
			<th>리뷰번호</th>
			<th>이름</th>
			<th>제목</th>
			<th>내용</th>
			<th>등록일</th>
			<th>이미지</th>
		</tr>
		</thead>

<c:forEach var='list' items='${list }'>
	<tbody>
	<tr>	
		<td>${list.rnum }</td>
		<td>${list.name }</td>
		<td>${list.title }</td>
		<td>${list.content }</td> 
		<td>${list.regdate }</td>
		<td><img src="${pageContext.request.contextPath }/img/${list.savefilename }"></td>
	</tr>	
	</tbody>	
	</c:forEach>	
	</table>
</div>	
	<!-- 애니매이션 담당 JQUERY -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<!-- 부트스트랩 JS  -->
	<script src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.js"></script>
</body>


</html>