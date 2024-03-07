package com.kjq.controller;

import cn.hutool.json.JSONUtil;
import com.kjq.POJO.Sort;
import com.kjq.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
public class SortController {

    @Autowired
    SortService sortService;

    @GetMapping("/getSorts")
    public String getSorts(){
        return JSONUtil.toJsonStr(sortService.getSorts());
    }

    @Secured({"ROLE_admin"})
    @GetMapping("/admin/getSorts")
    public String getAdminSorts(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return JSONUtil.toJsonStr(sortService.getAdminSorts(page, limit));
    }

    @Secured({"ROLE_maintain"})
    @GetMapping("/maintain/getSort")
    public String getSortList(){
        return JSONUtil.toJsonStr(sortService.getSortList());
    }

    @Secured({"ROLE_admin"})
    @PostMapping("/admin/sort")
    public String addAdminSort(@RequestBody Sort sort){
        return JSONUtil.toJsonStr(sortService.addAdminSort(sort));
    }

    @Secured({"ROLE_admin"})
    @PutMapping("/admin/sort")
    public String updateAdminSort(@RequestBody Sort sort){
        return JSONUtil.toJsonStr(sortService.updateAdminSort(sort));
    }

    @Secured({"ROLE_admin"})
    @DeleteMapping("/admin/sort/{id}")
    public String deleteAdminSort(@PathVariable Integer id){
        return JSONUtil.toJsonStr(sortService.deleteAdminSort(id));
    }
}
