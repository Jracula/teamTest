<%@page import="com.litbooks.qna.model.vo.Qna"%>
<%@page import="com.litbooks.faq.model.vo.Faq"%>
<%@page import="com.litbooks.ooo.vo.OneOnOne"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	ArrayList<Qna> list = (ArrayList<Qna>)request.getAttribute("list");
		ArrayList<OneOnOne> list1 = (ArrayList<OneOnOne>)request.getAttribute("list1");
   		String pageNavi = (String)request.getAttribute("pageNavi");
   		String pageNavi1 = (String)request.getAttribute("pageNavi1");
		int start = (int)request.getAttribute("start");
		int start1 = (int)request.getAttribute("start1");
    %>
<!DOCTYPE html>
<html>
<head>
<style>

	.update-qna{
		margin-top: 100px;
	}
	.tbl-divs{
		overflow: hidden;
	}
	.tbl-div{
		width: 580px;
		float: left;
		margin-top: 10px;
	}
	.tbl-div:first-child {
		margin-right: 20px
	}
	.textarea{
		width: 1100px;
		height: 300px;
	}
	.titlearea{
		height: 50px;
		
	}

	
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
			<div class="page-title">1:1 문의는 여기서</div>
			
		<%if(m != null && m.getMemberNo() > 0) {%>
				<a href="/qnaList.do?reqPage=1" class="btn bc1" >문의게시판</a>
				<label><a href="/oneOnOneList.do?reqPage=1&reqPage1=1&memberNo=<%=m.getMemberNo() %>" class="btn bc1">1:1게시판</a></label>
				<label><a href="#" class="btn bc1">메일보내기</a></label>	
			<%}else {%>
				<label><a href="/signinFrm.do" class="btn bc1">로그인을 해주세요</a></label>
				<a href="/qnaList.do?reqPage=1" class="btn bc1" >문의게시판</a>
				
			<%} %>
		<div class="tbl-divs">
			<div class="tbl-div">
				<table class="tbl tbl-hover notice-tbl">
					<tr class="tr-4">
						<td colspan="5">고객님이 문의게시판에 질문한 내용이야</td>
					</tr>
					<tr class="tr-4">
						<th style="width:20%">번호</th>
						<th style="width:35%">제목</th>
						<th style="width:10%">작성자</th>
						<th style="width:15%">작성일</th>
						<th style="width:20%">조회수</th>
					</tr>
					<%for(int i=0; i<list.size(); i++) {%>
						<% Qna q = list.get(i); %>
					<tr class="tr-1">
						<td><%=i+start %></td>
						<td>
							<a href="/qnaView.do?qnaNo=<%=q.getqNo() %>">
								<%=q.getqTitle() %>
							</a>
						</td>
						<td class="qMemberId"><%=m.getMemberId() %></td>
						<td><%=q.getqRegDate() %></td>
						<td><%=q.getqReadCount() %></td>
					</tr>
					<%} %> 
				</table>
				<div id="pageNavi"><%=pageNavi %></div>
			</div>
			<div class="tbl-div">
				<table class="tbl tbl-hover notice-tbl">
				<tr class="tr-3">
						<td colspan="5">고객님이 1:1 게시판에 질문한 내용이야</td>
					</tr>
					<tr class="tr-3">
						<th style="width:20%">번호</th>
						<th style="width:35%">제목</th>
						<th style="width:10%">작성자</th>
						<th style="width:15%">작성일</th>
						<th style="width:20%">조회수</th>
					</tr>
					
					<%for(int i=0; i<list1.size(); i++) {%>
						<% OneOnOne o = list1.get(i); %>
					<tr class="tr-1">
						<td><%=i+start1 %></td>
						<td>
							<a href="/oneOnOneView.do?oNo=<%=o.getoNo() %>&memberNo=<%=m.getMemberNo()%>">
								<%=o.getoTitle() %>
							</a>
						</td>
						<td class="qMemberId"><%=m.getMemberId() %></td>
						<td><%=o.getoRegDate() %></td>
						<td><%=o.getoReadCount() %></td>
					</tr>
					<%} %> 
				</table>
				<div id="pageNavi"><%=pageNavi1 %></div>
			</div>
		</div>
		
		
		<div class="update-qna">
			<table class="tbl tbl-hover board-tbl">
				<form action="/oneOnOneWrite.do" method="post">
					<tr class="tr-4">
						<td colspan="6"><p>문의하기 전에 이전에 질문했던 사항을 위에서 확인해주세요.</p></td>
					</tr>
					<tr class="tr-3" colspan="5">
						<td style = "width:20%">골라골라</td>
						<td style = "width:10%"><input type="radio" name="oneOnOneFlag" value="1">  회원관리</td>
						<td style = "width:10%"><input type="radio" name="oneOnOneFlag" value="2">  결제/혜택/이벤트</td>
						<td style = "width:10%"><input type="radio" name="oneOnOneFlag" value="3">  도서관련 문의</td>
						<td style = "width:10%"><input type="radio" name="oneOnOneFlag" value="4">  뷰어 문의</td>
						<td style = "width:10%"><input type="radio" name="oneOnOneFlag" value="5">  기타 문의</td>
					</tr>
					<tr class="tr-3">
						<td style = "width:20%"> 문의사항 제목</td>
						<td colspan="5"><input class="titlearea" style="width:1100px" type="text" name="oneOnOneTitle"></td>
					</tr>
					<tr class="tr-3">
						<td style = "width:20%">문의 내용</td>
						<td colspan="5"><textarea class="textarea" name="oneOnOneContent"></textarea></td>
						<input type=hidden name = "oneOnOneWriter" value="<%= m.getMemberId() %>">	
						<input type=hidden name ="oMemberNo" value="<%= m.getMemberNo() %>">
					</tr>
					<tr>
						<td colspan="6"><button type="submit" class="btn bc1 bs4">문의하기</button></td>					
					</tr>
				</form>
			</table>
		</div>
	</div>
	
	<script>
		$("[type=radio]").on("click",function(){
			const flagVal = $(this).val();
			
			
		});
		
	
	</script>
	
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>