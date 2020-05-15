<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<c:forEach var="dto" items="${requestScope.dto }">
	<img src="../img/${dto.thumb_save}" width="300px" height="400px">
	<h1>${dto.name}</h1>
	<h4>${dto.description}</h4>
	<h2>${dto.price}</h2>
	구매수량 <br>
	총 상품금액 : ${dto.price } 원 <br>
	<input type="button" value="장바구니 담기" id="incart"><br>
	<br>
	------------------하단 탭 (상품설명/상품이미지/고객후기/상품문의 )
</c:forEach>