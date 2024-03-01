package com.kjq.mapper;

import com.kjq.POJO.Score;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScoreMapper {
    @Select("select * from `score` where score_status = 1 and score_id = #{appointId} and by_score_id = #{id}")
    Score getScopeByUserIds(Integer appointId, Integer id);

    @Insert("insert into `score` (create_time, score_status, score_id, by_score_id, point) values (#{createTime}, #{scoreStatus}, #{scoreId}, #{byScoreId}, #{point})")
    boolean addScope(Score score);

    @Select("select * from `score` where score_status = 1 limit #{page}, #{limit}")
    List<Score> getAdminScores(Integer page, Integer limit);
}
