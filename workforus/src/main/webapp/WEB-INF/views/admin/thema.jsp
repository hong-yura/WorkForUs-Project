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
		<link rel="stylesheet" href="${staticUrl}/css/pages/admin.css"/>
	</head>
	<body>
		<%@ include file="../module/navigation.jsp" %>
		<div id="app">
			<div id="main">
			
				<div class="page-heading">
					<div class="page-title">
						<div class="row">
							<div class="col-12 col-md-6 order-md-1 order-last">
								<h3>Admin - Thema Setting</h3>
							</div>
							<div class="col-12 col-md-6 order-md-2 order-first">
								<nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
									<ol class="breadcrumb">
										<li class="breadcrumb-item">
											<a href="${homeUrl}">Home</a></li>
										<li class="breadcrumb-item">
											<a href="#">Admin</a></li>
										<li class="breadcrumb-item active" aria-current="page">Thema Setting</li>
									</ol> 
								</nav>
							</div>
						</div>
					</div>
				</div>
				
				<section class="section">
					<div class="card">
						<div class="card-header">
						
							<ul class="nav nav-tabs" id="myTab" role="tablist">
								<li class="nav-item" role="presentation">
									<a class="nav-link active" id="menuLogo-tab" href="#menuLogo" data-bs-toggle="tab"
										role="tab" aria-controls="menuLogo" aria-selected="true">Menu Logo</a>
								</li>
								<li class="nav-item" role="presentation">
									<a class="nav-link" id="loginImage-tab" href="#loginImage" data-bs-toggle="tab"
										role="tab" aria-controls="loginImage" aria-selected="false">Login Image</a>
								</li>
							</ul>
							<div class="tab-content" id="myTabContent">
								<div class="tab-pane fade active show" id="menuLogo" role="tabpanel" aria-labelledby="menuLogo-tab">
									<c:url value="/admin/thema" var="adminThemaUrl" />
									<form action="${adminThemaUrl}" method="post" id="menuLogoForm" enctype="multipart/form-data">
										<div class="card-body">
											<div class="row">
												<div class="card-title-cus">
													<h4 class="card-title">메뉴 로고 설정</h4>
												</div>
												<div class="col-lg-5">
													<img id="logo-prevImage" alt="메뉴 로고 이미지" src="https://via.placeholder.com/145x45.png">
												</div>
												<div class="col-lg-4">
													<label class="btn btn-lavender" for="upload-menu-logo-img">Upload New Menu Logo
														<input hidden id="upload-menu-logo-img" accept="image/png" type="file" >
													</label>
													<button type="button" class="btn btn-outline-danger">Reset</button>
												</div>
											</div>
											<div class="col-12 margin-cus">
												<p>메뉴 네비게이션 상단의 로고 이미지를 설정 할 수 있습니다.<br>
												※ PNG 파일 형식 145x45 사이즈로 등록하세요.
												</p>
											</div>
										</div>
									</form>
								</div>
								<div class="tab-pane fade" id="loginImage" role="tabpanel" aria-labelledby="loginImage-tab">
									<form action="" method="post" id="loginImageForm">
										<div class="card-body">
											<div class="row">
												<div class="card-title-cus">
													<h4 class="card-title">로그인 배경 이미지 설정</h4>
												</div>
												<div class="col-lg-5">
													<img alt="로그인 이미지" src="https://via.placeholder.com/480x270.png">
												</div>
												<div class="col-lg-4">
													<label class="btn btn-lavender" for="upload-login-img">Upload New Login Image
														<input hidden id="upload-login-img" accept="image/png, image/jpg" type="file" >
													</label>
													<button type="button" class="btn btn-outline-danger">Reset</button>
												</div>
											</div>
											<div class="col-12 margin-cus">
												<p>로그인 화면의 배경 이미지를 설정 할 수 있습니다.<br>
												※ 모바일 웹 디스플레이 비율 16:9 ,  1920x1080 이상의 해상도 이미지를 권장합니다.<br>
												※ PNG, JPG 파일 형식으로 등록하세요.
												</p>
											</div>
										</div>
									</form>
								</div>
							</div>
							<hr>
							<div class="col-12 d-flex justify-content-end">
								<button type="button" class="btn btn-lg btn-primary" id="saveLogoBtn" onclick="saveLogo();">Save Change</button>
							</div>
						</div>
					</div> <!-- card End -->
				</section>

				<%@ include file="../module/footer.jsp" %>
			</div>
		</div>
		<script src="${staticUrl}/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
		<script src="${staticUrl}/js/bootstrap.bundle.min.js"></script>
		<script src="${staticUrl}/js/main.js"></script>
	</body>
</html>