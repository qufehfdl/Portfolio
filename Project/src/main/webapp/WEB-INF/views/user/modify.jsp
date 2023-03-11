<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

	<c:import url="/WEB-INF/views/include/top.jsp" />

	<form:form action="${root }user/modify" method="post"
		modelAttribute="modifyUser">
		<div>
			<form:label path="user_name">이름</form:label>
			<form:input path="user_name" readonly="true" />
		</div>
		<div>
			<form:label path="user_id">아이디</form:label>
			<form:input path="user_id" readonly="true" />
		</div>
		<div>
			<form:label path="user_pw">비밀번호</form:label>
			<form:password path="user_pw" />
			<form:errors path="user_pw" style="color:red"></form:errors>
		</div>
		<div>
			<form:label path="user_pw2">비밀번호 확인</form:label>
			<form:password path="user_pw2" />
			<form:errors path="user_pw2" style="color:red"></form:errors>
		</div>
		<div>
			<form:button>정보수정</form:button>
		</div>
	</form:form>


</body>
</html>
