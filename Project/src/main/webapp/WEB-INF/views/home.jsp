<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	function yesterDay() {
		$.ajax({
			url : '${root}weather/yesterDay',
			type : 'get',
			dataType : 'json',
			success : function(result) {
				$("#public_api_1").text(
						result.response.body.items.item[0].stnNm + '의 최저기온은 '
								+ result.response.body.items.item[0].minTa
								+ '도 최고 기온은 '
								+ result.response.body.items.item[0].maxTa
								+ '도 평균기온은 '
								+ result.response.body.items.item[0].avgTa
								+ ' 입니다~');
			}
		})
	}
	function foreCast() {
		$.ajax({
			url : '${root}weather/foreCast',
			type : 'get',
			dataType : 'json',
			success : function(result) {
				$("#public_api_2").text(
						'🌤️ 4일 후 오전 하늘은'
								+ result.response.body.items.item[0].wf4Am
								+ ' ☔ 4일 후 오전 강수 확률'
								+ result.response.body.items.item[0].rnSt4Am
								+ '% 입니다' + '🌤️ 4일 후 오후 하늘은 '
								+ result.response.body.items.item[0].wf4Pm
								+ ' ☔ 4일 후 오후 강수 확률 '
								+ result.response.body.items.item[0].rnSt4Pm
								+ '% 입니다');

				$("#public_api_3").text(
						'🌤️ 5일 후 오전 하늘은 '
								+ result.response.body.items.item[0].wf5Am
								+ ' ☔ 5일 후 오전 강수 확률 '
								+ result.response.body.items.item[0].rnSt5Am
								+ '% 입니다' + '🌤️ 5일 후 오후 하늘은 '
								+ result.response.body.items.item[0].wf5Pm
								+ ' ☔ 5일 후 오후 강수 확률 '
								+ result.response.body.items.item[0].rnSt5Pm
								+ '% 입니다');

				$("#public_api_4").text(
						'🌤️ 6일 후 오전 하늘은 '
								+ result.response.body.items.item[0].wf6Am
								+ ' ☔ 6일 후 오전 강수 확률 '
								+ result.response.body.items.item[0].rnSt6Am
								+ '% 입니다' + '🌤️ 6일 후 오후 하늘은 '
								+ result.response.body.items.item[0].wf6Pm
								+ ' ☔ 6일 후 오후 강수 확률 '
								+ result.response.body.items.item[0].rnSt6Pm
								+ '% 입니다');
			}
		})
	}
	function myEvent() {
		$.ajax({
			url : '${root}synchronized',
			type : 'get',
			dataType : 'text',
			success : function(result) {
				alert('회원님의 이벤트 번호는 : ' + result);
			}
		})
	}

	function myThreadLocal(content) {
		var content = $("#content").val();
		$.ajax({
			url : '${root}myThreadLocal/' + content,
			type : 'get',
			dataType : 'text',
			success : function(result) {
				alert(result)
			}
		})
	}

	function myAtomic(str) {
		var str = $("#str").val();
		$.ajax({
			url : '${root}myAtomic/' + str,
			type : 'get',
			dataType : 'text',
			success : function(result) {
				alert(result)
			}
		})
	}
	
	function myConcurrent() {
		var str = $("#concurrent").val();
		var myid = $("#id").val;
		$.ajax({
			url : '${root}myConcurrent/' + str,
			type : 'get',
			dataType : 'text',
			success : function(result) {
				alert(result)
			}
		})
	}
</script>
<body>
	<c:import url="/WEB-INF/views/include/top.jsp"></c:import>
	🐣 세션시간 1분 [현재 꺼둠]
	<br>
	<br> 🧐 추가/공부할 기능
	<ul>
		<li>대규모 트래픽 처리에 대해서 공부하고 적용해보자
		<li>멀티쓰레드 공부하고 적용해보자
	</ul>
	<hr>
	🕐어제 🌤️날씨☔️
	<br>
	<br>
	<div id="public_api_1"></div>
	<hr>
	🌈기상예보🌈
	<br>
	<br>
	<div id="public_api_2"></div>
	<br>
	<div id="public_api_3"></div>
	<br>
	<div id="public_api_4"></div>
	<br>
	<hr>

	<input type="button" id='weather' value="어제 날씨" onclick="yesterDay()">
	<input type="button" id='weather' value="기상 예보" onclick="foreCast()">
	<hr>
	
	🌀 동시성 공부
	<ul>
		<li>ThreadLocal 사용 <input type="text" id="content"> <input
			type="button" onclick="myThreadLocal('content')" value="ThreadLocal"><br>
			<br>
			
			
		<li>ConcurrentHashMap 사용 
		
		<input type="text" id="concurrent">
		<c:set var="id" value="aaa"></c:set>
		 <input	type="button" onclick="myConcurrent()" value=ConcurrentHashMap>
		 
			
			
		<li>Atomic Class 사용 <input type="text" id="str"> <input
			type="button" onclick="myAtomic('str')" value="Atomic"><br>
			<br>
		<li>synchronized 키워드 사용 <input type="button" onclick="myEvent()"
			value="동시성 문제 이벤트"><br><br>
	</ul>

</body>
</html>
