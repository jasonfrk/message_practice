<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<title>쪽지쓰기</title>
</head>
<style>
	.cls-nickname{
		cursor: pointer;
	}
</style>
<body>
	<form id="writeForm" action="${pagecontext.request.contextPath}/writeProc.msg" method="post">
		<div class="container">
			<div class="row">
				<div class="col-3">
					<span>보내는 이 : </span>
				</div>
				<div class="col-9">
					<input type="text" class="form-control" id="from_nickname" name="from_nickname" value="${loginSession.getNickname()}" readonly />
				</div>
			</div>
			<div class="row">
				<div class="col-3">
					<span>받는 이 : </span>
				</div>
				<div class="col-9">
					<input type="text" class="form-control" id="to_nickname" name="to_nickname" value="" readonly />
				</div>
			</div>
			<div class="row">
				<div class="col-3">
					<span>리스트 : </span>
				</div>
				<div class="col-9">
					<c:forEach items="${list}" var="nickname">
						<span class="cls-nickname">${nickname}</span>
					</c:forEach>
				</div>
			</div>
			<div class="row">
				<div class="col-3">
					<span>내용</span>
				</div>
				<div class="col-9">
					<textarea type="text" class="form-control" id="content" name="content" value=""></textarea>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<button type="submit" class="btn btn-danger" id="submitBtn">전송</button>
				</div>
			</div>
		</div>
	</form>
	
	<script>
		// 메세지 전송 버튼 클릭 시 이벤트 처리
		$("#writeForm").on("submit", function(){
			if($("#from_nickname").val() === "" || $("#to_nickname").val() === "" || $("#content").val() === ""){
				alert("빈 칸을 입력하세요.");
				return false;
			}
		})
	
		// 받는 이 선택
		$(".cls-nickname").on("click", function(e){
			let nickname = $(e.target).html();
			$("#to_nickname").val(nickname);
		})
	</script>
</body>
</html>