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
									<a href="${homeUrl}">Home</a></li>
								<li class="breadcrumb-item">
									<a href="${homeUrl}/mail">Mail</a></li>
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
     							<c:url value="${homeUrl}/mail/mailWrite" var="mailWriteUrl"/>	
								<a href="${mailWriteUrl}">
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
                            	<a href="${homeUrl}/mail/trashMail">휴지통</a>
                            </div>
						</div>
						
						<!-- 네비제외한 본문 -->
						<div class="card card-body col-12 col-md-9 col-lg-9">
							<!-- 안읽음처리 -->
							<c:url var="modReadUrl" value="${homeUrl}/mail/modRead">
								<c:param name="mailId" value="${receiveData	.mailId}"/>
							</c:url>
							<!-- 휴지통 -->
							<c:url var="trashMail" value="${homeUrl}/mail/trash">
                           		<c:param name="mailId" value="${receiveData.mailId}"/>
                           	</c:url>
                           	<!-- 메일복구 -->
							<c:url var="recoveryMail" value="${homeUrl}/mail/recoveryMail">
                           		<c:param name="mailId" value="${receiveData.mailId}"/>
                           	</c:url>
                           	<!-- 답장 -->
							<c:url var="replyMail" value="${homeUrl}/mail/replyMail">
                           		<c:param name="email" value="${receiveData.mailSendEmail}"/>
                           		<c:param name="title" value="${receiveData.mailObj.mailTitle}"/>
                           		<c:param name="content" value="${receiveData.mailObj.mailContent}"/>                         
                         	</c:url>
			                <div class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
				                <div class="dataTable-top">
				                </div>
				                <!-- 메일함에 따라 상단에 뜨는 버튼이 다르게 함 -->
				                <c:choose>
				                	<c:when test="${receiveData.mailFolder eq '3'}">
			                		 	<a href="${homeUrl}/mail" class="btn btn-outline-primary">목록</a>
			                		 	<button class="btn btn-outline-primary" type="button" data-bs-toggle="modal" data-bs-target="#deleteModal">영구삭제</button>
   						               	<a href="${recoveryMail}"><button type="button" class="btn btn-outline-primary">복구</button></a>
				                	</c:when>
				                	<c:otherwise>
		 								<a href="${homeUrl}/mail" class="btn btn-outline-primary">목록</a>
						               	<a href="${trashMail}"><button type="button" class="btn btn-outline-primary"><i class="bi bi-trash"></i></button></a>
						               	<a href="${modReadUrl}"><button type="button" class="btn btn-outline-primary">안읽음</button></a>
				             		  	<a href="${replyMail}"><button type="button" class="btn btn-outline-primary">답장</button></a>
				                	</c:otherwise>
				                </c:choose>
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
				                                            <label>보낸 사람 : <b>${receiveData.empObj.empNm}&lt;${receiveData.mailSendEmail}&gt;</b></label>
				                                        </div>
				                                        <div>
       														
				                                            <label>보낸일자 : <fmt:formatDate value="${receiveData.mailObj.mailSendTime}" type="both"/></label>
				                                        </div>
				                                        <br>
				                                        <div class="form-group border" style="min-height:200px; height:auto; margin-top:1rem;">
															<c:choose> 
															 <c:when test="${receiveData.mailObj.mailContent != NULL}">
																<p>${receiveData.mailObj.mailContent}</p>
															</c:when>
															<c:otherwise>
																<p>내용없음</p>
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
      		<!-- 메일삭제 모달 --> 
  		<div class="modal fade" tabindex="-1" id="deleteModal">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">영구삭제</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<p>해당 메일을 영구삭제하시겠습니까?</p>
						<p>*삭제 후 복구가 불가능합니다.
					</div>
					<div class="modal-footer">
						<button type="button" class="btn" data-bs-dismiss="modal">취소</button>
						<button type="button" class="btn btn-primary ml-1" data-bs-dismiss="modal" onclick="mailDelete('${receiveData.mailId}');">삭제</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" tabindex="-1" id="resultModal">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">결과 확인</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<p>삭제되었습니다.</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="location.href='${homeUrl}/mail/trashMail'">확인</button>
					</div>
				</div>
			</div>
		</div>
			</section>
			</div>
		</div>	
		<%@ include file="../module/footer.jsp" %>		
	</div>

	
	<script type="text/javascript">
	
		function mailDelete(mailId) {
			$.ajax({
				type: "post",
				url: "${homeUrl}/mail/maildelete",
				data: {
					mailId: mailId
				},
				dataType: "json",
				success: function(data) {
					var myModal = new bootstrap.Modal(document.getElementById("resultModal"), {
						keyboard: false					
					});
					
					var title = myModal._element.querySelector(".modal-title");
					var body = myModal._element.querySelector(".modal-body");
					title.innerText = data.title;
					body.innerHTML = "<p>" + "삭제되었습니다" + "</p>";
						
					myModal.show();
				}
			})
		}
	</script>
	<script src="${staticUrl}/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="${staticUrl}/js/bootstrap.bundle.min.js"></script>
    <script src="${staticUrl}/js/main.js"></script>
</body>
</html>