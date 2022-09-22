
// 댓글 토글
function showToggle(element){
 
 	// 부모의 부모의 부모의 형제 형제
	var con = element.parentElement.parentElement.parentElement.nextElementSibling.nextElementSibling;

 	if(con.style.display =="none"){
    	con.style.display="inline";
	}else{
    	con.style.display="none";
	}
 	
}
// 게시글 삭제 하기
function boardDelete(pId){
	$.ajax({
		url : "/board/post/delete", 
		type : "post", 
		data : {
			postId : pId
		},
		dataType : "json",
		success: function(data) {
			var myModal = new bootstrap.Modal(document.getElementById("resultModal"), {
				keyboard: false // ajax가 전달되기 전에 사용자가 모달을 닫는 것을 방지하기 위함
			});
			
			var title = myModal._element.querySelector(".modal-title");
			var body = myModal._element.querySelector(".modal-body");
			title.innerText = "성공";
			body.innerHTML = "<p>" + "게시글이 삭제되었습니다." + "</p>"
				
			myModal.show();
		}
	});
}

// 본댓글 삭제하기
function commentDelete1(element) { // 삭제 버튼 요소
	// 삭제 버튼을 누르면 commentId 가 있어야 한다. commentId 를 가져오기 위해서는? 
	var commentId = element.parentElement.previousElementSibling.previousElementSibling.value;
	var allDelete = element.parentElement.parentElement.parentElement.parentElement.parentElement; // 본댓, 대댓 다 없애기 
	var postId = element.nextElementSibling.value;
	console.log(postId);
	var commentCnt = document.getElementsByClassName('comment-count')[0];
	
	$.ajax({
		url: "/board/comment/delete",
		type: "post",
		data: {
			commentId: commentId,
			postId: postId
		},
		dataType:"json",
		success: function(data) {
			// 서버에 데이터 전송 후 삭제 성공 하면 화면 상에서도 삭제.
			if(data.code === "error") {
				alert("삭제 실패");
			}else {
				allDelete.remove();
				commentCnt.innerHTML='댓글 ' + data.commentCnt + '개';
			}
		}
	});
}

// 대댓 삭제하기
function commentDelete2(element) { // 삭제 버튼 요소
	// 삭제 버튼을 누르면 commentId 가 있어야 한다. commentId 를 가져오기 위해서는? 
	var commentId = element.parentElement.previousElementSibling.previousElementSibling.value;
	var allDelete = element.parentElement.parentElement.parentElement.parentElement; // 본댓, 대댓 다 없애기 
	var postId = element.nextElementSibling.value;
	var commentCnt = document.getElementsByClassName('comment-count')[0];

	$.ajax({
		url: "/board/comment/delete",
		type: "post",
		data: {
			commentId: commentId,
			postId: postId
		},
		dataType:"json",
		success: function(data) {
			// 서버에 데이터 전송 후 삭제 성공 하면 화면 상에서도 삭제.
			if(data.code === "error") {
				alert("삭제 실패");
			}else{
				allDelete.remove();
				commentCnt.innerHTML='댓글 ' + data.commentCnt + '개';
			}
		}
	});
}

// 댓글 ajax 구현 x
function addComment(element, groupNo, depth, postId){
	// var postId = document.getElementById("postId").value;
	var content = element.previousElementSibling.innerText;
	
	console.log(depth);
	console.log(element);
	console.log(postId);
	console.log(groupNo);
	$.ajax({
		url: "/board/comment/add",
		type: "post",
		dataType: "json",
		data: {
			depth : depth,
			groupNo : groupNo,
			content : content,
			postId : postId
		},
		success : function(data){
			if(data.code === "success"){
				alert(data.message);
			}else {
				alert("저장에 실패하였습니다.");
			}
		},
		error : function(data){
			alert("저장에 실패하였습니다.");
		}
	});
}

