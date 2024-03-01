package com.kjq.mapper;

import com.kjq.POJO.Report;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReportMapper {
    @Select("select * from report where report_status = 1 limit #{page}, #{limit}")
    List<Report> getAdminReports(Integer page, Integer limit);
}
