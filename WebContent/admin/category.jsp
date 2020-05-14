<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<%-- <form class="form-inline" role="form" action="${cp }/admin/catAdd.do"
		method="get" onsubmit="return catChk()"> --%>
	<div class="form-inline">
		<span class="label label-success">카테고리</span><br> <input
			type="text" class="form-control" placeholder="카테고리를 입력하세요."
			name="catName" maxlength="10"> <input type="button"
			class="btn btn-primary" value="추가" id="btnCatAdd">
	</div>
	<!-- </form> -->
	<br>
	<%-- <form class="form-inline" role="form"
		action="${cp }/admin/catTypeAdd.do" method="get"
		onsubmit="return catTypeChk()"> --%>
	<div class="form-inline">
		<span class="label label-success">세부 카테고리 추가</span><br> <select
			name="cat" class="form-control">
			<c:forEach var="dto" items="${catList }">
				<option value="${dto.cnum }">${dto.name }</option>
			</c:forEach>
		</select> <input type="text" class="form-control" placeholder="종류를 입력하세요."
			name="catTypeName"> <input type="button"
			class="btn btn-primary" value="추가" id="btnCatTypeAdd">
	</div>
	<!-- </form> -->

	<table class="table table-striped">
		<thead>
			<tr>
				<th>카테고리 번호</th>
				<th>카테고리 이름</th>
				<th>수정</th>
				<th>삭제</th>
				<th>종류 번호</th>
				<th>종류 이름</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="dto" items="${catListAll }">
				<tr>
					<td>${dto.cnum }</td>
					<td>${dto.name }</td>
					<td><button data-toggle="modal" data-target="#catUpdate"
							class="btn btn-xs btn-info glyphicon glyphicon-pencil"
							onclick="modalVal(${dto.cnum},'${dto.name}')"></button></td>
					<td><button
							class="btn btn-xs btn-danger glyphicon glyphicon-trash"
							onclick="delCat(${dto.cnum})"></button></td>
					<td>${dto.tnum }</td>
					<td>${dto.tname }</td>
					<c:choose>
						<c:when test="${dto.tname != null }">
							<td><button data-toggle="modal" data-target="#catUpdate"
									class="btn btn-xs btn-info glyphicon glyphicon-pencil"
									onclick="modalVal(${dto.tnum},'${dto.tname}')"></button></td>
							<td><button
									class="btn btn-xs btn-danger glyphicon glyphicon-trash"
									onclick="delCat(${dto.tnum})"></button></td>
						</c:when>
						<c:otherwise>
							<td></td>
							<td></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="modal fade" id="catUpdate" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">카테고리 명을 입력하세요.</h5>
				</div>
				<div class="modal-body">
					<input type="hidden" id="catNum"> <input type="text"
						id="catName">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="btnCatUpdate">확인</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

	//카테고리 추가 기능//
	$("#btnCatAdd").on("click",catAdd);
	
	let xhr;
	function catAdd(){
		let catName = $("input[name=catName]").val();
		if(catName.value == ""){
			alert("카테고리명을 입력하세요.");
			catName.focus();
			return false;
		}
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = catAddOk;
		xhr.open("get", `${cp }/admin/catAdd.do?catName=${'${catName}'}`, true);
		xhr.send();
	}
	function catAddOk(){
		if(xhr.readyState == 4 && xhr.status == 200){
			let json = JSON.parse(xhr.responseText);
			if(json.n>0){
				alert("카테고리 추가 성공");
				catComboLoad();
			}else{
				alert("실패");
			}
		}
	}
	//카테고리 추가 기능//
	
	//카테고리 combobox 불러오기
	let xhrCat;
	function catComboLoad(){
		xhrCat = new XMLHttpRequest();
		xhrCat.onreadystatechange = catComboLoadOk;
		xhrCat.open("get", `${cp}/admin/catSel.do`, true);
		xhrCat.send();
	}
	function catComboLoadOk(){
		if(xhrCat.readyState == 4 && xhrCat.status == 200){
			let json = JSON.parse(xhr.responseText);
			console.log(json.length);
			for(let j of json){
				
			}
		}
	}
	
	function modalVal(catNum, catName) {
		$("#catNum").val(catNum);
		$("#catName").val(catName);
	}
	
	document.getElementById("btnCatUpdate").onclick = function(){
		let catNum = document.getElementById("catNum").value;
		let catName = document.getElementById("catName").value;
		location = `${cp}/admin/catUpdate.do?catNum=${'${catNum}'}&catName=${'${catName}'}`;
	};
	
	function delCat(catNum){
		location = `${cp}/admin/catDel.do?catNum=${'${catNum}'}`;
	}
	
	function catTypeChk(){
		let cat = document.getElementsByName("cat")[0];
		if(cat.value == ""){
			alert("카테고리가 없습니다.");
			cat.focus();
			return false;
		}
		let catTypeName = document.getElementsByName("catTypeName")[0];
		if(catTypeName.value == ""){
			alert("타입명을 입력하세요.");	
			catTypeName.focus();
			return false;
		}
		return true;
	}
</script>