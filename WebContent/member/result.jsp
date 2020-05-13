<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/result.jsp</title>
</head>
<body>
<c:choose> 
	<c:when test="${code=='success' }">
		<h1>회원가입성공!!!!!!</h1>
	</c:when>
	<c:otherwise>
		<h1>회원가입실패!!!</h1>
	</c:otherwise>
</c:choose>
</body> 
</html>