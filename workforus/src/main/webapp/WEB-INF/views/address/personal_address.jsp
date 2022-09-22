<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>workforus - Personal Address</title>
		<%@ include file="../module/head.jsp" %>
		<link rel="stylesheet" href="${staticUrl}/vendors/simple-datatables/style.css" />
		<link rel="stylesheet" href="${staticUrl}/css/pages/address.css" />
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
											<a href="${homeUrl}">Home</a></li>
										<li class="breadcrumb-item active" aria-current="page">Personal Address</li>
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
							<h4 class="card-title">개인 주소록</h4>
						</div>
						<div class="card-body">
							<div class="row">
								<div class="buttons col-md-6">
									<button class="btn btn-outline-primary">메일</button>
									<button class="btn btn-outline-primary">내보내기</button>
									<input id="inputHidden" type="file" accept=".csv">
									<button id="inputHiddenBtn" class="btn btn-outline-primary" onclick="fileImport();">가져오기</button>
									<c:url var="addressAddUrl" value="/address/add" />
									<button class="btn btn-outline-primary" type="button" data-bs-toggle="modal" data-bs-target="#addressAddModal2">추가</button> <!-- 권한이 있는 사람만 -->
									<button class="btn btn-outline-primary">삭제</button> <!-- 권한이 있는 사람만 -->
								</div>
								<div class="col-md-6">
									<div class="input-group">
										<input type="text" class="form-control" placeholder="이름, 부서, 번호, 태그 검색"
											aria-label="이름, 부서, 번호, 태그 검색" aria-describedby="address-search">
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
												<th>이름(표시명)</th>
												<th>부 서</th>
												<th>직급/직책</th>
												<th>이 메 일</th>
												<th>전 화 번 호</th>
												<th>주 소</th>
												<th>소 속</th>
												<th>태 그</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<th>강동원</th>
												<th>해외사업부</th>
												<th>팀장</th>
												<th>dongdong@gmail.com</th>
												<th>070-0000-0000</th>
												<th>우리나라 어딘가</th>
												<th>(주)유라랜드</th>
												<th>협력업체</th>
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
					
				<!-- 주소록 추가 모달 -->	
				<div class="modal fade" id="addressAddModal2" tabindex="-1" aria-labelledby="addrModalLabel2" aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="addrModalLabel2">주소록 추가</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<form action="#" method="post">
									<div class="mb-3">
										<label for="customName" class="col-form-label">이름(표시명) :</label>
										<input type="text" class="form-control" id="customName" placeholder="이름(표시명)">
									</div>
									<div class="mb-3">
										<label for="customDept" class="col-form-label">부 서 :</label>
										<input class="form-control" id="customDept" placeholder="부 서">
									</div>
									<div class="mb-3">
										<label for="customJob" class="col-form-label">직급/직책 :</label>
										<input class="form-control" id="customJob" placeholder="직급/직책">
									</div>
									<div class="mb-3">
										<label for="customEmail" class="col-form-label">Email :</label>
										<input class="form-control" id="customEmail" placeholder="Email">
									</div>
									<div class="mb-3">
										<label for="customPhone" class="col-form-label">전화번호 :</label>
										<input class="form-control" id="customPhone" placeholder="전화번호">
									</div>
									<div class="mb-3">
										<label for="customAddr" class="col-form-label">주 소 :</label>
										<input class="form-control" id="customAddr" placeholder="주 소">
									</div>
									<div class="mb-3">
										<label for="customCompany" class="col-form-label">소 속 :</label>
										<input class="form-control" id="customCompany" placeholder="소 속">
									</div>
									<div class="mb-3">
										<label for="customTag" class="col-form-label">태 그 :</label>
										<input class="form-control" id="customTag" placeholder="태 그">
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
		<script src="${staticUrl}/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
		<script src="${staticUrl}/js/bootstrap.bundle.min.js"></script>
		
		<script src="${staticUrl}/vendors/simple-datatables/simple-datatables.js"></script>
		<script type="text/javascript">
			let table1 = document.g('#table1');
	        let dataTable = new simpleDatatables.DataTable(table1);
	        
	        function fileImport() {
				let inputHidden = document.getElementById("inputHidden");
				inputHidden.click();
			}
		</script>
		<script src="${staticUrl}/js/main.js"></script>
	</body>
</html>