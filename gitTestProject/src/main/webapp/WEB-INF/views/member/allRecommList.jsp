<%@page import="com.litbooks.book.vo.Recomm"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("list"); 
    	String pageNavi = (String)request.getAttribute("pageNavi");
        int start = (int)request.getAttribute("start");
        ArrayList<Recomm> recommlist = (ArrayList<Recomm>)request.getAttribute("recommlist"); 
    %>
    
<!DOCTYPE html>
<html>

<style>
.allMember-title{
	text-align : center;
	margin : 40px;
}
	.tbl tr{
		border-bottom: 1px solid #ccc;
	}
#pageNavi{
	margin : 60px;
}

</style>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<div class="page-content">
	<div class="allMember-title"><h2>후기(평점)조회</h2></div>
		<table class="tbl tbl-hover">
			<tr class="tr-3">
				<th>No </th>
				<th>댓글번호</th>
				<th>평점</th>
				<th>아이디</th>
				<th>댓글내용</th>
				<th>댓글/대댓글</th>
				<th>작성일</th>
				<th>게시판번호</th>
			</tr>
			<%for(int i=0;i<recommlist.size();i++) {%>
				<%Recomm rc = recommlist.get(i); %>
				<tr class="tr-1">
					<td><%=i+start %></td>
					<td><%=rc.getRecommNo() %></td>
					<%if(rc.getRating() > 0) {%>
						<td><%=rc.getRating()%></td>
					<% }%>
					<%if(rc.getRating() == 0) {%>
						<td>평점 없음</td>
					<% }%>
					<td><%=rc.getRcWriter() %></td>
					
					<td><%=rc.getRecommContent() %></td>
					<%if(rc.getRecommRef() > 0) {%>
						<td>대댓글</td>
					<% }%>
					<%if(rc.getRecommRef() == 0) {%>
						<td>댓글</td>
					<% }%>
					<td><%=rc.getRecommDate()%></td>
					<td><%=rc.getBookRef()%></td>
				</tr>
			<%} %>
		</table>
		
		<div id="pageNavi"><%=pageNavi %></div>

</div>



<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>