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

	<%@include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>