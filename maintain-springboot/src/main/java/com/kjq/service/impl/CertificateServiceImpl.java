package com.kjq.service.impl;

import cn.hutool.core.date.DateUtil;
import com.kjq.POJO.Certificate;
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

import java.util.List;

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

        //获取文章总数
        Certificate certificate = new Certificate();
        certificate.setId(certificateMapper.getTotal(user.getId()));
        List<Certificate> certificates = certificateMapper.getCertificates(page, limit, user.getId());
        certificates.add(certificate);
        return FFResult.success(StatusCodeEnum.SUCCESS, certificates);
    }

    @Override
    public FFResult getAdminCertificates(Integer page, Integer limit) {
        page = (page - 1) * limit;
        Certificate certificate = new Certificate();
        List<Certificate> adminCertificates = certificateMapper.getAdminCertificates(page, limit);
        certificate.setId(certificateMapper.getAdminTotal());
        adminCertificates.add(certificate);
        return FFResult.success(StatusCodeEnum.SUCCESS, adminCertificates);
    }

    @Override
    public FFResult addCertificate(Certificate certificate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        certificate.setCreateTime(DateUtil.formatDate(DateUtil.date()));
        certificate.setCertificateStatus(0);
        certificate.setUserId(user.getId());
        if(certificateMapper.addCertificate(certificate)){
            return FFResult.success(StatusCodeEnum.SUCCESS, certificate);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }

    @Override
    public FFResult deleteCertificate(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        if(certificateMapper.deleteCertificate(id, user.getId())){
            return FFResult.success(StatusCodeEnum.SUCCESS);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }

    @Override
    public FFResult passCertificate(Integer id) {
        if(certificateMapper.passCertificate(id)){
            return FFResult.success(StatusCodeEnum.SUCCESS);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }

    @Override
    public FFResult downCertificate(Integer id) {
        if(certificateMapper.downCertificate(id)){
            return FFResult.success(StatusCodeEnum.SUCCESS);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }
}
