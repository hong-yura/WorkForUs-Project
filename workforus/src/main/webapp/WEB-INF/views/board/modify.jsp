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
      	<div class="page-heading margin-left-10">
      		<h3>Board</h3> <!-- 게시판 이름 -->
      	</div>
      	<div class="write-container radius">
      		<div class="row">
      			<div class="col-2">
      				<!-- 상단 -->
      				<div class="section-left"> <!-- 목록 -->
      					<p class="title-c">제목</p>
      					<p>첨부파일</p>
      				</div>
      			</div>
      			<div class="col-10">
    				<input type="text" class="form-control post-title" name="postTitle">
      				<div class="file-controller">
      					<input type="file" id="files" class="file-input form-control" name="postFile" multiple>
      					
      				</div>
      			</div>
      		</div>
      		<hr>
      		<!-- 내용 입력란 - summernote 사용 -->
      		<div class="row">
      			<div class="col-12">
      				<form action="" method="post">
	      				<textarea id="summernote" name="editordata"></textarea>
      				</form>
      			</div>
      		</div>
      	</div>
      	
      	
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
 
 </script>
</body>
</html>