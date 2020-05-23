<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
table, th, td {
	text-align: center;
}

.table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th,
	.table>thead>tr>td, .table>thead>tr>th {
	vertical-align: middle;
}

#order-table>tbody>tr>td>img{
	width: 200px;
	height: 200px;
}
</style>
<div class="container">
	<div class="row">
		<h3>주문 상세</h3>
	</div>
	<div class="row">
		<table class="table table-bordered" id="order-table">
			<thead>
				<tr>
					<th style="width: 10%;">번호</th>
					<th style="width: 10%;">썸네일</th>
					<th style="width: 25%;">상품명</th>
					<th style="width: 10%;">개수</th>
					<th style="width: 10%;">가격</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dto" items="${detailList }">
					<tr>
						<td>${dto.opnum }</td>
						<td><img src="${cp }/img/${dto.thumb_save }"></td>
						<td>${dto.pname }</td>
						<td>${dto.ea }</td>
						<td>${dto.price }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="row">
		<div class="col-md-2">
			<span class="label label-success">아이디</span>
			<input type="text" class="form-control" disabled="disabled" value="${ordInfo.id }">
		</div>
	</div>
	<div class="row">
		<div class="col-md-5">
			<span class="label label-success">주소</span>
			<input type="text" class="form-control" disabled="disabled" value="${ordInfo.addr }">
		</div>
	</div>
	<div class="row">
		<div class="col-md-2">
			<span class="label label-success">구매일자</span>
			<input type="text" class="form-control" disabled="disabled" value="${ordInfo.reg_date }">
		</div>
		<div class="col-md-2">
			<span class="label label-success">사용 포인트</span>
			<input type="text" class="form-control" disabled="disabled" value="${ordInfo.use_point }">
		</div>
	</div>
	<div class="row">
		<div class="col-md-2">
			<span class="label label-success">결제 여부</span>
			<input type="text" class="form-control" disabled="disabled" value="${ordInfo.pay_yn }">
		</div>
		<div class="col-md-2">
			<span class="label label-success">결제 방법</span>
			<input type="text" class="form-control" disabled="disabled" value="${ordInfo.pay_wayName }">
		</div>
	</div>
	<div class="row">
		<div class="col-md-2">
			<span class="label label-success">결제 금액</span>
			<input type="text" class="form-control" disabled="disabled" value="${ordInfo.price }">
		</div>
	</div>
</div>
