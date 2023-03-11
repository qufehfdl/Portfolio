<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath }/" />
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

	<c:import url="/WEB-INF/views/include/top.jsp" />

	<form:form action="${root }board/modify_pro" method="post"
		modelAttribute="modifyBean" enctype="multipart/form-data">
		<form:hidden path="content_num" />
		<form:hidden path="content_board_num" />
		<input type="hidden" name="page" value="${page }">
		<div>
			<form:label path="content_writer_name">작성자</form:label>
			<form:input path="content_writer_name" readonly="true" />
		</div>
		<hr>
		<div>
			<form:label path="content_date">작성날짜</form:label>
			<form:input path="content_date" readonly="true" />
		</div>
		<hr>
		<div>
			<form:label path="content_subject">제목</form:label>
			<form:input path="content_subject" />
			<form:errors path="content_subject" style="color:red"></form:errors>
		</div>
		<hr>
		<div>
			<form:label path="content_text">내용</form:label>
			<form:textarea path="content_text" rows="30"	style="resize: none; width: 100%;" />
			<form:errors path="content_text" style="color:red"></form:errors>
		</div>
		<hr>
		<div>
			<label for="board_file">첨부 이미지</label>
			<c:if test="${modifyContentBean.content_file != null }">
				<img src="${root }upload/${modifyContentBean.content_file}"
					width="100%" />
				<!-- 유효성검사에 실패하면 다시 수정페이지로 돌아갔을 때 이미지가 없는 현상이 발생하므로 히든 추가 -->
				<form:hidden path="content_file" />
			</c:if>
			<form:input path="upload_file" type="file" accept="image/*" />
		</div>
		<hr>
		<div class="text-right">
			<form:button>수정 완료</form:button>
			<input type="button" onclick="location.href='${root }board/read?board_category=${board_category}&content_num=${content_num}&page=${page}'" value="취소">
		</div>
	</form:form>


</body>
</html>
