<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
	<div class="page-content">
		<div class="page-title">1:1 문의글 작성</div>
		<form action="/oneOnOneWrite.do" method="post" enctype="multipart/form-data">
		<!-- 
			첨부파일이 존재하는 경우 
			일단 양이 많기때문에 method = post
			파일이 포함되는 경우 enctype="multipart/form-data
			이 양식을 반드시 지켜줘야한다. 
		-->
			<table class="tbl" id="oneOnOneWrite">
				<tr class="tr-1">
					<th class="td-3">제목
					<td colspan="3">
						<input type="text" name="oneOnOneTitle" class="input-form">
					</td>
				</tr>
				<tr class="tr-1">
					<th class="td-3">작성자
					<td>
						<%=m.getMemberId() %>
						<input type="hidden" name="oneOnOneWriter" value="<%=m.getMemberId() %>">
					</td>
					<th class="td-3">첨부파일</th>
					<td><input type="file" name="upfile"></td>
				</tr>
				<tr class="tr-1">
					<td colspan="4" style="text-align:left">
						<textarea id="oneOnOneContent" name="oneOnOneContent" class="input-form"></textarea>
					</td>
				</tr>
				<tr class="tr-1">
					<td colspan="4">
						<button type="submit" class="btn bc200 bs4">1:1 문의사항 작성 완료</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script>
	$("#oneOnOneContent").summernote({
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