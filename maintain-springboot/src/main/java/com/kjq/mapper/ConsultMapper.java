package com.kjq.mapper;

import com.kjq.POJO.Consult;
import com.kjq.model.vo.ConsultVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ConsultMapper {

    List<ConsultVo> getConsults(Integer page, Integer limit, Integer id);

    List<ConsultVo> getAdminConsults(Integer page, Integer limit);

    @Insert("insert into consult(user_send_id, user_accept_id, content, consult_status, create_time, status) value(#{userSendId}, #{userAcceptId}, #{content}, #{consultStatus}, #{createTime}, #{status})")
    boolean addConsult(Consult consult);

    @Select("select * from consult where consult_status = 1 and status = 0 and user_accept_id = #{id}")
    List<Consult> getListConsult(Integer id);

    @Update("update consult set consult_status = 0 where id = #{id1} and user_accept_id = #{id}")
    boolean deleteConsult(Integer id, Integer id1);

    @Update("update consult set status = 1 and id = #{id}")
    boolean updateStatus(Integer id);

    @Select("select count(*) from consult where user_accept_id = #{id} and consult_status = 1 and status = 0")
    Integer getTotal(Integer id);

    @Update("update consult set status = 1 where id = #{id} and user_accept_id = #{userId}")
    boolean updateIdStatus(Integer id, Integer userId);
}
