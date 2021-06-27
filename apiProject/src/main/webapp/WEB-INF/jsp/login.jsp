<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<%@include file ="bootstrapHeader.jsp" %>
		
	</head>
	<body>
	    <main id ="login-form"  class="login-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="text-center mb-4">
				        <h1 class="h3 mb-3 font-weight-normal">API LOGIN</h1>
				        <p>[ADMIN]</p>
				      </div>
                    <div class="card-body">
                        <form action="" method="">
                            <div class="form-group row">
                                <label for="email_address" class="col-md-4 col-form-label text-md-right">ID :</label>
                                <div class="col-md-6">
                                    <input type="text" id="email_address" class="form-control" name="id" required autofocus>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right">Password :</label>
                                <div class="col-md-6">
                                    <input type="password" id="password" class="form-control" name="password" required>
                                </div>
                            </div>

                            <div class="col-md-6 offset-md-4">
                                <button  type="submit" class="btn btn-primary">
                                    로그인
                                </button>
                            </div>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    </div>

</main>
	  </body>
</html>


<script>
(function() {
	  "use strict";
	  window.addEventListener("load", function() {
	    var form = document.getElementById("login-form");
	    form.addEventListener("submit", function(event) {
	      	$.ajax({
				type: "GET", 
				url:"/userTest",
				dataType:"text", 
				success : function(result){
					alert(result);
					location.href = "";		// 성공시 해당 페이지로 이동 ex) location.href  = "home"; 
				},
				error : function(a, b, c){
					//통신 실패시 발생하는 함수(콜백)
					alert(a + b + c);
				}
			});
	      // 서버 연동 처리
	    }, false);
	  }, false);
	}());
</script>
