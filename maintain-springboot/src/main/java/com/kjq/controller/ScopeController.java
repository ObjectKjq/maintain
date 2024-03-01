package com.kjq.controller;

import cn.hutool.json.JSONUtil;
import com.kjq.service.ScopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScopeController {

    @Autowired
    ScopeService scopeService;

    @Secured({"ROLE_maintain"})
    @GetMapping("/maintain/getScopes")
    public String getScopes(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return JSONUtil.toJsonStr(scopeService.getScopes(page, limit));
    }

}
