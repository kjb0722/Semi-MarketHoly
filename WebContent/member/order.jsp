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

<form action="/Pay.do">
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
				<td>${cart.name}</td>
				<td>${cart.EA}</td>
				<td>${cart.price}</td>
			</tr>
		</table>
	</div>

	<div id="3" class="container" style='width: 1000px;'>

		<h1 style="text-align: center;">주문자 정보</h1>
		<hr style="border: solid 1px purple;">

		<p>보내는 사람*</p>
		
		<c:forEach var="mem" items="${requestScope.member}">
		</c:forEach>
		<input type="text" id="sender" value="${mem.name}" disabled="disabled"><br>
		<p>휴대폰*</p>
		<input type="text" name="phone" value="${mem.phone}" disabled="disabled">
		<br>
		<p>이메일*</p>
		<input type="text" id="email" value="${mem.email}" disabled="disabled">

	</div>
	<div id="4" class="container" style='width: 1000px;'>

		<h1 style="text-align: center;">배송정보</h1>
		<hr style="border: solid 1px purple;">

		<h4>배송지 선택</h4>
		기본배송지<input type="radio" name="chaddr" value="oraddr"onclick="sameget()"> 
		새로운배송지<input type="radio" name="chaddr" value="newaddr" onclick="clear()"> <br> 
		* 배송 휴무일 <p class="text-danger">- 일요일 및 공휴일 -</p>
		<hr style="border: solid 1px purple;">

	</div>

	<div id="5" class="container" style='width: 1000px;'>


		<div id="5-1">
			<!-- 얻어온 정보가 자동으로 넘어오게  -->
			주소* :<input type="text" class="form-control" name="tag" id="addr" placeholder="주소를 입력해 주세요">
			<br>
			수령인 이름* :<input type="text" class="form-control" name="tag" id="recname" placeholder="이름를 입력해 주세요">
			<br>
			휴대폰* :<input type="text" class="form-control" name="tag" id="phone" placeholder="번호를 입력해 주세요">
			<br>
			<p>배송요청사항</p>
			<textarea cols="100" rows="5" id="wants"></textarea>
		</div>


	</div>


	<div id="6" class="container" style='width: 1000px;'>

		<h1 style="text-align: center;">적립금</h1>
		<hr style="border: solid 1px purple;">
		적립금 사용:<input type="text" id="usepoint">
		전액사용<input type="checkbox" id="allpoint" onclick="pointAll()" >
		<br>
		<br>
		<button type="button" class="btn btn-info" id="allpointOk">사용확인</button>
		


	</div>

	<div id="7" class="container" style='width: 1000px;'>

		<h1 style="text-align: center;">결제수단</h1>
		<hr style="border: solid 1px purple;">

		카드결제<input type="radio" name="chpay" value="card" >
		<br> 
		무통장<input type="radio" name="chpay" value="nocard" >


	</div>

	<div id="8" class="container" style='width: 1000px;'>

		<h1 style="text-align: center;">결제금액</h1>
		<hr style="border: solid 1px purple;">

		상품금액 :<input type="text" id="price" value="${cart.price}" disabled="disabled"><br>
		상품할인금액 :500원<br>
		배송비 :2500원<br>
		적립금사용 :<input type="text" id="point" value="${member.point}" disabled="disabled"><br> 
		<br>
		최종결제금액 :<input type="text" id="total"> <br>
		
		<hr style="border: solid 1px purple;">
		
		<button type="submit" class="btn btn-info">결제하기</button>
		
	</div>
	
	


</form>

<script>
	var xhrsame = null;
	function sameget() {
		var addr = ${member.addr}
		var rename = ${member.name}
		var phone = ${member.phone}
		xhrsame = new XMLHttpRequest();
		xhrsame.onreadystatechange = samegetOk;
		xhrsame.open('post', 'list.do', true);
		xhrsame.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded');
		xhrsame
				.send('addr=' + addr + '&recname=' + recname + '&phone='
						+ phone);
		
		document.getElementsById("addr").disabled=true;
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
	
	function pointAll() {
		//전액사용 체크 하는 경우에는 포인트 사용금액에 allpoint의 내용이 들어가야하고
		//입력 사용일 경우에는 usepoint의 값이 들어가야함.
			
		var allchecked = document.getElementById("allpoint");
		
		if(allpoint==true){
			allpoint.value=${mem.getPoint}
			point=allpoint.value
			disabled=disabled;		
		}
			
	}
}
	 
</script>