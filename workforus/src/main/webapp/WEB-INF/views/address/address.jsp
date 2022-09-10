<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>workforus - Address</title>
		<%@ include file="../module/header.jsp" %>
		<link rel="stylesheet" href="static/vendors/simple-datatables/style.css" />
		<link rel="stylesheet" href="static/css/pages/address.css" />
	</head>
	<body>
		<%@ include file="../module/navigation.jsp" %>
		<div id="app">
			<div id="main">
			
				<div class="page-heading">
					<div class="page-title">
						<div class="row">
							<div class="col-12 col-md-6 order-md-1 order-last">
								<h3>Address</h3>
							</div>
							<div class="col-12 col-md-6 order-md-2 order-first">
								<nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
									<ol class="breadcrumb">
										<li class="breadcrumb-item">
											<a href="views/home.jsp">Home</a></li> <!-- curl로 수정 -->
										<li class="breadcrumb-item active" aria-current="page">Address</li>
									</ol> 
								</nav>
							</div>
						</div>
					</div>
				</div>
				
				<section class="section">
					<div class="card">
						<div class="card-header">
							<h4 class="card-title">공용 주소록</h4>
						</div>
						<div class="card-body">
							<div class="row">
								<div class="buttons col-md-6">
									<button class="btn btn-outline-primary">메일</button>
									<button class="btn btn-outline-primary">내보내기</button>
									<input id="inputHidden" type="file" accept=".csv">
									<button id="inputHiddenBtn" class="btn btn-outline-primary" onclick="fileImport();">가져오기</button>
									<button class="btn btn-outline-primary">추가</button> <!-- 권한이 있는 사람만 -->
									<button class="btn btn-outline-primary">삭제</button> <!-- 권한이 있는 사람만 -->
								</div>
								<div class="col-md-6">
									<div class="input-group">
										<input type="text" class="form-control" placeholder="이름, 부서, 번호 검색"
											aria-label="이름, 부서, 번호 검색" aria-describedby="address-search">
										<button class="btn btn-outline-secondary" type="button" id="address-search">검 색</button>
									</div>
								</div>
							</div>
							<div class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
								<div class="dataTable-top">
									
								</div>
								<div class="dataTable-container">
									<table class="table table-hover dataTable-table" id="table1">
										
										<colgroup>
											<col>
											<col>
											<col>
											<col>
											<col>
										</colgroup>
										<thead class="table-primary">
											<tr>
												<th>이 름</th>
												<th>부 서</th>
												<th>휴 대 전 화</th>
												<th>이 메 일</th>
												<th>내 선 번 호</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<th>박보검</th>
												<th>연구개발부</th>
												<th>010-1234-5678</th>
												<th>bogum@workforus.site</th>
												<th>070-0000-0000</th>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="dataTable-bottom">
									<!-- 페이징 -->
								</div>
							</div>
						</div>
					</div>
				</section>

				<%@ include file="../module/footer.jsp" %>
			</div>
		</div>
		<script src="static/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
		<script src="static/js/bootstrap.bundle.min.js"></script>
		
		<script src="static/vendors/simple-datatables/simple-datatables.js"></script>
		<script type="text/javascript">
			let table1 = document.querySelector('#table1');
	        let dataTable = new simpleDatatables.DataTable(table1);
	        
	        function fileImport() {
				let inputHidden = document.getElementById("inputHidden");
				inputHidden.click();
			}
		</script>
		<script src="static/js/main.js"></script>
	</body>
</html>