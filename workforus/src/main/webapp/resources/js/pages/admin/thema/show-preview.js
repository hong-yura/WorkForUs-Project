/**
 * 
 */

const input = document.querySelector("#imageFile")
const image = document.querySelector("#logoPrevImage")
const handleFile = (files) => {
	const logoImageUrl = URL.createObjectURL(files[0])
	image.src = logoImageUrl
	console.log(image.src)
	image.onloade = () => {
		URL.revokeObjectURL(image.src)
	}
}
input.addEventListener("change", (e) => handleFile(e.target.files))
