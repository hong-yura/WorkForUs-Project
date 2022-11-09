<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:url var="deptAddUrl" value="/admin/dept-manage" />
<form action="${deptAddUrl}" method="post" id="deptAddForm">
	<div class="mb-3 font-bold">
		<label id="deptAddNo" class="col-form-label">부서 코드는 자동으로 생성됩니다.</label>
	</div>
	<div class="mb-3 font-bold">
		<label for="deptAddName" class="col-form-label">부서명 :</label>
		<input id="deptAddName" class="form-control" name="deptName" placeholder="부서명 입력" >
	</div>
	<div class="mb-3 font-bold">
		<label for="deptAddMngId" class="col-form-label">부서 책임자 :</label>
		<input id="deptAddMngId" class="form-control" name="deptMngId" placeholder="부서 책임자 사원 번호 입력 (직급 부장 이상만 가능)" >
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-primary" onclick="deptAdd();">추 가</button>
		<button type="button" class="btn btn-danger" data-bs-dismiss="modal">취 소</button>
	</div>
</form>
