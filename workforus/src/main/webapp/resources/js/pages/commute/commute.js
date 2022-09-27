/**
 * 
 */
 
 	// 달(month) 이동 및 정보 가져오기
	function loadPrevNext(element, value){
		var defaultText = document.getElementById("CalendaryearMonth");
		var year1 = defaultText.innerText.substring(0,4);
		var month1 = defaultText.innerText.substring(5) - 1;
		
		var currentDate = new Date(year1, month1);

		
		var table = value.parentElement.parentElement.nextElementSibling;
		
		if(element == 0) {
			 currentDate.setMonth(currentDate.getMonth() - 1);
		} else {
			currentDate.setMonth(currentDate.getMonth() + 1);
		}
		
		var year = currentDate.getFullYear();
		var month = currentDate.getMonth() + 1;
		var testText = document.getElementById("CalendaryearMonth");
		testText.innerHTML = year + "." + month;
		var month2 = currentDate.getMonth();
		
		location.href="/work/record?year=" + year + "&month=" + month2;
		
	}
	
	// 출근시간 입력
	function commuteIn() {
		var currentDate = new Date();
		var currentTime = currentDate.getHours() + ":"
       					+ currentDate.getMinutes() + ":"
       				    + currentDate.getSeconds();
		console.log(currentTime);           					
		$.ajax({
			type: "POST",
			url: "${pageContext.request.contextPath}/work/in",
			data: {
				intime: currentTime
			},
			async:false,		// ajax를 동기식으로 변경함..
			dataType: "json",
		});
		location.reload();
	}    
	
	// 퇴근시간 입력 
	function commuteOut() {
		var currentDate = new Date();
		var currentTime = currentDate.getHours() + ":"
       					+ currentDate.getMinutes() + ":"
       				    + currentDate.getSeconds();
		console.log(currentTime);           					
		$.ajax({
			type: "POST",
			url: "${pageContext.request.contextPath}/work/out",
			data: {
				intime: currentTime
			},
			async:false,		// ajax를 동기식으로 변경함..
			dataType: "json",
		});
		location.reload();
	}
	
	

	