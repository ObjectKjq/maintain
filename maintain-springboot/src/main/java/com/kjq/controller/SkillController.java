package com.kjq.controller;

import cn.hutool.json.JSONUtil;
import com.kjq.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SkillController {

    @Autowired
    SkillService skillService;

    @Secured({"ROLE_maintain"})
    @GetMapping("/maintain/getSkills")
    public String getSkills(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return JSONUtil.toJsonStr(skillService.getSkills(page, limit));
    }

}
