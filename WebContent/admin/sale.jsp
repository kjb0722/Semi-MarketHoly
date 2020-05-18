<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="${cp }/css/input.css" />

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<style>
table, th, td {
	text-align: center;
}

table>tbody>th>td {
	background-color: aqua;
	vertical-align: middle;
}

input[type="checkbox"] {
	border: 2px solid #bcbcbc;
	cursor: pointer;
	zoom: 1.8;
}
</style>
<div class="container">
	<div class="row">
		<h3>세일 상품 관리</h3>
	</div>
	<div class="row form-group">
		<div class="col-md-3">
			<label class="radio-container">
				카테고리별 적용
				<input type="radio" name="sale-way" id="sale-cat" checked="checked">
				<span class="radiomark"></span>
			</label>
			<label class="radio-container">
				세부 카테고리별 적용
				<input type="radio" name="sale-way" id="sale-catType">
				<span class="radiomark"></span>
			</label>
			<label class="radio-container">
				상품별 적용
				<input type="radio" name="sale-way" id="sale-prod">
				<span class="radiomark"></span>
			</label>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-md-2">
			<span class="label label-success">카테고리</span>
			<select id="cat" name="cat" class="form-control">
				<c:forEach var="dto" items="${catList }">
					<option value="${dto.cnum }">${dto.name }</option>
				</c:forEach>
			</select>
		</div>
		<div class="col-md-2">
			<span class="label label-success">세부 카테고리명</span>
			<select id="catType" name="catType" class="form-control">

			</select>
		</div>
	</div>
	<div class="row form-group">
		<div class="col-md-3">
			<span class="label label-success">할인 이름</span>
			<input type="text" class="form-control" placeholder="할인명을 입력하세요." maxlength="30" name="name" id="name">
		</div>
	</div>
	<div class="row form-group">
		<div class="col-md-10">
			<span class="label label-success">적용 날짜</span>
			<input type="text" id="start-date">
			<span>~</span>
			<input type="text" id="end-date">
		</div>
	</div>
	<div class="row form-group">
		<div class="col-md-2">
			<span class="label label-success">할인율</span>
			<input name="percent" id="percent" type="number" class="form-control" placeholder="할인율을 입력하세요." min="1" max="100">
		</div>
	</div>
	<div class="row form-group">
		<div class="col-md-2">
			<input type="button" class="btn btn-primary form-control " id="btnSale" value="할인 적용">
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<table id="table-prod" class="table table-bordered">
				<thead>
					<tr>
						<th style="width: 5%"><input type="checkbox" id="chkbox-all" name="prod-chk"></th>
						<th style="width: 5%">상품 번호</th>
						<th style="width: 15%">썸네일</th>
						<th style="width: 35%">상품명</th>
						<th style="width: 12.5%">가격</th>
						<th style="width: 12.5%">재고</th>
						<th style="width: 15%">적용 할인</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		//최초 페이지 이동시 이벤트 실행
		$("#sale-cat").click();		
		$("#cat").change();
	});
	
	//할인 적용
	$("#btnSale").click(function(){
		$("#table-prod>tbody>tr").each(function(i, tr) {
			if($(this).find("td").eq(0).children().prop("checked") == true){
				let pnum = $(this).find("td").eq(1).text();
				jQuery.ajax({
					dataType:"JSON",
					url:`${cp}/admin/saleProdAdd.do`,
					method:"get",
					data:{pnum:pnum},
					success:function(data){
						alert("aa");
					}
				});
			}
		});
		
		saleAdd();
	});
	
	function saleAdd(){
		let name = $("#name");
		if(name.val() == ""){
			alert("할인 이름을 입력하세요.");
			name.focus();
			return;
		}
		let startDate = $("#start-date");
		if(startDate.datepicker("getDate") == null){
			alert("시작 날짜를 지정하세요.");
			startDate.focus();
			return;
		}
		let endDate = $("#end-date");
		if(endDate.datepicker("getDate") == null){
			alert("종료 날짜를 지정하세요.");
			endDate.focus();
			return;
		}
		if(startDate.datepicker("getDate") > endDate.datepicker("getDate")){
			alert("시작 날짜가 종료 날짜보다 이전 날짜를 선택하세요.");
			startDate.focus();
			return;
		}
		let percent = $("#percent");
		if(percent.val() == ""){
			alert("할인율을 입력하세요.");
			percent.focus();
			return;
		}
		
		let sale = {
				name:name.val(),
				startDate:startDate.datepicker({dateFormat:"yyyy-mm-dd"}).val(),
				endDate:endDate.datepicker({dateFormat:"yyyy-mm-dd"}).val(),
				percent:percent.val()
		};
		
		if($("#sale-cat").prop("checked") == true){
			let cat = $("#cat").val();
			sale.catNum = cat;
			sale.catTypeNum = -1;
			catSaleAdd(sale);
		}else if($("#sale-catType").prop("checked") == true){
			let num = $("#catType").val();
			if(num == null){
				alert("세부 카테고리가 없습니다.");
				return;
			}
			catNum = num.substr(0,num.indexOf("|"));
			catTypeNum = num.substr(num.indexOf("|")+1);
			
			sale.catNum = catNum;
			sale.catTypeNum = catTypeNum;
			catSaleAdd(sale);
		}else{
			$("#table-prod>tbody>tr").each(function(i, tr) {
				
			});
		}
	}
	function catSaleAdd(sale){
		jQuery.ajax({
			dataType: "JSON",
			url: `${cp}/admin/saleCatAdd.do`,
			method: "get",
			data:{catNum:sale.catNum,
				catTypeNum:sale.catTypeNum,
				name:sale.name,
				startDate:sale.startDate,
				endDate:sale.endDate,
				percent:sale.percent},
			success:function(data){
				if(data.n>0){
					alert(data.n+"건 할인 적용 완료");	
					inputInit();
				}else{
					location = `${cp}/error.do`;
				}
			}
		});
	}
	//할인 적용
	
	//input 초기화//
	function inputInit(){
		$("#name").val("");
		$("#percent").val("");
	}
	//input 초기화//
	
	//시작 날짜, 종료 날짜
	$("#start-date,#end-date").datepicker({
		dateFormat: 'yy-mm-dd' //Input Display Format 변경
            ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
            ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
            ,changeYear: true //콤보박스에서 년 선택 가능
            ,changeMonth: true //콤보박스에서 월 선택 가능                
            ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
            ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
            ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
            ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
            ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
            ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
            ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
            ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
            ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
            /* ,minDate: "-1M" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
            ,maxDate: "+1M" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)    */
	});
	//시작 날짜, 종료 날짜

	//세일 방법 라디오버튼 이벤트
	//카테고리별
	$("#sale-cat").click(function(){
		$("#catType").prop("disabled", true);
		$("input[name='prod-chk']").prop("disabled", true);
		prodListLoad();
	});
	//세부 카테고리별
	$("#sale-catType").click(function(){
		$("#catType").prop("disabled", false);
		catTypeLoad($("#cat").val());
		$("input[name='prod-chk']").prop("disabled", true);
	});
	//상품별 적용
	$("#sale-prod").click(function() {
		$("#catType").prop("disabled", false);
		$("input[name='prod-chk']").prop("disabled", false);
		prodListLoad();
	});
	//세일 방법 라디오버튼 이벤트
	
	
	//상품 체크박스 전체 선택 이벤트
	$("#chkbox-all").click(function(){
		$("#table-prod tbody tr input[type=checkbox]").prop("checked",$(this).prop("checked"));
	});
	//상품 체크박스 전체 선택 이벤트

	//카테고리 변경시 세부 카테고리 로드//
	$("#cat").change(function() {
		let cat = $("#cat").val();
		if(cat != ""){
			catTypeLoad(cat);
		}
	});
	
	let xhr;
	function catTypeLoad(cat){
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = catTypeLoadOk;
		xhr.open("get", `${cp}/admin/catTypeSel.do?type=${'${cat}'}`, true);
		xhr.send();
	}
	
	function catTypeLoadOk(){
		if(xhr.readyState == 4 && xhr.status == 200){
			let json = JSON.parse(xhr.responseText);
			$("#catType").empty();
			for(let j of json){
				$("#catType").append("<option value='"+j.cnum+"|"+j.type+"''>"+j.name+"</option>");
			}
			
			prodListLoad();
		}
	}
	//카테고리 변경시 세부 카테고리 로드//
	
	//상품 리스트 로드//
	function prodListLoad(){
		let num;
		let catNum;
		let catTypeNum;
		if($("#sale-cat").prop("checked") == true){
			catNum = $("#cat").val();
			catTypeNum = -1;
		}else{
			num = $("#catType").val();
			if(num == null){
				alert("세부 카테고리가 없습니다.");
				return;
			}else{
				catNum = num.substr(0,num.indexOf("|"));
				catTypeNum = num.substr(num.indexOf("|")+1);				
			}
		}
		
		jQuery.ajax({
			dataType:"JSON",
			url: `${cp}/admin/saleProdList.do`,
			method:"get",
			data:{catNum:catNum,
				catTypeNum:catTypeNum},
			success:function(data){
				let table = $("#table-prod>tbody");
				table.empty();
				for(let dto of data){
					let row = "<tr class='align-middle'>";
					if(dto.onSaleName == -1){						
						row += "<td><input type='checkbox' name='prod-chk'></td>";
					}else{
						row += "<td><input type='checkbox' name='prod-chk' disabled='disabled'></td>";
					}
					row += "<td>"+dto.pnum+"</td>";
					row += `<td><img src='${cp}/img/${'${dto.thumb_save}'}'></td>`;
					row += "<td>"+dto.name+"</td>";
					row += "<td>"+dto.price+"</td>";
					row += "<td>"+dto.stock+"</td>";
					if(dto.onSaleName == -1){
						row += "<td></td>";						
					}else{
						row += "<td>"+dto.onSaleName+"</td>";											
					}
					row += "</tr>";
					table.append(row);
				}
				
				$("#table-prod>tbody>tr>td").css("vertical-align","middle");
				
				$("#table-prod>tbody>tr>td>img").css("width","200px");
				$("#table-prod>tbody>tr>td>img").css("height","200px");
				
				let saleCat = $("#sale-cat").prop("checked");
				let saleCatType = $("#sale-catType").prop("checked");
				if(saleCat == true || saleCatType == true){
					$("input[name='prod-chk']").prop("disabled",true);
				}
			}
		});
	}
	//상품 리스트 로드//
	
	//세부 카테고리 이벤트
	$("#catType").change(function(){
		prodListLoad();
	});
	//세부 카테고리 이벤트
</script>