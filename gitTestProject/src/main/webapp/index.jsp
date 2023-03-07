<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	<a href="/qnaList.do?reqPage=1">장형천 p태그 삽입</a>
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
      <div class="item active">
        <img src="/mainImage/4.PNG" alt="Los Angeles" style="width:100%;">
      </div>

      <div class="item">
        <img src="/mainImage/2.PNG" alt="Chicago" style="width:100%;">
      </div>
    
      <div class="item">
        <img src="/mainImage/3.PNG" alt="New york" style="width:100%;">
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
  	<h2>여기는 신간, 베스트, 이벤트, 신간캘린더 넣을 자리?</h2>
  </div>
  <div class="content-1">
  	<div class="content-title">
  		<h3>숨겨진 lit의 발견</h3>
  	</div>
  	<div class="content-list">
  		 <div class="product">
          <a href="#">
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
          <a href="#">
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
          <a href="#">
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
          <a href="#">
            <div class="book-img">
             	<img src="/mainImage/세이노.PNG" width="180px"  height="280px;" style="border-radius: 10px;">
            </div>
            <div class="book-info">
                 <div class="info">가장 충격적인</div>
                 <div class="info">마지막문장</div>
             </div>
           </a>
          </div>
          <div class="product">
          <a href="#">
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
          <a href="#">
            <div class="book-img">
             	<img src="/mainImage/하얼빈.PNG" width="180px" height="280px;" style="border-radius: 10px;">
            </div>
            <div class="book-info">
                 <div class="info">독립을 향한</div>
                 <div class="info">방아쇠를 당기다</div>
             </div>
           </a>
          </div>  
  	</div>
  </div>
  
  <div class="content-2">
  	<div class="content-title">
  		<h3>화제의 책소식</h3>
  	</div>
  
  
  
  
  </div><%--content-2 끝 --%>
  
  
  
  
</div>
	
    
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>