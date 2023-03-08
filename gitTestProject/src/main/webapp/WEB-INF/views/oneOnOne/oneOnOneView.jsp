<%@page import="com.litbooks.ooo.vo.OneOnOne"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
  		OneOnOne o = (OneOnOne)request.getAttribute("o");
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
		<div class="page-title">공지사항</div>
		<table class="tbl" id="oneOnOneView">
			<tr class="tr-3">
				<th colspan="6"><%=o.getoTitle() %>
			</tr>
			<tr class="tr-1">
				<th class="td-3">작성자</th>
				<td><%=o.getoWriter() %>
				<th class="td-3">작성일</th>
				<td><%=o.getoRegDate() %>
				<th class="td-3">조회수</th>
				<td><%=o.getoReadCount() %>
			</tr>
			<tr class="tr-1">
				<th class="td-3">첨부파일</th>
				<td colspan="5">
				<%if(o.getFileName() != null) {%>
				<img src="/img/file.png" width="16px">
				<a href="/noticeFileDown.do?noticeNo=<%=o.getoNo() %>"><%=o.getFileName() %></a>
				<%} %>
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<div id="noticeContent">
						<%=o.getoContentBr() %>
					</div>
				</td>
			</tr>
			<tr>
				<th></th>
			</tr>
			<!-- if(m.getMemberId().equals(n.getNoticeWriter()))
			 조건을 이렇게만 주면 로그인을 하지 않았을 경우 에러발생 로그인조건을 걸어줘야한다. -->
			<%if( m!=null && m.getMemberId().equals(o.getoWriter()) ) {%>
			<tr>
				<th colspan="6">
					<a class="btn bc44" href="/oneOnOneUpdateFrm.do?oNo=<%=o.getoNo() %>">수정</a>
					<button class="btn bc44" onclick="noticeDelete(<%=o.getoNo()%>);">삭제</button>
				</th>
			</tr>
			<%} %>
		</table>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>