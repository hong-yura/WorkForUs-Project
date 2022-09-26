<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="kor">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewpoint" content="width=device-width, initial-scale=1.0">
	<title>workforus - 로그인</title>
	<%@ include file="../module/head.jsp" %>
	<c:url var="staticUrl" value="/static"/>
	<link rel="stylesheet" href="${staticUrl}/css/pages/info.css">
</head>
<body>

	<%@ include file="../module/navigation.jsp" %>
	<div id="app">
        <div id="main">
            <div class="page-heading">
                <h3>개인정보수정</h3>
            </div>
            	<c:url var="infoUrl" value="/info"/>
				    <div class="info-body">
				    	<div class="profile-icon">
				        	<i class="bi bi-person-circle"></i>
				        </div>
				        <div class="info">
				        	<div class="info-detail">
					        	<label>이름</label>
					        	<input type="text" class="form-control" id="empNm" name="empNm" value="${info.empNm}">
				        	</div>
				        	<div class="info-detail">
					        	<label>성별</label>
					        	<div>
					        		<c:choose>
					        			<c:when test="${not empty info.empGender}">
					        				<c:if test="${info.empGender eq '남'}">
									        	<div class="form-check">
												    <input class="form-check-input" type="radio" name="empGender" value="남" checked>
												    <label class="form-check-label" for="flexRadioDefault1">남</label>
												</div>
												<div class="form-check">
												    <input class="form-check-input" type="radio" name="empGender" value="여">
												    <label class="form-check-label" for="flexRadioDefault1">여</label>
												</div>
											</c:if>
											<c:if test="${info.empGender eq '여'}">
												<div class="form-check">
												    <input class="form-check-input" type="radio" name="empGender" value="남">
												    <label class="form-check-label" for="flexRadioDefault1">남</label>
												</div>
												<div class="form-check">
												    <input class="form-check-input" type="radio" name="empGender" value="여" checked>
												    <label class="form-check-label" for="flexRadioDefault1">여</label>
												</div>
											</c:if>
										</c:when>
										<c:otherwise>
											<div class="form-check">
											    <input class="form-check-input" type="radio" name="empGender" value="남">
											    <label class="form-check-label" for="flexRadioDefault1">남</label>
											</div>
											<div class="form-check">
											    <input class="form-check-input" type="radio" name="empGender" value="여">
											    <label class="form-check-label" for="flexRadioDefault1">여</label>
											</div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="info-detail">
					        	<label>생년월일</label>
					        	<div class="date-container">
				        			<div class="select-container col-md-3">
							        	<select class="form-select" id="select_year" onchange="lastDay()" name="empBirthYear">
					        				<c:choose>
						        				<c:when test="${not empty info.empBirth}">
													<option value="${empBirthYear}" selected>${empBirthYear}</option>
						        				</c:when>
						        				<c:otherwise>
													<option selected>년</option>
												</c:otherwise>
											</c:choose>
										</select>
										<label class="date-label">년</label>
									</div>
									<div class="select-container col-md-3">
										<select class="form-select" id="select_month" onchange="lastDay()" name="empBirthMonth">
											<option selected>월</option>
										</select>
										<label class="date-label">월</label>
									</div>
									<div class="select-container col-md-3">
										<select class="form-select" id="select_day" name="empBirthDay">
											<option selected>일</option>
										</select>
										<label class="date-label">일</label>
									</div>
								</div>
				        	</div>
				        	<div class="info-detail">
					        	<label>주소</label>
					        	<div class="addr-control">
						        	<input type="text" class="form-control margin-right" id="sample6_postcode" placeholder="우편번호" name="empZipCode" value="${info.empZipCode}">
									<button type="button" class="btn btn-secondary addr-btn" onclick="sample6_execDaumPostcode()">검색</button>
								</div>
								<div class="addr-control">
									<input type="text" class="form-control" id="sample6_address" placeholder="주소" name="empAddr1" value="${info.empAddr}">
								</div>
								<div class="addr-control">
									<input type="text" class="form-control margin-right" id="sample6_detailAddress" placeholder="상세주소" name="empAddr2" value="${info.empAddrDetail}">
									<input type="text" class="form-control" id="sample6_extraAddress" placeholder="참고항목" name="empAddr3" value="${info.empAddrExtra}">
					        	</div>
					        </div>
					        <div class="info-detail">
					        	<label>사내 이메일</label>
					        	<input type="text" class="form-control" name="empEmail" id="empEmail" value="${info.empEmail}">
					        </div>
					        <div class="info-detail">
					        	<label>사내 번호</label>
					        	<input type="text" class="form-control" name="empPhone" id="empPhone" value="${info.empPhone}">
					        </div>
					        <div class="info-detail">
					        	<label>부서</label>
					        	<input type="text" class="form-control" name="deptNo" value="${info.deptNo}" disabled>
					        </div>
					        <div class="info-detail">
					        	<label>직급</label>
					        	<input type="text" class="form-control" name="jobId" value="${info.jobId}" disabled>
					        </div>
					        <div class="info-detail">
					        	<label>연봉</label>
					        	<input type="text" class="form-control" name="empSalary" value="${info.empSalary}" disabled>
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
							    <div class="div-btn">
								    <button type="button" class="btn btn-primary" onclick="successModify();">수정</button>
							    </div>
					        </div>
				        </div>
			        </div>
			        
		        <!-- 수정 성공 시 모달 -->
		        <div class="modal fade" id="successModal" tabindex="-1" aria-labelledby="errorModalLabel">
					<div class="modal-dialog  modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="errorModalLabel">회원가입 완료</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							</div>
							<div class="modal-body">
								회원가입이 완료되었습니다.
		      				</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-secondary btn-sm" data-bs-dismiss="modal">확인</button>
							</div>
						</div>
					</div>
				</div>
		        
		    <%@ include file="../module/footer.jsp" %>
	   </div>
	</div>
