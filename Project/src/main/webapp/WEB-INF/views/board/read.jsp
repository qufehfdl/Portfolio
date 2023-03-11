<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var='root' value='${pageContext.request.contextPath }/' />
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<meta charset="UTF-8">
</head>

<script>
/*  매개변수로 답글 순서와 실제 답글 번호를 받아오고 */ 
	function modifyReply(index , reply_num) {
/* id선택자가 id순서인 댓글의 값을 받아 와서 replyModify(수정할 댓글 내용)에 저장*/ */
		var replyModify = $("#"+index).val();
		$.ajax({
			url : '${root}reply/modify/' + replyModify+"/"+reply_num,
			type : 'get',
			dataType : 'json',
			success : function(result) {
				alert("답글 수정을 완료했습니다😊")
			}
		})
	}
</script>
<body>

	<c:import url="/WEB-INF/views/include/top.jsp" />

	<div align="right">
		<a href="${root }board/main?board_category=${board_category}&page=${page}">📄 목록보기</a>
		<c:if	test="${loginUserBean.user_num == readContentBean.content_writer_num }">
			<a href="${root }board/modify?board_category=${board_category}&content_num=${content_num}&page=${page}">✏️ 수정하기</a>
			<a href="${root }board/delete?board_category=${board_category}&content_num=${content_num}">🌀 삭제하기</a>
		</c:if>
	</div>
	<div>
		<label for="board_writer_name">작성자</label>
		 <input type="text"	id="board_writer_name" name="board_writer_name"	value="${readContentBean.content_writer_name }" disabled="disabled" />
	</div>
	<div>
		<label for="board_date">작성날짜</label>
		 <input type="text"	id="board_date" name="board_date"	value="${readContentBean.content_date }" disabled="disabled" />
	</div>
	<div>
		<label for="board_subject">제목</label> 
		 <input type="text"	id="board_subject" name="board_subject"	value="${readContentBean.content_subject }" disabled="disabled" />
	</div>
	<div>
		<label for="board_content">내용</label><br>
		<textarea id="board_content" name="board_content" rows="30"	style="resize: none; width: 100%;" disabled="disabled">${readContentBean.content_text }</textarea>
	</div>
	<hr>
	<form:form action="${root }reply/write" modelAttribute="replyBean" method="post">
		<c:if test="${board_category == 5 || board_category == 6}">
			<form:hidden path="board_category" />
			<form:hidden path="content_num" />
			<br>
			😊😊😊 답글 😊😊😊 : 답글 작성은 동기 처리
			<br>
			<form:label path="reply"></form:label>
			<form:input path="reply" />
			<form:button>답글달기</form:button>
			<br>
			<br>
			<hr>
			<br>
			😊😊😊 답글 목록 😊😊😊 : 답글 수정은 비동기처리
			<br>
			<c:forEach items="${replyList}" var="list" varStatus="status">
				<c:if test="${list.getUser_num() == loginUserBean.user_num }">
						${list.getUser_name() }	🌠 
						<input type="text" id='modifyReply${status.index }'	value="${list.getReply() }">
						<!-- onclick이벤트로 자바스크립트에 매개변수에다 여러 데이터값을 넘기는 방법 -->
					<button type="button"	onclick="modifyReply('modifyReply${status.index }',${list.getReply_num() })">답글 수정</button>
					<br> 
				</c:if>
				<c:if test="${list.getUser_num() != loginUserBean.user_num }">
						${list.getUser_name() }	🌠 <span>${list.getReply() }</span>
					<br>
				</c:if>
			</c:forEach>
			<br>
			<hr>
		</c:if>
	</form:form>
	<c:if test="${readContentBean.content_file != null }">
		<div>
			<label for="board_file">첨부 이미지</label> <br>
			 <img	src="${root }upload/${readContentBean.content_file}" width="50%" height="200" />
		</div>
	</c:if>
</body>
</html>
