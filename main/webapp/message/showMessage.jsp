<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<title>메세지 확인</title>
</head>
<body>
	<table class="table">
		<thead>
			<tr>
				<th colspan="5">
					<button type="button" id="deleteBtn" class="btn btn-danger">삭제</button>
					<button type="button" id="backBtn" class="btn btn-secondary">뒤로가기</button>
				</th>
			</tr>
			<tr>
				<th scope="col">선택</th>
				<th scope="col">받는 사람</th>
				<th scope="col">보내는 사람</th>
				<th scope="col">내용</th>
				<th scope="col">날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="message">
				<tr>
					<td><input type="checkbox" name="seq_msg" class="cls-checkBox" value="${message.seq_msg}"></td>
					<td>${message.to_nickname}</td>
					<td>${message.from_nickname}</td>
					<td>${message.content}</td>
					<td>${message.written_date}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<form action="${pagecontext.request.contextPath}/toSearchMsg.msg" method="post" id="searchForm">
		<div class="container">
			<div class="row">
				<div class="col">
					<p>검색옵션</p>
				</div>
			</div>
			<div class="row">
				<div class="col-2">
					<input type="checkbox" name="searchOpt" class="searchOpt" value="total"> 전체
				</div>
				<div class="col-10">
	                <input type="text" id="total" class="searchInput" name="total" disabled />
	            </div>
				<div class="col-2">
					<input type="checkbox" name="searchOpt" class="searchOpt" value="from_nickname"> 보내는 사람
				</div>
	            <div class="col-10">
	                <input type="text" id="from_nickname" class="searchInput" name="from_nickname" disabled />
	            </div>
				<div class="col-2">
					<input type="checkbox" name="searchOpt" class="searchOpt" value="content"> 내용
				</div>
	            <div class="col-10">
	                <input type="text" id="content" class="searchInput" name="content" disabled/>
	            </div>
				<div class="col-2">
					<input type="checkbox" name="searchOpt" class="searchOpt" value="written_date"> 날짜
				</div>
	            <div class="col-10">
	                <input type="text" id="written_date" class="searchInput" name="written_date" disabled/>
	            </div>
			</div>
			<div class="row">
				<div class="col">
					<button type="submit" id="searchBtn" class="btn btn-primary">검색</button>
				</div>
			</div>
		</div>
	</form>
	
	<script>
		// 검색 버튼
		$("#searchForm").on("submit", function(){
			
	        if($(".searchOpt").is(":checked") === false || $(".searchInput").is()){
	        	return false;
	        }
	        
	    })
	    
		// 검색옵션 - 전체선택 시 나머지 체크해제 및 선택불가
	    $(".searchOpt").on("click", function(){
            if($(this).val() === "total"){
                if($(this).prop("checked") === true){
                    ($(".searchOpt").not($(this).val("total"))).prop("checked", false);
                    ($(".searchOpt").not($(this).val("total"))).attr("disabled", true);
                    $(".searchInput").val("");
                    $(".searchInput").attr("disabled", true);
					$("#total").attr("disabled", false);
                } else {
                    ($(".searchOpt").not($(this).val("total"))).attr("disabled", false);
					$("#total").val("");
					$("#total").attr("disabled", true);
                }
            } else {
                // 체크박스에 해당하는 입력창 활성화
                let temp = $(this).val();
                if($(this).prop("checked") === true){
                    $("#"+temp).attr("disabled", false);
                } else {
                    $("#"+temp).val("");
                    $("#"+temp).attr("disabled", true);
                }
            }
        })
	
		// 삭제버튼
		$("#deleteBtn").on("click", function(){
			// 체크박스가 선택이 되어있을 때에만 시퀀스 번호 서버 전송
			if($(".cls-checkBox:checked").length !== 0){
				// 동적으로 <form>생성하고 속성 추가
				let deleteForm = $("<form id='deleteForm' class='d-none'></From>");
				deleteForm.attr("method", "post");
				deleteForm.attr("action", "${pagecontext.request.contextPath}/deleteProc.msg");
				deleteForm.append($(".cls-checkBox"));
				deleteForm.appendTo("body");
				console.log(deleteForm);
				deleteForm.submit();
			}
		})
		
		// 뒤로가기 버튼
		$("#backBtn").on("click", function(){
			$(location).attr("href", "${pagecontext.requeset.contextPath}/member/myPage.jsp");
		})
	</script>
</body>
</html>