package com.kjq.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consult {
    private Integer id;
    private Integer userSendId;
    private Integer userAcceptId;
    private String content;
    private Integer consultStatus;
    private String createTime;
    private Integer status;
}
