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
	<hr style="border: solid 1px purple">
<div class="container">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>상품명</th>
				<th>제목</th>
				<th>이름</th>
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
					<td>${vo.pname }</td>
					<td>${vo.title }</td>
					<td>${vo.name }</td>
					<td>${vo.regdate }</td>
				</tr>
				<tr id='${vo.rnum }' style='display:none;'>
					<td><p><img src="${pageContext.request.contextPath }/img/${vo.savefilename }" width="400px" height="500px"></p>${vo.content }</td>
				</tr>	
			</tbody>	
		</c:forEach>	
	</table>
</div>
<script>
	function showHidden(rNum) {
		var id =document.getElementById(rNum);
		
		if(id.style.display == 'none'){	
			id.style.display = 'block' ;
		}else{ 
			id.style.display = 'none' ;
		}
	}
</script>
</html>