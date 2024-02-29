package com.kjq.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.kjq.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
