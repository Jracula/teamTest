<%@page import="com.litbooks.book.vo.Book"%>
<%@page import="com.litbooks.basket.vo.Basket"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<Basket> list = (ArrayList<Basket>)request.getAttribute("list");
    	ArrayList<Book> bask = (ArrayList<Book>)request.getAttribute("bask");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니(카트)</title>
<style>
	#buyBook{
        border: none;
		color: #fff;
        background-color: #205AA7;
        width: 500px;
        height: 50px;
        font-size: 24px;            
	}
	
	.table-content{
		border: 1px solid black;
		width: 50%;
	}
	
	.table-content th{
		align-content: center;
	}
	
	#check{
		width: 15px;
		height: 15px;
	}

	.pay-content{
		margin: 30px 50px;
	}
	
	#allChk{
		width: 15px;
		height: 15px;
	}
	
	.removeBtn{
		width: 80px;
		height: 45px;
		border: none;
		background-color: #205AA7;
		color: #fff;
		font-size: 14px;
	}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
    <div class="page-content">
        <% if(m != null && m.getMemberLevel() != 1) { %>
        <div class="page-title">장바구니(카트)</div>
        
        <table class="table-content">
            <tr>
                <th colspan="2"><input type="checkbox" id="allChk"><label for="allChk">전체선택</label></th>
                <th></th>
                <th>책 이름</th>
                <th>책 가격</th>
                <th></th>
                <th><button type="button" class="removeBtn">선택삭제</button></th>
            </tr>
            
            <% for(int i=0; i<list.size(); i++) { %> <!-- 장바구니 테이블 -->
            <% Basket ba = list.get(i); %> 			
       		<% Book detail = bask.get(i); %>
            <tr>
                <td><input type="checkbox" class="chk" id="check" value=<%=ba.getBasketNo() %>></td>
                <td>
		        <%if (detail.getBookImage()!=null){%>
					<img src="/upload/book/cover-image/<%=detail.getBookImage() %>" height="150px">
				<%}else{ %>
					<img src="/upload/book/cover-image/00000000.jpg" height=150px>
				<%} %>
                </td>
                <td><span style="display: none;" id="bookNo" class="bkn"><%=detail.getBookNo() %></span></td> 
				<td><%=detail.getBookTitle() %></td> <!-- 책 이름 -->
                <td class="amountPrice"><%=detail.getBookPrice() %></td>
    		</tr>
    		<% } %>
        </table>
        
       	<div class="pay-content">
            <span class="material-symbols-outlined" style="margin: 0">check_circle</span>
            <span id="checkCount">O</span>개를 선택하셨습니다.
            <div>총 상품 금액 : <span id="allPrice">0</span>원</div>
            <div>합계 : <span id="allPrice2">0</span>원</div>
            <button id="buyBook">구매하기</button>
        </div>
        <% } %>
	</div>

	<%@include file="/WEB-INF/views/common/footer.jsp" %>
	
	<script>
		// 전체 선택
		const allChk = document.querySelector("#allChk");
		allChk.addEventListener("change", function() {
			const chk = document.querySelectorAll(".chk");
			const status = allChk.checked;
			
			$(".chk").prop("checked",status);
			const checkCount = $(".chk:checked").length;
			$("#checkCount").text(checkCount);
			
			totalPrice();
		});
		
		// 일괄 선택
		$(".chk").on("click", function() {
			$(this).prop("checked");
			const checkCount = $(".chk:checked").length;
			$("#checkCount").text(checkCount);
			
			totalPrice();
		});
		
		// 선택삭제 버튼 클릭 시 장바구니 테이블 삭제
		// → 체크박스를 배열로 저장 후 Ajax로 넘겨준다.
		$(".removeBtn").on("click", function() {
			// 체크박스를 저장할 배열
			const no = new Array();
			const checkBox = $(".chk:checked");
			
			checkBox.each(function(index, item) {
				const value = $(item).val();
				no.push(value);
			});

			console.log(no.join("/"));

			$.ajax({
				url : "/cartDelete.do",
				type : "POST",
				dataType : "JSON",
				data : { no : no.join("/")},
				success : function(data) {
					console.log(data);
					if(data){
						checkBox.each(function(index, item){
							$(item).parent().parent().remove();
							totalPrice()
						});
					}else{

					}
				},
				error : function() {
					alert("알 수 없는 이유로 삭제에 실패했습니다.");
					location.href="/cart.do";
				}
			});
		});

		// 총 상품 금액, 합계 --> 함수처리
		function totalPrice() {
			let sum = 0;
			const price = $(".amountPrice");
			price.each(function(index, item) {
				const checkbox = $(item).parent().find("input[type=checkbox]");
				if (checkbox.is(":checked")) {
					const bookPrice = $(item).text();
					sum += Number(bookPrice);
				}
			});
			$("#allPrice").text(sum);
			$("#allPrice2").text(sum);
		}

		// 결제페이지로 이동
		$("#buyBook").on(
				"click",
				function() {
					const memberNo = $("#memberNo").text();
					const bookNo = $("#bookNo").text(); // id, class / eq(0)~eq(9) / book 1~10
					const basketNo = new Array();
					$(".chk:checked").each(function(index, item) {
						basketNo.push($(item).val());
					});
					//console.log(basketNo.join("/"));
					location.href = "/orderPayMent.do?memberNo=" + memberNo
							+ "&basketNo=" + basketNo.join("/");
					//memberNo(헤더), bookNo
				});
	</script>
	
	
</body>
</html>