<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 주문(결제) 하기 -->
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
    <p class="page-content">
        <div class="page-title">
            <h2>주문목록</h2>
        </div>
        <hr>
        <div><span class="material-symbols-outlined">warning</span>다운로드 또는 열람 시점부터 대여가 시작됩니다.</div>
        <img src="//img.ridicdn.net/cover/4766000064/xxlarge#1" width="200px" ><span>비공제 도서</span><p></p>
        <div><span>[구매] 책 제목</span></div>
        <div><span>출판사</span></div>
        <div><span class="material-symbols-outlined">alarm</span>대여기간 : 3일</div>
        <div><span class="material-symbols-outlined">warning</span>PC뷰어, 페이퍼에서는 열람할 수 없습니다.</div>

        <div class="order-content">
            <div class="order-info" style="float: right;"><h2>결제정보</h2>
                <table>
                    <tr>
                        <th>총 주문 금액</th> <!-- orderB 총 가격(order_price) -->
                    </tr>
                    <tr>
                        <td>할인율</td>
                    </tr>
                    <tr>
                        <th>총 결제 금액</th> <!-- orderB 총 가격(order_price) -->
                    </tr>
                </table>
                <p><h4>구매동의</h4></p>
                <input type="checkbox"><span>상품, 가격, 할인정보, 유의사항등을 확인하였으며 구매에 동의합니다.</span>
                <p><button type="btn" color="#205AA7">결제하기</button></p>
            </div>
        </div>
	
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>