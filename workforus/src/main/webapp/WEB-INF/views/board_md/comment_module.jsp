<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
				 		<input type="text" name="groupNo" value="${groupNo}" hidden>
				 		<button class="btn" type="submit" name="depth" value="1">작성</button>
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
			<div class="allDelete"> <!-- 대댓까지 삭제하기 위해서 만든 div -->
				<div class="mb-1">
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
						<div class="card-body" style="padding-bottom:0px;">
							<input type="hidden" name=commentId value="${comment.commentId}"> <!-- comment id를 숨겨두기 -> 어떤 댓글인지 알아야 해서  -->
							<p class="card-text black comment-p">${comment.content}</p>
							<c:if test="${loginData.empId eq comment.empId}"> <!-- 본인이 작성한 게시글에 대해서만 수정 삭제 나오도록  -->
								<div class="text-end">
									<button class="btn btn-sm btn-outline-dark" type="button" onclick="changeModify(this);">수정</button>
									<button class="btn btn-sm btn-outline-dark" type="button" onclick="commentDelete1(this);">삭제</button>
									<input name="postId" value="${comment.postId}" hidden>
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
				<form action="${commentUrl}/add2" class="secComm" method="post">
					<div class="card">
						<div class="display-inline emp-img margin-left-10">
							<img class="emp-image display-inline" src="${staticUrl}/images/faces/1.jpg">
							<input type="hidden" name="postId" id="postId" value="${postData.postId}">
							<div class="input-group display-inline">
								<textarea class="textarea-content" onkeyup="this.style.height='26px'; this.style.height = this.scrollHeight + 'px';" name="content" rows="3" placeholder="댓글 작성" ></textarea>
								<input type="text" name="groupNo" value="${comment.groupNo}" hidden>
								<button class="btn" type="submit" name="depth" value="2" >작성</button>
							</div>
						</div>
					</div>
				</form>
				<div class="mb-1 second-comment-container">
					<c:forEach items="${commentList}" var="secComment">
						<c:if test="${secComment.groupNo eq comment.groupNo && secComment.depth == 1}">
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
								<div class="card-body" style="padding-bottom:0px;">
									<input type="hidden" name=commentId value="${secComment.commentId}"> <!-- comment id를 숨겨두기 -> 어떤 댓글인지 알아야 해서  -->
									<p class="card-text black">${secComment.content}</p>
									<c:if test="${loginData.empId eq secComment.empId}"> <!-- 본인이 작성한 게시글에 대해서만 수정 삭제 나오도록  -->
										<div class="text-end">
											<button class="btn btn-sm btn-outline-dark" type="button" onclick="changeModify(this);">수정</button>
											<button class="btn btn-sm btn-outline-dark" type="button" onclick="commentDelete2(this);">삭제</button>
											<input name="postId" value="${comment.postId}" hidden>
										</div>
									</c:if>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
			<!-- 대댓끝 -->
			</div>
		</c:if>
	</c:forEach>
</div>