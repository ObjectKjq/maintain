package com.kjq.controller;

import cn.hutool.json.JSONUtil;
import com.kjq.POJO.Certificate;
import com.kjq.service.CertificateService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

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

    @Secured({"ROLE_maintain"})
    @PostMapping("/maintain/certificate")
    public String addCertificate(@RequestBody Certificate certificate){
        return JSONUtil.toJsonStr(certificateService.addCertificate(certificate));
    }

    @Secured({"ROLE_maintain"})
    @DeleteMapping("/maintain/certificate/{id}")
    public String deleteCertificate(@PathVariable Integer id){
        return JSONUtil.toJsonStr(certificateService.deleteCertificate(id));
    }

    @Secured({"ROLE_admin"})
    @PutMapping("/admin/passCertificate/{id}")
    public String passCertificate(@PathVariable Integer id){
        return JSONUtil.toJsonStr(certificateService.passCertificate(id));
    }

    @Secured({"ROLE_admin"})
    @DeleteMapping("/admin/downCertificate/{id}")
    public String downCertificate(@PathVariable Integer id){
        return JSONUtil.toJsonStr(certificateService.downCertificate(id));
    }
}
