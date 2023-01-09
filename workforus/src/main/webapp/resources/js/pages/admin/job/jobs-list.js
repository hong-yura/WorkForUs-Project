/*
 * 사원별 직급 조회
 */
JobsList();
function JobsList() {

    $.ajax({
        type: "GET",
        url: "/admin/list",
        dataType: "json",
        success: function(json) {

            if(json.jobsData.length == 0) {
                $("#jobsList").append("<th><td style='text-align: center colspan='3''>직급 정보가 없습니다.</td></th>");
            } else {
                $(json.jobsData).each(function(i) {
                    const jobsListAdd = "<tr>"+
                        "<td>"+json.jobsData[i].empObj.empId+"</td>"+
                        "<td>"+json.jobsData[i].empObj.empNm+"</td>"+
                        "<td>"+json.jobsData[i].jobName+"</td>"+
                        "</tr>";

                    $("#jobsList").append(jobsListAdd);

                })
            }
            console.log(json);

        },
        error: function(xhr) {
            alert("에러코드 : " + xhr.status);
        }
    });
}