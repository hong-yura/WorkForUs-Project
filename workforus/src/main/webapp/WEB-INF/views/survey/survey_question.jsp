<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>workforus - 설문조사 : ${surveyData.surveyTitle }</title>
	<%@ include file="../module/head.jsp" %>
	<link rel="stylesheet" href="${staticUrl}/css/pages/survey.css">
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	 <div id="app">
    	<div id="main">
    	
      		<form action="/survey/response" method="post">
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
											<a href="${homeUrl}/home.jsp">Home</a></li> <!-- curl로 수정 -->
										<li class="breadcrumb-item active" aria-current="page">survey</li>
										<li class="breadcrumb-item active" aria-current="page">${surveyData.surveyTitle }</li>
									</ol> 
								</nav>
							</div>
						</div>
					</div>
				</div>
				<section class="section">
					<div class="row">
						<div class="col-12">
							<div class="card">
								<div class="card">
		                    		<div class="card-header">
		                                 <h4>설문 조사</h4>
		                                 <p>${surveyData.surveyTitle }</p>
		                             </div>
	                                <div class="card-body">
	                                    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
	                                        <div class="carousel-inner" style="margin: auto; width: 50%; margin-top: 50px;">
	                                        	<div class="carousel-item active start-page" style="text-align : center;" >
	                                        		<h3>설문조사를 시작합니다.</h3>
	                                        	</div>
												<c:if test="${not empty questionDatas}">
													<c:forEach items="${questionDatas }" var="question" varStatus="status">
														<!-- 주관식 -->
														<c:if test="${question.typeNo eq 1}"> 
															<div class="carousel-item start-page" style="text-align : center;">
																<div class="question-content-container">
				                                            		<h6 class="question-content">Q${status.count}. ${question.quesContent}</h6>
				                                            	</div>
				                                            	<div class="response-container">
				                                            		<textarea class="form-control" rows="10" style="resize: none;" placeholder="답변을 입력해주세요."></textarea>
				                                            	</div>
															</div>
														</c:if>
														<!-- 객관식 -->
														<c:if test="${question.typeNo eq 2}"> 
				                                            <div class="carousel-item start-page">
				                                            	<div class="question-content-container" style="text-align : center; margin-bottom:  50px;">
				                                            		<h6 class="question-content">Q${status.count}. ${question.quesContent}</h6>
				                                            	</div>
						                                        <div class="question-distractor-container">
					                                            	<c:if test="${not empty distractorData}">
					                                            		<c:forEach items="${distractorData}" var="distractor">
					                                            			<c:if test="${distractor.quesNo eq question.quesNo}">
							                                            		<div class="form-check" style="margin:15px; text-align : center;">
							                                            			<div class="checkbox">
							                                            				<input type="checkbox" id="checkbox" class="form-check-input">
							                                            				<label for="checkbox">${distractor.distractorContent}</label>
							                                            			</div>
							                                            		</div>
					                                            			</c:if>
						                                            	</c:forEach>
					                                            	</c:if>
						                                        </div>
				                                            </div>
														</c:if>
														<!-- 날짜 -->
														<c:if test="${question.typeNo eq 3}"> 
															<div class="carousel-item start-page">
																<div class="question-content-container" style="text-align : center;">
				                                            		<h6 class="question-content">Q${status.count}. ${question.quesContent}</h6>
				                                            	</div>
				                                            	<div class="response-container">
				                                            		<input type="datetime-local" class="form-control border-dark">
				                                            	</div>
															</div>
														</c:if>
		                                        	</c:forEach>
												</c:if>
	                                        </div>
	                                        <a class="carousel-control-prev" href="#carouselExampleControls" role="button"
	                                            data-bs-slide="prev">
	                                            <span class="bi bi-chevron-left" aria-hidden="true" style="color: black;"></span>
	                                            <span class="visually-hidden">Previous</span>
	                                        </a>
	                                        <a class="carousel-control-next" href="#carouselExampleControls" role="button"
	                                            data-bs-slide="next">
	                                            <span class="bi bi-chevron-right" aria-hidden="true" style="color: black;"></span>
	                                            <span class="visually-hidden">Next</span>
	                                        </a>
	                                       
	                                    </div>
										<div class="text-end">
											<button class="btn btn-sm btn-outline-dark" type="submit">제출</button>
											<button class="btn btn-sm btn-outline-dark" type="button" onclick="commentDelete2(this);">목록</button>
											<input name="postId" value="${comment.postId}" hidden>
										</div>
	                                </div>
	                            </div>
							</div>
						</div>
					</div>
				</section>
      		</form>
		</div>
	</div>
</body>
</html>