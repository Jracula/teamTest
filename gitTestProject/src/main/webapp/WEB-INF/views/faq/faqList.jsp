<%@page import="com.litbooks.faq.model.vo.Faq"%>
<%@page import="com.litbooks.qna.model.vo.Qna"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<Faq> list = (ArrayList<Faq>)request.getAttribute("list");
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
		<div class="page-title">FAQ</div>
		<%--
		<%if(m !=null && m.getMemberLevel() == 1) {%>
		<a class="btn bc2 writeBtn" href="/faqWriteFrm.do">글쓰기</a>
		<%} %>
		
		 --%>

		<a href="/qnaList.do?reqPage=1" class="btn bc1" >문의게시판</a>
		<label><a href="#" class="btn bc1">1:1게시판</a></label>
		<label><a href="#" class="btn bc1">메일보내기</a></label>
		
		<table class="tbl tbl-hover notice-tbl">
			<tr class="tr-2">
				<th style="width:10%">번호</th>
				<th style="width:55%">제목</th>
				<th style="width:10%">작성자</th>
				<th style="width:10%">작성일</th>
				<th style="width:10%">조회수</th>
			</tr>
			<%for(int i=0; i<list.size(); i++) {%>
				<%Faq f = list.get(i); %>
			<tr class="tr-1">
				<td><%=i+start %></td>
				<td>
					<a href="/FaqView.do?faqNo=<%=f.getfNo() %>">
						<%=f.getfTitle() %>
					</a>
				</td>
				<td><%=f.getfWriter() %></td>
				<td><%=f.getfRegDate() %></td>
				<td><%=f.getfReadCount() %></td>
			</tr>
			<%} %>
		</table>
		<a class="btn bc2 writeBtn" href="/faqWriteFrm.do">글쓰기</a>
		<div id="pageNavi"><%=pageNavi %></div>		


	</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>