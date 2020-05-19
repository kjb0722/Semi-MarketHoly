<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 부트스트랩 cdn -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css" href="${cp }/css/index.css" />
<title>마켓홀리</title>
<style type="text/css">

</style>
</head>
<body>
	<header class="container">
		<div class="navbar">
			<ul class="nav navbar-nav pull-right">
				<c:choose>
					<c:when test="${sessionScope.memberDto == null }">
						<li>
							<a href="${cp }/member/startJoin.do">회원가입</a>
						</li>
						<li>
							<a href="${cp }/member/loginstart.do">로그인</a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<li class="dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" href="${cp }/member/startChangeInfo.do">${sessionScope.memberDto.id}님</a>
							<ul class="dropdown-menu">							
								<li class="dropdown-item"><a href="${cp }/mypage/mypage.jsp">마이페이지</a></li>
								<li class="dropdown-item"><a href=#>로그아웃</a></li>
							</ul>
						</li>
					</c:otherwise>
				</c:choose>
				<%-- <c:if test="${sessionScope.rating == 99 }">
					<li><a href="/admin/admin.do">관리자</a></li>				
				</c:if> --%>
				<li>
					<a href="${cp }/admin/admin.do">관리자</a>
				</li>
			</ul>
			<div id="wrap-logo">
				<a href="${cp }/main.do"><img src="${cp }/img/logo_0518.jpg" alt="Logo"></a>
			</div>
		</div>

		<nav class="navbar navbar-default">
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><b class="glyphicon glyphicon-list">&nbsp;</b>전체 카테고리<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<c:forEach var="cat" items="${applicationScope.catList }">
								<li class="dropdown-submenu">
									<a href="${cp }/product/list.do?cnum=${cat.cnum }">${cat.name }</a>
									<ul class="dropdown-menu">
										<c:forEach var="type" items="${applicationScope.catTypeList }">
											<c:if test="${cat.cnum == type.type }">
												<li>
													<a class="cat-submenu" href="${cp }/product/list.do?cnum=${type.cnum}&type=${type.type}">${type.name }</a>
												</li>
											</c:if>
										</c:forEach>
									</ul>
								</li>
							</c:forEach>
						</ul>
					<li>
						<a href="${cp }/product/new.do">신상품</a>
					</li>
					<li>
						<a href="#">베스트</a>
					</li>
					<li>
						<a href="#">알뜰쇼핑</a>
					</li>
					<li>
						<a href="${cp }/qna/qnaList.do">인표연습용</a>
					</li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li>
						<a href="${cp }/member/listReview.do"><b class="glyphicon glyphicon-log-out"></b>&nbsp;로그아웃</a>
					</li>
				</ul>
				<form class="navbar-form pull-right">
					<input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
					<button type="submit" class="btn">
						<span class="glyphicon glyphicon-search"></span>
					</button>
				</form>
			</div>
		</nav>
		<c:if test="${sessionScope.memberDto.rating == 99 }">
			<div class="container">
				<jsp:include page="/admin/admin.jsp"></jsp:include>
			</div>
		</c:if>
	</header>

	<section class="container">
		<c:choose>
			<c:when test="${param.page == null }">
				<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1" class=""></li>
						<li data-target="#carousel-example-generic" data-slide-to="2" class=""></li>
					</ol>
					<div class="carousel-inner" role="listbox">
						<div class="item active">
							<img data-src="holder.js/1140x500/auto/#777:#555/text:First slide" alt="First slide [1140x500]" src="${cp }/img/honey.jpg" data-holder-rendered="true">
						</div>
						<div class="item">
							<img data-src="holder.js/1140x500/auto/#666:#444/text:Second slide" alt="Second slide [1140x500]" src="${cp }/img/love.jpg" data-holder-rendered="true">
						</div>
						<div class="item">
							<img data-src="holder.js/1140x500/auto/#555:#333/text:Third slide" alt="Third slide [1140x500]" src="${cp }/img/vitamin.jpg" data-holder-rendered="true">
						</div>
					</div>
					<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev"> <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next"> <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> <span class="sr-only">Next</span>
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
				<h3 class="panel-title">마켓 홀리</h3>
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