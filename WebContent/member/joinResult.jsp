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
	<c:when test="${code=='fail1' }">
		<script>
			alert("중복되는 이메일입니다.");
			window.history.back();	
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert("중복 아이디를 사용하지마세요.");
			window.history.back();	
		</script>
	
	</c:otherwise>
	
</c:choose>
</body> 
</html>


