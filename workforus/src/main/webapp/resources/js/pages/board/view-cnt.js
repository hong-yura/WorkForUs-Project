$(document).ready(function(){
    // postId만 전달해주면 됨
    const postId = $("#postId")[0].value;
    const viewCnt = $("#view-cnt")[0];

    console.log(postId);

    $.ajax({
        type: "PATCH",
        url : "/post/view",
        data : JSON.stringify({
            "postId" : postId
        }),
        contentType : "application/json; charset=UTF-8",
        success: function(data){
            viewCnt.innerText = data.viewCnt;
        },
        error: function (){
            alert("로딩에 실패하였습니다. 다시 접속해주세요.")
        }
    });
});