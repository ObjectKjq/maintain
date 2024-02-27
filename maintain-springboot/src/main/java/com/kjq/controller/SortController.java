package com.kjq.controller;

import cn.hutool.json.JSONUtil;
import com.kjq.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SortController {

    @Autowired
    SortService sortService;

    @GetMapping("/getSorts")
//    @Secured({"ROLE_user"})
    public String getSorts(){
        return JSONUtil.toJsonStr(sortService.getSorts());
    }
}
