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
    <title>workforus - 로그인</title>
    <c:url var="staticUrl" value="/static"/>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${staticUrl}/css/bootstrap.css">
    <link rel="stylesheet" href="${staticUrl}/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="${staticUrl}/css/app.css">
    <link rel="stylesheet" href="${staticUrl}/css/pages/auth.css">
</head>

<body>
    <div id="auth">
		<div class="row h-100">
		    <div class="col-lg-5 col-12">
		        <div id="auth-left">
		            <div class="auth-logo">
		                <a href=""><img src="static/images/logo/logo-main.png" alt="Logo"></a>
		            </div>
		            <h1 class="auth-title">로그인</h1>
					<c:url var="loginUrl" value="/login"/>
		            <form action="${loginUrl}" method="post">
		                <div class="form-group position-relative has-icon-left mb-4">
		                    <input type="text" class="form-control form-control-xl" name="empId" placeholder="ID">
		                    <div class="form-control-icon">
		                        <i class="bi bi-person"></i>
		                    </div>
		                </div>
		                <div class="form-group position-relative has-icon-left mb-4">
		                    <input type="password" class="form-control form-control-xl" name="empPw" placeholder="Password">
		                    <div class="form-control-icon">
		                        <i class="bi bi-shield-lock"></i>
		                    </div>
		                </div>
		                <button class="btn btn-primary btn-block btn-lg shadow-lg mt-5">로그인</button>
		                ${requestScope.loginFailMsg}
		            </form>
		        </div>
		    </div>
		    <div class="col-lg-7 d-none d-lg-block">
		        <div id="auth-right"></div>
		    </div>
		</div>
    </div>
</body>
</html>