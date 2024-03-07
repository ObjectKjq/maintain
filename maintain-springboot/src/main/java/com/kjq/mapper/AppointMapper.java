package com.kjq.mapper;

import com.kjq.POJO.Appoint;
import com.kjq.model.vo.AppointListVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AppointMapper {
    List<AppointListVo> getAppoints(Integer page, Integer num, String name, String city, String skill, String time);
    @Select("select avg(point) from score where score_status = 1 and score_id = #{appointId} group by score_id")
    Double getAvgScore(Integer appointId);

    @Update("UPDATE appoint SET by_appoint_id = #{byAppointId}, message = #{message}, status = 1 WHERE id = #{id} and status = 0 and appoint_status = 1")
    boolean updateAppoint(Integer byAppointId, String message, Integer id);

    @Select("select count(*) from appoint where by_appoint_id = #{id} and appoint_status = 1 and status = #{status}")
    Integer getTotal(Integer id, Integer status);

    @Select("select * from appoint where by_appoint_id = #{id} and appoint_status = 1 and status = #{status} limit #{page}, #{num}")
    List<Appoint> getListAppoint(Integer id, Integer page, Integer num, Integer status);

    @Update("update appoint set by_appoint_id = null, message = null, status = 0, price = null where id = #{id} and by_appoint_id = #{id1} and appoint_status = 1")
    boolean cancelAppoint(Integer id, Integer id1);

    @Update("update appoint set status = 3 where id = #{id} and by_appoint_id = #{id1} and appoint_status = 1")
    boolean tPrice(Integer id, Integer id1);

    @Select("select * from appoint where id = #{id} and appoint_status = 1")
    Appoint getAppointById(Integer id);

    @Update("update appoint set status = 5 where id = #{id} and by_appoint_id = #{id1} and appoint_status = 1")
    boolean updateStatus(Integer id, Integer id1);

    List<Appoint> getMaintainAppoints(Integer page, Integer limit, Integer status, String title, Integer id);


    Integer getAppointTotal(Integer status, String title, Integer id);

    @Update("update appoint set appoint_status = 0 where appoint_id = #{userId} and appoint_status = 1 and id = #{id}")
    boolean deleteAppoint(Integer id, Integer userId);

    @Update("update appoint set content = #{content}, appoint_time = #{appointTime} where id = #{id} and appoint_id = #{appointId} and appoint_status = 1")
    boolean updateMaintainAppoint(Integer appointId, String content, String appointTime, Integer id);

    @Insert("insert into appoint(create_time, appoint_status, appoint_id, content, appoint_time, status) value (#{createTime}, #{appointStatus}, #{appointId}, #{content}, #{appointTime}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean addAppoint(Appoint appoint);

    @Update("update appoint set price = #{price}, status = 2 where id = #{id} and appoint_id = #{appointId} and appoint_status = 1 and status = 1")
    boolean addPrice(Integer price, Integer id, Integer appointId);

    @Update("update appoint set status = 4 where status = 3 and appoint_id = #{appointId} and appoint_status = 1 and id = #{id}")
    boolean subAppoint(Integer id, Integer appointId);
}
