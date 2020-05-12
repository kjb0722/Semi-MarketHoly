<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form class="form-inline" role="form" action="${cp }/admin/catAdd.do" method="post">
	<span class="label label-success">카테고리</span><br>
	<input type="text" class="form-control" placeholder="카테고리를 입력하세요." name="catName" maxlength="10">
	<button type="submit" class="btn btn-lg btn-primary">추가</button>
</form>
<br>
<form class="form-inline" role="form" action="${cp }/admin/catTypeAdd.do" method="post">
	<span class="label label-success">종류</span><br>
	<select name="cat" class="form-control">
		<c:forEach var="dto" items="${catList }">
			<option value="${dto.cnum }">${dto.name }</option>
		</c:forEach>
	</select>
	<input type="text" class="form-control" placeholder="종류를 입력하세요.">
	<button type="button" class="btn btn-lg btn-primary">추가</button>
</form>

<table class="table table-striped">
	<thead>
		<tr>
			<th>카테고리 번호</th>
			<th>카테고리 이름</th>
			<th>종류 번호</th>
			<th>종류 이름</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="dto" items="${catListAll }">
			<tr>
				<td>${dto.cnum }</td>
				<td>${dto.name }</td>
				<td>${dto.tnum }</td>
				<td>${dto.tname }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>