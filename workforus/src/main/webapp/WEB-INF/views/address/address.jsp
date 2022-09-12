<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
					<c:url var="addressUrl" value="/address" />

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
									<c:url var="addressAddUrl" value="/address/add" />
									<button class="btn btn-outline-primary" type="button" data-bs-toggle="modal" data-bs-target="#addressAddModal">추가</button> <!-- 권한이 있는 사람만 -->
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
											<c:forEach items="${data}" var="data">
											<tr>
												<th>${data.empNm}</th>
												<th>${data.deptName}</th>
												<th>${data.empPhone}</th>
												<th>${data.empEmail}</th>
												<th>${data.empTel}</th>
											</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<div class="dataTable-bottom">
									<!-- 페이징 -->
								</div>
							</div>
						</div>
					</div>
					
				<!-- 주소록 추가 모달 -->	
				<div class="modal fade" id="addressAddModal" tabindex="-1" aria-labelledby="addrModalLabel1" aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="addrModalLabel1">주소록 추가</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<form action="#" method="post">
									<div class="mb-3">
										<label for="empName" class="col-form-label">이 름 :</label>
										<input type="text" class="form-control" id="empName" placeholder="이 름">
									</div>
									<div class="mb-3">
										<label for="deptName" class="col-form-label">부 서 :</label>
										<input class="form-control" id="deptName" placeholder="부 서">
									</div>
									<div class="mb-3">
										<label for="empPhone" class="col-form-label">휴대전화 :</label>
										<input class="form-control" id="empPhone" placeholder="휴대전화">
									</div>
									<div class="mb-3">
										<label for="empEmail" class="col-form-label">Email :</label>
										<input class="form-control" id="empEmail" placeholder="Email">
									</div>
									<div class="mb-3">
										<label for="empTel" class="col-form-label">내선전화 :</label>
										<input class="form-control" id="empTel" placeholder="내선전화">
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취 소</button>
								<button type="button" class="btn btn-primary">추 가</button>
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
			let table1 = document.g('#table1');
	        let dataTable = new simpleDatatables.DataTable(table1);
	        
	        function fileImport() {
				let inputHidden = document.getElementById("inputHidden");
				inputHidden.click();
			}
		</script>
		<script src="static/js/main.js"></script>
	</body>
</html>