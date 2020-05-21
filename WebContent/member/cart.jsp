<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container" style='width: 1000px; text-align: center;'>

	<hr style="border: solid 1px purple;">
	<h1 class="display-1">장바구니</h1>
	<span class='text-muted'>주문하실 상품명 및 수량을 정확하게 확인해 주세요.</span>
	<hr style="border: solid 1px purple;">

</div>
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


		<c:forEach var="cart1" items="${requestScope.cart }"
			varStatus="status">
			<tr>
				<td><input type="checkbox" size="5" name="undercheck"
					value="${cart1.cartnum}" onchange="showBox()"></td>
				<td><img src="${cp }/img/${cart1.thumb_save}" width="100px"
					height="100px">
				<td>${cart1.name}</td>
				<td><button type="button" class="btn btn-default"
						onclick="minus(${status.index})">
						<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
					</button> <label class="EA">${cart1.EA}</label>&nbsp
					<button type="button" class="btn btn-default"
						onclick="plus(${status.index})">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
					</button></td>
				<td><input type="hidden" name="cart-price" value='${cart1.price}'> 
				<label class="sum">${cart1.price*cart1.EA}</label>
				</td>
			</tr>
		</c:forEach>
	</table>
	<hr style="border: solid 1px purple;">
	<button type="submit" class="btn" id="selectdel" style="background-color: purple;color:white">선택삭제</button>
	<hr style="border: solid 1px purple;">
	<input type="button" value="확인" onClick="location.href='order.do?';">
</div>
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
<form action="${cp}/order.do" method="get">
	<div id="outbox" class="container" style="padding-left: 110px; padding-top: 100px">
		<div id="box1" class="a b">
			<span>상품금액</span><br>
			<span id="total">0</span>

		</div>
		<div class="mini b">
			<span id="minimini">-</span>
		</div>

		<div id="box2" class="a b">
			<span>상품할인금액 </span><br> <span id="DCprice"></span>
			<!-- cart.percent -->
		</div>
		<div class="mini b">
			<span id="minimini">+</span>
		</div>

		<div id="box3" class="a b">
			<span>배송비</span><br> <span>2500원</span>
			<input type="hidden" value="2500" id="shipping">
		</div>

		<div class="mini b">
			<span id="minimini">=</span>
		</div>

		<div id="box3" class="a b">
			<span>결제금액</span><br> 
			<span id="finalprice"></span>
		</div>
	</div>
	<div class="container" style='width: 1000px;' align="center">
	<hr style="border: solid 1px purple;">
		<button type="submit" class="btn btn-lg" style="background-color: purple;color:white">주문하기</button>
		
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
		total.innerHTML = 0;
		for(var i=0;i<undercheck.length;i++){
			if (undercheck[i].checked==true) {
				var price = parseInt(total.innerHTML);
				price += parseInt(sum[i].innerHTML); 
				total.innerHTML = price;	
			}
			
		}
	
	}
	function plus(index) {
		//var EA=document.getElementById("EA");
		var EA = document.querySelectorAll(".EA");
		var sum = document.querySelectorAll(".sum");
		var cartPrice = document.getElementsByName("cart-price");
		var total = document.getElementById("total");
		var shipping = document.getElementById("shipping").value;
		EA[index].innerHTML=parseInt(EA[index].innerHTML)+1;
		sum[index].innerHTML = cartPrice[index].value * (parseInt(EA[index].innerHTML));
		
		var undercheck = document.getElementsByName("undercheck");
		if(undercheck[index].checked == true){
			total.innerHTML =  parseInt(total.innerHTML) + parseInt(cartPrice[index].value);
			finalprice.innerHTML =  parseInt(total.innerHTML)+parseInt(shipping);
		}
	}
	
	function minus(index) {
		var EA = document.querySelectorAll(".EA");	
		var sum = document.querySelectorAll(".sum");
		var cartPrice = document.getElementsByName("cart-price");
		var total = document.getElementById("total");
		var shipping=parseInt(2500);
			if(EA[index].innerHTML<=1){
				alert("최소수량입니다");
			}else{
			
			EA[index].innerHTML =parseInt(EA[index].innerHTML)-1; 
			sum[index].innerHTML = cartPrice[index].value * (parseInt(EA[index].innerHTML));  
			var undercheck = document.getElementsByName("undercheck");
			if(undercheck[index].checked == true){
				total.innerHTML =  parseInt(total.innerHTML) - parseInt(cartPrice[index].value);
				finalprice.innerHTML =  parseInt(total.innerHTML)+parseInt(shipping);
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
   
		alert(param)
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				let json = JSON.parse(xhr.responseText);
				if(json.n > 0){
					alert("삭제성공");
					location ='${cp}/cart.jsp';
				}else{
					//location = `${cp}/error.do`;
				}
			}
		};

	xhr.open('get','${cp}/cartdel.do?'+ param,true);
	xhr.send();
}	

	

</script>
