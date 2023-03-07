<%@page import="com.litbooks.qna.model.vo.QnaComment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.litbooks.qna.model.vo.Qna"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    Qna q = (Qna)request.getAttribute("q");
    ArrayList<QnaComment> commentList = (ArrayList<QnaComment>)request.getAttribute("commentList");
    ArrayList<QnaComment> reCommentList = (ArrayList<QnaComment>)request.getAttribute("reCommentList");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#qnaView th, #qnaView td{
		border: 1px solid #eee;
	}
	#qnaContent{
		text-align: left;
		min-height: 300px;
	}
	
	.inputCommentBox{
		margin: 50px;
	}
	.inputCommentBox>form>ul{
		list-style-type: none;
		display: flex;
	}
	.inputCommentBox>form>ul>li:first-child{
		width: 15%;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	.inputCommentBox>form>ul>li:first-child>span{
		font-size: 80px;
		color: #ccc;
	}
	.inputCommentBox>form>ul>li:nth-child(2){
		width: 75%;
	}
	.inputCommentBox>form>ul>li:nth-child(2)>textarea{
		height: 96px;
		min-height: 96px;
	}
	.inputCommentBox>form>ul>li:last-child{
		width: 10%;
	}
	.commentBox{
		margin: 50px;
	}
	.inputRecommentBox{
		margin: 30px 0px;
		display: none;
	}
	#goList{
		float: right;
		margin-bottom : 10px;
	}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
		<div class="page-content">
			<div class="page-title">문의게시판
				<a class="btn bc44" href="/qnaList.do?reqPage=1" id="goList">목록</a>
			</div>
			<table class="tbl" id="qnaView">
			<tr class="tr-3">
				<th colspan="6"><%=q.getqTitle() %>
				
			</tr>
			<tr class="tr-1">
				<th class="td-3">글번호</th>
				<td><%=q.getqNo() %>
				<th class="td-3">작성자</th>
				<td><%=q.getqWriter() %>
				<th class="td-3">작성일</th>
				<td><%=q.getqRegDate() %>
			</tr>
			<tr class="tr-1">
				<th class="td-3">첨부파일</th>
				<td colspan="3">
				<%if(q.getFileName() != null) {%>
				<img src="/img/file.png" width="16px">
				<a href="/qnaFileDown.do?qNo=<%=q.getqNo() %>"><%=q.getFileName() %></a>
				<%} %>
				<th class="td-3">조회수</th>
				<td><%=q.getqReadCount() %>
				</td>
				
			</tr>
			<tr>
				<td colspan="6">
					<div id="qnaContent">
						<%=q.getQnaContentBr() %>
					</div>
				</td>
			</tr>
			<!-- if(m.getMemberId().equals(n.getNoticeWriter()))
			 조건을 이렇게만 주면 로그인을 하지 않았을 경우 에러발생 로그인조건을 걸어줘야한다. -->
			 
	
			<%--
			<%if( m!=null && m.getMemberId().equals(q.getqWriter()) ) {%>
			<tr>
				<th colspan="6">
					<a class="btn bc44" href="/qnaUpdateFrm.do?qNo=<%=q.getqNo() %>">수정</a>
					<button class="btn bc44" onclick="qnaDelete(<%=q.getqNo()%>);">삭제</button>
				</th>
			</tr>
			<%} %>
			
			 --%>
			 <tr>
				<th colspan="6">
					<a class="btn bc44" href="/qnaUpdateFrm.do?qNo=<%=q.getqNo() %>">수정</a>
					<button class="btn bc44" onclick="qnaDelete(<%=q.getqNo()%>);">삭제</button>
					
				</th>
			</tr>
		</table>
		<div class="commentBox">
			<%for(QnaComment qc : commentList) {%>
			<ul class="posting-comment">
				<li>
					<span class="material-icons">account_box</span>
				</li>
				<li>
					<p class="comment-info">
						<span><%=qc.getQcWriter() %></span>
						<span><%=qc.getQcDate() %></span>
					</p>
					<p class="comment-content"><%=qc.getQcContentBr() %></p>
					<textarea name="qcContent" class="input-form" style="min-height:96px;display:none;"><%=qc.getQcContent() %></textarea>
					<p class="comment-link">
						<%if(m != null) {%>
							<%if(m.getMemberId().equals(qc.getQcWriter())) {%>
			<%-- 수정필--%>					<a href="javascript:void(0)" onclick="modifyComment(this, <%=qc.getQcNo()%>, <%=q.getqNo()%>);">수정</a>
			<%-- 수정필--%>					<a href="javascript:void(0)" onclick="deleteComment(this, <%=qc.getQcNo()%>, <%=q.getqNo()%>);">삭제</a>
							<%} //해당댓글 수정조건(댓글작성자가 로그인한 회원인지 확인)%>							
							<!-- javascipt:void(0) 자바스크립트 따라가겠다느 뜻 a태그는 쓰되, 이동은하지않을 때 사용 -->
							<a href="javascript:void(0)" class="recShow">답글달기</a>
						<%}//대댓글 달기 조건문(로그인체크) %>
					</p>
				</li>
			</ul>
			<%for(QnaComment qcc : reCommentList ) {%>
				<%if(qcc.getQcRef() == qc.getQcNo()) {%>
				<ul class="posting-comment reply">
					<li>
						<span class="material-icons">subdirectory_arrow_right</span>
						<span class="material-icons">account_box</span>
					</li>
					<li>	
						<p class="comment-info">
							<span><%=qcc.getQcWriter() %></span>
							<span><%=qcc.getQcDate() %></span>
						</p>
						<p class="comment-content"><%=qcc.getQcContentBr() %></p>
						<textarea name="qcContent" class="input-form" style="min-height:96px;display:none;"><%=qcc.getQcContent() %></textarea>
						<p class="comment-link">
						
						<%-- 
							<%if(m!=null && m.getMemberId().equals(qcc.getQcWriter())) {%>
								 수정 삭제 버튼 넣을것
							<%} %>						
						--%>
			<%-- 수정필--%>					<a href="javascript:void(0)" onclick="modifyComment(this, <%=qcc.getQcNo()%>, <%=q.getqNo()%>);">수정</a>
			<%-- 수정필--%>					<a href="javascript:void(0)" onclick="deleteComment(this, <%=qcc.getQcNo()%>, <%=q.getqNo()%>);">삭제</a>
						</p>
					</li>
				</ul>
				<%} //댓글번호 체크 if문 종료 %>
			<%} //대댓글 출력 for문 종료%>
					
				<!-- 댓글에 대한 대댓글 입력양식 -->
				<%if(m != null) {%>
					<div class="inputCommentBox inputRecommentBox">
						<form action="/insertQnaComment.do" method="post">
						<!-- 댓글작성 시 사용했던 서블릿을 또 이용 아래댓글과 차이점 ncRef값이 존재 -->
							<ul>
								<li>
									<span class="material-icons">subdirectory_arrow_right</span>
								</li>
								<li>
									<input type="hidden" name="qcWriter" value="<%=m.getMemberId() %>">
									<input type="hidden" name="qnaRef" value="<%=q.getqNo() %>">
									<!-- 현재 출력하고 있는 댓글의 번호를 ncRef로 받아온다 -->
									<input type="hidden" name="qcRef" value="<%=qc.getQcNo() %>">
									<textarea name="qcContent" class="input-form"></textarea>
								</li>
								<li>
									<button type="submit" class="btn bc1 bs4">등록</button>
								</li>
							</ul>
						</form>
					</div>
				<%} %>
			
			<%}//댓글 출력 for문 끝나는위치 %>
		</div>
		<!-- 로그인이 되어있는 경우에만 댓글 작성 화면을 띄움 -->
		<%if(m != null) {%>
		<div class="inputCommentBox">
			<form action="/insertQnaComment.do" method="post">
				<ul>
					<li>
						<span class="material-icons">account_box</span>
					</li>
					<li>
						<input type="hidden" name="qcWriter" value="<%=m.getMemberId() %>">
						<input type="hidden" name="qnaRef" value="<%=q.getqNo() %>">
						<input type="hidden" name="qcRef" value="0">
						<textarea name="qcContent" class="input-form"></textarea>
					</li>
					<li>
						<button type="submit" class="btn bc4 bs4">등록</button>
					</li>
				</ul>
			</form>
		</div>
		<%} %>
		</div>
		
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
	
	<script>
		function qnaDelete(qNo){
			if(confirm("게시글을 삭제하시겠습니까?")){
				location.href="/deleteQna.do?qNo="+qNo;
			}
		}
	
	</script>
</body>
</html>