<%@page import="com.litbooks.orderB.vo.OrderB"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
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
			
			<table class="btn">
				<tr class="tr-2">
					<th>주문번호</th>
					<th>구매일</th>
					<th>결제내역</th>
					<th>주문금액</th>
					<th>결제수단</th>
				</tr>
				<tr class="tr-2">
					<% for(OrderB o : list) { %>
						<td><%=o.getOrderNo() %></td>
						<td><%=o.getOrderRegDate() %></td>
						<td><%=o.getBook_title() %></td>
						<td><%=o.getOrderPrice() %></td>
						<td><%=o.getOrderPay() %></td>
					<% } %>
				</tr>
			</table>
		</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>