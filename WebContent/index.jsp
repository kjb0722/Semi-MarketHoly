<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${cp }/bootstrap/css/bootstrap.min.css">
<!-- <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script> -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>
<script src="${cp }/bootstrap/js/bootstrap.min.js"></script>
<title>마켓 홀리쉣</title>
<style>
.carousel-inner>.item>img {
	margin: 0 auto;
	height: 300px;
}

.navbar>a>img {
	width: 206px;
	height: 208px;
}

footer {
	margin-top: 100px;
}
</style>
</head>
<body>
	<header class="container">
		<div class="navbar">
			<ul class="nav navbar-nav pull-right">
				<li><a href="${cp }/member/start.do">회원가입</a></li>
				<c:choose>
					<c:when test="${sessionScope.id == null }">
						<li><a href="${cp }/member/loginstart.do">로그인</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${cp }/member/logout.do">로그아웃</a></li>
					</c:otherwise>
				</c:choose>
				<%-- <c:if test="${sessionScope.rating == 99 }">
					<li><a href="/admin/admin.do">관리자</a></li>				
				</c:if> --%>
				<li><a href="${cp }/admin/admin.do">관리자</a></li>
			</ul>
			<a href="${cp }/main.do"><img src="${cp }/img/logo1.png"
				alt="Logo"></a>
		</div>
		<nav class="navbar navbar-default">
			<div class="container">
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">전체 카테고리 <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">Action</a></li>
								<li><a href="#">Another action</a></li>
								<li><a href="#">Something else here</a></li>
								<li role="separator" class="divider"></li>
								<li class="dropdown-header">Nav header</li>
								<li><a href="#">Separated link</a></li>
								<li><a href="#">One more separated link</a></li>
							</ul></li>
						<li class="active"><a href="#">신상품</a></li>
						<li><a href="#">베스트</a></li>
						<li><a href="#">알뜰쇼핑</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</nav>
	</header>

	<section class="container">
		<c:choose>
			<c:when test="${param.page == null }">
				<div id="carousel-example-generic" class="carousel slide"
					data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0"
							class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1"
							class=""></li>
						<li data-target="#carousel-example-generic" data-slide-to="2"
							class=""></li>
					</ol>
					<div class="carousel-inner" role="listbox">
						<div class="item active">
							<img
								data-src="holder.js/1140x500/auto/#777:#555/text:First slide"
								alt="First slide [1140x500]" src="img/img1.jpg"
								data-holder-rendered="true">
						</div>
						<div class="item">
							<img
								data-src="holder.js/1140x500/auto/#666:#444/text:Second slide"
								alt="Second slide [1140x500]" src="img/img2.png"
								data-holder-rendered="true">
						</div>
						<div class="item">
							<img
								data-src="holder.js/1140x500/auto/#555:#333/text:Third slide"
								alt="Third slide [1140x500]" src="img/img3.jpg"
								data-holder-rendered="true">
						</div>
					</div>
					<a class="left carousel-control" href="#carousel-example-generic"
						role="button" data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#carousel-example-generic"
						role="button" data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
			</c:when>
			<c:otherwise>
				<jsp:include page="/${param.page }"></jsp:include>
			</c:otherwise>
		</c:choose>
	</section>
	<footer class="container">
		<div class="panel panel-success">
			<div class="panel-heading">
				<h3 class="panel-title">마켓 홀리쒯</h3>
			</div>
			<div class="panel-body">
				<h3>제작자</h3>
				<ul>
					<li>권준범</li>
					<li>임다은</li>
					<li>전인표</li>
					<li>전효진</li>
				</ul>
			</div>
		</div>
	</footer>
</body>
</html>