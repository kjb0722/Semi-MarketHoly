<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
	<div class="row">
		<h3>세일 상품 관리</h3>
	</div>
	<div class="row">
		<div class="col-md-2">
			<span class="label label-success">카테고리</span>
			<select name="cat" class="form-control" onchange="catChange(this)">
				<c:forEach var="dto" items="${catList }">
					<option value="${dto.cnum }">${dto.name }</option>
				</c:forEach>
			</select>
		</div>
		<div class="col-md-2">
			<span class="label label-success">세부 카테고리명</span>
			<select name="catType" class="form-control">

			</select>
		</div>
	</div>
	<div class="row">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th class="col-md-1">선택</th>
					<th class="col-md-1">상품 번호</th>
					<th>썸네일</th>
					<th>상품명</th>
					<th>가격</th>
					<th>재고</th>
					<th>적용 할인</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		//최초 페이지 이동시 세부 카테고리 로드
		$("select[name=cat]").change();
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
			$("select[name=catType]").empty();
			for(let j of json){
				$("select[name=catType]").append("<option value='"+j.cnum+"|"+j.type+"''>"+j.name+"</option>");
			}
		}
	}
	//카테고리 변경시 세부 카테고리 로드//
</script>