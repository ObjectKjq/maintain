package com.kjq.service;

import com.kjq.POJO.Score;
import com.kjq.utils.FFResult;

public interface ScoreService {
    FFResult getAdminScores(Integer page, Integer limit);

    FFResult addAdminScore(Score score);

    FFResult updateAdminScore(Score score);

    FFResult deleteAdminScore(Integer id);
}
