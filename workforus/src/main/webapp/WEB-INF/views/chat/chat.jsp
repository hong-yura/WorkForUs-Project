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
    <title>Dashboard - Mazer Admin Dashboard</title>
    <%@ include file="../module/header.jsp" %>
    <link rel="stylesheet" href="${staticUrl}/css/pages/chat.css">
	<link rel="stylesheet" href="${staticUrl}/css/widgets/chat.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</head>
<style>
.search-icon {
	width: 14%;
	padding: 5px 0 0 0;
	border-radius: 5px;
	background-color: #5a8dee;
	border: #5a8dee;
}
ul{
	list-style: none;
	padding: 10px 10px 10px 10px;
	font-size: 17px;
	color: black;
	margin: 0px;
}
.chat-plus-icon {
	position: absolute;
	font-size: 50px;
	top: 680px;
	left: 10px;
	color: #5a8dee;
	cursor: pointer;
}
</style>
<body>
    <%@ include file="../module/navigation.jsp" %>
    <c:url var="chatUrl" value="/chat" />
    <div id="app">
        <div id="main">
            <div class="page-heading">
                <h3>chat</h3>
            </div>
            <div class="chat-container">
            	<div class="chat-left-layout">
            	<div class="div-search">
            		<input type="text" class="form-control" />
            		<button class="btn btn-secondary search-icon"><i class="bi bi-search"></i></button>
            	</div>
            	<div>
	            	<div class="table-responsive">
	            		<table class="table table-lg">
		            		<c:if test="${not empty chatRoomDatas}">
			            		<c:forEach items="${chatRoomDatas}" var="data">
			            			<tr class="chat-room">
			            				<td class="chat-room-profile">
			            					<div class="avatar bg-warning me-3">
			            						<span class="avatar-content">AS</span>
			            					</div>
			            				</td>
			            				<td>
				            				<div class="chat-room-title">
				            					${data.chatTitle}
				            				</div>
				            				<div class="chat-room-content">
					            				${data.chatLastCont}
			            					</div>
			            				</td>
			            			</tr>
			            		</c:forEach>
		            		</c:if>
	            		</table>
	            		<i class="bi bi-plus-circle-fill chat-plus-icon" onclick="chatRoomAddModal()"></i>
	            	</div>
            	</div>
            	
            	<!-- 채팅방 추가 모달 -->
	           	<div class="modal fade" id="chatRoomAddModal" tabindex="-1" aria-labelledby="errorModalLabel">
					<div class="modal-dialog  modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="errorModalLabel">채팅방 생성</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<div>
									<label for="id_empName">채팅방에 추가할 멤버 검색</label>
									<div class="chat-member">
										<div class="chat-member-search">
											<input type="text" class="form-control" id="id_empName" name="empNm" onkeyup="filter()" placeholder="">
										</div>
										<div class="table-responsive" style="height: 400px">
											<table class="table mb-0 table-lg">
												<c:if test="${not empty empDtoDatas}"> 
												<c:forEach items="${empDtoDatas}" var="data">
													<tr class="empNm_list">
														<td class="empNm_data" id="dataEmpNm" onclick="addChatMember(this);">${data.empNm}</td>
													</tr>
												</c:forEach>
												</c:if>
											</table>
										</div>
									</div>
								</div>
								<div>
									<label for="id_empMember" id="add-member">추가한 멤버</label>
									<div class="table-responsive" style="height: 200px">
										<table class="table mb-0 table-lg">
										</table>
									</div>
								</div>
		      				</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" onclick="chatRoomInsert('${loginEmp.getUsername()}');" data-bs-dismiss="modal">생성</button>
							</div>
						</div>
					</div>
	           	</div>
	          	</div>
            	
            	<!-- 채팅방 생성 알림 모달 -->
            	<div class="modal fade" tabindex="-1" id="resultModal">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">채팅방 생성</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<p>채팅방이 생성되었습니다.</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="location.href='${chatUrl}'">확인</button>
							</div>
						</div>
					</div>
				</div>
            	
            	<div class="chat-center-layout">
	                <section class="section">
		                <div class="card">
		                    <div class="card-header">
		                        <div class="media d-flex align-items-center">
		                            <div class="avatar me-3">
		                                <img src="static/images/faces/1.jpg" alt="" srcset="">
		                                <span class="avatar-status bg-success"></span>
		                            </div>
		                            <div class="name flex-grow-1">
		                                <h6 class="mb-0">Fred</h6>
		                                <span class="text-xs">Online</span>
		                            </div>
		                            <button class="btn btn-sm">
		                                <i data-feather="x"></i>
		                            </button>
		                        </div>
		                    </div>
		                    <div class="card-body pt-4 bg-grey" id="id_chat">
		                    	<!--  
		                        <div class="chat-content">
		                            <div class="chat">
		                                <div class="chat-body">
		                                    <div class="chat-message" id="id_chat"></div>
		                                </div>
		                            </div>
		                            <div class="chat chat-left">
		                                <div class="chat-body">
		                                    <div class="chat-message">I'm looking for the best admin dashboard
		                                        template</div>
		                                    <div class="chat-message">With bootstrap certainly</div>
		                                </div>
		                            </div>
		                        </div>
		                        -->
		                    </div>
		                    <div class="card-footer">
		                    	<form onsubmit="return sendMessage(this.context);">
		                        <div class="message-form d-flex flex-direction-column align-items-center">
		                            <a href="http://" class="black"><i data-feather="smile"></i></a>
		                            <div class="d-flex flex-grow-1 ml-4">
		                                <input type="text" class="form-control" id="id_context" name="context" placeholder="Type your message..">
		                                <button type="submit" class="submit-btn">전송</button>
		                            </div>
		                        </div>
		                        </form>
		                    </div>
		                </div>
	          		</section>
            	</div>
            	<div class="chat-right-layout">
            		<div class="div-search">
	            		<input type="text" class="form-control" />
	            		<button class="btn btn-secondary search-icon"><i class="bi bi-search"></i></button>
            		</div>
            		<div class="sidebar-menu">
        				<ul class="menu sub-menu">
            				<li class="sidebar-item has-sub">
				            	<a href="#" class="sidebar-link">
				              		<span>대화 상대</span>
				            	</a>
				            	<ul class="submenu">
				              		<li class="submenu-item sub-list">
				                		<a href="#">1</a>
				              		</li>
				              		<li class="submenu-item sub-list">
				                		<a href="#">2</a>
				              		</li>
				            	</ul>
				            </li>
						</ul>
          			</div>
          			<div class="sidebar-menu">
        				<ul class="menu sub-menu">
            				<li class="sidebar-item has-sub">
				            	<a href="#" class="sidebar-link">
				              		<span>이미지 파일</span>
				            	</a>
				            	<ul class="submenu">
				              		<li class="submenu-item sub-list">
				                		<a href="#">1</a>
				              		</li>
				              		<li class="submenu-item sub-list">
				                		<a href="#">2</a>
				              		</li>
				            	</ul>
				            </li>
						</ul>
          			</div>
          			<div class="sidebar-menu">
        				<ul class="menu sub-menu">
            				<li class="sidebar-item has-sub">
				            	<a href="#" class="sidebar-link">
				              		<span>첨부 파일</span>
				            	</a>
				            	<ul class="submenu">
				              		<li class="submenu-item sub-list">
				                		<a href="#">1</a>
				              		</li>
				              		<li class="submenu-item sub-list">
				                		<a href="#">2</a>
				              		</li>
				            	</ul>
				            </li>
						</ul>
          			</div>
          			<div class="sidebar-menu">
        				<ul class="menu sub-menu">
            				<li class="sidebar-item has-sub">
				            	<a href="#" class="sidebar-link">
				              		<span>URL 링크</span>
				            	</a>
				            	<ul class="submenu">
				              		<li class="submenu-item sub-list">
				                		<a href="#">1</a>
				              		</li>
				              		<li class="submenu-item sub-list">
				                		<a href="#">2</a>
				              		</li>
				            	</ul>
				            </li>
						</ul>
          			</div>
            	</div>
            </div>
            </div>
            <%@ include file="../module/footer.jsp" %>
        </div>
