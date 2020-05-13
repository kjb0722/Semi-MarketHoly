<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="${cp }/bootstrap/js/bootstrap-filestyle.min.js">
	
</script>
<div class="container">
	<form class="form-inline" role="form" action="${cp }/admin/prodAdd.do"
		method="get" onsubmit="return prodChk()">
		<span class="label label-success">상품명</span><br> <input
			type="text" class="form-control" placeholder="상품명을 입력하세요."
			maxlength="10" name="name"> <br> <span
			class="label label-success">상품 설명</span><br>
		<textarea class="form-control" rows="5" cols="35" name="description"></textarea>
		<br> <br> <span class="label label-success">썸네일</span><br>
		<label class="file-upload btn btn-warning"> 파일을 선택해주세요. <input
			type="file" />
		</label> <br> <br> <span class="label label-success">상품 상세 설명
			이미지</span><br> <label class="file-upload btn btn-warning"> 파일을
			선택해주세요. <input type="file" />
		</label> <br> <br> <input type="submit" value="저장"> <input
			type="reset" value="초기화">
	</form>
</div>

<script type="text/javascript">
	function prodChk() {
		let name = document.getElementsByName("name")[0];

		if (name.value == "") {
			alert("상품명을 입력하세요.");
			name.focus();
			return false;
		}

		return true;
	}
</script>