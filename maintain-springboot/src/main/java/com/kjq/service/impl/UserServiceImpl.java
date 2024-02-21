package com.kjq.service.impl;

import com.kjq.POJO.User;
import com.kjq.mapper.UserMapper;
import com.kjq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    //根据用户名查询用户信息
    @Override
    public User getByUsername(String username) {
        if(username == null){
            return null;
        }
        return userMapper.queryNameUser(username);
    }

    // 根据用户id用户的权限，在这里只用到了管理员
    @Override
    public Integer getUserAuthorityInfo(Integer userId) {
        return userMapper.selectIdUser(userId).getUserRole();
    }
}
