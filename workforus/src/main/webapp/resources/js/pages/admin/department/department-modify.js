/**
 * 부서 수정
 */

function deptModify() {
	var deptNo = deptForm.deptNo.value
	var deptName = $.trim($("#deptModName").val())
	var deptMngId = $.trim($("#deptModMngId").val())
	
	if(deptName.trim().length == 0) {
		alert("부서명을 입력해주세요.");
		$("#deptModName").focus();
		return;
	}

	if(deptMngId.trim().length == 0) {
		alert("부서 책임자를 입력해주세요.");
		$("#deptModMngId").focus();
		return;
	}
	
	// if deptMngId가 null이면 참조할 객체가 없으므로 실패
	// if deptMngId가 부서명 중복인지 확인
	// 직급이 부장 이상만
	var data = {"deptNo": deptNo, "deptName": deptName, "deptMngId": deptMngId}
	
	$.ajax({
		type: "post",
		url: "/admin/dept_modify",
		data: JSON.stringify(data),
		contentType: "application/json; charset=UTF-8",
		success: function(data) {
			var resModal = new bootstrap.Modal(document.getElementById("resultModal"), {
				keyboard: false
			});
			
			var title = resModal._element.querySelector(".modal-title");
			var body = resModal._element.querySelector(".modal-body");
			title.innerText = data.title;
			body.innerHTML = "<p>" + data.message + "</p>"
			
			resModal.show();
		},
		error: function() {
			alert("부서 수정 실패");
		}
	})
}