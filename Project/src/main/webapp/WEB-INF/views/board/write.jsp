<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var='root' value='${pageContext.request.contextPath}/' />
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

	<c:import url="/WEB-INF/views/include/top.jsp" />

	<div>
		<!-- 파일 업로드 -->
		<form:form action="${root }board/write_pro" method="post"
			modelAttribute="writeBean" enctype="multipart/form-data">
			
			<!-- 현재 이 게시글이 어떤게시판의 글인지 히든으로 게시판 번호를 넘겨줌 -->
			<%-- <form:hidden path="content_board_num" /> --%>

			<!-- 히든으로 넘기지 않고 글 작성시 게시판 카테고리를 고를 수 있게 변경 -->	
			<div>
				<form:select path="content_board_num">
					<form:option value="1">JAVA 게시판</form:option>
					<form:option value="2">JSP 게시판</form:option>
					<form:option value="3">SPRING 게시판</form:option>
					<form:option value="4">여러가지 공부 게시판</form:option>
					<form:option value="5">방명록 게시판</form:option>
					<form:option value="6">취미 게시판</form:option>
				</form:select>

			</div>
			<div>
				<form:label path="content_subject">제목</form:label>
				<form:input path="content_subject" />
				<form:errors path="content_subject" style="color:red"></form:errors>
			</div>
			<hr>
			<div>
				<form:label path="content_text">내용</form:label>
				<form:textarea path="content_text" rows="30"
					cssStyle="width:100%;resize:none;" />
				<form:errors path="content_text" style="color:red"></form:errors>
			</div>
			<hr>
			<div>
				<form:label path="upload_file">첨부이미지</form:label>
				<form:input type="file" path="upload_file" accept="image/*" />
			</div>
			<hr>
			<div>
				<div>
					<form:button>작성하기</form:button>
				</div>
			</div>
		</form:form>
	</div>


</body>
</html>
