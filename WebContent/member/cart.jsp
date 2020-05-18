<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet"href="/Semi-MarketHoly/bootstrap/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="/Semi-MarketHoly/bootstrap/js/bootstrap.min.js"></script>
<div class="container" style='width: 1000px; text-align: center;'>

	<hr style="border: solid 1px purple;">
	<h1 class="display-1">장바구니</h1>
	<span class='text-muted'>주문하실 상품명 및 수량을 정확하게 확인해 주세요.</span>
		<hr style="border: solid 1px purple;">
	
</div>
<div id="2" class="container" style='width: 1000px;'>
		<table class="table table-hover">
			<tr>
				<th><input type="checkbox" id="allchecked" onchange="allcheck()"> 전체선택</th>
				<th>상품정보</th>
				<th>수량</th>
				<th>상품금액</th>
				<!-- 장바구니에 담겨있는 리스트 얻어오기 -->
			</tr>


			<tr>
				<td><input type="checkbox" size="5" name="undercheck"></td>
				<td>${cart.name}</td>
				<td>${cart.EA}</td>
				<td>${cart.price}</td>
			</tr>
	
		</table>
		<hr style="border: solid 1px purple;">
		<button type="submit" class="btn btn-info">선택삭제</button>
		<button type="submit" class="btn btn-info">전체삭제</button>
		<hr style="border: solid 1px purple;">
	</div>
<style>
 #s{font-size:20px;}
 #minimini{font-size:25px;}
 .mini{width:30px;height: 200px; margin:80px 10px;text-align: center;}
 .a{border: solid 1px gray;width: 200px;height: 200px;text-align: center;}
 .b{float:left;}
</style>
	<div id="outbox" class="container" style="padding-left: 110px;padding-top: 100px" >
		<div id="box1" class="a b">
			<span>상품금액</span><br>
			<span id="s">${cart.price}원</span>
		</div>
				<div class="mini b">
				<span id="minimini">-</span>
				</div>
		
		<div id="box2" class="a b">
			<span>상품할인금액 </span><br>
			<span id="s">${cart.price*0.2}원</span><!-- cart.percent -->
		</div>
				<div class="mini b">
				<span id="minimini">+</span>
				</div>
				
		<div id="box3" class="a b">
			<span >배송비</span><br>
			<span id="s">2500원</span>
		</div>
		
				<div class="mini b">
				<span id="minimini">=</span>
				</div>
	
		<div id="box3" class="a b">
			<span>상품금액</span><br>
			<span id="s">${cart.price-(cart.price*0.2)}원</span>
		</div>
	</div>	

<script>
	function allcheck() {
		var allchecked = document.getElementById("allchecked");
		var undercheck = document.getElementsByName("undercheck");
	
		for (var i = 0; i < undercheck.length; i++) {
			undercheck[i].checked = allchecked.checked;
	}
}


</script>