<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<% request.setCharacterEncoding("utf-8"); %>




<style type="text/css">

	label{margin-top: 10px;}
	#btn{width:120px; height:60px;}
	#title{text-align: center;}
	#wrap{position:relative; left:28% }
</style>


<h1 id="title">QnA 작성하기</h1>
<div id="wrap" class="container">
	<form method="post" action="${pageContext.request.contextPath }/qna/qnaWrite.do" onsubmit="return validate();">    	  
		
			<input type="hidden" name="pnum" value="${pnum }"><br> <!-- request를 오는걸 hidden -->
		
		
		<strong>아이디</strong><br>
		
			<input type="text" name="id" value="${memDto.id }" readonly="readonly"><br>
		
		<strong>작성자</strong> <br>	
			
			<input type="text" name="name" value="${memDto.name }" readonly="readonly"><br>
	
		<strong>제목</strong> <br>
			<input type="text" name="title" id="title">
			<br>
		<strong>내용</strong> <br>
			<textarea cols="50" rows="5" name="content" id="content"></textarea><br>
		
		<label>비밀글 <input type="checkbox" name="locker" value="Y" ></label>
		<br>
			<input type="hidden" name="ref" value="${param.ref }">
			<input type="hidden" name="num" value="${memDto.num }"><br>	
		
		
		<input id="btn" type="submit" value="등록"><br>
	</form>
</div>

<script>
	function validate() {
		var title = document.getElementById("title");
		var content = document.getElementById("content");
		
		if(title.value==""){
			alert("제목을 입력해주세요.");
			return false;
		}
		
		if(content.value==""){
			alert("내용을 입력해주세요.");
			return false;
		}
		return true;
	}



</script>




