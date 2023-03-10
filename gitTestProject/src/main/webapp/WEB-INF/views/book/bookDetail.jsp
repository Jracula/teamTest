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
    ArrayList<Recomm> recommList
    =(ArrayList<Recomm>)request.getAttribute("recommList");
    ArrayList<Recomm> rerecommList
    =(ArrayList<Recomm>)request.getAttribute("rerecommList");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=b.getBookTitle() %> - LiTBOOKS</title>
<link rel="stylesheet" href="/css/recomm.css">
<link rel="stylesheet" href="/css/bootstrap-modal.css" />
<style>
.coverImage:hover {
  cursor: zoom-in;
}
.page-content>* {
	margin-top: 10px;
	margin-bottom: 10px;
}
.book-card-wrap>* {
  line-height: 200%;
}
.intro-warp {
	clear: both;
	padding-top: 30px;
}
.intro-warp>div {
	margin-top: 100px;
	margin-bottom: 100px;
}
.next-episodes>div {
	overflow: hidden;
	clear: both;
}
.next-episodes>div>div {
	margin-top: 12px;
	margin-bottom: 12px;
	margin-left: 12px;
	float: left;
}
.next-episodes>div>a:hover>div {
	background-color: #E0F0FF;
}
.next-episodes>div>a>div {
	margin-top: 12px;
	margin-bottom: 12px;
	margin-left: 12px;
	float: left;
}
.material-symbols-outlined {
  font-variation-settings:
  'FILL' 0,
  'wght' 400,
  'GRAD' 0,
  'opsz' 48
}
.star {
  font-variation-settings:
  'FILL' 1,
  'wght' 400,
  'GRAD' 0,
  'opsz' 48
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
	<div class="page-content">
		<span id="bookName" style="display: none"><%=b.getBookTitle() %></span>
		<div class="book-cover" style="float: left; width: 300px; margin-right: 40px;">
		<%if (b.getBookImage()!=null){%>
			<img class="coverImage" src="/upload/book/cover-image/<%=b.getBookImage() %>" width=100%;>
		<%}else{ %>
			<img src="/upload/book/cover-image/00000000.jpg" width=100%>
		<%} %>
		</div>
		<div class="book-card-wrap" style="float: left;">
		<%if(b.getBookGenre()!=null){ %>
			<p class="genre-category">장르 ＞ <%=b.getBookGenre() %></p>
		<%}else{ %>
			<p class="genre-category">장르 없음</p>
		<%} %>
			<h1><%=b.getBookTitle() %></h1>
			<p>&nbsp;</p>
		<%if(b.getBookScore()!=0){ %>
			<p>평점 - <span><%=Math.round((b.getBookScore()*100))/100.0 %></span></p>
		<%}else{ %>
			<p>&nbsp;</p>
		<%} %>
			<p>저자 - <%=b.getWriter() %></p>
			<p>출판사 - <%=b.getPublisher() %></p>
		<%-- 판매중 상태를 확인 후 가격 노출 --%>
		<%if (b.getOnSale()==1) {%>
		<%-- 할인율이 0%가 아닐 경우, 할인된 판매가를 노출 --%>
			<%int newPrice = b.getBookPrice() * (100 - b.getDiscount()) / 100; %>
			<% if (b.getDiscount()!=0) {%>
			<p style="text-decoration: line-through;">정가 - <%=b.getBookPrice() %>원</p>
			<p>판매가 - <span id="realPrice" style="color: green;"><%=newPrice %></span>원</p>
			<%}else { %>
			<p>정가 - <%=b.getBookPrice() %>원</p>
			<p>&nbsp;<span id="realPrice" style="display:none;"><%=newPrice %></span></p>	<%-- 할인율이 0%면 display none처리 --%>
			<%} %>
		<%}else if (b.getOnSale()==0) {%>
			<p style="color: gray;">판매중지된 상품입니다.<span id="realPrice" style="display:none;">-1</span></p>
			<p>&nbsp;</p>
		<%} %>
	<%if(m!=null){ %>
		<%if(m.getMemberLevel()==1){%>
			<a class="btn bc6" style="padding-left:30px; padding-right:30px;" href="/bookUpdateFrm.do?bookNo=<%=b.getBookNo() %>">편집</a>
		<%} %>
	<%} %>
			<button class="btn bc9" id="addCart">장바구니에 담기</button>
<!-- 장바구니에 담기 Modal을 실행시킬 숨겨진 버튼 -->
			<button type="button" class="btn btn-info btn-lg" id="modalButton" data-toggle="modal" data-target="#myModal" style="display: none;">modal용 숨겨진 버튼</button>
<!-- 장바구니에 담기 Modal -->
			<div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title" style="text-align: center;">알림</h4>
						</div>
						<div class="modal-body">
							<p id="givenMessage" style="text-align: center;">장바구니 결과 메세지</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">확인</button>
						</div>
					</div>
				</div>
			</div>
<!-- 장바구니에 담기 Modal 끝 -->

			<button class="btn bc9" id="payOneBtn">구매하기</button>
			<%-- orderPayOne.do?bookNo=<%=b.getBookNo()%>&bookPrice=<%=b.getBookPrice()%> --%>
		</div>
		<div style="clear: both;"></div>
	<%if(b.getNonFee()==1) {%>
		<button class="btn bc66" id="letMeRead" style="width: 48.7%;">이 책은 무료로 감상하실 수 있습니다.</button>
	<%}else{ %>
		<button class="btn bc99" id="letMeRead" style="width: 48.7%;">책 읽기</button>
	<%} %>
		<div class="intro-warp">
			<div>
				<h3>작품 소개</h3>
				<hr style="margin-top: 10px; margin-bottom: 10px;">
				<div>
				<%if (b.getBookIntro()!=null){%>
				<%=b.getBookIntro().replaceAll("\r\n","<br>") %>
				<%}else{ %>
				작품 소개가 없습니다.
				<%} %>
				</div>
			</div>
<!-- seriesList의 크기가 1보다 크면, 같은 시리즈물을 표시하는 영역 -->
		<%if(seriesList.size()>1) {%>
			<div class="next-episodes">
				<h3>이 책의 시리즈</h3>
				<hr style="margin-top: 10px; margin-bottom: 10px;">
			<%for(int i=0; i<seriesList.size(); i++){%>
				<%if(i<5){%>
					<%Book bs = seriesList.get(i); %>
				<div>
					<div>
					<%if (bs.getBookImage()!=null){%>
						<img class="coverImage" src="/upload/book/cover-image/<%=bs.getBookImage() %>" width=70px>
					<%}else{ %>
						<img src="/upload/book/cover-image/00000000.jpg" width=70px>
					<%} %>
					</div>
					<a href="/bookDetail.do?bookNo=<%=bs.getBookNo()%>">
					<div>
						<p><%=bs.getBookTitle() %></p>
			<%-- 판매중 상태를 확인 후 가격 노출 --%>
					<%if (bs.getOnSale()==1) {%>
			<%-- 할인율이 0%가 아닐 경우, 할인된 판매가를 노출 --%>
						<%int newPrice = bs.getBookPrice() * (100 - bs.getDiscount()) / 100; %>
						<%if (bs.getDiscount()!=0) {%>
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
					<%if(bs.getBookScore()!=0){ %>
						<p>평점 - <span><%=Math.round((bs.getBookScore()*100))/100.0 %></span></p>
					<%}else{ %>
						<p>평점 없음</p>
					<%} %>
					</div></a>
				</div>
				<%}else{%>
				<div>이 책의 시리즈는 6권 이상이 있습니다.</div>
				<%break;}%>
			<%}%>
			</div>
		<%}%>
		</div>
	<div style="clear: both;"></div>

		
		<!-- 댓글 후기 노출 영역 -->
		<div class="commentBox">
		
		<%-- 로그인 되어있는 경우에만 댓글 작성 화면을 띄움 --%>
      <%if (m != null) {%>
			<div class="inputCommentBox">
				
				<form id="rating-form" action="/insertRecomm.do" method="post">
					<div class="star-wrap star-wrap1">
						<div class="score-text-box">
							<input type="hidden" id="insertRating" name="rating" value="">
							<span id="star-result-left"> </span> 
							<span id="star-result-number">평점을 입력해보세요!</span> 
							<span id="star-result-right"> </span>
						</div>
						<span class="material-symbols-outlined star">star</span> 
						<span class="material-symbols-outlined star">star</span> 
						<span class="material-symbols-outlined star">star</span> 
						<span class="material-symbols-outlined star">star</span> 
						<span class="material-symbols-outlined star">star</span>
					</div>
					
					<ul>
						<li><input type="hidden" name="rcWriter"
							value="<%=m.getMemberId() %>"> <input type="hidden"
							name="bookRef" value="<%=b.getBookNo() %>"> <input
							type="hidden" name="recommRef" value="0"> <textarea
								name="recommContent" class="input-form"
								style="border-radius: 8px;"
								placeholder="후기&감상평을 남겨보세요!  욕설과 비속어 사용시 해당 후기&감상평이 제재될 수 있습니다. 타인을 비방하는 문구 사용을 주세요 "></textarea>
						</li>
						<li>
							<button type="submit" id="recommbtn" class="recommbtn recommbc1 recommbs1">등록</button>
						</li>
					</ul>
				</form>
			</div>
			<%} %>
		
         <%for(Recomm rc : recommList) {%>
         <ul class="posting-comment">
            <li>
               <span class="material-icons">
               	
            </li>
            <li>
               <p class="comment-info">
                  
                  <span><%=rc.getRecommDate() %></span>
                  <span><%=rc.getRcWriter() %></span>
                  <%if(rc.getRating() == 1) {%>
               		<span class="commentrash">님의 평점</span>
               		<img src="/countingstar/별1개.png" style="width: 100px">
                <%} %>
               <%if(rc.getRating() == 2) {%>
               		<span class="commentrash" >님의 평점</span>
               		<img src="/countingstar/별2개.png" style="width: 100px">
                <%} %>
                <%if(rc.getRating() == 3) {%>
                	<span class="commentrash">님의 평점</span>
               		<img src="/countingstar/별3개.png" style="width: 100px">
                <%} %>
                <%if(rc.getRating() == 4) {%>
                	<span class="commentrash">님의 평점</span>
               		<img src="/countingstar/별4개.png" style="width: 100px">
                <%} %>
                <%if(rc.getRating() == 5) {%>
                	<span class="commentrash">님의 평점</span>
               		<img src="/countingstar/별5개.png" style="width: 100px">
                <%} %>
               </span>
               </p>
               <p class="comment-content"><%=rc.getRecommContent() %></p>
               <textarea name="recommContent" class="input-form" style="min-height:96px;display:none;"><%=rc.getRecommContent() %></textarea>
               <p class="comment-link">
                  <%if(m != null) {%>
                     <%if(m.getMemberId().equals(rc.getRcWriter())) {%>
                     <a href="javascript:void(0)" onclick="modifyComment(this,<%=rc.getRecommNo()%>,<%=b.getBookNo()%>);"><span class="material-symbols-outlined plusborder">
						edit</span></a>
                     <a href="javascript:void(0)" onclick="deleteCommnet(this,<%=rc.getRecommNo()%>,<%=b.getBookNo()%>);"><span class="material-symbols-outlined plusborder">
						delete</span></a>
                     
                     <%}//해당 댓글 수정 조건(댓글작성자가 로그인한 회원인지 확인) %>
                  
                  <a href="javascript:void(0)" class="reshow"><span class="recommopenbtn">답글달기</span></a>
                  <%}// 대댓글 달기 조건문(로그인체크) %>
               </p>
            </li>
         </ul>   
         <%for(Recomm rcc : rerecommList ) {%>
            <%if(rcc.getRecommRef() == rc.getRecommNo()) {%>
            <ul class="posting-comment reply">
               <li>
                  <span class="material-icons">subdirectory_arrow_right</span>
                  
               </li>
               <li>
                  <p class="comment-info">
                     <span><%=rcc.getRecommDate() %></span>
                     <span><%=rcc.getRcWriter() %></span>
                  </p>
                  <p class="comment-content"><%=rcc.getRecommContent() %></p>
                  <textarea name="recommContent" class="input-form" style="min-height:96px; display:none; border-radius: 5px; "><%=rcc.getRecommContent() %></textarea>
                  <p class="comment-link">
                     <%if(m!=null && m.getMemberId().equals(rcc.getRcWriter())) {%>
                        <a href="javascript:void(0)" onclick="modifyComment(this,<%=rcc.getRecommNo()%>,<%=b.getBookNo()%>);"><span class="material-symbols-outlined plusborder">
						edit</span></a>
                     	<a href="javascript:void(0)" onclick="deleteCommnet(this,<%=rcc.getRecommNo()%>,<%=b.getBookNo()%>);"><span class="material-symbols-outlined plusborder">
						delete</span></a>
                     
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
                     <li style="margin-bottom: 8px;">
                        <input type="hidden" name="rcWriter" value="<%=m.getMemberId() %>">
                        <input type="hidden" name="bookRef" value="<%=b.getBookNo() %>">
                        <input type="hidden" name="recommRef" value="<%=rc.getRecommNo() %>">
                        <input type="hidden" id="insertRating" name="rating" value="0">
                        <textarea name="recommContent" class="input-form rerecommbox" style="min-height: 96px; min-width: 1020px;" placeholder="바르고 고운말 사용 부탁드립니다, Please use sweety wording"></textarea>
                     </li>
                     <li>
                        <button type="submit" class="rerecommbtn recommbc1 recommbs1" style="margin-left: 1100px; border-radius: 10px  ">등록</button>
                     </li>
                  </ul>
               </form>
            </div>   
            <%} %>
            
         <%}//댓글 출력 for문 끝나는 위치 %>
      </div>
	</div>	
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
	<script>
//url로부터 bookNo값 알아내기
	const ltrim = /^\S{0,}bookNo=/;
	const currentUrl = window.location.href;
	const needRtrim = currentUrl.replace(ltrim, "");
	const rtrim = /[&]\S{0,}$/;
	const bookNo = needRtrim.replace(rtrim, "");
//url로부터 bookNo 도출 끝
	// 장바구니 담기 ajax
	$("#addCart").on("click", function(){
		const onSale = Number($("#realPrice").text());
		if(onSale>0){
			$.ajax({
				url: "/insertOneIntoCart.do",
				type: "GET",
				data: {input1 : bookNo},
				success: function(message){
					$("#givenMessage").text(message);
					$("#modalButton").click();
				},
				error: function(){
					console.log("알 수 없는 오류가 발생했습니다.");
				}
			});
		} else {
			$("#givenMessage").text("판매가가 없는 상품입니다.");
			$("#modalButton").click();
		}
	});
	
	// 책 단권 구매하기 ajax
	const memberLevel = $("#memberLevel").text();
			$("#payOneBtn").on("click", function() {
				if(memberLevel == 2) {
			
			const memberNo = $("#memberNo").text();

			const bookPrice = $("#realPrice").text();
			//console.log("memberNo : " + memberNo);
			//console.log("bookPrice : " + bookPrice);
			
			const bookName = $("#bookName").text();
			
			if(bookPrice < 0) {
				$("#givenMessage").text("판매중지 상품입니다.");
				$("#modalButton").click();
				return;
			}
			
			const d = new Date();
			const date = d.getFullYear()+""+(d.getMonth()+1)+""+d.getDate()+""+d.getHours()+""+d.getMinutes()+""+d.getSeconds();
			
			IMP.init("imp36057035");
	        IMP.request_pay({
	            pg: "html5_inicis",
	            pay_method : "card",
	            merchant_uid : "상품번호_"+date,
	            name : bookName, // "결제테스트"(테스트용)
	            amount : bookPrice, // bookPrice, 100(테스트용)
	            buyer_email : "jjune41@naver.com", <%-- <%m.getMemberEmail();%>, --%> // //로그인한 회원의 이메일
	            buyer_name : "홍길동", <%-- <%m.getMemberName();%>, --%> // 로그인 한 회원의 이름
	            buyer_tel : "010-1111-1111", <%-- <%m.getMemberPhone();%>, --%> // 로그인 한 회원의 전화번호
	            buyer_addr : "서울시 영등포구 당산동",   // 로그인 한 회원의 주소
	            buyer_code : "000001"     // 구매코드
	        }, function(rsp) {
	        	if(rsp.success) {
	        		$.ajax({
	        			url : "/orderPayOne.do",
	        			type : "POST",
	        			dataType : "JSON",
	        			data : {memberNo : memberNo, bookNo : bookNo, bookPrice : bookPrice, payMethod:rsp.pay_method},
	        			success : function(data) {
	        				if(data == "1") {
	        					location.href="/orderList.do?reqPage=1&memberNo="+memberNo;
	        				} else {
	        					location.href="/bookDetail.do";
	        				}
	        			},
	        			error : function() {
	        				alert("알 수 없는 이유로 결제에 실패했습니다.");
	        			}
	        		});
	        	}
	        });
				} else {
					// 관리자일 경우 구매버튼 클릭 시 모달창 띄우기
					$("#givenMessage").text("일반 회원 로그인이 필요합니다.");
					$("#modalButton").click();
				}
		});
		
	// 책 내용 읽기
	$("#letMeRead").on("click", function(){
		$.ajax({
			url: "/isThisNonFee.do",
			type: "GET",
			data: {theBookNo : bookNo},
			success: function(result){
				if(result==2){	//무료감상이 아닌데 로그인을 안 함
					$("#givenMessage").text("로그인이 필요합니다.");
					$("#modalButton").click();
				}else if(result==3){
					//무료감상 가능. 로그인조차 불필요 
					window.open("/reading.jsp", "reading", "toolbar=yes, location=yes, directories=no, status=no, menubar=yes, scrollbars=yes, resizable=no, copyhistory=yes, width=800, height=600");
				}else if(result==1){	//무료감상이 아니지만, 구매완료 이력이 남아 있거나 관리자인 경우
					window.open("/reading.jsp", "reading", "toolbar=yes, location=yes, directories=no, status=no, menubar=yes, scrollbars=yes, resizable=no, copyhistory=yes, width=800, height=600");
				}else if(result==0){	//무료감상이 아닌데, 구매완료 이력이 없음. 관리자도 아님
					$("#givenMessage").text("책을 먼저 구매해주세요.");
					$("#modalButton").click();
				}
			},
			error: function(){
				console.log("알 수 없는 오류가 발생했습니다.");
			}
		});		
	});

	// 커머이미지 크게 보기
	$(".coverImage").on("click", function(){
		const imgUrl = $(this).attr("src");
		window.open(imgUrl, "reading", "toolbar=yes, location=yes, directories=no, status=no, menubar=yes, scrollbars=yes, resizable=no, copyhistory=yes");
	});
	</script>
	<script src="/js/recomm.js"></script>
</body>
</html>
