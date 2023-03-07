<%@page import="com.litbooks.member.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("list"); 
    	String pageNavi = (String)request.getAttribute("pageNavi");
        int start = (int)request.getAttribute("start");
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
	<div class="allMember-title"><h2>전체 회원 정보</h2></div>
		<table class="tbl tbl-hover">
			<tr class="tr-3">
				<th>No</th>
				<th>회원번호</th>
				<th>아이디</th>
				<th>이름</th>
				<th>전화번호</th>
				<th>이메일</th>
				<th>가입일</th>
			</tr>
			<%for(int i=0;i<list.size();i++) {%>
				<%Member member = list.get(i); %>
				<tr class="tr-1">
					<td><%=i+start %></td>
					<td><%=member.getMemberNo() %></td>
					<td><%=member.getMemberId() %></td>
					<td><%=member.getMemberName() %></td>
					<td><%=member.getMemberPhone() %></td>
					<td><%=member.getMemberEmail() %></td>
					<td><%=member.getEnrollDate()%></td>
				</tr>
			<%} %>
		</table>
		
		<div id="pageNavi"><%=pageNavi %></div>

</div>



<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>