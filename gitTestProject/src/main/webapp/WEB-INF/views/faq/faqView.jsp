<%@page import="com.litbooks.faq.model.vo.Faq"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Faq f = (Faq)request.getAttribute("f");  
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
		<div class="page-content">
			<div class="page-title">FAQ</div>
			<table class="tbl" id="noticeView">
			<tr class="tr-3">
				<th colspan="6"><%=f.getfTitle() %>
			</tr>
			<tr class="tr-1">
				<th class="td-3">작성자</th>
				<td><%=f.getfWriter() %>
				<th class="td-3">작성일</th>
				<td><%=f.getfRegDate() %>
				<th class="td-3">조회수</th>
				<td><%=f.getfReadCount() %>
			</tr>
			<tr class="tr-1">
				<th class="td-3">첨부파일</th>
				<td colspan="5">
				<%if(f.getFilename() != null) {%>
				<img src="/img/file.png" width="16px">
				<a href="/noticeFileDown.do?noticeNo=<%=f.getfNo() %>"><%=f.getFilename() %></a>
				<%} %>
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<div id="noticeContent">
						<%=f.getfContentBr() %>
					</div>
				</td>
			</tr>
		</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>