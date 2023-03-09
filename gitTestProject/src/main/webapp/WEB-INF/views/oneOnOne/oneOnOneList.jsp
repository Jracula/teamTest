<%@page import="com.litbooks.qna.model.vo.Qna"%>
<%@page import="com.litbooks.faq.model.vo.Faq"%>
<%@page import="com.litbooks.ooo.vo.OneOnOne"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	ArrayList<Qna> list = (ArrayList<Qna>)request.getAttribute("list");
		ArrayList<OneOnOne> list1 = (ArrayList<OneOnOne>)request.getAttribute("list1");
		ArrayList<OneOnOne> list2 = (ArrayList<OneOnOne>)request.getAttribute("list2");
   		String pageNavi = (String)request.getAttribute("pageNavi");
   		String pageNavi1 = (String)request.getAttribute("pageNavi1");
   		String pageNavi2 = (String)request.getAttribute("pageNavi2");
		int start = (int)request.getAttribute("start");
		int start1 = (int)request.getAttribute("start1");
		int start2 = (int)request.getAttribute("start2");
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
	.tbl-div-2{
		width: 580px;
		float: left;
		margin-top: 10px;
	}
	.tbl-div-2:first-child {
		margin-right: 20px
	}
	.textarea{
		width: 1100px;
		height: 300px;
	}
	.titlearea{
		height: 50px;
		
	}
	.board-tbl{
		margin: 10px auto;
		margin: 10px auto;
		border: 1px solid #eeeeee;
	}
	.tr-1 {
		border-bottom: 1px solid #eeeeee;
	}
	.oneTblTitle{
		border-bottom: 5px solid black;
	}
	
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/bootstrap-modal.css" />
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
			<div class="page-title">1:1 문의는 여기서</div>
			<div class="mid-navi">
					<a href="/qnaList.do?reqPage=1" class="btn bc200" >문의게시판</a>
					<a href="/faqList.do?reqPage=1&fFlag=1" class="btn bc200" >자주하는 질문</a>
				<%if(m != null && m.getMemberNo() > 0) {%>
					<label><a href="/oneOnOneList.do?reqPage=1&reqPage1=1&memberNo=<%=m.getMemberNo() %>" class="btn bc200">1:1게시판</a></label>
				<%}else {%>
					<label><a href="/signinFrm.do" class="btn bc200">로그인을 해주세요</a></label>
				<%} %>
					<button type="button" class="btn bc200" data-toggle="modal" data-target="#myModal">메일보내기</button>	
			</div>
		<!-- Modal 내용 변경 -->
			<div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title" style="text-align: center;">알림</h4>
						</div>
						<div class="modal-body">
							<p id="givenMessage" style="text-align: center;">준비중입니다. 다음에 도전하세요</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">확인</button>
						</div>
					</div>
				</div>
			</div>
	<!-- Modal 끝 -->	
		<%if(m.getMemberLevel() == 1) {%>
			<div class="tbl-div">
				<table class="tbl tbl-hover notice-tbl">
					<tr class="tr-3">
						<td class="oneTblTitle" colspan="5">고객님이 1:1 게시판에 질문한 내용이야</td>
					</tr>
					<tr class="tr-3">
						<th style="width:20%">번호</th>
						<th style="width:40%">제목</th>
						<th style="width:20%">작성자</th>
						<th style="width:20%">작성일</th>
					</tr>
					
					<%for(int i=0; i<list2.size(); i++) {%>
						<% OneOnOne o = list2.get(i); %>
					<tr class="tr-1">
						<td><%=i+start2 %></td>
						<td>
							<a href="/oneOnOneView1.do?oNo=<%=o.getoNo() %>&memberNo=<%=m.getMemberNo()%>">
								<%=o.getoTitle() %>
							</a>
						</td>
						<td class="qMemberId"><%=o.getoWriter() %></td>
						<td><%=o.getoRegDate() %></td>
					</tr>
					<%} %> 
				</table>
				<div id="pageNavi"><%=pageNavi2 %></div>
			</div>
		
		
		<%}else {%>
		<div class="tbl-divs">
			<div class="tbl-div-2">
				<table class="tbl tbl-hover notice-tbl">
					<tr class="tr-4">
						<td class="oneTblTitle" colspan="5">고객님이 문의게시판에 질문한 내용이야</td>
					</tr>
					<tr class="tr-4">
						<th style="width:20%">번호</th>
						<th style="width:35%">제목</th>
						<th style="width:10%">작성자</th>
						<th style="width:15%">작성일</th>
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
					</tr>
					<%} %> 
				</table>
				<div id="pageNavi"><%=pageNavi %></div>
			</div>
			<div class="tbl-div-2">
				<table class="tbl tbl-hover notice-tbl">
				<tr class="tr-3">
						<td class="oneTblTitle" colspan="5">고객님이 1:1 게시판에 질문한 내용이야</td>
					</tr>
					<tr class="tr-3">
						<th style="width:20%">번호</th>
						<th style="width:40%">제목</th>
						<th style="width:20%">작성자</th>
						<th style="width:20%">작성일</th>
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
						<td colspan="6"><button type="submit" class="btn bc200 bs4">문의하기</button></td>					
					</tr>
				</form>
			</table>
		</div>
		<%} %>
	</div>
	
	<script>
		
	
	</script>
	
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>