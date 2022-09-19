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
                <h3>Profile Statistics</h3>
            </div>
            <div class="page-content">
                <section class="row">
                    <div class="col-12 col-lg-9">
                        <div class="row">
                            <div class="col-6 col-lg-3 col-md-6">
                                <div class="card">
                                    <div class="card-body px-3 py-4-5">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="stats-icon purple">
                                                    <i class="iconly-boldShow"></i>
                                                </div>
                                            </div>
                                            <div class="col-md-8">
                                                <h6 class="text-muted font-semibold">Profile Views</h6>
                                                <h6 class="font-extrabold mb-0">112.000</h6>
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
                                                    <i class="iconly-boldProfile"></i>
                                                </div>
                                            </div>
                                            <div class="col-md-8">
                                                <h6 class="text-muted font-semibold">Followers</h6>
                                                <h6 class="font-extrabold mb-0">183.000</h6>
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
                                                    <i class="iconly-boldAdd-User"></i>
                                                </div>
                                            </div>
                                            <div class="col-md-8">
                                                <h6 class="text-muted font-semibold">Following</h6>
                                                <h6 class="font-extrabold mb-0">80.000</h6>
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
                                                    <i class="iconly-boldBookmark"></i>
                                                </div>
                                            </div>
                                            <div class="col-md-8">
                                                <h6 class="text-muted font-semibold">Saved Post</h6>
                                                <h6 class="font-extrabold mb-0">112</h6>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-header">
                                        <h4>Profile Visit</h4>
                                    </div>
                                    <div class="card-body">
                                        <div id="chart-profile-visit"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12 col-xl-4">
                                <div class="card">
                                    <div class="card-header">
                                        <h4>Profile Visit</h4>
                                    </div>
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-6">
                                                <div class="d-flex align-items-center">
                                                    <svg class="bi text-primary" width="32" height="32" fill="blue"
                                                        style="width:10px">
                                                        <use
                                                            xlink:href="assets/vendors/bootstrap-icons/bootstrap-icons.svg#circle-fill" />
                                                    </svg>
                                                    <h5 class="mb-0 ms-3">Europe</h5>
                                                </div>
                                            </div>
                                            <div class="col-6">
                                                <h5 class="mb-0">862</h5>
                                            </div>
                                            <div class="col-12">
                                                <div id="chart-europe"></div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-6">
                                                <div class="d-flex align-items-center">
                                                    <svg class="bi text-success" width="32" height="32" fill="blue"
                                                        style="width:10px">
                                                        <use
                                                            xlink:href="assets/vendors/bootstrap-icons/bootstrap-icons.svg#circle-fill" />
                                                    </svg>
                                                    <h5 class="mb-0 ms-3">America</h5>
                                                </div>
                                            </div>
                                            <div class="col-6">
                                                <h5 class="mb-0">375</h5>
                                            </div>
                                            <div class="col-12">
                                                <div id="chart-america"></div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-6">
                                                <div class="d-flex align-items-center">
                                                    <svg class="bi text-danger" width="32" height="32" fill="blue"
                                                        style="width:10px">
                                                        <use
                                                            xlink:href="assets/vendors/bootstrap-icons/bootstrap-icons.svg#circle-fill" />
                                                    </svg>
                                                    <h5 class="mb-0 ms-3">Indonesia</h5>
                                                </div>
                                            </div>
                                            <div class="col-6">
                                                <h5 class="mb-0">1025</h5>
                                            </div>
                                            <div class="col-12">
                                                <div id="chart-indonesia"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-xl-8">
                                <div class="card">
                                    <div class="card-header">
                                        <h4>Latest Comments</h4>
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table class="table table-hover table-lg">
                                                <thead>
                                                    <tr>
                                                        <th>Name</th>
                                                        <th>Comment</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td class="col-3">
                                                            <div class="d-flex align-items-center">
                                                                <div class="avatar avatar-md">
                                                                    <img src="${staticUrl}/images/faces/5.jpg">
                                                                </div>
                                                                <p class="font-bold ms-3 mb-0">Si Cantik</p>
                                                            </div>
                                                        </td>
                                                        <td class="col-auto">
                                                            <p class=" mb-0">Congratulations on your graduation!</p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="col-3">
                                                            <div class="d-flex align-items-center">
                                                                <div class="avatar avatar-md">
                                                                    <img src="${staticUrl}/images/faces/2.jpg">
                                                                </div>
                                                                <p class="font-bold ms-3 mb-0">Si Ganteng</p>
                                                            </div>
                                                        </td>
                                                        <td class="col-auto">
                                                            <p class=" mb-0">Wow amazing design! Can you make another
                                                                tutorial for
                                                                this design?</p>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-lg-3">
                        <div class="card">
                            <div class="card-body py-4 px-5">
                                <div class="d-flex align-items-center">
                                    <div class="avatar avatar-xl">
                                        <img src="${staticUrl}/images/faces/1.jpg" alt="Face 1">
                                    </div>
                                    <div class="ms-3 name">
                                        <h5 class="font-bold">John Duck</h5>
                                        <h6 class="text-muted mb-0">@johnducky</h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card">
                            <div class="card-header">
                                <h4>Recent Messages</h4>
                            </div>
                            <div class="card-content pb-4">
                                <div class="recent-message d-flex px-4 py-3">
                                    <div class="avatar avatar-lg">
                                        <img src="${staticUrl}/images/faces/4.jpg">
                                    </div>
                                    <div class="name ms-4">
                                        <h5 class="mb-1">Hank Schrader</h5>
                                        <h6 class="text-muted mb-0">@johnducky</h6>
                                    </div>
                                </div>
                                <div class="recent-message d-flex px-4 py-3">
                                    <div class="avatar avatar-lg">
                                        <img src="${staticUrl}/images/faces/5.jpg">
                                    </div>
                                    <div class="name ms-4">
                                        <h5 class="mb-1">Dean Winchester</h5>
                                        <h6 class="text-muted mb-0">@imdean</h6>
                                    </div>
                                </div>
                                <div class="recent-message d-flex px-4 py-3">
                                    <div class="avatar avatar-lg">
                                        <img src="${staticUrl}/images/faces/1.jpg">
                                    </div>
                                    <div class="name ms-4">
                                        <h5 class="mb-1">John Dodol</h5>
                                        <h6 class="text-muted mb-0">@dodoljohn</h6>
                                    </div>
                                </div>
                                <div class="px-4">
                                    <button class='btn btn-block btn-xl btn-light-primary font-bold mt-3'>Start
                                        Conversation</button>
                                </div>
                            </div>
                        </div>
                        <div class="card">
                            <div class="card-header">
                                <h4>Visitors Profile</h4>
                            </div>
                            <div class="card-body">
                                <div id="chart-visitors-profile"></div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
            <%@ include file="./module/footer.jsp" %>
        </div>
    </div>
    <script src="${staticUrl}/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="${staticUrl}/js/bootstrap.bundle.min.js"></script>

    <script src="${staticUrl}/vendors/apexcharts/apexcharts.js"></script>
    <script src="${staticUrl}/js/pages/dashboard.js"></script>

    <script src="${staticUrl}/js/main.js"></script>
    
    <script src="${staticUrl}/js/pages/info/info.js"></script>
</body>

</html>
