/*
 * 개인 주소록 조회
 */
personalAddrList();
function personalAddrList() {

    $.ajax({
        type: "GET",
        url: "/address/personal",
        dataType: "json",
        success: function(json) {

            if(json.addrData.length == 0) {
                $("#personalAddrList").append("<tr><td style='text-align: center' colspan='7'>주소록 정보가 없습니다.</td></tr>");
            } else {
                $(json.addrData).each(function(i) {
                    const addressAdd = "<tr>"+
                        "<td><input type='checkbox' name='addrCheck' class='form-check-input'></td>"+
                        "<td>"+json.addrData[i].addrName+"</td>"+
                        "<td>"+json.addrData[i].addrPhone+"</td>"+
                        "<td>"+json.addrData[i].addrDept+"</td>"+
                        "<td>"+json.addrData[i].addrEmail+"</td>"+
                        "<td>"+json.addrData[i].addrDetail+"</td>"+
                        "<td>"+json.addrData[i].addrTagObj.addrTagName+"</td>"+
                        "</tr>";

                    $("#personalAddrList").append(addressAdd);

                })
            }
            console.log(json);
        },
        error: function(xhr) {
            alert("에러코드 : " + xhr.status);
        }
    });

};