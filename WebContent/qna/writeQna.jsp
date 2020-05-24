<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<% request.setCharacterEncoding("utf-8"); %>




<style type="text/css">
	.input2 { width:500px;  }
	label{margin-top: 10px;}
	#btn{width:120px; height:60px;}
	#title{text-align: center;}
	#wrap{position:relative; left:28% }
</style>
<h1 id="title">QnA 작성하기</h1>
<div id="wrap" class="container">
	<form method="post" action="${pageContext.request.contextPath }/qna/qnaWrite.do">    	  
		
			<input type="hidden" name="pnum" value="${pnum }"><br> <!-- request를 오는걸 hidden -->
		
		
		<strong>아이디</strong><br>
		<div class="input2 input-group mb-3">
			<input type="text" name="id" value="${memDto.id }" readonly="readonly"><br>
		</div>
		<strong>작성자</strong> <br>	
		<div class="input2 input-group mb-3"> 	
			<input type="text" name="name" value="${memDto.name }" readonly="readonly"><br>
		</div>
		<strong>제목</strong> <br>
		<div class="input2 input-group mb-3"> 		
			<input type="text" name="title"><br>
		</div>
		<strong>내용</strong> <br>
			<textarea cols="50" rows="5" name="content"></textarea><br>
		
		<label>비밀글 <input type="checkbox" name="locker" value="Y" ></label>
		<br>
			<input type="hidden" name="ref" value="${param.ref }">
			<input type="hidden" name="num" value="${memDto.num }"><br>	
		
		
		<input id="btn" type="submit" value="등록"><br>
	</form>
</div>





