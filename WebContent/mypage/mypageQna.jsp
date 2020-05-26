<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="container">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>qna번호</th>
					<th>제목</th>
					<th>아이디</th>
					<th>등록일</th>
				</tr>
				<tr style='display: none'>
					<th></th>
					<th></th>
					<th>내용</th>
					<th></th>
				</tr>


			</thead>


			<c:forEach var='vo' items='${list }'> 
				<tbody>
					<tr onclick="showHidden(${vo.rnum2},'${vo.id }','${vo.locker }')">
						<c:set var="cNum" value="${vo.rnum2-1}"/>
						<td>${count-cNum}</td>
						<td>
							<c:if test="${vo.level > 1 }">
								<c:forEach var="i" begin="1" end="${vo.level - 1 }">
									[re]&nbsp;
								</c:forEach> 
							</c:if>
							${vo.title } 
							<c:if test="${vo.locker == 'Y' }">
								<img src="${pageContext.request.contextPath }/img/locker.jpg" width="30px" height="30px">
							</c:if>
						</td>
						<td>${vo.id }</td>
						<td>${vo.reg_date }</td>
					</tr>
					<tr id='${vo.rnum2 }' style='display: none;'>
						<td>${vo.content }</td>
					</tr>
				</tbody>
				
			</c:forEach>
		</table>
	</div>
		
	
	
	<br>
	<br>
	<!-- 페이징처리 -->
<div>
<c:choose>
	<c:when test ="${startPage>4 }">
		<a href="${pageContext.request.contextPath }/mypage/mypageQna.do?pageNum=${startPage-1}">[이전]</a>
	</c:when>
	<c:otherwise>
		이전
	</c:otherwise>
</c:choose>
<c:forEach var="i" begin="${startPage }" end="${endPage }">
	<c:choose>
		<c:when test="${i==pageNum }">
			<a href="${pageContext.request.contextPath }/mypage/mypageQna.do?pageNum=${i}">
			<span style='color:blue'>[${i }]</span></a>
		</c:when>		
		<c:otherwise>
			<a href = "${pageContext.request.contextPath }/mypage/mypageQna.do?pageNum=${i}">
			<span style='color:#999'>[${i }]</span></a>
		</c:otherwise>
	</c:choose>
</c:forEach>
<c:choose>
	<c:when test ="${endPage<pageCount }">
		<a href="${pageContext.request.contextPath }/mypage/mypageQna.do?pageNum=${endPage+1}">[다음]</a>
	</c:when>
	<c:otherwise>
		다음
	</c:otherwise>
</c:choose>
</div>


<script>
	function showHidden(dtoQnum,dtoId,dtoLocker) {
		var id =document.getElementById(dtoQnum);
					
		if(dtoId == '${sessionScope.memberDto.id}' || dtoLocker=='N' ){
			if(id.style.display == 'none'){ 
				id.style.display = 'block' ;
			}else{ 
				id.style.display = 'none' ;
			}
		}else{
			alert("비밀글입니다.");
		}
	}
	
</script>