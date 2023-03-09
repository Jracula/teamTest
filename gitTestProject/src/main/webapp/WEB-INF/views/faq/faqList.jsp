<%@page import="com.litbooks.faq.model.vo.Faq"%>
<%@page import="com.litbooks.qna.model.vo.Qna"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<Faq> list = (ArrayList<Faq>)request.getAttribute("list");
    	String pageNavi = (String)request.getAttribute("pageNavi");
    	int start = (int)request.getAttribute("start");
		int fFlag = (int)request.getAttribute("fFlag");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/bootstrap-modal.css" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />

<style>
	.page-icon{
		overflow: hidden;
	}
	.click-icon-wrap{
		float : left;
		text-align: center;
		margin: 10px 50px;
		margin-bottom: 100px;
		cursor: pointer;
	}
	.click-icon>.material-symbols-outlined{
		font-size : 120px;
	}
	.click-icon-wrap>div{
		text-align: center;
	}
	.click-icon-wrap>p{
		font-size : 24px;
	}

	.board-tbl{
		display:none;
	}
	.active-tab{
		display:block;
	}

	.board-tbl{
		margin: 10px auto;
		border: 1px solid #eeeeee;
	}

	.tr-1 {
		border-bottom: 1px solid #eeeeee;
	}
	
</style>
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="page-title"><h1>FAQ</h1></div>
		<div class="page-icon">
			<div class="click-icon-wrap" onclick="location.href='/faqList.do?reqPage=1&reqPage1=1&fFlag=1';">
				<div class="click-icon"><span class="material-symbols-outlined">loyalty</span></div>
				<p>회원관리<p>			
			</div>
			<div class="click-icon-wrap" onclick="location.href='/faqList.do?reqPage=1&reqPage1=1&fFlag=2';">
				<div class="click-icon"><span class="material-symbols-outlined">payments</span></div>
				<p>결제/혜택/이벤트</p>		
			</div>
			<div class="click-icon-wrap" onclick="location.href='/faqList.do?reqPage=1&reqPage1=1&fFlag=3';">
				<div class="click-icon" ><span class="material-symbols-outlined">menu_book</span></div>
				<p>도서 관련 문의</p>			
			</div>
			<div class="click-icon-wrap" onclick="location.href='/faqList.do?reqPage=1&reqPage1=1&fFlag=4';">
				<div class="click-icon"><span class="material-symbols-outlined">assistant_on_hub</span></div>
				<p>뷰어 문의</a>	
			</div>
			<div class="click-icon-wrap" onclick="location.href='/faqList.do?reqPage=1&reqPage1=1&fFlag=5';">
				<div class="click-icon"><span class="material-symbols-outlined">add_task</span></div>
				<p>기타 문의</a>
			</div>
		</div>
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
		
		<table class="tbl tbl-hover board-tbl active-tab" id="detail-exp">
			<tr class="tr-2">
				<th style = "width:5%">번호</th>
				<th style = "width:5%">아이디</th>
				<th style = "width:20%">문의사항</th>
				<th style = "width:5%">작성일</th>
				<th style = "width:5%">조회수</th>
			</tr>
			<%if(fFlag == 1) {%>
				<%
				for(Faq f : list) {
				%>
					<tr class="tr-1">
						<td><%=f.getfNo() %></td>
						<td><%=f.getfWriter() %></td>
						<td>
							<a href="/faqView.do?faqNo=<%=f.getfNo() %>">
								<%=f.getfTitle() %>
							</a>
						</td>
						<td><%=f.getfRegDate() %></td>
						<td><%=f.getfReadCount() %></td>
					</tr>			
				<%} %>
			<%} %>
			<%if(fFlag == 2) {%>
				<%
				for(Faq f : list) {
				%>
					<tr class="tr-1">
						<td><%=f.getfNo() %></td>
						<td><%=f.getfWriter() %></td>
						<td>
							<a href="/faqView.do?faqNo=<%=f.getfNo() %>">
								<%=f.getfTitle() %>
							</a>
						</td>
						<td><%=f.getfRegDate() %></td>
						<td><%=f.getfReadCount() %></td>
					</tr>			
				<%} %>
			<%} %>
			
			<%if(fFlag == 3) {%>
				<%
				for(Faq f : list) {
				%>
					<tr class="tr-1">
						<td><%=f.getfNo() %></td>
						<td><%=f.getfWriter() %></td>
						<td>
							<a href="/faqView.do?faqNo=<%=f.getfNo() %>">
								<%=f.getfTitle() %>
							</a>
						</td>
						<td><%=f.getfRegDate() %></td>
						<td><%=f.getfReadCount() %></td>
					</tr>			
				<%} %>
			<%} %>
			
			<%if(fFlag == 4) {%>
				<%
				for(Faq f : list) {
				%>
					<tr class="tr-1">
						<td><%=f.getfNo() %></td>
						<td><%=f.getfWriter() %></td>
						<td>
							<a href="/faqView.do?faqNo=<%=f.getfNo() %>">
								<%=f.getfTitle() %>
							</a>
						</td>
						<td><%=f.getfRegDate() %></td>
						<td><%=f.getfReadCount() %></td>
					</tr>			
				<%} %>
			<%} %>
			
			<%if(fFlag == 5) {%>
				<%
				for(Faq f : list) {
				%>
					<tr class="tr-1">
						<td><%=f.getfNo() %></td>
						<td><%=f.getfWriter() %></td>
						<td>
							<a href="/faqView.do?faqNo=<%=f.getfNo() %>">
								<%=f.getfTitle() %>
							</a>
						</td>
						<td><%=f.getfRegDate() %></td>
						<td><%=f.getfReadCount() %></td>
					</tr>			
				<%} %>
			<%} %>
			</table>
			
		<% if(m != null && m.getMemberLevel() == 1){ %>
			<a class="btn bc200 writeBtn" href="/faqWriteFrm.do">글쓰기</a>
		
		<%}else {%>
		
		<%} %>
		<div id="pageNavi"><%=pageNavi %></div>		
	</div>
	
	<script>
		const index = '<%=fFlag%>';
		const Wrap = $(".click-icon-wrap");
		Wrap.eq(index-1).css("backgroundColor","#BDCDD6");
		Wrap.eq(index-1).css("border-bottom-left-radius","15px");
		Wrap.eq(index-1).css("border-bottom-right-radius","15px");
		Wrap.eq(index-1).css("border-top-left-radius","15px");
		Wrap.eq(index-1).css("border-top-right-radius","15px");
		

	
	
