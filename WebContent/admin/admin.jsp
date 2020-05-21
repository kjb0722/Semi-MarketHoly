<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="container">
	<div class="row">
		<h3>관리자 메뉴</h3>
	</div>
	<div class="row">
		<div class="well col-md-11">
			<input type="button" class="btn btn-lg btn-default" value="카테고리 관리" name="category">
			<input type="button" class="btn btn-lg btn-default" value="상품 추가" name="product">
			<input type="button" class="btn btn-lg btn-default" value="할인 상품 관리" name="sale">
		</div>
	</div>
	<div class="row">
		<div class="well col-md-11">
			<input type="button" class="btn btn-lg btn-default" value="회원 관리" name="member">
			<input type="button" class="btn btn-lg btn-default" value="Q&A" name="qna">
			<input type="button" class="btn btn-lg btn-default" value="주문 관리" name="order">
		</div>
	</div>
	<div class="row">
		<div class="well col-md-11">
			<input type="button" class="btn btn-lg btn-default" value="통계" name="stat">
		</div>
	</div>
</div>
<script>
	let btn = document.querySelectorAll(`input[type=button]`);
	for(let b of btn){
		b.onclick = function(){
			location = `${cp}/admin/${'${b.name}'}.do`;
		}
	}
</script>