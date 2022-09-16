<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="kor">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Mazer Admin Dashboard</title>
	<link rel="stylesheet" href="static/css/widgets/chat.css">
    <link rel="stylesheet" href="static/css/chat.css">
    <%@ include file="../module/header.jsp" %>
</head>
<style>
	ul{
		list-style: none;
		padding: 10px 10px 10px 10px;
		font-size: 17px;
		color: black;
		margin: 0px;
	}
	
	.sub-menu {
		border: 0.5px solid;
		border-style: inherit;
	}
	.sub-list {
		padding: 10px;
	}
</style>
<body>
    <%@ include file="../module/navigation.jsp" %>
    <div id="app">
        <div id="main">
            <div class="page-heading">
                <h3>chat</h3>
            </div>
            <div class="chat-container">
            	<div class="chat-left-layout">
            	<div>
            		<input type="text" class="form-control" />
            	</div>
            	<div>
	            	<div class="table-responsive">
	            		<table class="table table-lg">
	            			<tr class="chat-room">
	            				<td class="chat-room-profile">
	            					<div class="avatar bg-warning me-3">
	            						<span class="avatar-content">AS</span>
	            					</div>
	            				</td>
	            				<td>
		            				<div class="chat-room-title">
		            					채팅방 제목
		            				</div>
		            				
		            				<div class="chat-room-content">
			            				대화내용
	            					</div>
	            				</td>
	            			</tr>
	            			<tr class="chat-room">
	            				<td class="chat-room-profile">
	            					<div class="avatar bg-warning me-3">
	            						<span class="avatar-content">AS</span>
	            					</div>
	            				</td>
	            				<td>
		            				<div class="chat-room-title">
		            					채팅방 제목
		            				</div>
		            				
		            				<div class="chat-room-content">
			            				대화내용
	            					</div>
	            				</td>
	            			</tr>
	            			<tr class="chat-room">
	            				<td class="chat-room-profile">
	            					<div class="avatar bg-warning me-3">
	            						<span class="avatar-content">AS</span>
	            					</div>
	            				</td>
	            				<td>
		            				<div class="chat-room-title">
		            					채팅방 제목
		            				</div>
		            				
		            				<div class="chat-room-content">
			            				대화내용
	            					</div>
	            				</td>
	            			</tr>
	            			<tr class="chat-room">
	            				<td class="chat-room-profile">
	            					<div class="avatar bg-warning me-3">
	            						<span class="avatar-content">AS</span>
	            					</div>
	            				</td>
	            				<td>
		            				<div class="chat-room-title">
		            					채팅방 제목
		            				</div>
		            				
		            				<div class="chat-room-content">
			            				대화내용
	            					</div>
	            				</td>
	            			</tr>
	            			<tr class="chat-room">
	            				<td class="chat-room-profile">
	            					<div class="avatar bg-warning me-3">
	            						<span class="avatar-content">AS</span>
	            					</div>
	            				</td>
	            				<td>
		            				<div class="chat-room-title">
		            					채팅방 제목
		            				</div>
		            				
		            				<div class="chat-room-content">
			            				대화내용
	            					</div>
	            				</td>
	            			</tr>
	            			<tr class="chat-room">
	            				<td class="chat-room-profile">
	            					<div class="avatar bg-warning me-3">
	            						<span class="avatar-content">AS</span>
	            					</div>
	            				</td>
	            				<td>
		            				<div class="chat-room-title">
		            					채팅방 제목
		            				</div>
		            				
		            				<div class="chat-room-content">
			            				대화내용
	            					</div>
	            				</td>
	            			</tr>
	            			<tr class="chat-room">
	            				<td class="chat-room-profile">
	            					<div class="avatar bg-warning me-3">
	            						<span class="avatar-content">AS</span>
	            					</div>
	            				</td>
	            				<td>
		            				<div class="chat-room-title">
		            					채팅방 제목
		            				</div>
		            				
		            				<div class="chat-room-content">
			            				대화내용
	            					</div>
	            				</td>
	            			</tr>
	            			<tr class="chat-room">
	            				<td class="chat-room-profile">
	            					<div class="avatar bg-warning me-3">
	            						<span class="avatar-content">AS</span>
	            					</div>
	            				</td>
	            				<td>
		            				<div class="chat-room-title">
		            					채팅방 제목
		            				</div>
		            				
		            				<div class="chat-room-content">
			            				대화내용
	            					</div>
	            				</td>
	            			</tr>
	            			<tr class="chat-room">
	            				<td class="chat-room-profile">
	            					<div class="avatar bg-warning me-3">
	            						<span class="avatar-content">AS</span>
	            					</div>
	            				</td>
	            				<td>
		            				<div class="chat-room-title">
		            					채팅방 제목
		            				</div>
		            				
		            				<div class="chat-room-content">
			            				대화내용
	            					</div>
	            				</td>
	            			</tr>
	            			<tr class="chat-room">
	            				<td class="chat-room-profile">
	            					<div class="avatar bg-warning me-3">
	            						<span class="avatar-content">AS</span>
	            					</div>
	            				</td>
	            				<td>
		            				<div class="chat-room-title">
		            					채팅방 제목
		            				</div>
		            				
		            				<div class="chat-room-content">
			            				대화내용
	            					</div>
	            				</td>
	            			</tr>
	            			<tr class="chat-room">
	            				<td class="chat-room-profile">
	            					<div class="avatar bg-warning me-3">
	            						<span class="avatar-content">AS</span>
	            					</div>
	            				</td>
	            				<td>
		            				<div class="chat-room-title">
		            					채팅방 제목
		            				</div>
		            				
		            				<div class="chat-room-content">
			            				대화내용
	            					</div>
	            				</td>
	            			</tr>
	            			<tr class="chat-room">
	            				<td class="chat-room-profile">
	            					<div class="avatar bg-warning me-3">
	            						<span class="avatar-content">AS</span>
	            					</div>
	            				</td>
	            				<td>
		            				<div class="chat-room-title">
		            					채팅방 제목
		            				</div>
		            				
		            				<div class="chat-room-content">
			            				대화내용
	            					</div>
	            				</td>
	            			</tr>
	            		</table>
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
		                    <div class="card-body pt-4 bg-grey">
		                        <div class="chat-content">
		                            <div class="chat">
		                                <div class="chat-body">
		                                    <div class="chat-message">Hi Alfy, how can i help you?</div>
		                                </div>
		                            </div>
		                            <div class="chat chat-left">
		                                <div class="chat-body">
		                                    <div class="chat-message">I'm looking for the best admin dashboard
		                                        template</div>
		                                    <div class="chat-message">With bootstrap certainly</div>
		                                </div>
		                            </div>
		                            <div class="chat">
		                                <div class="chat-body">
		                                    <div class="chat-message">I recommend you to use Mazer Dashboard</div>
		                                </div>
		                            </div>
		                            <div class="chat chat-left">
		                                <div class="chat-body">
		                                    <div class="chat-message">That"s great! I like it so much :)</div>
		                                </div>
		                            </div>
		                            <div class="chat">
		                                <div class="chat-body">
		                                    <div class="chat-message">Hi Alfy, how can i help you?</div>
		                                </div>
		                            </div>
		                            <div class="chat">
		                                <div class="chat-body">
		                                    <div class="chat-message">Hi Alfy, how can i help you?</div>
		                                </div>
		                            </div>
		                            <div class="chat chat-left">
		                                <div class="chat-body">
		                                    <div class="chat-message">That"s great! I like it so much :)</div>
		                                </div>
		                            </div>
		                            <div class="chat chat-left">
		                                <div class="chat-body">
		                                    <div class="chat-message">That"s great! I like it so much :)</div>
		                                </div>
		                            </div>
		                        </div>
		                    </div>
		                    <div class="card-footer">
		                        <div class="message-form d-flex flex-direction-column align-items-center">
		                            <a href="http://" class="black"><i data-feather="smile"></i></a>
		                            <div class="d-flex flex-grow-1 ml-4">
		                                <input type="text" class="form-control" placeholder="Type your message..">
		                                <button type="button" class="submit-btn">전송</button>
		                            </div>
		                        </div>
		                    </div>
		                </div>
	          		</section>
            	</div>
            	<div class="chat-right-layout">
            		<input type="text" class="form-control" />
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
   
    <script src="static/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script src="static/js/bootstrap.bundle.min.js"></script>
    <script src="static/js/main.js"></script>
    <script src="static/js/chat.js"></script>
</body>

</html>