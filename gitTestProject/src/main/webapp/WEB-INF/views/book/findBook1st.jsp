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
    <!-- 구글 아이콘 -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- jquery -->
    <script src="/js/jquery-3.6.0.js"></script>
    <!-- 기본 CSS -->
    <link rel="stylesheet" href="/css/default.css" />
    <!-- 기본 js -->
    <script src="/js/default.js"></script>
</head>
	<div class="page-content">
		<div class="searchEngine">
			<form action="/book1stSearchInDetail.do" method="post" onsubmit="return checkKeyword();">
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
			<%for(int i=0; i<books.size(); i++){%>
			<div class="book-wrap" style="float: left; width: 400px; height: 160px;">
			<%Book bs = books.get(i); %>
				<div style="float: left;">
				<%if (bs.getBookImage()!=null){%>
					<img src="/upload/book/cover-image/<%=bs.getBookImage() %>" width=100px>
				<%}else{ %>
					<img src="/upload/book/cover-image/00000000.jpg" width=100px>
				<%} %>
				</div>
				<div class="selectBook" type="button" style="float: left; width: 300px; cursor: pointer;">
					<span style="display:none;"><%=bs.getBookNo() %></span>
					<p><%=bs.getBookTitle() %></p>
					<p><%=bs.getBookGenre() %></p>
					<p><span><%=bs.getWriter() %></span> | <span><%=bs.getPublisher() %></span></p>
			<%-- 판매중 상태를 확인 후 가격 노출 --%>
					<span style="display:none;"><%=bs.getBookPrice() %></span>
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
			</div>
			<%} %>
		<%} %>
	<%} %>
		</div>
	</div>
	<script>
	function checkKeyword() {
		const keyword1 = $("[name=searchTitle]").val();
		const keyword2 = $("[name=searchWriter]").val();
		if (!keyword1 && !keyword2) {
			alert("책제목 또는 저자 중 하나 이상은 입력 후 검색해주십시오.");
			return false;
		}
		return true;
	}
	$(".page-item").on("click", function(){
		const reqPage = $(this).children().first().text();
		$("[name=reqPage]").val(reqPage);
		$("#submitButton").click();
	});
	
	$(".selectBook").on("click",function(){	//클릭한 개체의 값들을 부모창으로 반환
		const bookNo = $(this).children().first().text();
		const bookPrice = $(this).children().eq(4).text();
		const bookGenre = $(this).children().eq(2).text();
		const writer = $(this).children().eq(3).children().first().text();
		const publisher = $(this).children().eq(3).children().eq(1).text();
		window.opener.document.getElementById("book1st").value = bookNo;
		window.opener.document.getElementById("bookPrice").value = bookPrice;
		window.opener.document.getElementById("bookGenre").value = bookGenre;
		window.opener.document.getElementById("writer").value = writer;
		window.opener.document.getElementById("publisher").value = publisher;
		window.close();
	});
	</script>
</body>
</html>