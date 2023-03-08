<%@page import="com.litbooks.book.vo.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.litbooks.orderB.vo.OrderB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<Book> list = (ArrayList<Book>)request.getAttribute("list");
    	String basketNo = (String)request.getAttribute("basketNo");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문(결제)</title>
</head>
    <style>
        #payMentBtn {
            border: none;
            color: #fff;
            background-color: #205AA7;
            width: 500px;
            height: 50px;
            font-size: 24px;
        }
        
       .table-content{
			width: 70%;
		}
        
        input {
        	border: none;
        }
        
    </style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
    <div class="page-content">
        <div class="page-title">
            <h2>주문(결제)목록</h2>
            <span id="basketNo" style="display:none;"><%=basketNo %></span>
            <span id="memberNo" style="display:none;"><%=m.getMemberNo() %></span>
        </div>
        <hr>
        <br>
        <span class="material-symbols-outlined" style="margin: 0">warning</span>
		다운로드 또는 열람 시점부터 대여가 시작됩니다.
        <div>비공제 도서</div>
        <br>
        <table class="tbl table-content">
        	<tr class="tr-2">
        		<th></th>
        		<th>책제목</th>
        		<th>출판사</th>
        		<th>가격</th>
        	</tr>
        
        	<% for(int i=0; i<list.size(); i++) { %>
        	<% Book b = list.get(i); %>
	        	
        	<tr class="tr-1 point">
        		<td><img src="/upload/book/cover-image/00000000.jpg" width="200px" ></td>        		
        		<td><%=b.getBookTitle() %><span class="bookNo" style="display:none;"><%=b.getBookNo() %></span></td>
        		<td><%=b.getPublisher() %></td>
        		<td><%=b.getBookPrice() %></td>
        	</tr>
        	<% } %>
        </table>
        <span class="material-symbols-outlined" style="margin: 0">alarm</span>대여기간 : 3일 <br>        	
        <span class="material-symbols-outlined" style="margin: 0">warning</span>PC뷰어, 페이퍼에서는 열람할 수 없습니다.<br><br>
        
        <div class="order-content">
            <div class="order-info"><h2>결제정보</h2>                
                <h3>구매동의</h3>
                <table>
                	<tr>
                		<th>총 결제 금액 : </th>
        		<% int totalSum = 0; %>
                <% for(int i=0; i<list.size(); i++) { %>
        		<% Book b = list.get(i); %>
        		<% totalSum += b.getBookPrice(); %>
                <% } %>
                		<td id="sum"><%=totalSum %></td>
                	</tr>
                </table>
                <input type="checkbox" id="product">
                <label for="product"><span id="agreeMent">상품, 가격, 할인정보, 유의사항등을 확인하였으며 구매에 동의합니다.</span></label>
                <p><button type="button" id="payMentBtn">결제하기</button></p>
            </div>
        </div>
    </div>
    
        <script>
        	// 결제페이지 총 금액
        	const totalSum = $("#sum").text();
        
            $("#payMentBtn").on("click", function() {
                let price = 0;
                const bookNo = new Array();
                const bookPrice = new Array();
             	// 책제목, 책가격 뽑아내기
            	const point= $(".point");
            	point.each(function(index, item){
            		const no = $(item).find(".bookNo").text();
            		const bookPrice1 = $(item).children().eq(3).text();
            		
            		price += Number(bookPrice1);
            		bookNo.push(no);
            		bookPrice.push(bookPrice1);
            	});
                
                const d = new Date();
                const date = d.getFullYear()+""+(d.getMonth()+1)+""+d.getDate()+""+d.getHours()+""+d.getMinutes()+""+d.getSeconds();
                const basketNo = $("#basketNo").text();
                const memberNo1 = $("#memberNo").text();
            	console.log(price);
            
                IMP.init("imp36057035");
                IMP.request_pay({
                    pg: "html5_inicis",
                    pay_method : "card",
                    merchant_uid : "상품번호_"+date,
                    name : "결제 테스트",
                    amount : 100, // price
                    buyer_email : "jjune41@naver.com", <%-- <%m.getMemberEmail();%>, --%> // //로그인한 회원의 이메일
                    buyer_name : "홍길동", <%-- <%m.getMemberName();%>, --%> // 로그인 한 회원의 이름
                    buyer_tel : "010-1111-1111", <%-- <%m.getMemberPhone();%>, --%> // 로그인 한 회원의 전화번호
                    buyer_addr : "서울시 영등포구 당산동",    // 로그인 한 회원의 주소
                    buyer_code : "000001"     // 구매코드
                }, function(rsp) {
                    if(rsp.success) {
                    	
                    	console.log(rsp);
                    	
                        $.ajax({
                            url : "/insertPayMent.do", // 결제관련 정보를 DB에 insert하는 서블릿
                            type : "POST",
                            dataType : "JSON",
                            data : {memberNo1 : memberNo1, bookNo : bookNo.join("/"), bookPrice : bookPrice.join("/"), price : price, basketNo : basketNo, payMethod:rsp.pay_method},
                            // { key : value }
                            success : function(data) {
                                if(data == "1"){
                                	// 결제 정보 저장 성공 시
                                	location.href="/orderList.do?reqPage=1&memberNo="+memberNo1;
                                }else{
                                	// 결제 정보 저장 실패 시
                                	location.href="/orderPayMent.do";
                                }
                            },
                            error : function() {
                                alert("알 수 없는 이유로 결제에 실패했습니다.");
                            }
                        });
                    } else {
                        alert("알 수 없는 이유로 결제에 실패했습니다.");
                    }
                });
            });
        </script>	
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>