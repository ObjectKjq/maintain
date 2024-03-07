package com.kjq.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.kjq.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportController {

    @Autowired
    ReportService reportService;

    @Secured({"ROLE_admin"})
    @GetMapping("/admin/getReports")
    public String getAdminReports(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return JSONUtil.toJsonStr(reportService.getAdminReports(page, limit));
    }

    @PostMapping("/addReport")
    public String addReport(@RequestBody JSONObject jsonObject){
        Integer appointId = jsonObject.getInt("appointId");
        String content = jsonObject.getStr("content");
        return JSONUtil.toJsonStr(reportService.addReport(appointId, content));
    }
}
