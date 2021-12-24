<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<title>정보수정 페이지</title>
</head>
<body>
	<form id="modifyForm" action="${pagecontext.request.contextPath}/modifyProc.mem" method="post">
		<div class="container">
			<h2>정보수정</h2>
			<div class="row">
				<div class="col">
					<input type="text" id="id" name="id" class="form-control" value="${loginSession.id}" readonly/>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<input type="password" id="pw" name="pw" class="form-control" placeholder="비밀번호를 입력하세요">
				</div>
			</div>
			<div class="row">
				<div class="col">
					<input type="text" class="form-control" value="${loginSession.nickname}" readonly />
				</div>
			</div>
			<div class="row">
				<div class="col">
					<input type="text" id="nickname" name="nickname" class="form-control" placeholder="닉네임을 입력하세요">
				</div>
			</div>
			<div class="row">
				<div class="col-4">
					<button type="button" class="btn btn-secondary" id="cancelBtn">취소</button>
				</div>
				<div class="col-4">
					<button type="submit" class="btn btn-success" id="confirmBtn">제출</button>
				</div>
			</div>
			
		</div>
	</form>
	<script>
		$("#modifyForm").on("submit", function(){
			if($("#pw").val() === "" && $("#nickname").val() === ""){
				alert("수정할 비밀번호 혹은 닉네임을 입력하세요.");
				return false;
			}
		})
		
		$("#cancelBtn").on("click", function(){
			$(location).attr("href", "${pagecontext.request.contextPath}/member/myPage.jsp");
		})
	</script>
</body>
</html>