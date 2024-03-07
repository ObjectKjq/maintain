package com.kjq.controller;

import cn.hutool.json.JSONUtil;
import com.kjq.POJO.Score;
import com.kjq.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScoreController {

    @Autowired
    ScoreService scopeService;

    @Secured({"ROLE_admin"})
    @GetMapping("/admin/getScores")
    public String getAdminScores(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return JSONUtil.toJsonStr(scopeService.getAdminScores(page, limit));
    }

    @Secured({"ROLE_admin"})
    @PostMapping("/admin/score")
    public String addAdminScore(@RequestBody Score score){
        return JSONUtil.toJsonStr(scopeService.addAdminScore(score));
    }

    @Secured({"ROLE_admin"})
    @PutMapping("/admin/score")
    public String updateAdminScore(@RequestBody Score score){
        return JSONUtil.toJsonStr(scopeService.updateAdminScore(score));
    }

    @Secured({"ROLE_admin"})
    @DeleteMapping("/admin/score/{id}")
    public String deleteAdminScore(@PathVariable Integer id){
        return JSONUtil.toJsonStr(scopeService.deleteAdminScore(id));
    }

}
