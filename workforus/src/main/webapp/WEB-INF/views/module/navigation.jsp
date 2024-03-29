<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<header class='mb-3'>
	<nav class="navbar navbar-expand navbar-light navbar-top">
		<div class="container-fluid">

			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto mb-lg-0">
					<li class="nav-item dropdown me-1">
					<a class="nav-link active dropdown-toggle text-gray-600" href="#"
						data-bs-toggle="dropdown" aria-expanded="false">
						<i class='bi bi-envelope bi-sub fs-4'></i>
					</a>
						<ul class="dropdown-menu dropdown-menu-end"
							aria-labelledby="dropdownMenuButton">
							<li>
								<h6 class="dropdown-header">Mail</h6>
							</li>
							<li>
								<a class="dropdown-item" href="${homeUrl}/mail/mailWrite">new mail</a>
							</li>
						</ul>
					</li>
					<li class="nav-item dropdown me-3">
					<a class="nav-link active dropdown-toggle text-gray-600" href="#"
						data-bs-toggle="dropdown" data-bs-display="static" aria-expanded="false" >
						<i class='bi bi-bell bi-sub fs-4'></i>
					</a>
						<ul class="dropdown-menu dropdown-menu-end notification-dropdown"
							aria-labelledby="dropdownMenuButton" >
							<li class="dropdown-header">
								<h6>Notifications</h6>
							</li>
							<li class="dropdown-item notification-item">
							<a class="d-flex align-items-center" href="${homeUrl}/mail">
								<div class="notification-icon bg-danger">
									<c:choose>
										<c:when test="${cntMail > 99}">
											<p id="mailNum" style="margin-top:7px;">99+</p>
										</c:when>
										<c:otherwise>
											<p id="mailNum" style="margin-top:7px;">${cntMail}</p>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="notification-text ms-4">
									<p class="notification-title font-bold">Unread Mail</p>
									<p class="notification-subtitle font-thin text-sm">Please Reply as soon as posiible</p>
								</div>
							</a>
							</li>
							<li class="dropdown-item notification-item">
							<a class="d-flex align-items-center" href="#">
								<div class="notification-icon bg-success">
									<p style="margin-top:7px;">0</p>
								</div>
								<div class="notification-text ms-4">
									<p class="notification-title font-bold">Application for approval</p>
									<p class="notification-subtitle font-thin text-sm">application...</p>
								</div>
							</a>
							</li>
							<li>
								<p class="text-center py-2 mb-0">
									<a href="#">See all notification</a>
								</p>
							</li>
						</ul>
					</li>
				</ul>
				<div class="dropdown">
					<a href="#" data-bs-toggle="dropdown" aria-expanded="false">
						<div class="user-menu d-flex">
							<div class="user-name text-end me-3">
								<h6 class="mb-0 text-gray-600">${empNm}</h6>
								<p class="mb-0 text-sm text-gray-600">${deptNm}</p>
							</div>
							<div class="user-img d-flex align-items-center">
								<div class="avatar avatar-md">
									<img src="${staticUrl}/images/faces/1.jpg">
								</div>
							</div>
						</div>
					</a>
					<ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton" style="min-width: 11rem;">
						<li>
							<h6 class="dropdown-header">Hello, ${empNm}!</h6>
						</li>
						<li>
							<a class="dropdown-item" href="${homrUrl}/info">
								<i class="icon-mid bi bi-person me-2"></i> My Profile
							</a>
						</li>
						<li>
							<hr class="dropdown-divider">
						</li>
						<li>
							<a class="dropdown-item" href="${homeUrl}/logout">
								<i class="icon-mid bi bi-box-arrow-left me-2"></i> Logout
							</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</nav>
</header>
<!-- header End -->

