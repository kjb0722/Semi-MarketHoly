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
					<h1>${dto.val }</h1>
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
					<th style="width: 5%">번호</th>
					<th>주문자</th>
					<th>상태</th>
					<th>주문 상품</th>
					<th>결제</th>
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
		orderListLoad($("#kind").val(), "", 1, $("#cboStatus").val());
	});
	
	//주문 상태 콤보박스 이벤트//
	$("#cboStatus").change(function() {
		$("#btnSearch").click();
	});
	//주문 상태 콤보박스 이벤트//
	
	//검색 text 이벤트//
	$("#txtWord").keyup(function() {
		$("#btnSearch").click();
	});
	//검색 text 이벤트//
	
	//검색 버튼 이벤트//
	$("#btnSearch").click(function() {
		let kind = $("#kind").val();
		let word = $("#txtWord").val();
		let status = $("#cboStatus").val();
		orderListLoad(kind,word,1,status);
	});
	//검색 버튼 이벤트//

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
				tbodyRowAdd(data[0]);
			}
		});
	}
	//주문 목록 로드//
	
	function tbodyRowAdd(data){
		let tbody = $("#order-table>tbody");
		tbody.empty();
		for(let dto of data){
			let row = "<tr>";
			row += "<td>"+dto.onum+"</td>";
			row += "<td>"+dto.id+"</td>";
			row += "<td>"+dto.statusName+"</td>";
			row += "<td>"+dto.prodName+"</td>";
			row += "<td>"+dto.pay_yn+"</td>";
			row += "<td>"+dto.price+"</td>";
			row += "<td>"+dto.addr+"</td>";
			row += "<td>"+dto.pay_wayName+"</td>";
			row += "<td>"+dto.use_point+"</td>";
			row += "<td>"+dto.reg_date+"</td>";
			row += "</tr>";
			tbody.append(row);
		}
	}
</script>