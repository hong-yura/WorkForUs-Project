/**
 * 
 */

const input = document.querySelector("#imageFile");
const file = input.files[0];
const image = document.querySelector("#logoPrevImage");

const maxFileSize = 1024 * 1024 * 5.0; // 5MB
const regExp = new RegExp("(.*?)\.(jpg|jpeg|png)$"); // 정규표현식 화이트 리스트 허용

// 파일 사이즈, 확장자 체크
function checkExtension(fileName, fileSize) {
	if(fileSize >= maxFileSize) {
		alert("파일 사이즈가 초과되었습니다.");
		return false;
	}	
	
	if(!regExp.test(fileName)) {
		alert("허용되지 않는 확장자입니다.");
		return false;
	}
	return true;
}

// 미리보기
const handleFile = (file) => {
	
	const logoImageUrl = URL.createObjectURL(file[0]);
	image.src = logoImageUrl;
	console.log(image.src);
	image.onloade = () => {
		URL.revokeObjectURL(image.src);
	}
}
input.addEventListener("change", (e) => handleFile(e.target.files));


// 업로드
const logoUpload = (e) => {
	const formData = new FormData();
	const file = $("#imageFile")[0].files[0];
	console.log(file);
	
	if(!checkExtension(file.name, file.size)) {
		return false;
	}
	
	formData.append("imageFile", file);
	console.log(formData);
	
	$.ajax({
		type: "post",
		url: "/admin/logoUpload",
		enctype: "multipart/form-data", // 문자열 제외 데이터 전송할 때 작성해줘야 하는 부분
		processData: false, // 얘도 문자열 제외의 데이터 전송에 필요(파일과 같은 객체들..)
		contentType: false, // 컨텐츠 타입 유형을 명시 - false 해야 자동으로 명시해줌
		data: formData,
		success: function(data) {
			alert("logo 설정이 완료되었습니다.");
		},
		error: function(data) {
			alert("logo 설정을 실패하였습니다.");
		}
	});
}