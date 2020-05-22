<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
table, th, td {
	text-align: center;
}

nav {
	list-style: none;
	margin: 0;
	padding: 0;
	text-align: center;
}

.cursor-pointer {
	cursor: pointer;
}

.hidden {
	display: none;
}
</style>
<div class="container">
	<div class="row">
		<h3>회원 관리</h3>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="form-inline form-group">
				<select class="form-control" name="type">
					<c:forEach var="dto" items="${comList }">
						<option value="${dto.val }">${dto.name }</option>
					</c:forEach>
				</select>
				<input type="text" class="form-control" id="word" placeholder="검색명을 입력하세요." name="word">
				<input type="button" class="btn btn-primary" id="btnSearch" value="검색">
				<input type="button" class="btn btn-success" id="btnAllSearch" value="전체 검색">
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th style="width: 5%;">번호</th>
						<th style="width: 10%;">아이디</th>
						<th style="width: 8%;">이름</th>
						<th style="width: 7%;">등급</th>
						<th style="width: 12%;">이메일</th>
						<th class="hidden">생일</th>
						<th class="hidden">전화번호</th>
						<th style="width: 5%">성별</th>
						<th style="width: 15%;">주소</th>
						<!-- <th style="width: 13%">가입날짜</th> -->
						<th style="width: 8%;">포인트</th>
						<th style="width: 5%">탈퇴</th>
						<th style="width: 12%;">탈퇴 날짜</th>
						<th style="width: 5%">수정</th>
						<th style="width: 5%">탈퇴</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>
		</div>
	</div>

	<div class="modal fade" id="memModify" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">회원 정보 수정</h5>
				</div>
				<div class="modal-body">
					<div class="row form-group">
						<div class="col-md-4">
							<h4>
								<label class="label label-success">회원 번호</label>
							</h4>
							<input type="text" class="form-control" id="mNum" disabled="disabled">
						</div>
						<div class="col-md-4">
							<h4>
								<label class="label label-success">아이디</label>
							</h4>
							<input type="text" class="form-control" id="mId" disabled="disabled">
						</div>
					</div>
					<div class="row form-group">
						<div class="col-md-4">
							<h4>
								<label class="label label-success">이름</label>
							</h4>
							<input type="text" class="form-control" id="mName">
						</div>
						<div class="col-md-4">
							<h4>
								<label class="label label-success">생일</label>
							</h4>
							<input type="text" class="form-control" id="mBirth">
						</div>
					</div>
					<div class="row form-group">
						<div class="col-md-6">
							<h4>
								<label class="label label-success">이메일</label>
							</h4>
							<input type="text" class="form-control" id="mEmail">
						</div>
					</div>
					<div class="row form-group">
						<div class="col-md-4">
							<h4>
								<label class="label label-success">전화번호</label>
							</h4>
							<input type="text" class="form-control" id="mPhone">
						</div>
						<div class="col-md-6">
							<h4>
								<label class="label label-success">성별</label>
							</h4>
							<input type="radio" id="men" name="gender" value="1">
							<label class="form-check-label" for="men">남자</label>
							<input type="radio" id="women" name="gender" value="2" checked>
							<label class="form-check-label" for="women">여자</label>
						</div>
					</div>
					<div class="row form-group">
						<div class="col-md-8">
							<h4>
								<label class="label label-success">주소</label>
							</h4>
							<input type="text" class="form-control" id="mAddress">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="btnMemModi">수정</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
	<div id="page-div" class="row"></div>
</div>

