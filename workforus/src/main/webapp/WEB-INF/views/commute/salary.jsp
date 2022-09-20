<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>연봉 조회</title>
	<script type="text/javascript">
		var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
		var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
		  return new bootstrap.Tooltip(tooltipTriggerEl)
		})
	</script>
	<%@ include file="../module/head.jsp" %>
	<link rel="stylesheet" href="${staticUrl}/css/pages/tooltip.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

</head>
<body class="theme-light" style="overflow-y: auto;">
	 <%@ include file="../module/navigation.jsp" %>
	<div id="main">	
		<div class="page-heading">
						<div class="page-title">
							<div class="row">
								<div class="col-12 col-md-6 order-md-1 order-last">
									<h3>연봉 조회</h3>
								</div>
								<div class="col-12 col-md-6 order-md-2 order-first">
									<nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
										<ol class="breadcrumb">
											<li class="breadcrumb-item">
												<a href="#">Home</a></li>
											<li class="breadcrumb-item">
												<a href="#">Mypage</a></li>
											<li class="breadcrumb-item active" aria-current="page">근태관리</li>
										</ol> 
									</nav>
								</div>
							</div>
						</div>
						<% 
							Calendar cal = Calendar.getInstance(); 
							cal.getTime();
							int calYear = cal.get(Calendar.YEAR);
							int calMonth = cal.get(Calendar.MONTH) + 1;
						%>
						<div style="text-align:center; margin:3rem;">
							<h4><%=calYear%>년 <%=calMonth%>월</h4>
						</div>
						<div style="margin-left:5rem;">
							지급 예정일 : <%=calYear%>년 <%=calMonth%>월 10일
						</div>
						<div class="col-12 col-md-12 col-lg-12" style="text-align:center;">
							<div class="col-12 col-md-10 col-lg-10" style="display:inline-block; margin-top:2rem;">
								<div class="card">
									<span style="margin: 7px;">
										<b>이번 달 지급예정 급액</b>
										<a type="button" data-bs-toggle="tooltip" title="세전급여 - (기본세금 + 소득세)">
									  		<i class="bi bi-question-circle" ></i>
										</a>
									</span>
									<!-- controller에서 계산해서 받아와야지 뭐.. -->
									<span style="margin-bottom:3px;">${realSalary} 원</span>
								</div>
							</div>
						</div>
						 <div class="col-12 col-md-12 col-lg-12" style="text-align:center; margin-bottom:10rem;">
			            	<div class="col-12 col-md-3 col-lg-3" style="display:inline-block; margin:1rem">
			            		<div class="card" style="height:6rem;">
			            			<h6 style="padding:1rem; background-color:#ececec; text-align:center;">금일 기준 세전 급여
			            				<a type="button" data-bs-toggle="tooltip" title="  단순 날짜로만 계산된 금액  (금일 포함)">
									  		<i class="bi bi-question-circle" ></i>
										</a>
			            			</h6>
			            			<div style="text-align:center;">
			            				<!-- data.salary로 계약한 금액을 불러온다. -->
			            				${calSalary} 원
			            			</div>
			            		</div>
			            	</div>
			            	<div class="col-12 col-md-3 col-lg-3" style="display:inline-block;  margin:1rem">
			            		<div class="card" style="height:6rem;">
			            			<h6 style="padding:1rem; background-color:#ececec; text-align:center;">기본세금
				            			<a type="button" data-bs-toggle="tooltip" title="국민연금 + 건강보험 + 고용보험 → 8.8993%">
									  		<i class="bi bi-question-circle" ></i>
										</a>	
			            			</h6>
			            			<div style="text-align:center;">
			            				${commonTax} 원			            				 
			            			</div>
			            		</div>
			            	</div>
			            	<div class="col-12 col-md-3  col-lg-3" style="display:inline-block;  margin:1rem">
			            		<div class="card" style="height:6rem;">
			            			<h6 style="padding:1rem; background-color:#ececec; text-align:center;">소득세
			            				<a type="button" data-bs-toggle="tooltip" title="지방소득세 포함">
									  		<i class="bi bi-question-circle"></i>
										</a>
			            			</h6>
			            			<!-- 전자결재테이블 JOIN필요 -->
			            			<div style="text-align:center;">
			            				${incomeTax} 원
			            			</div>
			            		</div>
			            	</div>
			            	<table class="table table-striped mb-0">
                                <thead>
                                    <tr>
                                        <th>급여월</th>
                                        <th>지급금액</th>
                                        <th>기본세금</th>
                                        <th>소득세</th>
                                    </tr>
                                </thead>
                                <tbody>
                              		<c:forEach items="${salaryList}" var="salaryData" varStatus="status">
										<tr class="table-light">
											<td>
												${salaryData.salaryLogDt}
											</td>
											<td>
												<!-- 이거는 값 바꿔야함~~ -->
												<b>
												<fmt:formatNumber value="${salaryData.salaryLog}"/>
												</b>
											</td>
											<td>
												<fmt:formatNumber value="${salaryData.salaryTax}"/>
											</td>
											<td>
												<fmt:formatNumber value="${salaryData.incomeTax}"/>
											</td>
										</tr>
									</c:forEach>
                                </tbody>
                           </table>
						</div>
					</div>
		 <%@ include file="../module/footer.jsp" %>		
	</div> 




	<script src="static/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="static/js/bootstrap.bundle.min.js"></script>
	
    <script src="static/js/main.js"></script>
</body>

</html>