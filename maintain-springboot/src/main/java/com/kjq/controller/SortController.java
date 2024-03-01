package com.kjq.controller;

import cn.hutool.json.JSONUtil;
import com.kjq.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Secured({"ROLE_admin"})
    @GetMapping("/admin/getSorts")
    public String getAdminSorts(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return JSONUtil.toJsonStr(sortService.getAdminSorts(page, limit));
    }
}
