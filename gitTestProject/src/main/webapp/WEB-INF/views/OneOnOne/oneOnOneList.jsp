<%@page import="com.litbooks.ooo.vo.OneOnOne"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	ArrayList<OneOnOne> list = (ArrayList<OneOnOne>)request.getAttribute("list");
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
		<div class="page-title">1:1 문의는 여기서</div>
		<%if(m!=null && m.getMemberLevel() == 1) {%>
		<a class="btn bc2 writeBtn" href="/oneOnOneWriteFrm.do">글쓰기</a>
		<%} %>
		
		<table class="tbl tbl-hover notice-tbl">
			<tr class="tr-2">
				<th style="width:10%">번호</th>
				<th style="width:55%">제목</th>
				<th style="width:10%">작성자</th>
				<th style="width:10%">작성일</th>
				<th style="width:10%">조회수</th>
			</tr>
			<%for(int i=0; i<list.size(); i++) {%>
				<%OneOnOne o = list.get(i); %>
			<tr class="tr-1">
				<td><%=i+start %></td>
				<td>
					<a href="/oneOnOneView.do?oNo=<%=o.getoNo() %>">
						<%=o.getoTitle() %>
					</a>
				</td>
				<td><%=o.getoWriter() %></td>
				<td><%=o.getoRegDate() %></td>
				<td><%=o.getoReadCount() %></td>
			</tr>
			<%} %>
		</table>
		<div id="pageNavi"><%=pageNavi %></div>
	</div>
	
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>