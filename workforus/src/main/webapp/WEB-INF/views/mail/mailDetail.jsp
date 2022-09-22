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
						<h3>받은메일함</h3>
					</div>
					<div class="col-12 col-md-6 order-md-2 order-first">
						<nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
							<ol class="breadcrumb">
								<li class="breadcrumb-item">
									<a href="#">Home</a></li>
								<li class="breadcrumb-item">
									<a href="#">Mail</a></li>
								<li class="breadcrumb-item active" aria-current="page">받은메일함</li>
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
                            	<a href="${homeUrl}/mail">받은메일함</a>
                            </div>
                            <div style="margin: 8px 0;">
                            	<a href="${homeUrl}/mail/spam">스팸메일함</a>
                            </div>
                            <div style="margin: 8px 0;">
                            	<a href="${homeUrl}/mail/delMail">휴지통</a>
                            </div>
						</div>
						
						<!-- 네비제외한 본문 -->
						<div class="card card-body col-12 col-md-9 col-lg-9">
			                <div class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
				                <div class="dataTable-top">
				                </div>
				               	<a href="${homeUrl}/mail" class="btn btn-outline-primary">목록</a>
				               	<button type="button" class="btn btn-outline-primary"><i class="bi bi-trash"></i></button>
				               	<button type="button" class="btn btn-outline-primary">안읽음</button>
				               	<button type="button" class="btn btn-outline-primary">답장</button>
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
				                                            <label><h4>${receiveData.mailObj.mailTitle }</h4></label>
				                                        </div>
				                                        <div >
				                                            <label>보낸 사람 : <b>${receiveData.empObj.empNm}&lt;${receiveData.empObj.empEmail}&gt;</b></label>
				                                        </div>
				                                        <div>
       														
				                                            <label>보낸일자 : <fmt:formatDate value="${receiveData.mailObj.mailSendTime}" type="both"/></label>
				                                        </div>
				                                        <br>
				                                        <div class="form-group border" style="height:10rem; margin-top:1rem;">
															<p>${receiveData.mailObj.mailContent}</p>				
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