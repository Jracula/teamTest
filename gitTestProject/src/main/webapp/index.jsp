<%@page import="com.litbooks.book.vo.Book"%>
<%@page import="oracle.net.aso.b"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    Book b = (Book)request.getAttribute("b");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LiTBOOKS</title>
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
   <link rel="stylesheet" href="/css/boot.css" />

<style>

.main-top{
	width: 1200px;
	margin : 0 auto;
}
.main-title{
	width: 1200px;
	margin : 0 auto;
}

.star {
  font-variation-settings:
  'FILL' 1,
  'wght' 400,
  'GRAD' 0,
  'opsz' 48
}

</style>

</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<%-- 
	<div class="main-top">
		<h1>LITBOOKS</h1>
	</div>
	--%>
	<hr>
	<%-- <a href="/test.do">서블릿테스트</a>--%>
	<div class="container">
	<div class="main-title">

  		<%--<h2>LITBOOKS</h2>   --%>
  	</div>
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">
      <div class="item active" onclick="location.href='/bookDetail.do?bookNo=167'">
        <img src="/mainImage/main-banner1.jpg" alt="banner1" style="width:100%;">
      </div>

      <div class="item" onclick="location.href='/bookDetail.do?bookNo=143'">
        <img src="/mainImage/main-banner2.jpg" alt="banner2" style="width:100%;">
      </div>
    
      <div class="item" onclick="location.href='/bookDetail.do?bookNo=168'">
        <img src="/mainImage/main-banner3.jpg" alt="banner3" style="width:100%;">
      </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
  
  <div class="main-content">
	  <div class="main-icon">
	  	<%-- <h2>여기는 신간, 베스트, 이벤트, 신간캘린더 넣을 자리?</h2> --%>
	  	<a href="/"><img alt="icon" src="/mainImage/main-icon1.png" style="width: 130px;"></a>
	  	<a href="/"><img alt="icon" src="/mainImage/main-icon2.png" style="width: 130px;"></a>
	  	<a href="/"><img alt="icon" src="/mainImage/main-icon3.png" style="width: 130px;"></a>
	  	<a href="/"><img alt="icon" src="/mainImage/main-icon4.png" style="width: 130px;"></a>
	  	<a href="/"><img alt="icon" src="/mainImage/main-icon5.png" style="width: 130px;"></a>
	  </div>
  </div>
  
  
  <div class="content-1">
  	<div class="content-title">
  		<h3>숨겨진 lit의 발견</h3>
  	</div>
  	<div class="content-list">
  		 <div class="product">
          <a href="/bookDetail.do?bookNo=141" style="text-decoration: none; color: #252A34">
            <div class="book-img">
             	<img src="/mainImage/하얼빈.PNG" width="180px" height="280px;" style="border-radius: 10px;">
            </div>
            <div class="book-info">
                 <div class="info">독립을 향한</div>
                 <div class="info">방아쇠를 당기다</div>
             </div>
           </a>
          </div>
          <div class="product">
          <a href="/bookDetail.do?bookNo=142" style="text-decoration: none; color: #252A34">
            <div class="book-img">
             	<img src="/mainImage/세이노.PNG" width="180px"  height="280px;" style="border-radius: 10px;">
            </div>
            <div class="book-info">
                 <div class="info">재야의 명저</div>
                 <div class="info">세이노의 가르침</div>
             </div>
           </a>
          </div>
          <div class="product">
          <a href="/bookDetail.do?bookNo=143" style="text-decoration: none; color: #252A34">
            <div class="book-img">
             	<img src="/mainImage/동급생.PNG" width="180px"  height="280px;" style="border-radius: 10px;">
            </div>
            <div class="book-info">
                 <div class="info">가장 충격적인</div>
                 <div class="info">마지막문장</div>
             </div>
           </a>
          </div>
          <div class="product">
          <a href="/bookDetail.do?bookNo=150" style="text-decoration: none; color: #252A34">
            <div class="book-img">
             	<img src="/mainImage/아침점심저녁.jpg" width="180px"  height="280px;" style="border-radius: 10px;">
            </div>
            <div class="book-info">
                 <div class="info">아침 점심 저녁</div>
                 <div class="info">허경영!허경영!허경영!</div>
             </div>
           </a>
          </div>
          <div class="product">
          <a href="/bookDetail.do?bookNo=159" style="text-decoration: none; color: #252A34">
            <div class="book-img">
             	<img src="/mainImage/너가좋아했으면.PNG" width="180px"  height="280px;" style="border-radius: 10px;">
            </div>
            <div class="book-info">
                 <div class="info">방랑시인의 </div>
                 <div class="info">너가 좋아했으면</div>
             </div>
           </a>
          </div>
          <div class="product">
          <a href="/bookDetail.do?bookNo=154" style="text-decoration: none; color: #252A34">
            <div class="book-img">
             	<img src="/mainImage/원씽.png" width="180px" height="280px;" style="border-radius: 10px;">
            </div>
            <div class="book-info">
                 <div class="info">모든 일을 시작하게</div>
                 <div class="info">하는 단 하나의 도미노 </div>
             </div>
           </a>
          </div>  
  	</div>
  </div>
  
  <div class="content-2">
  	<div class="content-title">
  		<h3>지금 많이 읽는 작품</h3>
  	</div>
  	<div class="list-wrap " >
	  	<div class="book-list" style="width: 280px;" >
	  		<div class="img" >
	  			<img alt="" src="/mainImage/세이노.PNG" width="120px;"  height="188px;" style="float : left; ">
	  		</div>
	  		<div class="count">
		  		<h3>1</h3>
		  	</div>
		  	<div class="list-con" style="float : left; ">
		    	<a href="/bookDetail.do?bookNo=142" class="con con-1" style="text-decoration: none; color: #252A34">세이노의<br> 가르침</a>
		    	<p class="con con-2">세이노<p>
		    	<p><span class="material-symbols-outlined star">grade</span><span class="score"> 4.3</span></p>
		    </div>
	  	</div>
	  	<div class="book-list" style="width: 280px;">
	  		<div class="img" >
  			<img alt="" src="/mainImage/불편한.png" width="120px;"  height="188px;" style="float : left; ">
	  		</div>
	  		<div class="count">
		  		<h3>3</h3>
		  	</div>
		  	<div class="list-con" style="float : left; ">
		    	<a href="/bookDetail.do?bookNo=151" class="con con-1" style="text-decoration: none; color: #252A34">불편한 편의점</a>
		    	<p class="con con-2">김호연<p>
		    	<p><span class="material-symbols-outlined star">grade</span><span class="score"> 4.5</span></p>
		    </div>
	  	</div>
	  	<div class="book-list" style="width: 280px;" >
	  		<div class="img" >
	  			<img alt="" src="/mainImage/헤일메리.png" width="120px;" height="188px;"  style="float : left; ">
	  		</div>
	  		<div class="count">
		  		<h3>5</h3>
		  	</div>
		  	<div class="list-con" style="float : left; ">
		    	<a href="/bookDetail.do?bookNo=153" class="con con-1" style="text-decoration: none; color: #252A34">프로젝트<br>헤일메리</a>
		    	<p class="con con-2">앤디 위어<p>
		    	<p><span class="material-symbols-outlined star">grade</span><span class="score"> 4.8</span></p>
		    </div>
	  	</div>
	  	<div class="book-list" style="width: 280px;" >
	  		<div class="img" >
	  			<img alt="" src="/mainImage/원씽.png" width="120px;" height="188px;" style="float : left; ">
	  		</div>
	  		<div class="count">
		  		<h3>7</h3>
		  	</div>
		  	<div class="list-con" style="float : left; ">
		    	<a href="/bookDetail.do?bookNo=154" class="con con-1" style="text-decoration: none; color: #252A34">원씽</a>
		    	<p class="con con-2">게리 켈러,<br>제이 파파산<p>
		    	<p><span class="material-symbols-outlined star">grade</span><span class="score"> 4.3</span></p>
		    </div>
	  	</div>
	  	
    </div>
    <br>
    
      	<div class="list-wrap " >
	  	<div class="book-list" style="width: 280px;" >
	  		<div class="img" >
	  			<img alt="" src="/mainImage/악인론.png" width="120px;" height="188px;" style="float : left; ">
	  		</div>
	  		<div class="count">
		  		<h3>2</h3>
		  	</div>
		  	<div class="list-con" style="float : left; ">
		    	<a href="/bookDetail.do?bookNo=155" class="con con-1" style="text-decoration: none; color: #252A34">악인론</a>
		    	<p class="con con-2">손수현<p>
		    	<p><span class="material-symbols-outlined star">grade</span><span class="score"> 4.3</span></p>
		    </div>
	  	</div>
	  	<div class="book-list" style="width: 280px;" >
	  		<div class="img" >
  			<img alt="" src="/mainImage/희망의끈.png" width="120px;"  height="188px;" style="float : left; ">
	  		</div>
	  		<div class="count">
		  		<h3>4</h3>
		  	</div>
		  	<div class="list-con" style="float : left; ">
		    	<a href="/bookDetail.do?bookNo=156" class="con con-1" style="text-decoration: none; color: #252A34">희망의 끈</a>
		    	<p class="con con-2">히가시노 게이고<p>
		    	<p><span class="material-symbols-outlined star">grade</span><span class="score"> 4.5</span></p>
		    </div>
	  	</div>
	  	<div class="book-list" style="width: 280px;" >
	  		<div class="img" >
	  			<img alt="" src="/mainImage/돈의속성.png" width="120px;" height="188px;" style="float : left; ">
	  		</div>
	  		<div class="count">
		  		<h3>6</h3>
		  	</div>
		  	<div class="list-con" style="float : left; ">
		    	<a href="/bookDetail.do?bookNo=157" class="con con-1" style="text-decoration: none; color: #252A34">돈의 속성</a>
		    	<p class="con con-2">김승호<p>
		    	<p><span class="material-symbols-outlined star">grade</span><span class="score"> 4.8</span></p>
		    </div>
	  	</div>
	  	<div class="book-list" style="width: 280px;" >
	  		<div class="img" >
	  			<img alt="" src="/mainImage/슈퍼해빗.png" width="120px;" height="188px;" style="float : left; ">
	  		</div>
	  		<div class="count">
		  		<h3>4</h3>
		  	</div>
		  	<div class="list-con" style="float : left; ">
		    	<a href="/bookDetail.do?bookNo=158" class="con con-1" style="text-decoration: none; color: #252A34">슈퍼 해빗</a>
		    	<p class="con con-2">케이티 밀크먼<p>
		    	<p><span class="material-symbols-outlined star">grade</span><span class="score"> 4.3</span></p>
		    </div>
	  	</div>
	  	
    </div>
	  	<div class="banner-zone">
	  		<img alt="" src="/mainImage/banner1.png">
	  	</div>
  
  
    <div class="content-1">
  	<div class="content-title">
  		<h3>새로나온 e-book</h3>
  	</div>
  	<div class="content-list">
  		 <div class="product">
          <a href="#" style="text-decoration: none; color: #252A34">
            <div class="book-img">
             	<img src="/mainImage/트러스트.png" width="180px" height="280px;" style="border-radius: 10px;">
            </div>
            <div class="book-info">
                 <div class="info">트러스트</div>
                 <div class="info info2">에르난 디아스 ㅣ 문학동네</div>
             </div>
           </a>
          </div>
          <div class="product">
          <a href="#" style="text-decoration: none; color: #252A34">
            <div class="book-img">
             	<img src="/mainImage/러브몬스터.png" width="180px"  height="280px;" style="border-radius: 10px;">
            </div>
            <div class="book-info">
                 <div class="info">러브몬스터</div>
                 <div class="info info2">이두온 ㅣ 창비</div>
             </div>
           </a>
          </div>
          <div class="product">
          <a href="#" style="text-decoration: none; color: #252A34">
            <div class="book-img">
             	<img src="/mainImage/심연.png" width="180px"  height="280px;" style="border-radius: 10px;">
            </div>
            <div class="book-info">
                 <div class="info">심연속으로</div>
                 <div class="info info2">앤서니 데이비드 ㅣ 타인의사유</div>
             </div>
           </a>
          </div>
          <div class="product">
          <a href="#" style="text-decoration: none; color: #252A34">
            <div class="book-img">
             	<img src="/mainImage/월.png" width="180px"  height="280px;" style="border-radius: 10px;">
            </div>
            <div class="book-info">
                 <div class="info">월</div>
                 <div class="info info2">최정나 ㅣ 문학동네</div>
             </div>
           </a>
          </div>
          <div class="product">
          <a href="#" style="text-decoration: none; color: #252A34">
            <div class="book-img">
             	<img src="/mainImage/몸.png" width="180px"  height="280px;" style="border-radius: 10px;">
            </div>
            <div class="book-info">
                 <div class="info">내몸은 거꾸로 간다</div>
                 <div class="info info2">이지 ㅣ 프롬북스</div>
             </div>
           </a>
          </div>
          <div class="product">
          <a href="#" style="text-decoration: none; color: #252A34">
            <div class="book-img">
             	<img src="/mainImage/연결된고통.png" width="180px" height="280px;" style="border-radius: 10px;">
            </div>
            <div class="book-info">
                 <div class="info">연결된 고통</div>
                 <div class="info info2">이기병 ㅣ 아몬드</div>
             </div>
           </a>
	      </div>  
	  	</div>
	  </div>
  </div><%--content-2 끝 --%>
  
  <div class="content-2 bottom">
  	<div class="content-title">
  		<h3>주간 베스트</h3>
  	</div>
  	<div class="list-wrap " >
	  	<div class="book-list" style="width: 280px;" >
	  		<div class="img" >
	  			<img alt="" src="/mainImage/세이노.PNG" width="120px;" height="188px;"  style="float : left; ">
	  		</div>
	  		<div class="count">
		  		<h3>1</h3>
		  	</div>
		  	<div class="list-con" style="float : left; ">
		    	<a href="/bookDetail.do?bookNo=142" class="con con-1" style="text-decoration: none; color: #252A34">세이노의<br>가르침</a>
		    	<p class="con con-2">세이노<p>
		    	<p><span class="material-symbols-outlined star">grade</span><span class="score"> 4.3</span></p>
		    </div>
	  	</div>
	  	<div class="book-list" style="width: 280px;" >
	  		<div class="img" >
  			<img alt="" src="/mainImage/불편한.png" width="120px;"  height="188px;" style="float : left; ">
	  		</div>
	  		<div class="count">
		  		<h3>2</h3>
		  	</div>
		  	<div class="list-con" style="float : left; ">
		    	<a href="/bookDetail.do?bookNo=151" class="con con-1" style="text-decoration: none; color: #252A34">불편한 편의점</a>
		    	<p class="con con-2">김호연<p>
		    	<p><span class="material-symbols-outlined star">grade</span><span class="score"> 4.5</span></p>
		    </div>
	  	</div>
	  	<div class="book-list" style="width: 280px;" >
	  		<div class="img" >
	  			<img alt="" src="/mainImage/헤일메리.png" width="120px;" height="188px;" style="float : left; ">
	  		</div>
	  		<div class="count">
		  		<h3>3</h3>
		  	</div>
		  	<div class="list-con" style="float : left; ">
		    	<a href="/bookDetail.do?bookNo=153" class="con con-1" style="text-decoration: none; color: #252A34">프로젝트<br>헤일메리</a>
		    	<p class="con con-2">앤디 위어<p>
		    	<p><span class="material-symbols-outlined star">grade</span><span class="score"> 4.8</span></p>
		    </div>
	  	</div>
	  	<div class="book-list" style="width: 280px;" >
	  		<div class="img" >
	  			<img alt="" src="/mainImage/원씽.png" width="120px;" height="188px;"  style="float : left; ">
	  		</div>
	  		<div class="count">
		  		<h3>4</h3>
		  	</div>
		  	<div class="list-con" style="float : left; ">
		    	<a href="/bookDetail.do?bookNo=154" class="con con-1" style="text-decoration: none; color: #252A34">원씽</a>
		    	<p class="con con-2">게리 켈러,<br>제이 파파산<p>
		    	<p><span class="material-symbols-outlined star">grade</span><span class="score"> 4.3</span></p>
		    </div>
	  	</div>
    </div>
</div>


	<div class="top-btn">
		<a class="top"ref="#"><img alt="top" src="/mainImage/top.png"></a>
	</div>
    
    
    
    
    <script>
	    $(".top-btn").on("click", function() {
	    	window.scrollTo({ top: 0, behavior: "smooth" });  
	    });
    </script>
    
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
	
</body>

</html>