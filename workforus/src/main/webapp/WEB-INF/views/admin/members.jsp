<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>workforus - Address</title>
		<%@ include file="../module/header.jsp" %>
		<link rel="stylesheet" href="static/css/pages/jui-ui.min.classic.css" />
		<link rel="stylesheet" href="static/css/pages/admin.css" />
		
		
		<script type="text/javascript">
			jui.ready([ "ui.tree" ], function(tree) {
			    tree_1 = tree("#tree_1.tree", {
			        root: { title: "workforus" },
			        event: {
			            select: function(node) {
			                this.select(node.index); // 선택 효과
			                alert("index(" + node.index + "), title(" + node.data.title + ")");
			            }
			        },
			        tpl: {
			            node: $("#tpl_tree").html()
			        }
			    });
			});
		</script>
	</head>
	<body>
		<%@ include file="../module/navigation.jsp" %>
		<div id="app">
			<div id="main">
			
				<div class="page-heading">
					<div class="page-title">
						<div class="row">
							<div class="col-12 col-md-6 order-md-1 order-last">
								<h3>Admin - Group Manage</h3>
							</div>
							<div class="col-12 col-md-6 order-md-2 order-first">
								<nav aria-label="breadcrumb" class="breadcrumb-header float-start float-lg-end">
									<ol class="breadcrumb">
										<li class="breadcrumb-item">
											<a href="${homeUrl}">Home</a></li>
										<li class="breadcrumb-item">
											<a href="#">Admin</a></li>
										<li class="breadcrumb-item active" aria-current="page">Group Manage</li>
									</ol> 
								</nav>
							</div>
						</div>
					</div>
				</div>
				
				<section class="section">
					<c:url var="membersUrl" value="/members" />
					<div class="row">
						<div class="col-xl-4">
							<div class="card">
								<div class="card-content">
									<div class="card-body">
									<h4 class="card-title">조직도 설계</h4>
										<!-- tree -->
										<div class="jui">
											<div class="row row-tree">
												<div class="col col-3">
													<ul id="tree_1" class="tree line">
														<li class="open root">
															<i></i><a href="#">workforus</a>
															<ul>
																<li class="open">
																	<i></i><a href="#">임원진</a>
																</li>
																<li class="open">
																	<i></i><a href="#">총무부</a>
																</li>
																<li class="open">
																	<i></i><a href="#">재무회계부</a>
																</li>
																<li class="open">
																	<i></i><a href="#">인사관리부</a>
																</li>
																<li class="open">
																	<i></i><a href="#">생산관리부</a>
																</li>
																<li class="open">
																	<i></i><a href="#">품질관리부</a>
																</li>
																<li class="open">
																	<i></i><a href="#">연구개발부</a>
																</li>
																<li class="open">
																	<i></i><a href="#">법무부</a>
																</li>
																<li class="open">
																	<i></i><a href="#">기획전략부</a>
																</li>
																<li class="open">
																	<i></i><a href="#">마케팅부</a>
																</li>
																<li class="open">
																	<i></i><a href="#">IT관리부</a>
																</li>
																<li class="open">
																	<i></i><a href="#">영업관리부</a>
																</li>
																<li class="open">
																	<i></i><a href="#">해외사업부</a>
																	<ul>
																		<li class="leaf">
																			<i></i><a href="#">Item 2.1</a>
																			<ul></ul>
																		</li>
																	</ul>
																</li>
																<li class="leaf last">
																	<i></i><a href="#">부서 대기</a>
																	<ul></ul>
																</li>
															</ul>
														</li>
													</ul>
												</div>
											</div>
										</div>
										<!-- tree End -->
									</div>
								</div>
							</div>
						</div>
						
						<div class="col-66p">
							<div class="card">
								<div class="card-content">
									<div class="card-body">
									<h4 class="card-title">부서 정보</h4>
										<c:forEach items="${deptDatas}" var="deptDatas">
											
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
						
					</div>
				
				
				</section>
				<%@ include file="../module/footer.jsp" %>
				<script id="tpl_tree" type="text/javascript">
					<li>
						<div><i></i> <!= title !></div>
					</li>
				</script>
			</div>
		</div>
		<script src="static/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
		<script src="static/js/bootstrap.bundle.min.js"></script>
		<script src="static/js/pages/admin/jui-core.min.js"></script>
		<script src="static/js/pages/admin/jui-ui.min.js"></script>
		<script src="static/js/pages/admin/tree.js"></script>
		<script src="static/js/main.js"></script>
	</body>
</html>