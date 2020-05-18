<%@page import="com.market.review.dto.ReviewDto"%>
<%@page import="com.market.review.dao.ReviewDao"%>
<%@page import="com.market.product.dao.ProductDao"%>
<%@page import="com.market.product.dto.ProductDto"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%-- <c:forEach var="dto" items="${requestScope.dto }"> --%>
	<img src="../img/${dto.thumb_save}" width="300px" height="400px">
	<h1>${dto.name}</h1>
	<h4>${dto.description}</h4>
	<h2>${dto.price}</h2>
	구매수량 <br>
	총 상품금액 : ${dto.price } 원 <br>
	<input type="button" value="장바구니 담기" id="incart"><br>
	<br>
	------------------하단 탭 (상품설명/상품이미지/고객후기/상품문의 )
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
					<td><p><img src="${pageContext.request.contextPath }/img/${vo.savefilename }"></p>${vo.content }</td>
<!-- 					<td>${vo.content }</td> -->
				</tr>	
			</tbody>	
		</c:forEach>	
	</table>
</div>
<!-- 페이징처리 -->
<div>
<c:choose>
	<c:when test ="${startPage>4 }">
		<a href="${pageContext.request.contextPath }/product/detail?pageNum=${startPage-1}">[이전]</a>
	</c:when>
	<c:otherwise>
		이전
	</c:otherwise>
</c:choose>
<c:forEach var="i" begin="${startPage }" end="${endPage }">
	<c:choose>
		<c:when test="${i==pageNum }">
			<a href="${pageContext.request.contextPath }/product/detail.do?pageNum=${i}">
			<span style='color:blue'>[${i }]</span></a>
		</c:when>		
		<c:otherwise>
			<a href = "${pageContext.request.contextPath }/product/detail.do?pageNum=${i}">
			<span style='color:#999'>[${i }]</span></a>
		</c:otherwise>
	</c:choose>
</c:forEach>
<c:choose>
	<c:when test ="${endPage<pageCount }">
		<a href="${pageContext.request.contextPath }/product/detail.do?pageNum=${endPage+1}">[다음]</a>
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