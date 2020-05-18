<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	table, th, td {
		text-align: center;
	}
</style>
<div class="container">
	<div class="row">
		<h3>QnA</h3>
	</div>
	<div class="row">
		<div class="col-md-7 form-inline">
			<span class="label label-success">검색 종류</span>
			<select name="cat" class="form-control">
				<option value="dd">상품명</option>
			</select>
			<input type="text" class="form-control" placeholder="검색어를 입력하세요" maxlength="30">
			<input type="button" class="btn btn-lg btn-primary" value="검색">
			<input type="button" class="btn btn-lg btn-info" value="미답변 목록">
		</div>
		<div class="col-md-5 form-inline">
			<input type="button" class="btn btn-lg btn-warning pull-right" value="답변 완료 목록">
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<table class="table table-bordered" id="qna-table">
				<thead>
					<tr>
						<th>번호</th>
						<th>카테고리</th>
						<th>상품명</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody>
				
				</tbody>
			</table>
		</div>
	</div>
</div>
<script>
	$(document).ready(function() {
		qnaListLoad();
	});
	
	//qna 목록 로드//
	function qnaListLoad(){
		jQuery.ajax({
			dataType:"JSON",
			url:`${cp}/admin/qnaList.do`,
			method:"get",
			success:function(data){
				for(let dto of data){
					tbody = $("#qna-table>tbody");
					let row = "<tr>";
					row += "<td>"+dto.qnum+"</td>";
					row += "<td>"+dto.cname+"</td>";
					row += "<td>"+dto.pname+"</td>";
					row += "<td>"+dto.title+"</td>";
					row += "<td>"+dto.writer+"</td>";
					row += "<td>"+dto.reg_date+"</td>";
					row += "</tr>";
					tbody.append(row);
				}
			}
		});
	}
	//qna 목록 로드//
</script>