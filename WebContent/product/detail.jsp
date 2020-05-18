<%@page import="com.market.review.dto.ReviewDto"%>
<%@page import="com.market.review.dao.ReviewDao"%>
<%@page import="com.market.product.dao.ProductDao"%>
<%@page import="com.market.product.dto.ProductDto"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%-- <c:forEach var="dto" items="${requestScope.dto }"> --%>
<img src="${pageContext.request.contextPath }/img/${dto.thumb_save}"
	width="300px" height="400px">
<h1>${dto.name}</h1>
<h4>${dto.description}</h4>
<h2>${dto.price}</h2>
구매수량
<br>

<button type="button" class="btn btn-default">
	<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
</button>
<input type="text" id="EA">
<button type="button" class="btn btn-default">
	<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
</button>

총 상품금액 : ${dto.price } 원
<br>

<input type="button" value="장바구니 담기" id="incart">
<br>
<script>
	var incart=document.getElementById("incart");
	incart.onclick=function(){
	var id='${sessionScope.dto.id}';
	var pnum=${param.pnum};
	var EA=document.getElementById("EA").value;
	xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4 && xhr.status==200){
			let json = JSON.parse(xhr.responseText);
			if(json.n > 0){
				alert("장바구니 담기 성공");
				location =`${cp}/cart.do`;
			}else{
				location = `${cp}/error.do`;
			}
		}
	};
	
	xhr.open('get','${cp}/member/cartAdd.do?id='+id+'&pnum='+pnum+'&EA='+EA,true);
	xhr.send();
}	

	
</script>
------------------하단 탭 (상품설명/상품이미지/고객후기/상품문의 )
