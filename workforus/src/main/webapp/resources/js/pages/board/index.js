
function showToggle(e){
 
 	var fir = e.target.parentElement.parentElement.parentElement;
 	var sec = fir.nextElementSibling;
 	
 	if(sec.style.display='none'){
    	sec.style.display='inline';
	}else{
    	sec.style.display='none';
	}
 	
}

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
function addSecComment(element, groupNo, depth, postId){
	//var postId = document.getElementById("postId").value;
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
		}
		success : function(data){
			var commentDto = data.commentDto;
			
		},
		error : function(data){
			alert("저장에 실패하였습니다.");
		}
	});
}
