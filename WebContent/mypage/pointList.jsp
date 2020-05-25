<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#rating{position: relative; left:190px;}
	#table1{position: relative; left:130px; width:800px;}
</style>
</head>
<body>

	<img id="rating" src="${cp }/img/rating.png">
	<br><br><br>
	<table id="table1" class="table table-bordered">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>포인트</th>
			<th>등급</th>
		</tr>
		<tr>
			<td>${pointDto.id }	</td>
			<td>${pointDto.name }	</td>
			<td>${pointDto.point }	</td>	
			<c:choose>	
				<c:when test="	${pointDto.rating>=0}	">
					<td>일반회원</td>
				</c:when>
				<c:when test="	${pointDto.rating>=20}	">
					<td>화이트</td>
				</c:when>
				<c:when test="	${pointDto.rating>=30}	">
					<td>라벤더</td>
				</c:when>
				<c:otherwise>
					<td>퍼플</td>
				</c:otherwise>

			</c:choose>	
		</tr>
	</table>
</body>
</html>