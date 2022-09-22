<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>보낸 메일함</title>
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
						</div>
						<div class="card card-body col-12 col-md-9 col-lg-9">
			                <div class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
				                <div class="dataTable-top">
				                </div>
				                <ul class="list-inline m-0 d-flex">
				                	<li style="margin:3px;">
				                		<a href="#" class="btn btn-outline-primary"><i class="bi bi-star"></i></a>
				                	</li>
				                	<li style="margin:3px;">
				                		<a href="#" class="btn btn-outline-primary"><i class="bi bi-trash"></i></a>
				                	</li>
				                </ul>
				                <!-- 검색기능 
				                <div class="dataTable-search">
					               	 <input class="dataTable-input" placeholder="Search..." type="text">
				                </div>
				                 -->
			                </div>
			                <div class="dataTable-container">
				                <table class="table table-striped dataTable-table" id="table1">
				                    <thead>
				                        <tr>
				                        	<th	class="td1"><input type="checkbox"></th>
			                        		<th data-sortable="" style="width: 5.9593%;">
			                        			<a href="#" class="dataTable-sorter"></a> <!-- 읽은여부 -->
			                        		</th>
				                        	<th data-sortable="" style="width: 29.7041%;">
				                        		<a href="#" class="dataTable-sorter">받는사람</a>
				                        	</th>
				                        	<th data-sortable="" style="width: 40.9215%;">
				                        		<a href="#" class="dataTable-sorter">제목</a>
				                        	</th>
				                        	<th data-sortable="" style="width: 20.1082%;">
				                        		<a href="#" class="dataTable-sorter">전송일자</a>
			                        		</th>
			                        	</tr>
				                    </thead>
				                    <tbody>
				                    	<tr>
				                    		<td><input type="checkbox"></td>
				                    		<td>
				                    			<i class="bi bi-envelope"></i><!-- <i class="bi bi-envelope-open"></i> 읽은거 -->
				                    		</td>
				                    		<td>
				                    			jugi123@workforus.site
				                    		</td>
				                    		<td>
				                    			이번달 식단입니다.
				                    		</td>
				                    		<td>
				                    			2022-07-18
				                    		</td>
				                    	</tr>
				                    </tbody>
				                </table>
			                </div>
			                <div class="dataTable-bottom">
				                <nav class="dataTable-pagination">
					                <ul class="dataTable-pagination-list pagination pagination-primary">
						                <li class="pager page-item"><a href="#" data-page="1" class="page-link">‹</a></li>
						                <li class="active page-item"><a href="#" data-page="1" class="page-link">1</a></li>
						                <li class="page-item"><a href="#" data-page="2" class="page-link">2</a></li>
						                <li class="page-item"><a href="#" data-page="3" class="page-link">3</a></li>
						                <li class="pager page-item"><a href="#" data-page="2" class="page-link">›</a></li>
					                </ul>
				                </nav>
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