<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:url var="staticUrl" value="/static" />
<c:url var="jQueryUrl" value="/webjars/jquery/3.6.0" />
<c:url var="bs5Url" value="/webjars/bootstrap/5.2.0" />
<c:url var="dayjsUrl" value="/webjars/dayjs/1.11.1"/>

<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap">
<link rel="stylesheet" href="${staticUrl}/css/bootstrap.css">
<link rel="stylesheet" href="${staticUrl}/vendors/iconly/bold.css">
<link rel="stylesheet" href="${staticUrl}/css/app.css">
<link rel="stylesheet" href="${staticUrl}/vendors/perfect-scrollbar/perfect-scrollbar.css">
<link rel="stylesheet" href="${staticUrl}/vendors/bootstrap-icons/bootstrap-icons.css">
<link rel="icon" href="${staticUrl}/images/logo/favicon.ico">
<script src="${dayjsUrl}/dayjs.min.js"></script>
<script src="${dayjsUrl}/locale/ko.js"></script>
<script src="${bs5Url}/js/bootstrap.min.js"></script>
<script src="${jQueryUrl}/jquery.min.js"></script>