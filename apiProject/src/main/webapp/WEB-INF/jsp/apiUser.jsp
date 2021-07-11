<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String userId;
	String userPw;

	if (session.getAttribute("userId") != null && session.getAttribute("userPw") != null) {
		userId = (String) session.getAttribute("userId");
		userPw = (String) session.getAttribute("userPw");
	}
%>
<html>
	<head>
		<%@include file ="bootstrapHeader.jsp" %>
	</head>
<script>
$(document).ready(function() {
	$("#userId").on("change", function() {
		$("#userPw").val("");
		$("input[name='userAvailable']:input[value='Y']").prop("checked",true);
		$("input[name='adminYn']:input[value='N']").prop("checked",true);
	});
	
	// 조회
	$("#searchBtn").on("click", function() {
		if($("#userId").val()==""){
			alert("조회할 id를 입력해주세요.")
			return false;
		}
		
		// 토큰 가지고 오기
		var userObject = {
			userId : "${userId}" ,
			userPw : "${userPw}"
		};
		
		var token = "";
		
		$.ajax({
			type : "POST",
			url : "/auth",
			data: JSON.stringify(userObject),
			async: false,
			contentType : "application/json; charset=UTF-8",
			success : function(data) {
				if (!(data == null || data == "")){
					console.log("accessToken : "+data.accessToken);
					token = data.accessToken;
				}
			}
		});
		
		$.ajax({
			type : "GET",
			url : "/admin/apiUser?userId="+$("#userId").val(),
			contentType : "application/json; charset=UTF-8",
			headers: {
				"Authorization" : "Bearer "+token
			} ,
			success : function(data) {
				if (!(data == null || data == "")){
					$("#userId").val(data.userId);
					$("#userPw").val(data.userPw);
					
					$("input[name='userAvailable']:input[value='"+data.userAvailable+"']").prop("checked",true);
					$("input[name='adminYn']:input[value='"+data.adminYn+"']").prop("checked",true);

				} else {
					alert("조회 내역이 없습니다.");
				}
			},
			error : function(a, b, c){
				alert(b);
				return;
			}
		});
	});
	
	//저장
	$("#saveBtn").on("click", function() {
		// 토큰 가지고 오기
		var userObject = {
			userId : "${userId}" ,
			userPw : "${userPw}"
		};
		
		var token = "";
		
		$.ajax({
			type : "POST",
			url : "/auth",
			data: JSON.stringify(userObject),
			async: false,
			contentType : "application/json; charset=UTF-8",
			success : function(data) {
				if (!(data == null || data == "")){
					console.log("accessToken : "+data.accessToken);
					token = data.accessToken;
				}
			}
		});
		
		var param = {
			userId : $("#userId").val(),
			userPw : $("#userPw").val(),
			userAvailable : $("input[name='userAvailable']:checked").val(),
			adminYn : $("input[name='adminYn']:checked").val()
		};
		
		$.ajax({
			type : "POST",
			url : "/admin/apiUser",
			data: JSON.stringify(param),
			contentType : "application/json; charset=UTF-8",
			headers: {
				"Authorization" : "Bearer "+token
			} ,
			success : function(data) {
				if (data > 0){
					//저장
					alert("저장을 완료하였습니다.");
				} else {
					alert("저장에 실패하였습니다.");
				}
			}
		});
	});
});
</script>
<body>
<div class="bs-component">
	<div class="container">
		<div class="text-left mt-4">
			<h6 class="h6 font-weight-normal">사용자등록</h6>
		</div>
			
		<div class="row justify-content-left mt-4">
			<div class="form-group row">
				<label for="userId" class="col-md-2 col-form-label text-end">ID :</label>
				<div class="col-md-8">
					<input type="text" id="userId" class="form-control" name="userId" required autofocus>
				</div>
				<div class="col-md-2">
					<button type="button" id="searchBtn" class="btn btn-primary">조회</button>
					<button type="button" id="saveBtn" class="btn btn-primary">저장</button>
				</div>
			</div>
			<div class="form-group row mt-4">
				<label for="userPw" class="col-md-2 col-form-label text-end">Password :</label>
				<div class="col-md-8">
					<input type="password" id="userPw" class="form-control" name="userPw" required>
				</div>
				<div class="col-md-2">
				</div>
			</div>
			<div class="form-group row mt-4">
				<label class="col-md-2 col-form-label text-end">사용여부 :</label>
				<div class="col-md-10 mt-2">
					<div class="form-check form-check-inline">
						<label class="form-check-label">
							<input type="radio" class="form-check-input" name="userAvailable" id="userYn1" value="Y" checked="checked">
								사용
						</label>
					</div>
					<div class="form-check form-check-inline">
						<label class="form-check-label">
							<input type="radio" class="form-check-input" name="userAvailable" id="userYn2" value="N">
								미사용
						</label>
					</div>
				</div>
			</div>
			<div class="form-group row mt-4">
				<label class="col-md-2 col-form-label text-end">관리자여부 :</label>
				<div class="col-md-10 mt-2">
					<div class="form-check form-check-inline">
						<label class="form-check-label">
							<input type="radio" class="form-check-input" name="adminYn" id="adminYn1" value="Y">
								사용
						</label>
					</div>
					<div class="form-check form-check-inline">
						<label class="form-check-label">
							<input type="radio" class="form-check-input" name="adminYn" id="adminYn2" value="N" checked="checked">
								미사용
						</label>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>