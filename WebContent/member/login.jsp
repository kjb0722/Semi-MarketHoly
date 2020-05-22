<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
    
    #login-form > div {
        margin: 15px 0;
    }
    #tags{text-align: right; font-size: 16px;}
    
</style>
<div class="container">
    <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info class">
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
                    <div id ="tags">
	                    <a href="${pageContext.request.contextPath }/member/startFindId.do">아이디찾기</a> /
	                    <a href="${pageContext.request.contextPath }/member/startFindPwd.do">비밀번호찾기</a>
                    </div>
                    <div>
                        <input type="submit" class="form-control btn btn-primary" value="로그인"></input><br><br>
                        <button type="button" class="form-control btn btn-primary" onclick="intoJoin()">회원가입</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
	function intoJoin() {
		location = "${pageContext.request.contextPath }/member/startJoin.do";
	}

</script>