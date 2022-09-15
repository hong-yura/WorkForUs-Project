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
	<div class="container">
	
	</div>
	
	<div id="app">
      <div id="main">
	   <%@ include file="../module/footer.jsp" %>
      </div>
    </div>
    
    <script src="${cssUrl}/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="${cssUrl}/js/bootstrap.bundle.min.js"></script>

    <script src="${cssUrl}/vendors/apexcharts/apexcharts.js"></script>
    <script src="${cssUrl}/js/pages/dashboard.js"></script>

    <script src="${cssUrl}/js/main.js"></script>
</body>
</html>