<%@page import="com.litbooks.notice.vo.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
    	String pageNavi = (String)request.getAttribute("pageNavi");
    	int start = (int)request.getAttribute("start");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style>
	.notice-tbl a:hover{
		text-decoration: underline;
	}
	.notice-tbl tr{
		border-bottom: 1px solid #ccc
	}
	.notice-tbl tr>td:nth-child(2) {
		text-align: left;
	}
	#pageNavi{
		margin: 30px;
	}
</style>
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
		<div class="page-content">
			<div class="page-title">공지사항</div>
			<!-- 로그인이 되어 있는 상태이면서 회원등급이 관리자인 경우 → 글쓰기 가능 -->
			<%if(m!= null && m.getMemberLevel() == 1) { %>
				<a class="btn bc2 writeBtn" href="/noticeWriteFrm.do">글쓰기</a>
			<% } %>
			
			<!-- 공지사항 전체 조회 -->
			<table class="tbl tbl-hover notice-tbl">
				<tr class="tr-2">
					<th style="width:10%">번호</th>
					<th style="width:45%">제목</th>
					<th style="width:15%">작성자</th>
					<th style="width:20%">작성일</th>
					<th style="width:10%">조회수</th>
				</tr>
				<% for(int i=0; i<list.size(); i++) { %>
					<%Notice n = list.get(i); %>
					<tr class="tr-1">
						<td><%=i+start %></td>
						<td>
							<a href="/noticeView.do?noticeNo=<%=n.getNoticeNo()%>">
							<%=n.getNoticeTitle() %>
							</a>
						</td>						
						<td><%=n.getNoticeWriter() %></td>
						<td><%=n.getRegDate() %></td>
						<td><%=n.getReadCount() %></td>
					</tr>
				<% } %>
			</table>
			
			<div id="pageNavi"><%=pageNavi %></div>
		</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>			
</body>
</html>