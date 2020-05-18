<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>writeReview.jsp</title>
</head>
<body>
<h1>상품 후기 작성하기</h1>
<form method="post" action="${pageContext.request.contextPath }/review/writeReview.do" enctype="multipart/form-data">
    <!-- 답글인 경우 부모글에 대한 정보 보내기 -->
	onum<input type="text" name="onum" ><br>		<!-- request를 오는걸 hidden -->
	pnum<input type="text" name="pnum" ><br>
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
	사진첨부하기<br>
	<input type="file" name="file1"><br>
	<input type="submit" value="등록"><br>
</form>
</body>
</html>