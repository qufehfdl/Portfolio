<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<script>
	$(document).ready(function() {
		$('#search').click(function() {
			if ($('#search2').val().length == 0) {
				alert('글자를 입력해 주세요');
			}
		});
	});
</script>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
	<meta name="viewport" content="width=device-width, initial-scale=1">
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
			<div class="navbar-collapse" id="navbarTogglerDemo03">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 flex-wrap">
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
</body>
</html>