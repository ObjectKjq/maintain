package com.kjq.service;

import com.kjq.POJO.User;

public interface UserService {
    User getByUsername(String username);

    Integer getUserAuthorityInfo(Integer userId);
}
