package com.kjq.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private Integer id;
    private Integer userId;
    private Integer sortId;
    private String createTime;
    private String title;
    private Integer articleStatus;
    private String content;
}
