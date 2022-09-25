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
    <%@ include file="../module/head.jsp" %>
    <link rel="stylesheet" href="${staticUrl}/css/pages/board.css">
</head>
<body>
	<%@ include file="../module/navigation.jsp" %>
	
    <div id="app">
      <div id="main">
      
    	<div class="page-heading">
			<div class="page-title">
				<div class="row">
					<div class="col-12 col-md-6 order-md-1 order-last">
						<h3>Board</h3>
					</div>
					<div class="col-12 col-md-6 order-md-2 order-first">
						<nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
							<ol class="breadcrumb">
								<li class="breadcrumb-item">
									<a href="views/home.jsp">Home</a></li> <!-- curl로 수정 -->
								<li class="breadcrumb-item active" aria-current="page">Board</li>
							</ol> 
						</nav>
					</div>
				</div>
			</div>
		</div>
		
	    <!-- board -->
      	<div class="page-content">
			<section class="row">
				<div class="col-12"> 
					<!-- 상단 -->
					<div class="section-top radius">
						<div class="info-container">
							<ul class="info-ul black" style="margin-bottom: 20px;">
								<li>게시판 주소 : https://www.workforus.work/board?bId=${boardData.boardId}
								<button class="btn" type="button" onclick="boardUrlCopy()"><i class="bi bi-clipboard-check"></i></button> 
								<input id="board-url"  value="https://www.workforus.work/board?bId=${boardData.boardId}" style="display: none;">
								</li>
								<li>운영자 : ${boardData.boardManager}</li>
							</ul>
							<c:if test="${boardData.deptNo != 0}"> 
								<hr style="margin: 0px; width: 99%">
								<div class="dropdown" >
									<a class="nav-link active dropdown-toggle text-gray-600" href="#" data-bs-toggle="dropdown" aria-expanded="false" style="color: black;">
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
							</c:if>
							
						</div>
					</div>
					<!-- 메인 -->
						<div class="section-main radius">
						<c:url value="/board" var="boardUrl"/>
						<form action="${boardUrl}?bId=${boardData.boardId}" method="get">
							<div class="main-button">
								<c:url value="/board/post/add" var="postAddUrl"/>
								<c:url value="/board/add" var="boardAddUrl"/>
								<button type="button" class="btn black" onclick="location.href='${postAddUrl}?boardId=${boardData.boardId}'" style="font-weight: bold;"><i class="bi bi-pencil"></i> 새글작성</button>
								<button type="button" class="btn black"  onclick="location.href='${boardAddUrl}'"> <i class="bi bi-plus-circle"></i> 게시판생성</button>
									<div class="search-container">
										<select class="searchType" name="searchType" style="border: solid 1px #d0d3cd; border-radius: 0.3rem;">
											  <option selected value="title">제목</option>
											  <option value="writer">작성자</option>
										</select>
										<i class="bi bi-search"> </i>
										<input class="search-input" type="search" name="search" placeholder=" 입력">
										<input class="board-id" type="text" name="bId" value="${boardData.boardId}" hidden>
										<button class="btn-style black" type="submit">검색</button>
									</div>
								<hr style="margin: 0px; margin-bottom: 3px;">
							</div>
						</form>
						<div class="dataTable-container">
							<table class="table dataTable-table black">
								 <colgroup>
								 	<col width="2%">
						            <col width=8%>
						            <col width=50%>
						            <col width=10%>
						            <col width=10%>
						            <col width=10%>
						            <col width=10%>
						        </colgroup>
								<thead>
									<tr>
										<th></th>
										<th>번호</th>
										<th>제목</th>
										<th>작성자</th>
										<th>작성일</th>
										<th>조회</th>
										<th>좋아요</th>
									</tr>
								</thead>
								<tbody>
									<!-- 첫 번째 c:foreach문으로 공지를 출력하고, 두 번째 c:foreach문으로 일반 게시글을 출력 -->
		                           <c:if test="${not empty postList}">
	                                 <c:forEach items="${postList}" var="postData">
	                                    <c:if test="${postData.noticeYn eq 'Y' && not empty postData.noticeYn}">
	                                    <c:url var="detailUrl" value="/board/detail">
	                                       <c:param name="postId" value="${postData.postId}"/>
	                                    </c:url>
	                                       <tr class="table-primary" style="cursor: pointer;"  onclick="location.href='${detailUrl}'">
	                                          <td></td>
	                                          <td><i class="bi bi-megaphone-fill" ></i></td>
	                                          <td>${postData.postTitle}</td>
	                                          <td>${postData.empObj.empNm}</td>
	                                          <td>${postData.addDate}</td>
	                                          <td>${postData.viewCnt}</td>
	                                          <td>${postData.likeCnt}</td>
	                                       </tr>
	                                    </c:if>
	                                 </c:forEach>
	                                 <c:forEach items="${notNotice}" var="notNotice" varStatus="status">
	                                    <c:url var="detailUrl" value="/board/detail">
	                                       <c:param name="postId" value="${notNotice.postId}"/>
	                                    </c:url>
	                                       <tr class="table"  style="cursor: pointer; color: black;" onclick="location.href='${detailUrl}'">
	                                          <td></td>
	                                          <td>${status.count}</td>
	                                          <td>${notNotice.postTitle}</td>
	                                          <td>${notNotice.empObj.empNm}</td>
	                                          <td>${notNotice.addDate}</td>
	                                          <td>${notNotice.viewCnt}</td>
	                                          <td>${notNotice.likeCnt}</td>
	                                       </tr>
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