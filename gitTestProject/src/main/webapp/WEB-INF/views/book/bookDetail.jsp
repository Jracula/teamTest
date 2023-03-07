<%@page import="java.util.zip.CRC32"%>
<%@page import="com.litbooks.book.vo.BookView"%>
<%@page import="com.litbooks.book.vo.Recomm"%>
<%@page import="com.litbooks.book.vo.Book"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    Book b = (Book)request.getAttribute("b");
    ArrayList<Book> seriesList = (ArrayList<Book>)request.getAttribute("seriesList");
    
    %>
<%
	Recomm rv = (Recomm)request.getAttribute("rv");
    ArrayList<Recomm> recommList
    =(ArrayList<Recomm>)request.getAttribute("recommList");
    ArrayList<Recomm> rerecommList
    =(ArrayList<Recomm>)request.getAttribute("rerecommList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=b.getBookTitle() %> - LITBOOKS</title>
<link rel="stylesheet" href="/css/recomm.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<style>
.material-symbols-outlined {
	font-variation-settings: 'FILL' 0, 'wght' 400, 'GRAD' 0, 'opsz' 48
}
.material-symbols-outlined-star {
	font-variation-settings: 'FILL' 0, 'wght' 400, 'GRAD' 0, 'opsz' 48
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<div class="page-content">
		<p id="bookNo" style="display: none;"><%=b.getBookNo() %></p>
		<div class="book-cover">
			<%if (b.getBookImage()!=null){%>
			<img src="/upload/book/cover-image/<%=b.getBookImage() %>"
				width=400px>
			<%}else{ %>
			<img src="/upload/book/cover-image/00000000.jpg" width=400px>
			<%} %>
		</div>
		<div class="book-card-wrap">
			<p class="genre-category">
				장르 ＞
				<%=b.getBookGenre() %></p>
			<h1><%=b.getBookTitle() %></h1>
			<div class="avg-score">평점</div>
			<p>
				저자 -
				<%=b.getWriter() %></p>
			<p>
				출판사 -
				<%=b.getPublisher() %></p>
			<%-- 판매중 상태를 확인 후 가격 노출 --%>
			<%if (b.getOnSale()==1) {%>
			<p>
				정가 -
				<%=b.getBookPrice() %>원
			</p>
			<%-- 할인율이 0%가 아닐 경우, 할인된 판매가를 노출 --%>
			<%int newPrice = b.getBookPrice() * (100 - b.getDiscount()) / 100; %>
			<% if (b.getDiscount()!=0) {%>
			<p>
				판매가 - <span id="realPrice" style="color: green;"><%=newPrice %></span>원
			</p>
			<%}else { %>
			<p>
				&nbsp;<span id="realPrice" style="display: none;"><%=newPrice %></span>
			</p>
			<%-- 할인율이 0%면 display none처리 --%>
			<%} %>
			<%}else if (b.getOnSale()==0) {%>
			<p style="color: gray;">
				판매중지된 상품입니다.<span id="realPrice" style="display: none;">0</span>
			</p>
			<p>&nbsp;</p>
			<%} %>
			<!-- 아래 두 버튼들은 로그인했을 때만 나오도록 표시하던가, 아니면 눌렀을 때 로그인하라는 경고창을 띄우던가 -->
			<button class="btn bc9" id="addCart">장바구니에 담기</button>
			<a class="btn bc9" href="#">구매하기</a>
		</div>
		<div></div>
		<div>
			<div>
				<p>작품 소개</p>
				<div>
					<%if (b.getBookIntro()!=null){%>
					<%=b.getBookIntro() %>
					<%}else{ %>
					작품 소개가 없습니다.
					<%} %>
				</div>
				<a class="btn bc3">더 보기 버튼</a>
				<%--누르면 부모 요소의 height를 넓힘--%>
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
					<img src="/upload/book/cover-image/<%=bs.getBookImage() %>"
						width=80px>
					<%}else{ %>
					<img src="/upload/book/cover-image/00000000.jpg" width=80px>
					<%} %>
				</div>
				<div>
					<a href="/bookDetail.do?bookNo=<%=bs.getBookNo()%>"><p><%=bs.getBookTitle() %></p></a>
					<%-- 판매중 상태를 확인 후 가격 노출 --%>
					<%if (bs.getOnSale()==1) {%>
					<p><%=bs.getBookPrice() %>원
					</p>
					<%-- 할인율이 0%가 아닐 경우, 할인된 판매가를 노출 --%>
					<%int newPrice = bs.getBookPrice() * (100 - bs.getDiscount()) / 100; %>
					<% if (bs.getDiscount()!=0) {%>
					<p>
						판매가 - <span style="color: green;"><%=newPrice %></span>원
					</p>
					<%}else { %>
					<p>
						&nbsp;<span style="display: none;"><%=newPrice %></span>
					</p>
					<%-- 할인율이 0%면 display none처리 --%>
					<%} %>
					<%}else if (bs.getOnSale()==0) {%>
					<p style="color: gray;">
						판매중지된 상품입니다.<span style="display: none;">0</span>
					</p>
					<p>&nbsp;</p>
					<%} %>
				</div>
			</div>
			<%}%>
		</div>
		<%}%>



		<!-- 댓글 후기 노출 영역 -->
		<div class="commentBox">

			
			<%-- 로그인 되어있는 경우에만 댓글 작성 화면을 띄움 --%>
			<%if (m != null) {%>
			<div class="inputCommentBox">
				<form action="/insertRecomm.do" method="post">
					<ul>
						<li>
							<input type="text" id="rating-wrapper" name="rating-wrapper" class="rating-wrapper" placeholder="평점좀 매겨 봐라" readonly>
						</li>
						<li class="star-wrap star-wrap1">
							<p>
								<span name="rating" id="star-result">0</span><span>점</span>
							</p>
							<p>
								<span class="material-symbols-outlined star">star</span> <span
									class="material-symbols-outlined star">star</span> <span
									class="material-symbols-outlined star">star</span> <span
									class="material-symbols-outlined star">star</span> <span
									class="material-symbols-outlined star">star</span>
							</p>
							<input type="hidden" name="rating" value="<%=b.getBookNo()%>">
						</li>
						<li>
							<input type="hidden" name="rcWriter" value="<%=m.getMemberId() %>"> 
							<input type="hidden" name="bookRef" value="<%=b.getBookNo() %>"> 
							<input type="hidden" name="recommRef" value="0"> 
							
							<textarea name="recommContent" class="plusholder" placeholder="후기&감상평을 남겨보세요!  욕설과 비속어 사용시 해당 후기&감상평이 제재될 수 있습니다. 타인을 비방하는 문구 사용을 해주세요 "></textarea>
						</li>

						<li>
							<button type="submit" id="recommbtn" class="recommbtn recommbc1 recommbs1">등록</button>
						</li>
						
					</ul>
				</form>
			</div>
			
			<%} %>
	
		<div style="padding: 4px;"><a href="#" style="margin-left:1050px; padding:4px;">최신순</a><a>|</a><a href="#" style="padding:4px;">과거순</a></div>
			<%for(Recomm rc : recommList) {%>
			
			<ul class="posting-comment">
				<li>
					<ul class="posting-comment-reft">
						<li><span class="material-icons">account_box</span></li>
						<li><span>평점:<%=rc.getRating() %></span></li>
					</ul>
				</li>
				<li>
					<p class="comment-info">
						<span><%=rc.getRcWriter() %></span> <span><%=rc.getRecommDate() %></span>
					</p>
					<p class="comment-content"><%=rc.getRecommContent() %></p> <textarea
						name="recommContent" class="input-form"
						style="min-height: 96px; display: none;"><%=rc.getRecommContent() %></textarea>
					<p class="comment-link">
						<%if(m != null) {%>
						<%if(m.getMemberId().equals(rc.getRcWriter())) {%>
						<a href="javascript:void(0)"
							onclick="modifyComment(this,<%=rc.getRecommNo()%>,<%=b.getBookNo()%>);"><span
							class="material-symbols-outlined plusborder"> edit</span></a> <a
							href="javascript:void(0)"
							onclick="deleteCommnet(this,<%=rc.getRecommNo()%>,<%=b.getBookNo()%>);"><span
							class="material-symbols-outlined plusborder"> delete</span></a>

						<%}//해당 댓글 수정 조건(댓글작성자가 로그인한 회원인지 확인) %>

						<a href="javascript:void(0)" class="reshow"><span
							class="recommopenbtn">답글달기</span></a>
						<%}// 대댓글 달기 조건문(로그인체크) %>
					</p>
				</li>
			</ul>
			<%for(Recomm rcc : rerecommList ) {%>
			<%if(rcc.getRecommRef() == rc.getRecommNo()) {%>
			<ul class="posting-comment reply">
				<li><span class="material-icons">subdirectory_arrow_right</span>

				</li>
				<li>
					<p class="comment-info">
						<span><%=rcc.getRcWriter() %></span> <span><%=rcc.getRecommDate() %></span>
					</p>
					<p class="comment-content"><%=rcc.getRecommContent() %></p> <textarea
						name="recommContent" class="input-form"
						style="min-height: 96px; display: none; border-radius: 5px;"><%=rcc.getRecommContent() %></textarea>
					<p class="comment-link">
						<%if(m!=null && m.getMemberId().equals(rcc.getRcWriter())) {%>
						<a href="javascript:void(0)"
							onclick="modifyComment(this,<%=rcc.getRecommNo()%>,<%=b.getBookNo()%>);"><span
							class="material-symbols-outlined plusborder"> edit</span></a> <a
							href="javascript:void(0)"
							onclick="deleteCommnet(this,<%=rcc.getRecommNo()%>,<%=b.getBookNo()%>);"><span
							class="material-symbols-outlined plusborder"> delete</span></a>

						<%} %>
					</p>
				</li>
			</ul>
			<%}//댓글번호 체크 if문 종료 %>
			<%}//대댓글 출력 for문 종료 %>

			<%-- 댓글에 대한 대댓글 입력양식 --%>
			<%if(m != null) {%>
			<div class="inputCommentBox inputRecommentBox">
				<form action="/insertRecomm.do" method="post">
					<ul class="rerecomm-inputbox">
						<li style="margin-bottom: 8px;"><input type="hidden"
							name="rcWriter" value="<%=m.getMemberId() %>"> <input
							type="hidden" name="bookRef" value="<%=b.getBookNo() %>">
							<input type="hidden" name="recommRef"
							value="<%=rc.getRecommNo() %>"> <textarea
								name="recommContent" class="input-form rerecommbox"
								style="min-height: 96px; min-width: 1020px;"></textarea></li>
						<li>
							<button type="submit" class="rerecommbtn recommbc1 recommbs1"
								style="margin-left: 1100px; border-radius: 10px">등록</button>
						</li>
					</ul>
				</form>
			</div>
			<%} %>

			<%}//댓글 출력 for문 끝나는 위치 %>
		</div>

		<%@ include file="/WEB-INF/views/common/footer.jsp"%>
		<script>
	$("#addCart").on("click", function(){
		const memberNo = $("#memberNo").text();	//header.jsp의 Member m.getMemberNo()
		if(memberNo!=0){ //로그인되어 있는지부터 확인
			const bookNo = $("#bookNo").text();
			$.ajax({
				url: "/insertOneIntoCart.do",
				type: "GET",
				data: {input1 : bookNo, input2 : memberNo},
				success: function(message){
					alert(message);
				},
				error: function(){
					console.log("알 수 없는 오류가 발생했습니다.");
				}
			});
		}else{
			console.log(memberNo);
			alert("회원 로그인이 필요합니다.");
		}
	});
	
	</script>
		<script src="/js/recomm.js"></script>
</body>
</html>