/**
 * 부서 삭제
 */

const deptDelete = () => {
	$.ajax({
		type: "post",
		url: "/admin/dept_delete",
		data: {
			deptNo: deptForm.deptNo.value
		},
		dataType: "json",
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
			alert("부서 삭제 실패");
		}
	})
}