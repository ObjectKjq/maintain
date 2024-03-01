package com.kjq.service;

import com.kjq.utils.FFResult;

public interface ArticleService {
    FFResult getArticles(Integer page, Integer num, Integer sortId, String searchText);

    FFResult getArticle(Integer id);

    FFResult getArticles(Integer page, Integer limit);

    FFResult getAdminArticles(Integer page, Integer limit, Integer status);
}
