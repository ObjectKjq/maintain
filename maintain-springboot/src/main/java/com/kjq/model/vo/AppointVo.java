package com.kjq.model.vo;

import com.kjq.POJO.Appoint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointVo extends Appoint {
    private Integer total;
}
