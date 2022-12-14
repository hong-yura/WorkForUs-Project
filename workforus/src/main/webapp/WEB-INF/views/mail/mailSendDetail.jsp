<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>받은메일함</title>
	
	<%@ include file="../module/head.jsp" %>

</head>
<body>
    <%@ include file="../module/navigation.jsp" %>
	<div id="main">	
		<div class="page-heading">
			<div class="page-title">
				<div class="row">
					<div class="col-12 col-md-6 order-md-1 order-last">
						<h3>보낸메일함</h3>
					</div>
					<div class="col-12 col-md-6 order-md-2 order-first">
						<nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
							<ol class="breadcrumb">
								<li class="breadcrumb-item">
									<a href="${homeUrl}">Home</a></li>
								<li class="breadcrumb-item">
									<a href="${homeUrl}/mail">Mail</a></li>
								<li class="breadcrumb-item active" aria-current="page">보낸메일함</li>
							</ol> 
						</nav>
					</div>
				</div>
				<section class="section">
					<!-- 메일쓰는버튼 -->
					<div class="row ">
					
						<div class="card col-12 col-md-2 col-lg-2 ">
	                          <!-- compose button  -->
	                       	<a href="${homeUrl}/mail/mailWrite">
	                            <button type="button" class="btn btn-primary btn-block my-4 compose-btn">
	                                메일쓰기
	                            </button>
	                       	</a>
                            <hr>
                            <div style="margin: 8px 0;">
                            	<a href="${homeUrl}/mail/mailSend">보낸메일함</a>
                            </div>
                            <div style="margin: 8px 0;">
                            	<a href="/mail/tempMail">임시저장함</a>
                            </div>
                            <div style="margin: 8px 0;">
                            	<a href="/mail/outMail">외부전송메일함</a>
                            </div>
						</div>
						
						<!-- 네비제외한 본문 -->
						<div class="card card-body col-12 col-md-9 col-lg-9">
							<c:url var="modReadUrl" value="${homeUrl}/mail/delSend">
								<c:param name="mailId" value="${receiveData	.mailId}"/>
							</c:url>
			                <div class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
				                <div class="dataTable-top">
				                </div>
				               	<a href="javascript:history.back();" class="btn btn-outline-primary">목록</a>
				                <c:if test="${tempDetail ne null}">
					               	<a href="javascript:history.back();" class="btn btn-primary">전송</a>
				                </c:if>
			                </div>
			                <!-- 메일내용 -->
			                <div class="dataTable-container">
				                <div class="card"> 
				                
				                
				                    <div class="card-content">
				                        <div class="card-body">
				                            <form class="form form-horizontal">
				                                <div class="form-body">
				                                    <div class="row">
				                                        <div >
				                                        	<c:choose>
					                                       		<c:when test="${tempDetail eq Null}">
						                                            <label><h4>${sendData.mailObj.mailTitle }</h4></label>
					                                       		</c:when>
																<c:otherwise>
						                                            <label><h4>${tempDetail.tempMailTitle}</h4></label>
																</c:otherwise>
															</c:choose>
				                                        </div>
				                                        <div >
				                                            <label>받는사람 : 
				                                            	<c:choose>
				                                            		<c:when test="${tempDetail eq Null}">
				                                            			<c:choose>
						                                            		<c:when test="${sendData.empId eq 'outMail' }">
						                                            			${sendData.mailObj.receiveEmail}
						                                            		</c:when>
						                                            		<c:otherwise>
						                                            			<b>${sendData.empObj.empNm}&lt;${sendData.empObj.empEmail}&gt;</b>
						                                            		</c:otherwise>
						                                            	</c:choose>
				                                            		</c:when>
				                                            		<c:otherwise>
				                                            			${tempDetail.receiveEmail}
				                                            		</c:otherwise>
				                                            	</c:choose>
				                                            
				                                            </label>
				                                        </div>
				                                        <div>
				                                        		<c:choose>
				                                            		<c:when test="${tempDetail eq Null}">
			                                          				    <label>보낸일자 : <fmt:formatDate value="${sendData.mailObj.mailSendTime}" type="both"/></label>
				                                            		</c:when>
				                                            		<c:otherwise>
			                                           					<label>저장일자 : <fmt:formatDate value="${tempDetail.saveTime}" type="both"/></label>
				                                            		</c:otherwise>
				                                            	</c:choose>
				                                        </div>
				                                        <div>
		                                            		<c:if test="${tempDetail eq Null}">
					                                            <label>읽은시간 : 
					                                        	<c:choose>
				                                            		<c:when test="${sendData.empId eq 'outMail' }">
				                                            			외부메일(알수없음)
							                                        </c:when>
							                                        <c:otherwise>						                                        
							                                            	<c:choose>
							                                            		<c:when test="${sendData.mailReadFl eq 'Y'}">
							                                            			<fmt:formatDate value="${sendData.mailReadTime}" type="both"/>
							                                            		</c:when>
							                                            		<c:otherwise>
							                                            			읽지않음
							                                            		</c:otherwise>
							                                            	</c:choose>
							                                        </c:otherwise>
							                                    </c:choose>
					                                            </label>
						                                    </c:if>
				                                        </div>
				                                        <br>
				                                        <div class="form-group border" style="height:auto; min-height:200px; margin-top:1rem;">
															<c:choose>
					                                       		<c:when test="${tempDetail eq Null}">
																	<c:choose> 
																	 <c:when test="${sendData.mailObj.mailContent != NULL}">
																		<p>${sendData.mailObj.mailContent}</p>
																	</c:when>
																	<c:otherwise>
																		<p>내용없음</p>
																	</c:otherwise>				
																	</c:choose>
					                                       		</c:when>
																<c:otherwise>
																	<c:choose> 
																	 <c:when test="${tempDetail.tempMailContent != NULL}">
																		<p>${tempDetail.tempMailContent}</p>
																	</c:when>
																	<c:otherwise>
																		<p>내용없음</p>
																	</c:otherwise>				
																	</c:choose>
																</c:otherwise>
															</c:choose>
				                                        </div>
			                                        	
				                                    </div>
				                                </div>
				                            </form>
				                        </div>
				                    </div>




				                </div>
			                </div>
	              	  </div>
           		 </div>
					
					
					
					
					
					
					
				
			</section>
			</div>
		</div>	
		<%@ include file="../module/footer.jsp" %>		
	</div>
	<script src="${staticUrl}/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="${staticUrl}/js/bootstrap.bundle.min.js"></script>
	
    <script src="${staticUrl}/js/main.js"></script>

</body>
</html>