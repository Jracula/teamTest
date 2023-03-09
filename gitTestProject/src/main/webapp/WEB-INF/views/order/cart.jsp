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
<link rel="stylesheet" href="/css/bootstrap-modal.css" />
<style>
	button:hover {
		cursor: pointer;
	}
	#buyBook{
        border: none;
		color: #fff;
        background-color: #205AA7;
        width: 600px;
        height: 50px;
        font-size: 24px;            
	}
	
	.table-content{
		border: 1px solid black;
		width: 600px;
	}
	
	.table-content th{
		border-bottom: 2px solid gray; 
		align-content: center;
		text-align: left;
		line-height: 300%;
	}
	
	#check{
		width: 15px;
		height: 15px;
	}
	
	.pay-content{
		margin-top: 30px;
		margin-bottom: 30px;
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
     <div style="width: 600px; margin:0 auto;">
        <% if(m != null && m.getMemberLevel() != 1) { %>
        <div class="page-title">장바구니(카트)</div>
        
        <table class="table-content">
            <tr>
                <th colspan="3" style="width: 25%;">&nbsp;<input type="checkbox" id="allChk">　　<label for="allChk">전체선택</label></th>
                <th style="width: 50%; text-indent: 10px;">책 이름</th>
                <th style="width: 18%; text-align: right;">책 가격</th>
                <th style="width: 5%;"></th>
            </tr>
            
            <% for(int i=0; i<list.size(); i++) { %> <!-- 장바구니 테이블 -->
            <% Basket ba = list.get(i); %> 			
       		<% Book detail = bask.get(i); %>
            <tr>
                <td>&nbsp;<input type="checkbox" class="chk" id="check" value=<%=ba.getBasketNo() %>></td>
                <td style="text-align: center;">
		        <%if (detail.getBookImage()!=null){%>
					<img src="/upload/book/cover-image/<%=detail.getBookImage() %>" height="150px">
				<%}else{ %>
					<img src="/upload/book/cover-image/00000000.jpg" height=150px>
				<%} %>
                </td>
                <td><span style="display: none;" id="bookNo" class="bkn"><%=detail.getBookNo() %></span></td> 
				<td style="text-indent: 10px;"><%=detail.getBookTitle() %></td> <!-- 책 이름 -->
                <td class="amountPrice" style="text-align: right;"><%=detail.getBookPrice() %></td>
                <td> 원</td>
    		</tr>
    		<% } %>
        </table>
        <button type="button" class="removeBtn">선택삭제</button>
       	<div class="pay-content">
            <span class="material-symbols-outlined" style="margin: 0">check_circle</span>
            <span id="checkCount">O</span>개를 선택하셨습니다.
            <div>합계 : <span id="allPrice2">0</span>원</div>
            <br>
            
            <!-- 장바구니에서 책을 선택하지 않을 시 실행시킬 버튼 숨기기 -->
			<button type="button" class="btn btn-info btn-lg" id="modalButton" data-toggle="modal" data-target="#myModal" style="display: none;">modal용 숨겨진 버튼</button>
			<!-- 장바구니에서 책 선택하지 않을 시 실행시킬 Modal -->
			<div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title" style="text-align: center;">알림</h4>
						</div>
						<div class="modal-body">
							<p id="givenMessage" style="text-align: center;">장바구니 결과 메세지</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">확인</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 모달창 끝 -->
            
            <button id="buyBook">구매하기</button>
        </div>
        <% } %>
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
			
			totalPrice();
		});
		
		// 일괄 선택
		$(".chk").on("click", function() {
			$(this).prop("checked");
			const checkCount = $(".chk:checked").length;
			if($(".chk:checked").length==$(".chk").length){
				$("#allChk").prop("checked", true);
			}else{
				$("#allChk").prop("checked", false);
			}
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
						const checkCount = $(".chk:checked").length;
						$("#checkCount").text(checkCount);
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
					if(basketNo == "") {
						// 장바구니에서 책을 선택하지 않을 경우 모달창 출력
						$("#givenMessage").text("책을 선택해주세요.");
						$("#modalButton").click();
					} else {
						location.href = "/orderPayMent.do?memberNo=" + memberNo
								+ "&basketNo=" + basketNo.join("/");						
					}
					//memberNo(헤더), bookNo
				});
	</script>
	
	
</body>
</html>