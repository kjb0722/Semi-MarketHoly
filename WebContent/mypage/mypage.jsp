<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 
<style>
	#header{width:20%;display:inline-block; }
	#footer{width:50%;display:inline-block;}
</style>
 -->
	<div id="header">
		<jsp:include page="/mypage/header.jsp"/>
	</div>
	<br>
	<br>
	
	
	<c:choose>
		<c:when test="${param.mypage != null }">
			<div id ="footer">
				<jsp:include page="/${param.mypage }"></jsp:include>
			</div>
		</c:when>
		
	</c:choose>

	
