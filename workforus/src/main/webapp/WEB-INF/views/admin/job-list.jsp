<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>workforus - Job List</title>
	<%@ include file="../module/head.jsp" %>
	<link rel="stylesheet" href="${staticUrl}/vendors/simple-datatables/style.css" />
</head>
<body>
<%@ include file="../module/navigation.jsp" %>
	<div id="app">
		<div id="main">
		
			<div class="page-heading">
				<div class="page-title">
					<div class="row">
						<div class="col-12 col-md-6 order-md-1 order-last">
							<h3>Admin - Job List</h3>
						</div>
						<div class="col-12 col-md-6 order-md-2 order-first">
							<nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
								<ol class="breadcrumb">
									<li class="breadcrumb-item">
										<a href="${homeUrl}">Home</a></li>
									<li class="breadcrumb-item">
										<a href="#">Admin</a></li>
									<li class="breadcrumb-item active" aria-current="page">Job List</li>
								</ol> 
							</nav>
						</div>
					</div>
				</div>
			</div>
			
			<section class="section">
				<div class="card">
				<c:url var="jobListUrl" value="/job-list" />

					<div class="card-header">
						<h4 class="card-title">직급 조회 목록</h4>
					</div>
					<div class="card-body">
						<div class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
							<div class="dataTable-top">
									<div class="dataTable-dropdown">
										<select class="dataTable-selector form-select">
											<option value="5">5개 보기</option>
											<option value="10">10개 보기</option>
											<option value="15">15개 보기</option>
											<option value="20">20개 보기</option>
										</select>
									</div>
									<div class="col-md-6"></div>
									<div class="col-md-5">
										<div class="input-group">
											<input type="text" class="form-control" placeholder="사번, 이름, 직급 검색"
												aria-label="사번, 이름, 직급 검색" aria-describedby="job-search">
											<button class="btn btn-outline-secondary" type="button" id="job-search">검 색</button>
										</div>
									</div>
							</div>
							<div class="dataTable-container">
								<table class="table table-hover dataTable-table" id="jobTable">

									<thead class="table-primary">
										<tr>
											<th data-sortable>사 번</th>
											<th data-sortable>이 름</th>
											<th data-sortable>직 급</th>
										</tr>
									</thead>
									<tbody id="jobsList">
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
	<script src="${staticUrl}/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script src="${staticUrl}/js/bootstrap.bundle.min.js"></script>
	<script src="${staticUrl}/js/main.js"></script>

	<script src="${staticUrl}/js/pages/admin/job/jobs-list.js"></script>

</body>
</html>