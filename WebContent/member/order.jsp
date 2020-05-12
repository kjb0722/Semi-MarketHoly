<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<th>상품금액</th>
		</tr>
	</table>
</div>

<div id="3">
	<h1 >주문자 정보</h1>
		보내는 사람*  <input type="text" id="sender">
		<br>
		휴대폰*  <select>  
		<option>010</option> 
		<option>016</option> 
		<option>011</option> 
		</select>
		-<input type="text" name="phone" size="4">-<input type="text" name="phone" size="4">
		<br>
		이메일*  <input type="text" id="email">

</div>
<div id="4">

	<h1>배송정보</h1>  
	* 배송 휴무일 :일요일 

</div>
<div id="5">
	주소<input type="text" id="addr"><br>
	수령인 이름*<input type="text" id="addr"><br>
	휴대폰<input type="text" id="addr"><br>
	배송요청사항<br><textarea cols="50" rows="10"></textarea>
</div>
</form>

</body>
</html>