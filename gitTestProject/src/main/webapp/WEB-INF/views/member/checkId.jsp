<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String memberId = (String)request.getAttribute("memberId");
    	int result = (int)request.getAttribute("result");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	#check-container{
		text-align : center;
		padding-top : 50px;
		width : 400px;
		margin : 0 auto;
		font-size : 15px;
	}
	.id-wrap{
		display : flex;
	}
	.id-wrap>input{
		width: 70%;
	}
	.id-wrap>button{
		width: 30%;
	}
	.close-btn{
		background-color: rgb(32, 90, 167);	
	    border-radius: 5px;
	    width: 100px;
	    height: 40px;
	    border : 0px;
	    color : white;
	    font-weight: 600;
	    cursor: pointer;
	}
</style>

</head>
<body>
	<div id="check-container">
		<%if(result == 0) {%>
			<div>
				[<span class="join-text"><%=memberId %></span>]는 사용 가능한 아이디입니다.
			</div>
			<br><br>
			<button type="button" class="close-btn" id="closeBtn">닫기</button>
			
			<script>
				$("#closeBtn").on("click",function(){
					const id = $(".join-text").text();
					//부모창의 요소를 선택하는 방법
					const inputId = $("#memberId",opener.document);
					inputId.val(id);
					self.close();
				});
			</script>
			
			
		<%}else if(result == 1){ %>
			<div>
				[<span class="fc-6 f-bold"><%=memberId %></span>]는 이미 사용중인 아이디입니다.
				<br>
				<form action="/checkId.do">
					<div class="id-wrap">
						<input type="text" name="checkId" class="input-box">
						<button type="submit" class="btn bc2">조회</button>
					</div>
				</form>
			</div>
		<%} %>
	</div>
</body>
</html>