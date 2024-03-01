package com.kjq.service;

import com.kjq.utils.FFResult;

public interface CertificateService {
    FFResult getCertificates(Integer page, Integer limit);

    FFResult getAdminCertificates(Integer page, Integer limit);
}
