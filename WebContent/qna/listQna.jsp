<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/qna/listQna.jsp</title>
</head>
<body>

<div class="container">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>qna번호</th>
				<th>제목</th>
				<th>아이디</th>
				<th>등록일</th>
			</tr>
			<tr style='display:none'>
				<th></th>
				<th></th>
				<th>내용</th>
				<th></th>
			</tr>
			
			
		</thead>
		<c:forEach var='dto' items='${list }'>
			<tbody>
				<tr onclick="showHidden(${dto.qnum},'${dto.id }','${dto.locker }')">					
					<td>${dto.qnum }</td>
					<c:choose>
						<c:when test="${dto.locker == 'Y'}">
							<td>${dto.title } <img src="${pageContext.request.contextPath }/img/locker.jpg" width="30px" height="30px"></td>
						</c:when>
						<c:otherwise>
							<td>${dto.title } </td>
						</c:otherwise>
					</c:choose>
					<td>${dto.id }</td>
					<td>${dto.reg_date }</td>
				</tr>
				<tr id='${dto.qnum }' style='display:none;'>					
 					<td>${dto.content }</td>
				</tr>	
				
			</tbody>	
		</c:forEach>	
	</table>
</div>
		<input type="button" value = "후기쓰기" style="float:right" onclick="location.href='${cp }/qna/writeQna.jsp'">
		<br><br>
<!-- 페이징처리 -->





</body>

<script>
	function showHidden(dtoQnum,dtoId,dtoLocker) {
		var id =document.getElementById(dtoQnum);
					
		if(dtoId == '${sessionScope.memberDto.id}' || dtoLocker=='N' ){
			if(id.style.display == 'none'){ 
				id.style.display = 'block' ;
			}else{ 
				id.style.display = 'none' ;
			}
		}else{
			alert("비밀글입니다.");
		}
	}
	
</script>


</html>