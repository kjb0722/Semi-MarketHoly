<%@page import="com.market.review.dto.ReviewDto"%>
<%@page import="com.market.review.dao.ReviewDao"%>
<%@page import="com.market.product.dao.ProductDao"%>
<%@page import="com.market.product.dto.ProductDto"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<img src="${pageContext.request.contextPath }/img/${dto.thumb_save}"
	width="300px" height="400px">
<h1>${dto.name}</h1>
<h4>${dto.description}</h4>
<h2>${dto.price}</h2>
구매수량
<br>

<button type="button" class="btn btn-default">
	<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
</button>
<input type="text" id="EA">
<button type="button" class="btn btn-default">
	<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
</button>

총 상품금액 : ${dto.price } 원
<br>

<input type="button" value="장바구니 담기" onclick="incart()">
<br>
<script>

function incart() {		
	var id='${sessionScope.memberDto.id}';
	var pnum=${param.pnum};
	var EA=document.getElementById("EA").value;
	location = "${cp }/member/cartAdd.do?pnum="+pnum+"&id="+id+"&EA="+EA;
	
}
	

	
</script>
------------------하단 탭 (상품설명/상품이미지/고객후기/상품문의 )

<div class="container">

  <h2>탭</h2>

  <ul id="myTab" class="nav nav-tabs" role="tablist">
    <li role="presentation" class=""><a href="#home" id="home-tab" role="tab" data-toggle="tab" aria-controls="home" aria-expanded="false">Home</a></li>
    <li role="presentation" class="active"><a href="#profile" role="tab" id="profile-tab" data-toggle="tab" aria-controls="profile" aria-expanded="true">Profile</a></li>
    <li role="presentation" class="dropdown">
      <a href="#" id="myTabDrop1" class="dropdown-toggle" data-toggle="dropdown" aria-controls="myTabDrop1-contents">Dropdown <span class="caret"></span></a>
      <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1" id="myTabDrop1-contents">
        <li><a href="#dropdown1" tabindex="-1" role="tab" id="dropdown1-tab" data-toggle="tab" aria-controls="dropdown1">@fat</a></li>
        <li><a href="#dropdown2" tabindex="-1" role="tab" id="dropdown2-tab" data-toggle="tab" aria-controls="dropdown2">@mdo</a></li>
      </ul>
    </li>
  </ul>
  <div id="myTabContent" class="tab-content">
    <div role="tabpanel" class="tab-pane fade" id="home" aria-labelledby="home-tab">
      <p>Home content</p>
    </div>
    <div role="tabpanel" class="tab-pane fade active in" id="profile" aria-labelledby="profile-tab">
      <p>Profile content</p>
    </div>
    <div role="tabpanel" class="tab-pane fade" id="dropdown1" aria-labelledby="dropdown1-tab">
      <p>@fat content</p>
    </div>
    <div role="tabpanel" class="tab-pane fade" id="dropdown2" aria-labelledby="dropdown2-tab">
      <p>@mdo content</p>
    </div>
  </div>

</div>
