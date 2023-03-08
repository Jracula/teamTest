<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    ArrayList<String> genreList = (ArrayList<String>)request.getAttribute("genreList");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신규 도서 등록 - 관리자 모드</title>
<style>
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="page-title">신규 도서 등록</div>
		<form action="/bookWrite.do" method="post" enctype="multipart/form-data">
			<table class="tbl" id="bookWrite">
				<tr class="tr-1">
					<th class="td-3">책제목</th>
					<td colspan="5">
						<input type="text" name="bookTitle" class="input-form" placeholder="한글 최대 100자" required>
					</td>
				</tr>
				<tr class="tr-1">
					<th class="td-3">책표지 이미지</th>
					<td colspan="5" style="text-align: left;">
						<input type="file" name="imagefile" accept=".jpg,.jpeg,.gif,.png,.webp">
					</td>
				</tr>
				<tr class="tr-1">
					<th class="td-3">권(화) 수</th>
					<td>
						<input type="number" name="bookEpi" id="bookEpi" class="input-form" min="1" max="9999" placeholder="몇권째?" required>
					</td>
				<%-- bookEpi가 1이 아니면 활성화되도록 --%>
					<th class="td-3">첫권(화)</th>
					<td>
						<input type="number" name="book1st" id="book1st" min="0" placeholder="단권 또는 1권은 지정 불필요" class="input-form" readonly>
						<button type="button" id="findSimilar" style="font-size: 20px; display: none;">찾아보기</button>
					</td>
					<th class="td-3" rowspan="2">무료 감상</th>
					<td rowspan="2">
						<input type="checkbox" name="nonFee" value="1" class="input-form" style="width: 25px; height: 25px; margin-left: 20px;">
					</td>
				</tr>
				<tr class="tr-1">
					<th class="td-3">정가(원)</th>
					<td>
						<input type="number" name="bookPrice" id="bookPrice" class="input-form" step="100" min="0" placeholder="정가" required>
					</td>
					<th class="td-3">할인율(%)</th>
					<td>
						<input type="number" name="discount" value="0" min="0" max="100" class="input-form">
					</td>
				</tr>
				<tr class="tr-1">
					<th class="td-3">장르</th>
					<td>
            			<select name="bookGenre" id="bookGenre">
                			<option value="">(없음)</option>	<!-- 장르 미선택용. value로 null을 반환함 -->
            			<%for(int i=0; i<genreList.size(); i++){%>
                			<option value="<%=genreList.get(i) %>"><%=genreList.get(i) %></option>
                		<%} %>
            			</select>
					</td>
					<th class="td-3">작가명</th>
					<td>
						<input type="text" name="writer" id="writer" placeholder="비울 경우 '작자미상'" class="input-form">
					</td>
					<th class="td-3">출판사</th>
					<td>
						<input type="text" name="publisher" id="publisher" placeholder="비울 경우 '출판사불명'" class="input-form">
					</td>
				</tr>
				<tr class="tr-1">
					<td colspan="6">
						<textarea name="bookIntro" placeholder="작품 소개글. 한글 최대 1000자" class="input-form" style="width:100%;"></textarea>
					</td>
				</tr>
				<tr class="tr-1">
					<td colspan="6">
						<button type="submit" class="btn bc1 bs4">신규 도서 등록</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
	<script>
	//무료 감상 체크 전환에 따라 정가와 할인율의 서식 변경
	$("[name=nonFee]").on("change", function(){
		if($(this).is(":checked")){
			$("[name=bookPrice]").css({'text-decoration' : 'line-through'});
			$("[name=discount]").css("color","#BCBCBC");
		} else if ( !$(this).is(":checked") ){
			$("[name=bookPrice]").css({'text-decoration' : ''});
			$("[name=discount]").css("color","#252A34");
		}		
	});
	
	//책 권수에 따라 1권인 책의 bookNo 지정 여부 등 전환
	$("#bookEpi").on("keyup change", function(){
		if($(this).val()>1){
			$("#book1st").attr("placeholder", "1권인 책의 bookNo를 지정");
			$("#book1st").removeAttr("readonly");
			$("#book1st").attr("required", true);
			$("#findSimilar").show();
		}else if($(this).val()==1){
			$("#book1st").val("");
			$("#book1st").attr("placeholder", "단권 또는 1권은 지정 불필요");
			$("#book1st").removeAttr("required");
			$("#book1st").attr("readonly", true);
			$("#findSimilar").hide();
		}
	});

	//1권의 bookNo를 찾기 위해 자식창 열기
	$("#findSimilar").on("click",function(){
		window.open("/book1stSearchInDetail.do", "findBook1st", "toolbar=yes, location=yes, directories=no, status=no, menubar=yes, scrollbars=yes, resizable=no, copyhistory=yes, width=1240, height=680");
	});
	</script>
</body>
</html>