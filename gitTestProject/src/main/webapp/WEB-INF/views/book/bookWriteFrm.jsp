<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
					<td colspan="5">
						<input type="file" name="imagefile" accept=".jpg,.jpeg,.gif,.png,.webp">
					</td>
				</tr>
				<tr class="tr-1">
					<th class="td-3">권(화) 수</th>
					<td>
						<input type="number" name="bookEpi" class="input-form" min="1" max="9999" placeholder="몇권째?" required>
					</td>
				<%-- bookEpi가 1이 아니면 활성화되도록 --%>
					<th class="td-3">첫권(화)</th>
					<td>
						<input type="number" name="book1st" placeholder="1권의 bookNo" class="input-form">
					</td>
					<th class="td-3" rowspan="2">무료 감상</th>
					<td rowspan="2">
						<input type="checkbox" name="nonFee" class="input-form">
					</td>
				</tr>
				<tr class="tr-1">
					<th class="td-3">정가(원)</th>
					<td>
						<input type="number" name="bookPrice" class="input-form" step="100" min="0" placeholder="정가" required>
					</td>
					<th class="td-3">할인율(%)</th>
					<td>
						<input type="number" name="discount" value="0" min="0" max="100" class="input-form">
					</td>
				</tr>
				<tr class="tr-1">
					<th class="td-3">장르</th>
					<td>
            			<select name="bookGenre">
                			<option value="">(없음)</option>
                			<option value="BL">BL</option>
                			<option value="GL">GL</option>
                			<option value="공포">공포</option>
                			<option value="국내순정">국내순정</option>
                			<option value="드라마">드라마</option>
                			<option value="무협">무협</option>
                			<option value="성인">성인</option>
                			<option value="스포츠">스포츠</option>
            			</select>
					</td>
					<th class="td-3">작가명</th>
					<td>
						<input type="text" name="writer" placeholder="비울 경우 '작자미상'" class="input-form">
					</td>
					<th class="td-3">출판사</th>
					<td>
						<input type="text" name="publisher" placeholder="비울 경우 '출판사불명'" class="input-form">
					</td>
				</tr>
				<tr class="tr-1">
					<td colspan="6">
						<textarea name="bookIntro" placeholder="작품 소개글. 한글 최대 1000자" class="input-form"></textarea>
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
</body>
</html>