package com.kjq.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.kjq.POJO.Skill;
import com.kjq.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
public class SkillController {

    @Autowired
    SkillService skillService;

    @Secured({"ROLE_maintain"})
    @GetMapping("/maintain/getSkills")
    public String getSkills(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return JSONUtil.toJsonStr(skillService.getSkills(page, limit));
    }

    @Secured({"ROLE_maintain"})
    @PostMapping("/maintain/skill")
    public String addSkill(@RequestBody JSONObject jsonObject){
        String content = jsonObject.getStr("content");
        return JSONUtil.toJsonStr(skillService.addSkill(content));
    }

    @Secured({"ROLE_maintain"})
    @PutMapping("/maintain/skill")
    public String updateSkill(@RequestBody Skill skill){
        return JSONUtil.toJsonStr(skillService.updateSkill(skill));
    }

    @Secured({"ROLE_maintain"})
    @DeleteMapping("/maintain/skill/{id}")
    public String deleteSkill(@PathVariable Integer id){
        return JSONUtil.toJsonStr(skillService.deleteSkill(id));
    }
}
