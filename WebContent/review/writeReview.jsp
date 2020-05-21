<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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


<h1>상품 후기 작성하기</h1>
<div id="wrap">
	<form method="post" action="${pageContext.request.contextPath }/review/writeReview.do" enctype="multipart/form-data">    
   
    <!-- 답글인 경우 부모글에 대한 정보 보내기 -->
	onum<input type="text" name="onum" value="${numbersDto.onum }" ><br>		<!-- request를 오는걸 hidden -->
	pnum<input type="text" name="pnum" value="${numbersDto.pnum }" ><br>
	num<input type="text" name="num" value="${num }"><br>
	
	아이디<br>
	<div class="input input-group mb-3"> 	
		<input type="text" name="id" value="${id }" readonly="readonly" class="form-control"><br>
	</div>
	<br>
	작성자 <br>
	<div class="input input-group mb-3"> 	
		<input type="text" name="name" value="${name }" readonly="readonly" class="form-control"><br>
	</div>
	<br>
	제목 <br>
	<div class="input input-group mb-3"> 
		<input type="text" name="title" class="form-control"><br>
	</div>
	<br>
	내용 <br>
	<div class="input input-group mb-3"> 
		<textarea cols="50" rows="5" name="content" class="form-control"></textarea><br>
	</div>
	<br>
	사진첨부하기<br>
	<input type="file" name="file1" ><br>
	
	<input id="btn" type="submit" value="등록"><br>
	</form>
</div>
</body>
</html>