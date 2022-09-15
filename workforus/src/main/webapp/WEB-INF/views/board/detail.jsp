<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>workforus - 게시판 : ${postData.postTitle}</title>
	<!-- 헤더 수정 후 넣기 -->
	<%@ include file="../module/header.jsp" %>
    <link rel="stylesheet" href="${staticUrl}/css/pages/board.css">
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<div id="app">
      <div id="main">
      	<div class="page-heading margin-left-10">
      		<h3>Board</h3> 
      	</div>
	      	<!-- board -->
      	<div class="page-content">
			<section class="row">
				<div class="col-12"> 
					<div class="post-container radius">
						<div class="post-top">
							<div class="avatar avatar-md emp-img"> <!-- 이미지 -->
								<img class="emp-image" src="${staticUrl}/images/faces/1.jpg">
							</div>
							<div class="title-container">
								<h3>${postData.postTitle}</h3>
							</div>
						</div>
						<div class="post-main radius">
							
						</div>
						<div class="view-like-container">
							<i class="bi bi-eye"> ${postData.viewCnt}</i>
							<button type="button" class="btn" onclick=""><i class="bi bi-hand-thumbs-up"></i> ${postData.likeCnt}</button>
						</div>
					</div>
				</div>
			</section>
		</div>
		<!-- footer -->
        <%@ include file="../module/footer.jsp" %>
      </div>
    </div>
    
    <script src="${staticUrl}/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="${staticUrl}/js/bootstrap.bundle.min.js"></script>

    <script src="${staticUrl}/vendors/apexcharts/apexcharts.js"></script>
    <script src="${staticUrl}/js/pages/dashboard.js"></script>

    <script src="${staticUrl}/js/main.js"></script>
</body>
</html>