<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
					<tr onclick="showHidden(${vo.qnum},'${vo.id }','${vo.locker }')">
						<td>${dto.qnum }</td>
						<%-- <c:choose>
							<c:when test="${dto.locker == 'Y'}">
								<td><c:if test="${dto.ref>0 }">
										<c:forEach var="i" begin="1" end="${dto.ref }">
									&nbsp;&nbsp;
									</c:forEach>
									[re]
								</c:if> ${dto.title } <img src="${pageContext.request.contextPath }/img/locker.jpg" width="30px" height="30px"></td>
							</c:when>
							<c:otherwise>
								<td><c:if test="${dto.ref>0 }">
										<c:forEach var="i" begin="1" end="${dto.ref }">
								&nbsp;&nbsp;
								</c:forEach>
								[re]
							</c:if> ${dto.title }</td>
							</c:otherwise>
						</c:choose> --%>

						<td>
							<c:if test="${dto.level > 1 }">
								<c:forEach var="i" begin="1" end="${dto.level - 1 }">
									[re]&nbsp;
								</c:forEach> 
							</c:if>
							${dto.title } 
							<c:if test="${dto.locker == 'Y' }">
								<img src="${pageContext.request.contextPath }/img/locker.jpg" width="30px" height="30px">
							</c:if>
						</td>
						<td>${id }</td>
						<td>${dto.reg_date }</td>
					</tr>
					<tr id='${dto.qnum }' style='display: none;'>
						<td>${dto.content }</td>
					</tr>

				</tbody>
			</c:forEach>
		</table>
	</div>
	<input type="button" value="QnA작성하기" style="float: right" onclick="location.href='${cp }/qna/writeQna.jsp?pnum=${vo.pnum }&id=${vo.id }&num=${vo.num }'">
	<br>
	<br>
	<!-- 페이징처리 -->
<div>
<c:choose>
	<c:when test ="${startPage>4 }">
		<a href="${pageContext.request.contextPath }/qna/qnaList.do?pageNum=${startPage-1}&pnum=${pnum}">[이전]</a>
	</c:when>
	<c:otherwise>
		이전
	</c:otherwise>
</c:choose>
<c:forEach var="i" begin="${startPage }" end="${endPage }">
	<c:choose>
		<c:when test="${i==pageNum }">
			<a href="${pageContext.request.contextPath }/qna/qnaList.do?pageNum=${i}">
			<span style='color:blue'>[${i }]</span></a>
		</c:when>		
		<c:otherwise>
			<a href = "${pageContext.request.contextPath }/qna/qnaList.do?pageNum=${i}">
			<span style='color:#999'>[${i }]</span></a>
		</c:otherwise>
	</c:choose>
</c:forEach>
<c:choose>
	<c:when test ="${endPage<pageCount }">
		<a href="${pageContext.request.contextPath }/qna/qnaList.do?pageNum=${endPage+1}">[다음]</a>
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
