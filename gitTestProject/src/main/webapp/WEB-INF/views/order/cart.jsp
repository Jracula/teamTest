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
		display: inline-flex;
		border: 1px solid black;
	}
	
	.table-content th{
		border: 1px solid black;
	}
	
	.table-content td{
		border: 1px solid black;
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
                <th>책 이미지</th>
                <th>책 번호</th>
                <th>책 이름</th>
                <th>책 가격</th>
                <th><button type="button" class="removeBtn">선택삭제</button></th>
            </tr>
            
            <% for(int i=0; i<list.size(); i++) { %>
            <% Basket ba = list.get(i); %>
            <tr>
                <td><input type="checkbox" class="chk" value=<%=ba.getBasketNo() %>></td>
                <td><img src="#" width="150px" height="150px"></td>
                <td><span style="display: block;" id="bookNo"><%=ba.getBasketNo() %></span></td> 
				<td> <!-- 책 이름 -->
				<% for(int j=0; j<bask.size(); j++) {%>
       			<% Book detail = bask.get(j); %>
				<%=detail.getBookTitle() %>
				<% } %>
				</td> 
                <td class="amountPrice"> <!-- 책 가격 -->
                <% for(int j=0; j<bask.size(); j++) {%>
       			<% Book detail = bask.get(j); %>
                <%=detail.getBookPrice() %>
                <% } %>
                </td> 
                <td></td>
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
			
			// 수정필요
			const check = $(".chk:checked");
			
			const no = new Array();
			
			check.each(function(index, item) {				
				const bookNo = $(this).parent().parent().next().children().find("span").text();
				console.log("bookNo = " + bookNo);
			});
			
			// 체크 후 선택삭제 시 장바구니 DB에 해당하는 책 번호 삭제
			location.href="/cartDelete.do?no="+no.join("/");
		});
		
		
		
		// 총 상품 금액, 합계 --> 함수처리
		let sum = 0;
		const price = $(".amountPrice");
		//console.log(price);
		for(let i=0; i<price.length; i++) {
			//console.log(price.eq(i).text());
			sum += Number(price.eq(i).text());
			//console.log(sum);
			$("#allPrice").text(sum);
			$("#allPrice2").text(sum);
		}
	
		// 결제페이지로 이동
		$("#buyBook").on("click", function() {
			const memberNo = $("#memberNo").text();
			const bookNo = $("#bookNo").text(); // id, class / eq(0)~eq(9) / book 1~10
			const basketNo = new Array();
			$(".chk:checked").each(function(index,item){
				basketNo.push($(item).val());
			});
			//console.log(basketNo.join("/"));
			location.href="/orderPayMent.do?memberNo="+memberNo+"&basketNo="+basketNo.join("/");
			 //memberNo(헤더), bookNo
		});
	</script>
	
	
</body>
</html>