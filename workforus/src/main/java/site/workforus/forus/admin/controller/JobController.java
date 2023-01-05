package site.workforus.forus.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import site.workforus.forus.admin.service.JobService;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping(value = "job-list")
    public String getJobsPage() {
        return "admin/job-list";
    }


    // 사원별 직급 조회
    @ResponseBody
    @GetMapping(value = "/list", produces = "application/json; charset=utf-8")
    public Map<String, Object> getJobsList() {
    log.info("getJobsList");

    Map<String, Object> data = new HashMap<String, Object>();
    data.put("jobsData", jobService.getJobsByEmpId());

    return data;
    }



}
