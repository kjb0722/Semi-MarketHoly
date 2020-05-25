<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 차트 cdn -->
<script src="https://d3js.org/d3.v5.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/billboard.js/1.12.9/billboard.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/billboard.js/1.12.9/billboard.min.css">

<style>
.stat-nav>li>a:focus, .stat-nav>li>a:hover {
	background-color: white;
	text-decoration: none;
	border-radius: 0;
}

.nav-tabs>li>a:hover {
	font-weight: bold;
}
</style>
<div class="container">
	<ul class="nav nav-tabs stat-nav" id="stat-Tab">
		<li><a href="#monthSell" data-toggle="tab">월별 매출</a></li>
		<li><a href="#daySell" data-toggle="tab">일별 매출</a></li>
		<li><a href="#genderStat" data-toggle="tab">성별 구매 건수</a></li>
	</ul>

	<div class="tab-content">
		<div class="tab-pane" id="monthSell">
			<div class="row">
				<div class="col-md-2 form-inline">
					<select id="year-monthSell" class="form-control"></select>
				</div>
			</div>
			<div class="row">
				<div id="monthSellChart"></div>
			</div>
		</div>
		<div class="tab-pane" id="daySell">
			<div class="row">
				<div class="col-md-4 form-inline">
					<select id="year-daySell" class="form-control"></select>
					<select id="month-daySell" class="form-control"></select>
				</div>
			</div>
			<div class="row">
				<div id="daySellChart"></div>
			</div>
		</div>
		<div class="tab-pane" id="genderStat">
			<div class="row">
				<div id="genderChart"></div>
			</div>
		</div>
	</div>
