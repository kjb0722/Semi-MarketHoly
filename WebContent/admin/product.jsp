<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-filestyle/2.1.0/bootstrap-filestyle.min.js">
</script>
<style>
</style>
<div class="container">
	<div class="row">
		<h3>상품 추가</h3>
	</div>
	<form class="form-group" role="form" action="${cp }/admin/prodAdd.do" method="post" onsubmit="return prodChk()" enctype="multipart/form-data">
		<div class="col-md-5">
			<div class="row">
				<div class="col-md-12 form-group form-inline">
					<div class="form-group">
						<span class="label label-success">카테고리</span>
						<select name="cat" class="form-control" onchange="catChange(this)">
							<c:forEach var="dto" items="${catList }">
								<option value="${dto.cnum }">${dto.name }</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 form-group form-inline">
					<span class="label label-success">세부 카테고리명</span>
					<select name="catType" class="form-control">

					</select>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 form-group form-inline">
					<span class="label label-success">상품명</span>
					<input id="name" type="text" class="form-control" placeholder="상품명을 입력하세요." maxlength="30" name="name">
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 form-group">
					<span class="label label-success">상품 설명</span>
					<textarea class="form-control" rows="5" cols="41" name="description" style="resize: none;"></textarea>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<span class="label label-success">가격</span>
					<input id="price" name="price" type="number" class="form-control num-input" placeholder="가격을 입력하세요." min="1" max="999999999999">
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 form-group">
					<span class="label label-success">수량</span>
					<input id="stock" name="stock" type="number" class="form-control num-input" placeholder="수량을 입력하세요." min="1" max="999999999999">
				</div>
			</div>
			<div class="row">
				<div class="col-md-8 form-group">
					<span class="label label-success">썸네일</span>
					<label class="file-upload btn btn-warning">
						파일을 선택해주세요.
						<input type="file" accept="image/gif,image/jpeg,image/png,image/jpg.image/bmp" name="thumb" id="thumb" />
					</label>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 form-group">
					<span class="label label-success">상품 상세 설명 이미지</span>
					<label class="file-upload btn btn-warning">
						파일을 선택해주세요.
						<input type="file" accept="image/gif,image/jpeg,image/png,image/jpg.image/bmp" name="detail" id="detail" />
					</label>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-12 form-group">
					<input type="submit" class="btn btn-lg btn-primary" value="저장">
					<input type="reset" class="btn btn-lg btn-default" value="초기화">
				</div>
			</div>
		</div>
		<div class="col-md-7">
			<div class="row">
				<div class="col-md-6">
					<span class="label label-success">판매 단위</span>
					<input id="unit" type="text" class="form-control" placeholder="판매단위를 입력하세요." maxlength="30" name="unit">
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<span class="label label-success">용량</span>
					<input id="volume" type="text" class="form-control" placeholder="용량을 입력하세요." maxlength="30" name="volume">
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<span class="label label-success">원산지</span>
					<input id="origin" type="text" class="form-control" placeholder="원산지를 입력하세요." maxlength="30" name="origin">
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<span class="label label-success">포장 종류</span>
					<input id="pack-type" type="text" class="form-control" placeholder="포장 종류를 입력하세요." maxlength="30" name="pack_type">
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<span class="label label-success">유통기한</span>
					<input id="shelf_life" type="text" class="form-control" placeholder="유통기한을 입력하세요." maxlength="30" name="shelf_life">
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<span class="label label-success">안내사항</span>
					<input id="guidance" type="text" class="form-control" placeholder="안내사항을 입력하세요." maxlength="30" name="guidance">
				</div>
			</div>
		</div>
	</form>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		//최초 페이지 이동시 세부 카테고리 로드
		$("select[name=cat]").change();
	});
	
	//재고, 가격은 12자리까지만 입력되도록//
	$("input[name=stock]").on("input",function(){
		if($(this).val().length > 12){
			$(this).val($(this).val().slice(0,12));
		}
	});
	
	$("input[name=price]").on("input",function(){
		if($(this).val().length > 12){
			$(this).val($(this).val().slice(0,12));
		}
	});
	//재고, 가격은 12자리까지만 입력되도록//
	
	//form 전송 전 체크//
	function prodChk() {
		let cat = $("select[name=cat]");
		if(cat.val() == null){
			alert("카테고리를 선택하세요.");
			cat.focus();
			return false;
		}
		
		let catType = $("select[name=catType]");
		if(catType.val() == null){
			alert("세부 카테고리를 선택하세요.");
			catType.focus();
			return false;
		}
		
		let name = document.getElementsByName("name")[0];
		if (name.value == "") {
			alert("상품명을 입력하세요.");
			name.focus();
			return false;
		}
		
		let thumb = $("#thumb");
		if(thumb.val() == ""){
			alert("썸네일 파일을 선택하세요.");
			thumb.focus();
			return false;
		}
		
		let detail = $("#detail");
		if(detail.val() == ""){
			alert("상품 상세 설명 이미지를 선택하세요.");
			detail.focus();
			return false;
		}

		return true;
	}
	//form 전송 전 체크//
	
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