package com.kjq.service.impl;

import com.kjq.POJO.Article;
import com.kjq.POJO.User;
import com.kjq.enums.StatusCodeEnum;
import com.kjq.mapper.ArticleMapper;
import com.kjq.mapper.UserMapper;
import com.kjq.service.ArticleService;
import com.kjq.utils.FFResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    UserMapper userMapper;

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

    @Override
    public FFResult getArticles(Integer page, Integer limit) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        page = (page - 1) * limit;

        return FFResult.success(StatusCodeEnum.SUCCESS, articleMapper.getMaintainArticles(page, limit, user.getId()));
    }

    @Override
    public FFResult getAdminArticles(Integer page, Integer limit, Integer status) {
        page = (page - 1) * limit;
        return FFResult.success(StatusCodeEnum.SUCCESS, articleMapper.getAdminArticles(page, limit, status));
    }
}
