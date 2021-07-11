<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userId;

    if (session.getAttribute("userId") != null) {
		userId = (String) session.getAttribute("userId");
    } else{
    	response.sendRedirect("/"); //세션 없으면 이동
    }
%>
<!DOCTYPE html>
<html>
	<head>
		<%@include file ="bootstrapHeader.jsp" %>
	</head>
<script>
	$(document).ready(function() {
		$(".nav-item a").on("click", function(){
			// active 메뉴 처리
			$(".nav-link").removeClass("active")
			$(this).addClass("active")
			
			// 메뉴 이동
			$('#iframe').attr('src',$(this).attr('data-url'));
		});
	});
</script>
<body>
<div class="bs-component">
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<div class="container">
			<a class="navbar-brand" href="/main">API ADMIN</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarColor01">
				<ul class="navbar-nav me-auto">
					<li class="nav-item">
						<a class="nav-link active" data-url="/apiIntro">Intro
							<span class="visually-hidden">(current)</span>
						</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" data-url="/apiUser">사용자
						</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" data-url="/apiCustomQuery">커스텀쿼리
						</a>
					</li>
					</ul>
					<ul class="navbar-nav ms-md-auto">
					<li class="nav-item">
						<span class="text-muted">${userId}</span>
					</li>
				</ul>
			</div>
		</div>
	</nav>
		
	<div class="container">
		<iframe id="iframe" width="100%" height="800" src="/apiIntro"></iframe>
	</div>
</div>
</body>
</html>
