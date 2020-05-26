<%@page import="com.market.review.dto.ReviewDto"%>
<%@page import="com.market.review.dao.ReviewDao"%>
<%@page import="com.market.product.dao.ProductDao"%>
<%@page import="com.market.product.dto.ProductDto"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>

.info {
	float: left;
	width: 50%;
}

.container {
	clear: both
}

#t {
	float: right;
}

#img {
	width: 90%;
	height: 85%;
}

#discript_img {
	width: 100%;
	height: 1100px
}

#imgwrap {
	width: 50%;
	float: left;
	text-align: center;
}

h1, h2, h3 {
	display: inline
}

.nav-tabs a {
	text-decoration: none;
	color: black;
}

.nav-tabs>li>a {
	margin-right: 3px;
	line-height: 1.42857143;
	border: 1px solid lightgray;
	border-radius: 6px 6px 0 0;
}
.nav>li>a:focus,.nav>li>a:hover{
	background-color: lightgray;
	text-decoration: none;
}
</style>
<div id="imgwrap">
	<div class="info" id="img">
		<img src="${pageContext.request.contextPath }/img/${dto.thumb_save}" id="img" >
	</div>
</div>
<div class="info">
	<c:if test="${memberDto.rating == 99 }">
		<div class="row">
			<div class="well col-md-8 form-inline form-group">
				<div class="form-group">
					<span class="label label-success">관리자 메뉴</span>
					<input type="button" class="btn btn-default" value="상품 수정" id="btnModify" data-toggle='modal' data-target='#prodModify'>
					<input type="button" class="btn btn-default" value="상품 삭제" id="btnDelete">
				</div>
			</div>
		</div>
	</c:if>
	<h1>${dto.name}</h1>
	<h4 class='text-muted'>${dto.description}</h4>
	<h1>${dto.price}</h1>
	원
	<hr style="border: solid 1px RebeccaPurple;">
	<h4>
		구매수량&nbsp&nbsp
		<button type="button" class="btn btn-default" onclick="minus()">
			<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
		</button>
		<label id="EA">1</label>
		<button type="button" class="btn btn-default" onclick="plus()">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
		</button>
	</h4>
	<br>
	<input type="hidden" id="price" value="${dto.price }">
	<p class='text-muted' style="font-size: 0.9em;">
		<!-- 판매단위 1팩
		<br>
		중량 용량 200g
		<br>
		원산지 국산
		<br>
		포장타입 냉장/종이포장
		<br>
		유통기한 농산물로 별도 유통기한은 없으나 가급적 빨리 드시기 바랍니다.
		<br>
		안내사항 -상품특성상 3%내외의 중량차이가 발생할 수 있습니다.
		<br> -->
		판매단위 ${prodInfo.unit }
		<br>
		중량 용량 ${prodInfo.volume }
		<br>
		원산지 ${prodInfo.origin }
		<br>
		포장타입 ${prodInfo.pack_type }
		<br>
		유통기한 ${prodInfo.shelf_life }
		<br>
		안내사항 ${prodInfo.guidance }
		<br>
	</p>
	<hr style="border: solid 1px RebeccaPurple;">
	<div id="t">
		<h3>총 상품금액 :</h3>
		<h1>
			<label id="sum">${dto.price }</label>
		</h1>
		원
		<br>

		<button type="button" class="btn-lg pull-right" id="incart" style="background-color: RebeccaPurple; color: white" onclick="incart()">장바구니 담기</button>
	</div>

</div>


<ul id="myTab" class="nav nav-tabs" role="tablist">
	<li>
		<a href="#discript" data-toggle="tab" tabindex="1" id="discript">상품설명</a>
	</li>
	<li>
		<a href="#review" data-toggle="tab" tabindex="2" id="review">상품후기</a>
	</li>
	<li>
		<a href="#qna" data-toggle="tab" tabindex="3" id="qna">상품문의</a>
	</li>
</ul>
<br>

<div id="myTabContent" class="tab-content">
	<!-- 상품상세탭 -->
	<div class="tab-pane_1 active in">
		<img src="${pageContext.request.contextPath}/img/${dto.detail_save}" id="discript_img">
	</div>


	<!-- 리뷰탭 -->
	<div class="tab-pane_2"></div>

	<!-- qna탭 -->
	<div class="tab-pane_3"></div>
</div>


<section class="container">
	<c:choose>
		<c:when test="${param.tabpage == null}">
		</c:when>
		<c:otherwise>
			<jsp:include page="/${param.tabpage}"></jsp:include>
		</c:otherwise>
	</c:choose>
</section>

