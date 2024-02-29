package com.kjq.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointListVo {
    private Integer id;
    private Integer appointId;
    private String avatar;
    private String username;
    private String createTime;
    private String appointTime;
    //业务范围
    private List<String> scopes;
    //技能
    private List<String> skills;

    //评分
    private Double score;
    //技能证书
    private List<String> certificates;
    //业务介绍
    private String content;
}
