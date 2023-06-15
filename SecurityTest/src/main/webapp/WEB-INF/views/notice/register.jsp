<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice Register</title>
</head>
<body>
	<h2>NOTICE REGISTER : access to admin</h2>
	
	<p>principal : <sec:authentication property="principal"/></p>
	<p>member : <sec:authentication property="principal.member"/></p>
	<p>사용자이름 : <sec:authentication property="principal.member.userName"/></p>
	<p>사용자 아이디 : <sec:authentication property="principal.member.userId"/></p>
	<p>사용자 비밀번호 : <sec:authentication property="principal.member.userPw"/></p>
	<p>사용자 권한리스트 : <sec:authentication property="principal.member.authList"/></p>
	<hr/>
	<sec:authorize access="hasRole('ROLE_MEMBER')">
		역할명 : 일반회원 권한입니다
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		역할명 : 관리자 권한입니다
	</sec:authorize>
	
</body>
</html>