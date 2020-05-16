<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
table, th, td {
	text-align: center;
}
table>tbody>th>td {
	background-color: aqua;
	vertical-align: middle;
}
</style>
<div class="container">
	<div class="row">
		<h3>세일 상품 관리</h3>
	</div>
	<div class="row">
		<div class="col-md-2">
			<span class="label label-success">카테고리</span>
			<select id="cat" name="cat" class="form-control" onchange="catChange(this)">
				<c:forEach var="dto" items="${catList }">
					<option value="${dto.cnum }">${dto.name }</option>
				</c:forEach>
			</select>
		</div>
		<div class="col-md-2">
			<span class="label label-success">세부 카테고리명</span>
			<select id="catType" name="catType" class="form-control">

			</select>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th style="width: 5%"><input type="checkbox" id=""></th>
						<th style="width: 5%">상품 번호</th>
						<th style="width: 15%">썸네일</th>
						<th>상품명</th>
						<th style="width: 12.5%">가격</th>
						<th style="width: 12.5%">재고</th>
						<th>적용 할인</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		//최초 페이지 이동시 세부 카테고리 로드
		$("#cat").change();
		
		//상품 리스트 로드
		//prodListLoad();
	});

	//카테고리 변경시 세부 카테고리 로드//
	function catChange(e){
		if(e.value != ""){
			catTypeLoad(e.value);
		}
	}
	
	let xhr;
	function catTypeLoad(cat){
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = catTypeLoadOk;
		xhr.open("get", `${cp}/admin/catTypeSel.do?type=${'${cat}'}`, true);
		xhr.send();
	}
	
	function catTypeLoadOk(){
		if(xhr.readyState == 4 && xhr.status == 200){
			let json = JSON.parse(xhr.responseText);
			$("#catType").empty();
			for(let j of json){
				$("#catType").append("<option value='"+j.cnum+"|"+j.type+"''>"+j.name+"</option>");
			}
			
			prodListLoad();
		}
	}
	//카테고리 변경시 세부 카테고리 로드//
	
	//상품 리스트 로드//
	function prodListLoad(){
		let num = $("#catType").val();
		if(num != null){
			let catNum = num.substr(0,num.indexOf("|"));
			let catTypeNum = num.substr(num.indexOf("|")+1);
			jQuery.ajax({
				dataType:"JSON",
				url: `${cp}/admin/saleProdList.do`,
				method:"get",
				data:{catNum:catNum,
					catTypeNum:catTypeNum},
				success:function(data){
					let table = $("table>tbody");
					table.empty();
					for(let dto of data){
						let row = "<tr class='align-middle'>";
						row += "<td><input type='checkbox' class='custom-control-input aria-label='Checkbox for following text input'></td>";
						row += "<td>"+dto.pnum+"</td>";
						row += `<td><img src='${cp}/img/${'${dto.thumb_save}'}'></td>`;
						row += "<td>"+dto.name+"</td>";
						row += "<td>"+dto.price+"</td>";
						row += "<td>"+dto.stock+"</td>";
						row += "<td></td>";
						row += "</tr>";
						table.append(row);
					}
					
					$("tbody>tr>td").css("vertical-align","middle");
				}
			});
		}else{
			alert("세부 카테고리가 없습니다.");
			return;
		}
	}
	//상품 리스트 로드//
	
	//세부 카테고리 이벤트
	$("#catType").change(function(){
		prodListLoad();
	});
	//세부 카테고리 이벤트
</script>