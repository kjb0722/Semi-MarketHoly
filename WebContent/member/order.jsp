<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div class="container" style='width: 1000px; text-align: center;'>

	<h1 class="display-1">주문서</h1>
	<span class='text-muted'>주문하실 상품명 및 수량을 정확하게 확인해 주세요.</span>
	
</div>

<br>

<form action="${pageContext.request.contextPath}/pay.do" onsubmit="return submitChk();">
	<div id="2" class="container" style='width: 1000px;'>
		<hr style="border: solid 1px purple;">
		<table class="table table-hover">
			<tr>
				<th><input type="checkbox" id="allchecked"
					onchange="allcheck()"> 전체선택</th>
				<th>상품정보</th>
				<th>수량</th>
				<th>상품금액</th>
			</tr>
				<c:forEach var="cartnum" items="${cartnum }" varStatus="status">
					<input type="hidden" name="pnum" value="${pnum[status.index]}">
					<input type="hidden" name="cartPrice" value="${cartPrice[status.index]}">
					<tr>
						<td><input type="checkbox" size="5" name="undercheck"></td>
						<td>${pname[status.index]}<input type="hidden" name="pname" value=${pname[status.index]}></td>
						<td>${EA[status.index]}<input type="hidden" name="EA" value=${EA[status.index]}></td>
						<td>${cartPrice[status.index] * EA[status.index]}</td>
					</tr>
					
				</c:forEach>
		</table>
	</div>

	<div id="3" class="container" style='width: 1000px;'>

		<h1 style="text-align: center;">주문자 정보</h1>
		<hr style="border: solid 1px purple;">

		<p>보내는 사람*</p>
		
		<input type="text" id="sender" value="${member.name}" disabled="disabled"><br>
		<p>휴대폰*</p>
		<input type="text" name="phone" value="${member.phone}" disabled="disabled">
		<br>
		<p>이메일*</p>
		<input type="text" id="email" value="${member.email}" disabled="disabled">

	</div>
	<div id="4" class="container" style='width: 1000px;'>

		<h1 style="text-align: center;">배송정보</h1>
		<hr style="border: solid 1px purple;">

		<h4>배송지 선택</h4>
		기본배송지<input type="radio" name="chaddr" value="oraddr" checked="checked" onclick="clearAddr(true)"> 
		새로운배송지<input type="radio" name="chaddr" value="newaddr" onclick="clearAddr(false)"> <br> 

		* 배송 휴무일 <p class="text-danger">- 일요일 및 공휴일 -</p>
		<hr style="border: solid 1px purple;">

	</div>

	<div id="5" class="container" style='width: 1000px;'>


		<div id="orginfo">
			<!-- 얻어온 정보가 자동으로 넘어오게  -->
			주소* :<input type="text" class="form-control" name="oraddr" id="addr" value="${member.addr} " readonly="readonly">
			<br>
			수령인 이름* :<input type="text" class="form-control" name="recname" id="recname" value="${member.name} " readonly="readonly">
			<br>
			휴대폰* :<input type="text" class="form-control" name="phone" id="phone" value="${member.phone} " readonly="readonly">
			<br>
			<p>배송요청사항</p>
			<textarea cols="100" rows="5" id="wants"></textarea>
		</div>
		<div id="newinfo" style="display: none;">
			주소* :<input type="text" class="form-control" name="nwaddr" id="addr" placeholder="주소를 입력해 주세요">
			<br>
			수령인 이름* :<input type="text" class="form-control" name="recname" id="recname" placeholder="이름를 입력해 주세요">
			<br>
			휴대폰* :<input type="text" class="form-control" name="phone" id="phone" placeholder="번호를 입력해 주세요">
			<br>
			<p>배송요청사항</p>
			<textarea cols="100" rows="5" id="wants"></textarea>
		</div>

	</div>


	<div id="6" class="container" style='width: 1000px;'>

		<h1 style="text-align: center;">적립금</h1>
		<hr style="border: solid 1px purple;">
		적립금 사용:<input type="text" id="usepoint" name="usepoint" value="0">
		<input type="hidden" name = "point" value="${member.point}">
		전액사용<input type="checkbox" id="allpoint" onclick="pointAllUse()" >
		<br>
		<br>
		<button type="button" class="btn btn-info" id="allpointOk" onclick="overpoint()">사용확인</button>
		


	</div>

	<div id="7" class="container" style='width: 1000px;'>

		<h1 style="text-align: center;">결제수단</h1>
		<hr style="border: solid 1px purple;">

		카드결제<input type="radio" name="chpay" value="1" checked="checked">
		<br> 
		무통장<input type="radio" name="chpay" value="2" >


	</div>

	<div id="8" class="container" style='width: 1000px;'>
 		<h1 style = 'text-align:center'>결제확인</h1>
 		<hr style="border: solid 1px purple;">
 		<h1>${DCprice[0] }</h1>
 		<c:choose>
 			<c:when test="${DCprice[0] == '' }">
 				<h3>할인금액 :0 </h3>
				<input type="hidden" name="DCprice" value="0">		
 			</c:when>
 			<c:otherwise>
 				<h3>할인금액 :${DCprice[0] } </h3>
				<input type="hidden" name="DCprice" readonly="readonly"   value="${DCprice[0] }">
 			</c:otherwise>
 		</c:choose>
		<h3>총 결제금액 :${finalprice[0]} </h3>
		<input type="hidden" name="finalprice" value="${finalprice[0]}">
		<span class='text-muted'> 결제 금액을 정확하게 확인 후 결제를 진행하세요 </span>
		<hr style="border: solid 1px purple;">
		<%-- <input type="hidden" name="pnum" value="${pnum}"> --%> 
		<button type="submit" class="btn btn-info lg" id="pay_btn">결제하기</button>
	</div>
	
	


</form>

<script>

function clearAddr(check) {
	document.getElementById("orginfo").style.display = check == false ? "none" : "block";
	document.getElementById("newinfo").style.display = check == true ? "none" : "block";
}

	function allcheck() {
		var allchecked = document.getElementById("allchecked");
		var undercheck = document.getElementsByName("undercheck");

		for (var i = 0; i < undercheck.length; i++) {
			undercheck[i].checked = allchecked.checked;
		}
	
	}
	
	function pointAllUse() {
		//전액사용 체크 하는 경우에는 포인트 사용금액에 allpoint의 내용이 들어가야하고
		//입력 사용일 경우에는 usepoint의 값이 들어가야함.
		var allpoint = document.getElementById("allpoint");	
		var usepoint = document.getElementById("usepoint");
		if(allpoint.checked==true){
			usepoint.value='${member.point}';
			usepoint.readOnly=true;		
		}else{
			usepoint.value-='${member.point}';
			usepoint.readOnly=false;		
		}
	}
	
	function overpoint() {
		var usepoint = document.getElementById("usepoint");
		var point=${member.point}
		if (usepoint.value > point) {
		   alert("포인트가 부족합니다");
		   usepoint.value="0";
		}
	}
	 
	 function submitChk(){
		 var usepoint = document.getElementById("usepoint");
		 var point=${member.point};
		 if (usepoint.value > point) {
			   alert("포인트가 부족합니다");
			   usepoint.value="0";
			   return false;
		 }
		 return true;
	 }
</script>