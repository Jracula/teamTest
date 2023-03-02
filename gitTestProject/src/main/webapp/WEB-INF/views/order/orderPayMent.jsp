<%@page import="com.litbooks.orderB.vo.OrderB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	OrderB order = (OrderB)request.getAttribute("order");
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
			display: inline-flex;
		}
        
        input {
        	border: none;
        }
        
        .order-content{
            float: right;
        }
        
    </style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
	<form action="/orderPayMent.do?memberNo=2&bookNo=2">
    <div class="page-content">
        <div class="page-title">
            <h2>주문(결제)목록</h2>
        </div>
        <hr>
        <table class="table-content">
        	<tr>
	        	<td><span class="material-symbols-outlined">warning</span>다운로드 또는 열람 시점부터 대여가 시작됩니다.</td>
        	</tr>
        	<tr>
        		<td>
        		<img src="https://cdn-icons-png.flaticon.com/512/4416/4416261.png" width="200px" style="display: flex" >
        		<span>비공제 도서</span>
        		<br>
        		<span><%=order.getBook_title() %></span>
        		<!--  <span>[구매] 책 제목</span>-->
        		</td>
        	</tr>
        	<tr>
        		<td><p><span><%=order.getPublisher() %></span></p></td>
        		<!--  <td><p><span>출판사</span></p></td>-->
        	</tr>
        	<tr>
        		<td><p><span class="material-symbols-outlined">alarm</span>대여기간 : 3일</p></td>        	
        	</tr>
        	<tr>
        		<td><span class="material-symbols-outlined">warning</span>PC뷰어, 페이퍼에서는 열람할 수 없습니다.</td>
        	</tr>
        </table>
        
        <div class="order-content">
            <div class="order-info"><h2>결제정보</h2>
                <table>
                	<tr>
                		<td><input type="hidden" name="<%=order.getMemberNo() %>" value="<%=order.getMemberNo() %>"></td> <!-- 회원번호 -->
                		<td><input type="hidden" name="<%=order.getBookNo()%>"></td> <!-- 책번호 -->
                	</tr>
                    <tr>
                        <td>주문 금액 : </td>
                        <td><input type="text" id="price" readonly value="<%=order.getBookPrice()%>"></td>
                    </tr>
                    <tr>
                        <td>할인율</td>
                        <td><input type="text" id="sale"></td>
                    </tr>
                    <tr>
                        <td>결제 금액 : </td>
                        <td><input type="text" id="Allprice" name="<%=order.getOrderPrice() %>" readonly value="<%=order.getBookPrice()%>"></td>
                        <td><input type="hidden" name="<%=order.getOrderPay()%>"></td> <!-- 결제수단 -->
                        <td><input type="hidden" name="<%=order.getOrderRegDate()%>"></td> <!-- 결제날짜 -->
                    </tr>
                </table>
                <p><h4>구매동의</h4></p>
                <input type="checkbox" id="product">
                <label for="product"><span id="agreeMent">상품, 가격, 할인정보, 유의사항등을 확인하였으며 구매에 동의합니다.</span></label>
                <p><button type="button" id="payMentBtn">결제하기</button></p>
            </div>
        </div>
    </div>
 	</form>
        <script>
        
            $("#payMentBtn").on("click", function() {
                const price = $("#price").val();
                
                const d = new Date();
                const date = d.getFullYear()+""+(d.getMonth()+1)+""+d.getDate()+""+d.getHours()+""+d.getMinutes()+""+d.getSeconds();

                IMP.init("imp36057035");
                IMP.request_pay({
                    pg: "html5_inicis",
                    pay_method : "card",
                    merchant_uid : "상품번호_"+date,
                    name : "결제 테스트",
                    amount : price,
                    buyer_email : "jjune41@naver.com",   // 로그인한 회원의 이메일
                    buyer_name : "홍길동",    // 로그인 한 회원의 이름
                    buyer_tel : "010-1111-1111",     // 로그인 한 회원의 전화번호
                    buyer_addr : "서울시 영등포구 당산동",    // 로그인 한 회원의 주소
                    buyer_code : "000001"     // 구매코드
                }, function(rsp) {
                    if(rsp.success) {
                    	// 1. form hidden
                    	// --> 장바구니 삭제
                        $.ajax({
                            url : "/insertPayMent.do", // 결제관련 정보를 DB에 insert하는 서블릿
                            type : "POST",
                            dataType : "JSON",
                            data : {memberNo : memberNo, bookTitle : bookTitle, bookPrice : bookPrice, order_pay : order_pay},
                            success : function(data) {
                                alert("결제 성공");
                                // form 태그로 insert
                            },
                            error : function() {
                                alert("에러 발생");
                            }
                        });
                    } else {
                        alert("결제에 실패했습니다.");
                    }
                });
            });
        </script>	
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>