</body>
<script src="${staticUrl}/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
<script src="${staticUrl}/js/bootstrap.bundle.min.js"></script>
<script src="${staticUrl}/js/main.js"></script>
<script type="text/javascript">
var ws = new WebSocket("ws://localhost/chat/socket");
ws.onopen = function() {
	console.log("채팅 입장");
};
ws.onmessage = function(data) {
	console.log(data);
	id_chat.innerText += data.data;
	id_chat.scrollTo(0, id_chat.scrollHeight);
};
ws.onclose = function() {
	console.log("채팅 퇴장");
};
function sendMessage(element) {
	value = element.value;
	element.value = "";
	ws.send(value);
	element.focus();
	return false;
};


function chatRoomAddModal() {
	var modal = new bootstrap.Modal(document.getElementById("chatRoomAddModal"), {
		keyboard: false
	});
	
	modal.show();
}

function filter() {
	let search = document.getElementById("id_empName").value.toLowerCase();
	let listInner = document.getElementsByClassName("empNm_list");
	
	for(let i = 0; i < listInner.length; i++) {
		let empNm = listInner[i].getElementsByClassName("empNm_data");
		if(empNm[0].innerHTML.toLowerCase().includes(search)) {
			listInner[i].style.display = "flex";
			empNm[0].addEventListener("click", function() {
				addChatMember(empNm[0]);
			})
		} else {
			listInner[i].style.display = "none";
		}
	}
	
}

function addChatMember(data) {
	var text = data.innerText;
	text = text.replace(/\n/g, "");
	text = text.replace(/\r/g, "");
	text = text.replace(/\t/g, "");
	var chatMember = document.getElementsByClassName("table")[2];
	
	for(i=0; i < document.getElementsByClassName("table")[2].childElementCount; i++) {
		var chatMemberList = document.getElementsByClassName("table")[2].children[i];
		if(chatMemberList.innerText.includes(text)) {
			return false;
		}
	}
	chatMember.innerHTML += "<tr><td>" + text + "<td><tr>";
}
function chatRoomInsert(loginEmpId) {
	console.log(loginEmpId);
	$.ajax ({
		url: "${chatUrl}/room/add",
		type: "post",
		data: {
			id: loginEmpId
		},
		dataType: "json",
		success: function(data) {
			var myModal = new bootstrap.Modal(document.getElementById("resultModal"), {
				keyboard: false
			});
				
			myModal.show();
		}
	})
}
</script>
</html>