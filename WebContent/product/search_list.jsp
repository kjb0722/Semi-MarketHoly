<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.pagination {
	display: block;
	text-align: center;
}

.pagination>li>a {
	float: none;
}
</style>
<!-- 검색한 상품 리스트 : 필터넣기 / 카테고리는 없어도됨 / 아이디 받아야됨 (장바구니) 
	이름일부만 넣으면 검색 상품명으로-->
<hr style="border: solid 2px RebeccaPurple;">
<h1>상품검색</h1>
<h4>신선한 홀리의 상품을 검색해보세요.</h4>
<hr style="border: solid 1px RebeccaPurple;">
<!-- 상품리스트 -->
<div class="container">
	<div class="row">
		<ul>
			<c:forEach var="pro" items="${requestScope.list }">
				<div class="col-sm-4">
					총 ${pageCount*9 }개의 상품이 검색되었습니다.
					<a href="${cp }/product/detail.do?pnum=${pro.pnum}">
						<div style="position: relative;">
							<img src="${cp }/img/${pro.thumb_save}" width="300px"
								height="400px">
							<div style="position: absolute; top: 340px; left: 210px">
								<button data-toggle="modal" data-target="#cartmodal"
									class="btn btn-link"
									onclick="getProd('${pro.name}',${pro.price })">
									<img src="../img/btn-cart.png" alt="담기" width="50px"
										height="50px">
								</button>
							</div>
						</div> <a href="${cp }/product/detail.do?pnum=${pro.pnum}">
							<div>
								<h3>${pro.name}<br>
								</h3>
							</div>
							<div>${pro.price }</div>
							<div>${pro.description }</div> <c:set var="cp"
								value="${pageContext.request.contextPath }" />
					</a>
				</div>
			</c:forEach>
		</ul>
	</div>
</div>
<br>
<br>

<!-- 페이징 -->
<div>
	<ul class="pagination pagination-lg">
		<li class="page-item"><c:if test="${pageNum>1}">
				<li class="page-item"><a
					href="${cp }/product/new.do?pageNum=${pageNum-1}&cnum=${cnum }&type=${type }">
						&laquo; </a>
			</c:if></li>
		<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
			<c:choose>
				<c:when test="${i==pageNum }">
					<li class="page-item active"><a
						href="${cp }/product/new.do?pageNum=${i}&cnum=${cnum }&type=${type }">
							<span style='color: white'>${i}</span>
					</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a
						href="${cp }/product/new.do?pageNum=${i}&cnum=${cnum }&type=${type }">
							<span style='color: gray'>${i}</span>
					</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${pageCount>endPageNum}">
			<li class="page-item">
			<li class="page-item"><a
				href="${cp }/product/new.do?pageNum=${endPageNum+1}">></a></li>
		</c:if>


	</ul>
</div>

</body>
</html>