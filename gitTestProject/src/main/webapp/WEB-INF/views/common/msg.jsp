<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    String title = (String)request.getAttribute("title");
    String msg = (String)request.getAttribute("msg");
    String icon = (String)request.getAttribute("icon");
    String loc = (String)request.getAttribute("loc");
    %><!-- 값 추출 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/js/sweetalert.min.js"></script>
</head>
<body>
	<div style="display:none;">
		<div id="title"><%=title %></div>
		<div id="msg"><%=msg %></div>
		<div id="icon"><%=icon %></div>
		<div id="loc"><%=loc %></div>
	</div>
	<script>
		const title = document.querySelector("#title").innerText;
		const msg = document.querySelector("#msg").innerText;
		const icon = document.querySelector("#icon").innerText;
		const loc = document.querySelector("#loc").innerText;
		
		// sweetalert
		swal({
			// 모달창 내부 컨텐츠
			title : title,
			text : msg,
			icon : icon
		}).then(function(){
			// 페이지 이동
			location.href = loc;			
		});
	</script>
</body>
</html>