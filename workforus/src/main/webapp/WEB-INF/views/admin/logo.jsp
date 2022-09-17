<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>workforus - Admin</title>
		<%@ include file="../module/head.jsp" %>
	</head>
	<body>
		<%@ include file="../module/navigation.jsp" %>
		<div id="app">
			<div id="main">
			
				<div class="page-heading">
					<div class="page-title">
						<div class="row">
							<div class="col-12 col-md-6 order-md-1 order-last">
								<h3>Admin - Logo Setting</h3>
							</div>
							<div class="col-12 col-md-6 order-md-2 order-first">
								<nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
									<ol class="breadcrumb">
										<li class="breadcrumb-item">
											<a href="${homeUrl}">Home</a></li>
										<li class="breadcrumb-item">
											<a href="#">Admin</a></li>
										<li class="breadcrumb-item active" aria-current="page">Logo Setting</li>
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
							<h4 class="card-title">메뉴 로고 설정</h4>
							<div class="card-body">
	
							</div>
							<h4 class="card-title">파비콘 설정</h4>
							<div class="card-body">
	
							</div>
							<h4 class="card-title">웹 타이틀 설정</h4>
							<div class="card-body">
	
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
	</body>
</html>