<!-- nav Start -->
<nav>
<!-- 아이콘 메뉴 Start -->
<div class="mb-3 d-block d-xl">
    <div id="sidebar2">
      <div class="sidebar2-wrapper">
        <div class="sidebar2-header">
        <c:url var="homeUrl" value=".." />
          <a href="#" class="burger-btn d-block d-xl">
            <i class="bi bi-justify fs-3"></i>
          </a>
        </div>
        <div class="sidebar2-menu">
          <ul class="menu">
            <li class="sidebar2-item">
              <a href="#">
              	<img src="${staticUrl}/images/logo/logo1.png" alt="Logo" style="width: 2%; height: auto; margin-left: -0.2rem"/>
              </a>
            </li>

            <li class="sidebar2-item" title="My Page">
              <a href="#" class="sidebar2-link">
                <i class="bi bi-person-square"></i>
              </a>
            </li>

            <li class="sidebar2-item" title="Mail">
              <a href="${homeUrl}/mail" class="sidebar2-link">
                <i class="bi bi-envelope"></i>
              </a>
            </li>

            <li class="sidebar2-item" title="Address">
              <a href="${homeUrl}/address" class="sidebar2-link">
                <i class="bi bi-journals"></i>
              </a>
            </li>

            <li class="sidebar2-item" title="Calendar">
              <a href="${homeUrl}/calendar" class="sidebar2-link">
                <i class="bi bi-calendar-check"></i>
              </a>
            </li>

            <li class="sidebar2-item" title="Chat">
              <a href="#" class="sidebar2-link">
                <i class="bi bi-chat-dots"></i>
              </a>
            </li>
            <li class="sidebar2-item" title="Documents">
              <a href="#" class="sidebar2-link">
                <i class="bi bi-file-text"></i>
              </a>
            </li>

            <li class="sidebar2-item" title="Community">
              <a href="#" class="sidebar2-link">
                <i class="bi bi-chat-left-text"></i>
              </a>
            </li>

            <li class="sidebar2-item" title="Survey">
              <a href="#" class="sidebar2-link">
                <i class="bi bi-clipboard-data"></i>
              </a>
            </li>
            
            <li class="sidebar2-item" title="Admin">
              <a href="${homeUrl}/admin/thema" class="sidebar2-link">
                <i class="bi bi-gear-fill"></i>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
<!-- 아이콘 메뉴 End -->

