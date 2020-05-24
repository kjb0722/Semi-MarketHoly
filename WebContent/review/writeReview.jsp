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
	#wrap{position:relative; left:28%; }
	#title{text-align: center;}
</style>
</head>
<body>


<h1 id="title">상품 후기 작성하기</h1><br>
<div id="wrap" class="container">
	<form method="post" action="${pageContext.request.contextPath }/review/writeReview.do" enctype="multipart/form-data">    
   
    <!-- 답글인 경우 부모글에 대한 정보 보내기 -->
	<input type="hidden" name="onum" value="${numbersDto.onum }" >
	<input type="hidden" name="pnum" value="${numbersDto.pnum }" >
	<input type="hidden" name="num" value="${num }">
	
	<strong>아이디</strong><br>
	<div class="input input-group mb-3"> 	
		<input type="text" name="id" value="${id }" readonly="readonly" class="form-control"><br>
	</div>
	<br>
	<strong>작성자</strong> <br>
	<div class="input input-group mb-3"> 	
		<input type="text" name="name" value="${name }" readonly="readonly" class="form-control"><br>
	</div>
	<br>
	<strong>제목</strong> <br>
	<div class="input input-group mb-3"> 
		<input type="text" name="title" class="form-control"><br>
	</div>
	<br>
	<strong>내용</strong> <br>
	<div class="input input-group mb-3"> 
		<textarea cols="50" rows="5" name="content" class="form-control"></textarea><br>
	</div>
	<br>
	<strong>사진첨부하기</strong><br>
	<input type="file" name="file1" ><br>
	
	<input id="btn" type="submit" value="등록"><br>
	</form>
</div>
</body>
</html>