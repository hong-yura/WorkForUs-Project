<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"> 
	<title>workforus - 게시판</title>
    <%@ include file="../module/header.jsp" %>
    <link rel="stylesheet" href="static/css/pages/board.css">
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	
    <div id="app">
      <div id="main">
      	<div class="page-heading">
      		<h3>Board</h3> <!-- 게시판 이름 -->
      	</div>
	      	<!-- board -->
      	<div class="page-content">
			<section class="row">
				<div class="col-12"> 
					<!-- 상단 -->
					<div class="section-top radius">
						<div class="info-container">
							<ul class="info-ul black" style="margin-bottom: 20px;">
								<li>게시판 주소 :  ${boardData.invLink}
								<button class="btn"><i class="bi bi-clipboard-check"></i></button>
								</li>
								<li>운영자 : ${boardData.boardManager}</li>
							</ul>
							<hr style="margin: 0px; width: 99%">
							<div class="dropdown black" >
								<a class="nav-link active dropdown-toggle text-gray-600" href="#" data-bs-toggle="dropdown" aria-expanded="false">
								게시판 멤버</a>
								<ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton" style="min-width: 11rem;">
									<li>
										<h6 class="dropdown-header">멤버</h6>
									</li>
									<c:if test="${not empty participList}">
										<c:forEach items="${participList}" var="participDto">
											<li>
												<a class="dropdown-item" href="#">
													<i class="icon-mid bi bi-person me-2"></i>${participDto.empObj.empNm}
												</a>
											</li>
										</c:forEach>
									</c:if>
								</ul>
							</div>
						</div>
					</div>
					<!-- 메인 -->
					<div class="section-main radius">
						<div class="main-button">
							<button type="submit" class="btn black" style="font-weight: bold;"><i class="bi bi-pencil"></i> 새글작성</button>
							<button type="submit" class="btn black"><i class="bi bi-trash"></i> 삭제</button>
							<button type="submit" class="btn black"> <i class="bi bi-plus-circle"></i> 게시판생성</button>
							<div class="search-container">
								<!-- <select class="form-select">
									<option>제목</option>
									<option>작성자</option>
									<option>작성일</option>
								</select>
								 -->
								<i class="bi bi-search"> </i><input class="search-input" type="search" placeholder=" 입력">
								<button class="btn-style black" type="button">검색</button>
							</div>
							<hr style="margin: 0px; margin-bottom: 3px;">
						</div>
						<div class="dataTable-container">
							<table class="table dataTable-table black">
								 <colgroup>
						            <col width=5%>
						            <col width=10%>
						            <col width=40%>
						            <col width=10%>
						            <col width=10%>
						            <col width=10%>
						            <col width=10%>
						        </colgroup>
								<thead>
									<tr>
										<th><input type="checkbox"></th>
										<th>번호</th>
										<th>제목</th>
										<th>작성자</th>
										<th>작성일</th>
										<th>조회</th>
										<th>좋아요</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${not empty postList}">
										<c:forEach items="${postList}" var="postData">
											<c:url var="detailUrl" value="/board/detail">
												<c:param name="boardId" value="${postData.boardId}"></c:param>
											</c:url>
											<c:choose>
												<c:when test="${postData.noticeYn eq 'Y'}">
													<tr class="table-active black" style="cursor: pointer;" onclick="location.href='${detailUrl}'">
														<td><input type="checkbox"></td>
														<td><i class="bi bi-megaphone-fill"></i></td>
														<td>${postData.postTitle}</td>
														<td>${postData.writer}</td>
														<td>${postData.addDate}</td>
														<td>${postData.viewCnt}</td>
														<td>${postData.likeCnt}</td>
													</tr>
												</c:when>
												<c:otherwise>
													<tr class="table-active"  style="cursor: pointer;" onclick="location.href='${detailUrl}'">
														<td><input type="checkbox"></td>
														<td><i class="bi bi-megaphone-fill"></i></td>
														<td>${postData.postTitle}</td>
														<td>${postData.writer}</td>
														<td>${postData.addDate}</td>
														<td>${postData.viewCnt}</td>
														<td>${postData.likeCnt}</td>
													</tr>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</section>
		</div>
		<!-- footer -->
        <%@ include file="../module/footer.jsp" %>
      </div>
    </div>
    <script src="static/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="static/js/bootstrap.bundle.min.js"></script>

    <script src="static/vendors/apexcharts/apexcharts.js"></script>
    <script src="static/js/pages/dashboard.js"></script>

    <script src="static/js/main.js"></script>
</body>
</html>