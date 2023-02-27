<%@page import="com.litbooks.book.vo.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    Book b = (Book)request.getAttribute("b");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=b.getBookTitle() %> - LITBOOKS</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="book-cover">책 표지</div>
		<div class="book-card-wrap">
			<p class="genre-category">장르 ＞ <%=b.getBookGenre() %></p>
			<h1><%=b.getBookTitle() %></h1>
			<div class="avg-score">평점</div>
			<p>저자 - <%=b.getWriter() %></p>
			<p>출판사 - <%=b.getPublisher() %></p>
			<p>정가 - <%=b.getBookPrice() %>원</p>
			<%int newPrice = b.getBookPrice() * (100 - b.getDiscount()) / 100; %>
			<p>판매가 - <%=newPrice %>원</p>
			<a class="btn bc9" href="#">카트에 담기</a>
			<a class="btn bc9" href="#">구매하기</a>
		</div>
		<div>
			<div>
				<p>작품 소개</p>
				<div><%=b.getBookIntro() %></div>
				<div>더 보기 버튼</div> <%--누르면 부모 요소의 height를 넓힘--%>
			</div>
		</div>
		<%-- 댓글 후기 노출 영역 --%>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>