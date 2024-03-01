package com.kjq.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    private Integer id;
    private String createTime;
    private Integer skillStatus;
    private Integer userId;
    private String content;
}
