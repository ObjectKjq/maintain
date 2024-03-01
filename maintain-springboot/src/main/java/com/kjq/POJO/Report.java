package com.kjq.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    private Integer id;
    private String createTime;
    private Integer reportStatus;
    private Integer reportId;
    private Integer byReportId;
    private String content;
}
