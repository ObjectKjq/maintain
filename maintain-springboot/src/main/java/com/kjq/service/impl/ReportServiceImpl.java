package com.kjq.service.impl;

import cn.hutool.core.date.DateUtil;
import com.kjq.POJO.Report;
import com.kjq.POJO.User;
import com.kjq.enums.StatusCodeEnum;
import com.kjq.mapper.ReportMapper;
import com.kjq.mapper.UserMapper;
import com.kjq.service.ReportService;
import com.kjq.utils.FFResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportMapper reportMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public FFResult getAdminReports(Integer page, Integer limit) {
        page = (page - 1) * limit;
        return FFResult.success(StatusCodeEnum.SUCCESS, reportMapper.getAdminReports(page, limit));
    }

    @Override
    public FFResult addReport(Integer appointId, String content) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userMapper.queryAccountUser(username);
        Report report = new Report();
        report.setCreateTime(DateUtil.formatDate(DateUtil.date()));
        report.setReportStatus(1);
        report.setReportId(user.getId());
        report.setByReportId(appointId);
        report.setContent(content);
        if(reportMapper.addReport(report)){
            return FFResult.success(StatusCodeEnum.SUCCESS);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }
}
