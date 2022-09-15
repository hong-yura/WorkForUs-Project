function ok_check() {
	var id = document.getElementById('id').value;
	var pw = document.getElementById('pw').value;
	var number = pw.search(/[0-9]/g);
	var english = pw.search(/[a-z]/ig);
	var spece = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
	var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
	
	if(pw.length < 8 || pw. length > 20) {
		document.getElementById('okCheck').innerText = '8자리 ~ 20 자리 이내로 입력해주세요.';
		document.getElementById('okCheck').style.color = '#FF0000';
		return false;
	} else if (pw.search(/\s/) != -1) {
		document.getElementById('okCheck').innerText = '비밀번호는 공백 없이 입력해주세요.';
		document.getElementById('okCheck').style.color = '#FF0000';
		return false;
	} else if (number < 0 || english < 0 || spece < 0) {
		document.getElementById('okCheck').innerText = '영문, 숫자, 특수문자를 혼합하여 입력해주세요.';
		document.getElementById('okCheck').style.color = '#FF0000';
		return false;
	} else if ((number < 0 && english < 0) || (english < 0 && spece < 0) || (spece < 0 && number < 0)) {
		document.getElementById('okCheck').innerText = '영문, 숫자, 특수문자 중 2 가지 이상을 혼합하여 입력해주세요.';
		document.getElementById('okCheck').style.color = '#FF0000';
		return false;
	} else if (/(\w)\1\1\1/.test(pw)) {
		document.getElementById('okCheck').innerText = '같은 문자를 4번 이상 사용하실 수 없습니다.';
		document.getElementById('okCheck').style.color = '#FF0000';
		return false;
	} else if(pw.search(id) > -1) {
		document.getElementById('okCheck').innerText = '비밀번호에 아이디가 포함되었습니다.';
		document.getElementById('okCheck').style.color = '#FF0000';
		return false;
	} else {
		document.getElementById('okCheck').innerText = '';
		return true;
	}
	
	if(false === reg.test(pw)) {
		document.getElementById('okCheck').innerText = '비밀번호는 8자 이상이어야 하며, 숫자/대문자/소문자/특수문자를 모두 포함해야 합니다.';
		document.getElementById('okCheck').style.color = '#FF0000';
		return false;
	} else {
		document.getElementById('okCheck').innerText = '';
		return true;
	}
}

function check_pw() {
	var pw = document.getElementById('pw').value;
	var pwCheck = document.getElementById('pwCheck').value;
	
	if(pw != pwCheck) {
		document.getElementById('checkPw').innerText = '비밀번호가 일치하지 않습니다';
		document.getElementById('checkPw').style.color = '#FF0000';
		return false;
	} else {
		document.getElementById('okCheck').innerText = '';
		return true;
	}
}


function check_email() {
	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var checkEmail = document.getElementById('checkEmail');
	var email = document.getElementById('email_id').value;
	
	if(re.test(email)) {
		document.getElementById('okCheck').innerText = '';
		return true;
	} else {
		checkEmail.innerText = '적합하지 못한 이메일 형식입니다.';
		checkEmail.style.color = '#FF0000';
		return false;
	}
	
}

function check_assist_email() {
	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var checkAssistEmail = document.getElementById('checkAssistEmail');
	var emailAssist = document.getElementById('email_assist_id').value;
	
	if(re.test(emailAssist)) {
		document.getElementById('okCheck').innerText = '';
		return true;
	} else {
		checkAssistEmail.innerText = '적합하지 못한 이메일 형식입니다.';
		checkAssistEmail.style.color = '#FF0000';
		return false;
	}
}

function ok_signup_check() {
	var empId = document.getElementById('id');
	var empNm = document.getElementById('name');
	var pw = document.getElementById('pw');
	var pwCheck = document.getElementById('pwCheck');
	var emailId = document.getElementById('email_id');
	var emailAssistId = document.getElementById('email_assist_id');

	var modal = new bootstrap.Modal(document.getElementById("signupCompleteModal"), {
		keyboard: false
	});
	
	if(empId.value === "") {
		alert("아이디를 입력하세요.");
		empId.focus();
		return false;
	}
	
	if(empNm.value === "") {
		alert("이름을 입력하세요.");
		empNm.focus();
		return false;
	}
	
	if(pw.value === "" || pwCheck.value === "") {
		alert("비밀번호를 입력하세요.");
		pw.focus();
		return false;
	}
	
	if(ok_check() === false || check_pw() === false) {
		alert("비밀번호를 확인해주세요.");
		pw.focus();
		return false;
	}
	
	if(emailId.value === "" || emailAssistId.value === "") {
		alert("이메일을 입력헤주세요.");
		emailId.focus();
		return false;
	}
	
	if(check_email() === false || check_assist_email() === false) {
		alert("이메일을 확인해주세요.");
		emailAssistId.focus();
		return false;
	}
	modal.show();
}