<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>마이페이지</title>
</head>
<body>
	<!-- 로그아웃, 쪽지쓰기, 쪽지보기 -->
	<div class="container">
		<div class="row">
			<div class="col-3">
				<button type="button" id="logoutBtn" class="btn btn-dark">로그아웃</button>
			</div>
			<div class="col-3">
				<button type="button" id="modifyBtn" class="btn btn-secondary">정보수정</button>
			</div>
			<div class="col-3">
				<button type="button" id="writeBtn" class="btn btn-warning">쪽지쓰기</button>
			</div>
			<div class="col-3">
				<button type="button" id="showBtn" class="btn btn-success">쪽지보기</button>
			</div>
		</div>
	</div>
	<script>
		document.getElementById("modifyBtn").onclick = function(){
			location.href = "${pagecontext.request.contextPath}/toModify.mem";
		}
	
		// 쪽지 보기 페이지 요청
		document.getElementById("showBtn").onclick = function(){
			location.href = "${pagecontext.request.contextPath}/getMessageProc.msg";
		}
	
	
		// 쪽지 쓰기 페이지 요청
		document.getElementById("writeBtn").onclick = function(){
			location.href = "${pageContext.request.contextPath}/toWrite.msg";
		}
		
		// 로그아웃
		document.getElementById("logoutBtn").onclick = function(){
			location.href = "${pageContext.request.contextPath}/logoutProc.mem";
		}
	
	</script>
</body>
</html>