<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<h2>register</h2>
	<form:form modelAttribute="board" method="post" action="/crud/board/register">
		<c:if test="${status eq 'u' }">
			<input type="hidden" name="boardNo" value="${board.boardNo }"/>
		</c:if>
		<table>
			<tr>
				<td>제목</td>
				<td>
					<input type="text" id="title" name="title" value="${board.title }"> 
				</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>
					<input type="text" id="writer" name="writer" value="${board.writer }"> 
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea rows="10" cols="30" name="content" id="content">${board.content }</textarea>
				</td>
			</tr>
		</table>
		<div>
			<c:set value="등록" var="btnText"/>
			<c:if test="${status eq 'u' }">
				<c:set value="수정" var="btnText"/>
			</c:if>	
			<input type="button" id="btnRegister" value="${btnText }">
			<input type="button" id="btnList" value="목록">
		</div>
	</form:form>
</body>
<script type="text/javascript">
$(function(){
	var board = $("#board")
	var btnRegister = $("#btnRegister")
	var btnList = $("#btnList")
	
	// 등록버튼을 클릭 시 이벤트 
	btnRegister.on("click", function(){
		var title = $("#title").val()		// 제목을 가져온다 
		var content = $("#content").val()	// 내용을 가져온다 
		var writer = $("#writer").val()		// 작성자를 가져온다 
		
		if(title == null || title == ""){
			alert("제목을 입력하세요");
			return false;
		}
		
		if(content == null || content == ""){
			alert("내용을 입력하세요");
			return false;
		}
		
		if(writer == null || writer == ""){
			alert("작성자를 입력하세요");
			return false;
		}
		
		if($(this).val() == "수정"){
			board.attr("action", "/crud/board/modify");
		}
		
		board.submit();		// submit이벤트를 날려 서버로 데이터를 전송 
	})
	
	
	// 목록 버튼 클릭 시 이벤트
	btnList.on("click", function(){
		location.href="/crud/board/list"
	})
	
})
</script>
</html>