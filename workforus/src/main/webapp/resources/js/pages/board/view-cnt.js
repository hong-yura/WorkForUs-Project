$(document).ready(function(){
    // postId만 전달해주면 됨
    const postId = $("#postId")[0].value;
    const viewCnt = $("#view-cnt")[0].innerText.trim();
    const view = $("#view-cnt")[0];

    console.log(postId);
    console.log(viewCnt);

    $.ajax({
        type: "PATCH",
        url : "/post/view",
        data : JSON.stringify({
            "postId" : postId,
            "viewCnt" : viewCnt
        }),
        contentType : "application/json; charset=UTF-8",
        success: function(data){
            view.innerText = data.viewCnt;
        },
        error: function (){
            alert("로딩에 실패하였습니다. 다시 접속해주세요.")
        }
    });
})