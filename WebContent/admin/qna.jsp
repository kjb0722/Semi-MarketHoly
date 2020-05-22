<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
table, th, td {
	text-align: center;
}

.hidden {
	display: none;
}

.align-left {
	text-align: left;
}

nav {
	list-style: none;
	margin: 0;
	padding: 0;
	text-align: center;
}

.cursor-pointer {
	cursor: pointer;
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
			<input type="button" class="btn btn-lg btn-info" value="미답변 목록" id="btnUnanswer">
		</div>
		<div class="col-md-5 form-inline">
			<input type="button" class="btn btn-lg btn-warning pull-right" value="답변 완료 목록" id="btnAnsComplete">
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<table class="table table-bordered" id="qna-table">
				<thead>
					<tr>
						<th style="width: 5%">번호</th>
						<th style="width: 13%">카테고리</th>
						<th style="width: 27%">상품명</th>
						<th style="width: 34%">제목</th>
						<th style="width: 8%">작성자</th>
						<th style="width: 13%">작성일</th>
						<th class="hidden">내용</th>
						<th class="hidden">상품 번호</th>
						<th class="hidden">level</th>
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
					<button type="button" class="btn btn-primary" id="btnModify" disabled="disabled">답변 수정</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
	<div id="page-div" class="row"></div>
</div>
<script>
	$(document).ready(function() {
		qnaListLoad("","",1);
	});
	
	//답변 수정//
	$("#btnModify").click(function(){
		let qnum = $("#queQnum").val();
		let title = $("#ansTitle").val();
		let content = $("#ansContent").val();
		jQuery.ajax({
			dataType:"JSON",
			url:`${cp}/admin/qnaModify.do`,
			method:"post",
			data:{qnum:qnum,
				title:title,
				content:content},
			success:function(data){
				if(data.n>0){
					alert("답변 수정 완료");
					$("#qnaAnswer").modal("hide");
					qnaListLoad("","",1);
				}else{
					location = `${cp}/error.do`;
				}
			}
		});
	});
	//답변 수정//
	
	//답변 완료 목록 로드//
	$("#btnAnsComplete").click(function(){
		ansComplete();
	});
	function ansComplete(pageNum){
		jQuery.ajax({
			dataType:"JSON",
			url:`${cp}/admin/ansComplete.do`,
			method:"get",
			data:{pageNum:pageNum},
			success:function(data){
				tbodyRowAdd(data[0]);
				
				//페이징//
				pageDiv = $("#page-div");
				pageDiv.empty();
				
				let startPageNum = data[1];
				let endPageNum = data[2];
				let pageNum = data[3];
				let pageCount = data[4];
				
				let row = "<nav>";
				row += "<ul class='pagination'>";
				
				if(startPageNum != 1){
					row += "<li>";
					row += "<a onclick='ansComPageMove("+(pageNum-1)+")' class='cursor-pointer' aria-label='Previous'>";
					row += "<span aria-hidden='true'>&laquo</span>";
					row += "</a>";
					row += "</li>";
				}
				
				for(let i=startPageNum;i<=endPageNum;i++){
					row += "<li><a onclick='ansComPageMove("+(i)+")' class='cursor-pointer'>"+i+"</a></li>";
				}
				
				if(pageCount > endPageNum){
					row += "<li>";
					row += "<a onclick='ansComPageMove("+(pageNum+1)+")' class='cursor-pointer' aria-label='Next'>";
					row += "<span aria-hidden='true'>&raquo;</span>";
					row += "</a>";
					row += "</li>";
				}
				
				row += "</ul>";
				row += "</nav>";
				pageDiv.append(row);
				//페이징//
				
				//글 선택 모달 이벤트//
				setAnswerModalVal();
				//글 선택 모달 이벤트//
			}
		});
	}
	
	function ansComPageMove(page){
		ansComplete(page);
	}
	//답변 완료 목록 로드//
	
	//미답변 목록 로드//
	$("#btnUnanswer").click(function() {
		unanswerLoad();
	});
	function unanswerLoad(){
		jQuery.ajax({
			dataType:"JSON",
			url: `${cp}/admin/qnaUnanswerList.do`,
			method:"get",
			success:function(data){
				tbodyRowAdd(data[0]);
				
				//페이징//
				pageDiv = $("#page-div");
				pageDiv.empty();
				
				let startPageNum = data[1];
				let endPageNum = data[2];
				let pageNum = data[3];
				let pageCount = data[4];
				
				let row = "<nav>";
				row += "<ul class='pagination'>";
				
				if(startPageNum != 1){
					row += "<li>";
					row += "<a onclick='unansPageMove("+(pageNum-1)+")' class='cursor-pointer' aria-label='Previous'>";
					row += "<span aria-hidden='true'>&laquo</span>";
					row += "</a>";
					row += "</li>";
				}
				
				for(let i=startPageNum;i<=endPageNum;i++){
					row += "<li><a onclick='unansPageMove("+(i)+")' class='cursor-pointer'>"+i+"</a></li>";
				}
				
				if(pageCount > endPageNum){
					row += "<li>";
					row += "<a onclick='unansPageMove("+(pageNum+1)+")' class='cursor-pointer' aria-label='Next'>";
					row += "<span aria-hidden='true'>&raquo;</span>";
					row += "</a>";
					row += "</li>";
				}
				
				row += "</ul>";
				row += "</nav>";
				pageDiv.append(row);
				//페이징//
				
				//글 선택 모달 이벤트//
				setAnswerModalVal();
				//글 선택 모달 이벤트//
			}
		});
	}
	//미답변 목록 로드//
	
	function tbodyRowAdd(data){
		let tbody = $("#qna-table>tbody");
		tbody.empty();
		for(let dto of data){
			let replyIcon = "";
			let row = "";
			if(dto.level >= 2){
				replyIcon = "<span class='glyphicon glyphicon-check'></span>";
				//row += "<tr>";
				row += "<tr data-toggle='modal' data-target='#qnaAnswer' style='cursor:pointer;'>";
			}else{
				replyIcon = "<span class='glyphicon glyphicon-question-sign'></span>";
				row += "<tr data-toggle='modal' data-target='#qnaAnswer' style='cursor:pointer;'>";
			}
			row += "<td>"+dto.qnum+"</td>";
			row += "<td>"+dto.cname+"</td>";
			row += "<td>"+dto.pname+"</td>";
			row += "<td class='align-left'>"+replyIcon+dto.title+"</td>";
			row += "<td>"+dto.writer+"</td>";
			row += "<td>"+dto.reg_date+"</td>";
			row += "<td class='hidden'>"+dto.content+"</td>";
			row += "<td class='hidden'>"+dto.pnum+"</td>";
			row += "<td class='hidden'>"+dto.level+"</td>";
			row += "</tr>";
			tbody.append(row);
			
			if(dto.level>=2){
				tbody.find("tr").last().css("color","blue");					
			}
		}
	}
	
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
					$("#qnaAnswer").modal("hide");
					qnaListLoad("","");
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
	function qnaListLoad(kind,word,pageNum){
		jQuery.ajax({
			dataType:"JSON",
			url:`${cp}/admin/qnaList.do`,
			method:"get",
			data:{word:word,
				kind:kind,
				pageNum:pageNum},
			success:function(data){
				//테이블 row 추가
				tbodyRowAdd(data[0]);
				
				//페이징//
				pageDiv = $("#page-div");
				pageDiv.empty();
				
				let startPageNum = data[1];
				let endPageNum = data[2];
				let pageNum = data[3];
				let pageCount = data[4];
				
				let row = "<nav>";
				row += "<ul class='pagination'>";
				
				if(startPageNum != 1){
					row += "<li>";
					row += "<a onclick='qnaPageMove("+(pageNum-1)+")' class='cursor-pointer' aria-label='Previous'>";
					row += "<span aria-hidden='true'>&laquo</span>";
					row += "</a>";
					row += "</li>";
				}
				
				for(let i=startPageNum;i<=endPageNum;i++){
					row += "<li><a onclick='qnaPageMove("+(i)+")' class='cursor-pointer'>"+i+"</a></li>";
				}
				
				if(pageCount > endPageNum){
					row += "<li>";
					row += "<a onclick='qnaPageMove("+(pageNum+1)+")' class='cursor-pointer' aria-label='Next'>";
					row += "<span aria-hidden='true'>&raquo;</span>";
					row += "</a>";
					row += "</li>";
				}
				
				row += "</ul>";
				row += "</nav>";
				pageDiv.append(row);
				//페이징//
				
				//글 선택 모달 이벤트//
				setAnswerModalVal();
				//글 선택 모달 이벤트//
				
			}
		});
	}
	//qna 목록 로드//
	
	function setAnswerModalVal(){
		$("#qna-table>tbody>tr").click(function() {
			$("#queQnum").val($(this).children().eq(0).text());
			$("#queContent").val($(this).children().eq(6).text());
			$("#quePnum").val($(this).children().eq(7).text());
			if($(this).children().eq(8).text() == 1){
				$("#ansTitle").val("");
				$("#ansContent").val("");
				$("#btnWrite").prop("disabled", false);
				$("#btnModify").prop("disabled", true);
			}else{
				$("#ansTitle").val($(this).children().eq(3).text());
				$("#ansContent").val($(this).children().eq(6).text());
				$("#btnWrite").prop("disabled", true);
				$("#btnModify").prop("disabled", false);
			}
		});
	}
	
	function pagination(data, funcName){
		
	}
	
	function qnaPageMove(page){
		qnaListLoad("","",page);
	}
	
	function unansPageMove(page){
		unanswerLoad();
	}
</script>