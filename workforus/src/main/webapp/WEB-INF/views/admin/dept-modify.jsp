<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:url var="deptModUrl" value="/admin/dept-manage" />
<form action="${deptModUrl}" method="post" id="deptModForm">
	<div class="mb-3 font-bold">
		<label id="deptModNo" class="col-form-label">부서 코드는 수정 할 수 없습니다.</label>
	</div>
	<div class="mb-3 font-bold">
		<label for="deptModName" class="col-form-label">부서명 :</label>
		<input id="deptModName" class="form-control" name="deptName" placeholder="부서명 입력">
	</div>
	<div class="mb-3 font-bold">
		<label for="deptModMngId" class="col-form-label">부서 책임자 :</label>
		<input id="deptModMngId" class="form-control" name="deptMngId" placeholder="부서 책임자 사원 번호 입력 (직급 부장 이상만 가능)">
	</div>
	<div class="mb-3 font-bold">
		<input id="modDeptModDt" type="hidden" class="form-control" name="deptModDt" readonly>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-info" onclick="deptModify();">수 정</button>
		<button type="button" class="btn btn-danger" data-bs-dismiss="modal">취 소</button>
	</div>
</form>
