<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>workforus - Department Manage</title>
	<%@ include file="../module/header.jsp" %>
	<link rel="stylesheet" href="static/css/pages/jui-ui.min.classic.css" />
	<link rel="stylesheet" href="static/css/pages/admin.css" />
	<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
	<script src="static/js/pages/admin/jui-core.min.js"></script>
	<script src="static/js/pages/admin/jui-ui.min.js"></script>
</head>
<script type="text/javascript">
	function formCheck(form) {
		var modal = new bootstrap.Modal(document.getElementById("errorModal"), {
			keyboard: false
		});
		var title = modal._element.querySelector(".modal-title");
		var body = modal._element.querySelector(".modal-body");
		if(form.title.value === undefined || form.title.value.trim() === "") {
			title.innerText = "부서명을 입력하세요.;
			body.innerText = "부서 책임자 사원번호를 입력하세요.";
			modal.show();
			return;
		}
		
		form.submit();
	}	
</script>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<div id="app">
		<div id="main">
		
			<div class="page-heading">
				<div class="page-title">
					<div class="row">
						<div class="col-12 col-md-6 order-md-1 order-last">
							<h3>Admin - Department Manage</h3>
						</div>
						<div class="col-12 col-md-6 order-md-2 order-first">
							<nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
								<ol class="breadcrumb">
									<li class="breadcrumb-item">
										<a href="${homeUrl}">Home</a></li>
									<li class="breadcrumb-item">
										<a href="#">Admin</a></li>
									<li class="breadcrumb-item active" aria-current="page">Department Manage</li>
								</ol> 
							</nav>
						</div>
					</div>
				</div>
			</div>
			
			<section class="section">
				<c:url var="membersUrl" value="/members" />
				<div class="row">
					<div class="col-xl-4">
						<div class="card">
							<div class="card-content">
								<div class="card-body">
									<div class="row">
										<div class="card-title col-md-9">
											<h4>조직도 설계</h4>
										</div>
										<div class="col-md-3">
											<c:url var="adminUrl" value="/admin" />
											<button type="button" class="btn btn-outline-primary"
												data-bs-toggle="modal" data-bs-target="#deptAddModal">추 가</button>
										</div>
									</div>
									<!-- tree -->
									<div class="jui">
										<div class="row row-tree">
											<div class="col col-3">
												<ul id="tree_3" class="tree line">
													<li class="open root">
														<i></i><a>workforus</a>
														<ul>
														
														<c:forEach items="${deptDatas}" var="deptData">
															<li class="open">
																<i></i><a onclick="deptDetail(${deptData.deptNo});" >${deptData.deptName}</a>
															</li>
														</c:forEach>
														</ul>
													</li>
												</ul>
											</div>
										</div>
									</div>
									<!-- tree End -->
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-66p">
						<div class="card">
							<div class="card-content">
								<div id="deptDetailCard" class="card-body">
									<div class="row">
										<div class="col-md-8">
											<h4 class="card-title">부서 정보</h4>
										</div>
										<div class="col-md-3">
											<button type="button" class="btn btn-outline-info dept-btn-mg"
												data-bs-toggle="modal" data-bs-target="#deptModModal">수 정</button>
											<button type="button" class="btn btn-outline-danger" 
												data-bs-toggle="modal" data-bs-target="#deptDelModal">삭 제</button>
										</div>
									</div>
									<hr>
									<form action="get" class="form" id="deptForm">
										<div class="form-body">
											<div class="row">
												<div class="col-md-20p dept-label">
													<label>부서 코드</label>
												</div>
												<div class="col-md-8">
													<div class="form-group">
														<div class="position-relative">
															<input class="form-control dept-form-control" type="text" name="deptNo" value="${deptData.deptNo}" disabled >
														</div>
													</div>
												</div>
												<div class="col-md-20p dept-label">
													<label>부서명</label>
												</div>
												<div class="col-md-8">
													<div class="form-group">
														<div class="position-relative">
															<input class="form-control dept-form-control" type="text" name="deptName" disabled >
														</div>
													</div>
												</div>
												<div class="col-md-20p dept-label">
													<label>부서 책임자</label>
												</div>
												<div class="col-md-8">
													<div class="form-group">
														<div class="position-relative">
															<input class="form-control dept-form-control" type="text" name="deptMngId" disabled >
														</div>
													</div>
												</div>
												<div class="col-md-20p dept-label">
													<label>부서 생성일</label>
												</div>
												<div class="col-md-8">
													<div class="form-group">
														<div class="position-relative">
															<input class="form-control dept-form-control" type="text" name="deptAddDt" disabled >
														</div>
													</div>
												</div>
												<div class="col-md-20p dept-label">
													<label>최종 수정일</label>
												</div>
												<div class="col-md-8">
													<div class="form-group">
														<div class="position-relative">
															<input class="form-control dept-form-control" type="text" name="deptModDt" disabled >
														</div>
													</div>
												</div>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
					
				</div>
				
				<!-- 부서 추가 모달 -->
				<div class="modal fade" id="deptAddModal" tabindex="-1" aria-labelledby="deptModalLabel1" aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="deptModalLabel1">부서 추가</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<jsp:include page="../admin/dept_add.jsp" />
							</div>
						</div>
					</div>
				</div>
				
				<!-- 부서 수정 모달 -->
				<div class="modal fade" id="deptModModal" tabindex="-1" aria-labelledby="deptModalLabel2" aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="deptModalLabel2">부서 정보 수정</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<jsp:include page="../admin/dept_modify.jsp" />
							</div>
						</div>
					</div>
				</div>
			
				<!-- 부서 삭제 모달 -->
				<div class="modal fade" tabindex="-1" id="deptDelModal">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">부서 삭제</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<p>해당 부서를 삭제하시겠습니까?</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
								<button type="button" class="btn btn-danger" data-bs-dismiss="modal" onclick="deptDelete();">삭제</button>
							</div>
						</div>
					</div>
				</div>
				<!-- 삭제 결과 확인 -->
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
								<button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="location.href='${deptUrl}'">확인</button>
							</div>
						</div>
					</div>
				</div>
			</section>
			<%@ include file="../module/footer.jsp" %>
		</div>
	</div>
	<script src="static/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script src="static/js/bootstrap.bundle.min.js"></script>
	<script src="static/js/main.js"></script>
	
	<script type="text/javascript">
		// 에러 체크
		var modal = new bootstrap.Modal(document.getElementById("errorModal"), {
			keyboard: false
		})
		modal.show();
	
		// 부서 정보 상세
		function deptDetail(no) {
			$.ajax({
				url: "${adminUrl}/dept_manage",
				type: "get",
				data: {
					no: no
				},
				success: function(data) {
					var form = document.getElementById("deptForm");
					
					form.deptNo.value = data.deptNo;
					form.deptName.value = data.deptName;
					form.deptMngId.value = data.deptMngId;
					form.deptAddDt.value = data.deptAddDt;
					form.deptModDt.value = data.deptModDt;
					
				}
			})
			
		}
		
		// 부서 삭제 모달 내에 삭제 버튼 클릭 시 동작
		function deptDelete() {
			
		}
		
	</script>

</body>
</html>