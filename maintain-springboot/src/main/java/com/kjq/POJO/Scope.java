package com.kjq.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scope {
    private Integer id;
    private String createTime;
    private Integer scopeStatus;
    private String content;
    private Integer userId;
}
