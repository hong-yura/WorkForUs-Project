<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>workforus - 게시판 : ${postData.postTitle}</title>

	<%@ include file="../module/head.jsp" %> <!-- 순서는 무조건 위에  -->
    
    <link rel="stylesheet" href="${staticUrl}/css/pages/board.css">
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<div id="app">
      <div id="main">
      	<div class="page-heading">
			<div class="page-title">
				<div class="row">
					<div class="col-12 col-md-6 order-md-1 order-last">
						<h3>Board</h3>
					</div>
					<div class="col-12 col-md-6 order-md-2 order-first">
						<nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
							<ol class="breadcrumb">
								<li class="breadcrumb-item">
									<a href="views/home.jsp">home</a></li> <!-- curl로 수정 -->
								<li class="breadcrumb-item active" aria-current="page">Board</li>
								<li class="breadcrumb-item active" aria-current="page">상세페이지</li>
							</ol> 
						</nav>
					</div>
				</div>
			</div>
		</div>
	      	<!-- board -->
      	<div class="page-content">
			<section class="row">
				<div class="col-12"> 
					<div class="post-container radius">
						<div class="post-top">
							<div class="avatar avatar-md emp-img"> <!-- 이미지 -->
								<img class="emp-image" src="${staticUrl}/images/faces/1.jpg">
								<p class="post-writer">${postData.empObj.empNm}</p>
								<p class="add-date">${postData.addDate}</p>
							</div>
							<div class="title-container">
								<h4 class="post-title black">${postData.postTitle}</h4>
								<c:if test="${loginId eq postData.writer}">
									<div class="btn-group remo-button-controller">
										<button type="button" class="btn dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" style="background-color : #fff; border-style : none;">
									    </button>
									    <ul class="dropdown-menu" aria-labelledby="btnGroupDrop1" style="border: solid 1px #c0c0c0; width: 40px;">
									    	<c:url value="/board/post/modify" var="modUrl"/>
									      <li><a class="dropdown-item" href="${modUrl}?postId=${postData.postId}"><i class="bi bi-pencil-square"></i> 글수정</a></li>
									      <hr style="margin: 0px;">
									      <li><a class="dropdown-item" style="cursor: pointer;" data-bs-toggle="modal" data-bs-target="#deleteModal" onclick="deletePost(this)"><i class="bi bi-trash"></i> 글삭제</a></li>
									    </ul>
									</div>
								</c:if>
								<hr>
							</div>
						</div>
						<div class="post-main"  style="color: black; min-height: 200px;">
							<p class="post-content">${postData.content}</p>
						</div>
						<!-- 올린 파일이 보이도록 한다. -->
						<c:if test="${not empty files}">
							<div class="dropdown">
							 	<button class="btn dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
							    	파일 보기
							  	</button>
							  	<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
							  		<c:if test="${not empty files}">
										<c:forEach items="${files}" var="file"> 
										    <li><a class="dropdown-item link-secondary" href="${file.uploadUrl}" download="${file.fileNm}"><i class="bi bi-paperclip"></i>${file.fileNm}</a></li>
							  			</c:forEach>
							  		</c:if>
							  	</ul>
							</div>
						</c:if>
						<div class="view-like-container">
							<i class="bi bi-eye black"> ${postData.viewCnt}</i>
							<button type="button" class="btn black" onclick="incLike(id_like, ${postData.postId}, ${postData.likeCnt });"><i class="bi bi-hand-thumbs-up" id="id_like">${postData.likeCnt}</i>
							</button>
							<hr style="margin-top: 0px;">
						</div>
						<input value="n" id="like-yn" hidden>
						<!-- 댓글 모듈 -->
						 <%@ include file="../board_md/comment_module.jsp" %>
					</div>
				</div>
			</section> 
		</div>
		<!-- 글 삭제 모달 -->
		  <%@ include file="../board_md/delete_modal.jsp" %>

		<!-- footer -->
        <%@ include file="../module/footer.jsp" %>
      </div>
    </div>
    
    
    <script src="${staticUrl}/js/pages/board/index.js"></script>
    
    <script src="${staticUrl}/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="${staticUrl}/js/bootstrap.bundle.min.js"></script>

    <script src="${staticUrl}/vendors/apexcharts/apexcharts.js"></script>
    <script src="${staticUrl}/js/pages/dashboard.js"></script>

    <script src="${staticUrl}/js/main.js"></script>
</body>
</html>