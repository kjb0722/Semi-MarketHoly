<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form class="form-inline" role="form" action="${cp }/admin/catAdd.do"
	method="get" onsubmit="return catChk()">
	<span class="label label-success">카테고리</span><br> <input
		type="text" class="form-control" placeholder="카테고리를 입력하세요."
		name="catName" maxlength="10">
	<button type="submit" class="btn btn-lg btn-primary">추가</button>
</form>
<br>
<form class="form-inline" role="form"
	action="${cp }/admin/catTypeAdd.do" method="get" onsubmit="return catTypeChk()">
	<span class="label label-success">종류</span><br> <select name="cat"
		class="form-control">
		<c:forEach var="dto" items="${catList }">
			<option value="${dto.cnum }">${dto.name }</option>
		</c:forEach>
	</select> <input type="text" class="form-control" placeholder="종류를 입력하세요."
		name="catTypeName">
	<button type="submit" class="btn btn-lg btn-primary">추가</button>
</form>

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
						class="btn btn-info" onclick="modalVal(${dto.cnum},'${dto.name}')">수정</button></td>
				<td><button class="btn btn-danger" onclick="delCat(${dto.cnum})">삭제</button></td>
				<td>${dto.tnum }</td>
				<td>${dto.tname }</td>
				<c:choose>
					<c:when test="${dto.tname != null }">
						<td><button data-toggle="modal" data-target="#catUpdate"
								class="btn btn-info"
								onclick="modalVal(${dto.tnum},'${dto.tname}')">수정</button></td>
						<td><button class="btn btn-danger" onclick="delCat(${dto.tnum})">삭제</button></td>
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

<script type="text/javascript">
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
	
	function catChk(){
		let catName = document.getElementsByName("catName")[0].value;
		if(catName == ""){
			alert("카테고리명을 입력하세요.");
			return false;
		}
		return true;
	}
	
	function catTypeChk(){
		let cat = document.getElementsByName("cat")[0].value;
		if(cat == ""){
			alert("카테고리가 없습니다.");
			return false;
		}
		let catTypeName = document.getElementsByName("catTypeName")[0].value;
		if(catTypeName == ""){
			alert("타입명을 입력하세요.");	
			return false;
		}
		return true;
	}
</script>