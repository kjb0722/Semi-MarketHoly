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
<h1>신상품</h1>
<!-- 상품리스트 -->
<div class="container">
	<div class="row">
		<ul>
			<c:forEach var="pro" items="${requestScope.list }">
				<div class="col-sm-4">
					<a href="${cp }/product/detail.do?pnum=${pro.pnum}">
						<div style="position: relative;">
							<img src="${cp }/img/${pro.thumb_save}" width="300px"
								height="400px">
							<div style="position: absolute; top: 340px; left: 210px">
								<button type="button" id="incart">
									<img src="../img/btn-cart.png" alt="담기" width="50px"
										height="50px">
								</button>
							</div>
						</div>

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
			<li class="page-item"><li class="page-item"><a
				href="${cp }/product/new.do?pageNum=${endPageNum+1}">></a>
		</li></c:if>


	</ul>
</div>

</body>
</html>