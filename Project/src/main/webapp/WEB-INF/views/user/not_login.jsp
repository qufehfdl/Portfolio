 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }/"></c:set>
<script >
alert("로그인 상태가 아니면 접근할 수 없어요😊")
location.href="${root}user/login"
</script>
