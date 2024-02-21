package com.kjq.service.impl;

import com.kjq.POJO.AccountUser;
import com.kjq.POJO.User;
import com.kjq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return new AccountUser(user.getId(), user.getUserAccount(), user.getUserPassword(), getUserAuthority(user.getId()));
    }

    public List<GrantedAuthority> getUserAuthority(Integer userId) {
        // 实际怎么写以数据表结构为准，这里只是写个例子
        // 角色(比如ROLE_admin)，菜单操作权限(比如sys:user:list)
        String authority;
        Integer role = userService.getUserAuthorityInfo(userId);     // 比如ROLE_admin,ROLE_normal,sys:user:list,...
        if (role == 0){
            authority = "ROLE_user";
        }else if(role == 1) {
            authority = "ROLE_admin";
        }else{
            authority = "ROLE_maintain";
        }
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
    }
}
