<%@page import="com.litbooks.qna.model.vo.Qna"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<Qna> list = (ArrayList<Qna>)request.getAttribute("list");
    	String pageNavi = (String)request.getAttribute("pageNavi");
    	int start = (int)request.getAttribute("start");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="page-title">문의게시판</div>
			<div class="navi">
				<a href="/faqList.do?reqPage=1" class="btn bc1" >FAQ</a>
				<label><a href="/oneOnOneList.do?reqPage=1" class="btn bc1">1:1게시판</a></label>
				<label><a href="#" class="btn bc1">메일보내기</a></label>
			</div>
		
		<table class="tbl tbl-hover board-tbl">
			<tr class="tr-2">
				<th style = "width:5%">번호</th>
				<th style = "width:5%">아이디</th>
				<th style = "width:20%">문의사항</th>
				<th style = "width:5%">작성일</th>
				<th style = "width:5%">조회수</th>
			</tr>
			<%
			for(Qna q : list) {
			%>
		
			<tr class="tr-1">
				<td><%=q.getqNo() %></td>
				<td><%=q.getqWriter() %></td>
				<td>
					<a href="/qnaView.do?qnaNo=<%=q.getqNo() %>">
						<%=q.getqTitle() %>
					</a>
				</td>
				<td><%=q.getqRegDate() %></td>
				<td><%=q.getqReadCount() %></td>
			</tr>
				<%} %>
		</table>
		<a class="btn bc2 writeBtn" href="/qnaWriteFrm.do">글쓰기</a>	
		<div id="pageNavi"><%=pageNavi %></div>		
		</div>
		
		
	
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>
