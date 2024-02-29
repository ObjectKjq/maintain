package com.kjq.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score {
    private Integer id;
    private String createTime;
    private Integer scoreStatus;
    private Integer scoreId;
    private Integer byScoreId;
    private Integer point;
}
