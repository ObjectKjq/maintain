package com.kjq.mapper;

import com.kjq.POJO.Scope;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ScopeMapper {
    @Select("select * from `scope` where scope_status = 1 and user_id = #{id} limit #{page}, #{limit}")
    List<Scope> getScopes(Integer page, Integer limit, Integer id);

    @Select("select count(*) from `scope` where scope_status = 1 and user_id = #{id}")
    Integer getTotal(Integer id);

    @Insert("insert into scope (create_time, scope_status, content, user_id) values (#{createTime}, #{scopeStatus}, #{content}, #{userId});")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean addScope(Scope scope);

    @Update("update scope set content = #{content} where user_id = #{userId} and id = #{id} and scope_status = 1")
    boolean updateScope(Scope scope);

    @Update("update scope set scope_status = 0 where id = #{id} and user_id = #{userId}")
    boolean deleteScope(Integer id, Integer userId);
}
