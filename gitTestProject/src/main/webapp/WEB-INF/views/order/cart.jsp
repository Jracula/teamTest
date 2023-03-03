<%@page import="com.litbooks.basket.vo.Basket"%>
<%@page import="com.litbooks.book.vo.Book"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<Basket> baskList = (ArrayList<Basket>)request.getAttribute("baskList");
    	ArrayList<Book> bookList = (ArrayList<Book>)request.getAttribute("bookList");
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
		display: inline-flex;
	}
	
	.pay-content{
		float: right;
	}
	
	#allChk{
		width: 15px;
		height: 15px;
	}
	
	.chk {
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
        <div class="page-title">장바구니(카트)</div>
        <table class="table-content">
            <tr>
                <th><input type="checkbox" id="allChk"><label for="allChk">전체선택</label></th>
                <th></th>
                <th></th>
                <th><button type="button" class="removeBtn">선택삭제</button></th>
            </tr>
            
            <% for(int i=0; i<bookList.size(); i++) { %>
            <% Book b = bookList.get(i); %>
            <tr>
                <td><input type="checkbox" class="chk" name="cartNo"></td>
                <td><img src="#" width="100px" height="100px"></td>
                <td>
                    <span><%=b.getBookTitle() %></span>
                    <p><%=b.getWriter() %></p>
                </td>
                <td class="amountPrice"><%=b.getBookPrice() %></td>
            </tr>
    	<% } %>
        </table>
       	<div class="pay-content">
            <span class="material-symbols-outlined">check_circle</span><span id="checkCount">O</span>개를 선택하셨습니다.
            <div>총 상품 금액 : <span id="allPrice"> 원</span></div>
            <div>합계 : <span id="allPrice2"> 원</span></div>
            <button id="buyBook">구매하기</button>
        </div>
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
		});
		
		// 일괄 선택
		$(".chk").on("click", function() {
			$(this).prop("checked");
			const checkCount = $(".chk:checked").length;
			$("#checkCount").text(checkCount);
		});
		
		// 선택삭제
		$(".removeBtn").on("click", function() {
			const chk = document.querySelectorAll(".chk");
			for(let i=0; i<chk.length; i++) {
				if(chk[i].checked) {
					chk[i].parentElement.parentElement.remove();
				}
			}
		});
		
		// 총 상품 금액, 합계
		let sum = 0;
		const price = $(".amountPrice");
		console.log(price);
		for(let i=0; i<price.length; i++) {
			sum = Number(price.text());
			console.log(sum);
		}
		$("#allPrice").text(sum);
		$("#allPrice2").text(sum);
	
		// 결제페이지로 이동
		$("#buyBook").on("click", function() {
			location.href="/orderPayMent.do?memberNo="+2+"&bookNo="+2;
			// memberNo(헤더), bookNo
		});
	</script>
	
	
</body>
</html>