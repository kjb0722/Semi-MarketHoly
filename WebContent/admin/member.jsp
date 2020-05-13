<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
table, th, td {
	text-align: center;
}
</style>
<div class="container">
	<div class="form-inline">
		<select class="form-control" name="type">
			<c:forEach var="dto" items="${comList }">
				<option value="${dto.val }">${dto.name }</option>
			</c:forEach>
		</select> <input type="text" class="form-control" id="word"
			placeholder="검색명을 입력하세요." name="word"> <input type="button"
			class="btn btn-primary" id="btnSearch" value="검색"> <input
			type="button" class="btn btn-success" id="btnAllSearch" value="전체 검색">
	</div>

	<div>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>회원 번호</th>
					<th>아이디</th>
					<th>이름</th>
					<th>등급</th>
					<th>이메일</th>
					<th>생일</th>
					<th>전화번호</th>
					<th>성별</th>
					<th>주소</th>
					<th>가입날짜</th>
					<th>포인트</th>
					<th>탈퇴 여부</th>
					<th>탈퇴 날짜</th>
					<th>수정</th>
					<th>탈퇴</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>
</div>

<script>
	//페이지 로드시에 리스트 불러오기
	$(document).ready(function() {
		getMemList("","");		
	});

	//검색 버튼 이벤트
	$("#btnSearch").on("click", function() {
		let word = $("#word").val();
		if(word == ""){
			alert("검색명을 입력하세요.");
			return false;
		}
		let type = $("select[name=type]").val();
		
		getMemList(word, type);
	});
	
	//전체 검색 버튼 이벤트
	$("#btnAllSearch").on("click", function(){
		getMemList("","");
	});
	
	//ajax JSON으로 목록 불러오기
	let xhr;
	function getMemList(word, type) {
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = getMemListOk;
		xhr.open("get", `${cp}/admin/memList.do?word=${'${word}'}&type=${'${type}'}`, true);
		xhr.send();
	}
	
	function getMemListOk() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			removeList();
			let json = JSON.parse(xhr.responseText);
			for(let j of json){
				let row = "<tr>";
				row += "<td>"+j.num+"</td>";
				row += "<td>"+j.id+"</td>";
				row += "<td>"+j.name+"</td>";
				row += "<td>"+j.rating+"</td>";
				row += "<td>"+j.email+"</td>";
				row += "<td>"+j.birth+"</td>";
				row += "<td>"+j.phone+"</td>";
				if(j.gender == 1){
					row += "<td>남</td>";				
				}else{
					row += "<td>여</td>";
				}
				row += "<td>"+j.addr+"</td>";
				row += "<td>"+j.reg_date+"</td>";
				row += "<td>"+j.point+"</td>";
				row += "<td>"+j.del_yn+"</td>";
				if(j.del_date == null){
					row += "<td></td>";
				}else{
					row += "<td>"+j.del_date+"</td>";				
				}
				row += "<td><button class='btn btn-xs btn-info glyphicon glyphicon-pencil' onclick='memModify(this)'></button></td>";
				row += "<td><button class='btn btn-xs btn-danger glyphicon glyphicon-trash' onclick='memDelete(this)'></button></td>";
				row += "</tr>";
				$("tbody").append(row);
			}
		}
	}
	
	//목록 전체 삭제
	function removeList() {
		$("table>tbody").empty();
	}
	
	//수정 이벤트
	function memModify(target){
		console.log(target);
	}
	
	//삭제 이벤트
	function memDelete(target){
		
	}
</script>