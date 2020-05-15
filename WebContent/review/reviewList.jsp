<%@page import="com.market.review.dto.ReviewDto"%>
<%@page import="com.market.review.dao.ReviewDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reviewList.jsp</title>
</head>
<body>
		<table>
		<tr>
			<th>리뷰번호</th>
			<th>이름</th>
			<th>제목</th>
			<th>내용</th>
			<th>등록일</th>
			<th>이미지</th>
		</tr>
		

<c:forEach var='list' items='${list }'>
	<tr onclick="">
		<td>${list.rnum }</td>
		<td>${list.name }</td>
		<td>${list.title }</td>
		<td>${list.content }</td> 
		<td>${list.regdate }</td>
		<td><img src="${pageContext.request.contextPath }/img/${list.savefilename }"></td>
	</tr>		
</c:forEach>	
</table>	
</body>
<script></script>



</html>