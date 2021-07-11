<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userId;
	String userPw;

	if (session.getAttribute("userId") != null && session.getAttribute("userPw") != null) {
		userId = (String) session.getAttribute("userId");
		userPw = (String) session.getAttribute("userPw");
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<%@include file ="bootstrapHeader.jsp" %>
	</head>
<script>
$(document).ready(function() {
	$("#customId").on("change", function() {
		$("#queryText").val("");
		$("#userAvailble").attr("checked", "checked");
	});

	// 조회
	$("#searchBtn").on("click", function() {
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
			url : "/admin/apiQuery?customId="+$("#customId").val(),
			contentType : "application/json; charset=UTF-8",
			headers: {
				"Authorization" : "Bearer "+token
			} ,
			success : function(data) {
				if (!(data == null || data == "")){
					$("#queryText").val(data.queryText);
					
					if (data.userAvailable == "Y"){
						$("#userAvailable").prop("checked", true);
					} else {
						$("#userAvailable").prop("checked", false);
					}
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
			customId : $("#customId").val(),
			userAvailable : ($("#userAvailable").is(":checked") ? "Y" : "N"),
			queryText : $("#queryText").val()
		};
		
		$.ajax({
			type : "POST",
			url : "/admin/apiQuery",
			data: JSON.stringify(param),
			contentType : "application/json; charset=UTF-8",
			headers: {
				"Authorization" : "Bearer "+token
			} ,
			success : function(data) {
				if (data > 0){
					//저장
					alert("저장을 완료하였습니다.");
					$("#customId").focus();
				} else {
					alert("저장에 실패하였습니다.");
					$("#queryText").focus();
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
			<h6 class="h6 font-weight-normal">CUSTOM API QUERY</h6>
		</div>
			
		<div class="row justify-content-left mt-4">
			<div class="form-group row">
				<div class="col-md-8">
					<select class="form-select" id="customId">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">4</option>
						<option value="4">5</option>
						<option value="5">6</option>
						<option value="6">7</option>
					</select>
				</div>
				<div class="col-md-2">
					<button type="button" id="searchBtn" class="btn btn-primary">조회</button>
					<button type="button" id="saveBtn" class="btn btn-primary">저장</button>
				</div>
				<div class="col-md-2 align-self-center">
					<input class="form-check-input" type="checkbox" id="userAvailable" checked="checked">
					<label class="form-check-label" for="userAvailble">사용여부</label>
				</div>
			</div>
			<div class="form-group mt-4">
				<textarea class="form-control" id="queryText" rows="15"></textarea>
			</div>
		</div>
	</div>
</div>
</body>
</html>