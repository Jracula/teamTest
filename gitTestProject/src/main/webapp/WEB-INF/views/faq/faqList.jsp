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
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
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
	.material-symbols-outlined{
		font-size : 150px;
	}
	.click-icon-wrap>div{
		text-align: center;
	}
	.click-icon-wrap>p{
		font-size : 30px;
	}
	.mid-navi{
		margin-bottom: 30px;
	}
</style>
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="page-title"><h1>FAQ</h1></div>
		<div class="page-icon">
			<div class="click-icon-wrap">
				<span style="display:none">1</span>
				<div class="click-icon"><span class="material-symbols-outlined">loyalty</span></div>
				<p>회원관리<p>			
			</div>
			<div class="click-icon-wrap">
				<span style="display:none">2</span>
				<div class="click-icon"><span class="material-symbols-outlined">payments</span></div>
				<p>결제/혜택/이벤트</p>		
			</div>
			<div class="click-icon-wrap">
				<span style="display:none">3</span>
				<div class="click-icon" ><span class="material-symbols-outlined">menu_book</span></div>
				<p>도서 관련 문의</p>			
			</div>
			<div class="click-icon-wrap">
				<span style="display:none">4</span>
				<div class="click-icon"><span class="material-symbols-outlined">assistant_on_hub</span></div>
				<p>뷰어 문의</a>	
			</div>
		</div>
		<div class="mid-navi">
			<a href="/qnaList.do?reqPage=1" class="btn bc1" >문의게시판</a>
			<label><a href="#" class="btn bc1">1:1게시판</a></label>
			<label><a href="#" class="btn bc1">메일보내기</a></label>				
		</div>
		
		<table class="tbl tbl-hover notice-tbl">
			<tr class="tr-2">
				<th style="width:10%">번호</th>
				<th style="width:55%">제목</th>
				<th style="width:10%">작성자</th>
				<th style="width:10%">작성일</th>
				<th style="width:10%">조회수</th>
			</tr>
			

<%--  			<%for(int i=0; i<list.size(); i++) {%>
			<%Faq f = list.get(i); %>
			
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
				<%} %>
 --%>
			 
		</table>
		
		<% if(m != null && m.getMemberLevel() == 1){ %>
			<a class="btn bc2 writeBtn" href="/faqWriteFrm.do">글쓰기</a>
		
		<%}else {%>
		
		<%} %>
		<div id="pageNavi"><%=pageNavi %></div>		
	</div>
	<script>
	
	
$(".click-icon-wrap").click(function(){
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