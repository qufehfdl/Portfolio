<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }/"></c:set>


<script>
alert("저장 완료😊")
location.href="${root}board/read?board_category=${writeBean.content_board_num}&content_num=${writeBean.content_num}"
</script>
 