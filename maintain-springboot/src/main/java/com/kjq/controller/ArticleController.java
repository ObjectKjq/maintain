package com.kjq.controller;

import cn.hutool.json.JSONUtil;
import com.kjq.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
