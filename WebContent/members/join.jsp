<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>members/join.jsp</title>
</head>
<body>
<h1>회원가입</h1>
<br>
<form method="post" action="${pageContext.request.contextPath }/members/join.do">
	아이디* <input type="text" name="id">
	<input type="button" value="중복체크">
	<br> 
	비밀번호* <input type="password" name="pwd"><br> 
	비밀번호확인* <input type="password" name="pwdCheck"><br> 
	이름* <input type="text" name= "name"><br>
	이메일* <input type="text" name="email">
	<input type="button" value="이메일중복확인">
	<br>
	전화번호*<input type="text" name="phone"><br>
	배송 주소*<input type="text" name ="addr"><br>
	<form>
	성별<input type="radio" name="gender" value="male" >남자
	<input type="radio" name="gender" value="female" >여자<br>
	</form>
	
	생년월일*<input type="text" name="birth"> ex) YYYYMMDD<br> 
	
	
	<input type="submit" value="가입하기">
</form>
</body>
</html>