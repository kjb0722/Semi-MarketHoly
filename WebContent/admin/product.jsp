<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-filestyle/2.1.0/bootstrap-filestyle.min.js">
</script>
<style>
#name {
	width: 350px;
}

#price, #stock {
	width: 200px;
}
</style>
<div class="container">
	<div class="row">
		<h3>상품 추가</h3>
	</div>
	<div class="contaainer-fluid">
		<form class="form-inline" role="form" action="${cp }/admin/prodAdd.do" method="post" onsubmit="return prodChk()" enctype="multipart/form-data">
			<div class="row">
				<div class="col-md-5 form-group">
					<div class="form-group">
						<span class="label label-success">카테고리</span>
						<select name="cat" class="form-control" onchange="catChange(this)">
							<c:forEach var="dto" items="${catList }">
								<option value="${dto.cnum }">${dto.name }</option>
							</c:forEach>
						</select>
					</div>
					<span class="label label-success">세부 카테고리명</span>
					<select name="catType" class="form-control">

					</select>
				</div>
			</div>
			<div class="row">
				<div class="col-md-5 form-group">
					<span class="label label-success">상품명</span>
					<br>
					<input id="name" type="text" class="form-control" placeholder="상품명을 입력하세요." maxlength="30" name="name">
					<br>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3 form-group">
					<span class="label label-success">상품 설명</span>
					<textarea class="form-control" rows="5" cols="40" name="description"></textarea>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3 form-group">
					<span class="label label-success">가격</span>
					<input id="price" name="price" type="number" class="form-control num-input" placeholder="가격을 입력하세요." min="1" max="999999999999">
				</div>
			</div>
			<div class="row">
				<div class="col-md-3 form-group">
					<span class="label label-success">수량</span>
					<input id="stock" name="stock" type="number" class="form-control num-input" placeholder="수량을 입력하세요." min="1" max="999999999999">
				</div>
			</div>
			<div class="row">
				<div class="col-md-3 form-group">
					<span class="label label-success">썸네일</span>
					<label class="file-upload btn btn-warning">
						파일을 선택해주세요.
						<input type="file" accept="image/gif,image/jpeg,image/png,image/jpg.image/bmp" name="thumb" />
					</label>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3 form-group">
					<span class="label label-success">상품 상세 설명 이미지</span>
					<label class="file-upload btn btn-warning">
						파일을 선택해주세요.
						<input type="file" accept="image/gif,image/jpeg,image/png,image/jpg.image/bmp" name="detail" />
					</label>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-3 form-group">
					<input type="submit" class="btn btn-lg btn-primary" value="저장">
					<input type="reset" class="btn btn-lg btn-default" value="초기화">
				</div>
			</div>
		</form>
	</div>
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