<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
		<div class="page-content">
			<div class="page-title">결제(주문)취소</div>
			        <table class="tbl">
            <tr>
                <th class="tr-2">주문번호</th>
                <td class="tr-1">OOO</td>
            </tr>
            <tr>
                <th class="tr-2">구분(제목)</th>
                <td class="tr-1">책제목</td>
            </tr>
            <tr>
                <th class="tr-2">주문금액</th>
                <td class="tr-1">OOO원 (총가격)</td>
            </tr>
            <tr>
                <th class="tr-2">PG 결제 금액</th>
                <td class="tr-1">OOO원(결제 시 총가격)</td>
            </tr>
            <tr>
                <th class="tr-2">결제수단</th>
                <td class="tr-1">카카오페이, 삼성페이, </td>
            </tr>
        </table>

        <button>영수증 인쇄</button>
        <button>매출전표 인쇄</button>
        <button id="orderCancel" style="float: right;">결제취소</button>
        <p>· 구매 취소는 결제일로 부터 7일 이내에 다운로드 또는 스트리밍으로 이용하지 않은 도서만 가능합니다.</p>
        <p>· 문화비 소득공제는 주문도서와 결제 수단에 따라 적용됩니다.</p>
		</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>