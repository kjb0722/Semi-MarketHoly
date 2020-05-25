<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<table class="table table-bordered">
		<tr>
			<th>주문번호</th>
			<th>주문일자</th>
			<th>opnum</th>
			<th>pname</th>
		</tr>
		<c:forEach var='vo' items='${list }'>
			<tr>
				<td>${vo.onum }</td>
				<td>${vo.reg_date }	</td>
				<td>${vo.opnum }</td>
				<td>${vo.pname }</td>
			</tr>
		</c:forEach>
	</table>
