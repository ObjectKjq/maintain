package com.kjq.service.impl;

import cn.hutool.core.date.DateUtil;
import com.kjq.POJO.Score;
import com.kjq.enums.StatusCodeEnum;
import com.kjq.mapper.ScoreMapper;
import com.kjq.service.ScoreService;
import com.kjq.utils.FFResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    ScoreMapper scoreMapper;

    @Override
    public FFResult getAdminScores(Integer page, Integer limit) {
        page = (page - 1) * limit;
        List<Score> adminScores = scoreMapper.getAdminScores(page, limit);
        Score score = new Score();
        score.setId(scoreMapper.getTotal());
        adminScores.add(score);
        return FFResult.success(StatusCodeEnum.SUCCESS, adminScores);
    }

    @Override
    public FFResult addAdminScore(Score score) {
        score.setCreateTime(DateUtil.formatDate(DateUtil.date()));
        score.setScoreStatus(1);
        if(scoreMapper.addScope(score)){
            return FFResult.success(StatusCodeEnum.SUCCESS, score);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }

    @Override
    public FFResult updateAdminScore(Score score) {
        if(scoreMapper.updateAdminScore(score)){
            return FFResult.success(StatusCodeEnum.SUCCESS);
        }
        return FFResult.error(StatusCodeEnum.ERROR);
    }

    @Override
    public FFResult deleteAdminScore(Integer id) {
        if(scoreMapper.deleteAdminScore(id)){
            return FFResult.success(StatusCodeEnum.SUCCESS);
        }

        return FFResult.error(StatusCodeEnum.ERROR);
    }
}
