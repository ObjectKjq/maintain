package com.kjq.service.impl;

import com.kjq.POJO.User;
import com.kjq.enums.StatusCodeEnum;
import com.kjq.mapper.CertificateMapper;
import com.kjq.mapper.UserMapper;
import com.kjq.service.CertificateService;
import com.kjq.utils.FFResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    CertificateMapper certificateMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public FFResult getCertificates(Integer page, Integer limit) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        page = (page - 1) * limit;
        return FFResult.success(StatusCodeEnum.SUCCESS, certificateMapper.getCertificates(page, limit, user.getId()));
    }

    @Override
    public FFResult getAdminCertificates(Integer page, Integer limit) {
        page = (page - 1) * limit;
        return FFResult.success(StatusCodeEnum.SUCCESS, certificateMapper.getAdminCertificates(page, limit));
    }
}
