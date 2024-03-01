package com.kjq.mapper;

import com.kjq.POJO.Skill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SkillMapper {
    @Select("select * from skill where skill_status = 1 and user_id = #{id} limit #{page}, #{limit}")
    List<Skill> getSkills(Integer page, Integer limit, Integer id);
}
