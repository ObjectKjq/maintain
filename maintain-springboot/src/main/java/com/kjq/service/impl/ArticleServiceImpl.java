package com.kjq.service.impl;

import cn.hutool.core.date.DateUtil;
import com.kjq.POJO.Article;
import com.kjq.POJO.User;
import com.kjq.enums.StatusCodeEnum;
import com.kjq.mapper.ArticleMapper;
import com.kjq.mapper.SortMapper;
import com.kjq.mapper.UserMapper;
import com.kjq.model.vo.ArticleVo;
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

    @Autowired
    SortMapper sortMapper;

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
        //获取总文章数
        ArticleVo articleVo = new ArticleVo();
        articleVo.setId(articleMapper.getTotal(user.getId()));
        List<ArticleVo> maintainArticles = articleMapper.getMaintainArticles(page, limit, user.getId());
        maintainArticles.add(articleVo);
        return FFResult.success(StatusCodeEnum.SUCCESS, maintainArticles);
    }

    @Override
    public FFResult getAdminArticles(Integer page, Integer limit, Integer status) {
        page = (page - 1) * limit;
        return FFResult.success(StatusCodeEnum.SUCCESS, articleMapper.getAdminArticles(page, limit, status));
    }

    @Override
    public FFResult addArticle(Integer sortId, String title, String content) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);

        Article article = new Article();
        article.setUserId(user.getId());
        article.setSortId(sortId);
        article.setCreateTime(DateUtil.formatDate(DateUtil.date()));
        article.setTitle(title);
        article.setContent(content);
        article.setArticleStatus(0);
        if(articleMapper.addArticle(article)){
            return FFResult.success(StatusCodeEnum.SUCCESS);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }

    @Override
    public FFResult getMaintainArticle(Integer id) {

        return FFResult.success(StatusCodeEnum.SUCCESS, articleMapper.getMaintainArticle(id));
    }

    @Override
    public FFResult updateArticle(ArticleVo articleVo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        //获取分类id
        Integer sortId = sortMapper.getNameId(articleVo.getSort());
        if(articleMapper.updateArticle(articleVo, user.getId(), sortId)){
            return FFResult.success(StatusCodeEnum.SUCCESS);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }

    @Override
    public FFResult deleteArticle(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        if(articleMapper.deleteArticle(id, user.getId())){
            return FFResult.success(StatusCodeEnum.SUCCESS);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }
}
