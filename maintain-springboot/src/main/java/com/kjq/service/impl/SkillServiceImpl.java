package com.kjq.service.impl;

import com.kjq.POJO.User;
import com.kjq.enums.StatusCodeEnum;
import com.kjq.mapper.SkillMapper;
import com.kjq.mapper.UserMapper;
import com.kjq.service.SkillService;
import com.kjq.utils.FFResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    SkillMapper skillMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public FFResult getSkills(Integer page, Integer limit) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        page = (page - 1) * limit;
        return FFResult.success(StatusCodeEnum.SUCCESS, skillMapper.getSkills(page, limit, user.getId()));
    }
}
