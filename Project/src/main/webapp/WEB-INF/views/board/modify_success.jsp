<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }/"></c:set>


<script>
	alert("수정 완료😊")
	location.href = "${root}board/read?board_category=${modifyBean.content_board_num}&content_num=${modifyBean.content_num}&page=${page}"
</script>
