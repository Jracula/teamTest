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
			<%if( m!=null && m.getMemberId().equals(o.getoWriter()) || m.getMemberLevel() == 1 ) {%>
			<tr>
				<th colspan="6">
					<a class="btn bc200" href="/oneOnOneUpdateFrm.do?oNo=<%=o.getoNo() %>">수정</a>
					<button class="btn bc200" onclick="oneOnOneDelete(<%=o.getoNo()%>);">삭제</button>
				</th>
			</tr>
			<%} %>
		</table>
		<%-- <div class="commentBox">
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
						<textarea name="ocContent" class="input-form" style="min-height:96px;display:none;"><%=oc.getOo_content() %></textarea>
						<p class="comment-link">
							<%if(m != null) {%>
								<%if(m.getMemberId().equals(oc.getOo_writer())) {%>
									<a href="javascript:void(0)" onclick="modifyComment(this, <%=oc.getOo_no()%>, <%=o.getoNo()%>);">수정</a>
									<a href="javascript:void(0)" onclick="deleteComment(this, <%=oc.getOo_no()%>, <%=o.getoNo()%>);">삭제</a>
								<%} //해당댓글 수정조건(댓글작성자가 로그인한 회원인지 확인)%>							
								<!-- javascipt:void(0) 자바스크립트 따라가겠다느 뜻 a태그는 쓰되, 이동은하지않을 때 사용 -->
								<a href="javascript:void(0)" class="recShow">답글달기</a>
							<%}//대댓글 달기 조건문(로그인체크) %>
						</p>
					</li>
				</ul>
				<%for(NoticeComment ncc : reCommentList ) {%>
					<%if(ncc.getNcRef() == nc.getNcNo()) {%>
					<ul class="posting-comment reply">
						<li>
							<span class="material-icons">subdirectory_arrow_right</span>
							<span class="material-icons">account_box</span>
						</li>
						<li>	
							<p class="comment-info">
								<span><%=ncc.getNcWriter() %></span>
								<span><%=ncc.getNcDate() %></span>
							</p>
							<p class="comment-content"><%=ncc.getNcContentBr() %></p>
							<textarea name="ncContent" class="input-form" style="min-height:96px;display:none;"><%=ncc.getNcContent() %></textarea>
							<p class="comment-link">
								<%if(m!=null && m.getMemberId().equals(ncc.getNcWriter())) {%>
									<a href="javascript:void(0)" onclick="modifyComment(this, <%=ncc.getNcNo()%>, <%=o.getoNo()%>);">수정</a>
									<a href="javascript:void(0)" onclick="deleteComment(this, <%=ncc.getNcNo()%>, <%=o.getoNo()%>);">삭제</a>
								<%} %>
							</p>
						</li>
					</ul>
					<%} //댓글번호 체크 if문 종료 %>
				<%} //대댓글 출력 for문 종료%>
					<!-- 댓글에 대한 대댓글 입력양식 -->
					<%if(m != null) {%>
						<div class="inputCommentBox inputRecommentBox">
							<form action="/insertOneOnOneComment.do" method="post">
							<!-- 댓글작성 시 사용했던 서블릿을 또 이용 아래댓글과 차이점 ncRef값이 존재 -->
								<ul>
									<li>
										<span class="material-icons">subdirectory_arrow_right</span>
									</li>
									<li>
										<input type="hidden" name="ocWriter" value="<%=m.getMemberId() %>">
										<input type="hidden" name="oRef" value="<%=o.getoNo() %>">
										<!-- 현재 출력하고 있는 댓글의 번호를 ncRef로 받아온다 -->
										<input type="hidden" name="ncRef" value="<%=oc.getOo_no() %>">
										<textarea name="ocContent" class="input-form"></textarea>
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
		<!-- 로그인이 되어있는 경우에만 댓글 작성 화면을 띄움 --
		<!-- 여기가 댓글 -->
			<%if(m != null) {%>
			<div class="inputCommentBox">
				<form action="/insertOneOnOneComment.do?memberNo=<%=m.getMemberNo() %>" method="post">
					<ul>
						<li>
							<span class="material-icons">account_box</span>
						</li>
						<li>
							<input type="hidden" name="ocWriter" value="<%=m.getMemberId() %>">
							<input type="hidden" name="oRef" value="<%=o.getoNo() %>">
							<input type="hidden" name="ncRef" value="0">
							<textarea name="ocContent" class="input-form"></textarea>
						</li>
						<li>
							<button type="submit" class="btn bc4 bs4">등록</button>
						</li>
					</ul>
				</form>
			</div>
			<%} %>
		</div>
	</div> --%>
		
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
	
	<script>
		function oneOnOneDelete(oneOnOneNo){
			if(confirm("게시글을 삭제하시겠습니까?")){
				location.href="/deleteOneOnOne.do?oneOnOneNo="+oneOnOneNo;
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
		
		function modifyComment(obj, ocNo, oNo){
			//숨겨놓은 textarea를 화면에 보여줌
			$(obj).parent().prev().show();
			//화면에 있던 댓글내용(p태그)를 숨김
			$(obj).parent().prev().prev().hide();
			//수정 -> 수정완료
			$(obj).text("수정완료");
			$(obj).attr("onclick", "modifyComplete(this, "+ocNo+", "+oNo+")");
			//삭제 -> 수정취로
			$(obj).next().text("수정취소");
			$(obj).next().attr("onclick", "modifyCancel(this, "+ocNo+", "+oNo+")");
			//답글달기버튼 삭제
			$(obj).next().next().hide();
		}
		
		function modifyCancel(obj, ocNo, oNo){
			$(obj).parent().prev().hide(); //textarea숨김
			$(obj).parent().prev().prev().show(); //기존댓글 다시 보여줌
			//수정완료 -> 수정
			$(obj).prev().text("수정");
			$(obj).prev().attr("onclick", "modifyComment(this, "+ocNo+", "+oNo+")");
			//수정취소 -> 삭제
			$(obj).text("삭제");
			$(obj).attr("onclick", "deleteComment(this, "+ocNo+", "+oNo+")");
			//답글달기 버튼 다시 보여줌
			$(obj).next().show();
		}	
		<%-- 수정 필요 --%>
		function modifyComplete(obj, ocNo, oNo){
			//form태그를 생성해서 전송
			//댓글내용, 댓글번호, 공지사항번호
			//1. form태그 생성
			/* 여기 수정해야함 */
			const memberNo = $("#memberNo");
			const form = $("<form style='display:none;' action='/updateOneOnOneComment.do? method='post'</form>");
			//2. input태그 2개숨김
			const ocNoInput = $("<input type='text' name='ocNo'>");
			ncNoInput.val(ocNo);
			const oNoInput = $("<input type='text' name='oNo'>");
			oNoInput.val(oNo);
			//3. textarea
			const ocContent = $(obj).parent().prev().clone();
			//4. form 태그에 input, textarea를 모두 포함
			form.append(ocNoInput).append(oNoInput).append(ocContent);
			//5. 생성된 form태그를 body태그에 추가
			$("body").append(form);
			//body의 맨마지막 현재페이지의 footer아래에 생성됨
			form.submit();
		}
		
		function deleteComment(obj, ocNo, oNo){
			if(confirm("댓글을 삭제하시겠습니까?")){
				/*여기 수정해야 함  */
				location.href="/deleteOneOnOneComment.do?ocNo="+ocNo+"&oNo="+oNo;
			}
		}
		
		
		
	</script>
	
</body>
</html>