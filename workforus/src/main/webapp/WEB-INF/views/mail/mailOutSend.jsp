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
                            <div style="margin: 8px 0;">
                            	<a href="/mail/outMail">외부전송메일함</a>
                            </div>
						</div>
						<div class="card card-body col-12 col-md-9 col-lg-9">
			                <div class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
				                <div class="dataTable-top">
				                </div>
				                <ul class="list-inline m-0 d-flex">
				                </ul>
				                <!-- 검색기능 
				                <div class="dataTable-search">
					               	 <input class="dataTable-input" placeholder="Search..." type="text">
				                </div>
				                 -->
   				               	<button type="button" class="btn btn-outline-primary"><i class="bi bi-trash"></i></button>
				                 
			                </div>
			                <div class="dataTable-container">
				                <table class="table table-striped dataTable-table" id="table1">
				                    <thead>
				                        <tr>
				                        	<th	class="td1">
							            		<input type="checkbox" class="form-check-input form-check-primary form-check-glow" id="cboxAll" name="cboxAll" onclick="selectAll()">
				                        	</th>
				                        	<th data-sortable="" style="width: 22.7041%;">
				                        		<a href="#" class="dataTable-sorter">수신외부이메일</a>
				                        	</th>
				                        	<th data-sortable="" style="width: 44.9215%;">
				                        		<a href="#" class="dataTable-sorter">제목</a>
				                        	</th>
				                        	<th data-sortable="" style="width: 17.1082%;">
				                        		<a href="#" class="dataTable-sorter">전송일자</a>
			                        		</th>
			                        		<th data-sortable="" style="width: 10.9593%;">상세정보</th>
			                        	</tr>
				                    </thead>
				                    <tbody>
				                    	
				                    	<c:forEach items="${dataList}" var="MailData" varStatus="status">
					                    	<c:url var="detailUrl" value="${homeUrl}/mail/sendDetail">
												<c:param name="mailId" value="${MailData.mailId}"/>
											</c:url>
											<tr class="table-light">
												<td><input type="checkbox" class="form-check-input form-check-primary form-check-glow" name="cbox" id="mailList" onclick="checkSelectAll()"></td>
												<td>
													${MailData.mailObj.receiveEmail}
												</td>
												<td>
													${MailData.mailObj.mailTitle}
												</td>
												<td>
													<fmt:formatDate value="${MailData.mailObj.mailSendTime}" type="date" pattern="YYYY-MM-dd"/>
												</td> 
					                    		<td>
               		   				               	<button type="submit" class="btn btn-outline-primary"  onclick="location.href='${detailUrl}'">조회</button>
					                    		</td>
											</tr>
		
										</c:forEach>	
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
   	<script type="text/javascript">
		function selectAll() {
			if(document.getElementById("cboxAll").checked==true){  //id 를 사용하여 하나의 객체만을 호출
		         for(var i=0;i<20;i++) document.getElementsByName("cbox")[i].checked=true;   //name 을 사용하여 배열 형태로 담아 호출
		      }
		    if(document.getElementById("cboxAll").checked==false){
		         for(var i=0;i<20;i++) document.getElementsByName("cbox")[i].checked=false;  
		    }
		}
		
		function checkSelectAll()  {
			  // 전체 체크박스
			  const checkboxes 
			    = document.querySelectorAll('input[name="cbox"]');
			  // 선택된 체크박스
			  const checked 
			    = document.querySelectorAll('input[name="cbox"]:checked');
			  // select all 체크박스
			  const selectAll 
			    = document.querySelector('input[name="cboxAll"]');
			  
			  if(checkboxes.length === checked.length)  {
			    selectAll.checked = true;
			  }else {
			    selectAll.checked = false;
			  }
		}
		
	</script>    
</body>
</html>