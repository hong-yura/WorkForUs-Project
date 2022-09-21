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
	<!-- 헤더 수정 후 넣기 -->
	<%@ include file="../module/head.jsp" %>
    <link rel="stylesheet" href="${staticUrl}/css/pages/board.css">
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<div id="app">
      <div id="main">
      	<div class="page-heading margin-left-10">
      		<h3>Board</h3> 
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
								<c:if test="${loginData.empId eq postData.writer}">
									<div class="btn-group remo-button-controller">
										<button type="button" class="btn dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" style="background-color : #fff; border-style : none;">
									    </button>
									    <ul class="dropdown-menu" aria-labelledby="btnGroupDrop1" style="border: solid 1px #c0c0c0; width: 40px;">
									    	<c:url value="/board/post/modify" var="modUrl"/>
									      <li><a class="dropdown-item" href="${modUrl}?postId=${postData.postId}"><i class="bi bi-pencil-square"></i> 글수정</a></li>
									      <hr style="margin: 0px;">
									      <li><a class="dropdown-item" style="cursor: pointer;" data-bs-toggle="modal" data-bs-target="#deleteModal"><i class="bi bi-trash"></i> 글삭제</a></li>
									    </ul>
									</div>
								</c:if>
								<hr>
							</div>
						</div>
						<div class="post-main">
							<p class="black">${postData.content}</p>
						</div>
						<!-- 올린 파일이 보이도록 한다. -->
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
						<div class="view-like-container">
							<i class="bi bi-eye black"> ${postData.viewCnt}</i>
							<button type="button" class="btn black" onclick=""><i class="bi bi-hand-thumbs-up"></i> ${postData.likeCnt}</button>
							<hr style="margin-top: 0px;">
						</div>
						<!-- 댓글 작성하기 -->
						<div class="mb-1">
							<c:url var="commentUrl" value="/board/comment" /> <!-- boardController에서 한꺼번에 처리하기 -->
							<form action="${commentUrl}/add" method="post" style="margin: 10px;">
								<div class="avatar avatar-md emp-img display-block" style="margin: 10px;"> <!-- 이미지 -->
									<div class="commentCnt-container black">
										<p class="comment-count">댓글 ${commentCnt}개</p>
										<div class="display-inline">
											<img class="emp-image display-inline" src="${staticUrl}/images/faces/1.jpg">
											<input type="hidden" name="postId" value="${postData.postId}">
											<div class="input-group display-inline">
												<textarea class="textarea-content" name="content" rows="3" placeholder="댓글 작성" onkeyup="this.style.height='26px'; this.style.height = this.scrollHeight + 'px';"></textarea>
										 		<button class="btn" type="submit" onclick="addComment(this, ${comment.groupNo + 1},${comment.depth } ,${ postData.postId});" >작성</button>
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
						<!-- 댓글 출력 -->
						<div class="mb-3">
							<c:forEach items="${commentList}" var="comment" varStatus="status">
								<c:if test="${comment.depth == 0}">
									<div class="mb-1"><!-- 댓글 삭제 하기 위해서는 여기까지 찾은 다음에 삭제해야 한다.  --> <!-- 댓글이 여러개 나와야 하니까 여기서부터 반복되어야 함 -->
										<div class="card comment-container">
											<div class="card-header black">
												<div class="justify-content-between">
													<div class="avatar emp-img">
														<img class="emp-image display-inline" src="${staticUrl}/images/faces/2.jpg">
													</div>
													<span><small>${comment.empNm}</small></span>
													<span><small>${comment.addDate}</small></span>
												</div>
											</div>
											<div class="card-body">
												<input type="hidden" name=commentId value="${comment.commentId}"> <!-- comment id를 숨겨두기 -> 어떤 댓글인지 알아야 해서  -->
												<p class="card-text black comment-p">${comment.content}</p>
												<c:if test="${sessionScope.loginData.empId eq comment.empId}"> <!-- 본인이 작성한 게시글에 대해서만 수정 삭제 나오도록  -->
													<div class="text-end">
														<button class="btn btn-sm btn-outline-dark" type="button" onclick="changeModify(this);">수정</button>
														<button class="btn btn-sm btn-outline-dark" type="button" onclick="commentDelete(this);">삭제</button>
													</div>
												</c:if>
											</div>
											<div class="toggle-btn-container"> 
												<i class="bi bi-caret-down-fill"></i>
												<button class="btn" type="button" onclick="showToggle(this);">댓글보기</button>
											</div>
										</div>
									</div>
									<!-- 대댓 -->
									<div class="card">
										<div class="display-inline emp-img margin-left-10">
											<img class="emp-image display-inline" src="${staticUrl}/images/faces/1.jpg">
											<input type="hidden" name="postId" id="postId" value="${postData.postId}">
											<div class="input-group display-inline">
												<textarea class="textarea-content radius" onkeyup="this.style.height='26px'; this.style.height = this.scrollHeight + 'px';" name="content" rows="3" placeholder="댓글 작성" ></textarea>
												<button class="btn" type="submit" onclick="addSecComment(this, ${comment.groupNo}, ${comment.depth}, ${postData.postId});">작성</button>
											</div>
										</div>
									</div>
									<c:forEach items="${commentList}" var="secComment">
										<c:if test="${secComment.groupNo eq comment.groupNo && secComment.depth == 1}">
											<div class="mb-1 second-comment-container">
												<div class="card second-comment-controller">
													<div class="card-header black">
														<div class="justify-content-between">
															<div class="avatar emp-img">
																<img class="emp-image display-inline" src="${staticUrl}/images/faces/2.jpg">
															</div>
															<span><small>${secComment.empNm}</small></span>
															<span><small>${secComment.addDate}</small></span>
														</div>
													</div>
													<div class="card-body">
														<input type="hidden" name=commentId value="${secComment.commentId}"> <!-- comment id를 숨겨두기 -> 어떤 댓글인지 알아야 해서  -->
														<p class="card-text black">${secComment.content}</p>
														<c:if test="${loginData.empId eq secComment.empId}"> <!-- 본인이 작성한 게시글에 대해서만 수정 삭제 나오도록  -->
															<div class="text-end">
																<button class="btn btn-sm btn-outline-dark" type="button" onclick="changeModify(this);">수정</button>
																<button class="btn btn-sm btn-outline-dark" type="button" onclick="commentDelete(this);">삭제</button>
															</div>
														</c:if>
													</div>
												</div>
											</div>
										</c:if>
									</c:forEach>
									<!-- 대댓끝 -->
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
			</section>
		</div>
		<!-- 글 삭제 모달 -->
		 <jsp:include page="/WEB-INF/views/board_md/delete_modal.jsp"/>

		<!-- footer -->
        <%@ include file="../module/footer.jsp" %>
      </div>
    </div>
    

    <script src="${staticUrl}/js/pages/board/index.js" ></script>
    <script src="${staticUrl}/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="${staticUrl}/js/bootstrap.bundle.min.js"></script>

    <script src="${staticUrl}/vendors/apexcharts/apexcharts.js"></script>
    <script src="${staticUrl}/js/pages/dashboard.js"></script>

    <script src="${staticUrl}/js/main.js"></script>
</body>
</html>