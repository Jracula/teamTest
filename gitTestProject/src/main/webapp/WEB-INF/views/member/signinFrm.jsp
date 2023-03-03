<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style >
.login_wrap{
    width: 1200px;
    margin: 0 auto;
    text-align: center;
    margin-top: 85px;
}
.login_title>h2{
    font-size: 30px;
    color: rgb(32, 90, 167);
    margin-bottom: 50px;
}

.input-box{
    margin-top: 15px;
}

.link-box{
    margin-top: 40px;
    margin-bottom: 30px;
    text-decoration: none;
}

.login-form{
    width: 400px;
    height: 55px;
    border: 1px solid rgb(211, 211, 211);
    border-radius: 5px;    
}

[type=text]{
    padding-left: 10px;
}

[type=password]{
    padding-left: 10px;
}

.link{
    text-decoration: none;
    font-size: 15px;
    color: rgb(131, 131, 131);
    font-weight: 400;
}

.link:hover{
    color: rgb(32, 90, 167);
}


.login-btn{
    display: inline;
    width: 400px;
    height: 55px;
    background-color: rgb(32, 90, 167);
    border-radius: 5px;
    border: 0px;
    font-size: 17px;
    font-weight: 400;
    color: white;
    cursor: pointer;
}
.join-btn{
    display: block;
    margin: 0 auto;
    margin-top: 5px;
    width: 400px;
    height: 55px;
    border-radius: 5px;
    border: 0px;
    border: 1.5px solid  rgb(32, 90, 167);
    background-color: white;
    font-size: 17px;
    font-weight: 400;
    color: rgb(32, 90, 167);
    cursor: pointer;
    margin-bottom : 150px;
}
.join-btn:hover{
    background-color: rgb(32, 90, 167);
    color: white;
}

</style>
</head>
<body>
		<%@ include file="/WEB-INF/views/common/header.jsp" %>
		    <div class="login_wrap">
        <div class="login_title">
            <h2>로그인</h2>
        </div>
            <form action="/signin.do" method="post">
                <div class="input-box">
					<input type="text" name="loginId" id="loginId" class="login-form" placeholder="아이디를 입력하세요">
				</div>
                <!--
                <div class="login-comment">
                    <span class="idComment">코멘트</span>
                </div>
                -->
                <div class="input-box">
					<input type="password" name="loginPw" id="loginPw" class="login-form" placeholder="비밀번호를 입력하세요">
				</div>
                <!--
                <div class="login-comment">
                    <span class="idComment">코멘트</span>
                </div>
                -->
				<div class="link-box">
					<a href="#" class="link">아이디 찾기 ㅣ 비밀번호 찾기</a>
				</div>	
                <div class="btn-box">
                    <button type="submit" class="login-btn">로그인</button>
                </div>

            </form>                 
            	<div class="btn-box">
                    <button type="submit" class="join-btn">회원가입</button> 
                </div>
   		 </div>
   		 
   		 <script>
   		 	$(".join-btn").on("click",function(){
   		 		location.href="/joinFrm.do";
   		 	});
   		 
   		 </script>
		
		
		
		<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>