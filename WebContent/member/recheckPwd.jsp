<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
    
    #login-form > div {
        margin: 15px 0;
    }
    #info{font-size:0.75em;}
</style>
<div class="container">
    <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info class">
            <div class="panel-heading">
                <div class="panel-title">비밀번호 재확인</div>
                 <p id="info">회원님의 정보를 안전하게 보호하기 위해 비밀번호를 다시 한번 확인해주세요.</p>
            </div>
            
           
            
            <div class="panel-body">
                <form id="login-form" method="post" action="/Semi-MarketHoly/member/checkPwd.do">
                    <strong>아이디</strong><br>    
                    ${sessionScope.memberDto.id} <br>
                    <strong>비밀번호</strong><br>
                    <input type="text" name= "id" value="${sessionScope.memberDto.id}" hidden="hidden">
                    <div>
                        <input type="password" class="form-control" name="pwd" placeholder="비밀번호를 입력해주세요" autofocus>
                    </div>
                    <div>
                        <input type="submit" class="form-control btn btn-primary" value="확인"></input>
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