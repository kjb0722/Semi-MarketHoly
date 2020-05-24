<%@page import="com.market.review.dto.ReviewDto"%>
<%@page import="com.market.review.dao.ReviewDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>리뷰번호</th>
				<th>제목</th>
				<th>이름</th>
				<th>등록일</th>
			</tr>
			<tr style='display:none'>
				<th>내용</th>
				<th></th>
				<th></th>
				<th>이미지</th>
			</tr>
		</thead>
		<c:forEach var='vo' items='${requestScope.list }'>
			<tbody>
				<tr onclick="showHidden(${vo.rnum})">	
					<td>${vo.rnum}</td>
					<c:choose>
						<c:when test="${vo.savefilename  == null }">
							<td>${vo.title }</td>
						</c:when>
						<c:otherwise>
							<td>${vo.title }
								<img src="${pageContext.request.contextPath }/img/clip.jpg" width="30px" height="30px">
							</td>
						</c:otherwise>
					</c:choose>
					<td>${vo.name }</td>
					<td>${vo.regdate }</td>
				</tr>
				<tr id='${vo.rnum }' style='display:none;'>
					<c:choose>
						<c:when test="${vo.savefilename  == null }">
							<td>${vo.content }</td>							
						</c:when>
						<c:otherwise>
							<td><p><img src="${pageContext.request.contextPath }/img/${vo.savefilename }" width="400px" height="500px"></p>${vo.content }</td>
						</c:otherwise>
					</c:choose>
				
				</tr>	
			</tbody>	
		</c:forEach>	
	</table>
</div>
		<input type="button" value = "후기쓰기" style="float:right" onclick="location.href='${cp }/review/startWriteReview.do?pnum=${pnum }'">
		<br><br>


<!-- 페이징처리 -->
<div>
<c:choose>
	<c:when test ="${startPage>4 }">
		<a href="${pageContext.request.contextPath }/review/listReview.do?pageNum=${startPage-1}&pnum=${pnum}">[이전]</a>
	</c:when>
	<c:otherwise>
		이전
	</c:otherwise>
</c:choose>
<c:forEach var="i" begin="${startPage }" end="${endPage }">
	<c:choose>
		<c:when test="${i==pageNum }">
			<a href="${pageContext.request.contextPath }/review/listReview.do?pageNum=${i}&pnum=${pnum}">
			<span style='color:blue'>[${i }]</span></a>
		</c:when>		
		<c:otherwise>
			<a href = "${pageContext.request.contextPath }/review/listReview.do?pageNum=${i}&pnum=${pnum}">
			<span style='color:#999'>[${i }]</span></a>
		</c:otherwise>
	</c:choose>
</c:forEach>
<c:choose>
	<c:when test ="${endPage<pageCount }">
		<a href="${pageContext.request.contextPath }/review/listReview.do?pageNum=${endPage+1}&pnum=${pnum}">[다음]</a>
	</c:when>
	<c:otherwise>
		다음
	</c:otherwise>
</c:choose>
</div>

<script>
	function showHidden(rNum) {
		var id =document.getElementById(rNum);
		
		if(id.style.display == 'none'){	
			id.style.display = 'block' ;
		}else{ 
			id.style.display = 'none' ;
		}
	}
</script>


