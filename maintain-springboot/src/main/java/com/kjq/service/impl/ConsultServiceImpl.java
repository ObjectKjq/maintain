package com.kjq.service.impl;

import com.kjq.POJO.User;
import com.kjq.enums.StatusCodeEnum;
import com.kjq.mapper.ConsultMapper;
import com.kjq.mapper.UserMapper;
import com.kjq.service.ConsultService;
import com.kjq.utils.FFResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ConsultServiceImpl implements ConsultService {

    @Autowired
    ConsultMapper consultMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public FFResult getConsults(Integer page, Integer limit) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        page = (page - 1) * limit;

        return FFResult.success(StatusCodeEnum.SUCCESS, consultMapper.getConsults(page, limit, user.getId()));
    }

    @Override
    public FFResult getAdminConsults(Integer page, Integer limit) {
        page = (page - 1) * limit;
        return FFResult.success(StatusCodeEnum.SUCCESS, consultMapper.getAdminConsults(page, limit));
    }
}
