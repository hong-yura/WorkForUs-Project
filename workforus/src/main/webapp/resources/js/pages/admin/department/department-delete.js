/**
 * 부서 삭제
 */
 
/*
const deptDelete = () => {
	const deptNo = $("input[name=deptNo]").val()
	
	const data = {"deptNo": deptNo}
	$.ajax({
		type: "DELETE",
		url: "/admin/dept_delete",
		data: JSON.stringify(data),
		contentType: "application/json; charset=UTF-8",
		success: function(data) {
			const resModal = new bootstrap.Modal(document.getElementById("resultModal"), {
				keyboard: false
			});
			
			const title = resModal._element.querySelector(".modal-title");
			const body = resModal._element.querySelector(".modal-body");
			title.innerText = data.result;
			body.innerHTML = "<p>" + data.message + "</p>"
			
			resModal.show();
		},
		error: function() {
			alert("부서 삭제 실패");
		}
	})
}
*/


const deptDelete = () => {
	$.ajax({
		type: "post",
		url: "/admin/dept_delete",
		data: {
			deptNo: deptForm.deptNo.value
		},
		success: function(data) {
			const resModal = new bootstrap.Modal(document.getElementById("resultModal"), {
				keyboard: false
			});
			
			const title = resModal._element.querySelector(".modal-title");
			const body = resModal._element.querySelector(".modal-body");
			title.innerText = data.result;
			body.innerHTML = "<p>" + data.message + "</p>"
			
			resModal.show();
		},
		error: function() {
			alert("부서 삭제 실패");
		}
	})
}
