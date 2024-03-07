package com.kjq.service.impl;

import cn.hutool.core.date.DateUtil;
import com.kjq.POJO.Scope;
import com.kjq.POJO.User;
import com.kjq.enums.StatusCodeEnum;
import com.kjq.mapper.ScopeMapper;
import com.kjq.mapper.UserMapper;
import com.kjq.service.ScopeService;
import com.kjq.utils.FFResult;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

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
        //获取业务范围总数
        Scope scope = new Scope();
        scope.setId(scopeMapper.getTotal(user.getId()));
        List<Scope> scopes = scopeMapper.getScopes(page, limit, user.getId());
        scopes.add(scope);
        return FFResult.success(StatusCodeEnum.SUCCESS, scopes);
    }

    @Override
    public FFResult addScope(Scope scope) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        scope.setCreateTime(DateUtil.formatDate(DateUtil.date()));
        scope.setScopeStatus(1);
        scope.setUserId(user.getId());
        if(scopeMapper.addScope(scope)){
            return FFResult.success(StatusCodeEnum.SUCCESS, scope);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }

    @Override
    public FFResult updateScope(Scope scope) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        scope.setUserId(user.getId());
        if(scopeMapper.updateScope(scope)){
            return FFResult.success(StatusCodeEnum.SUCCESS);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }

    @Override
    public FFResult deleteScope(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        if(scopeMapper.deleteScope(id, user.getId())){
            return FFResult.success(StatusCodeEnum.SUCCESS);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }
}
