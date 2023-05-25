<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원정보</h2>
	<table border="1">
		<tr>
			<td>userId</td>
			<td>${member.userId }</td>
		</tr>
		<tr>
			<td>${member.password }</td>
			<td></td>
		</tr>
		<tr>
			<td>userName</td>
			<td>${member.userName }</td>
		</tr>
		<tr>
			<td>E-Mail</td>
			<td>${member.email }</td>
		</tr>
		<tr>
			<td>dateOfBirth</td>
			<td>${member.dateOfBirth }</td>
		</tr>
		<tr>
			<td>gender</td>
			<td>${member.gender }</td>
		</tr>
		<tr>
			<td>개발자여부</td>
			<td>
				<c:if test="${member.developer == 'Y'}">
					<c:set value="개발자"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td>외국인여부</td>
			<td>
				<c:if test="${member.foreigner == true}">
					<c:set value="외국인"/>
				</c:if>
				
			</td>
		</tr>	
		<tr>
			<td>국적</td>
			<td>
				<c:forEach items="${member.nationality }" var="nationality">
					<c:if test="">
						<c:set value=""/>
					</c:if>			
				</c:forEach>				
			</td>
		</tr>	
		<tr>
			<td>소유차량</td>
			<td>
				
			</td>
		</tr>	
		<tr>
			<td>취미</td>
			<td></td>
		</tr>
		<tr>
			<td>취미</td>
			<td>
				<c:forEach items="">
					<c:if test="${member.hobby} ">
						<c:set>${member.hobby}</c:set>
					</c:if>				
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td>우편번호</td>
			<td>${address.postCode }</td>
		</tr>
		<tr>
			<td>주소</td>
			<td>${address.location }</td>
		</tr>
		<tr>
			<td>카드1 - 번호</td>
			<td></td>
		</tr>
		<tr>
			<td>카드1 - 유효년월</td>
			<td></td>
		</tr>
		<tr>
			<td>카드2 - 번호</td>
			<td></td>
		</tr>
		<tr>
			<td>카드2 - 유효년월</td>
			<td></td>
		</tr>
		<tr>
			<td>소개</td>
			<td>
				${introduction }
			</td>
		</tr>	
	</table>

</body>
</html>