<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Member member = (Member)request.getAttribute("m");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/semi_mypage.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
<div class="update-all-wrap">
	<form action="#" method="post">
		<div class="update-title">
		<%if(member.getMemberLevel() == 1) {%>
					<h2>관리자 정보</h2>
				<%}else{ %>
					<h2>마이페이지</h2>
				<%} %>
			
		</div>
		<div class="join-box">
			<table>
				<tr>
					<td>아이디</td>
					<td>
						<input type="text" name="memberId" id="memberId"class="inputBox" value="<%=member.getMemberId()%>" readonly>
						<div class="comment" id="idCheck"></div>
					</td>
				</tr>

				<tr>
					<td>이름</td>
					<td>
						<input type="text" name="memberName" class="inputBox" value="<%=member.getMemberName()%>" readonly>
					</td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td>
						<input type="text" name="memberPhone" class="inputBox" value="<%=member.getMemberPhone()%>" readonly>
					</td>
				</tr>
				<tr>
					<td>가입일</td>
					<td>
						<input type="text" name="enrollDate" class="inputBox" value="<%=member.getEnrollDate()%>" disabled>
					</td>
				</tr>
				<tr>
					<td>이메일인증</td>
					<td>
						<input type="text" name="memberEmail"  class="inputBox" value="<%=member.getMemberEmail()%>" readonly>
					</td>
				</tr>

			</table>
		</div>
			<div class="btnBox">
				<%if(m.getMemberLevel() == 1) {%>
					<a class="button" href="/updateMemberFrm.do?memberId=<%=m.getMemberId()%>" id="update-btn">관리자 정보 수정</a>
					<a class="button admin-btn" href="/selectAllMember.do?reqPage=1">전체회원 정보</a>
					<a class="button admin-btn" href="/selectAllRecomm.do?reqPage=1">평점(후기)조회</a>
					<a class="button admin-btn" href="/adminOrderList.do?reqPage=1">구매(결제)내역 조회</a>
					<a class="button admin-btn" href="/adminOrderUpdate.do">구매(결제)내역 변경</a>
					<a class="button admin-btn" style="color: #C00065; border-color: #C00065" href="/bookWriteFrm.do">신규 도서 등록</a>
				<%}else{ %>
					<a class="button" href="/updateMemberFrm.do?memberId=<%=m.getMemberId()%>" id="update-btn">내정보 수정</a>
					<a class="button" href="/orderList.do?reqPage=1&memberNo=<%=m.getMemberNo() %>" id="list-btn">구매내역</a>
					<button type="button" class="delBtn" id="delete-btn">회원탈퇴</button>
					<%-- <a class="button" href="/deleteMember.do?memberId=<%=m.getMemberId()%>" id="delete-btn">회원탈퇴</a>--%>
				<%} %>
				<div class="del-modalWrap">
           	     <div class="del-modal" >
                       <div class="del-top">
                             <h3>정말,,탈퇴하시겠습니까?</h3>               
                        </div>
                        <div class="btnDiv">
	                        <a class="okBtn" href="/deleteMember.do?memberId=<%=m.getMemberId()%>" id="delete-btn">확인</a>
	                  		<button type="button" class="reset" id="reset">취소</button>
                  		</div>
                 </div>
			</div>
			</div>
		</form>		
	</div>



	<script>
	//비밀번호 유효성 검사
	$("[name=memberPw]").on("keyup",function() {
			const pwReg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{10,}$/;
			const inputPw = $(this).val();
			const check = pwReg.test(inputPw);
			if (check) {
				$("#pwCheck").text("");
				result[1] = true;
			} else {
				$("#pwCheck").text("비밀번호는 영어 소문자 또는 대문자,숫자,특수문자($@$!%*#?&)포함 10자리 이상");
				$("#pwCheck").css("color", "red");
				result[1] = false;
				}
			});

	
	//비밀번호 중복 체크
	$("[name=memberPwCheck]").on("keyup", function() {
		const pwInput = $("[name=memberPw]").val();
		if ($(this).val() == pwInput) {
			$("#pwDoubleCheck").text("비밀번호가 일치합니다");
			$("#pwDoubleCheck").css("color", "rgb(32, 90, 167)");
			result[2] = true;
		} else {
			$("#pwDoubleCheck").text("비밀번호가 일치하지 않습니다");
			$("#pwDoubleCheck").css("color", "red");
			result[2] = false;
		}
	});
	
	//이메일 인증
	let mailCode;
	$("#emailCheck").on("click",function(){
		$(this).hide();
		$("#check").show();
		const email = $("#memberEmail").val();
		$.ajax({
			url : "/emailCheck.do",
			data : {email : email},
			type : "post",
			success : function(data){
				if(data == "null"){
					alert("이메일 주소를 확인하세요");
				}else{
					mailCode = data;
					$("#email-time").text();
					authTime();
				}
			},
			error : function(){
				consol.log("에러발생");
			}
		});
	});
	
	
	let intervalId;
	function authTime() {
		$("#email-time").html("<span id='min'>3</span> : <span id='sec'>00</span>");
		intervalId = window.setInterval(function() {
			timeCount();
		},1000) //1초마다 timeCount함수 반복
	}
	
	function timeCount() {
		const min = $("#min").text();
		const sec = $("#sec").text();
		if(sec == "00"){
			if(min != "0"){
				const newMin = Number(min) - 1;
				$("#min").text(newMin);
				$("#sec").text(59);
			}else{
				window.clearInterval(intervalId);
				mailCode = null;
				$("#email-time").text("인증 시간 만료");
				$("#email-time").css("color","red");
			}
		}else{
			const newSec = Number(sec) - 1;
			if(newSec < 10){
				$("#sec").text("0"+newSec);
			}else{
			$("#sec").text(newSec);	
			}
		}
	}
	
	//인증하기 버튼
	$("#check").on("click",function(){
		if(mailCode == null){
			$("#email-time").text("");
			$("#email-time").css("color","red");
		}else{
			const checkEmail = $("#checkEmail").val();
			if(checkEmail == mailCode){
				$("#email-time").prop("readonly",true); 
				$("#email-time").text("인증 완료");
				$("#email-time").css("color","green");
				clearInterval(intervalId);
				result[3] = true;
			}else{
				$("#email-time").text("인증 실패");
				$("#email-time").css("color","red");
				result[3] = false;
			}
		}
	});
	
	$("#delete-btn").on("click",function(){
		$(".del-modalWrap").css("display","flex");
	});
	
	$("#reset").on("click",function(){
		$(".del-modalWrap").css("display","none");
	});
	

	
	
	</script>


	<%@ include file="/WEB-INF/views/common/footer.jsp"%>

</body>
</html>