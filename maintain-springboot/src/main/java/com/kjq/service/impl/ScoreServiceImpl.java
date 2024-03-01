package com.kjq.service.impl;

import com.kjq.enums.StatusCodeEnum;
import com.kjq.mapper.ScoreMapper;
import com.kjq.service.ScoreService;
import com.kjq.utils.FFResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    ScoreMapper scopeMapper;

    @Override
    public FFResult getAdminScores(Integer page, Integer limit) {
        page = (page - 1) * limit;
        return FFResult.success(StatusCodeEnum.SUCCESS, scopeMapper.getAdminScores(page, limit));
    }
}
