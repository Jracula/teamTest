<%@page import="com.litbooks.orderB.vo.OrderB"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<OrderB> list = (ArrayList<OrderB>)request.getAttribute("list");
    	String pageNavi = (String)request.getAttribute("pageNavi");
    	int start = (int)request.getAttribute("start");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제(주문)내역</title>
<style>
	.orberB-tbl a:hover{
		text-decoration: underline;
	}
	.orberB-tbl tr{
		border-bottom: 1px solid #ccc
	}
	.orberB-tbl tr>td:nth-child(2) {
		text-align: left;
	}
	#pageNavi{
		margin: 30px;
	}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
		<div class="page-content">
			<div class="page-title">결제(주문)내역</div>
			<form action="/orderList.do?reqPage=1">
				<table class="tbl tbl-hober orberB-tbl">
					<tr class="tr-2">
						<th width="20%">주문번호</th>
						<th width="20%">구매일</th>
						<th width="20%">결제내역</th>
						<th width="20%">주문금액</th>
						<th width="20%">결제수단</th>
					</tr>
					
						<% for(int i=0; i<list.size(); i++) { %>
							<%OrderB o = list.get(i); %>
					<tr class="tr-1">
							<td><%=i+start %></td>
							<td><%=o.getOrderRegDate() %></td>
							<td><%=o.getBook_title() %></td>
							<td><%=o.getOrderPrice() %></td>
							<td><%=o.getOrderPay() %></td>
						<% } %>
					</tr>
				</table>
				
				<div id="pageNavi"><%=pageNavi %></div>
				
			</form>
		</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>