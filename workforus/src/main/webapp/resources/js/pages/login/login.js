let mainText = document.querySelector(".login-layout")
let logo = document.querySelector(".logo-photo")

window.addEventListener('scroll', function() {
	let value = window.scrollY;
	
	if(value > 250) {
		mainText.style.animation = "disappear 1s ease-out forwards";
	} else {
		mainText.style.animation = "slide 1s ease-out";
	}
	console.log(value);
	
	if(value < 1200) {
		logo.style.animation = "disappear_logo 1s ease-out forwards";
	} else {
		logo.style.animation = "slide_logo 1s ease-out";
	}
})