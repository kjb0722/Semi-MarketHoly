<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>qna/writeQna.jsp</title>
</head>
<body>
	<h1>상품 후기 작성하기</h1>
<form method="post" action="${pageContext.request.contextPath }/qna/qnaWrite.do">    
     <input type="hidden" name="qnum" value="${param.qnum }">
	<input type="hidden" name="ref" value="${param.ref }">
	<input type="hidden" name="lev" value="${param.lev }">
	<input type="hidden" name="step" value="${param.step }">
   
    <!-- 답글인 경우 부모글에 대한 정보 보내기 -->
	pnum<input type="text" name="pnum" ><br> <!-- request를 오는걸 hidden -->
	num<input type="text" name="num" ><br>	
	id<input type="text" name="id" ><br>
	작성자 <br>
	<input type="text" name="name"><br>
	비밀번호<br>
	<input type="password" name="pwd"><br>
	제목 <br>
	<input type="text" name="title"><br>
	내용 <br>
	<textarea cols="50" rows="5" name="content"></textarea><br>
	<input type="submit" value="등록"><br>
</form>






</body>
</html>