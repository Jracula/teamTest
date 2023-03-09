<%@page import="com.litbooks.book.vo.Book"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    ArrayList<Book> books = (ArrayList<Book>)request.getAttribute("books");
    String pageNavi = (String)request.getAttribute("pageNavi");
    int start = (int)request.getAttribute("start");
    int totalCount = (int)request.getAttribute("totalCount");
    ArrayList<String> genreList = (ArrayList<String>)request.getAttribute("genreList");
    //이전 검색 조건들 ↓
    String recievedTitle = (String)request.getAttribute("recievedTitle");
    String recievedWriter = (String)request.getAttribute("recievedWriter");
    String[] recievedGenre = (String[])request.getAttribute("recievedGenre");
    int recievedOnSale = (int)request.getAttribute("recievedOnSale");
    //이전 검색 조건들 ↑
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
<style>
.coverImage:hover {
  cursor: zoom-in;
}
.searchEngine {
	background-color: #EEEEEE;
	padding: 20px;
	border-radius: 14px; 
}
.searchEngine>form>* {
	margin-top: 12px;
	margin-bottom: 12px;
}
.text-bar {
	border: none;
	padding:5px;
	text-indent: 10px;
	width: 93%;
}
.genre-options>label {
	margin-left: 3px;
	margin-right: 8px;
}
.result-wrap>h3 {
	line-height: 400%;
}
.result-wrap>div {
	margin-top: 5px;
	margin-bottom: 5px;
}
.selectBook{
	float: left;
	width: 260px;
	margin-left: 8px;
	padding:7px;
	cursor: pointer;
}
.selectBook:hover {
	background-color: #E0F0FF;
}
</style>
</head>
	<div class="page-content">
		<div class="searchEngine">
			<form action="/book1stSearchInDetail.do" method="post" onsubmit="return checkKeyword();">
				<div><div style="display:inline-block; width:50px;">책제목</div>　<input class="text-bar" type="text" name="searchTitle" value="<%=recievedTitle %>" placeholder="검색할 책 제목"></div>
				<div><div style="display:inline-block; width:50px;">저　자</div>　<input class="text-bar" type="text" name="searchWriter" value="<%=recievedWriter %>" placeholder="검색할 저자 이름"></div>
				<div class="genre-options">
			<%for(int i=0; i<genreList.size(); i++){%>
				<%int check=0; %>
				<%for(int j=0; j<recievedGenre.length; j++){ %>
					<%if(genreList.get(i).equals(recievedGenre[j])){ %>
						<%check++; %>
					<%} %>
				<%} %>
				<%if(check>0){ %>
					<input type="checkbox" name="selectedGenre" id=<%="genre"+i%> value="<%=genreList.get(i) %>" checked="true"><label for=<%="genre"+i%>><%=genreList.get(i) %></label>
				<%}else if(check==0){ %>
					<input type="checkbox" name="selectedGenre" id=<%="genre"+i%> value="<%=genreList.get(i) %>"><label for=<%="genre"+i%>><%=genreList.get(i) %></label>
				<%} %>
            <%} %>
            	</div>
            	<div><label>
            	<%if(recievedOnSale==1){ %>
            		<input type="checkbox" name="onSale" value="1" checked="true">
            	<%}else if(recievedOnSale==0){ %>
            		<input type="checkbox" name="onSale" value="1">
            	<%} %>
            		판매중지 제외</label>
				</div>
				<input type="hidden" name="reqPage" value="1">
				<button type="submit" style="background-color: #0B6DB7; border: none; border-radius: 5px; cursor: pointer;"><span style="color: white; font-size:16px; padding:16px;">검색</span></button>
			</form>
		</div>

