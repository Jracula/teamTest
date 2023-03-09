<%@page import="com.litbooks.ooo.vo.OneOnOne"%>
<%@page import="com.litbooks.ooo.vo.OneOnOneComment"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
  		OneOnOne o = (OneOnOne)request.getAttribute("o");
   		ArrayList<OneOnOneComment> commentList = (ArrayList<OneOnOneComment>)request.getAttribute("commentList");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#oneOnOneView th, #oneOnOneView td{
		border: 1px solid #eee;
	}
	#oneOnOneContent{
		text-align: left;
		min-height: 300px;
	}
	
	#goList{
		float: right;
		margin-bottom : 10px;
	}

</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="page-title">1:1 문의사항
			<a class="btn bc200" href="/oneOnOneList.do?reqPage=1&reqPage1=1&memberNo=<%=m.getMemberNo() %>" id="goList">목록</a>
		</div>
		<table class="tbl" id="oneOnOneView">
			<tr class="tr-3">
				<th colspan="6"><%=o.getoTitle() %>
			</tr>
			<tr class="tr-1">
				<th class="td-3">작성자</th>
				<td><%=o.getoWriter() %>
				<th class="td-3">작성일</th>
				<td><%=o.getoRegDate() %>
				<th class="td-3">조회수</th>
				<td><%=o.getoReadCount() %>
			</tr>
			<tr class="tr-1">
				<th class="td-3">첨부파일</th>
				<td colspan="5">
				<%if(o.getFileName() != null) {%>
				<img src="/img/file.png" width="16px">
				<a href="/noticeFileDown.do?noticeNo=<%=o.getoNo() %>"><%=o.getFileName() %></a>
				<%} %>
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<div id="oneOnOneContent">
						<%=o.getoContentBr() %>
					</div>
				</td>
			</tr>
			<!-- if(m.getMemberId().equals(n.getNoticeWriter()))
			 조건을 이렇게만 주면 로그인을 하지 않았을 경우 에러발생 로그인조건을 걸어줘야한다. -->
			<%if( m!=null && m.getMemberId().equals(o.getoWriter()) ) {%>
			<tr>
				<th colspan="6">
					<a class="btn bc200" href="/oneOnOneUpdateFrm.do?oNo=<%=o.getoNo() %>">수정</a>
					<button class="btn bc200" onclick="oneOnOneDelete(<%=o.getoNo()%>);">삭제</button>
				</th>
			</tr>
			<%} %>
		</table>
<%-- 댓글		<div class="commentBox">
				<%for(OneOnOneComment oc : commentList) {%>
				<ul class="posting-comment">
					<li>
						<span class="material-icons">account_box</span>
					</li>
					<li>
						<p class="comment-info">
							<span><%=oc.getOo_writer() %></span>
							<span><%=oc.getOo_date() %></span>
						</p>
						<p class="comment-content"><%=oc.getOoContentBr() %></p>
						<textarea name="ncContent" class="input-form" style="min-height:96px;display:none;"><%=oc.getOo_content() %></textarea>
						<p class="comment-link">
							<%if(m != null) {%>
								<%if(m.getMemberId().equals(oc.getOo_writer())) {%>
									<a href="javascript:void(0)" onclick="modifyComment(this, <%=oc.getOo_no() %>, <%=n.getNoticeNo()%>);">수정</a>
									<a href="javascript:void(0)" onclick="deleteComment(this, <%=nc.getNcNo()%>, <%=n.getNoticeNo()%>);">삭제</a>
								<%} //해당댓글 수정조건(댓글작성자가 로그인한 회원인지 확인)%>							
								<!-- javascipt:void(0) 자바스크립트 따라가겠다느 뜻 a태그는 쓰되, 이동은하지않을 때 사용 -->
								<a href="javascript:void(0)" class="recShow">답글달기</a>
							<%}//대댓글 달기 조건문(로그인체크) %>
						</p>
					</li>
				</ul> 
			</div> --%>
		</div>
		
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
	
	<script>
		function oneOnOneDelete(oneOnOneNo){
			if(confirm("게시글을 삭제하시겠습니까?")){
				location.href="/deleteFaq.do?oneOnOneNo="+oneOnOneNo;
				//방법1.여기서는 noticeNo가 몇번인지 알 수 없으므로 매개변수로 넘겨줌
			}
		}
		$(".recShow").on("click", function(){
			//몇번째 댓글의 답글달기 버튼을 클릭한지 index번호를 구하기
			//답글달기 인덱스와 답글폼의 순번이 같으므로 인덱스대로 display:block;을 해줄것
			const idx = $(".recShow").index(this);
			if($(this).text() == "답글달기"){
				$(this).text("취소");
			}else{
				$(this).text("답글달기");
			}
			$(".inputRecommentBox").eq(idx).toggle();
			$(".inputRecommentBox").eq(idx).find("textarea").focus();
		});
		
		
	</script>
	
</body>
</html>