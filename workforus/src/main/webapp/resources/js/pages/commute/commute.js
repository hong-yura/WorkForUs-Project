/**
 * 
 */
 
 	// 달(month) 이동 및 정보 가져오기
	function loadPrevNext_backup(element, value){
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
		// var month2 = currentDate.getMonth();		 
			
		$.ajax({
			type: "GET",
			url : "/work/record2",
			data: {
				year : year,
				month : month
			},
			success: function(data){
				var listData = data;
				var tableBody = document.getElementById("tableBody");
				console.log(tableBody);
				tableBody.innerHTML = "";
				var html="<table class='table'>";
		  		
		  		$.each(data, (index, obj)=>{ 
		  			html+="<tr>";
		  	  		html+="<td>"+ (obj.commuteDt+"").substring(4,6) + "월" +  (obj.commuteDt+"").substring(6) + "일" + "</td>";
		  	  		html+="<td>"+ (obj.commuteTime+"").substring(11) + "</td>";
		  	  		if(obj.getoffTime === null) {
			  	  		html+="<td>"+ "-" +"</td>";
			  	  		html+="<td>"+ "-" +"</td>";
			  	  		html+="<td>"+ "-" +"</td>";
			  	  		html+="</tr>";
					} else if((obj.getoffTime+"").substring(11) == "23:59:59"){
			  	  		html+="<td>"+ "관리자문의" +"</td>";
			  	  		html+="<td>"+ "관리자문의" +"</td>";
			  	  		html+="<td>"+ "관리자문의" +"</td>";
			  	  		html+="</tr>";
					} else {
			  	  		html+="<td>"+ (obj.getoffTime+"").substring(11) +"</td>";
			  	  		html+="<td>"+ (obj.addedTime+"").substring(11, 13) + "h " + (obj.addedTime+"").substring(14, 16) + "m " + (obj.addedTime+"").substring(17) + "s" +"</td>";
			  	  		html+="<td>"+ (obj.workTime+"").substring(11, 13) + "h " + (obj.workTime+"").substring(14, 16) + "m " + (obj.workTime+"").substring(17) + "s" +"</td>";
			  	  		html+="</tr>";
					}
		  		})
		  		html+="</table>";
		  		
		  		$("#tableBody").html(html);
				console.log(listData);	
			}
		});
		
		
	}
	
	
	/*
		var tr = '<thead>'+
		    	  '<tr>' +
	              '<th>근무일자</th>'+
	              '<th>출근시간 </th>'+
	              '<th>퇴근시간 </th>'+
	              '<th>추가근무시간 </th>'+
	              '<th>근무시간 </th>'+
	       	      '</tr>'+
	              '</thead>'; 
			$.each(listData , function(i) {
	          td +='<tr>' + '<td>' +  (listData.commuteDt+"").substring(4,6) + "월" +  (listData.commuteDt+"").substring(6) + "일" + 
		          '</td><td>' + (listData.commuteTime+"").substring(11) + 
		          '</td><td>' + (listData.getoffTime+"").substring(11) + 
		          '</td><td>' + (listData.addedTime+"").substring(11, 13) + "h" + (listData.addedTime+"").substring(14, 16) + "h" + (listData.addedTime+"").substring(17) + "s"  + 
		          '</td><td>' + (listData.workTime+"").substring(11, 13) + "h" + (listData.workTime+"").substring(14, 16) + "h" + (listData.workTime+"").substring(17) + "s" + 
		          '</td></tr>';
	        });
	        $("#tableBody").append(td);
			console.log("로드완료");
			console.log(listData.commuteDt);
	*/
	
	
	// 출근시간 입력
	function commuteIn() {
		var currentDate = new Date();
		var currentTime = currentDate.getHours() + ":"
       					+ currentDate.getMinutes() + ":"
       				    + currentDate.getSeconds();
		console.log(currentTime);           					
		$.ajax({
			type: "POST",
			url: "/work/in",
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
			url: "/work/out",
			data: {
				intime: currentTime
			},
			async:false,		// ajax를 동기식으로 변경함..
			dataType: "json",
		});
		location.reload();
	}
	
	

	