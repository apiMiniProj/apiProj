<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<%@include file ="bootstrapHeader.jsp" %>
	</head>
	<body>
	<main  id ="prods-form"  class="prods-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="text-center mb-4">
				        <h1 class="h3 mb-3 font-weight-normal">API SEMINAL</h1>
				        <p>[상품정보]</p>
				    </div>
                                
                </div>
                <div class="card-body" >    
                <form action="" id="Prods"  target="iframe1" > 
                	<div class="row justify-content-center">         
                		<div class="form-group row">                         
                			<div class="col-sm-6">  
                			<input type="text" id="pro" class="form-control" name="pro" placeholder="상품코드를 입력하세요." autofocus>   </div>  &nbsp; 
                            <div class="col-sm">
                            <input  type="button" name = "select" value="조회" id="selectBtn" onClick="btnSelectClick()" class="btn btn-primary">  &nbsp;                               
                            <input  type="submit" name = "save"  value="저장" id="saveBtn" onClick="btnSaveClick()" class="btn btn-primary">   &nbsp; 
                            <input  type="button" name = "delete" value="삭제" id="deleteBtn" onClick="btnDeleteClick()" class="btn btn-primary">  &nbsp; 
                            <input  type="button" name = "init" value="초기화" onClick="btnInitClick();" class="btn btn-primary"> </div>                                 
                		</div>                             
                	</div> 
                   	<br> </br>   
                	<div class="form-group row">
                		<label for="pro_code" class="col-md-4 col-form-label text-md-right">상품코드 :</label>
                		<div class="col-md-6">
                			<input type="text" id="pro_code" class="form-control" name="pro_code" placeholder="*저장시 필수값*" required>
                		</div>
                	</div>

                	<div class="form-group row">
                		<label for="pro_name" class="col-md-4 col-form-label text-md-right">상품이름 :</label>
                			<div class="col-md-6">
                				<input type="text" id="pro_name" class="form-control" name="pro_name">
                			</div>
                	</div>

                	<div class="form-group row">
                		<label for="pro_category" class="col-md-4 col-form-label text-md-right">상품종류 :</label>
                			<div class="col-md-6">
                				<input type="text" id="pro_category" class="form-control" name="pro_category">
                			</div>
                	</div>
                         
                	<div class="form-group row"> 
                		<label for="pro_brand" class="col-md-4 col-form-label text-md-right">상품브랜드 :</label>
                			<div class="col-md-6">
                				<input type="text" id="pro_brand" class="form-control" name="pro_brand">
                			</div>
                	</div>
                         
                	<div class="form-group row">
                		<label for="pro_price" class="col-md-4 col-form-label text-md-right">상품가격 :</label>
                			<div class="col-md-6">
                				<input type="text" onkeyPress="return checkNumber(event);" id="pro_price" class="form-control" name="pro_price">
                			</div>
                	</div>
                         
                	<div class="form-group row">
               			<label for="pro_info" class="col-md-4 col-form-label text-md-right">상품정보 :</label>
                			<div class="col-md-6">
                				<input type="text" id="pro_info" class="form-control" name="pro_info">
                			</div>
                	</div>
                         
                	<div class="form-group row">
                		<label for="pro_available" class="col-md-4 col-form-label text-md-right">사용여부 :</label>
                			<div class="col-md-6">
                				<input type="radio" id="pro_available" value="Y" name="pro_available" checked="checked" /> 사용[Y] &nbsp; 
                                <input type="radio" id="pro_available" value="N" name="pro_available" /> 미사용[N]
                			</div>
                	</div>
                </form>
                    
                </div>
            	</form>
            </div>
        </div>
    </div>
<iframe id="iframe1" name="iframe1" style="display:none"></iframe>
    </div>
    </div>

	</main>
	</body>
</html>

<script>
//초기화
function btnInitClick(){
	$("#Prods")[0].reset();
}

//상품 저장
function btnSaveClick(){
	// 토큰 가지고 오기
	var userObject = {
		userId : "user" ,
		userPw : "user" 
	};
	
	var token = "";
	
	$.ajax({
		type : "POST",
		url : "http://210.182.19.6:7070/auth",
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
			proCode : $("#pro_code").val(),
			proName : $("#pro_name").val(),
			proCategory : $("#pro_category").val(),
			proBrand : $("#pro_brand").val(),
			proPrice : $("#pro_price").val(),
			proInfo : $("#pro_info").val(),
			proAvailable : $("#pro_available").val()
		};
	
	$.ajax({
		type : "POST",
		url : "http://210.182.19.6:7070/api/SaveApiProds",
		data: JSON.stringify(param),
		contentType : "application/json; charset=UTF-8",
		headers: {
			"Authorization" : "Bearer "+token
		} ,
		success : function(data) {
			if (data > 0){
				//저장
				alert("상품을 저장하였습니다.");
				$("#Prods")[0].reset();
			}
		}
	});
}

//상품 삭제
function btnDeleteClick(){
	// 토큰 가지고 오기
	var userObject = {
		userId : "user" ,
		userPw : "user" 
	};
	
	var token = "";
	
	$.ajax({
		type : "POST",
		url : "http://210.182.19.6:7070/auth",
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
			proCode : $("#pro").val()
		};
	
	$.ajax({
		type : "POST",
		url : "http://210.182.19.6:7070/api/DeleteApiProds",
		data: JSON.stringify(param),
		contentType : "application/json; charset=UTF-8",
		headers: {
			"Authorization" : "Bearer "+token
		} ,
		success : function(data) {
			if (data > 0){
				//삭제
				alert("해당 상품이 삭제 되었습니다.");
				$("#Prods")[0].reset();
			}else{
				alert( "' "+$("#pro").val()+" ' 에 대한 상품정보가 없습니다.");
				$("#Prods")[0].reset();
			}
		}
	});
}

//상품 조회
function btnSelectClick(){
	// 토큰 가지고 오기
	var userObject = {
		userId : "user" ,
		userPw : "user" 
	};
	
	var token = "";
	
	$.ajax({
		type : "POST",
		url : "http://210.182.19.6:7070/auth",
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
			proCode : $("#pro").val()
		};
	
	$.ajax({
		type : "GET",
		url : "http://210.182.19.6:7070/api/SelectApiProds",
		data: param,
		contentType : "application/json; charset=UTF-8",
		headers: {
			"Authorization" : "Bearer "+token
		} ,
		success : function(data) {
			if (!(data == null || data == "")){
				$('#pro_code').val(data.proCode);
				$('#pro_name').val(data.proName);
				$('#pro_category').val(data.proCategory);
				$('#pro_brand').val(data.proBrand);
				$('#pro_price').val(data.proPrice);
				$('#pro_info').val(data.proInfo);
				$('#pro_available').val(data.proAvailable);
			} else {
				alert( "' "+$("#pro").val()+" ' 에 대한 상품정보가 없습니다.");
				$("#Prods")[0].reset();
			}
		}
	});
}

//상품가격 숫자만 입력하도록 설정
function checkNumber(e) {
	var keyVal = event.keyCode;
	 
    if(((keyVal >= 48) && (keyVal <= 57))){
        return true;
    }
    else{
        alert("숫자만 입력가능합니다");
        return false;
    }
}
	
</script>


