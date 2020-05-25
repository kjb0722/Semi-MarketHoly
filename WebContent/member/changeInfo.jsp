<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>   
    
    #login-form > div {
        margin: 15px 0;
    }
</style>


<div class="container">
    <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2" >
        <div class="panel panel-info class">
            <div class="panel-heading">
                <div class="panel-title"><h1>개인정보수정</h1></div>
                	기본정보
            </div>
            <div class="panel-body">
                <form id="login-form" method="post" action="${cp }/member/updateInfo.do" onsubmit="return validate();">	
                    <div>
                    	아이디
                        <input type="text" class="form-control" name="id" id="id" value="${sessionScope.memberDto.id}" readonly="readonly">
                    </div>
                    
                    
                    <div>
                    	현재비밀번호
                        <input type="password" class="form-control" name="curPwd" id="curPwd" >
                    </div>
                    <div>
                    	새 비밀번호
                        <input type="password" class="form-control" name="nextPwd" id="nextPwd" onkeyup="checkPwd1()">
                    </div>
                    <div id="checkDiv"></div>
                    
                    <div>
                    	새 비밀번호 확인
                        <input type="password" class="form-control" name="checkPwd" id="checkPwd" onkeyup="checkPwd2()">
                    </div>
                    
                     
                    <div id="checkDiv2"></div>
                    <div>
                    	이름*
                        <input type="text" class="form-control" name="name" id="name" value="${sessionScope.memberDto.name}">
                    </div>
                    <div>
                    	이메일*
                        <input type="text" class="form-control" name="email" id="email" value ="${sessionScope.memberDto.email}">
    
                    </div>
                    <div>
                    	휴대폰번호*
                        <input type="text" class="form-control" name="phone" id="phone" value= "${sessionScope.memberDto.phone}">
                    </div>
          
                    <div>
                        <input type="submit" class="form-control btn btn-primary" value="회원정보수정"></input><br>
                    </div> 
                    <div>
                        <input type="submit" class="form-control btn btn-primary" formaction="${cp }/member/deleteAccount.do" value="회원탈퇴"></input><br>
                    </div> 
					
                </form>
            </div>
        </div>
    </div>
</div>
</body>

<script>
	
	function validate(){
		var curPwd = document.getElementById("curPwd");
		var nextPwd = document.getElementById("nextPwd");
		var checkPwd = document.getElementById("checkPwd");
		
		
		var pwCheck = /^[a-zA-Z0-9]{6,12}$/;
		
		if(curPwd.value!=""){
			if(!pwCheck.test(nextPwd.value)){
	        	alert("비밀번호는 6~12자리로 특수기호 없이 입력해주세요.");
	        	return false;
	    	}	
			if(!(nextPwd.value == checkPwd.value)){
				alert("비밀번호가 다릅니다.");
				return false;
			}
		}
		return true;
	}
		

	function checkPwd1(){
		var curpwd = document.getElementById("curPwd").value;
		var nextPwd = document.getElementById("nextPwd").value;
		var div = document.getElementById("checkDiv");
		
		if(curpwd==nextPwd){
			div.innerHTML="현재 비밀번호와 같습니다. 다르게 입력하세요";
			div.style.color="red";
		}else{
			div.innerHTML="현재비밀번호와 다르게 입력하세요."
			div.style.color="green";
		}
	}
	
	
	
	function checkPwd2(){
		var nextPwd = document.getElementById("nextPwd").value;
		var checkPwd = document.getElementById("checkPwd").value;
		var div = document.getElementById("checkDiv2");
		
		if(nextPwd==checkPwd){
			div.innerHTML="일치하는 비밀번호입니다.";
			div.style.color="green";
		}else{
			div.innerHTML="비밀번호가 일치하지 않습니다.";
			div.style.color="red";
		}
	}
	
	
	
	
	
	
</script>

