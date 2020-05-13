<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>foundId.jsp</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/latest/css/bootstrap.min.css" rel="stylesheet">
<style>
    body {
 
        background: #f8f8f8;
        padding: 60px 0;
    }
    
    #login-form > div {
        margin: 15px 0;
    }
</style>
</head>
<body>

<div class="container">
    <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-success">
            <div class="panel-heading">
                <div class="panel-title"><h1>아이디찾기</h1></div>
            </div>
            <div class="panel-body">
                	<br>
                    <div>
                    	고객님의 아이디는 ${id } 입니다.
                    </div>
                    <br>
                    <div>
                        <input type="button" class="form-control btn btn-primary" value="로그인페이지로 이동" onclick="intoLogin()"></input><br>
                    </div>
               
            </div>
        </div>
    </div>
</div>
</body>
<script>
	function intoLogin() {
		location = "${pageContext.request.contextPath }/member/loginstart.do";
	}
</script>

</html>