<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/join.jsp</title>
<script>
	var xhr = null;
	function idcheck(){
		var id = document.getElementById("id").value;
		
		xhr.onreadystatechange= getResult;
		xhr.open('get','${pageContext.request.contextPath }/member/idcheck.do?id='+id, true);
		xhr.send();
	}
	
	function getResult() {
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
	}
		
	var xhrval;
	function idvalidate() {
		var id = document.getElementById("id");
		
		
		xhrval = new XMLHttpRequest();
		xhrval.onreadystatechange= getResult2;
		xhrval.open('get','${pageContext.request.contextPath }/member/idval.do?id='+id, true);
		xhrval.send();
	}
	
	
	function getResult2() {
		if(xhr.readyState==4 && xhr.status==200){
			var data = xhr.responseText;
			var json = JSON.parse(data);
			//if(eval(using)==true)
			var div = document.getElementById("idval");

			if(json. ==){
				span.innerHTML=	"이미가입된 회원입니다.";
			}else{
				span.innerHTML="가입가능한 아이디입니다.";
			}			
		}
	}
	
	


</script>

</head>
<body>
<h1>회원가입</h1>
<br>
*필수 입력사항

<form method="post" action="${pageContext.request.contextPath }/member/join.do">
	아이디* <input type="text" name="id" id="id" onkeyup="idvalidate()" >
	<input type="button" value="중복체크" onclick="idcheck()"><span id="idcheck"></span>
	<div id="idval"></div>
	<br> 
	비밀번호* <input type="password" name="pwd"><br> 
	비밀번호확인* <input type="password" name="pwdCheck"><br> 
	이름* <input type="text" name= "name"><br>
	이메일* <input type="text" name="email">
	<input type="button" value="이메일중복확인">
	<br>
	전화번호*<input type="text" name="phone"><br>
	배송 주소*<input type="text" name ="addr"><br>

	성별<input type="radio" name="gender" value="1" >남자
	<input type="radio" name="gender" value="2" >여자<br>
	
	생년월일*<input type="text" name="birth"> ex) YYYYMMDD<br> 
	
	<input type="submit" value="가입하기">
</form>
</body>
</html>