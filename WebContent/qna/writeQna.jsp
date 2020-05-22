<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<% request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>writeReview.jsp</title>
<style type="text/css">
	.input { width:500px;  }
	label{margin-top: 10px;}
	#btn{width:120px; height:80px;}
</style>
</head>

<body>

	<h1>QnA 작성하기</h1>
<form method="post" action="${pageContext.request.contextPath }/qna/qnaWrite.do">    
  
	<input type="hidden" name="ref" value="${param.ref }">
   
    <!-- 답글인 경우 부모글에 대한 정보 보내기 -->
	pnum<input type="text" name="pnum" value="${pnum }"><br> <!-- request를 오는걸 hidden -->
	num<input type="text" name="num" value="${memDto.num }"><br>	
	id<input type="text" name="id" value="${memDto.id }"><br>
	작성자 <br>	
		<input type="text" name="name" value="${memDto.name }"><br>
	제목 <br>	
		<input type="text" name="title"><br>
	내용 <br>
		<textarea cols="50" rows="5" name="content"></textarea><br>
	비밀글<br>
	<input type="radio" name="locker" value="Y" ><br>
	
	<input type="submit" value="등록"><br>
</form>






</body>
</html>