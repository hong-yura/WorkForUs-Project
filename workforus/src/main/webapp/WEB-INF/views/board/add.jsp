<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>workforus - 게시글 추가</title>
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
      	<c:url value="/board/post/add" var="postAddUrl"/>
		<form action="${postAddUrl}?boardId=${boardId}" method="post" enctype="multipart/form-data" >
	      	<div class="page-heading margin-left-10">
	      		<h3>Board</h3> <!-- 게시판 이름 -->
	      	</div>
	      	<div class="write-container radius">
	      		<div class="row">
	      			<div class="col-2">
	      				<!-- 상단 -->
	      				<div class="section-left"> <!-- 목록 -->
	      					<p>제목</p>
	      					<p>첨부파일</p>
	      					<br>
	      					<p>공지여부</p>
	      				</div>
	      			</div>
	      			<div class="col-10">
	      				<input type="text" name="boardId" value="${boardId}" hidden>
	    				<input type="text" name="postTitle" class="form-control post-title" >
	      				<div class="file-controller">
	      					<input type="file" name="postFiles" id="files" class="file-input form-control" multiple>
	      				</div>
	      				<br>
	      				<input type="checkbox" name="noticeYn" value="Y">공지 등록
	      			</div>
	      		</div>
	      		<hr>
	      		<!-- 내용 입력란 - summernote 사용 -->
	      		<div class="row">
	      			<div class="col-12">
		      			<textarea id="summernote" name="content">${param.content}</textarea>
	      			</div>
	      		</div>
	      		<div class="text-end">
					<button class="btn" type="submit" name="temporaryYn" value="N" id="saveBtn">저장</button>
					<button class="btn" type="submit" name="temporaryYn" value="Y" id="saveBtn">임시 저장</button>
					<button class="btn" type="button" onclick="location.href='/board'">취소</button>
				</div>
		     </div>
		</form>
      	<!-- footer -->
        <%@ include file="../module/footer.jsp" %>
      </div>
    </div>
    
 <script type="text/javascript">
 	// 섬머노트 초기화
	$('#summernote').summernote({
		height : 300,
		minHeight: null,
		maxHeight : null,
		fcous : true,
		lang: "ko-KR",
		disableResizeEditor: true, // 크기 조절 기능 삭제
		callbacks: {
		    onImageUpload : function(files, editor, welEditable){
	
		        // 파일 업로드(다중업로드를 위해 반복문 사용)
		        for (var i = files.length - 1; i >= 0; i--) {
			         uploadSummernoteImageFile(files[i],
			         this);
		         }
	         }
         } 
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