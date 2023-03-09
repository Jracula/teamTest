<%@page import="com.litbooks.qna.model.vo.Qna"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Qna q = (Qna)request.getAttribute("q");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.tbl{
		margin: 10px auto;
		border: 1px solid #eeeeee;
	}
	.tr-1 {
		border-bottom: 1px solid #eeeeee;
	}


</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
		<div class="page-content">
			<div class="page-title">문의사항수정</div>
			<form action="/qnaUpdate.do" method="post" enctype="multipart/form-data">
				<table class="tbl" id="qnaUpdateFrm">
					<tr class="tr-1">
						<th class="td-3">제목</th>
						<td colspan="4">
							<input type="text" name="qnaTitle" class="input-form" value="<%=q.getqTitle() %>">
							<input type="hidden" name="status" value="stay">
							<input type="hidden" name="qnaNo" value="<%=q.getqNo() %>">
						</td>
					</tr>
					<tr class="tr-1">
						<th class="td-3">첨부파일</th>
						<td>
	
							<%if(q.getFilepath() != null) {%>
								<!-- 삭제하기를 누르면 한번에 둘다 안보이게 하기위해 같은 class를 주는것 -->
								<img src="/img/file.png" width="16px" class="delFile">
								<span class="delFile"><%=q.getFileName()%></span>
								<button type="button" class="btn bc200 delFile">삭제</button>
								<input type="file" name="upfile" style="display:none;">
								<input type="hidden" name="oldFilename" value="<%=q.getFileName() %>">
								<input type="hidden" name="oldFilepath" value="<%=q.getFilepath() %>">
							<%}else {%>
								<input type="file" name="upfile">
							<%} %>
						
						</td>
					</tr>
					<tr class="tr-1">
						<th class="td-3">내용</th>
						<th colspan="2">
							<!-- textarea에서는 엔터가 먹으므로 엔터가 업는 NoticeContent를 사용 -->
							<textarea style="width:1080px" class="input-form" id="qnaContent" name="qnaContent"><%=q.getqContent() %></textarea>
						</th>
					</tr>
					<tr class="tr-1">
						<th colspan="2">	
							<button type="submit" class="btn bc200 bs4">수정완료</button>
						</th>
					</tr>
				</table>
			</form>
		</div>
	<script>
	
	$("button.delFile").on("click", function(){
		$(".delFile").hide();
		$(this).next().show();
		//첨부파일이 있는 경우에 삭제를 누른 경우에만 stay -> delete
		$("[name=status]").val("delete");
	});
	
	$("#qnaContent").summernote({
		height : 400,
		lang : "ko-KR",
		callbacks : {
			onImageUpload : function(files) {
				uploadImage(files[0], this);
			}
		}	
	});
	
	function uploadImage(file, editor) {
		const form = new FormData();
		form.append("file", file);
		$.ajax({
			url : "/uploadImage.do",
			type : "POST",
			data : form,
			processData : false,
			contentType : false,
			success : function(data) {
				$(editor).summernote("insertImage", data);
			}
		});
	}	
	
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>