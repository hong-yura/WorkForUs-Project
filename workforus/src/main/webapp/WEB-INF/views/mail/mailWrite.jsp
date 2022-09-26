<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<%@ include file="../module/head.jsp" %>
 	<c:url value="${staticUrl}/vendors/ckeditor" var="ckedit"/>
	<script type="text/javascript" src="${ckedit}/ckeditor.js"></script>
 	
 	<!-- css폴더로 이동할예정 -->
 	<style>
	.ck.ck-editor {
    	max-width: 100%;
	}
	.ck-editor__editable {
	    min-height: 300px;
	}
	</style>
</head>
<body>
    <%@ include file="../module/navigation.jsp" %>
	<div id="app">
		<div id="main">
			<div class="page-heading">
			    <div class="page-title">
			        <div class="row">
						<div class="col-12 col-md-6 order-md-1 order-last">
			                <h3>메일작성</h3>
			            </div>
			            <div class="col-12 col-md-6 order-md-2 order-first">
			                <nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
			                    <ol class="breadcrumb">
			                        <li class="breadcrumb-item">
										<a href="${homeUrl}">Home</a>
		                        	</li>
			                        <li class="breadcrumb-item">
										<a href="${homeUrl}/mail">Mail</a>
		                        	</li>
			                        <li class="breadcrumb-item active" aria-current="page">메일작성</li>
			                    </ol>
			                </nav>
			            </div>
			        </div>
			    </div>
		    </div>
		    <!-- 카드 부분 -->
		    <div class="col-md-12 col-12">
                <div class="card">
                    <div class="card-header">
                    </div>
                    <div class="card-content">
                        <div class="card-body">
                   	     	<c:url value="/mail/mailWrite" var="mailWriteUrl"/>
                            <form class="form form-horizontal" action="${mailWriteUrl}" name="send" method="post" enctype="multipart/form-data" >
                                <div class="form-body">
                                    <div class="row">
                                        <div class="col-md-2 col-lg-2">
                                            <label>받는사람</label>
                                        </div>
                                        <div class="col-md-10 form-group col-lg-10">
                                        	<c:choose>
	                                        	<c:when test="${email eq NULL }">
		                                            <input type="email" id="receive-email" class="form-control"  name="receiveEmail"
		                                            	   value="${param.receiveEmail}"	placeholder="@workforus.site" required >
	                                        	</c:when>
	                                        	<c:otherwise>
		                                            <input type="email" id="receive-email" class="form-control"  name="receiveEmail"
		                                            	   value="${email}"	placeholder="@workforus.site" required >
	                                        	</c:otherwise>
                                        	</c:choose>
                                        </div>
                                        <div class="col-md-2 col-lg-2" >
                                            <label>제목</label>
                                        </div>
                                        <div class="col-md-10 form-group col-lg-10">
                                            <input type="text" id="title" value="${param.mailTitle}" name="mailTitle" class="form-control" placeholder="제목을 입력하세요." required>
                                        </div>
                                        <div class="col-md-2 col-lg-2" >
                                            <label>첨부파일</label>
                                        </div>
                                        <div class="col-md-10 form-group col-lg-10">
                                            <input type="file" id="email-id" class="form-control" placeholder="제목을 입력하세요.">
                                        </div>                                       
                                       <div class="mb-3" >
                                       		<c:choose>
	                                        	<c:when test="${email eq NULL }">
													<textarea class="form-control" id="content" name="mailContent" 
												 		 rows="5" placeholder="내용을 입력하세요.">${param.mailContent}</textarea>
	                                        	</c:when>
	                                        	<c:otherwise>
													<textarea class="form-control" id="content" name="mailContent" 
												 		 rows="5" placeholder="내용을 입력하세요.">
												 	<br><br><br>=====================REPLY=====================<br>
												 	제목 : ${title} <br>
												 	보낸이 : ${email} <br>
												 	내용 : ${content}
												 	</textarea>
	                                        	</c:otherwise>
                                        	</c:choose>
										</div>
                                        <div class="col-sm-12 d-flex justify-content-end">
                                            <button type="submit" class="btn btn-primary me-1 mb-1">전송</button>
                                            <input type="submit" class="btn btn-light-secondary me-1 mb-1" value="임시저장" formaction="/mail/saveTemp">
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
		<%@ include file="../module/footer.jsp" %>		
		</div>
	</div>
	<script src="${staticUrl}/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script src="${staticUrl}/js/bootstrap.bundle.min.js"></script>
	
    <script src="${staticUrl}/js/main.js"></script>
	<script type="text/javascript">
        ClassicEditor
            .create( document.querySelector( '#content' ), {
                language: 'ko' //언어설정
            })
            .catch( error => {
                console.error( error );
            } );
        
        
    </script>
</body>
</html>