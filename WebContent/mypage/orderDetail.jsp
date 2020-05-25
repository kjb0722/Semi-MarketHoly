<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
${list.onum }
	<table class="table table-bordered">
		<tr>
			<th>주문번호</th>
			<th>주문일자</th>
			<th>상품가격</th>
			<th>수량</th>
			<th>결제금액</th>
			<th>결제상태</th>
			<th>주문상태</th>
			<th>구매취소</th>
		</tr>
		<c:forEach var='vo' items='${list }'>
			<tr>
				<td>${vo.onum }</td>
				<td>${vo.reg_date}</td>
				<td>${vo.pname}</td>
				<td>${vo.price_1}</td>
				<td>${vo.ea }<td>
				<td>${vo.pay_yn }<td>	
				<td>${vo.use_point }</td>			
			<c:choose>		
				<c:when test="${vo.pay_yn=='N'}">
					<td>미결제</td>
				</c:when>
				<c:when test="${vo.pay_yn=='Y'}">
					<td>결제완료</td>
				</c:when>
			</c:choose>	

			
			</tr>
		</c:forEach>
	</table>
