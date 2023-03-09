<%@page import="com.litbooks.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Member m = (Member)session.getAttribute("m");
    %> <!-- 로그인 정보 -->

    <!-- 구글 아이콘 -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- jquery -->
    <script src="/js/jquery-3.6.0.js"></script>
    <!-- 기본 CSS -->
    <link rel="stylesheet" href="/css/default.css" />
    <!-- 기본 js -->
    <script src="/js/default.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> 




    <header>
    
        <style>
    	header *{
    		box-sizing:border-box;
    	}

    </style>
    
      <div class="site-logo">
        <a href="/"><img alt="logo" src="/mainImage/logo.png" style="vertical-align: middle;"></a>
      </div>
      <nav>
      	<form action="/bookSearchInDetail.do" method="get" onsubmit="return checkKeyword();">
      		<div class="search-bar" style="width: 400px; ">
      			<input type="text" name="searchKeyword" placeholder="책제목을 입력하세요.">
      			<button type="submit" ><span class="material-symbols-outlined search">search</span></button>
			</div>
      	</form>
      	<div class="header-link">
      <%if(m == null){%>
   		<!-- 모달 id와 target 이름 맞추기 -->

        <a class="login-b headerBtn" href="/signinFrm.do"><span class="material-symbols-outlined">face</span></span></a>
        <a class="join-b headerBtn" href="/joinFrm.do"><span class="material-symbols-outlined">person_add</span></a>

      <%}else {%>
      	<!-- 로그인 성공시 회원 이름 출력 -->
      	<!-- 마이페이지1 : /mypage1.do -->
      	<!-- <a class="btn bc11" href="/mypage1.do"><%=m.getMemberName() %></a> --> 
      	<!-- 마이페이지2 : 세션에서 꺼낸 memberId를 키값에넣어 보내줌 -->
		
			<%if(m.getMemberLevel() ==2 ) {%>
		      	<a class="headerBtn" href="/cart.do?memberNo=<%=m.getMemberNo()%>"><span class="material-symbols-outlined">shopping_cart</span></a>
		      	<a class="headerBtn" href="/mypage.do?memberId=<%=m.getMemberId()%>"><span class="material-symbols-outlined">face</span></span></a>
		      	<a class="headerBtn" href="/logout.do"><span class="material-symbols-outlined">logout</span></a>
		<!-- memberNo 가져오기용 -->	<span id="memberNo" style="display: none;"><%=m.getMemberNo() %></span>
		<!-- memberLevel 가져오기용 --> <p id="memberLevel" style="display: none;"><%=m.getMemberLevel() %></p>
			<%}else{ %>
				<a class="headerBtn" href="/mypage.do?memberId=<%=m.getMemberId()%>"><span class="material-symbols-outlined">manage_accounts</span></a>
		      	<a class="headerBtn" href="/logout.do"><span class="material-symbols-outlined">logout</span></a>
			<%} %>
      <%} %>
      </div>
      </nav>
      
      
    </header>

	<script>
		function checkKeyword() {	//검색바에서 공백을 제외하고 2자 이상으로 검색 요청 
			const keywordReg = /\S{2,}/;
			const keyword = $("form>div>input").val();
			const check = keywordReg.test(keyword);
			if (!check) {
				alert("2자 이상의 검색어로 입력해주십시오.");
				return false;
			}
			return true;
		}
	</script> 
    
    <!-- 로그인 안되어있을때만 모달 나오게함 -->
    <%--
    <%if(m == null){%>
   	<!-- 모달 -->
    <div id="login-modal" class="modal-bg">
      <div class="modal-wrap">
        <div class="modal-head">
          <h2>SIGN IN</h2>
          <span class="material-icons close-icon modal-close">close</span>
        </div>
        
        <form action="/signin.do" method="post">
        <div class="modal-content">
          <div class="input-box">
          	<label for="signId">아이디</label>
          	<input type="text" name="signId" id="signId" class="input-form" placeholder="아이디 입력">
          </div>
          <div class="input-box">
          	<label for="signPw">비밀번호</label>
          	<input type="password" name="signPw" id="signPw" class="input-form" placeholder="비밀번호 입력">
          </div>
          <div class="input-box link-box">
          <a href="#">아이디/비밀번호찾기</a>
          </div>
        </div>
        <div class="modal-foot">
          <button type="submit" class="btn bc11">로그인</button> 
          <!-- form태그안에 button태그는 자동으로 submit 되므로 type 설정해주기 -->
        <button type="button" class="btn bc1 modal-close">취소</button>
        </div>
        </form>
      </div>
    </div>
    <%} %>   --%>