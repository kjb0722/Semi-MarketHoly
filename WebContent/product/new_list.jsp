<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
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
						<div style="position: relative;">
							<img src="${cp }/img/${pro.thumb_save}" width="300px"
								height="400px">
							<div style="position: absolute; top: 340px; left: 210px">
								<button data-toggle="modal" data-target="#cartmodal"
								class="btn btn-link" onclick="getProd('${pro.name}',${pro.price },${pro.pnum })">
								<img src="../img/btn-cart.png" id="incart" alt="담기" width="50px"
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

<!-- 팝업창 -->
<div class="modal fade" id="cartmodal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm" style="max-width: 100%; width: 420px; display: table;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">x</span>
				</button>
				<h1 class="modal-title" id="myModalLabel">상품선택</h1>
			</div>
			<div class="modal-body">
				<label id="name"></label><br>
				<hr style="border: solid 1px purple;">
				<label id="opname"></label><br>
				<label id="price"></label>원
				<input type="hidden" id="pnum"> 
				<div class="pull-right">
				<button type="button" class="btn btn-default" onclick="minus()">
					<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
				</button>
				&nbsp<label id="EA">1</label>&nbsp
				<button type="button" class="btn btn-default" onclick="plus()">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
				</button>
				</div>  
				
				
			</div>
			<div class="modal-footer">
				<div class="pull-left">합계</div>  <label id="sum"></label> &nbsp원<br>
				구매시 0.1% 적립<br>
				<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-primary" onclick="incart('${id}','${pro.pnum}')">장바구니 담기</button>
			</div>
		</div>
	</div>
</div>

</body>

<script>
	// 부모창에서 모달로 데이터넘기기
	function getProd(name,price,pnum) {		
		var pname=document.getElementById("name");
		var pprice=document.getElementById("price");
		var opname=document.getElementById("opname");
		var sum=document.getElementById("sum");
		var EA=document.getElementById("EA");
		var ppnum=document.getElementById("pnum");
		ppnum.value=pnum;
		EA=parseInt(EA.innerHTML);
		pname.innerHTML = name;
		opname.innerHTML = name;
		pprice.innerHTML = price;
		sum.innerHTML =price*EA;    
		
	}
	function plus() {
		var EA=document.getElementById("EA");
		var price=document.getElementById("price");
		var sum=document.getElementById("sum");
		EA.innerHTML=parseInt(EA.innerHTML)+1;
		sum.innerHTML =parseInt(price.innerHTML)*parseInt(EA.innerHTML); 
	}
	function minus() {
		var EA=document.getElementById("EA");
		var price=document.getElementById("price");
		var sum=document.getElementById("sum");
		
		if(EA.innerHTML<=1){
			alert("최소수량입니다");  
		}else{
		EA.innerHTML =parseInt(EA.innerHTML)-1;
		sum.innerHTML =parseInt(price.innerHTML)*parseInt(EA.innerHTML);  
		}
	}
	function incart(id,pnum) {		
		var pnum=document.getElementById("pnum").value;
		var EA=document.getElementById("EA");
		location = "${cp }/member/cartAdd.do?pnum="+pnum+"&id="+id+"&EA="+parseInt(EA.innerHTML);
		
	}


</script>

</html>