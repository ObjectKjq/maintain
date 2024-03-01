package com.kjq.controller;

import cn.hutool.json.JSONUtil;
import com.kjq.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    @Autowired
    ReportService reportService;

    @Secured({"ROLE_admin"})
    @GetMapping("/admin/getReports")
    public String getAdminReports(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return JSONUtil.toJsonStr(reportService.getAdminReports(page, limit));
    }

}
