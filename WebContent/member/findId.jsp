<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<style>   
    
    #login-form > div {
        margin: 15px 0;
    }
</style>



<div class="container">
    <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info class">
            <div class="panel-heading">
                <div class="panel-title"><h1>아이디찾기</h1></div>
            </div>
            <div class="panel-body">
                <form id="login-form" method="get" action="${pageContext.request.contextPath }/member/findId.do">	
                    <div>
                    	이름
                        <input type="text" class="form-control" name="name" placeholder="고객님의 이름을 입력해주세요" autofocus>
                    </div>
                    <div>
                    	이메일
                        <input type="text" class="form-control" name="email" placeholder="가입시 등록하신 이메일주소를 입력해주세요">
                    </div>
                    <div>
                        <input type="submit" class="form-control btn btn-primary" value="확인"></input><br>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


