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
	<%@ include file="../module/header.jsp" %>
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
						<div class="view-like-container">
							<i class="bi bi-eye black"> ${postData.viewCnt}</i>
							<button type="button" class="btn black" onclick=""><i class="bi bi-hand-thumbs-up"></i> ${postData.likeCnt}</button>
							<hr style="margin-top: 0px;">
						</div>
						<!-- 댓글 작성하기 -->
						<div class="mb-1">
							<c:url var="commentUrl" value="/board/comment" /> <!-- boardController에서 한꺼번에 처리하기 -->
							<form action="${commentUrl}/add" method="post" style="margin: 10px;">
								<div class="avatar avatar-md emp-img display-block"> <!-- 이미지 -->
									<div class="commentCnt-container black">
										<p class="comment-count">댓글 ${commentCnt}개</p>
										<div class="display-inline">
											<img class="emp-image display-inline" src="${staticUrl}/images/faces/1.jpg">
											<input type="hidden" name="postId" value="${postData.postId}">
											<div class="input-group display-inline">
												<textarea class="textarea-content" onkeyup="this.style.height='26px'; this.style.height = this.scrollHeight + 'px';" name="content" rows="3" placeholder="댓글 작성" ></textarea>
												<button class="btn" type="submit">작성</button>
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
												<p class="card-text black">${comment.content}</p>
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
											<input type="hidden" name="postId" value="${postData.postId}">
											<div class="input-group display-inline">
												<textarea class="textarea-content" onkeyup="this.style.height='26px'; this.style.height = this.scrollHeight + 'px';" name="content" rows="3" placeholder="댓글 작성" ></textarea>
												<button class="btn" type="submit">작성</button>
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
														<c:if test="${sessionScope.loginData.empId eq secComment.empId}"> <!-- 본인이 작성한 게시글에 대해서만 수정 삭제 나오도록  -->
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
		<div class="modal fade" tabindex="-1" id="deleteModal">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">글 삭제</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<p>해당 게시글을 삭제하시겠습니까?</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
						<button type="button" class="btn btn-danger" data-bs-dismiss="modal" onclick="boardDelete(${data.id});">삭제</button>
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
						<button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="location.href='${boardUrl}'">확인</button>
					</div>
				</div>
			</div>
		</div>
		
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