package com.kjq.mapper;

import com.kjq.POJO.Scope;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScopeMapper {
    @Select("select * from `scope` where scope_status = 1 and user_id = #{id} limit #{page}, #{limit}")
    List<Scope> getScopes(Integer page, Integer limit, Integer id);
}
