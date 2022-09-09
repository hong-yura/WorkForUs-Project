<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>workforus - 게시판</title>
	 <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet"/>
    <link rel="stylesheet" href="static/css/bootstrap.css" />
    <link rel="stylesheet" href="static/vendors/iconly/bold.css" />
    <link rel="stylesheet" href="static/vendors/perfect-scrollbar/perfect-scrollbar.css"/>
    <link rel="stylesheet" href="static/vendors/bootstrap-icons/bootstrap-icons.css"/>
    <link rel="stylesheet" href="static/css/app.css" />
    <link rel="icon" href="static/images/logo/favicon.ico" />
    <link rel="stylesheet" href="static/css/pages/board.css">
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	
    <div id="app">
      <div id="main">
      	<!-- board -->
      	<div class="container">
			<div class="row">
			
				<!-- 사이드 -->
				<div class="col-sm-2">
					<div class="left-side radius">
						<div class="container-button">
							<button type="button" class="btn-blue radius"></button>
						</div>
					</div>
				</div>
				
				<!-- 본문 -->
				<div class="col-sm-10"> 
					<div class="section-top radius">
						
					</div>
					<div class="section-main radius">
					
					</div>
				</div>
			</div>
		</div>
		
		<!-- footer -->
        <%@ include file="../module/footer.jsp" %>
      </div>
    </div>
    <script src="static/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="static/js/bootstrap.bundle.min.js"></script>

    <script src="static/vendors/apexcharts/apexcharts.js"></script>
    <script src="static/js/pages/dashboard.js"></script>

    <script src="static/js/main.js"></script>
</body>
</html>