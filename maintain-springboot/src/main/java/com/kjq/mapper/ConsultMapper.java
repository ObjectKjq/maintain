package com.kjq.mapper;

import com.kjq.model.vo.ConsultVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ConsultMapper {

    List<ConsultVo> getConsults(Integer page, Integer limit, Integer id);

    List<ConsultVo> getAdminConsults(Integer page, Integer limit);
}
