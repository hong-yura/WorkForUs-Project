<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>workforus - 회원가입</title>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="static/css/bootstrap.css">
    <link rel="stylesheet" href="static/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="static/css/app.css">
    <link rel="stylesheet" href="static/css/pages/auth.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
	<link rel="stylesheet" href="static/vendors/iconly/bold.css">
	<link rel="stylesheet" href="static/vendors/perfect-scrollbar/perfect-scrollbar.css">
	<c:url var="bs5" value="/webjars/bootstrap/5.2.0" />
	<script type="text/javascript" src="${bs5}/js/bootstrap.min.js"></script>
</head>

<body>
    <div id="auth">
		<div class="row h-100">
		    <div class="col-lg-5 col-12">
		        <div id="auth-left">
		            <div class="auth-logo">
		                <a href=""><img src="static/images/logo/logo-main.png" alt="Logo"></a>
		            </div>
		            <h1 class="auth-title">회원가입</h1>
					
					<c:url var="loginUrl" value="/login"/>
					<c:url var="signupUrl" value="/signup"/>
		            <form action="${signupUrl}" method="post">
		                <div class="form-group position-relative has-icon-left mb-4">
		                    <input type="text" class="form-control form-control-xl" id="id" name="empId" placeholder="ID">
		                    <div class="form-control-icon">
		                        <i class="bi bi-person"></i>
		                    </div>
		                </div>
		                <div class="form-group position-relative has-icon-left mb-4">
		                    <input type="text" class="form-control form-control-xl" id="name" name="empNm" placeholder="Username">
		                    <div class="form-control-icon">
		                        <i class="bi bi-person"></i>
		                    </div>
		                </div>
		                <div class="form-group position-relative has-icon-left mb-4">
		                    <input type="password" class="form-control form-control-xl" onchange="ok_check()" id="pw" name="empPw" placeholder="Password">
		                    <span id="okCheck"></span>
		                    <div class="form-control-icon">
		                        <i class="bi bi-shield-lock"></i>
		                    </div>
		                </div>
		                <div class="form-group position-relative has-icon-left mb-4">
		                    <input type="password" class="form-control form-control-xl" onchange="check_pw()" id="pwCheck" name="empCheckPw" placeholder="Confirm Password">
		                    <span id="checkPw"></span>
		                    <div class="form-control-icon">
		                        <i class="bi bi-shield-lock"></i>
		                    </div>
		                </div>
		                <div class="form-group position-relative has-icon-left mb-4">
		                    <input type="text" class="form-control form-control-xl" onchange="check_email()" id="email_id" name="empEmail" placeholder="이메일">
		                    <span id="checkEmail"></span>
		                    <div class="form-control-icon">
		                        <i class="bi bi-envelope"></i>
		                    </div>
		                </div>
		                <div class="form-group position-relative has-icon-left mb-4">
		                    <input type="text" class="form-control form-control-xl" onchange="check_assist_email()" id="email_assist_id" name="empAssistEmail" placeholder="이메일" />
		                    <span id="checkAssistEmail"></span>
		                    <div class="form-control-icon">
		                        <i class="bi bi-envelope"></i>
		                    </div>
		                </div>
		                
		                <button class="btn btn-primary btn-block btn-lg shadow-lg mt-5" type="button" onclick="ok_signup_check()">회원가입</button>
		            
		            <div class="text-center mt-5 text-lg fs-4">
		                <p class='text-gray-600'>Already have an account? <a href="auth-login.html" class="font-bold">Log in</a>.</p>
		            </div>
		            
			        <div class="modal fade" id="signupCompleteModal" tabindex="-1" aria-labelledby="errorModalLabel">
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
				</form>
		        </div>
		    </div>
		    <div class="col-lg-7 d-none d-lg-block">
		        <div id="auth-right"></div>
		    </div>
		</div>
    </div>
    <script src="static/js/bootstrap.js"></script>
    <script src="static/js/app.js"></script>
    <script src="static/js/signup.js"></script>
</body>

</html>