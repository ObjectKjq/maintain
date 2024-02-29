package com.kjq.service.impl;

import com.kjq.mapper.ScoreMapper;
import com.kjq.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    ScoreMapper scopeMapper;

}
