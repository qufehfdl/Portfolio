<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<style>
a {
	text-decoration: none;
}

#right_proc {
	font-size: 19
}

#board_name {
	font-size: 22
}

#baner {
	font-size: 35
}

#right_proc {
	font-size: 18;
	font-weight: bold;
}
</style>
<script>
	$(document).ready(function () {
		$('#search').click(function () {
			if ($('#search2').val().length == 0 ) {
				alert('글자를 입력해 주세요');
			}
		});
	});
</script>
<meta charset="UTF-8">
</head>
<body>
	<div>
		<form action="${root}board/searchList" method="post">
			<table style="width: 100%;">
				<tr>
					<td width="200"><a href="${root }" id="baner"><b>🔭태화의
								공부공부</b></a></td>
					<td><c:forEach var="obj" items="${topMenuList }">
							<a href="${root }board/main?board_category=${obj.board_category}"
								id="board_name"><b>🌼 ${obj.board_name }</b></a>&nbsp;&nbsp;
					</c:forEach></td>
					<td align="right"><c:choose>
							<c:when test="${loginUserBean.userLogin == true }">
								<a href="${root }user/modify" id="right_proc">🟨정보수정</a>&nbsp;&nbsp; 
					<a href="${root }user/logout" id="right_proc">🟩로그아웃</a>
							</c:when>
							<c:otherwise>
								<a href="${root }user/login" id="right_proc">🟦로그인</a>&nbsp;&nbsp; 
					<a href="${root }user/join" id="right_proc">🟪회원가입</a>
							</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<td align="center" colspan="3">❓<input type="text"
						placeholder="검색해주세용~" name="content_subject" id="search2">
						<button type="submit" id="search">검색</button></td>
				</tr>
			</table>
		</form>
	</div>
	<hr>


</body>
</html>