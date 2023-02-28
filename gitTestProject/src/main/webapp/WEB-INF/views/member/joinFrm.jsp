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
            <form action="#" method="post">
                <div class="join-box">
                    <table>
                        <tr>
                            <td>아이디 <sup>*</sup></td>
                            <td>
                                <input type="text" name="memberId" class="inputBox" required>
                                <button type="button" class="double-check" id="">중복체크</button>
                                <div class="comment" id="idCheck"></div>
                            </td>
                        </tr>
                        <!--
                        <tr> 
                            <td><td>
                                <span class="idCheck">이미사용중인</span>
                            </td>
                        </tr>
                        -->
                        <tr>
                            <td>비밀번호 <sup>*</sup></td>
                            <td>
                                <input type="password" name="memberPw"  class="inputBox" required>
                                <div class="comment" id="pwCheck"></div>
                            </td>
                        </tr>
                        <tr>
                            <td>비밀번호확인 <sup>*</sup></td>
                            <td>
                                <input type="password" name="memberPwCheck"  class="inputBox" required>
                                <div class="comment" id="pwDoubleCheck"></div>
                            </td>
                        </tr>
                        <tr>
                            <td>이름 <sup>*</sup></td>
                            <td>
                                <input type="text" name="memberName" class="inputBox" required>
                            </td>
                        </tr>
                        <tr>
                            <td>이메일인증 <sup>*</sup></td>
                            <td>
                                <input type="text" name="memberId" class="inputBox" required placeholder="이메일 형식 : lit@litbooks.com">
                                <button type="button" class="email-check">이메일인증</button>
                                <div class="idCheck"></div>
                            </td>
                        </tr>
                        <tr>
                            <td>이용약관 <sup>*</sup></td>
                            <td>
                                <div class="agree-wrap">
                                <div class="agree-box">
                                        <input type="checkbox" onclick="agreeAll();" id="all-agree" class="all-agree">
                                        <label for="all-agree">선택포함 전체약관 동의</label>
                                </div>
                                <div class="agree-box" id="agreeBox">
                                        <input type="checkbox" id="agree-1" class="agreeCheck">
                                        <label for="agree-1">이용약관 동의(필수)</label>
                                        <span class="modal">약관보기></span>
                                        
                                </div>
                                <div class="agree-box" id="agreeBox">
                                    <input type="checkbox" id="agree-2" class="agreeCheck">
                                    <label for="agree-2">이벤트,혜택 알림 수신 동의(선택)</label>
                                </div>
                                <div class="agree-box" id="agreeBox">
                                    <input type="checkbox" id="agree-3" class="agreeCheck">
                                    <label for="agree-3">성별,생년 정보 제공 동의(선택)</label>
                                    <span class="modal">약관보기></span>   
                                </div>
                                <div class="agree-box" id="agreeBox">
                                    <input type="checkbox" id="agree-4" class="agreeCheck">
                                    <label for="agree-3">개인 정보 수집 및 이용 동의(필수)</label>
                                    <span class="modal">약관보기></span>   
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
	function agreeAll(){
	    if($(".all-agree").is(':checked')){
	        $("input[type=checkbox]").prop("checked",true);
	    }else{
	        $("input[type=checkbox]").prop("checked",false);
	    }
	}
	
	$("[name=memberId]").on("keyup", function(){
	    const idReg = /^(?=.*[0-9]+)[a-z][a-z0-9]{5,10}$/g;
	    const inputId = $(this).val();
	    const check = idReg.test(inputId);

	        if(check){
	            $("#idCheck").text("");
	            
	            result[0] = true;

	        }else{
	            $("#idCheck").text("아이디는 영어 소문자/숫자로 4~12글자 입니다.");
	            $("#idCheck").css("color","red");
	            result[0] = false;
	        }
	});
	

	</script>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>