
function showToggle(e){
 
 	var fir = e.target.parentElement.parentElement.parentElement;
 	var sec = fir.nextElementSibling;
 	
 	if(sec.style.display='none'){
    	sec.style.display='inline';
	}else{
    	sec.style.display='none';
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
			title.innerText = "오류";
			body.innerHTML = "<p>" + "삭제 실패" + "</p>"
				
			myModal.show();
		}
	});
}

// 댓글 삭제하기
function commentDelete(element) { // 삭제 버튼 요소
	var cid = element.parentElement.parentElement.firstElementChild.value;
	var card = element.parentElement.parentElement.parentElement.parentElement;
	
	$.ajax({
		url: "/board/comment/delete",
		type: "post",
		data: {
			id: cid
		},
		success: function(data) {
			// 서버에 데이터 전송 후 삭제 성공 하면 화면 상에서도 삭제.
			if(data.code === "success") {
				card.remove();
			}
		}
	})
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

