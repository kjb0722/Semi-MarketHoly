<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
			<th>주문번호</th>
			<th>주문일자</th>
			<th>주문상품정보</th>
			<th>상품금액(수량)</th>
			<th>결제금액</th>
			<th>결제상태</th>
			<th>주문상태</th>
			<th>주문취소</th>
		</tr>
		<c:forEach var='vo' items='${list }'>
			<tr>
				<td>${vo.onum }</td>
				<td>${vo.reg_date}</td>
				<td><a href="${cp }/product/detail.do?pnum=${vo.pnum}">${vo.name}</a></td>
				<td>${vo.price} (${vo.ea }개)</td>
				<td>${vo.price*vo.ea}원</td>
			<c:choose>		
				<c:when test="${vo.pay_yn=='N'}">
					<td>미결제</td>
				</c:when>
				<c:when test="${vo.pay_yn=='Y'}">
					<td>결제완료</td>
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
					<td style="color: red;">주문취소</td>	
				</c:when>
			</c:choose>
			<td><a href="${cp }/mypage/cancleOrder.do?onum=${vo.onum}">주문취소하기</a>	</td>	
			</tr>
		</c:forEach>
	</table>
</div>
