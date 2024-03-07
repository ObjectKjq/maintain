package com.kjq.service;

import com.kjq.utils.FFResult;

public interface ReportService {
    FFResult getAdminReports(Integer page, Integer limit);

    FFResult addReport(Integer appointId, String content);
}
