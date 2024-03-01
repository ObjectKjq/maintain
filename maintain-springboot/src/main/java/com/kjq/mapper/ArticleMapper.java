package com.kjq.mapper;

import com.kjq.POJO.Article;
import com.kjq.model.vo.ArticleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {
    List<Article> getArticles(Integer page, Integer num, Integer sortId, String searchText);

    @Select("select * from article where id = #{id} and article_status = 1")
    Article getArticle(Integer id);

    List<ArticleVo> getMaintainArticles(Integer page, Integer limit, Integer id);

    List<ArticleVo> getAdminArticles(Integer page, Integer limit, Integer status);
}
