<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
table, th {
	text-align: center;
}
</style>
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

		</select> <input type="text" class="form-control" placeholder="종류를 입력하세요."
			name="catTypeName"> <input type="button"
			class="btn btn-primary" value="추가" id="btnCatTypeAdd">
	</div>
	<!-- </form> -->

	<table class="table table-bordered">
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
	$("document").ready(function() {
		catListLoad();
		catComboLoad();
	});
	
	//카테고리 추가 기능//
	$("#btnCatAdd").on("click",catAdd);
	let xhr;
	function catAdd(){
		let catName = $("input[name=catName]").val();
		if(catName == ""){
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
				catListLoad();
			}else{
				alert("실패");
			}
		}
	}
	//카테고리 추가 기능//
	
	//카테고리 combobox 불러오기//
	let xhrCat;
	function catComboLoad(){
		xhrCat = new XMLHttpRequest();
		xhrCat.onreadystatechange = catComboLoadOk;
		xhrCat.open("get", `${cp}/admin/catSel.do`, true);
		xhrCat.send();
	}
	function catComboLoadOk(){
		if(xhrCat.readyState == 4 && xhrCat.status == 200){
			let json = JSON.parse(xhrCat.responseText);
			$("select[name=cat]").empty();
			$("input[name=catName]").val("");
			for(let j of json){
				$("select[name=cat]").append("<option value="+j.cnum+">"+j.name+"</option>");
			}
		}
	}
	//카테고리 combobox 불러오기//
	
	//세부 카테고리 추가//
	$("#btnCatTypeAdd").on("click", catTypeAdd);
	let xhrCatType;
	function catTypeAdd(){
		let cat = $("select[name=cat]").val();
		if(cat == ""){
			alert("카테고리가 선택되지 않았습니다.");
			return;
		}
		let catTypeName = $("input[name=catTypeName]").val();
		if(catTypeName == ""){
			alert("세부 카테고리명을 입력해주세요.");
			return;
		}
		xhrCatType = new XMLHttpRequest();
		xhrCatType.onreadystatechange = catTypeAddOk;
		xhrCatType.open("get", `${cp}/admin/catTypeAdd.do?cat=${'${cat}'}&catTypeName=${'${catTypeName}'}`, true);
		xhrCatType.send();
	}
	function catTypeAddOk(){
		if(xhrCatType.readyState == 4 && xhrCatType.status == 200){
			let json = JSON.parse(xhrCatType.responseText);	
			if(json.n > 0){
				$("input[name=catTypeName]").val("");
				alert("세부 카테고리 추가 성공");
				catListLoad();
			}else{
				alert("실패");
			}
		}		
	}
	//세부 카테고리 추가//
	
	//카테고리 및 세부 카테고리 리스트 불러오기//
	let xhrCatList;
	function catListLoad(){
		xhrCatList = new XMLHttpRequest();
		xhrCatList.onreadystatechange = catListLoadOk;
		xhrCatList.open("get",`${cp}/admin/catSelAll.do`,true);
		xhrCatList.send();
	}
	function catListLoadOk(){
		if(xhrCatList.readyState == 4 && xhrCatList.status == 200){
			let json = JSON.parse(xhrCatList.responseText);
			$("tbody").empty();
			for(let j of json){
				let row = "<tr>";
				row += "<td>"+j.cnum+"</td>";
				row += "<td>"+j.name+"</td>";
				row += "<td>";
				row += "<button data-toggle='modal'";
				row += "data-target='#catUpdate'";
				row += "class='btn btn-xs btn-info glyphicon glyphicon-pencil'";
				row += "onclick='modalVal("+ j.cnum +",'"+ j.name +"')'></button>";
				row += "<td>";
				row += "<button class='btn btn-xs btn-danger glyphicon glyphicon-trash'";
				row += "onclick='delCat("+ j.cnum+")'></button>";
				if(j.tnum == 0){
					row += "<td></td>";
				}else{
					row += "<td>"+j.tnum+"</td>";				
				}
				if(j.tname == null){
					row += "<td></td>";
				}else{
					row += "<td>"+j.tname+"</td>";
				}
				if(j.tname != null){
					row += "<td>";
					row += "<button data-toggle='modal' data-target='#catUpdate'";
					row += "class='btn btn-xs btn-info glyphicon glyphicon-pencil'";
					//row += "onclick='modalVal("+j.tnum+","+j.tname+")></button>";
					row += "></button>";
					row += "</td>";
					row += "<td>";
					row += "<button class='btn btn-xs btn-danger glyphicon glyphicon-trash'";
					row += "onclick='delCat("+j.tnum+")'></button>";
					row += "</td>";
				}else{
					row += "<td></td>";
					row += "<td></td>";
				}
				row += "</tr>";
				console.log(row);
				$("tbody").append(row);
			}
			$("tbody>button").on("click",function(){
				let rowIndex = $(this).parent().parent().children().index($(this).parent());
				var colIndex = $(this).parent().children().index($(this));
				alert("aa");
			});
		}
	}
	//카테고리 및 세부 카테고리 리스트 불러오기//
	
	//수정//
	function memModify(){
		let catNum = $("#catNum").val();
		let catName = $("#catName").val();
		jQuery.ajax({
	           url:`${cp}/admin/catUpdate.do`,
	           method:"GET",
	           data:{catNum:catNum,
	        	   	 catName:catName},
	           dataType:"JSON",
	           success : function(data) {
	                 alert("dd");
	           }
	     });
	}
	
	$("#btnCatUpdate").on("click",function(){
		memModify();
	});
	//수정//
	
	function modalVal(catNum, catName) {
		console.log(catNum);
		console.log(catName);
		$("#catNum").val(catNum);
		$("#catName").val(catName);
	}
	
/* 	document.getElementById("btnCatUpdate").onclick = function(){
		let catNum = document.getElementById("catNum").value;
		let catName = document.getElementById("catName").value;
		location = `${cp}/admin/catUpdate.do?catNum=${'${catNum}'}&catName=${'${catName}'}`;
	}; */
	
	function delCat(catNum){
		location = `${cp}/admin/catDel.do?catNum=${'${catNum}'}`;
	}
	
</script>