<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="kor">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>workforus - Home</title>
    <%@ include file="./module/head.jsp" %>
    <link rel="stylesheet" href="${staticUrl}/css/pages/info.css">
    <c:url var="axiosUrl" value="/webjars/axios/0.27.2" />
    <c:url var="babelUrl" value="/webjars/babel-standalone/6.26.0" />
	<script src="${axiosUrl}/dist/axios.min.js"></script>
	<script src="${babelUrl}/babel.min.js"></script>
</head>
<body>
    <%@ include file="./module/navigation.jsp" %>
    
    <!-- 개인페이지 modal -->
    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	  	<div class="modal-dialog modal-xl">
		    <div class="modal-content">
			    <div class="modal-header">
				    <h5 class="modal-title" id="staticBackdropLabel">개인정보수정</h5>
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
				    <button type="button" class="btn btn-primary">수정</button>
			    </div>
		    </div>
	 	</div>
	</div>
	
    <div id="app">
        <div id="main">
            <div class="page-heading">
                <h3>Home</h3>
            </div>
            <div class="page-content">
                <section class="row">
                    <div class="col-12 col-lg-12">
                        <div class="row">
                            <div class="col-6 col-lg-3 col-md-6">
                                <div class="card">
                                    <div class="card-body px-3 py-4-5">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="stats-icon purple">
                                                    <i class="iconly-boldCalendar"></i>
                                                </div>
                                            </div>
                                            <div class="col-md-8">
                                            	<a href="./calendar">
	                                                <h6 class="text-muted font-semibold">오늘의 일정</h6>
	                                                <h6 class="font-extrabold mb-0 calendar-today"> </h6>
	                                            </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-6 col-lg-3 col-md-6">
                                <div class="card">
                                    <div class="card-body px-3 py-4-5">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="stats-icon blue">
                                                    <i class="iconly-boldWork"></i>
                                                </div>
                                            </div>
                                            <div class="col-md-8">
	                                            <a href="./mail">
	                                                <h6 class="text-muted font-semibold">안 읽은 메일 수</h6>
	                                                <h6 class="font-extrabold mb-0">${cntMail }</h6>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-6 col-lg-3 col-md-6">
                                <div class="card">
                                    <div class="card-body px-3 py-4-5">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="stats-icon green">
                                                    <i class="iconly-boldHeart"></i>
                                                </div>
                                            </div>
                                            <div class="col-md-8">
	                                            <div class="mt-3">
	                                            	<a href="./board?bId=2">
	                                                	<h6 class="text-muted font-semibold">주간 식단표</h6>
	                                            	</a>
	                                            </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-6 col-lg-3 col-md-6">
                                <div class="card">
                                    <div class="card-body px-3 py-4-5">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="stats-icon red">
                                                    <i class="iconly-boldChat"></i>
                                                </div>
                                            </div>
                                            <div class="col-md-8">
	                                            <div class="mt-3">
	                                            	<a href="./chat">
	                                                	<h6 class="text-muted font-semibold">채팅하기</h6>
	                                            	</a>
	                                            </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <div class="card">
                                    <div class="card-header">
                                        <h2>이달의 사원</h4>
                                    </div>
                                    <div class="card-body">
                                        <img src="${staticUrl}/images/samples/best_emp.png">
                                        <h3 class="mb-0 ms-3">"믿음과 신뢰의 상징, 최영원입니다!"</h5>
                                    </div>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="card">
                                    <div class="card-header">
                                        <h2>사장님 말씀</h4>
                                    </div>
                                    <div class="card-body">
                                        <img src="${staticUrl}/images/samples/byebye.png">
                                        <h3 class="mb-0 ms-3">"이번 프로젝트 다들 고생하셨습니다~"</h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            <%@ include file="./module/footer.jsp" %>
          </div>
        </div>
    <script type="text/babel">
    	const empId = "${empId}"
    	const calendarToday = document.getElementsByClassName("calendar-today");
    	console.log(calendarToday);
		console.log(calendarToday[0].firstChild.data);
		axios.get(`http://localhost/schedule?empId=${empId}`)
			.then((res) => {
				calendarToday[0].firstChild.data = res.data.data.length + " 개";
			});
    </script>
    <script src="${staticUrl}/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="${staticUrl}/js/bootstrap.bundle.min.js"></script>

    <script src="${staticUrl}/js/main.js"></script>
</body>

</html>
