<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"> 
	<title>workforus - 설문</title>
    <%@ include file="../module/head.jsp" %>
    <link rel="stylesheet" href="${staticUrl}/css/pages/survey.css">
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	
    <div id="app">
      <div id="main">
      	<div class="page-heading margin-left-10">
      		<h3>Board</h3> <!-- 게시판 이름 -->
      	</div>
	      	<!-- board -->
      	<div class="page-content">
			<section class="row">
				<div class="col-12"> 
					
					<!-- 메인 -->
					<div class="section-main radius">
						<div class="main-button">
							<c:url value="/survey/add" var="surveyAddUrl"/>
							<button type="submit" class="btn black" onclick="location.href='${surveyAddUrl}'" style="font-weight: bold;"><i class="bi bi-plus-circle"></i> 설문지 생성</button>
							<div class="btn-group" style="width: 70px;float: right;">
							 	<button type="button" class="btn btn-sm dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
							    	상태
							  	</button>
							  	<ul class="dropdown-menu">
							    	<li><a class="dropdown-item" href="#">진행예정</a></li>
						    	  	<li><hr class="dropdown-divider"></li>
							    	<li><a class="dropdown-item" href="#">진행중</a></li>
						    	  	<li><hr class="dropdown-divider"></li>
							    	<li><a class="dropdown-item" href="#">진행완료</a></li>
							  	</ul>
							</div>
							<div class="search-container">
								<i class="bi bi-search"> </i><input class="search-input" type="search" placeholder=" 입력">
								<button class="btn-style black" type="button">검색</button>
							</div>
							<hr style="margin: 0px; margin-bottom: 3px;">
						</div>
						<div class="dataTable-container">
							<table class="table dataTable-table black">
								 <colgroup>
								 	<col width=2%>
						            <col width=8%>
						            <col width=40%>
						            <col width=10%>
						            <col width=20%>
						            <col width=10%>
						        </colgroup>
								<thead>
									<tr>
										<th></th>
										<th>번호</th>
										<th>제목</th>
										<th>질문자</th>
										<th>설문기간</th>
										<th>상태</th>
									</tr>
								</thead>
								<tbody>
									<!-- 첫 번째 c:foreach문으로 공지를 출력하고, 두 번째 c:foreach문으로 일반 게시글을 출력 -->
									<c:if test="${not empty surveyList}">
										<c:forEach items="${surveyList}" var="survey" varStatus="status">
											<c:url var="detailUrl" value="/survey/detail">
												<c:param name="surveyNo" value="${survey.surveyNo}"/>
											</c:url>
											<tr class="table" style="cursor: pointer; color: black;"  onclick="location.href='${detailUrl}'">
												<td></td>
												<td>${status.count}</td>
												<td>${survey.surveyTitle}</td>
												<td>${survey.empId}</td>
												<td>${survey.startDt} ~ ${survey.endDt}</td>
												<td>${survey.progress}</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</section>
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