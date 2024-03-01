package com.kjq.service;

import com.kjq.utils.FFResult;

public interface FeedbackService {
    FFResult addFeedback(String content);

    FFResult getAdminFeedback(Integer page, Integer limit);
}
