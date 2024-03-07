package com.kjq.mapper;

import com.kjq.POJO.Skill;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SkillMapper {
    @Select("select * from skill where skill_status = 1 and user_id = #{id} limit #{page}, #{limit}")
    List<Skill> getSkills(Integer page, Integer limit, Integer id);

    @Select("select count(*) from skill where user_id = #{id} and skill_status = 1")
    Integer getTotal(Integer id);

    @Insert("insert into skill(create_time, skill_status, user_id, content) value (#{createTime}, #{skillStatus}, #{userId}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean addSkill(Skill skill);

    @Update("update skill set content = #{content} where id = #{id} and skill_status = 1 and user_id = #{userId}")
    boolean updateSkill(Skill skill);

    @Update("update skill set skill_status = 0 where id = #{id} and user_id = #{userId}")
    boolean deleteSkill(Integer id, Integer userId);
}
