package com.kjq.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    private Integer id;
    private Integer userId;
    private String createTime;
    private String content;
    private Integer feedbackStatus;
}
