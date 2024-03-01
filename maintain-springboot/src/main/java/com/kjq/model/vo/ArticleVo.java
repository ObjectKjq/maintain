package com.kjq.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleVo {
    private Integer id;
    private String sort;
    private String name;
    private String createTime;
    private String title;
    private String content;
}
