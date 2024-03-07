package com.kjq.service.impl;

import cn.hutool.core.date.DateUtil;
import com.kjq.POJO.Feedback;
import com.kjq.POJO.User;
import com.kjq.enums.StatusCodeEnum;
import com.kjq.mapper.FeedbackMapper;
import com.kjq.mapper.UserMapper;
import com.kjq.service.FeedbackService;
import com.kjq.utils.FFResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    FeedbackMapper feedbackMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public FFResult addFeedback(String content) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);

        //添加反馈信息
        Feedback feedback = new Feedback();
        feedback.setFeedbackStatus(1);
        feedback.setContent(content);
        feedback.setUserId(user.getId());
        feedback.setCreateTime(DateUtil.formatDate(DateUtil.date()));
        if(feedbackMapper.addFeedback(feedback)){
            return FFResult.success(StatusCodeEnum.SUCCESS);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }

    @Override
    public FFResult getAdminFeedback(Integer page, Integer limit) {
        page = (page - 1) * limit;
        List<Feedback> adminFeedbacks = feedbackMapper.getAdminFeedback(page, limit);
        Feedback feedback = new Feedback();
        feedback.setId(feedbackMapper.getTotal());
        adminFeedbacks.add(feedback);
        return FFResult.success(StatusCodeEnum.SUCCESS, adminFeedbacks);
    }

    @Override
    public FFResult deleteAdminFeedback(Integer id) {
        if(feedbackMapper.deleteAdminFeedback(id)){
            return FFResult.success(StatusCodeEnum.SUCCESS);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }
}
