<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet"
	href="/Semi-MarketHoly/bootstrap/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="/Semi-MarketHoly/bootstrap/js/bootstrap.min.js"></script>
<div class="container" style='width: 1000px; text-align: center;'>

	<h1 class="display-1">주문서</h1>
	<span class='text-muted'>주문하실 상품명 및 수량을 정확하게 확인해 주세요.</span>


</div>


<br>

<form action="">
	<div id="2" class="container" style='width: 1000px;'>
		<hr style="border: solid 1px purple;">
		<table class="table table-hover">
			<tr>
				<th><input type="checkbox" id="allchecked"
					onchange="allcheck()"> 전체선택</th>
				<th>상품정보</th>
				<th>수량</th>
				<th>상품금액</th>
				<!-- 장바구니에 담겨있는 리스트 얻어오기 -->
			</tr>


			<tr>
				<td><input type="checkbox" size="5" name="undercheck"></td>
				<td>딸기</td>
				<td>2box</td>
				<td>12000원</td>
			</tr>
			<tr>
				<td><input type="checkbox" size="5" name="undercheck"></td>
				<td>수박</td>
				<td>1개</td>
				<td>11500원</td>
			</tr>
		</table>
	</div>

	<div id="3" class="container" style='width: 1000px;'>

		<h1 style="text-align: center;">주문자 정보</h1>
		<hr style="border: solid 1px purple;">

		<p>보내는 사람*</p>
		<input type="text" id="sender" value="${MemberDto.getName}"> <br>
		<p>휴대폰*</p>
		<input type="text" name="phone" value="${MemberDto.getPhone}">
		<br>
		<p>이메일*</p>
		<input type="text" id="email" value="${MemberDto.getEmail}">

	</div>
	<div id="4" class="container" style='width: 1000px;'>

		<h1 style="text-align: center;">배송정보</h1>
		<hr style="border: solid 1px purple;">

		<h4>배송지 선택</h4>
		기본배송지<input type="radio" name="chaddr" value="oraddr"
			onclick="sameget()"> 새로운배송지<input type="radio" name="chaddr"
			value="newaddr" onclick="clear()"> <br> * 배송 휴무일 :일요일
		<hr style="border: solid 1px purple;">

	</div>

	<div id="5" class="container" style='width: 1000px;'>


		<div id="5-1">
			<!-- 얻어온 정보가 자동으로 넘어오게  -->
			주소<input type="text" id="addr"><br> 수령인 이름*<input
				type="text" id="recname"><br> 휴대폰<input type="text"
				id="phone"><br> 배송요청사항<br>
			<textarea cols="50" rows="10" id="wants"></textarea>
		</div>


	</div>


	<div id="6" class="container" style='width: 1000px;'>

		<h1 style="text-align: center;">적립금</h1>
		<hr style="border: solid 1px purple;">
		* 적립금 사용:<input type="text" id="point"> 전액사용<input
			type="checkbox" id="allpoint">


	</div>

	<div id="7" class="container" style='width: 1000px;'>

		<h1 style="text-align: center;">결제수단</h1>
		<hr style="border: solid 1px purple;">

		카드결제<input type="radio" name="chpay" value="card" onclick="">
		<br> 무통장<input type="radio" name="chpay" value="nocard"
			onclick="">


	</div>

	<div id="8" class="container" style='width: 1000px;'>

		<h1 style="text-align: center;">결제금액</h1>
		<hr style="border: solid 2px purple;">

		상품금액 :<input type="text" id="price" value="${CartDto.getPrice}"><br>
		상품할인금액 :500원<br> 배송비 :2500원<br> 적립금사용 :<input type="text"
			id="point" value="${CartDto.getPoint}"><br> <br>
		최종결제금액 :<input type="text" id="total"> <br>
		<button type="button" class="btn btn-success">Success</button>
	</div>

</form>

<script>
	var xhrsame = null;
	function sameget() {
		var addr = document.getElementById("addr")
		var recname = document.getElementById("recname")
		var phone = document.getElementById("phone")
		xhrsame = new XMLHttpRequest();
		xhrsame.onreadystatechange = samegetOk;
		xhrsame.open('post', 'list.do', true);
		xhrsame.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded');
		xhrsame
				.send('addr=' + addr + '&recname=' + recname + '&phone='
						+ phone);
	}

	function samegetOk() {
		if (xhrsame.readyState == 4 && xhrsame.status == 200) {
			var xml = xhrsame.responseXML;
		}
	}

	function clear() {
		var addr = document.getElementById("addr");
		addr.innerHTML = "";
		var recname = document.getElementById("recname");
		addr.innerHTML = "";
		var phone = document.getElementById("phone");
		addr.innerHTML = "";
	}

	function allcheck() {
		var allchecked = document.getElementById("allchecked");
		var undercheck = document.getElementsByName("undercheck");

		for (var i = 0; i < undercheck.length; i++) {
			undercheck[i].checked = allchecked.checked;
		}
	}
</script>