package com.kjq.service.impl;

import com.kjq.POJO.User;
import com.kjq.enums.StatusCodeEnum;
import com.kjq.mapper.ScopeMapper;
import com.kjq.mapper.UserMapper;
import com.kjq.service.ScopeService;
import com.kjq.utils.FFResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ScopeServiceImpl implements ScopeService {

    @Autowired
    ScopeMapper scopeMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public FFResult getScopes(Integer page, Integer limit) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        page = (page - 1) * limit;
        return FFResult.success(StatusCodeEnum.SUCCESS, scopeMapper.getScopes(page, limit, user.getId()));
    }
}
