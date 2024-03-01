package com.kjq.service;

import com.kjq.utils.FFResult;

public interface ConsultService {
    FFResult getConsults(Integer page, Integer limit);

    FFResult getAdminConsults(Integer page, Integer limit);
}
