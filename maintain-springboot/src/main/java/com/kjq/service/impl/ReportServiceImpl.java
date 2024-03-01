package com.kjq.service.impl;

import com.kjq.enums.StatusCodeEnum;
import com.kjq.mapper.ReportMapper;
import com.kjq.service.ReportService;
import com.kjq.utils.FFResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportMapper reportMapper;

    @Override
    public FFResult getAdminReports(Integer page, Integer limit) {
        page = (page - 1) * limit;
        return FFResult.success(StatusCodeEnum.SUCCESS, reportMapper.getAdminReports(page, limit));
    }
}
