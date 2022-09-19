
function showToggle(e){
 
 	var fir = e.target.parentElement.parentElement.parentElement;
 	var sec = fir.nextElementSibling;
 	
 	if(sec.style.display='none'){
    	sec.style.display='inline';
	}else{
    	sec.style.display='none';
	}
 	
}