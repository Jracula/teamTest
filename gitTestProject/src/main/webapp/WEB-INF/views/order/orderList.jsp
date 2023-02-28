<%@page import="com.litbooks.orderB.vo.OrderB"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	//OrderB or = (OrderB)request.getAttribute("or");
    	ArrayList<OrderB> list = (ArrayList<OrderB>)request.getAttribute("list");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
		<div class="page-content">
			<div class="page-title">결제(주문)내역</div>
			<form action="/orderList.do?memberNo=2">
				<!-- /orderList.do?memberNo=2&bookNo=1&bookPrice=3000 -->
				<table class="tbl">
					<tr class="tr-2">
						<th>주문번호</th>
						<th>구매일</th>
						<th>결제내역</th>
						<th>주문금액</th>
						<th>결제수단</th>
					</tr>
					
					<tr class="tr-2">
						<% for(int i=0; i<list.size(); i++) { %>
							<%OrderB o = list.get(i); %>
							<td><%=o.getOrderNo() %></td>
							<td><%=o.getOrderRegDate() %></td>
							<td><%=o.getBook_title() %></td>
							<td><%=o.getOrderPrice() %></td>
							<td><%=o.getOrderPay() %></td>
						<% } %>
					</tr>
				</table>
			</form>
		</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>