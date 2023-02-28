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
		<div class="page-title">글작성</div>
		<form action="boardWrite.do" method="post" enctype="multipart/form-data">
			<table class="tbl" id="boardWrite">
				<tr class="tr-1">
					<th class="td-2">제목</th>
					<td colspan="3">
						<input type="text" name="boardTitle" class="input-form">
					</td>
				</tr>
				<tr class="tr-1">
					<th class="td-2">작성자</th>
					<td>
						<input type="text" name="writeName" class="input-form">
					</td>
					<%--
					<td>
						<%=m.getMemberId() %>
						<input type="hidden" name="boardWriter" value="<%=m.getMemberId()%>">
					</td>					
					 --%>
					<th class="td-2">첨부파일</th>
					<td>
						<input type="file" name="upfile">
					</td>
				</tr>
				<tr class="tr-3">
					<td class="td-1" colspan="4">
						<textarea name="boardContent" class="input-form"></textarea>
					</td>
				</tr>
			</table>
			<button type="submit" class="btn bc1 bs4">작성하기</button>
		</form>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>