<!-- 메인 메뉴 Start -->
  <div id="sidebar" class="active">
    <div class="sidebar-wrapper active">
      <div class="sidebar-header">
        <div class="d-flex justify-content-between">
          <div class="logo">
            <a href="${homeUrl}">
            	<%-- <c:url var="logoImgUrl" value="${logoImgPath}" /> --%>
            	<%-- <img id="logoImage" src="${logoImgUrl}" alt="Logo" style="max-width: 70%; height: auto" /> --%>
            	<img src="${staticUrl}/images/logo/logo-main.png" alt="Logo" style="max-width: 70%; height: auto" />
            </a>
          </div>
          <div class="toggler">
            <a href="#" class="sidebar-hide"
              ><i class="bi bi-x bi-middle"></i
            ></a>
          </div>
        </div>
      </div>
      <div class="sidebar-menu">
        <ul class="menu">
          <li class="sidebar-title">Menu</li>

          <li class="sidebar-item active">
            <a href="${homeUrl}" class="sidebar-link">
              <i class="bi bi-grid-fill"></i>
              <span>Home</span>
            </a>
          </li>
          <li class="sidebar-item has-sub">
            <a href="#" class="sidebar-link">
              <i class="bi bi-person-square"></i>
              <span>My Page</span>
            </a>
            <ul class="submenu">
              <li class="submenu-item">
                <a href="${homeUrl}/info">개인정보수정</a>
              </li>
              <li class="submenu-item">
                <a href="${homeUrl}/work">근태관리</a>
              </li>
              <li class="submenu-item">
                <a href="${homeUrl}/salary">월급조회</a>
              </li>
            </ul>
          </li>

          <li class="sidebar-item has-sub">
            <a href="#" class="sidebar-link">
              <i class="bi bi-envelope"></i>
              <span>Mail</span>
            </a>
            <ul class="submenu">
              <li class="submenu-item">
                <a href="${homeUrl}/mail">받은메일함</a>
              </li>
              <li class="submenu-item">
                <a href="${homeUrl}/mail/mailSend">보낸메일함</a>
              </li>
            </ul>
          </li>

          <li class="sidebar-item has-sub">
            <a href="${homeUrl}" class="sidebar-link">
              <i class="bi bi-journals"></i>
              <span>Address</span>
            </a>
            <ul class="submenu">
              <li class="submenu-item">
                <a href="${homeUrl}/address">공용 주소록</a>
              </li>
              <li class="submenu-item">
                <a href="${homeUrl}/address/department">부서 주소록</a>
              </li>
              <li class="submenu-item">
                <a href="${homeUrl}/address/personal">개인 주소록</a>
              </li>
            </ul>
          </li>

          <li class="sidebar-item">
            <a href="${homeUrl}/calendar" class="sidebar-link">
              <i class="bi bi-calendar-check"></i>
              <span>Calendar</span>
            </a>
          </li>

          <li class="sidebar-item">
            <a href="${homeUrl}/chat" class="sidebar-link">
              <i class="bi bi-chat-dots"></i>
              <span>Chat</span>
            </a>
          </li>

          <li class="sidebar-item has-sub" hidden>
            <a href="#" class="sidebar-link">
              <i class="bi bi-file-text"></i>
              <span>Documents</span>
            </a>
            <ul class="submenu">
              <li class="submenu-item">
                <a href="#l">#1</a>
              </li>
            </ul>
          </li>

          <li class="sidebar-item has-sub">
            <a href="#" class="sidebar-link">
              <i class="bi bi-chat-left-text"></i>
              <span>Community</span>
            </a>
            <ul class="submenu">
              <li class="submenu-item submenu2">
                <span style="font-size: 14px;">전사 게시판</span>
                <hr style="margin: 0px; border: 1px;">
                <ul id="submenu-item2" style="list-style: none;">
                	<c:forEach items="${sessionScope.boardList}" var="board">
                		<c:if test="${board.deptNo == 0}">
		                	<li>
		                		<a href="${homeUrl}/board?bId=${board.boardId}">${board.boardNm}</a>
		                	</li>
                		</c:if>
                	</c:forEach>
                </ul>
              </li>
              <li class="submenu-item">
                <span style="font-size: 14px;">부서 게시판</span>
                <hr style="margin: 0px; border: 1px;">
                <ul id="submenu-item2" style="list-style: none;">
                	<c:forEach items="${sessionScope.boardList}" var="board">
                		<c:if test="${board.deptNo > 0}">
		                	<li>
		                		<a href="${homeUrl}/board?bId=${board.boardId}">${board.boardNm}</a>
		                	</li>
                		</c:if>
                	</c:forEach>
                </ul>
              </li>
              </li>
            </ul>
          </li>

          <li class="sidebar-item has-sub">
            <a href="#" class="sidebar-link">
              <i class="bi bi-clipboard-data"></i>
              <span>Survey</span>
            </a>
            <ul class="submenu">
              <li class="submenu-item">
                <a href="${homeUrl}/survey">설문조사</a>
              </li>
            </ul>
          </li>
          
          <li class="sidebar-item has-sub">
            <a href="${homeUrl}" class="sidebar-link">
              <i class="bi bi-gear-fill"></i>
              <span>Admin</span>
            </a>
            <ul class="submenu">
              <li class="submenu-item">
                <a href="${homeUrl}/admin/thema">테마 설정</a>
              </li>
              <li class="submenu-item">
                <a href="${homeUrl}/admin/dept-manage">조직도 관리</a>
              </li>
              <li class="submenu-item">
                <a href="${homeUrl}/admin/job-list">직급 관리</a>
              </li>
            </ul>
          </li>
          
        </ul>
      </div>
      <button class="sidebar-toggler btn x">
        <i data-feather="x"></i>
      </button>
    </div>
  </div>
<!-- 메인 메뉴 End -->
  <!-- sidebar2 -->
</nav>