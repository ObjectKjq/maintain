package com.kjq.mapper;

import com.kjq.POJO.Article;
import com.kjq.model.vo.ArticleVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    List<Article> getArticles(Integer page, Integer num, Integer sortId, String searchText);

    @Select("select * from article where id = #{id} and article_status = 1")
    Article getArticle(Integer id);

    List<ArticleVo> getMaintainArticles(Integer page, Integer limit, Integer id);

    List<ArticleVo> getAdminArticles(Integer page, Integer limit, Integer status);

    @Insert("insert into article(user_id, sort_id, create_time, title, article_status, content) value (#{userId}, #{sortId}, #{createTime}, #{title}, #{articleStatus}, #{content})")
    boolean addArticle(Article article);

    @Select("select article.id as id, sort.content as sort, article.create_time as create_time, article.title as title, article.content as content from article " +
            "join sort on article.sort_id = sort.id " +
            "where article.id = #{id} " +
            "and article.article_status = 1 " +
            "and sort.sort_status = 1")
    ArticleVo getMaintainArticle(Integer id);

    @Update("update article set sort_id = #{sortId}, title = #{articleVo.title}, content = #{articleVo.content}, article_status = 0 where id = #{articleVo.id} and article_status = 1 and user_id = #{userId}")
    boolean updateArticle(ArticleVo articleVo, Integer userId, Integer sortId);

    @Select("select count(*) from article where user_id = #{id} and article_status = 1")
    Integer getTotal(Integer id);

    @Delete("delete from article where id = #{id} and user_id = #{userId} and article_status = 1")
    boolean deleteArticle(Integer id, Integer userId);
}
