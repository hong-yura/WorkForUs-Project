/*
 * 부서 주소록 조회
 */
deptAddrList();
function deptAddrList() {

    $.ajax({
        type: "GET",
        url: "/address/department",
        dataType: "json",
        success: function(json) {

            if(json.addrData.length == 0) {
                $("#deptAddrList").append("<tr><td>주소록 정보가 없습니다.</td></tr>");
            } else {
                $(json.addrData).each(function(i) {
                    const addressAdd = "<tr>"+
                    "<td><input type='checkbox' name='addrCheck' class='form-check-input'></td>"+
                    "<td>"+json.addrData[i].empId+"</td>"+
                    "<td>"+json.addrData[i].empObj.empNm+"</td>"+
                    "<td>"+json.addrData[i].empObj.deptNo+"</td>"+
                    "<td>"+json.deptNm+"</td>"+
                    "<td>"+json.addrData[i].empObj.empEmail+"</td>"+
                    "<td>"+json.addrData[i].empObj.empTel+"</td>"+
                    "</tr>";

                    $("#deptAddrList").append(addressAdd);

                })
            }
            console.log(json);
        },
        error: function(xhr) {
            alert("에러코드 : " + xhr.status);
        }
    });

};