<script>
	//페이지 로드시에 리스트 불러오기
	$(document).ready(function() {
		getMemList("","",1);		
	});

	//검색 버튼 이벤트
	$("#btnSearch").click(function() {
		memSearch();
	});

	//검색명 엔터 이벤트
	$("#word").keydown(function(e) {
		if(e.keyCode == 13){
			memSearch();
		}
	});
	
	//검색
	function memSearch(){
		let word = $("#word").val();
		if(word == ""){
			alert("검색명을 입력하세요.");
			return false;
		}
		let type = $("select[name=type]").val();
		
		getMemList(word, type, 1);
	}
	
	//전체 검색 버튼 이벤트
	$("#btnAllSearch").on("click", function(){
		getMemList("","",1);
	});
	
	//ajax JSON으로 목록 불러오기
	let xhr;
	function getMemList(word, type, pageNum) {
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = getMemListOk;
		xhr.open("get", `${cp}/admin/memList.do?word=${'${word}'}&type=${'${type}'}&pageNum=${'${pageNum}'}`, true);
		xhr.send();
	}
	
	function getMemListOk() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			removeList();
			let tbody = $("tbody");
			let json = JSON.parse(xhr.responseText);
			for(let j of json[0]){
				let row = "<tr>";
				row += "<td>"+j.num+"</td>";
				row += "<td>"+j.id+"</td>";
				row += "<td>"+j.name+"</td>";
				row += "<td>"+j.rating+"</td>";
				row += "<td>"+j.email+"</td>";
				row += "<td class='hidden'>"+j.birth+"</td>";
				row += "<td class='hidden'>"+j.phone+"</td>";
				if(j.gender == 1){
					row += "<td>남</td>";				
				}else{
					row += "<td>여</td>";
				}
				row += "<td>"+j.addr+"</td>";
				/* row += "<td>"+j.reg_date+"</td>"; */
				row += "<td>"+j.point+"</td>";
				row += "<td>"+j.del_yn+"</td>";
				if(j.del_date == null){
					row += "<td></td>";
				}else{
					row += "<td>"+j.del_date+"</td>";				
				}
				row += "<td>";
				row += "<button class='btn btn-xs btn-info glyphicon glyphicon-pencil' ";
				row += "name='btnModify' data-toggle='modal' data-target='#memModify'></button>";
				row += "</td>";
				row += "<td><button class='btn btn-xs btn-danger glyphicon glyphicon-trash' name='btnDelete'></button></td>";
				row += "</tr>";
				tbody.append(row);
				
				//탈퇴 회원이면 색상
				if(j.del_yn == "Y"){
					$("tbody tr").last().css("background-color", "#F08080");
				}
			}
			
			//페이징//
			pageDiv = $("#page-div");
			pageDiv.empty();
			
			let startPageNum = json[1];
			let endPageNum = json[2];
			let pageNum = json[3];
			let pageCount = json[4];
			
			let row = "<nav>";
			row += "<ul class='pagination'>";
			
			if(startPageNum != 1){
				row += "<li>";
				row += "<a onclick='pageMove("+(pageNum-1)+")' class='cursor-pointer' aria-label='Previous'>";
				row += "<span aria-hidden='true'>&laquo</span>";
				row += "</a>";
				row += "</li>";
			}
			
			for(let i=startPageNum;i<=endPageNum;i++){
				row += "<li><a onclick='pageMove("+i+")' class='cursor-pointer'>"+i+"</a></li>";
			}
			
			if(pageCount > endPageNum){
				row += "<li>";
				row += "<a onclick='pageMove("+(pageNum+1)+")' class='cursor-pointer' aria-label='Next'>";
				row += "<span aria-hidden='true'>&raquo;</span>";
				row += "</a>";
				row += "</li>";
			}
			
			row += "</ul>";
			row += "</nav>";
			pageDiv.append(row);
			//페이징//
			
			//삭제 이벤트
			$("button[name=btnDelete]").click(function() {
				let num = $(this).parent().parent().children().eq(0).text();
				memDelete(num);
			});
			
			//수정 이벤트
			$("button[name=btnModify]").click(function(){
				let tr = $(this).parent().parent().children();
				let num = tr.eq(0).text();
				let id = tr.eq(1).text();
				let name = tr.eq(2).text();
				let email = tr.eq(4).text();
				let birth = tr.eq(5).text();
				let phone = tr.eq(6).text();
				let gender = tr.eq(7).text();
				let addr = tr.eq(8).text();
				$("#mNum").val(num);
				$("#mId").val(id);
				$("#mName").val(name);
				$("#mEmail").val(email);
				$("#mBirth").val(birth);
				$("#mPhone").val(phone);
				if(gender == "남"){
					$("#men").prop("checked",true);
					$("input:radio[name='test']:input[value='1']").prop("checked",true);
				}else{
					$("#women").prop("checked",true);
					$("input:radio[name='test']:input[value='2']").prop("checked",true);
				}
				$("#mAddress").val(addr);
			});
		}
	}
	
	function pageMove(page){
		getMemList("","",page);
	}
	
	//탈퇴//
	function memDelete(num){
		let result = confirm("선택하신 회원을 삭제하시겠습니까?");
		if(result){
			jQuery.ajax({
		           url:`${cp}/admin/memDel.do`,
		           method:"GET",
		           data:{num:num},
		           dataType:"JSON",
		           success : function(data) {
		        	   if(data.n > 0){
		        		   alert("탈퇴 처리 완료");
		        		   getMemList("","",1);
		        	   }else{
		        		   location = `${cp}/error.do`;
		        	   }
		           }
		     });
		}
	}
	//탈퇴//
	
	//목록 전체 삭제
	function removeList() {
		$("table>tbody").empty();
	}
	
	//수정//
	$("#btnMemModi").click(function(){
		let result = confirm("수정하시겠습니까?");
		if(result){
			memModify();
		}
	});
	function memModify(){
		let num = $("#mNum").val();
		let name = $("#mName").val();
		let birth = $("#mBirth").val();
		let email = $("#mPhone").val();
		let gender = $("input[name='gender']:checked").val();
		let phone = $("#mPhone").val();
		let addr = $("#mAddress").val();
		jQuery.ajax({
			url:`${cp}/admin/memModify.do`,
			method:"get",
			data:{num:num,
				name:name,
				birth:birth,
				email:email,
				gender:gender,
				phone:phone,
				addr:addr},
			dataType:"JSON",
			success: function(data){
				if(data.n>0){
					alert("회원 정보 수정 완료");
					$("#memModify").modal("hide");
					getMemList("","",1);
				}else{
					location = `${cp}/error.jsp`;
				}
			}
		});
	}
	//수정//
</script>
