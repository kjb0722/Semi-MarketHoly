<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
	<div id="header">
		<jsp:include page="/mypage/header.jsp"/>
	</div>
	
	
	<c:choose>
		<c:when test="${param.mypage != null }">
			<div id ="footer">
				<jsp:include page="/${param.mypage }"></jsp:include>
				
			</div>
		</c:when>
	</c:choose>
	
	
