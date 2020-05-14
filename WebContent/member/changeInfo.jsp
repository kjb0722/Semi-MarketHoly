<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>   
    
    #login-form > div {
        margin: 15px 0;
    }
</style>



<div class="container">
    <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-success">
            <div class="panel-heading">
                <div class="panel-title"><h1>개인정보수정</h1></div>
                	기본정보
            </div>
            <div class="panel-body">
                <form id="login-form" method="post">	
                    <div>
                    	아이디
                        <input type="text" class="form-control" name="id" autofocus>
                    </div>
                    <div>
                    	현재비밀번호
                        <input type="text" class="form-control" name="curPwd">
                    </div>
                    <div>
                    	새 비밀번호
                        <input type="text" class="form-control" name="curPwd">
                    </div>
                    <div>
                    	새 비밀번호 확인
                        <input type="text" class="form-control" name="curPwd">
                    </div>
                    <div>
                    	이름
                        <input type="text" class="form-control" name="curPwd">
                    </div>
                    <div>
                    	이메일
                        <input type="text" class="form-control" name="curPwd">
                    </div>
                    <div>
                    	휴대폰번호
                        <input type="text" class="form-control" name="curPwd">
                    </div>
          
                    <div>
                        <input type="submit" class="form-control btn btn-primary" formaction=""  value="회원정보수정"></input><br>
                    </div>
                    <div>
                        <input type="submit" class="form-control btn btn-primary" formaction="" value="회원탈퇴"></input><br>
                    </div>             
                </form>
            </div>
        </div>
    </div>
</div>