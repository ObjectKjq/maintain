package com.kjq.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Certificate {
    private Integer id;
    private String createTime;
    private Integer certificateStatus;
    private Integer userId;
    private String content;
    private Integer status;
    private String image;
}
