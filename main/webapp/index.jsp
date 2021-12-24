<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="loginForm" action="${pagecontext.request.contextPath}/loginProc.mem" method="post">
		<div class="container">
			<div class="row">
				<div class="col">
					<input type="text" id="id" name="id" class="form-control">
				</div>
			</div>
			<div class="row">
				<div class="col">
					<input type="password" id="pw" name="pw" class="form-control">
				</div>
			</div>
			<div class="row">
				<div class="col-2">
					<button type="submit" id="loginBtn" class="btn btn-primary">로그인</button>
				</div>
				<div class="col-2">
					<button type="button" id="signupBtn" class="btn btn-warning">회원가입</button>
				</div>
			</div>
		</div>
	</form>
	<script>
		let id = document.getElementById("id");
		let pw = document.getElementById("pw");
		
		// 로그인 버튼
		document.getElementById("loginBtn").onclick = function(){
			console.log("로그인버튼 클릭");
			if(id.value === "" || pw.value === "") {
				alert("아이디와 비밀번호를 입력해주세요.");
				return;
			}
		}
	
		// 회원가입 버튼
		document.getElementById("signupBtn").onclick = function(){
			location.href = "${pagecontext.request.contextPath}/toSignup.mem";
		}
	</script>
</body>
</html>