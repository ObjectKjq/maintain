package com.kjq.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.kjq.POJO.Consult;
import com.kjq.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/addConsult")
    public String addConsult(@RequestBody JSONObject jsonObject){
        Integer id = jsonObject.getInt("id");
        String content = jsonObject.getStr("content");
        Integer appointId = jsonObject.getInt("appointId");
        return JSONUtil.toJsonStr(consultService.addConsult(content, appointId, id));
    }

    @GetMapping("/getListConsult")
    public String getListConsult(){
        return JSONUtil.toJsonStr(consultService.getListConsult());
    }

    @DeleteMapping("/deleteConsult")
    public String deleteConsult(@RequestBody JSONObject jsonObject){
        Integer id = jsonObject.getInt("id");
        return JSONUtil.toJsonStr(consultService.deleteConsult(id));
    }

    @Secured({"ROLE_maintain"})
    @PostMapping("/maintain/reply")
    public String reply(@RequestBody Consult consult){
        return JSONUtil.toJsonStr(consultService.reply(consult));
    }
}
