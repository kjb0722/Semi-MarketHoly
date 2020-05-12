<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>order</title>
<style type="text/css">

</style>
</head>
<body>
<div id="1">
	<h1 >주문서</h1>
	주문하실 상품명 및 수량을 정확하게 확인해 주세요.
</div>
<br>

<form action="">
<div id="2">
	<table border="1" width="500">
		<tr>
				<th>상품정보</th>
		</tr>
	</table>
</div>

<div id="3">

	<h1 >주문자 정보</h1>
		
		보내는 사람*  <input type="text" id="sender" value="${MemberDto.getName}">
		<br>
		휴대폰* <input type="text" name="phone" value="${MemberDto.getPhone}">
		<br>
		이메일*  <input type="text" id="email" value="${MemberDto.getEmail}">

</div>
<div id="4">

	<h1>배송정보</h1>  
	* 배송 휴무일 :일요일 

</div>

<div id="5">
	
	기본배송지<input type="radio" name="chaddr" value="oraddr" onclick="sameget()">
	새로운배송지<input type="radio" name="chaddr" value="newaddr" onclick="clear()">

			<div id="5-1">
				<!-- 얻어온 정보가 자동으로 넘어오게 -->
				주소<input type="text" id="addr"><br>
				수령인 이름*<input type="text" id="recname"><br>
				휴대폰<input type="text" id="phone"><br>
				배송요청사항<br><textarea cols="50" rows="10" id="wants"></textarea>
			</div>	

	
</div>


<div id="6">

	<h1>적립급</h1>  
	* 적립금 사용:<input type="text" id="point"> 
	전액사용<input type="checkbox" id="allpoint">


</div>

<div id="7">

	<h1>결제수단</h1>
	
	카드결제<input type="radio" name="chpay" value="card" onclick="">
	<br>
	무통장<input type="radio" name="chpay" value="nocard" onclick="">
	

</div>

<div id="8">

	<h1>결제금액</h1>
	
	상품금액 :<br>
	상품할인금액 :<br>
	배송비 :<br>
	적립금사용 :<br>
	<br>
	최종결제금액 :

</div>

</form>

<script type="text/javascript">
	var xhrsame = null;
	function sameget() {
		var addr=document.getElementById("addr")
		var recname=document.getElementById("recname")
		var phone=document.getElementById("phone")
		xhrsame = new XMLHttpRequest();
		xhrsame.onreadystatechange = samegetOk;
		xhrsame.open('post', 'list.do', true);
		xhrInsert.setRequestHeader('Content-Type',
		'application/x-www-form-urlencoded');
		xhrInsert.send('addr=' + addr + '&recname=' + recname
				+ '&phone=' + phone);
	}
	
	function samegetOk() {
		if (xhrsame.readyState == 4 && xhrsame.status == 200) {
			var xml = xhrsame.responseXML;
		
	}
	
	function clear() {
		var addr=document.getElementById("addr")
		addr.innerHTML="";
		var recname=document.getElementById("recname")
		addr.innerHTML="";
		var phone=document.getElementById("phone")
		addr.innerHTML="";
		
	}
</script>
</body>
</html>