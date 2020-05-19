<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
table, th, td {
	text-align: center;
}
.hidden{
	display: none;
}
</style>
<div class="container">
	<div class="row">
		<h3>QnA</h3>
	</div>
	<div class="row">
		<div class="col-md-7 form-inline">
			<span class="label label-success">검색 종류</span>
			<select class="form-control" id="kind">
				<c:forEach var="dto" items="${comList }">
					<option value="${dto.val }">${dto.name }</option>
				</c:forEach>
			</select>
			<input type="text" class="form-control" placeholder="검색어를 입력하세요" maxlength="30" id="txtWord">
			<input type="button" class="btn btn-lg btn-primary" value="검색" id="btnSearch">
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
						<th class="hidden">내용</th>
						<th class="hidden">상품 번호</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>
		</div>
	</div>

	<div class="modal fade" id="qnaAnswer" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title">질문글 답변</h2>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12 form-group">
							<input type="hidden" id="quePnum">
							<input type="hidden" id="queQnum">
							<label class="label label-success">질문 내용</label>
							<textarea id="queContent" class="form-control" rows="10" cols="100" style="resize: none;" disabled="disabled"></textarea>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 form-group">
							<label class="label label-success">제목</label>
							<input id="ansTitle" type="text" class="form-control" placeholder="제목을 입력하세요" maxlength="30">
							<label class="label label-success">답변</label>
							<textarea id="ansContent" class="form-control" rows="10" cols="100" style="resize: none;"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="btnWrite">답변 등록</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	$(document).ready(function() {
		qnaListLoad("","");
	});
	
	//답변 등록//
	$("#btnWrite").click(function() {
		ansWrite();
	});
	function ansWrite(){
		let qnum = $("#queQnum").val();
		let title = $("#ansTitle").val();
		let content = $("#ansContent").val();
		let pnum = $("#quePnum").val();
		jQuery.ajax({
			dataType:"JSON",
			url:`${cp}/admin/qnaAnsWrite.do`,
			method:"get",
			data:{qnum:qnum,
				title:title,
				content:content,
				pnum:pnum},
			success:function(data){
				if(data.n > 0){
					alert("답변 등록 완료");	
				}else{
					location = `${cp}/error.do`;
				}
			}
		});
	}
	//답변 등록//
	
	//검색 input text 엔터키 이벤트//
	$("#txtWord").keydown(function(e) {
		if(e.keyCode == 13){
			$("#btnSearch").click();	
		}
	});
	//검색 input text 엔터키 이벤트//
	
	//검색 버튼 이벤트//
	$("#btnSearch").click(function(){
		let kind = $("#kind").val();
		let word = $("#txtWord").val();
		qnaListLoad(kind,word);
	});
	//검색 버튼 이벤트//
	
	//qna 목록 로드//
	function qnaListLoad(kind,word){
		jQuery.ajax({
			dataType:"JSON",
			url:`${cp}/admin/qnaList.do`,
			method:"get",
			data:{word:word,
				kind:kind},
			success:function(data){
				tbody = $("#qna-table>tbody");
				tbody.empty();
				for(let dto of data){
					let row = "<tr data-toggle='modal' data-target='#qnaAnswer'>";
					row += "<td>"+dto.qnum+"</td>";
					row += "<td>"+dto.cname+"</td>";
					row += "<td>"+dto.pname+"</td>";
					row += "<td>"+dto.title+"</td>";
					row += "<td>"+dto.writer+"</td>";
					row += "<td>"+dto.reg_date+"</td>";
					row += "<td class='hidden'>"+dto.content+"</td>";
					row += "<td class='hidden'>"+dto.pnum+"</td>";
					row += "</tr>";
					tbody.append(row);
				}
				
				//글 선택 이벤트//
				$("#qna-table>tbody>tr").click(function() {
					$("#queQnum").val($(this).children().eq(0).text());
					$("#queContent").val($(this).children().eq(6).text());
					$("#quePnum").val($(this).children().eq(7).text());
					$("#ansTitle").val("");
					$("#ansContent").val("");
				});
			}
		});
	}
	//qna 목록 로드//
</script>