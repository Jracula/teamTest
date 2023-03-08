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
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
	<div class="page-content">
		<div class="page-title">문의글 작성</div>
		<form action="qnaWrite.do" method="post" enctype="multipart/form-data">
			<table class="tbl" id="boardWrite">
				<tr class="tr-1">
					<th class="td-3">제목</th>
					<td colspan="5">
						<input type="text" name="qTitle" class="input-form">
					</td>
				</tr>
				<tr class="tr-1">
					<th class="td-3">작성자</th>
					<td>
						<%=m.getMemberId() %>
						<input type="hidden" name="qWriter" value="<%=m.getMemberId()%>">				
					</td>					
					<td>
						<input type="hidden" name="qMemberNo" value="<%=m.getMemberNo()%>">
					</td>					
					<th class="td-3">첨부파일</th>
					<td>
						<input type="file" name="upfile">
					</td>
				</tr>
				<tr class="tr-3">
					<td class="td-1" colspan="5">
						<textarea name="qContent" class="input-form"></textarea>
					</td>
				</tr>
			</table>
			<button type="submit" class="btn bc1 bs4">문의글 작성하기</button>
		</form>
	</div>
	<script>
		$("#noticeContent").summernote({
			height : 400,
			lang : "ko-KR",
			callbacks : {
				onImageUpload : function(files){
					uploadImage(files[0], this);
				}
			}
			
		});
		
		function uploadImage(file, editor){
			//ajax를 통해서 서버에 이미지를 업로드
			//업로드 된 이미지의 경로를 받아오는 역할
			//받아온 이후 -> editor에 이미지 경로를 전달해서 화면에 표현
			
			//<form method="post" enctype="multipart/form-data"> 태그의 역할
			const form = new FormData();
			/*
			<form>
				<input type="file" name="file">
			</form>
			*/
			form.append("file",file);
			
			$.ajax({
				url : "/uploadImage.do",
				type : "post", // 파일 업로드를 하려면 반드시 type : "post" 줘야함
				data : form,
				processData : false, // 데이터 문자열로 보내면 안돼!
				contentType : false, // 파일 전송할때 enctype 기본값 제거해야돼!
				success : function(data){
					$(editor).summernote("insertImage",data);
				}
				
			});
			//processData : 전송하는 데이터를 기본적으로 문자열로 전송하게 세팅
				//-> 파일 전송을 위해서 기본값인 문자열 전송을 제거
			//contentType : enctype="application/x-www.form-urlencoded;charset=UTF-8" -> 기본값
				//-> 파일 전송을 위해서 enctype 기본값을 제거
			
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>