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
		<link rel="stylesheet" href="static/css/pages/admin.css" />
	</head>
	<body>
		<%@ include file="../module/navigation.jsp" %>
		<div id="app">
			<div id="main">
			
				<div class="page-heading">
					<div class="page-title">
						<div class="row">
							<div class="col-12 col-md-6 order-md-1 order-last">
								<h3>Admin - Members</h3>
							</div>
							<div class="col-12 col-md-6 order-md-2 order-first">
								<nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
									<ol class="breadcrumb">
										<li class="breadcrumb-item">
											<a href="${homeUrl}">Home</a></li>
										<li class="breadcrumb-item">
											<a href="#">Admin</a></li>
										<li class="breadcrumb-item active" aria-current="page">Group Manage</li>
									</ol> 
								</nav>
							</div>
						</div>
					</div>
				</div>
				
				<section class="section">
					<c:url var="membersUrl" value="/members" />
					<div class="row">
						<div class="col-xl-4">
							<div class="card">
								<div class="card-content">
									<div class="card-body">
										<h4 class="card-title">조직도 설계</h4>
									
									</div>
								</div>
							</div>
						</div>
						
						<div class="col-66p">
							<div class="card">
								<div class="card-content">
									<div class="card-body">
										<h4 class="card-title">부서 정보</h4>
										
									</div>
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
		
		<script src="static/js/main.js"></script>
	</body>
</html>