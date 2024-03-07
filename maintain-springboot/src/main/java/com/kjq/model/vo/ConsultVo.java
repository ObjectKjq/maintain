package com.kjq.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultVo {
    private Integer id;
    private String sendName;
    private String acceptName;
    private Integer userSendId;
    private Integer userAcceptId;
    private String content;
    private String createTime;
}