</div>
<script>
	$(document).ready(function() {
		setMonthSellDate();
		setDaySellDate();
		$("#stat-Tab a:first").tab("show");
	});

	//일별 매출 통계 년 월 콤보박스 세팅//
	function setDaySellDate() {
		var dt = new Date();
		var year = "";
		var com_year = dt.getFullYear();

		for (var y = (com_year); y >= (com_year - 5); y--) {
			$("#year-daySell").append(
					"<option value='"+ y +"'>" + y + " 년" + "</option>");
		}

		var month;
		for (var i = 1; i <= 12; i++) {
			$("#month-daySell").append(
					"<option value='"+ i +"'>" + i + " 월" + "</option>");
		}
	}
	//일별 매출 통계 년 월 콤보박스 세팅//

	//월별 매출 통계 년 월 콤보박스 세팅//
	function setMonthSellDate() {
		var dt = new Date();
		var year = "";
		var com_year = dt.getFullYear();

		for (var y = (com_year); y >= (com_year - 5); y--) {
			$("#year-monthSell").append(
					"<option value='"+ y +"'>" + y + " 년" + "</option>");
		}
	}
	//월별 매출 통계 년 월 콤보박스 세팅//

	//탭 이벤트//
	$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
		var target = $(e.target).attr("href") // activated tab
		if (target == "#monthSell") {
			monthSellLoad();
		} else if (target == "#daySell") {
			daySellLoad();
		} else if (target == "#genderStat") {
			genderStatLoad();
		}
	});
	//탭 이벤트//

	//성별 통계 로드//
	function genderStatLoad() {
		jQuery.ajax({
			dataType : "JSON",
			url : `${cp}/admin/genderStat.do`,
			success : function(data) {
				let man = ["남자"];
				let woman = ["여자"];
				for(let dto of data){
					if(dto.gender == "남자"){
						man.push(dto.cnt);
					}else{
						woman.push(dto.cnt);
					}
				}
				
				var chart = bb.generate({
					data : {
						columns : [ man,woman ],
						type : "pie",
					},
					bindto : "#genderChart"
				});				
			}
		});
	}
	//성별 통계 로드//

	//일별 매출 통계 년 월 콤보박스 세팅//
	$("#year-daySell").change(function() {
		daySellLoad();
	});
	$("#month-daySell").change(function() {
		daySellLoad();
	});
	//일별 매출 통계 년 월 콤보박스 세팅//

	//일별 매출 통계 로드//
	function daySellLoad() {
		let year = $("#year-daySell").val();
		let month = $("#month-daySell").val();
		jQuery.ajax({
			dataType : "JSON",
			method : "get",
			url : `${cp}/admin/daySell.do`,
			data : {
				year : year,
				month : month
			},
			success : function(data) {
				let numSell = [ "주문 건수" ];
				if(typeof data[0] != "undefined"){
					numSell.push(data[0].day1);
					numSell.push(data[0].day2);
					numSell.push(data[0].day3);
					numSell.push(data[0].day4);
					numSell.push(data[0].day5);
					numSell.push(data[0].day6);
					numSell.push(data[0].day7);
					numSell.push(data[0].day8);
					numSell.push(data[0].day9);
					numSell.push(data[0].day10);
					numSell.push(data[0].day11);
					numSell.push(data[0].day12);
					numSell.push(data[0].day13);
					numSell.push(data[0].day14);
					numSell.push(data[0].day15);
					numSell.push(data[0].day16);
					numSell.push(data[0].day17);
					numSell.push(data[0].day18);
					numSell.push(data[0].day19);
					numSell.push(data[0].day20);
					numSell.push(data[0].day21);
					numSell.push(data[0].day22);
					numSell.push(data[0].day23);
					numSell.push(data[0].day24);
					numSell.push(data[0].day25);
					numSell.push(data[0].day26);
					numSell.push(data[0].day27);
					numSell.push(data[0].day28);
					numSell.push(data[0].day29);
					numSell.push(data[0].day30);
					numSell.push(data[0].day31);
				}
				let amount = [ "매출액(단위:만원)" ];
				if(typeof data[1] != "undefined"){
					amount.push(data[1].day1);
					amount.push(data[1].day2);
					amount.push(data[1].day3);
					amount.push(data[1].day4);
					amount.push(data[1].day5);
					amount.push(data[1].day6);
					amount.push(data[1].day7);
					amount.push(data[1].day8);
					amount.push(data[1].day9);
					amount.push(data[1].day10);
					amount.push(data[1].day11);
					amount.push(data[1].day12);
					amount.push(data[1].day13);
					amount.push(data[1].day14);
					amount.push(data[1].day15);
					amount.push(data[1].day16);
					amount.push(data[1].day17);
					amount.push(data[1].day18);
					amount.push(data[1].day19);
					amount.push(data[1].day20);
					amount.push(data[1].day21);
					amount.push(data[1].day22);
					amount.push(data[1].day23);
					amount.push(data[1].day24);
					amount.push(data[1].day25);
					amount.push(data[1].day26);
					amount.push(data[1].day27);
					amount.push(data[1].day28);
					amount.push(data[1].day29);
					amount.push(data[1].day30);
					amount.push(data[1].day31);
				}

				var chart = bb.generate({
					data : {
						/* columns : [ [ "data1", 30, 200, 100, 400, 150, 250 ],
								[ "data2", 50, 20, 10, 40, 15, 25 ] ] */
						columns : [ numSell, amount ],
						type : "bar",
						types : {
							"주문 건수" : "line"
						},
					},
					"axis" : {
						x : {
							type : "category",
							categories : ["1일","2일","3일","4일","5일","6일","7일","8일","9일","10일","11일","12일","13일","14일","15일","16일","17일","18일","19일","20일","21일","22일","23일","24일","25일","26일","27일","28일","29일","30일","31일"]
						},
					},
					bindto : "#daySellChart"
				});
			}
		});
	}
	//일별 매출 통계 로드//

	//월별 매출 통계 콤보박스 이벤트//
	$("#year-monthSell").change(function() {
		monthSellLoad();
	});
	//월별 매출 통계 콤보박스 이벤트//

	//월별 매출 통계 로드//
	function monthSellLoad() {
		let year = $("#year-monthSell").val();
		jQuery.ajax({
			dataType : "JSON",
			method : "get",
			url : `${cp}/admin/monthSell.do`,
			data : {
				year : year
			},
			success : function(data) {
				let numSell = [ "주문 건수" ];
				if(typeof data[0] != "undefined"){
					numSell.push(data[0].jan);
					numSell.push(data[0].feb);
					numSell.push(data[0].mar);
					numSell.push(data[0].apr);
					numSell.push(data[0].may);
					numSell.push(data[0].jun);
					numSell.push(data[0].jul);
					numSell.push(data[0].aug);
					numSell.push(data[0].sep);
					numSell.push(data[0].oct);
					numSell.push(data[0].nov);
					numSell.push(data[0].dec);
				}
				
				let amount = [ "매출액(단위:만원)" ];
				if(typeof data[1] != "undefined"){
					amount.push(data[1].jan);
					amount.push(data[1].feb);
					amount.push(data[1].mar);
					amount.push(data[1].apr);
					amount.push(data[1].may);
					amount.push(data[1].jun);
					amount.push(data[1].jul);
					amount.push(data[1].aug);
					amount.push(data[1].sep);
					amount.push(data[1].oct);
					amount.push(data[1].nov);
					amount.push(data[1].dec);
				}
				var chart = bb.generate({
					"data" : {
						/* columns : [ [ "data1", 30, 200, 100, 400, 150, 250 ],
								[ "data2", 50, 20, 10, 40, 15, 25 ] ] */
						columns : [ numSell, amount ],
						type : "bar",
						types : {
							"주문 건수" : "line"
						},
					},
					"axis" : {
						x : {
							type : "category",
							categories : [ "1월", "2월", "3월", "4월", "5월", "6월",
									"7월", "8월", "9월", "10월", "11월", "12월" ]
						},
					},
					bindto : "#monthSellChart"
				});
			}
		});
	}
	//월별 매출 통계 로드//
</script>
