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
<title>(관리자페이지) 주문내역 취소</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
		<div class="page-content">
		<% if(m.getMemberLevel() == 1) { %>
			<div class="page-title">(관리자페이지) 주문내역 변경</div>
			<form action="/adminOrderUpdate.do">
			
				<table class="tbl tbl-hover">
					<tr class="tr-3">
						<th>선택</th>
						<th>주문번호</th>
						<th>회원아이디</th>
						<th>책 제목</th>
						<th>가격</th>
						<th>결제수단</th>
						<th>결제날짜</th>
						<th>결제상태</th>
						<th></th>
					</tr>
					
					<% for(int i=0; i<list.size(); i++) { %>
					<% OrderB o = list.get(i); %>
					<tr class="tr-1">
						<td><input type="checkbox" class="chk"></td>
						<td><%=o.getOrderNo() %></td>
						<td><%=o.getMemberId() %></td>
						<td><%=o.getBook_title() %></td>
						<td><%=o.getBookPrice() %></td>
						<td><%=o.getOrderPay() %></td>
						<td><%=o.getOrderRegDate() %></td>
						<td>
						<%if(o.getStatus().trim().equals("1")) { %>
							<select>
								<option value=1 selected>결제대기</option>
								<option value=2>결제취소</option>
								<option value=3>결제완료</option>
							</select>
						<% } else if(o.getStatus().trim().equals("2")) { %>
							<select>
								<option value=1>결제대기</option>
								<option value=2 selected>결제취소</option>
								<option value=3>결제완료</option>
							</select>						
						<% } else if(o.getStatus().trim().equals("3")) { %>
							<select>
								<option value=1>결제대기</option>
								<option value=2>결제취소</option>
								<option value=3 selected>결제완료</option>
							</select>						
						<% } %>
						</td>
						<td>
							<button type="button" class="btn bc5 adminChangePay">적용</button>
						</td>
					</tr>
					<% } %>
					
					<tr>
						<th colspan="9">
							<button type="button" class="btn bc2 bc4 changeOrderPay">선택회원 결제 취소</button>				
						</th>
					</tr>
				</table>
			</form>
			<% } %>
		</div>
		
		<script>
			// 결제방식 변경
			$(".adminChangePay").on("click", function() {
				const orderNo = $(this).parent().parent().children().eq(1).text();		
				const orderPay = $(this).parent().prev().children().val();
				
				//console.log("orderNo : " + orderNo);
				//console.log("orderPay : " + orderPay);
				
				location.href = "/adminChangePay.do?orderNo="+orderNo+"&orderPay="+orderPay;
			});
			
			// 선택회원 주문상태 변경
			$(".changeOrderPay").on("click", function() {
				const check = $(".chk:checked");
				if(check.length == 0) {
					alert("선택된 회원 없음");
					return;
				}
				
				// 주문상태를 저장할 배열
				const status = new Array();
				
				// 체크된 주문상태을 저장할 배열
				const chkStatus = new Array();
				
				check.each(function(index, item) {
					const orderNo = $(item).parent().next().text();
					console.log("orderNo : " + orderNo);
					status.push(orderNo);
					
					const orderPay = $(item).parent().parent().find("select").val();
					console.log("orderPay : " + orderPay);
					chkStatus.push(orderPay);
				});
				
				location.href="/adminCheckChangePay.do?status="+status.join("/")+"&chkStatus="+chkStatus.join("/");
				
				console.log("status : " + status);
				console.log("chkStatus : " + chkStatus);
			});
		</script>
		
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>