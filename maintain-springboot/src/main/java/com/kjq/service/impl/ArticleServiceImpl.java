package com.kjq.service.impl;

import com.kjq.POJO.Article;
import com.kjq.enums.StatusCodeEnum;
import com.kjq.mapper.ArticleMapper;
import com.kjq.service.ArticleService;
import com.kjq.utils.FFResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public FFResult getArticles(Integer page, Integer num, Integer sortId, String searchText) {

        page = (page - 1) * num;
        List<Article> articles = articleMapper.getArticles(page, num, sortId, searchText);
        return FFResult.success(StatusCodeEnum.SUCCESS, articles);
    }

    @Override
    public FFResult getArticle(Integer id) {

        return FFResult.success(StatusCodeEnum.SUCCESS, articleMapper.getArticle(id));
    }
}
