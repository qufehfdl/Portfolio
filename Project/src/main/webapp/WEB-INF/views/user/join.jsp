<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath }/"></c:set>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
</head>
<script>
	function checkUserIdExist() {
		var user_id = $("#user_id").val()
		
		//아이디를 입력 안해주면 유효성검사를 걸리기 때문에 체크코드
		if (user_id.length == 0) {
			alert('아이디를 입력해주세요😊')
			return
		}
		
// 		$.ajax({
// 		    url: "~", // 클라이언트가 요청을 보낼 서버의 URL 주소
// 		    type: "GET",                             // HTTP 요청 방식(GET, POST)
// 		    data: { name: "태화" },                // HTTP 요청과 함께 서버로 보낼 데이터
// 		    dataType: "json"                         // 서버에서 보내줄 데이터의 타입
// 		})

		$.ajax({
			url : '${root}user/checkUserIdExist/' + user_id,
			type : 'get',
			dataType : 'text',
			success : function(result) {
				if(result.trim() == 'true'){	//넘어오는 문자열값이 true 라면
					alert('사용 가능 아이디😊')
						$('#userIdExist').val('true')
				}else {
					alert('사용중인 아이디😊')
						$('#userIdExist').val('false')
				}
			}
		})
	}
	//사용자 아이디입력칸에 키보드를 누르면 무조건 false가 세팅되도록 : true가 되어야 중복확인 통과
	function resetUserIdExist() {
		$("#userIdExist").val('false')
	}
</script>
<body>

	<c:import url="/WEB-INF/views/include/top.jsp" />

	<div align="center">
		<form:form action="${root }user/join" method="post"
			modelAttribute="joinUser">
			<!-- 사용자가 중복확인을 눌렀는지 안눌렀는지 확인하기 위해 hidden으로 값 넘겨줌 -->
			<form:hidden path="userIdExist" />
			<table>
				<tr>
					<td><form:label path="user_name">이름</form:label> <form:input
							path="user_name" /> <form:errors path="user_name"
							style="color:red"></form:errors></td>
				</tr>
				<tr>
					<td><form:label path="user_id">아이디</form:label> <!-- 중복확인 했다 하더라도 새롭게 입력하면 다시 중복확인을 해야하므로 onkeypress사용  -->
						<form:input path="user_id" onkeypress="resetUserIdExist()" />
						<button type="button" onclick="checkUserIdExist()">중복확인</button> <form:errors
							path="user_id" style="color:red"></form:errors></td>
				</tr>
				<tr>
					<td><form:label path="user_pw">비밀번호</form:label> <form:password
							path="user_pw" /> <form:errors path="user_pw" style="color:red"></form:errors></td>
				</tr>
				<tr>
					<td><form:label path="user_pw2">비밀번호 확인</form:label> <form:password
							path="user_pw2" /> <form:errors path="user_pw2"
							style="color:red"></form:errors></td>
				</tr>
				<tr>
					<td><form:button>회원가입</form:button>
						<button type="reset">재작성</button></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>
