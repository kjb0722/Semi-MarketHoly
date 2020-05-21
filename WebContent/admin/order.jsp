<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
table, th, td {
	text-align: center;
}
</style>
<div class="container">
	<div class="row">
		<h3>주문 관리</h3>
	</div>
	<div class="row">
		<div class="col-md-7 form-inline">
			<span class="label label-success">검색 종류</span>
			<select class="form-control" id="kind">
				<c:forEach var="dto" items="${comList }">
					<option value="${dto.val }">${dto.name }</option>
				</c:forEach>
			</select>
			<input type="text" class="form-control" placeholder="검색어를 입력하세요" maxlength="30" id="txtWord">
			<input type="button" class="btn btn-lg btn-primary" value="검색" id="btnSearch">
		</div>
		<div class="col-md-5 form-inline">
			<select class="form-control pull-right" id="cboStatus">
				<c:forEach var="dto" items="${statusList }">
					<option value="${dto.val }">${dto.name }</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<div class="row">
		<table class="table table-bordered" id="order-table">
			<thead>
				<tr>
					<th>주문 번호</th>
					<th>주문자</th>
					<th>상태</th>
					<th>주문 상품</th>
					<th>결제 여부</th>
					<th>가격</th>
					<th>주소</th>
					<th>결제 방법</th>
					<th>사용 포인트</th>
					<th>등록 날짜</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>
</div>

<script>
	$(document).ready(function() {
		orderListLoad("", "", 1, $("#cboStatus").val());
	});

	//주문 목록 로드//
	function orderListLoad(kind, word, pageNum, status) {
		jQuery.ajax({
			dataType:"JSON",
			url:`${cp}/admin/orderList.do`,
			method:"get",
			data:{kind:kind,
				word:word,
				pageNum:pageNum,
				status:status},
			success:function(data){
				alert(data);
			}
		});
	}
	//주문 목록 로드//
</script>