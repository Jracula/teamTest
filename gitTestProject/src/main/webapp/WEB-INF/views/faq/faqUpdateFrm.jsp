<%@page import="com.litbooks.faq.model.vo.Faq"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Faq f = (Faq)request.getAttribute("f");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
		<div class="page-content">
		<div class="page-title">문의사항수정</div>
		<form action="/faqUpdate.do" method="post" enctype="multipart/form-data">
			<table class="tbl" id="faqUpdateFrm">
				<tr class="tr-1">
					<th class="td-3">제목</th>
					<td>
						<input type="text" name="faqTitle" class="input-form" value="<%=f.getfTitle() %>">
						<input type="hidden" name="status" value="stay">
						<input type="hidden" name="faqNo" value="<%=f.getfNo() %>">
					</td>
				</tr>
				<tr class="tr-1">
					<th class="td-3">첨부파일</th>
					<td>
						<!-- 
						<input type="file" name="upfile" value="<%=f.getFilepath() %>">
						input태그에 value를 작성하면 값이 보여야하지만 첨부파일은 보이지않음
						이유 : 일단 getFilePath()는 문자열형식 + 서버에있는 파일경로이므로 클라이언트가 알아봤자 의미가없다
						      보안의 문제도 있기 때문에 input type='file'에서는 value를 줄 수 없다 
						-->
						<!-- 
						-- 파일 경우의 수
						1. 기존 첨부파일이 있는 경우
						 -> 수정을 하지 않는 경우(oldFileName, oldFilePath로 update)
						 -> 첨부파일을 삭제만 한 경우(null로 update)
						 -> 기존파일을 삭제하고 새 파일을 첨부한경우(새 filename, 새 filepath로 update)
						
						2. 기존 첨부파일이 없는 경우
						 -> 수정을 하지 않는 경우(이미 null이지만 null로 update )
						 -> 새 첨부파일을 추가한 경우(새 filename, 새 filepath로 update )
						
						위 경우의 수를 만족할수있게 세팅을 해줘야한다.
						 -->
						<%if(f.getFilepath() != null) {%>
							<!-- 삭제하기를 누르면 한번에 둘다 안보이게 하기위해 같은 class를 주는것 -->
							<img src="/img/file.png" width="16px" class="delFile">
							<span class="delFile"><%=f.getFilename()%></span>
							<button type="button" class="btn bc1 delFile">삭제</button>
							<input type="file" name="upfile" style="display:none;">
							<input type="hidden" name="oldFilename" value="<%=f.getFilename() %>">
							<input type="hidden" name="oldFilepath" value="<%=f.getFilepath() %>">
						<%}else {%>
							<input type="file" name="upfile">
						<%} %>
					
					</td>
				</tr>
				<tr class="tr-1">
					<th class="td-3">내용</th>
					<td>
						<!-- textarea에서는 엔터가 먹으므로 엔터가 업는 NoticeContent를 사용 -->
						<textarea class="input-form" name="faqContent"><%=f.getfContent() %></textarea>
					</td>
				</tr>
				<tr class="tr-1">
					<th colspan="2">	
						<button type="submit" class="btn bc4 bs4">수정완료</button>
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
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>