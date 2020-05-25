<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
#s {
	font-size: 20px;
}

#minimini {
	font-size: 25px;
}

.mini {
	width: 30px;
	height: 200px;
	margin: 80px 10px;
	text-align: center;
}

.a {
	border: solid 1px gray;
	width: 200px;
	height: 200px;
	text-align: center;
}

.b {
	float: left;
}
</style>
<div class="container" style='width: 1000px; text-align: center;'>

	<hr style="border: solid 1px purple;">
	<h1 class="display-1">장바구니</h1>
	<span class='text-muted'>주문하실 상품명 및 수량을 정확하게 확인해 주세요.</span>
	<hr style="border: solid 1px purple;">
</div>
<form action="${cp}/order.do" method="post">
<div id="2" class="container" style='width: 1000px;'>
	<table class="table table-hover">
		<tr>
			<th><input type="checkbox" id="allchecked" onchange="allcheck()">
				전체선택</th>
			<th></th>
			<th>상품정보</th>
			<th>수량</th>
			<th>상품금액</th>
			<!-- 장바구니에 담겨있는 리스트 얻어오기 -->
		</tr>


		<c:forEach var="cart1" items="${requestScope.cart }" varStatus="status">
			<input type="hidden" name="pnum" value="${cart1.pnum }">
			<tr>
				<td><input type="checkbox" size="5" name="undercheck" value="${cart1.cartnum}" onchange="showBox()"></td>
				<td><img src="${cp }/img/${cart1.thumb_save}" width="100px"
					height="100px">
				<td><input type="text" value="${cart1.name}" name="pname" readonly style="border:0 ;text-align: center;"></td>
				<td><button type="button" class="btn btn-default" onclick="minus(${status.index})">
						<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
					</button>&nbsp <input type="text" name="EA" class="EA" value="${cart1.EA}" readonly style="border:0 ;text-align: center;">&nbsp
					<button type="button" class="btn btn-default" onclick="plus(${status.index})">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
					</button></td>
				<td><input type="hidden" name="cart-price" value='${cart1.price}'> 
				<label class="sum" id="sum">${cart1.price*cart1.EA}</label>
				</td>
			</tr>
		</c:forEach>
	</table>
	<hr style="border: solid 1px purple;">
	<button type="submit" class="btn" id="selectdel" style="background-color: purple;color:white">선택삭제</button>
	<hr style="border: solid 1px purple;">
</div>

	<div id="outbox" class="container" style="padding-left: 110px; padding-top: 100px">
		<div id="box1" class="a b">
			<h3>상품금액</h3>
			<br>
			<input type="text" name="total" id="total" readonly style="border:0 ;text-align: center;">

		</div>
		<div class="mini b">
			<span id="minimini">-</span>
		</div>

		<div id="box2" class="a b">
			<h3>상품할인금액 </h3>
			<br>
			<input type="text" name="DCprice" id="DCprice" readonly style="border:0 ;text-align: center;">
			<br>
			<input type="hidden" name="DCprice" value='${cart1.percent}'> 
			
			<!-- cart.percent -->
		</div>
		<div class="mini b">
			<span id="minimini">+</span>
		</div>

		<div id="box3" class="a b">
			<h3>배송비</h3><br>
			<input type="text" value="2500" name="shipping" id="shipping" readonly style="border:0 ;text-align: center;">
		</div>

		<div class="mini b">
			<span id="minimini">=</span>
		</div>

		<div id="box3" class="a b">
			<h3>결제금액</h3><br>
			<input type="text" value="" name="finalprice" id="finalprice" readonly style="border:0 ;text-align: center;">
			

		</div>
	</div>
	<div class="container" style='width: 1000px;' align="center">
	<hr style="border: solid 1px purple;">
		<button type="submit" class="btn btn-lg" style="background-color: purple;color:white" >주문하기</button>
		
	</div>
</form>

<script>

	function allcheck() {
		var allchecked = document.getElementById("allchecked");
		var undercheck = document.getElementsByName("undercheck");
		for (var i = 0; i < undercheck.length; i++) {
			undercheck[i].checked = allchecked.checked;
			showBox();
			
		}
	
	}
	
	function showBox() {
		var undercheck = document.getElementsByName("undercheck");
		var EA = document.querySelectorAll(".EA");
		var sum = document.querySelectorAll(".sum");
		var cartPrice = document.getElementsByName("cart-price");
		var total = document.getElementById("total");
		var DCprice = document.getElementById("DCprice");
		var finalprice=document.getElementById("finalprice")
		var shipping=document.getElementById("shipping")
		total.value = 0;
		var price = 0;
		for(var i=0;i<undercheck.length;i++){
			if (undercheck[i].checked==true) {
				sum[i].innerHTML = parseInt(EA[i].value) * parseInt(cartPrice[i].value);
				price += parseInt(total.value) + parseInt(sum[i].innerHTML);
			}
		}
		total.value = price;
		price += parseInt(shipping.value);
		finalprice.value = price;
	}
	
	
	function plus(index) {
		//var EA=document.getElementById("EA");
		var EA = document.querySelectorAll(".EA");
		var sum = document.querySelectorAll(".sum");
		var cartPrice = document.getElementsByName("cart-price");
		var total = document.getElementById("total");
		var shipping = document.getElementById("shipping").value;
		
		EA[index].value=parseInt(EA[index].value)+1;
		sum[index].innerHTML = cartPrice[index].value * (parseInt(EA[index].value));
		var undercheck = document.getElementsByName("undercheck");
		if(undercheck[index].checked == true){
			total.value =  parseInt(total.value) + parseInt(cartPrice[index].value);
			finalprice.value =  parseInt(total.value)+parseInt(shipping);
		}
	}
		function salenull() {
		var DCprice=document.getElementsByName("DCprice");
		for (var i = 0; i < DCprice.length; i++) {
			if(DCprice[i].value==null){
				DCprice[i].value = "1";
				}
			}
		}
	
	function minus(index) {
		var EA = document.querySelectorAll(".EA");	
		var sum = document.querySelectorAll(".sum");
		var cartPrice = document.getElementsByName("cart-price");
		var total = document.getElementById("total");
		var shipping=parseInt(2500);
			if(EA[index].value<=1){
				alert("최소수량입니다");
			}else{
			
			EA[index].value =parseInt(EA[index].value)-1; 
			sum[index].innerHTML = cartPrice[index].value * (parseInt(EA[index].value));  
			var undercheck = document.getElementsByName("undercheck");
			if(undercheck[index].checked == true){
				total.value =  parseInt(total.value) - parseInt(cartPrice[index].value);
				finalprice.value =  parseInt(total.value)+parseInt(shipping);
			}
		}
	}
 
	var selectdel=document.getElementById("selectdel");
	selectdel.onclick=function(e){
		e.preventDefault();
		var undercheck = document.getElementsByName("undercheck");
		var param="";
		var cnt=0;
		for (var i = 0; i < undercheck.length; i++) {
			if(undercheck[i].checked==true && cnt==0)
			   param +="undercheck="+ undercheck[i].value ;
			   
			else if(undercheck[i].checked==true  && cnt!=0){
				param +="&undercheck="+ undercheck[i].value ;
			}
			if(undercheck[i].checked==true)cnt++;
	     }
   
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				let json = JSON.parse(xhr.responseText);
				//location.href = location.href;
				if(json.code == 'success'){
					alert("삭제성공");
					location ='${cp}/cart.do';
				}else{
					location = `${cp}/error.do`;
				}
			}
		};

	xhr.open('get','${cp}/cartdel.do?'+ param,true);
	xhr.send();
}	

	

</script>
