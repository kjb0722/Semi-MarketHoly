<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/join.jsp</title>


</head>
<body>
<h1>회원가입</h1>
<br>
*필수 입력사항

<form method="post" action="${pageContext.request.contextPath }/member/join.do">
	아이디* <input type="text" name="id" id="id" onkeyup="idvalidate()" >
	<input type="button" value="중복체크" onclick="idcheck()">
	<span id="idcheck"></span>
	<div id="idval"></div>
	비밀번호* <input type="password" name="pwd" id="pwd" onkeyup="pwdval()"><br> 
	<div id="pwdval"></div>
	비밀번호확인* <input type="password" name="pwdCheck" id="pwdCheck" onkeyup="pwddouble()">
	<div id="pwddiv"></div> 
	
	이름* <input type="text" name= "name"><br>
	이메일* <input type="text" name="email" id= "email"> 
	<input type="button" value="이메일중복확인" onclick="emailCheck()">
	<span id="emailcheck"></span>
	<br>
	전화번호*<input type="text" name="phone"><br>
	배송 주소*<input type="text" name ="addr"><br>

	성별<input type="radio" name="gender" value="1" >남자
	<input type="radio" name="gender" value="2" >여자<br>
	
	생년월일*<input type="text" name="birth"> ex) YYYYMMDD<br> 
	
	<input type="submit" value="가입하기">
</form>
</body>

<script>
	var xhr = null;
	function idcheck(){
		var id = document.getElementById("id").value;
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange= function() {
			if(xhr.readyState==4 && xhr.status==200){
				var data = xhr.responseText;
				var json = JSON.parse(data);
				//if(eval(using)==true)
				var span = document.getElementById("idcheck");

				if(json.check==true){
					span.innerHTML=	"이미가입된 회원입니다.";
				}else{
					span.innerHTML="가입가능한 아이디입니다.";
				}		
			}
		};
		xhr.open('get','${pageContext.request.contextPath }/member/idcheck.do?id='+id, true);
		xhr.send();
	}
	
	
	
	var xhrval;
	function idvalidate() {
		var id = document.getElementById("id").value;
		xhrval = new XMLHttpRequest();
		xhrval.onreadystatechange=function() {
			if(xhrval.readyState==4 && xhrval.status==200){
				var data = xhrval.responseText;
				var json = JSON.parse(data);
				//if(eval(using)==true)
				var div = document.getElementById("idval");

				if(json.msg==true){
					div.innerHTML=	"아이디는 4자 이상으로 기입하세요.";
					div.style.color="red";
				}else{
					div.innerHTML="";
				}			
			}
		};
		xhrval.open('get','${pageContext.request.contextPath }/member/idval.do?id='+id, true);
		xhrval.send();
	}
	
	
	
	var xhr3
	function pwdval() {
		var pwd = document.getElementById("pwd").value;
		xhr3 = new XMLHttpRequest();
		xhr3.onreadystatechange= function(){
			if (xhr3.readyState==4 && xhr3.status==200) {
				var data = xhr3.responseText;
				var json = JSON.parse(data);
				var div = document.getElementById("pwdval");
				
				if(json.msg==true){
					div.innerHTML=	"비밀번호는 6자 이상으로 기입하세요.";
					div.style.color="red";
				}else{
					div.innerHTML="";
				}				
			}
		};
		xhr3.open('get','${pageContext.request.contextPath }/member/pwdval.do?pwd='+pwd, true);
		xhr3.send();
	}
	
	
	
	function pwddouble(){
		var pwd = document.getElementById("pwd").value;
		var pwdCheck = document.getElementById("pwdCheck").value;
		var div = document.getElementById("pwddiv");
		
		if(pwd==pwdCheck){
			div.innerHTML="일치하는 비밀번호입니다.";
			div.style.color="green";
		}else{
			div.innerHTML="비밀번호가 일치하지 않습니다."
			div.style.color="red";
		}
	}
	
	
	var xhr4 = null;
	function emailCheck(){
		var email = document.getElementById("email").value;
		xhr4 = new XMLHttpRequest();
		xhr4.onreadystatechange= function() {
			if(xhr4.readyState==4 && xhr4.status==200){
				var data = xhr4.responseText;
				var json = JSON.parse(data);
				var span = document.getElementById("emailcheck");

				if(json.check==true){
					span.innerHTML=	"이미가입된 회원입니다.";
				}else{
					span.innerHTML="가입가능한 아이디입니다.";
				}		
			}
		};
		xhr4.open('get','${pageContext.request.contextPath }/member/emailCheck.do?email='+email, true);
		xhr4.send();
	}
	
	
	
	
	
	
	
	
	
</script>
</html>