<div class="modal fade" id="prodModify" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h2 class="modal-title">상품 수정</h2>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-5 form-group">
						<label class="label label-success">상품명</label>
						<input id="txtName" type="text" class="form-control" value="${dto.name }" placeholder="상품명을 입력하세요" maxlength="30">
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 form-group">
						<label class="label label-success">상품 설명</label>
						<textarea id="txtDesc" class="form-control" rows="3" cols="50" style="resize: none;">${dto.description }</textarea>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 form-group">
						<label class="label label-success">가격</label>
						<input id="txtPrice" type="text" class="form-control" value="${dto.price }" placeholder="가격을 입력하세요" maxlength="30">
					</div>
					<div class="col-md-6 form-group">
						<label class="label label-success">재고</label>
						<input id="txtStock" type="text" class="form-control" value="${dto.stock }" placeholder="가격을 입력하세요" maxlength="30">
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 form-group">
						<label class="label label-success">판매 단위</label>
						<input id="txtUnit" type="text" class="form-control" value="${prodInfo.unit }" placeholder="판매 단위를 입력하세요" maxlength="30">
					</div>
					<div class="col-md-6 form-group">
						<label class="label label-success">용량</label>
						<input id="txtVolume" type="text" class="form-control" value="${prodInfo.volume }" placeholder="용량을 입력하세요" maxlength="30">
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 form-group">
						<label class="label label-success">원산지</label>
						<input id="txtOrigin" type="text" class="form-control" value="${prodInfo.origin }" placeholder="원산지를 입력하세요" maxlength="30">
					</div>
					<div class="col-md-6 form-group">
						<label class="label label-success">포장 종류</label>
						<input id="txtPackType" type="text" class="form-control" value="${prodInfo.pack_type }" placeholder="포장 종류를 입력하세요" maxlength="30">
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 form-group">
						<label class="label label-success">유통 기한</label>
						<input id="txtShelfLife" type="text" class="form-control" value="${prodInfo.shelf_life }" placeholder="유통 기한을 입력하세요" maxlength="30">
					</div>
					<div class="col-md-6 form-group">
						<label class="label label-success">안내사항</label>
						<input id="txtGuidance" type="text" class="form-control" value="${prodInfo.guidance }" placeholder="안내사항을 입력하세요" maxlength="30">
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btnModifyOk">상품 수정</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready = function(){
		
	}
	function plus() {
		var EA = document.getElementById("EA");
		var price = document.getElementById("price").value;
		var sum = document.getElementById("sum");
		EA.innerHTML = parseInt(EA.innerHTML) + 1;
		sum.innerHTML = price * parseInt(EA.innerHTML);
	}
	function minus() {
		var EA = document.getElementById("EA");
		var price = document.getElementById("price").value;
		var sum = document.getElementById("sum");

		if (EA.innerHTML <= 1) {
			alert("최소수량입니다");
		} else {
			EA.innerHTML = parseInt(EA.innerHTML) - 1;
			sum.innerHTML = price * parseInt(EA.innerHTML);
		}
	}
	function incart() {
		var id = '${sessionScope.memberDto.id}';
		var pnum = ${param.pnum};
		var EA = document.getElementById("EA");
		location = "${cp }/member/cartAdd.do?pnum=" + pnum + "&id=" + id
				+ "&EA=" + parseInt(EA.innerHTML);

	}

	$(document).ready(function(e) {

		var num = "${plag}";
		if (num !== "1") {
			$('.tab-pane_1').hide();
		}

	});

	$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
		var index = $(e.target).prop("tabindex");

		if (index === 1) {
			location = "${cp}/product/detail.do?pnum=" + ${dto.pnum};
		} else if (index === 2) {
			location = "${cp}/review/listReview.do?pnum=" + ${dto.pnum};
		} else if (index === 3) {
			location = "${cp}/qna/qnaList.do?pnum=" + ${dto.pnum};
		}

	});

	//상품 삭제 버튼 이벤트
	$("#btnDelete").click(function() {
		if (confirm("해당 상품을 삭제하시겠습니까?")) {
			jQuery.ajax({
				dataType:"JSON",
				url:`${cp}/admin/prodDel.do`,
				method:"get",
				data:{pnum:${dto.pnum}},
				success:function(data){
					if(data.n > 0){
						alert("삭제 성공");
						location = `${cp}/product/list.do?cnum=${dto.cnum }`;
					}
				}
			});
		}
	});
	
	//상품 수정 모달 버튼 이벤트
	$("#btnModifyOk").click(function() {
		let name = $("#txtName").val();
		let desc = $("#txtDesc").val();
		let price = $("#txtPrice").val();
		if(name == ""){
			alert("상품명을 입력하세요.");
			return;
		}
		if(desc == ""){
			alert("상품 설명을 입력하세요.");
			return;
		}
		if(price == ""){
			alert("가격을 입력하세요.");
			return;
		}
		
		let stock = $("#txtStock").val();
		let unit = $("#txtUnit").val();
		let volume = $("#txtVolume").val();
		let origin = $("#txtOrigin").val();
		let pack_type = $("#txtPackType").val();
		let shelf_life = $("#txtShelfLife").val();
		let guidance = $("#txtGuidance").val();

		jQuery.ajax({
			dataType: "JSON",
			url: `${cp}/admin/prodModify.do`,
			method: "post",
			data:{pnum:${dto.pnum},
				name:name,
				desc:desc,
				price:price,
				stock:stock,
				unit:unit,
				volume:volume,
				origin:origin,
				pack_type:pack_type,
				shelf_life:shelf_life,
				guidance:guidance},
			success:function(data){
				if(data.n > 0){
					alert("상품 수정 완료");
					location.reload();
				}else{
					location = `${cp}/error.do`;
				}
			}
		});
	});
</script>
