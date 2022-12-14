<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>workforus - ${postData.postTitle} : 수정</title>
	<!-- 부트스트랩4 추가 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  
	<%@ include file="../module/head.jsp" %> <!-- 순서는 무조건 위에  -->
 	<!-- 서머노트를 위해 추가해야할 부분 -->	
  	<script src="${staticUrl}/vendors/summernote/summernote-lite.js"></script>
  	<script src="${staticUrl}/vendors/summernote/lang/summernote-ko-KR.js"></script>
  	<link rel="stylesheet" href="${staticUrl}/vendors/summernote/summernote-lite.css">
    
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
								<li class="breadcrumb-item active" aria-current="page">글수정</li>
							</ol> 
						</nav>
					</div>
				</div>
			</div>
		</div>
		
      	<c:url value="/board/post/modify" var="modifyUrl"/>
		<form action="${modifyUrl}?postId=${postData.postId}" method="post"  enctype="multipart/form-data" >
	      	<div class="write-container radius">
	      		<div class="row">
	      			<div class="col-2">
	      				<!-- 상단 -->
	      				<div class="section-left"> <!-- 목록 -->
	      					<p class="title-c">제목</p>
	      					<p>첨부파일</p> <!-- 첨부파일은 삭제할 수 있도록 한다. -> ajax -->
	      				</div>
	      			</div>
	      			<div class="col-10">
	    				<input type="text" class="form-control post-title" name="postTitle" value="${postData.postTitle}"> 
	      				<div class="file-controller">
	      					<input type="file" id="files" class="file-input form-control" name="postFiles" multiple style="margin-bottom: 5px;">
	      					<c:if test="${not empty fileData}">
	      						<c:forEach items="${fileData}" var="file" >
			      					<input type="text" class="form-control display-inline" value="${file.fileNm}" readonly  style="background:#fff; width: 95%;">
	      							<button class="btn display-inline" type="button" onclick="deleteFile" style="margin-bottom: 3px; width: 10px; ">x</button><br>
	      						</c:forEach>
	      					</c:if>
	      				</div>
	      				<input type="checkbox" name="noticeYn" value="Y" style="margin-top: 10px;">공지 등록
	      			</div>
	      		</div>
	      		<hr>
	      		<!-- 내용 입력란 - summernote 사용 -->
	      		<div class="row">
	      			<div class="col-12">
		      			<textarea id="summernote" name="content">${postData.content}</textarea>
	      			</div>
	      		</div>
	      		<div class="text-end margin-10">
					<button class="btn btn-outline-secondary" type="submit">저장</button>
					<c:url value="/board" var="boardUrl"/>
					<button class="btn btn-outline-secondary" type="button" onclick="location.href='${boardUrl}?bId=${postData.boardId}'">취소</button>
				</div>
	      	</div>
		</form>
      	<!-- footer -->
        <%@ include file="../module/footer.jsp" %>
      </div>
    </div>
    
 <script type="text/javascript">
	$('#summernote').summernote({
		height : 300,
		minHeight: null,
		maxHeight : null,
		fcous : false,
		lang: "ko-KR"
	});

	// 이미지 파일 처리
	function uploadSummernoteImageFile(file, el) {
		data = new FormData();
		
		data.append("file", file);
		$.ajax({
			data : data,
			type : "POST",
			url : "/uploadSummernoteImageFile",
			contentType : false,
			enctype : 'multipart/form-data',
			processData : false,
			success : function(data) {
				$(el).summernote('editor.insertImage', data.url);
			}
		});
	}
	
 </script>
</body>
</html>