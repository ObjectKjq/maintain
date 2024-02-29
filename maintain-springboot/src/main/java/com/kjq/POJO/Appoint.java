package com.kjq.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appoint {
    private Integer id;
    private String createTime;
    private Integer appointStatus;
    private Integer appointId;
    private Integer byAppointId;
    private String content;
    private String appointTime;
    private String message;
    private Integer status;
    private Double price;
}
