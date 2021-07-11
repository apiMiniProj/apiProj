<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<%@include file ="bootstrapHeader.jsp" %>
		
	</head>
<script type="text/javascript">
$(document).ready(function() {
	$("#loginBtn").on("click", function() {
		var userObject = {
			userId : $("#userId").val(),
			userPw : $("#userPw").val()
		};
		 
		$.ajax({
			type : "POST",
			url : "/login",
			data: JSON.stringify(userObject),
			contentType : "application/json; charset=UTF-8",
			success : function(data) {
				console.log("data: " + data);
				
				if(data.userId == $("#userId").val()){
					//로그인 완료
					location.href = "/admin/main";	
				} else {
					alert("아이디와 비밀번호를 확인해주세요.");
				}
			}
		});
	});
});
</script>
	<body>
	    <main  id ="login-form"  class="login-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="text-center mb-4">
				        <h1 class="h3 mb-3 font-weight-normal">API LOGIN</h1>
				        <p>[ADMIN]</p>
				      </div>
                    <div class="card-body">
                        <form action="/" method="" target="iframe1">
                            <div class="form-group row mb-2">
                                <label for="email_address" class="col-md-4 col-form-label text-end">ID :</label>
                                <div class="col-md-6">
                                    <input type="text" id="userId" class="form-control" name="userId" required autofocus>
                                </div>
                            </div>

                            <div class="form-group row mb-2">
                                <label for="password" class="col-md-4 col-form-label text-end">Password :</label>
                                <div class="col-md-6">
                                    <input type="password" id="userPw" class="form-control" name="userPw" required>
                                </div>
                            </div>

                            <div class="col-md-6 offset-md-4">
                                <button id="loginBtn" type="submit" class="btn btn-primary">
                                    로그인
                                </button>
                            </div>
                   	    </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
<iframe id="iframe1" name="iframe1" style="display:none"></iframe>
</main>
	  </body>
</html>


<script>
(function() {
	  "use strict";
	  window.addEventListener("load", function() {
	    /*var form = document.getElementById("login-form");
	    
	    form.addEventListener("submit", function(event) {
	    	//alert("로그인")
	    	var userObject = {
				userId : $("#userId").val(),
				userPw : $("#userPw").val()
			};
	    	
	      	$.ajax({
				type: "POST", 
				url:"/login",
				data : userObject,
				success : function(result){
					alert(result);
					
					location.href = "/admin/main";		// 성공시 해당 페이지로 이동 ex) location.href  = "home"; 
				},
				error : function(a, b, c){
					//통신 실패시 발생하는 함수(콜백)
					alert(a + b + c);
					return;
				}
			});
	      // 서버 연동 처리
	    }, false);*/
	  }, false);
	}());
</script>
