<%@page import="kr.or.iei.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Member m = (Member)session.getAttribute("m");
    %> <!-- 로그인 정보 -->

    <!-- 구글 아이콘 -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- jquery -->
    <script src="/js/jquery-3.6.0.js"></script>
    <!-- 기본 CSS -->
    <link rel="stylesheet" href="/css/default.css" />
    <!-- 기본 js -->
    <script src="/js/default.js"></script>


    <header>
      <div class="site-logo">
        <a href="/">LITBOOKS</a>
      </div>
      <nav>
      	<div class="search-bar" style="text-align: center; text-indent:-100px;">Q 검색바 위치</div>
      </nav>
      <div class="header-link">
      <%if(m == null){%>
   		<!-- 모달 id와 target 이름 맞추기 -->
        <a class="btn bc11" href="/signinFrm.do">로그인</a>
        <a class="btn bc11" href="/signupFrm.do">회원가입</a>
      <%}else {%>
      	<!-- 로그인 성공시 회원 이름 출력 -->
      	<!-- 마이페이지1 : /mypage1.do -->
      	<!-- <a class="btn bc11" href="/mypage1.do"><%=m.getMemberName() %></a> --> 
      	<!-- 마이페이지2 : 세션에서 꺼낸 memberId를 키값에넣어 보내줌 -->
      	<a class="btn bc11" href="#">장바구니</a>
      	<a class="btn bc11" href="/mypage2.do?memberId=<%=m.getMemberId()%>"><%=m.getMemberName() %></a>
      	<a class="btn bc11" href="/logout.do">LOGOUT</a>
      <%} %>
      </div>
    </header>
    
     
    
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