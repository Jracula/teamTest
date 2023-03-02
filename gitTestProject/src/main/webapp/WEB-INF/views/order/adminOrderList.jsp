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
<title>관리자페이지 - 주문내역 조회</title>
<style>
	.orberB-tbl a:hover{
		text-decoration: underline;
	}
	.orberB-tbl tr{
		border-bottom: 1px solid #ccc
	}
	#pageNavi{
		margin: 30px;
	}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="page-title">(관리자페이지) 주문내역 조회</div>
		<form action="/AdminOrderList.do" method="post">
			<table class="tbl tbl-hober orberB-tbl">
				<tr class="tr-2">
					<th>주문번호</th>
					<th>회원아이디</th>
					<th>책 제목</th>
					<th>가격</th>
					<th>결제수단</th>
					<th>결제날짜</th>
				</tr>
				<!-- ArrayList -->
				<%for(int i=0; i<list.size(); i++) { %>
					<%OrderB o = list.get(i); %>
				<tr class="tr-1">
					<td><%=o.getOrderNo() %></td>
					<td><%=o.getMemberId() %></td>
					<td><%=o.getBook_title() %></td>
					<td><%=o.getBookPrice() %></td>
					<td><%=o.getOrderPay() %></td>
					<td><%=o.getOrderRegDate() %></td>
				<% } %>
				</tr>
			</table>
		</form>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>