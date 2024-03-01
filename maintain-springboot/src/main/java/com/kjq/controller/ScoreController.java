package com.kjq.controller;

import cn.hutool.json.JSONUtil;
import com.kjq.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreController {

    @Autowired
    ScoreService scopeService;

    @Secured({"ROLE_admin"})
    @GetMapping("/admin/getScores")
    public String getAdminScores(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return JSONUtil.toJsonStr(scopeService.getAdminScores(page, limit));
    }
}
