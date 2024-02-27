package com.kjq.service;

import com.kjq.POJO.User;
import com.kjq.utils.FFResult;

public interface UserService {
    User getByUsername(String username);

    Integer getUserAuthorityInfo(Integer userId);

    FFResult getUserByAccount(String account);

    FFResult getToken(String openid);
}
