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
				<!-- <th>내용</th> -->
				<th>등록일</th>
				<!-- <th>이미지</th> -->
			</tr>
			<tr style='display:none'>
				<th>내용</th>
				<th></th>
				<th></th>
				<th>이미지</th>
			</tr>
		</thead>
		<c:forEach var='vo' items='${requestScope.list }'>
			<tbody>
				<tr onclick="showHidden(${vo.rnum})">	
					<td>${vo.rnum}</td>
					<td>${vo.name }</td>
					<td>${vo.title }</td>
					<td>${vo.regdate }</td>
				</tr>
				<tr id='${vo.rnum }' style='display:none;'>
					<td><p><img src="${pageContext.request.contextPath }/img/${vo.savefilename }" width="400px" height="500px"></p>${vo.content }</td>
<!-- 					<td>${vo.content }</td> -->
				</tr>	
			</tbody>	
		</c:forEach>	
	</table>
</div>
		<input type="button" value = "후기쓰기" style="float:right" onclick="location.href='${cp }/review/writeReview.jsp'">
		<br><br>


<!-- 페이징처리 -->
<div>
<c:choose>
	<c:when test ="${startPage>4 }">
		<a href="${pageContext.request.contextPath }/member/listReview.do?pageNum=${startPage-1}">[이전]</a>
	</c:when>
	<c:otherwise>
		이전
	</c:otherwise>
</c:choose>
<c:forEach var="i" begin="${startPage }" end="${endPage }">
	<c:choose>
		<c:when test="${i==pageNum }">
			<a href="${pageContext.request.contextPath }/member/listReview.do?pageNum=${i}">
			<span style='color:blue'>[${i }]</span></a>
		</c:when>		
		<c:otherwise>
			<a href = "${pageContext.request.contextPath }/member/listReview.do?pageNum=${i}">
			<span style='color:#999'>[${i }]</span></a>
		</c:otherwise>
	</c:choose>
</c:forEach>
<c:choose>
	<c:when test ="${endPage<pageCount }">
		<a href="${pageContext.request.contextPath }/member/listReview.do?pageNum=${endPage+1}">[다음]</a>
	</c:when>
	<c:otherwise>
		다음
	</c:otherwise>
</c:choose>
</div>
<script>
	function showHidden(rNum) {
		var id =document.getElementById(rNum);
		
		if(id.style.display == 'none') id.style.display = 'block' ;
		else id.style.display = 'none' ;
	}
	
</script>



</html>

