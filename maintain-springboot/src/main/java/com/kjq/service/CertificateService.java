package com.kjq.service;

import com.kjq.POJO.Certificate;
import com.kjq.utils.FFResult;

public interface CertificateService {
    FFResult getCertificates(Integer page, Integer limit);

    FFResult getAdminCertificates(Integer page, Integer limit);

    FFResult addCertificate(Certificate certificate);

    FFResult deleteCertificate(Integer id);

    FFResult passCertificate(Integer id);

    FFResult downCertificate(Integer id);
}
