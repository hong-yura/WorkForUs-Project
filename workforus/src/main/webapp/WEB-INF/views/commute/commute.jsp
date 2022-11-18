<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>근태관리 근태페이지</title>
	<!-- datepicker -->
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	<%@ include file="../module/head.jsp" %>
	<script src="${staticUrl}/js/pages/commute/commute.js"></script>
</head>
<body class="theme-light" style="overflow-y: auto;">
    <%@ include file="../module/navigation.jsp" %>
	<div class="ps__rail-x" style="left: 0px; bottom: -341px;">
		<div class="ps__thumb-x" tabindex="0" style="left: 0px; width: 0px;"></div>
	</div>
	<div class="ps__rail-y" style="top: 341px; height: 569px; right: 0px;">
		<div class="ps__thumb-y" tabindex="0" style="top: 82px; height: 137px;"></div>
	</div>
    <div id="main">
		<div class="page-heading">
		    <div class="page-title">
		        <div class="row">
					<div class="col-12 col-md-6 order-md-1 order-last">
		                <h3>근태관리</h3>
		            </div>
		            <div class="col-12 col-md-6 order-md-2 order-first">
		                <nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
		                    <ol class="breadcrumb">
		                        <li class="breadcrumb-item">
									<a href="${homeUrl}">Home</a>
	                        	</li>
		                        <li class="breadcrumb-item">
									<a href="${homeUrl}/info">My page</a>
	                        	</li>
		                        <li class="breadcrumb-item active" aria-current="page">근태관리</li>
		                    </ol>
		                </nav>
		            </div>
		             
		        </div>
		    </div>
		    <section class="section">
		        <div class="row">
		        	<!-- sidebar	 -->
		            <div class="col-12 col-md-3 col-lg-3">
		                <div class="card">
		                    <div class="card-header">
		                   	    <% 
							    	Date date = new Date();
							    	request.setAttribute("date", date);
							    %>
		                        <h6>
								    <fmt:formatDate value="${date}" type="date" pattern="YYYY-MM-dd(E)"/>
		                        </h6>
		                    </div>
		                    <div class="card-body">
		                    	<div>
						   			<span class="col-12 col-md-6 col-lg-6" style="display:inline-block">
								   		출근시간
						   			</span>
						   			<span class="col-12 col-md-55 col-lg-5"  style="display:inline-block; text-align:right;">
										<c:choose>
								    		<c:when test="${empty data.commuteTime}">
								    			-
								    		</c:when>
								    		<c:otherwise>
								    			<div>${data.commuteTime }</div>
								    		</c:otherwise>
								    	</c:choose>
						   			</span>
						   		</div>
		                    	<div>
						   			<span class="col-12 col-md-6 col-lg-6" style="display:inline-block">
								   		퇴근시간
						   			</span>
						   			<span class="col-12 col-md-55 col-lg-5"  style="display:inline-block; text-align:right;">	
										<c:choose>
								    		<c:when test="${empty data.getoffTime} ">
								    			-
								    		</c:when>
								    		<c:otherwise>
								    			<div>${data.getoffTime}</div>
								    		</c:otherwise>
								    	</c:choose>
						   			</span>
						   		</div>
		                    	<div>
						   			<span class="col-12 col-md-6 col-lg-6" style="display:inline-block">
								   		금일근로시간
						   			</span>
						   			<span class="col-12 col-md-55 col-lg-5"  style="display:inline-block; text-align:right;">
										<c:choose>
								    		<c:when test="${empty data.getoffTime}">
								    			-
								    		</c:when>
								    		<c:otherwise>
								    			<div>${data.workTime}</div>
								    		</c:otherwise>
								    	</c:choose>
						   			</span>
						   		</div>
		                        <div class="badges border-bottom" style="text-align:center; padding:15px 0;">
		                            <button type="button" class="btn btn-primary col-12 col-md-5 col-lg-5" onclick="commuteIn();">
		                                출근
		                            </button>
		                           <button type="button" class="btn btn-outline-primary col-12 col-md-5 col-lg-5" onclick="commuteOut()">
		                           		퇴근
		                           </button>
		                        </div>
		                        <div>
		                        	<div style="padding-top:10px;"><h6>이번주 근로현황</h6></div>
		                        	<div class="progress progress-primary  mb-4" style="margin-top:20px;" >
					                    <div class="progress-bar" role="progressbar" style="width: ${progress}%" aria-valuemin="0" aria-valuemax="100">
					                    	<c:choose>
					                    		<c:when test="${progress > 100}">
								                    100%
					                    		</c:when>
					                    		<c:otherwise>
								                    ${progress}%
					                    		</c:otherwise>
					                    	</c:choose>
					                    </div>
					                </div>
					                <div>
						    			 → ${weekWorktime }
					                </div>
		                        </div>
		                    </div>
		                </div>
		            </div>
		            <!-- 화면중앙 -->
		            <div class="col-12 col-md-9 col-lg-9" style="text-align:center;">
		            	<div class="col-12 col-md-3 col-lg-3" style="display:inline-block; margin:1rem">
		            		<div class="card" style="height:6rem;">
		            			<h6 style="padding:1rem; text-align:center;">연장근로신청</h6>
		            			<p style="text-align:center;"><i class="bi bi-alarm"></i></p>
		            		</div>
		            	</div>
		            	<div class="col-12 col-md-3 col-lg-3" style="display:inline-block;  margin:1rem">
		            		<div class="card" style="height:6rem;">
		            			<h6 style="padding:1rem; text-align:center;">남은연차</h6>
		            			<!-- 현재남은연차개수 / 총연차개수 -->
		            			<div style="text-align:center;">
		            				0/0
		            			</div>
		            		</div>
		            	</div>
		            	<div class="col-12 col-md-3  col-lg-3" style="display:inline-block;  margin:1rem">
		            		<div class="card" style="height:6rem;">
		            			<h6 style="padding:1rem; text-align:center;">근로관련결제대기</h6>
		            			<!-- 전자결재테이블 JOIN필요 -->
		            			<div style="text-align:center;">
		            				0 건
		            			</div>
		            		</div>
		            	</div>

		            	<div class="col">
		            			
		            			<div class="card">
		            				<div class="list-group list-group-horizontal-sm mb-1 text-center" role="tablist">
                              	 		 <span class="list-group-item-action" style="padding:1rem" id="list-sunday-list" data-bs-toggle="list">
                              	 		 	이번주 누적시간 <br><br>
                              	 		 	<c:choose>
                              	 		 		<c:when test="${empty weekWorktime}"> 
                              	 		 			<h5>00:00:00</h5>
                              	 		 		</c:when>
                              	 		 		<c:otherwise>
                             	 		 			<h5>${weekWorktime}</h5>
                              	 		 		</c:otherwise>
                              	 		 	</c:choose>
                              	 		 </span>                              	 		
                              	 		 <span class="list-group-item-action" style="padding:1rem" id="list-sunday-list" data-bs-toggle="list">
                              	 		 	이번주 연장시간 <br><br>
                              	 		 	<c:choose>
                              	 		 		<c:when test="${empty weekAddtime}"> 
                              	 		 			<h5>00:00:00</h5>
                              	 		 		</c:when>
                              	 		 		<c:otherwise>
                              	 		 			<h5>${weekAddtime}</h5>
                              	 		 		</c:otherwise>
                              	 		 	</c:choose>
                              	 		 </span>                              	 		
                              	 		 <span class="list-group-item-action" style="padding:1rem" id="list-sunday-list" data-bs-toggle="list">
                              	 		 	이번주 잔여시간 <br><br>
                        	 		 		<h5>${remainTime}</h5>
                              	 		 </span>                	 		
                          			  </div>
		            			</div>

			            	<div class="col-12" style="text-align:center; height:4rem;">
			            		<ul class="pagination pagination-primary justify-content-center">
		            				<%
		            					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
	
		            					Calendar cal = Calendar.getInstance();
		            					cal.getTime();
		            					int month1 = cal.get(Calendar.MONTH) + 1;
		            				%>
		            				<li style="margin-top: 0.3rem;  margin-right: 1rem;" onclick="loadPrevNext(0, this);">
			            				<i class="bi bi-caret-left" style="cursor:pointer;" ></i>
		            				</li>
		            				<li id="result">
			            				<h4 id="CalendaryearMonth" onchange="getList(this);"> 
			            					<span id="year1"><%= cal.get(Calendar.YEAR) %></span>.<span id="month1"><%= month1 %></span>
		  	            				</h4>
		            				</li>
		            				<li style="margin-top: 0.3rem;  margin-left: 1rem;" onclick="loadPrevNext(1, this);">
			            				<i class="bi bi-caret-right" style="cursor:pointer;"></i>
			            				
		            				</li>
		            				
		            				
	                            </ul>
			            	</div>   
                         	<!-- 이번달 근무표 -->	
                         	<%@include file="./commuteList.jsp" %>
						</div>
		            </div>
		        </div>
		        <%@ include file="../module/footer.jsp" %>
		    </section>
		</div>
    </div>
	<script src="static/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="static/js/bootstrap.bundle.min.js"></script>

    <script src="${staticUrl}/js/main.js"></script>
</body>
</html>