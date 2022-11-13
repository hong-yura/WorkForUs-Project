/*
좋아요
 */
const boardLike = () => {
    const postId = $("#postId").val();
    const likeCnt = $("#id_like")[0];
    console.log(postId);

    $.ajax({
        type: "PUT",
        url: "/post/like",
        data: JSON.stringify({"postId":postId}),
        contentType:"application/json; charset=UTF-8",
        success: function(data, message){
            likeCnt.innerText = data.likeCnt;
        },
        error: function (){
            alert("다시 시도해주세요.");
        }
    });
}