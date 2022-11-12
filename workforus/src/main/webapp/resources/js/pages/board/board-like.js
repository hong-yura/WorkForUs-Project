/*
좋아요
 */
const boardLike = () => {
    // 전달해야 할 것 : postId
    const postId = $("#postId").val();
    const likeCnt = $("#id_like");

    $.ajax({
        type: "PUT",
        url: "/post/like",
        data: JSON.stringify(postId),
        contentType: "application/json; charset=UTF-8",
        success: function(data){
            console.log(data);
            // likeCnt.innerText =
        },
        error: function (){
            alert("다시 시도해주세요.");
        }
    });
}