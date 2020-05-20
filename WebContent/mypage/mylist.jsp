<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mypage/mylist.jsp</title>
</head>
<body>
<h3>배송정보</h3>
<ul>
	<li>${sessionScope.mebmerDto.name }</li>
	<li>${sessionScope.memberDto.phone }</li>
	<li>${sessionScope.memberDto.addr }</li>
</ul>







</body>
</html>