<!-- 이전 검색 조건을 저장하는 form으로서, 페이지 이동 버튼 동작을 위해 작성됨 -->
		<div class="searchEngine" style="display:none;">
			<form action="/book1stSearchInDetail.do" method="post" onsubmit="return checkKeyword();">
				<input type="text" name="searchTitle" value="<%=recievedTitle %>">
				<input type="text" name="searchWriter" value="<%=recievedWriter %>">
				<div>
			<%for(int i=0; i<genreList.size(); i++){%>
				<%int check=0; %>
				<%for(int j=0; j<recievedGenre.length; j++){ %>
					<%if(genreList.get(i).equals(recievedGenre[j])){ %>
						<%check++; %>
					<%} %>
				<%} %>
				<%if(check>0){ %>
				<input type="checkbox" name="selectedGenre" id=<%="genre"+i%> value="<%=genreList.get(i) %>" checked="true">
				<%}else if(check==0){ %>
				<input type="checkbox" name="selectedGenre" id=<%="genre"+i%> value="<%=genreList.get(i) %>">
				<%} %>
            <%} %>
            		<div>
            	<%if(recievedOnSale==1){ %>
            		<input type="checkbox" name="onSale" value="1" checked="true">
            	<%}else if(recievedOnSale==0){ %>
            		<input type="checkbox" name="onSale" value="1">
            	<%} %>
            		</div>
				</div>
				<input name="reqPage">
				<button type="submit" id="submitButton">보이지 않는 검색 버튼</button>
			</form>
		</div>
<!-- 숨겨진 검색 form 끝 -->
		
		<div class="result-wrap">
	<%if(books!=null){%>
		<%if(books.size()==0) {%>
			<h3>조건을 만족하는 책이 없습니다.</h3>
		<%}else{ %>
			<h3>총 <%=totalCount %>건의 검색 결과가 있습니다.</h3>
			<%for(int i=0; i<books.size(); i++){%>
			<div class="book-wrap" style="float: left; width: 400px; height: 160px;">
			<%Book bs = books.get(i); %>
				<div style="float: left;">
				<%if (bs.getBookImage()!=null){%>
					<img class="coverImage" src="/upload/book/cover-image/<%=bs.getBookImage() %>" width=100px>
				<%}else{ %>
					<img src="/upload/book/cover-image/00000000.jpg" width=100px>
				<%} %>
				</div>
				<div class="selectBook" type="button">
					<p style="display:none;"><%=bs.getBookNo() %></p>
					<p><%=bs.getBookTitle() %></p>
					<p><%=bs.getBookGenre() %></p>
					<p><span><%=bs.getWriter() %></span> | <span><%=bs.getPublisher() %></span></p>
					<p style="display: none;"><%=bs.getBookPrice() %></p>
			<%-- 판매중 상태를 확인 후 가격 노출 --%>
					<%if (bs.getOnSale()==1) {%>
			<%-- 할인율이 0%가 아닐 경우, 할인된 판매가를 노출 --%>
					<%int newPrice = bs.getBookPrice() * (100 - bs.getDiscount()) / 100; %>
					<% if (bs.getDiscount()!=0) {%>
					<p><span  style="text-decoration: line-through;"><%=bs.getBookPrice() %>원</span> → <span style="color: green;"><%=newPrice %></span>원</p>
						<p>&nbsp;</p>
					<%}else { %>
					<p><%=bs.getBookPrice() %>원</p>
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
		<div id="pageNavi" style="clear: both;"><%=pageNavi %></div>
	</div>
	<script>
	function checkKeyword() {	//검색폼에서 책제목 또는 저자에 공백을 제외하고 2자 이상으로 검색 요청
		const keywordReg = /\S{2,}/;
		const keyword1 = $("[name=searchTitle]").val();
		const keyword2 = $("[name=searchWriter]").val();
		const check1 = keywordReg.test(keyword1);
		const check2 = keywordReg.test(keyword2);
		if (!check1 && !check2) {
			alert("책제목 또는 저자를 2자 이상의 검색어로 입력해주십시오.");
			return false;
		}
		return true;
	}
	$(".page-item").on("click", function(){
		const reqPage = $(this).children().first().text();
		$("[name=reqPage]").val(reqPage);
		$("#submitButton").click();
	});
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

	// 커머이미지 크게 보기
	$(".coverImage").on("click", function(){
		const imgUrl = $(this).attr("src");
		window.open(imgUrl, "reading", "toolbar=yes, location=yes, directories=no, status=no, menubar=yes, scrollbars=yes, resizable=no, copyhistory=yes");
	});
	</script>
</body>
</html>