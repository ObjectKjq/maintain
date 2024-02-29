package com.kjq.service;

import com.kjq.POJO.User;
import com.kjq.utils.FFResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    User getByUsername(String username);

    Integer getUserAuthorityInfo(Integer userId);

    FFResult getUserByAccount(String account);

    FFResult getToken(HttpServletRequest req, String openid);

    FFResult upload(MultipartFile file);

    FFResult updateUser(String name, String sex);
}
