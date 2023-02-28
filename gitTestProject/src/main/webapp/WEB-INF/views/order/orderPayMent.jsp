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
<title>Insert title here</title>
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
        
        input {
        	border: none;
        }
    </style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
	<form action="/orderPayMent.do?memberNo=2&bookNo=1">
    <div class="order-list">
        <div class="order-title">
            <h2>주문목록</h2>
        </div>
        <hr>
        <div><span class="material-symbols-outlined">warning</span>다운로드 또는 열람 시점부터 대여가 시작됩니다.</div>
        <!-- 이미지 삽입 필요 -->
        <img src="//img.ridicdn.net/cover/4766000064/xxlarge#1" width="200px" ><span>비공제 도서</span><p></p>
        <div><span>[구매] 책 제목</span></div>
        <div><span>출판사</span></div>
        <div><span class="material-symbols-outlined">alarm</span>대여기간 : 3일</div>
        <div><span class="material-symbols-outlined">warning</span>PC뷰어, 페이퍼에서는 열람할 수 없습니다.</div>
	</div>
	
        <div class="order-content">
            <div class="order-info"><h2>결제정보</h2>
                <table>
                	<tr>
                		<td><input type="hidden" name="memeberNo" value="<%=order.getMemberNo() %>"></td>
                		<td><input type="hidden" name="bookNo" value="2"></td>
                	</tr>
                    <tr>
                        <td>총 주문 금액 : </td> <!-- orderB 총 가격(order_price) -->
                        <td><input type="text" id="price" readonly value="<%=order.getBookPrice()%>"></td>
                    </tr>
                    <tr>
                        <td>할인율</td>
                        <td><input type="text" id="sale"></td>
                    </tr>
                    <tr>
                        <td>총 결제 금액 : </td> <!-- orderB 총 가격(order_price) -->
                        <td><input type="text" id="Allprice" readonly value="<%=order.getOrderPrice() %>"></td>
                    </tr>
                </table>
                <p><h4>구매동의</h4></p>
                <input type="checkbox"><span>상품, 가격, 할인정보, 유의사항등을 확인하였으며 구매에 동의합니다.</span>
                <p><button id="payMentBtn">결제하기</button></p>
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
                    name : "결제",
                    amount : price,
                    buyer_email : "jjune41@naver.com",   // 로그인한 회원의 이메일
                    buyer_name : "홍길동",    // 로그인 한 회원의 이름
                    buyer_tel : "010-1111-1111",     // 로그인 한 회원의 전화번호
                    buyer_addr : "서울시 영등포구 당산동",    // 로그인 한 회원의 주소
                    buyer_code : "000001"     // 구매코드
                }, function(rsp) {
                    if(rsp.success) {
                        $.ajax({
                            url : "/insertPayMent.do", // 결제관련 정보를 DB에 insert하는 서블릿
                            type : "POST",
                            dataType : "JSON",
                            data : "{orderNo: orderNo, bookTitle : bookTitle, order_price : order_price, order_pay : order:pay}",
                            success : function(data) {
                                alert("결제 성공");
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