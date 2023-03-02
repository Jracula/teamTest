<%@page import="com.litbooks.book.vo.Book"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    ArrayList<Book> books = (ArrayList<Book>)request.getAttribute("books");
    ArrayList<String> genreList = (ArrayList<String>)request.getAttribute("genreList");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>책 검색 결과</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="searchEngine">
			<form action="/bookSearchInDetail.do" method="post">
				<div>책제목 : <input type="text" name="searchTitle" placeholder="검색할 책 제목"></div>
				<div>저자 : <input type="text" name="searchWriter" placeholder="검색할 저자 이름"></div>
				<div>
			<%for(int i=0; i<genreList.size(); i++){%>
				<input type="checkbox" name="selectedGenre" id=<%="genre"+i%> value="<%=genreList.get(i) %>"><label for=<%="genre"+i%>><%=genreList.get(i) %></label> 
            <%} %>
            	<div><label><input type="checkbox" name="onSale" value="1">판매중지 제외</label></div>
				</div>
				<button type="submit">검색</button>
			</form>
		</div>
		<div class="result-wrap">
	<%if(books!=null){%>
		<%if(books.size()==0) {%>
			<div>조건을 만족하는 책이 없습니다.</div>
		<%}else{ %>
			<div class="book-wrap">
			<%for(int i=0; i<books.size(); i++){%>
			<%Book bs = books.get(i); %>
				<div style="float: left;">
				<%if (bs.getBookImage()!=null){%>
					<img src="/upload/book/cover-image/<%=bs.getBookImage() %>" width=100px>
				<%}else{ %>
					<img src="/upload/book/cover-image/00000000.jpg" width=100px>
				<%} %>
				</div>
				<div style="float: left;">
					<a href="/bookDetail.do?bookNo=<%=bs.getBookNo()%>"><p><%=bs.getBookTitle() %></p>
					<p><%=bs.getWriter() %> | <%=bs.getPublisher() %></p></a>
			<%-- 판매중 상태를 확인 후 가격 노출 --%>
				<%if (bs.getOnSale()==1) {%>
					<p><%=bs.getBookPrice() %>원</p>
			<%-- 할인율이 0%가 아닐 경우, 할인된 판매가를 노출 --%>
				<%int newPrice = bs.getBookPrice() * (100 - bs.getDiscount()) / 100; %>
				<% if (bs.getDiscount()!=0) {%>
				<p>판매가 - <span style="color: green;"><%=newPrice %></span>원</p>
				<%}else { %>
				<p>&nbsp;<span style="display:none;"><%=newPrice %></span></p>	<%-- 할인율이 0%면 display none처리 --%>
				<%} %>
				<%}else if (bs.getOnSale()==0) {%>
					<p style="color: gray;">판매중지된 상품입니다.<span style="display:none;">0</span></p>
					<p>&nbsp;</p>
				<%} %>
				</div>
			<%} %>
			</div>
		<%} %>
	<%} %>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>