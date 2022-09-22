<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:url var="deptModUrl" value="/admin/dept_manage" />
<form action="${deptModUrl}" method="post" id="deptModForm">
	<div class="mb-3 font-bold">
		<label for="deptNo" class="col-form-label">부서 코드는 수정 할 수 없습니다.</label>
		<input id="deptNo" type="hidden" class="form-control">
	</div>
	<div class="mb-3 font-bold">
		<label for="deptName" class="col-form-label">부서명 :</label>
		<input id="deptName" class="form-control" name="deptName" placeholder="부서명 입력">
	</div>
	<div class="mb-3 font-bold">
		<label for="deptMngId" class="col-form-label">부서 책임자 :</label>
		<input name="deptMngId" class="form-control" id="deptMngId" placeholder="부서 책임자 사원 번호 입력 (직급 부장 이상만 가능)">
	</div>
	<div class="mb-3 font-bold">
		<input id="deptModDt" type="hidden" class="form-control" name="deptModDt" readonly>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-info" id="modDeptBtn" onclick="deptModify();">수 정</button>
		<button type="button" class="btn btn-danger" data-bs-dismiss="modal">취 소</button>
	</div>
</form>
