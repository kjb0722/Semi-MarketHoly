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
				<th>아이디</th>
				<th>제목</th>
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
				<tr onclick="showHidden(${dto.qnum})">	
					<td>${dto.qnum }</td>
					<td>${dto.id }</td>
					<td>${dto.title }</td>
					<td>${dto.regdate }</td>
				</tr>
				<tr id='${dto.qnum }' style='display:none;'>					
 					<td>${dto.content }</td>
				</tr>	
				
			</tbody>	
		</c:forEach>	
	</table>
</div>
		<input type="button" value = "후기쓰기" style="float:right" onclick="location.href='${cp }/review/writeReview.jsp'">
		<br><br>
<!-- 페이징처리 -->



</body>

<script>
	function showHidden(qNum) {
		var id =document.getElementById(qNum);
		
		if(id.style.display == 'none') id.style.display = 'block' ;
		else id.style.display = 'none' ;
	}
	
</script>


</html>