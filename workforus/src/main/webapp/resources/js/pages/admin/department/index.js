/* 		// 에러 체크
		var errorCheckModal = new bootstrap.Modal(document.getElementById("errorModal"), {
			keyboard: false
		})
		errorCheckModal.show();
*/

// 부서 정보 상세 조회
function deptDetail(deptNo) {
	$.ajax({
		type: "get",
		url: "/admin/dept_detail",
		data: {
			deptNo: deptNo
		},
		success: function(data) {
			var form = document.getElementById("deptForm");
			
			form.deptNo.value = data.deptNo;
			form.deptName.value = data.deptName;
			form.deptMngId.value = data.deptMngId;
			form.deptAddDt.value = data.deptAddDt;
			form.deptModDt.value = data.deptModDt;
		}
	})
}
		

		

		

