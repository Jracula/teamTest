<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#noticeWrite td, #noticeWrite th{
		border : 1px solid #ccc;
	}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
		<div class="page-content">
			<div class="page-title">공지사항 작성</div>
			<!-- ★ 첨부파일이 있는 경우 메소드를 post로 설정 / 첨부파일이 있는 경우 enctype="multipart/form-data"를 필수로 작성해줘야함 -->
			<form action="/noticeWrite.do" method="post" enctype="multipart/form-data">
			
				<table class="tbl" id="noticeWrite">
				
					<tr class="tr-1">
						<th class="td-3">제목</th>
						<td colspan="3"> <!-- 테이블 한줄이 컬럼 4개 -->
							<input type="text" name="noticeTitle" class="input-form">
						</td>
					</tr>
					
					<tr class="tr-1">
						<th class="td-3">작성자</th>
						<td>
							<%=m.getMemberId() %> <!-- 웹페이지 상에 보일 내용 -->
							<input type="hidden" name="noticeWriter" value="<%=m.getMemberId()%>"> <!-- 전송내용 -->
						</td>
						<th class="td-3">첨부파일</th>
						<td><input type="file" name="upfile"></td>
					</tr>
					
					<tr class="tr-1">
						<td colspan="4">
							<textarea name="noticeContent" class="input-form"></textarea>
						</td>
					</tr>
					
					<tr class="tr-1">
						<td colspan="4">
							<button type="submit" class="btn bc1 bs4">공지사항작성</button>
						</td>
					</tr>
					
				</table>
			</form>
		</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>	
</body>
</html>