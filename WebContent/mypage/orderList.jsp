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
	<hr style="border: solid 1px purple">
	<strong><h1>주문내역</h1></strong>
	<h3>배송정보</h3>
	<ul>
		<li>${sessionScope.memberDto.name }</li>
		<li>${sessionScope.memberDto.phone }</li>
		<li>${sessionScope.memberDto.addr }</li>
	</ul>
	
	<table class="table table-bordered">
		<tr>
			<th>주문일자</th>
			<th>주문상품정보</th>
			<th>상품금액(수량)</th>
			<th>결제금액</th>
			<th>결제상태</th>
			<th>주문상태</th>
		</tr>
		<c:forEach var='vo' items='${list }'>
			<tr>
				<td>${vo.end_date}</td>
				<td><a href="${cp }/product/detail.do">${vo.name}</a></td>
				<td>${vo.price} (${vo.ea }개)</td>
				<td>${vo.price*vo.ea}원</td>
			<c:choose>		
				<c:when test="${vo.pay_yn==1 }">
					<td>미결제</td>
				</c:when>
				<c:when test="${vo.pay_yn==2 }">
					<td>결제</td>
				</c:when>
			</c:choose>	
			<c:choose>		
				<c:when test="${vo.status==1 }">
					<td>상품준비</td>	
				</c:when>			
				<c:when test="${vo.status==2 }">
					<td>배송준비</td>	
				</c:when>
				<c:when test="${vo.status==3 }">
					<td>배송중</td>	
				</c:when>
				<c:when test="${vo.status==4 }">
					<td>배송완료</td>	
				</c:when>
				<c:when test="${vo.status==5 }">
					<td>구매완료</td>	
				</c:when>
				<c:when test="${vo.status==6 }">
					<td>주문취소</td>	
				</c:when>
			</c:choose>	
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>