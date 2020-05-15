<%@page import="com.market.product.dto.ProductDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.market.product.dao.ProductDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
	
	//로그인한 아이디도 세션에있는거 가져오기(로그인 안되어있을때 장바구니에 담으면....?)
	//재고수량 0인 상품 품절 표시
	//장바구니 버튼 누르면 cart.do로 이동해서 pnum,수량,세션에 있는 아이디 넘겨주기(로그인 안되어있으면 login.do로)
%>

<h1>상품목록</h1>
<h5>${ cnum} > ${ type} </h5>
<form action="${pageContext.request.contextPath }/product/list.do?cnum=${cnum }&type=${type }"
	method="post">
	<select name="list_filter" size="1">
		<option value="new"
			<c:if test="${list_filter=='new' }">selected</c:if>>신상품순</option>
		<option value="best"
			<c:if test="${list_filter=='best' }">selected</c:if>>인기상품순</option>
		<option value="lowprice"
			<c:if test="${list_filter=='lowprice' }">selected</c:if>>낮은가격순</option>
		<option value="highprice"
			<c:if test="${list_filter=='highprice' }">selected</c:if>>높은가격순</option>
	</select> <input type="submit" value="검색">
</form>
<div class="container">
	<div class="row">
			<ul><c:forEach var="pro" items="${requestScope.list }">
		<div class="col-sm-4">
					<a href="${cp }/product/detail.do?pnum=${pro.pnum}">
					<div style="position: relative;">
						<img src="../img/${pro.thumb_save}" width="300px" height="400px">
						<div style="position: absolute; top:340px; left:210px" >
							<button type="button" id="incart" ><img src="../img/btn-cart.png" alt="담기" width="50px" height="50px"></button>
						</div>
					</div>
					
					<div>
						<h3>${pro.name}<br></h3>
					</div>
					<div>
						${pro.price }
					</div>
					<div>
						${pro.description }
					</div>
						
					<c:set var="cp" value="${pageContext.request.contextPath }" />
					</a>
		</div>
				</c:forEach></ul>
	</div>
</div>

<!-- 페이징 -->
<div>
	<c:if test="${startPageNum>5}">
		<a href="${cp }/product/list.do?pageNum=${startPageNum-1}&cnum=${cnum }&type=${type }">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
		<c:choose>
			<c:when test="${i==pageNum }">
				<a
					href="${cp }/product/list.do?pageNum=${i}&cnum=${cnum }&type=${type }">
					<span style='color: lightsalmon'>[${i}]</span>
				</a>
			</c:when>
			<c:otherwise>
				<a
					href="${cp }/product/list.do?pageNum=${i}&cnum=${cnum }&type=${type }">
					<span style='color: gray'>[${i}]</span>
				</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${pageCount>endPageNum}">
		<a href="${cp }/product/list.do?pageNum=${endPageNum+1}">[다음]</a>
	</c:if>
</div>

</body>
</html>