<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.tbl tr{
		border: 1px solid #ccc;
	}

</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
		<div class="page-content">
			<div class="page-title">자주하는 질문</div>
			<form action="/faqWrite.do" method="post" enctype="multipart/form-data">
				<table class="tbl" id="faqWrite">
					<tr class="tr-1">
						<th class="td-3">제목
						<td colspan="5">
							<input type="text" name="faqTitle" class="input-form">
						</td>
					</tr>
					<tr class="tr-1">
						<th class="td-3">작성자
						<td>
							<%=m.getMemberId() %>
							<input type="hidden" name="faqWriter" value=<%=m.getMemberId() %>>
						<%--
							<%=m.getMemberId() %>
						 --%>
							<input type="hidden" name="faqWriter" value=<%=m.getMemberId() %>>
							<input type="hidden" name="fMemberNo" value=<%=m.getMemberNo() %>>
						</td>
						<th class="td-3">첨부파일</th>
						<td><input type="file" name="upfile"></td>
						<th class="td-3">category</th>
						<td>
							<select name="fFlag">
								<option value="1">회원관리</option>
								<option value="2">결제/혜택/이벤트</option>
								<option value="3">도서관련 문의</option>
								<option value="4">뷰어 문의</option>
								<option value="5">기타 문의</option>
							</select>
						</td>
					</tr>
					<tr class="tr-1">
						<td colspan="6" style="text-align:left">
							<textarea id="faqContent" name="faqContent" class="input-form"></textarea>
						</td>
					</tr>
					<tr class="tr-1">
						<td colspan="6">
							<button type="submit" class="btn bc200 bs4">FAQ 작성</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	<script>
		
	</script>
		
		<script>
		$("#faqContent").summernote({
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