<%@page import="com.litbooks.notice.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Notice n = (Notice)request.getAttribute("n");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
	
		<div class="page-content">
			<div class="page-title">공지사항수정</div>
			
			<form action="/noticeUpdate.do" method="post" enctype="multipart/form-data">
				<table class="tbl" id="noticeUpdateFrm">
					<tr class="tr-1">
						<th class="td-3">제목</th>
						<td>
							<input type="text" name="noticeTitle" class="input-form" value="<%=n.getNoticeTitle()%>">
							<input type="hidden" name="status" value="stay">
							<input type="hidden" name="noticeNo" value="<%=n.getNoticeNo()%>">
						</td>
					</tr>
					
					<tr class="tr-1">
						<th class="td-3">첨부파일</th>
						<td>
							<%if(n.getFilepath() != null) { %>
								<img src="/img/file.png" width="16px" class="delFile">
								<span class="delFile"><%=n.getFilename() %></span>
								<button type="button" class="btn bc1 delFile">삭제</button>
								<input type="file" name="upfile" style="display:none">
								<input type="hidden" name="oldFilename" value="<%=n.getFilename() %>">
								<input type="hidden" name="oldFilepath" value="<%=n.getFilepath() %>">
							<% } else { %>
								<input type="file" name="upfile">						
							<% } %>
						</td>
					</tr>
					
					<tr class="tr-1">
						<th class="td-3">내용</th>
						<td colspan="4" style="text-align: left;">
							<textarea class="input-form" name="noticeContent" id="noticeContent"><%=n.getNoticeContent()%></textarea>
						</td>
					</tr>
					
					<tr class="tr-1">
						<th colspan="2">
							<button type="submit" class="btn bc4 bs4">수정하기</button>
						</th>
					</tr>
					
				</table>
			</form>
		</div>
		
		<script>
			$("button.delFile").on("click", function() {
				$(".delFile").hide();
				$(this).next().show();
				$("[name=status]").val("delete");
			});
			
			$("#noticeContent").summernote({
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
		
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>