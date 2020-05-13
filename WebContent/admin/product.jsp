<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="${cp }/bootstrap/js/bootstrap-filestyle.min.js">
</script>
<div class="container">
	<div class="contaainer-fluid">
		<form class="form-inline" role="form" action="${cp }/admin/prodAdd.do"
			method="post" onsubmit="return prodChk()"
			enctype="multipart/form-data">
			<div class="form-group">
			<span class="label label-success">카테고리</span>
			
			<select name="cat" class="form-control" onchange="catChange(this)">
				<c:forEach var="dto" items="${catList }">
					<option value="${dto.cnum }">${dto.name }</option>
				</c:forEach>
			</select>
			
			<span class="label label-success">세부 카테고리명</span>
			
			<select name="catType" class="form-control">
	
			</select>
			</div>
			<br>
			<span class="label label-success">상품명</span>
			<br>
			<input type="text" class="form-control" placeholder="상품명을 입력하세요."
				maxlength="10" name="name">
			<br>
			<span class="label label-success">상품 설명</span>
			<br>
			<textarea class="form-control" rows="5" cols="35" name="description"></textarea>
			<br>
			<br> 
			<span class="label label-success">가격</span>
			
			<input name="price" type="number" class="form-control" placeholder="가격을 입력하세요." min="1" max="999999999999">
			
			<span class="label label-success">수량</span>
			
			<input name="stock" type="number" class="form-control" placeholder="수량을 입력하세요." min="1" max="999999999999">
			<br>
			<span class="label label-success">썸네일</span>
			<br>
			<label class="file-upload btn btn-warning"> 파일을 선택해주세요.
			<input type="file" name="thumb" />
			</label>
			<br>
			<br>
			<span class="label label-success">상품 상세 설명 이미지</span><br> <label class="file-upload btn btn-warning"> 파일을
				선택해주세요. <input type="file" name="detail" />
			</label>
			<br>
			<br>
			<input type="submit" class="btn btn-lg btn-success" value="저장">
			<input type="reset" class="btn btn-lg btn-default" value="초기화">
		</form>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		$("select[name=cat]").change();
	});
	
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
				$("select[name=catType]").append("<option value="+j.cnum+">"+j.name+"</option>");
			}
		}
	}
</script>