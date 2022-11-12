/*
좋아요
 */
const boardLike = () => {
    const postId = $("#postId").val();
    const likeCnt = $("#id_like");
    console.log(postId);

    $.ajax({
        type: "PUT",
        url: "/post/like",
        data: JSON.stringify({"postId":postId}),
        contentType:"application/json; charset=UTF-8",
        success: function(data){
            console.log(data);
            console.log(likeCnt);

        },
        error: function (){
            alert("다시 시도해주세요.");
        }
    });
}