package com.kjq.mapper;

import com.kjq.POJO.Certificate;
import com.kjq.utils.FFResult;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CertificateMapper {
    @Select("select * from certificate where certificate_status = 1 and user_id = #{id} limit #{page}, #{limit}")
    List<Certificate> getCertificates(Integer page, Integer limit, Integer id);

    @Select("select * from certificate where certificate_status = 0 limit #{page}, #{limit}")
    List<Certificate> getAdminCertificates(Integer page, Integer limit);

    @Select("select count(*) from certificate where user_id = #{id} and certificate_status = 1")
    Integer getTotal(Integer id);

    @Insert("insert into certificate(create_time, certificate_status, user_id, content) value(#{createTime}, #{certificateStatus}, #{userId}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean addCertificate(Certificate certificate);

    @Delete("delete from certificate where id = #{id} and user_id = #{userId}")
    boolean deleteCertificate(Integer id, Integer userId);

    @Select("select count(*) from certificate where certificate_status = 1")
    Integer getAdminTotal();

    @Update("update certificate set certificate_status = 1 where id = #{id}")
    boolean passCertificate(Integer id);

    @Delete("delete from certificate where id = #{id}")
    boolean downCertificate(Integer id);
}
