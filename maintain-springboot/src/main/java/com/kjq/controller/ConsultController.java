package com.kjq.controller;

import cn.hutool.json.JSONUtil;
import com.kjq.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsultController {

    @Autowired
    ConsultService consultService;

    @Secured({"ROLE_maintain"})
    @GetMapping("/maintain/getConsults")
    public String getConsults(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return JSONUtil.toJsonStr(consultService.getConsults(page, limit));
    }

    @Secured({"ROLE_admin"})
    @GetMapping("/admin/getConsults")
    public String getAdminConsults(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return JSONUtil.toJsonStr(consultService.getAdminConsults(page, limit));
    }
}
