<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>Spring Form</h4>
	<p>1) form:form 태그를 이용한 form을 생성해보자 </p>
	<form:form modelAttribute="member" method="post" action="/formtag/register"> 
		<!-- modelAttribute는 해당 데이터들만 가용하겠다! / 자바빈즈 클래스 객체 별칭처럼 사용 / 서버에서 이 자바빈즈 무조건 사용해야함-->
		<!-- modelAttribute="member"라는 자바 빈즈 키가 컨트롤러의 메소드와 연결되어있어야함 !!!!  -->
		<table border="1">
			<tr>
				<td>유저ID</td>
				<td>
					<form:input path="userId"/>
					<font color="red">
						<form:errors path="userId"/>
					</font>
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>
					<form:input path="userName"/>
					<font color="red">
						<form:errors path="userName"/>
					</font>
				</td>
			</tr>
		</table>
		<form:button name="register">등록</form:button>
	</form:form>
</body>
</html>