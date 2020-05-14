<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member.login.jsp</title>
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
                <div class="panel-title">로그인</div>
            </div>
            <div class="panel-body">
                <form id="login-form" method="post" action="/Semi-MarketHoly/member/login.do">
                    <div>
                        <input type="text" class="form-control" name="id" placeholder="아이디를 입력해주세요" autofocus>
                    </div>
                    <div>
                        <input type="password" class="form-control" name="pwd" placeholder="비밀번호를 입력해주세요.">
                    </div>
                    <a href="${pageContext.request.contextPath }/member/findId.do">아이디찾기</a>
                    <a href="${pageContext.request.contextPath }/member/findPwd.do">비밀번호찾기</a>
                    <div>
                        <input type="submit" class="form-control btn btn-primary" value="로그인"></input><br><br>
                        <button type="button" class="form-control btn btn-primary" onclick="intoJoin()">회원가입</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script>
	function intoJoin() {
		location = "${pageContext.request.contextPath }/member/start.do";
	}

</script>


</html>