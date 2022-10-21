/**
 * 부서 추가
 */

function deptAdd() {
	const deptName = $.trim($("#deptAddName").val())
	const deptMngId = $.trim($("#deptAddMngId").val())
	
	if(deptName.trim().length == 0) {
		alert("부서명을 입력해주세요.");
		$("#deptAddName").focus();
		return;
	}

	if(deptMngId.trim().length == 0) {
		alert("부서 책임자를 입력해주세요.");
		$("#deptAddMngId").focus();
		return;
	}
	
	// if deptMngId가 null이면 참조할 객체가 없으므로 실패
	// if deptMngId가 부서명 중복인지 확인
	// 직급이 부장 이상만

	const data = {"deptName": deptName, "deptMngId": deptMngId}
	
	$.ajax({
		type: "post",
		url: "/admin/dept_add",
		data: JSON.stringify(data),
		contentType: "application/json; charset=UTF-8",
		success: function(data) {
			const resModal = new bootstrap.Modal(document.getElementById("resultModal"), {
				keyboard: false
			});
			
			const title = resModal._element.querySelector(".modal-title");
			const body = resModal._element.querySelector(".modal-body");
			title.innerText = data.title;
			body.innerHTML = "<p>" + data.message + "</p>"
			
			resModal.show();
		},
		error: function() {
			alert("부서 추가 실패");
		}
	})
}