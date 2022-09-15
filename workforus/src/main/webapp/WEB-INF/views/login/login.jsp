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
	<link rel="stylesheet" href="static/css/style.css">
	<link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="static/css/bootstrap.css">
    <link rel="stylesheet" href="static/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="static/css/app.css">
    <link rel="stylesheet" href="static/css/pages/auth.css">
</head>
<body>
	<section class="background1_section">
		<div class="login-layout">
			<div class="login-controller">
				<h1 class="auth-title" style="color:white;">로그인</h1>
				<c:url var="loginUrl" value="/login"/>
	            <form action="${loginUrl}" method="post">
	                <div class="form-group position-relative has-icon-left mb-4" style="margin:20px 0 20px 0;">
	                    <input type="text" class="form-control form-control-xl" name="empId" placeholder="ID">
	                    <div class="form-control-icon" style="transform: translateY(-60%);">
	                        <i class="bi bi-person"></i>
	                    </div>
	                </div>
	                <div class="form-group position-relative has-icon-left mb-4" style="margin:20px 0 20px 0;">
	                    <input type="password" class="form-control form-control-xl" name="empPw" placeholder="Password">
	                    <div class="form-control-icon" style="transform: translateY(-60%);">
	                        <i class="bi bi-shield-lock"></i>
	                    </div>
	                </div>
	                <button class="btn btn-primary btn-block btn-lg shadow-lg mt-5" style="background: white; color:black;">Log in</button>
	                <div style="font-size:20px;">${requestScope.loginFailMsg}</div>
	            </form>
			</div>
		</div>
	</section>
	<section class="background2_section">
	</section>
	<section class="background3_section">
		<img class="logo-photo" src="static/images/logo/logo-main.png">
	</section>
	<script src="static/js/login.js"></script>
</body>
</html>