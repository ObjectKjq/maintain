package com.kjq.service.impl;

import com.kjq.POJO.User;
import com.kjq.enums.StatusCodeEnum;
import com.kjq.mapper.UserMapper;
import com.kjq.model.vo.UserVo;
import com.kjq.service.UserService;
import com.kjq.utils.FFResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    //根据用户名查询用户信息
    @Override
    public User getByUsername(String account) {
        if(account == null){
            return null;
        }
        return userMapper.queryAccountUser(account);
    }

    // 根据用户id用户的权限，在这里只用到了管理员
    @Override
    public Integer getUserAuthorityInfo(Integer userId) {
        return userMapper.selectIdUser(userId).getUserRole();
    }

    @Override
    public FFResult getUserByAccount(String account) {
        User user = userMapper.queryAccountUser(account);
        if(user == null){
            return FFResult.error(StatusCodeEnum.ERROR);
        }
        UserVo userVo = new UserVo();
        userVo.setName(user.getUsername());
        userVo.setAvatar(user.getAvatarUrl());
        List<String> list = new ArrayList<>();
        if(user.getUserRole() == 0){
            list.add("user");
        }else if(user.getUserRole() == 1){
            list.add("admin");
        }else {
            list.add("maintain");
        }
        userVo.setRoles(list);
        return FFResult.success(StatusCodeEnum.SUCCESS, userVo);
    }
}
