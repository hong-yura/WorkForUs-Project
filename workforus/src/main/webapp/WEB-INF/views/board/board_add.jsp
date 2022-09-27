<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  <!-- 게시판 생성 modal -->
  <form method="post" action="${boardUrl}/add">
    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	  	<div class="modal-dialog modal-xl">
		    <div class="modal-content">
			    <div class="modal-header">
				    <h5 class="modal-title" id="staticBackdropLabel">게시판 생성</h5>
				    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			    </div>
			    <div class="modal-body">
			    	<div class="profile-icon">
			        	<i class="bi bi-person-circle"></i>
			        </div>
			        <div class="info">
			        	<div class="info-detail">
			        		<input hidden name="currentBoardId" value="${boardData.boardId}">
				        	<label>게시판 이름</label>
				        	<input type="text" name="boardNm" class="form-control">
			        	</div>
			        	<div class="info-detail">
				        	<label>공개 여부</label>
				        	<div>
					        	<div class="form-check">
								    <input class="form-check-input" type="radio" name="publicYn" value="Y" id="flexRadioDefault1">
								    <label class="form-check-label" for="flexRadioDefault1">전체 공개</label>
								</div>
								<div class="form-check">
								    <input class="form-check-input" type="radio"  id="flexRadioDefault1" onclick="showMember(this);"> <!-- 공유할 사람들을 작성할 입력란이 보이도록 한다. -->
								    <label class="form-check-label" for="flexRadioDefault1">일부 공개</label>
								</div>
								<input type="text" class="form-control" style="background: #fff; width: 480px; border-style: none; font-color: red;" readonly placeholder="* 일부 공개 시 게시판 url을 공유하여 초대가 가능합니다.">
							</div>
						</div>
						<div class="info-detail">
				        	<label>익명 여부</label>
				        	<div>
					        	<div class="form-check">
								    <input class="form-check-input" type="radio" name="anonymityYn" value="Y" id="flexRadioDefault1">
								    <label class="form-check-label" for="flexRadioDefault1">익명</label>
								</div>
								<div class="form-check">
								    <input class="form-check-input" type="radio" name="anonymityYn" value="N" id="flexRadioDefault1">
								    <label class="form-check-label" for="flexRadioDefault1">실명</label>
								</div>
				        	</div>
			        	</div>
			        	<div class="info-detail">
				        	<label>익명 여부</label>
				        	<div>
								<div class="form-check">
								    <input class="form-check-input" type="radio" id="flexRadioDefault1">
								    <label class="form-check-label" for="flexRadioDefault1">부서게시판</label>
								</div>
								<div class="form-check">
								    <input class="form-check-input" type="radio" name="deptNo" value="0" id="flexRadioDefault1">
								    <label class="form-check-label" for="flexRadioDefault1">전사게시판</label>
								</div>
								<div class="select-container col-md-3">
									<select class="form-select" name="deptNo" aria-label="Default select example">
										<option selected>선택</option>
										<c:if test="${not empty deptData}">
											<c:forEach items="${deptData}" var="dept">
												<option value="${dept.deptNo}">${dept.deptName}</option>
											</c:forEach>
										</c:if>
									</select>
								</div>
				        	</div>
			        	</div>
			        </div>
			    </div>
			    <div class="modal-footer">
				    <button type="submit" class="btn btn-primary">생성</button>
			    </div>
		    </div>
	 	</div>
	</div>
</form>