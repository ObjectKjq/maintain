package com.kjq.mapper;

import com.kjq.POJO.Feedback;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FeedbackMapper {
    @Insert("insert into feedback (user_id, create_time, content, feedback_status) values (#{userId}, #{createTime}, #{content}, #{feedbackStatus})")
    boolean addFeedback(Feedback feedback);

    @Select("select * from feedback where feedback_status = 1 limit #{page}, #{limit}")
    List<Feedback> getAdminFeedback(Integer page, Integer limit);

    @Select("select count(*) from feedback where feedback_status = 1")
    Integer getTotal();

    @Update("update feedback set feedback_status = 0 where id = #{id}")
    boolean deleteAdminFeedback(Integer id);
}