</body>
	<script type="text/javascript">
		function successModify() {
			var empNm = document.getElementById("empNm").value;
			var empGender = document.querySelector("input[name='empGender']:checked").value;
			var empBirthYear = document.querySelector("select[name='empBirthYear']").value;
			var empBirthMonth = document.querySelector("select[name='empBirthMonth']").value;
			empBirthMonth = parseInt(empBirthMonth);
			var empBirthDay = document.querySelector("select[name='empBirthDay']").value;
			empBirthDay = parseInt(empBirthDay);
			
			if(empBirthMonth >= 1 && empBirthMonth <= 9) {
				empBirthMonth = "0" + document.querySelector("select[name='empBirthMonth']").value;
				console.log(empBirthMonth);
			}
			empBirthMonth = String(empBirthMonth);
			
			if(empBirthDay >= 1 && empBirthDay <= 9) {
				empBirthDay = "0" + document.querySelector("select[name='empBirthDay']").value;
				console.log(empBirthDay);
			}
			empBirthDay = String(empBirthDay);
			
			var empBirth = empBirthYear + empBirthMonth + empBirthDay;
			
			if(empBirth === '년월일') {
				empBirth = null;
				console.log(empBirth);
			}
			
			var empZipCode = document.getElementById("sample6_postcode").value;
			var empAddr = document.getElementById("sample6_address").value;
			var empAddrDetail = document.getElementById("sample6_detailAddress").value;
			var empAddrExtra = document.getElementById("sample6_extraAddress").value;
			var empAddrAll = empAddr + " " + empAddrDetail + " " + empAddrExtra;
			var empEmail = document.getElementById("empEmail").value;
			var empPhone = document.getElementById("empPhone").value;
			
			$.ajax({
				type: "post",
				url: "${infoUrl}/modify",
				data: {
					empNm : empNm,
					empGender : empGender,
					empBirth : empBirth,
					empZipCode : empZipCode,
					empAddrAll : empAddrAll,
					empAddr : empAddr,
					empAddrDetail : empAddrDetail,
					empAddrExtra : empAddrExtra,
					empEmail : empEmail,
					empPhone : empPhone
				},
				dataType: "json",
				success: function(data) {
					console.log("ajax success");
					var modal = new bootstrap.Modal(document.getElementById("successModal"), {
						keyboard: false
					});
					
					modal.show();
				}
 			})
		}
	</script>
	<script src="${staticUrl}/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script src="${staticUrl}/js/bootstrap.bundle.min.js"></script>
	<script src="${staticUrl}/js/main.js"></script>
	
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="${staticUrl}/js/pages/info/info.js"></script>
</html>