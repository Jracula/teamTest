<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/semi_join.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<div class="join-all-wrap">
		<div class="join-wrap">
			<div class="join-title">
				<h2>회원가입</h2>
			</div>
			<form action="/checkId.do" name="checkIdFrm">
				<input type="hidden" name="checkId">
			</form>
			<form action="/join.do" method="post">
				<div class="join-box">
					<table>
						<tr>
							<td>아이디 <sup>*</sup></td>
							<td><input type="text" name="memberId" id="memberId" class="inputBox" required> <%--<button type="button" class="double-check" id="double-check">중복체크</button>--%>
								<div class="comment" id="idCheck"></div></td>
						</tr>
						<tr>
							<td>비밀번호 <sup>*</sup></td>
							<td><input type="password" name="memberPw" class="inputBox"
								required>
								<div class="comment" id="pwCheck"></div></td>
						</tr>
						<tr>
							<td>비밀번호확인 <sup>*</sup></td>
							<td><input type="password" name="memberPwCheck"
								class="inputBox" required>
								<div class="comment" id="pwDoubleCheck"></div></td>
						</tr>
						<tr>
							<td>이름 <sup>*</sup></td>
							<td><input type="text" name="memberName" class="inputBox"
								required></td>
						</tr>
						<tr>
							<td>전화번호 <sup>*</sup></td>
							<td><input type="text" name="memberPhone" class="inputBox"
								required placeholder="숫자만 입력해주세요"></td>
						</tr>
						<tr>
							<td>이메일인증 <sup>*</sup></td>
							<td>
                              <input type="text" name="memberEmail" id="memberEmail" class="inputBox" required placeholder="이메일 형식 : lit@litbooks.com">
                         		<input type="text" name="checkEmail" id="checkEmail" class="inputBox" required placeholder="인증번호">						
                         		<button type="button" class="email-check" id="emailCheck">이메일인증</button>
                         		<button type="button" class="email-check" id="check" style="display:none;">확인</button>
								<div class="comment" id="email-time"></div>
							</td>
						</tr>
						<tr>
							<td>이용약관 <sup>*</sup></td>
							<td>
								<div class="agree-wrap">
									<div class="agree-box">
										<input type="checkbox" onclick="agreeAll();" id="all-agree"
											class="all-agree"> <label for="all-agree">선택포함 전체약관 동의</label>
									</div>
									<div class="agree-box" id="agreeBox">
										<input type="checkbox" id="agree-1" class="agreeCheck">
										<label for="agree-1">이용약관 동의(필수)</label> <span class="modal1">약관보기></span>

										<%--약관 모달 --%>
                                        <div class="agreeModal-wrap wrap1">
                                            <div class="agreeModal-1" >
                                                <div class="agreeModal-top">
                                                    <h3>이용약관 동의(필수)</h3>               
                                                </div>
                                                <div class="agreeModal-content">
                                                    <br>
                                                    <strong class="agreeModal-title">제1조 [목적]</strong>
                                                    <div class="content">
                                                        "이 약관은 주식회사 LITBOOKS(이하 주식회사 LITBOOKS 또는 주식회사 LITBOOKS가 운영하는 인터넷사이트를 “릿북스”라 함)가 운영하는 온라인쇼핑몰에서 제공하는 전자상거래 관련 서비스(이하 “서비스”라 함)를 이용함에 있어 컬리와 이용자의 권리, 의무 및 책임사항을 규정함을 목적으로 합니다."
                                                        
                                                    </div>
                                                    <br>
                                                    <strong class="agreeModal-title">제2조 [정의]</strong>
                                                    <div class="content">
                                                        <br>
                                                        1. “사이버몰”이란 컬리가 상품 또는 용역(이하 “상품 등” 이라 함)을 이용자에게 제공하기 위하여 컴퓨터 등 정보통신설비를 이용하여 상품 등을 거래할 수 있도록 설정한 가상의 영업장(http://www.kurly.com 등 컬리가 운영하는 웹사이트 및 모바일 웹, 앱 등을 모두 포함)을 말합니다.
                                                        2. "이용자"란 컬리에 접속하여 이 약관에 따라 컬리가 제공하는 서비스를 이용하는 고객을 말합니다.
                                                        3. “회원”이란 컬리에 회원등록을 한 자로서, 계속적으로 컬리가 제공하는 서비스를 이용할 수 있는 자를 말합니다.
                                                        4. "휴면회원"이란 컬리의 회원 중 최종접속일로부터 1년 이상 서비스 이용 기록이 없는 자를 말합니다. 휴면회원은 컬리의 정책에 따라 서비스 이용이 제한될 수 있으며, 다시 정상적인 회원 서비스를 이용하기 위하여는 거래 안전 등을 위하여 컬리가 정하는 본인확인 절차 등을 이행하여야 합니다.
                                                        5. “마켓플레이스 서비스”란 컬리가 제공하는 통신판매중개 서비스 및 관련 부가서비스 일체를 말합니다.
                                                    </div>
                                                    <br>
                                                    <strong class="agreeModal-title">제3조 [약관 등의 명시와 설명 및 개정]</strong>
                                                    <div class="content">
                                                        <br>
                                                        ① litbook은 이 약관의 내용과 상호 및 대표자 성명, 영업소 소재지 주소(소비자의 불만을 처리할 수 있는 곳의 주소를 포함), 전화번호/모사전송번호/전자우편주소, 사업자등록번호, 통신판매업 신고번호, 개인정보 보호책임자 등을 이용자가 쉽게 알 수 있도록 사이버몰의 초기 서비스화면(전면)에 게시합니다. 다만, 약관의 내용은 이용자가 연결화면을 통하여 볼 수 있도록 할 수 있습니다.
                                                        <br>
                                                        ② litbook은 필요한 경우 관련 법령을 위배하지 않는 범위에서 이 약관을 개정할 수 있습니다.
                                                        <br>
                                                        ④ 전항에 따라 공지된 적용일자까지 이용자가 명시적으로 거부의사를 표명하지 않을 경우에는 개정된 약관에 동의하는 것으로 간주하며, 변경된 약관에 동의하지 않는 회원은 회원 탈퇴를 요청할 수 있습니다.
                                                        <br>
                                                        ⑤ 이 약관에서 정하지 아니한 사항과 이 약관의 해석에 관하여는 전자상거래 등에서의 소비자보호에 관한 법률(이하 “전자상거래법”이라 함), 약관의 규제 등에 관한 법률, 공정거래위원회가 정하는 전자상거래 등에서의 소비자 보호지침 및 관계법령 또는 상관례에 따릅니다.
                                                        <br>
                                                        ⑥ litbook은 이 약관에 규정되지 않은 세부적인 운영정책, 이용약관, 규칙, 지침 및 서비스 이용과 관련된 전반적인 내용(이하 총칭하여 “운영정책”이라 함)을 제정하여 운영할 수 있으며, 해당 내용을 사이버몰에 게시합니다. 운영정책은 이 약관과 더불어 고객 서비스 이용계약(이하 “이용계약”이라 함)의 일부를 구성합니다.
                                                        <br>
  
                                                    </div>
                                                </div>
                                                <div class="resetBtn">
                                                <button type="button" class="reset" id="reset1">확인</button>
                                                <!--<input type="reset" class="reset" id="reset" value="확인">-->
                                            </div>
                                            </div>
                                        </div>


									</div>
									<div class="agree-box" id="agreeBox">
										<input type="checkbox" id="agree-2" class="agreeCheck">
										<label for="agree-2">이벤트,혜택 알림 수신 동의(선택)</label>
									</div>
									<div class="agree-box" id="agreeBox">
										<input type="checkbox" id="agree-3" class="agreeCheck">
										<label for="agree-3">성별,생년 정보 제공 동의(선택)</label> <span class="modal2">약관보기></span>
									</div>
									
										<%--약관 모달 --%>
                                        <div class="agreeModal-wrap wrap2">
                                            <div class="agreeModal-1" >
                                                <div class="agreeModal-top">
                                                    <h3>성별,생년 정보 제공 동의(선택)</h3>               
                                                </div>
                                                <div class="agreeModal-content">
                                                    <br>
                                                    <strong class="agreeModal-title">개인정보 수집 이용 목적</strong>
                                                    <div class="content">
														신규 서비스 개발 및 마케팅에의 활용. 신규 서비스 개발, 통계학적 특성, 이용 형태 등에 따른 맞춤형 서비스 제공, 광고 게재, 이벤트 참여기회 제공, 접속빈도 파악, 회원의 서비스 이용에 대한 통계                                                        
                                                    </div>
                                                    <br>
                                                    <strong class="agreeModal-title">선택 수집 항목 </strong>
                                                    <div class="content">
                                                       		생년, 성별
                                                   		 </div>
                                                    <br>
                                                    <strong class="agreeModal-title">이용 보유 기</strong>
                                                    <div class="content">
                                                       		탈퇴시 즉시 파기
                                                    </div>
  														<br>
  														자세한 내용은 개인정보 처리방침을 참고하시기 바랍니다.
                                                </div>
                                                <div class="resetBtn">
                                                <button type="button" class="reset" id="reset2">확인</button>
                                                <!--<input type="reset" class="reset" id="reset" value="확인">-->
                                            </div>
                                            </div>
                                        </div>
									
									
									
									
									<div class="agree-box" id="agreeBox">
										<input type="checkbox" id="agree-4" class="agreeCheck">
										<label for="agree-3">개인 정보 수집 및 이용 동의(필수)</label> <span class="modal3">약관보기></span>
									</div>
									
										<%--약관 모달 --%>
                                        <div class="agreeModal-wrap wrap3">
                                            <div class="agreeModal-1" >
                                                <div class="agreeModal-top">
                                                    <h3>개인정보 수집 및 이용 동의 (필수)</h3>               
                                                </div>
                                                <div class="agreeModal-content">
                                                    <br>
                                                    <strong class="agreeModal-title">수집 목적</strong>
                                                    <div class="content">
														회원 가입의사 확인, 이용자 식별 및 본인여부, 회원자격 유지·관리, 계약 이행 및 약관변경 등의 고지를 위한 연락, 본인의사 확인 및 민원 등의 고객 고충 처리, 부정이용 방지, 비인가 사용방지 및 서비스 제공 및 계약의 이행, 서비스 이용 및 상담, 문의, 후기 등 원활한 의사소통 경로 확보, 맞춤형 회원 서비스 제공, 거점 기반 서비스 제공                                                        
                                                   <br>
                                                   		서비스방문 및 이용기록 분석, 부정이용 방지 등을 위한 기록 관리
                                                    </div>
                                                    <br>
                                                    <strong class="agreeModal-title">수집 항목</strong>
                                                    <div class="content">
                 									 - 이름, 아이디, 비밀번호, 휴대폰번호, 이메일, 주소
                 									 - 서비스 이용기록, IP주소, 쿠키, MAC주소, 모바일 기기정보(앱 버전, OS 버전), ADID/IDFA(광고식별자)
                                                    </div>
                                                    <br>
                                                    <strong class="agreeModal-title">보유 기간</strong>
                                                    <div class="content">
                                                      - 회원탈퇴 시 즉시 파기
                                                      - 부정이용 방지를 위하여 30일 동안 보관 (아이디, 휴대폰 번호) 후 파기 
                                                      - 회원 탈퇴 즉시 또는 이용 목적 달성 즉시 파기
                                                    </div>
                                                    <br>
													※ 단, 회원 탈퇴와 별개로 분쟁 조정, 고객문의 대응 및 법령 준수 이력 증빙을 위하여 이메일, 문자, 알림톡 발송이력은 발송일로부터 6개월 보관(이름, 아이디, 휴대폰 번호, 이메일) 후 파기합니다.
                                                    <br>
                                                    ※ 서비스 제공을 위해서 필요한 최소한의 개인정보입니다. 동의를 해 주셔야 서비스를 이용하실 수 있으며, 동의하지 않으실 경우 서비스에 제한이 있을 수 있습니다.
                                                </div>
                                                <div class="resetBtn">
                                                <button type="button" class="reset" id="reset3">확인</button>
                                                <!--<input type="reset" class="reset" id="reset" value="확인">-->
                                            </div>
                                            </div>
                                        </div>
									
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="btn-box">
					<button type="submit" class="join-btn">가입하기</button>
				</div>
			</form>
		</div>
	</div>




	<script>
	
		const result = [false,false,false,false,false,false];
		
		//아이디 유효성, 중복 체크
		$("#memberId").on("keyup", function() {
			const memberId = $(this).val();
		console.log(memberId);
			const idReg = /^(?=.*[0-9]+)[a-z][a-z0-9]{4,12}$/g;
			const inputId = $(this).val();
			const check = idReg.test(inputId);
			if(check){
				$.ajax({
					url : "/checkId.do",
					type : "get",
					data : {
						memberId : memberId
					},
					success : function(data) {
						if (data == "1") {
							$("#idCheck").text("이미 사용중인 아이디 입니다.");
							$("#idCheck").css("color", "red");
						} else if (data == "0") {
							$("#idCheck").text("사용가능한 아이디 입니다.");
							$("#idCheck").css("color", "rgb(32, 90, 167)");
							result[0] = true;
						}
					}
				});		
				}else{
						$("#idCheck").text("아이디는 영어 소문자/숫자로 4~12글자");
						$("#idCheck").css("color", "red");
						result[0] = false;
			}
		});

		
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

			//전체 약관동의 체크
			function agreeAll() {
				if ($(".all-agree").is(':checked')) {
					$("input[type=checkbox]").prop("checked", true);
				} else {
					$("input[type=checkbox]").prop("checked", false);
				}
			}

				
			//약관보기 모달
			$(".modal1").on("click", function(){
			    $(".wrap1").css("display","flex");
			});
		
			$("#reset1").on("click", function(){
			    $(".wrap1").css("display","none");
			});
			
			
			$(".modal2").on("click", function(){
			    $(".wrap2").css("display","flex");
			});
			
			$("#reset2").on("click", function(){
			    $(".wrap2").css("display","none");
			});
			
			
			$(".modal3").on("click", function(){
			    $(".wrap3").css("display","flex");
			});
			
			$("#reset3").on("click", function(){
			    $(".wrap3").css("display","none");
			});
			
			
			
			
			//submit
			$("[type=submit]").on("click",function(event){ //하나라도 TRUE가 아닐때
				if(!(result[0] && result[1] && result[2] && result[3] &&$("#agree-1").prop("checked") && $("#agree-4").prop("checked"))){
			        event.preventDefault();
			        alert("입력하신 정보를 확인하세요");		        
				}
			});
			
			
			

	</script>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>