/* $(".click-icon-wrap").click(function(){
		const value = $(this).children().first().text();
		const table = (".tbl tbl-hover notice-tbl");
		
		$.ajax({
			url : "/faqAjax.do",
			type : "get",
			data : {fFlag : value},
			dataType : "gson",
			success : function(fpd){

				for(let i=0;i<fpd.legnth;i++){
					const tr = $("<tr></tr>");
					tr.addClass("tr-1");
					const td1 = $("<td></td>");
					td1.append(i+1);
					const td2 = $("<td></td>");
					const a = $("<a></a>");
					a.addClass("detailContent");
					a.attr("herf","/faqView.do?faqNo=f.getfNo()");
					a.html(fpd.getfContent);
					td2.append(a);
					tr.append(td2);
					const td3 = $("<tr></tr>");
					a.append(fpd[i].getfWriter);
					tr.append(td3);
					const td4 = $("<tr></tr>");
					a.append(fpd[i].getfRegDate);
					tr.append(td4);
					const td5 = $("<tr></tr>");
					a.append(fpd[i].getfReadCount);
					tr.append(td4);
					table.append(tr);
				}	
				
			},
			error : function(){
				console.log("error");
			}
			
		})
	});
	 */
	
	<%-- <%for(int i=0; i<list.size(); i++) {%>
	<%Faq f = list.get(i); %>
	<%if(f.getfFlag() == 1) {%>
		<tr class="tr-1">
			<td><%=i+start %></td>
			<td>
				<a class="detailContent" href="/faqView.do?faqNo=<%=f.getfNo() %>">
				<%=f.getfContent() %>
				</a>
			</td>
			<td><%=f.getfWriter() %></td>
			<td><%=f.getfRegDate() %></td>
			<td><%=f.getfReadCount() %></td>
		</tr>
	<%}else if(f.getfFlag() == 2) {%>
		<tr class="tr-1">
			<td><%=i+start %></td>
			<td>
				<a class="detailContent" href="/faqView.do?faqNo=<%=f.getfNo() %>">
				<%=f.getfContent() %>
				</a>
			</td>
			<td><%=f.getfWriter() %></td>
			<td><%=f.getfRegDate() %></td>
			<td><%=f.getfReadCount() %></td>
		</tr>
	<%}else if(f.getfFlag() == 3) {%>
		<tr class="tr-1">
			<td><%=i+start %></td>
			<td>
				<a class="detailContent" href="/faqView.do?faqNo=<%=f.getfNo() %>">
				<%=f.getfContent() %>
				</a>
			</td>
			<td><%=f.getfWriter() %></td>
			<td><%=f.getfRegDate() %></td>
			<td><%=f.getfReadCount() %></td>
		</tr>
	<%}else if(f.getfFlag() == 4) {%>
		<tr class="tr-1">
			<td><%=i+start %></td>
			<td>
				<a class="detailContent" href="/faqView.do?faqNo=<%=f.getfNo() %>">
				<%=f.getfContent() %>
				</a>
			</td>
			<td><%=f.getfWriter() %></td>
			<td><%=f.getfRegDate() %></td>
			<td><%=f.getfReadCount() %></td>
		</tr>
		<%} %> --%>
	
	</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>



</body>
</html>