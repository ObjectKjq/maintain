package com.kjq.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.kjq.model.vo.ArticleVo;
import com.kjq.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/getArticles")
    public String getArticles(
            @RequestParam("page") Integer page,
            @RequestParam("num") Integer num,
            @RequestParam(value = "sortId", required = false) Integer sortId,
            @RequestParam(value = "searchText", required = false) String searchText){

        return JSONUtil.toJsonStr(articleService.getArticles(page, num, sortId, searchText));
    }

    @GetMapping("/getArticle/{id}")
    public String getArticle(@PathVariable Integer id){
        return JSONUtil.toJsonStr(articleService.getArticle(id));
    }

    @Secured({"ROLE_maintain"})
    @GetMapping("/maintain/getArticles")
    public String getArticles(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return JSONUtil.toJsonStr(articleService.getArticles(page, limit));
    }

    @Secured({"ROLE_admin"})
    @GetMapping("/admin/getArticles")
    public String getAdminArticles(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, @RequestParam("status") Integer status){
        return JSONUtil.toJsonStr(articleService.getAdminArticles(page, limit, status));
    }

    @Secured({"ROLE_maintain"})
    @PostMapping("/maintain/addArticle")
    public String addArticle(@RequestBody JSONObject jsonObject){
        Integer sortId = jsonObject.getInt("sort");
        String title = jsonObject.getStr("title");
        String content = jsonObject.getStr("content");
        return JSONUtil.toJsonStr(articleService.addArticle(sortId, title, content));
    }

    @Secured({"ROLE_maintain"})
    @GetMapping("/maintain/getArticle/{id}")
    public String getMaintainArticle(@PathVariable Integer id){
        return JSONUtil.toJsonStr(articleService.getMaintainArticle(id));
    }

    @Secured({"ROLE_maintain"})
    @PutMapping("/maintain/updateArticle")
    public String updateArticle(@RequestBody ArticleVo articleVo){
        return JSONUtil.toJsonStr(articleService.updateArticle(articleVo));
    }

    @Secured({"ROLE_maintain"})
    @PutMapping("/maintain/deleteArticle/{id}")
    public String deleteArticle(@PathVariable Integer id){
        return JSONUtil.toJsonStr(articleService.deleteArticle(id));
    }

    @Secured({"ROLE_admin"})
    @DeleteMapping("/admin/article/{id}")
    public String deleteAdminArticle(@PathVariable Integer id){
        System.out.println(id);
        return JSONUtil.toJsonStr(articleService.deleteAdminArticle(id));
    }

    @Secured({"ROLE_admin"})
    @PutMapping("/admin/passArticle/{id}")
    public String passArticle(@PathVariable Integer id){
        return JSONUtil.toJsonStr(articleService.passArticle(id));
    }

    @Secured({"ROLE_admin"})
    @DeleteMapping("/admin/downArticle")
    public String downArticle(@RequestBody ArticleVo articleVo){
        return JSONUtil.toJsonStr(articleService.downArticle(articleVo));
    }
}
