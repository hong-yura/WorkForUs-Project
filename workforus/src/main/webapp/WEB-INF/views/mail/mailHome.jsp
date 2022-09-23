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
	<title>메일함</title>
	
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
				                <table class="table dataTable-table" id="table1">
				                    <thead>
				                        <tr>
				                        	<th	class="td1">
				                        		<input type="checkbox" class="form-check-input form-check-primary form-check-glow" id="cboxAll" name="cboxAll" onclick="selectAll()">
			                        		</th>
			                        		<th data-sortable="" style="width: 6.8322%;">
			                        			<a href="#" class="dataTable-sorter"></a>	<!-- 중요도 -->
			                        		</th>
			                        		<th data-sortable="" style="width: 5.9593%;">
			                        			<a href="#" class="dataTable-sorter"></a> <!-- 읽은여부 -->
			                        		</th>
				                        	<th data-sortable="" style="width: 29.7041%;">
				                        		<a href="#" class="dataTable-sorter">보낸사람</a>
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
				                    	<c:forEach items="${dataList}" var="MailData" varStatus="status">
					                    	<c:url var="detailUrl" value="/mail/detail">
												<c:param name="mailId" value="${MailData.mailId}"/>
											</c:url>
											<c:choose>
												<c:when  test="${MailData.mailReadFl eq 'N'}"> 
													<tr class="table-light">
												</c:when>
												<c:otherwise>
													<tr style="background-color:#F2F2F2">
												</c:otherwise>
											</c:choose>
											
												<td><input type="checkbox" class="form-check-input form-check-primary form-check-glow" name="cbox" id="mailList" onclick="checkSelectAll()"></td>
												<td onclick="location.href='${detailUrl}'">
					                    			<i class="bi bi-star"></i>	<!-- 중요도 -->
					                    		</td>
					                    		<td onclick="location.href='${detailUrl}'">
					                    			<c:choose>	<%-- 안읽은 모양 (readFl이 가져오도록 변경해야할듯)--%>
					                    				<c:when  test="${MailData.mailReadFl eq 'N'}">
						                    				<i class="bi bi-envelope"></i>
					                    				</c:when>
					                    				<c:otherwise>	<%-- 읽은 모양 --%>
							                    			<i class="bi bi-envelope-open"></i> 
					                    				</c:otherwise>
					                    			</c:choose>
					                    		</td>
												<td onclick="location.href='${detailUrl}'">
													${MailData.mailSendEmail}
												</td>
												<td onclick="location.href='${detailUrl}'">
													${MailData.mailObj.mailTitle}
												</td>
												<td onclick="location.href='${detailUrl}'">
													<fmt:formatDate value="${MailData.mailObj.mailSendTime}" type="date" pattern="YYYY-MM-dd"/>
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
	
    <script src="static/js/main.js"></script>
   	<script type="text/javascript">
		function selectAll() {
			if(document.getElementById("cboxAll").checked==true){  //id 를 사용하여 하나의 객체만을 호출
		         for(var i=0;i<3;i++) document.getElementsByName("cbox")[i].checked=true;   //name 을 사용하여 배열 형태로 담아 호출
		      }
		    if(document.getElementById("cboxAll").checked==false){
		         for(var i=0;i<3;i++) document.getElementsByName("cbox")[i].checked=false;  
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