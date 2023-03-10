<%@page import="com.litbooks.book.vo.Book"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    Book b = (Book)request.getAttribute("b");
    ArrayList<Book> seriesList = (ArrayList<Book>)request.getAttribute("seriesList");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=b.getBookTitle() %> - LITBOOKS</title>
 <link rel="stylesheet" href="/css/recomm.css" />
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="book-cover">
		<%if (b.getBookImage()!=null){%>
			<img src="/upload/book/cover-image/<%=b.getBookImage() %>" width=400px>
		<%}else{ %>
			<img src="/upload/book/cover-image/00000000.jpg" width=400px>
		<%} %>
		</div>
		<div class="book-card-wrap">
			<p class="genre-category">장르 ＞ <%=b.getBookGenre() %></p>
			<h1><%=b.getBookTitle() %></h1>
			<div class="avg-score">평점</div>
			<p>저자 - <%=b.getWriter() %></p>
			<p>출판사 - <%=b.getPublisher() %></p>
		<%-- 판매중 상태를 확인 후 가격 노출 --%>
		<%if (b.getOnSale()==1) {%>
			<p>정가 - <%=b.getBookPrice() %>원</p>
		<%-- 할인율이 0%가 아닐 경우, 할인된 판매가를 노출 --%>
			<% if (b.getDiscount()!=0) {%>
			<%int newPrice = b.getBookPrice() * (100 - b.getDiscount()) / 100; %>
			<p style="color: green;">판매가 - <%=newPrice %>원</p>
			<%}else { %>
			<p>&nbsp;</p>	<!-- 할인가가 표시되지 않더라도 같은 높이를 유지하기 위해 같은 태그로 공백 줘야됨 -->
			<%} %>
		<%}else if (b.getOnSale()==0) {%>
			<p style="color: gray;">판매중지된 상품입니다.</p>
			<p>&nbsp;</p>
		<%} %>
		<!-- 아래 두 버튼들은 로그인했을 때만 나오도록 표시하던가, 아니면 눌렀을 때 로그인하라는 경고창을 띄우던가 -->
			<a class="btn bc9" href="#">카트에 담기</a>
			<a class="btn bc9" href="#">구매하기</a>
		</div>
		<div>
		</div>
		<div>
			<div>
				<p>작품 소개</p>
				<div><%=b.getBookIntro() %></div>
				<a class="btn bc3">더 보기 버튼</a> <%--누르면 부모 요소의 height를 넓힘--%>
			</div>
		</div>
		
		<!-- seriesList의 크기가 1보다 크면, 같은 시리즈물을 표시하는 영역 -->
		<%if(seriesList.size()>1) {%>
		<div>
			<h3>이 책의 시리즈</h3>
		<%for(int i=0; i<seriesList.size(); i++){%>
			<%Book bs = seriesList.get(i); %>
			<div>
				<div>
			<%if (bs.getBookImage()!=null){%>
				<img src="/upload/book/cover-image/<%=bs.getBookImage() %>" width=80px>
			<%}else{ %>
				<img src="/upload/book/cover-image/00000000.jpg" width=80px>
			<%} %>
				</div>
				<div>
					<a href="/bookDetail.do?bookNo=<%=bs.getBookNo()%>"><p><%=bs.getBookTitle() %></p></a>
			<%-- 판매중 상태를 확인 후 가격 노출 --%>
				<%if (bs.getOnSale()==1) {%>
					<p><%=bs.getBookPrice() %>원</p>
				<%-- 할인율이 0%가 아닐 경우, 할인된 판매가를 노출 --%>
					<% if (bs.getDiscount()!=0) {%>
					<%int newPrices = bs.getBookPrice() * (100 - bs.getDiscount()) / 100; %>
					<p style="color: green;"><%=bs.getBookPrice() %>원</p>
					<%}else { %>
					<p>&nbsp;</p>	<!-- 할인가가 표시되지 않더라도 같은 높이를 유지하기 위해 같은 태그로 공백 줘야됨 -->
					<%} %>
				<%}else if (bs.getOnSale()==0) {%>
					<p style="color: gray;">판매중지된 상품입니다.</p>
					<p>&nbsp;</p>
				<%} %>
				</div>
			</div>
			<%}%>
		</div>
		<%}%>
		
		<!-- 댓글 후기 노출 영역 -->
		
		<h1 class="rating-top1">평점 & 후기</h1>
		<div class="star">  </div>
		<p class="rating-top2">평점을 입력해주세요</p>
		<div class="insert-box"> 
			<div class="insert-box top"></div>
			<div class="insert-box content"> <input type="text" name="Write"id="Write"class="Write"></div>
			
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>