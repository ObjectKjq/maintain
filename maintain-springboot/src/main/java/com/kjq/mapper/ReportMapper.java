package com.kjq.mapper;

import com.kjq.POJO.Report;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ReportMapper {
    @Select("select * from report where report_status = 1 limit #{page}, #{limit}")
    List<Report> getAdminReports(Integer page, Integer limit);

    @Insert("insert into report(create_time, report_status, report_id, by_report_id, content) value(#{createTime}, #{reportStatus}, #{reportId}, #{byReportId}, #{content})")
    boolean addReport(Report report);

    @Select("select count(*) from  report where report_status = 1")
    Integer getTotal();

    @Update("update report set report_status = 0 where id = #{id}")
    boolean deleteAdminReport(Integer id);
}
