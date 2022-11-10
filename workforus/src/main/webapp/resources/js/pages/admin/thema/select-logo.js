/**
 * 
 */
//수정중
function selectLogo() {
	$.ajax({
		type : "get",
		url : "/admin/thema",
		data : formData,
		processData : false,
		contentType : false,
		success : function(data) {
			logoPrevImage.src = data.logoImgPath
			alert("성공");
		}
	});
}