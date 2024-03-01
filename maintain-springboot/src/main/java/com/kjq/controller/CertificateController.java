package com.kjq.controller;

import cn.hutool.json.JSONUtil;
import com.kjq.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CertificateController {

    @Autowired
    CertificateService certificateService;

    @Secured({"ROLE_maintain"})
    @GetMapping("/maintain/getCertificates")
    public String getCertificates(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return JSONUtil.toJsonStr(certificateService.getCertificates(page, limit));
    }

    @Secured({"ROLE_admin"})
    @GetMapping("/admin/getAuditCertificates")
    public String getAdminCertificates(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return JSONUtil.toJsonStr(certificateService.getAdminCertificates(page, limit));
    }
}
