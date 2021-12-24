<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<title>회원가입 페이지</title>
</head>
<body>
	<form id="signupForm" action="${pagecontext.request.contextPath}/signupProc.mem" method="post">
		<div class="container">
			<div class="row">
				<div class="col-9">
					<input type="text" id="id" name="id" class="form-control" placeholder="아이디를 입력하세요">
				</div>
				<div class="col-3">
					<button type="button" id="checkBtn" class="btn btn-warning">중복확인</button>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<input type="password" id="pw" name="pw" class="form-control" placeholder="비밀번호를 입력하세요">
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
					<button type="submit" class="btn btn-success" id="confirmBtn" disabled>제출</button>
				</div>
			</div>
			
		</div>
	</form>
	<script>
		// 아이디 중복확인
		document.getElementById("checkBtn").onclick = function(){
			let id = document.getElementById("id").value;
			$.ajax({
				url: "${pagecontext.request.contextPath}/checkIdProc.mem",
				type: "post",
				data: {id: id}
			}).done(function(rs){
				console.log(rs);
				if(rs === "unavailable"){
					alert("중복된 아이디입니다.");
					$("#confirmBtn").attr("disabled", true);
				} else {
					alert("사용 가능한 아이디입니다.");
					$("#confirmBtn").attr("disabled", false);
					
				}
			}).fail(function(e){
				console.log(e);
			})
		}
		
		// submit이 발생하는 대상을 기준으로 이벤트 처리
		document.getElementById("signupForm").onsubmit = function(){
			if(document.getElementById("id").value === ""
					|| document.getElementById("pw").value === ""
					|| document.getElementById("nickname").value === ""){
				alert("값을 제대로 입력하세요.");
				return false;
			}
		}
		
		// 취소 버튼
		document.getElementById("cancelBtn").onclick = function(){
			location.href = "${pagecontext.request.contextPath}/index.jsp";
		}
		
	</script>
</body>
</html>