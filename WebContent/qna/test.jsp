<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:forEach var="item" items="${list}" begin=0 end=5 step=1 varStatus="status">
    번호 : ${status.count}
    이름 : ${item.name}
    나이 : ${item.age}
    주소 : ${item.addr}
</c:forEach>

</body>
</html>