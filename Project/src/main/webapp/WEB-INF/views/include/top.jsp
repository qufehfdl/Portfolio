<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<style>

</style>
<script>
	$(document).ready(function() {
		$('#search').click(function() {
			if ($('#search2').val().length == 0) {
				alert('글자를 입력해 주세요');
			}
		});
	});
</script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary"
		style="background-color: darkslateblue; border-radius: 5px">
		<div class="container-fluid">
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03"
				aria-controls="navbarTogglerDemo03" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<a class="navbar-brand" href="${root }" style="color: powderblue">🔭태화의
				블로그</a>
			<div class="collapse navbar-collapse" id="navbarTogglerDemo03">

				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<c:forEach var="obj" items="${topMenuList }">
						<li class="nav-item"><a
							href="${root }board/main?board_category=${obj.board_category}"
							id="board_name" class="nav-link" style="color: white"><i
								class="bi bi-layers-half"></i> ${obj.board_name }</a></li>
					</c:forEach>
				</ul>
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<c:choose>
						<c:when test="${loginUserBean.userLogin == true }">
							<li class="nav-item"><a href="${root }user/modify"
								id="right_proc" class="nav-link" style="color: white"> 정보수정</a></li>
							<li class="nav-item"><a href="${root }user/logout"
								id="right_proc" class="nav-link" style="color: white"> 로그아웃</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a href="${root }user/login"
								id="right_proc" class="nav-link" style="color: white"> 로그인</a></li>
							<li class="nav-item"><a href="${root }user/join"
								id="right_proc" class="nav-link" style="color: white"> 회원가입</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
				<form class="d-flex" role="search" action="${root}board/searchList" method="post">
					<input class="form-control me-2" type="text" placeholder="Search"
						aria-label="Search" name="content_subject" id="search2">
					<button class="btn btn-outline-success" type="submit"
						style="color: white" id="search">Search</button>
				</form>
			</div>
		</div>
	</nav>
	<%-- 	<div>
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
					<td align="center" colspan="3">❓</td>
				</tr>
			</table>
		</form>
	</div>
	<hr> --%>
</body>
</html>