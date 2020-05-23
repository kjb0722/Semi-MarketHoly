<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<li>
			<a href="#monthSell" data-toggle="tab">월별 매출</a>
		</li>
		<li>
			<a href="#2" data-toggle="tab">일별 매출</a>
		</li>
		<li>
			<a href="#3" data-toggle="tab">성별 매출</a>
		</li>
	</ul>

	<div class="tab-content">
		<div class="tab-pane" id="monthSell">
			<div id="lineChart"></div>
		</div>
		<div class="tab-pane" id="2">
			<h3>Notice the gap between the content and tab after applying a background color</h3>
		</div>
		<div class="tab-pane" id="3">
			<h3>add clearfix to tab-content (see the css)</h3>
		</div>
	</div>
</div>
<script>
	$(document).ready(function() {
		$("#stat-Tab a:first").tab("show");
	});
	
	$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
		var target = $(e.target).attr("href") // activated tab
		if (target == "#monthSell") {
			jQuery.ajax({
				dataType : "JSON",
				url : `${cp}/admin/statMonthSell.do`,
				success : function(data) {
					alert(data);
				}
			});
			
			var chart = bb.generate({
				data : {
					columns : [ [ "data1", 30, 200, 100, 400, 150, 250 ],
							[ "data2", 50, 20, 10, 40, 15, 25 ] ]
				},
				bindto : "#lineChart"
			});
		}
	});

</script>
