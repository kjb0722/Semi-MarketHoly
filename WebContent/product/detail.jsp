<%@page import="com.market.review.dto.ReviewDto"%>
<%@page import="com.market.review.dao.ReviewDao"%>
<%@page import="com.market.product.dao.ProductDao"%>
<%@page import="com.market.product.dto.ProductDto"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<img src="${pageContext.request.contextPath }/img/${dto.thumb_save}"
	width="300px" height="400px">
<div class="pull-right">
<h1>${dto.name}</h1>
<h4>${dto.description}</h4>
<h2>${dto.price}</h2>
구매수량
<button type="button" class="btn btn-default" onclick="minus()">
		<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
	</button>
	&nbsp<label id="EA">1</label>&nbsp
	<button type="button" class="btn btn-default" onclick="plus()">
		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
	</button><br>
	<input type="hidden" id="price" value="${dto.price }">
	총 상품금액 : <label id="sum">${dto.price }</label> 원<br>
	<input type="button" value="장바구니 담기" onclick="incart()">
</div>



<div class="container">
	<ul id="myTab" class="nav nav-tabs" role="tablist">
		<li role="presentation" class="active"><a href="#discript"
			id="discript-tab" role="tab" data-toggle="tab"
			aria-controls="discript" aria-expanded="false"> 상품설명</a></li>
		<li role="presentation" class=""><a href="#review" role="tab"
			id="review-tab" data-toggle="tab" aria-controls="review"
			aria-expanded="true">상품후기</a></li>
		<li role="presentation" class=""><a href="#qna" role="tab"
			id="qna-tab" data-toggle="tab" aria-controls="qna"
			aria-expanded="true">상품문의</a></li>
	</ul>
	<div id="myTabContent" class="tab-content">
		<div role="tabpanel" class="tab-pane fade" id="discript"
			aria-labelledby="discript-tab">
			<p>상품설명</p>
		</div>
		<div role="tabpanel" class="tab-pane fade active in" id="review"
			aria-labelledby="review-tab">
			<p>상품후기</p>
		</div>
		<div role="tabpanel" class="tab-pane fade active in" id="qna"
			aria-labelledby="qna-tab">
			<p>상품문의</p>
		</div>
	</div>
</div>

<script>
function plus() {
	var EA=document.getElementById("EA");
	var price=document.getElementById("price").value;
	var sum=document.getElementById("sum");
	EA.innerHTML=parseInt(EA.innerHTML)+1;
	sum.innerHTML =price*parseInt(EA.innerHTML); 
}
function minus() {
	var EA=document.getElementById("EA");
	var price=document.getElementById("price").value;
	var sum=document.getElementById("sum");
	
	if(EA.innerHTML<=1){
		alert("최소수량입니다");  
	}else{
	EA.innerHTML =parseInt(EA.innerHTML)-1;
	sum.innerHTML =price*parseInt(EA.innerHTML);  
	}
}
function incart() {		
	var id='${sessionScope.memberDto.id}';
	var pnum=${param.pnum};
	var EA=document.getElementById("EA");
	location = "${cp }/member/cartAdd.do?pnum="+pnum+"&id="+id+"&EA="+parseInt(EA.innerHTML);
	
}
	

	
</script>
