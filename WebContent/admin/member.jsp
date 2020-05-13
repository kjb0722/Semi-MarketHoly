<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container">
	<div class="form-inline">
		<label for="word">검색명</label> <input type="text" class="form-control"
			id="word" placeholder="검색명을 입력하세요.">
		<input type="button" class="btn btn-primary" id="btnSearch" value="검색">
	</div>

	<div class="col-md-6">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Username</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>Mark</td>
					<td>Otto</td>
					<td>@mdo</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<script>
	let xhr;
	getMemList();
	function getMemList(){
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = getMemListOk;
		xhr.open("get",`${cp}/admin/list.do`,true);
		xhr.send();
	}
	function getMemListOk(){
		if (xhr.readyState == 4 && xhr.status == 200) {
			removeList();
			
			
		}
	}
	document.getElementById("btnSearch").onclick = getMemList;
	
	function removeList(){
		
	}
</script>