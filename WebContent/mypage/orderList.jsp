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
	<h3>배송정보</h3>
	<ul>
		<li>${sessionScope.memberDto.name }</li>
		<li>${sessionScope.memberDto.phone }</li>
		<li>${sessionScope.memberDto.addr }</li>
	</ul>
	
	<table class="table table-striped">
		<tr>
			<th>주문일자</th>
			<th>주문상품정보</th>
			<th>상품금액(수량)</th>
			<th>주문상태</th>
		</tr>
		<c:forEach var='vo' items='${list }'>
			<tr>
				<td>${vo.end_date}</td>
				<td><a href="${cp }/product/detail.do">${vo.name}</a></td>
				<td>${vo.price} (${vo.ea }개)</td>
			<c:choose>		
				<c:when test="${vo.opnum==1 }">
					<td>상품준비</td>	
				</c:when>			
				<c:when test="${vo.opnum==2 }">
					<td>배송준비</td>	
				</c:when>
				<c:when test="${vo.opnum==3 }">
					<td>배송중</td>	
				</c:when>
				<c:when test="${vo.opnum==4 }">
					<td>배송완료</td>	
				</c:when>
				<c:when test="${vo.opnum==5 }">
					<td>구매완료</td>	
				</c:when>
				<c:when test="${vo.opnum==6 }">
					<td>주문취소</td>	
				</c:when>
			</c:choose>	
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>