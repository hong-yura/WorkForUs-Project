/**
 * 
 */

function logoUpload() {
	const formData = new FormData();
	const file = $("#imageFile")[0].files[0]
	formData.append("imageFile", file)
	console.log(formData)
	
	$.ajax({
		type: "post",
		url: "/admin/logoUpload",
		enctype: "multipart/form-data", // 문자열 제외 데이터 전송할 때 작성해줘야 하는 부분
		processData: false, // 얘도 문자열 제외의 데이터 전송에 필요(파일과 같은 객체들..)
		contentType: false, // 컨텐츠 타입 유형을 명시 - false 해야 자동으로 명시해줌
		data: formData,
		success: function(data) {
			alert("logo 설정이 완료되었습니다.")
		},
		error: function(data) {
			alert("logo 설정 실패")
		}
	});
}
