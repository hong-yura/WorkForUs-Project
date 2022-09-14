<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:url var="deptUrl" value="/dept_manage" />
<form action="${deptUrl}" method="post">
	<div class="mb-3">
		<label for="deptNo" class="col-form-label">부서 코드는 자동으로 생성됩니다.</label>
		<input type="text" class="form-control" id="deptNo" readonly>
	</div>
	<div class="mb-3">
		<label for="deptName" class="col-form-label">부서명 :</label>
		<input class="form-control" id="deptName" placeholder="부서명">
	</div>
	<div class="mb-3">
		<label for="deptMngId" class="col-form-label">부서 책임자 :</label>
		<input class="form-control" id="deptMngId" placeholder="부서 책임자 사원 번호">
	</div>
	<div class="mb-3">
		<label for="deptAddDt" class="col-form-label">부서 생성일 :</label>
		<input class="form-control" id="deptAddDt" readonly>
	</div>
	<div class="mb-3">
		<label for="deptModDt" class="col-form-label">최종 수정일 :</label>
		<input class="form-control" id="deptModDt" readonly>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-success" onclick="formCheck(this.form);">완 료</button>
		<button type="button" class="btn btn-danger" data-bs-dismiss="modal">취 소</button>
	</div>
</form>
