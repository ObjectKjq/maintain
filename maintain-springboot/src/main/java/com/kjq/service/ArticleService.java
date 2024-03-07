package com.kjq.service;

import com.kjq.model.vo.ArticleVo;
import com.kjq.utils.FFResult;

public interface ArticleService {
    FFResult getArticles(Integer page, Integer num, Integer sortId, String searchText);

    FFResult getArticle(Integer id);

    FFResult getArticles(Integer page, Integer limit);

    FFResult getAdminArticles(Integer page, Integer limit, Integer status);

    FFResult addArticle(Integer sortId, String title, String content);

    FFResult getMaintainArticle(Integer id);

    FFResult updateArticle(ArticleVo articleVo);

    FFResult deleteArticle(Integer id);
}
