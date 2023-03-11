<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath }/"></c:set>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
 
	<c:import url="/WEB-INF/views/include/top.jsp" />

	<div align="center">
		<form:form action="${root }user/login" method="post"
			modelAttribute="loginUser">
			<table>
				<tr>
					<td><form:label path="user_id">아이디</form:label> <form:input
							path="user_id" /></td>
				</tr>
				<tr>
					<td><form:label path="user_pw">비밀번호</form:label> <form:password
							path="user_pw" /></td>
				</tr>
				<tr>
					<td><form:label path="cook">아이디 저장</form:label> <form:checkbox
							path="cook" /></td>
				</tr>
				<tr>
					<td><form:button>로그인</form:button> <input type="button"
						onclick="location.href='${root}user/join'" value="회원가입"></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>