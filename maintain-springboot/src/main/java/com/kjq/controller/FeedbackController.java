package com.kjq.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.kjq.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    //用户反馈
    @PostMapping("/addFeedback")
    public String addFeedback(@RequestBody JSONObject jsonObject){
        String content = jsonObject.getStr("content");
        return JSONUtil.toJsonStr(feedbackService.addFeedback(content));
    }

    @Secured({"ROLE_admin"})
    @GetMapping("/admin/getFeedbacks")
    public String getAdminFeedbacks(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return JSONUtil.toJsonStr(feedbackService.getAdminFeedback(page, limit));
    }

    @Secured({"ROLE_admin"})
    @DeleteMapping("/admin/feedback/{id}")
    public String deleteAdminFeedback(@PathVariable Integer id){
        return JSONUtil.toJsonStr(feedbackService.deleteAdminFeedback(id));
    }
}
