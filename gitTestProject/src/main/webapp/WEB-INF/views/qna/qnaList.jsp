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
<style>
	.tbl{
		margin: 10px auto;
		border: 1px solid #eeeeee;
	}
	.tbl>tr>td {
		
	}
	
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="page-title">문의게시판</div>
			<div class="navi">
				<a href="/faqList.do?reqPage=1&fFlag=1" class="btn bc200" >FAQ</a>
				<%if(m != null && m.getMemberNo() > 0) {%>
					<label><a href="/oneOnOneList.do?reqPage=1&reqPage1=1&memberNo=<%=m.getMemberNo() %>" class="btn bc200">1:1게시판</a></label>
					<label><a href="/oneOnOneWrite.do?reqPage=1&memberNo=<%=m.getMemberNo() %>" class="btn bc200">메일보내기</a></label>
				
				<%}else { %>
					<label><a href="/oneOnOneList.do" class="btn bc200">1:1게시판</a></label>
					<label><a href="/oneOnOneWrite.do" class="btn bc200">메일보내기</a></label>
				<%} %>
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
		
		<%if(m != null && m.getMemberNo() > 0) { %>
			<a class="btn bc200 writeBtn" href="/qnaWriteFrm.do?reqPage=1">글쓰기</a>			
		<%} %>
			
			<p style="display:none" id="pMemberNo"><%=list.get(0).getMemberNo() %></p>
			<div id="pageNavi"><%=pageNavi %></div>		
		</div>
		
		
	
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>
