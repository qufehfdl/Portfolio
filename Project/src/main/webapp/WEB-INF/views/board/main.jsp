<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<html>
<head>
<style>
#htable {
	border-collapse: collapse;
	border: 3px solid black;
}
a{
text-decoration: none;

}
</style>
<meta charset="UTF-8">
</head>
<body>

	<c:import url="/WEB-INF/views/include/top.jsp" />
	<div align="right">
		<input type="button" onclick="location.href='${root }board/write'"
			value="글 쓰기">
	</div>
	<div align="center">
		<!-- 게시글 리스트 -->
		<table border="1" id="htable">
			<thead>
				<tr>
					<th width="50" align="center">글번호</th>
					<th width="700">제목</th>
					<th width="100" align="center">작성자</th>
					<th width="200" align="center">작성날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="obj" items="${contentList }">
					<tr height="40">
						<td align="center">${obj.content_num }</td>
						<td><a
							href='${root }board/read?board_category=${board_category}&content_num=${obj.content_num}&page=${page}'>&nbsp;
								${obj.content_subject }</a></td>
						<td align="center">${obj.content_writer_name }</td>
						<td align="center">${obj.content_date}</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="4" align="center">
						<!-- 이전 --> <c:choose>
							<c:when test="${pageBean.prevPage <=0 }">
							</c:when>
							<c:otherwise>
								<a
									href="${root }board/main?board_category=${board_category}&page=${pageBean.prevPage}">이전</a>
							</c:otherwise>
						</c:choose> <c:forEach var="idx" begin="${pageBean.min }"
							end="${pageBean.max }">
							<c:choose>
								<c:when test="${idx == pageBean.currentPage}">
									<a
										href="${root }board/main?board_category=${board_category}&page=${idx}">[${idx }]</a>
								</c:when>
								<c:otherwise>
									<a
										href="${root }board/main?board_category=${board_category}&page=${idx}">[${idx }]</a>
								</c:otherwise>
							</c:choose>
						</c:forEach> <!-- 다음 --> <c:choose>
							<c:when test="${pageBean.max >= pageBean.pageCnt }">
							</c:when>
							<c:otherwise>
								<a
									href="${root }board/main?board_category=${board_category}&page=${pageBean.nextPage}">다음</a>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

</body>
</html>






