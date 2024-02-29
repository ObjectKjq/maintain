package com.kjq.mapper;

import com.kjq.POJO.Feedback;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedbackMapper {
    @Insert("insert into feedback (user_id, create_time, content, feedback_status) values (#{userId}, #{createTime}, #{content}, #{feedbackStatus})")
    boolean addFeedback(Feedback feedback);
}
