<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  <!-- 게시판 생성 modal -->
  <form>
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
				        	<label>이름</label>
				        	<input type="text" class="form-control">
			        	</div>
			        	<div class="info-detail">
				        	<label>성별</label>
				        	<div>
					        	<div class="form-check">
								    <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
								    <label class="form-check-label" for="flexRadioDefault1">남</label>
								</div>
								<div class="form-check">
								    <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
								    <label class="form-check-label" for="flexRadioDefault1">여</label>
								</div>
							</div>
						</div>
						<div class="info-detail">
				        	<label>생년월일</label>
				        	<div class="date-container">
			        			<div class="select-container col-md-3">
						        	<select class="form-select" aria-label="Default select example">
										<option selected>년</option>
										<option value="1">One</option>
										<option value="2">Two</option>
										<option value="3">Three</option>
									</select>
									<label class="date-label">년</label>
								</div>
								<div class="select-container col-md-3">
									<select class="form-select" aria-label="Default select example">
										<option selected>월</option>
										<option value="1">One</option>
										<option value="2">Two</option>
										<option value="3">Three</option>
									</select>
									<label class="date-label">월</label>
								</div>
								<div class="select-container col-md-3">
									<select class="form-select" aria-label="Default select example">
										<option selected>일</option>
										<option value="1">One</option>
										<option value="2">Two</option>
										<option value="3">Three</option>
									</select>
									<label class="date-label">일</label>
								</div>
							</div>
			        	</div>
			        	<div class="info-detail">
				        	<label>주소</label>
				        	<div class="addr-control">
					        	<input type="text" class="form-control margin-right" id="sample6_postcode" placeholder="우편번호">
								<button type="button" class="btn btn-secondary addr-btn" onclick="sample6_execDaumPostcode()">검색</button>
							</div>
							<div class="addr-control">
								<input type="text" class="form-control" id="sample6_address" placeholder="주소">
							</div>
							<div class="addr-control">
								<input type="text" class="form-control margin-right" id="sample6_detailAddress" placeholder="상세주소">
								<input type="text" class="form-control" id="sample6_extraAddress" placeholder="참고항목">
				        	</div>
				        </div>
				        <div class="info-detail">
				        	<label>사내 이메일</label>
				        	<input type="text" class="form-control">
				        </div>
				        <div class="info-detail">
				        	<label>사내 번호</label>
				        	<input type="text" class="form-control">
				        </div>
				        <div class="info-detail">
				        	<label>부서</label>
				        	<input type="text" class="form-control" disabled>
				        </div>
				        <div class="info-detail">
				        	<label>직급</label>
				        	<input type="text" class="form-control" disabled>
				        </div>
				        <div class="info-detail">
				        	<div class="password-control">
				        		<div class="password-form margin-right">
						        	<label>비밀번호 재설정</label>
						        	<input type="text" class="form-control">
					        	</div>
					        	<div class="password-form">
						        	<label>확인</label>
						        	<input type="text" class="form-control">
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