<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>workforus - 설문 : ${surveyData.surveyTitle}</title>
	<%@ include file="../module/head.jsp" %>
	<link rel="stylesheet" href="${staticUrl}/css/pages/survey.css">
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	
    <div id="app">
    	<div id="main">

	    	<div class="page-heading">
				<div class="page-title">
					<div class="row">
						<div class="col-12 col-md-6 order-md-1 order-last">
							<h3>survey</h3>
						</div>
						<div class="col-12 col-md-6 order-md-2 order-first">
							<nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
								<ol class="breadcrumb">
									<li class="breadcrumb-item">
										<a href="views/home.jsp">Home</a></li> <!-- curl로 수정 -->
									<li class="breadcrumb-item active" aria-current="page">survey</li>
								</ol> 
							</nav>
						</div>
					</div>
				</div>
			</div>
			
			<c:url value="/survey/question" var="questionUrl"/>
      		<div class="page-content">
				<section class="row">
					<div class="col-12"> 
						<div class="section-main radius">
							<table class="table">
								<colgroup>
									<col width="10%">
								 	<col width="20%">
						            <col width="70%">
						        </colgroup>
						        <tbody>
									<tr>
										<th></th>
										<th>조사명</th>
										<th>${surveyData.surveyTitle}</th>
									</tr>
									<tr>
										<th></th>
										<th>질문자</th>
										<th>${surveyData.empId}</th>
									</tr>
									<tr>
										<th></th>
										<th>소요시간</th>
										<th>${takeTime} 분</th>
									</tr>
									<tr>
										<th></th>
										<th>조사기간</th>
										<th>${surveyData.startDt} ~ ${surveyData.endDt}</th>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</section>
				<div class="row">
					<div class="col-12">
						<div class="btn-group">
							<button class="btn btn-outline-dark" style="border-color: #c0c0c0;" type="submit" onclick="location.href='${questionUrl}?surveyNo=${surveyData.surveyNo}'">참여하기</button>
						</div>
						<div class="btn-group">
							<button class="btn btn-outline-dark" style="border-color: #c0c0c0" type="button">목록</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>