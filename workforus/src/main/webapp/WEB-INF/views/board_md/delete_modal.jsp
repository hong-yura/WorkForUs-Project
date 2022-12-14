<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
						<button type="button" class="btn btn-danger" data-bs-dismiss="modal" onclick="boardDelete(${postData.postId});">삭제</button>
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
						<c:url value="/board" var="boardUrl"/>
						<button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="location.href='${boardUrl}?bId=${postData.boardId}'">확인</button>
					</div>
				</div>
			</div>
		</div>
		