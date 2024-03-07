package com.kjq.controller;

import cn.hutool.json.JSONUtil;
import com.kjq.POJO.Scope;
import com.kjq.service.ScopeService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScopeController {

    @Autowired
    ScopeService scopeService;

    @Secured({"ROLE_maintain"})
    @GetMapping("/maintain/getScopes")
    public String getScopes(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return JSONUtil.toJsonStr(scopeService.getScopes(page, limit));
    }

    @Secured({"ROLE_maintain"})
    @PostMapping("/maintain/scope")
    public String addScope(@RequestBody Scope scope){
        return JSONUtil.toJsonStr(scopeService.addScope(scope));
    }

    @Secured({"ROLE_maintain"})
    @PutMapping("/maintain/scope")
    public String updateScope(@RequestBody Scope scope){
        return JSONUtil.toJsonStr(scopeService.updateScope(scope));
    }

    @Secured({"ROLE_maintain"})
    @DeleteMapping("/maintain/scope/{id}")
    public String deleteScope(@PathVariable Integer id){
        return JSONUtil.toJsonStr(scopeService.deleteScope(id));
    }
}
