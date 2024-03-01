package com.kjq.mapper;

import com.kjq.POJO.Certificate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CertificateMapper {
    @Select("select * from certificate where certificate_status = 1 and user_id = #{id} limit #{page}, #{limit}")
    List<Certificate> getCertificates(Integer page, Integer limit, Integer id);

    @Select("select * from certificate where certificate_status = 0 limit #{page}, #{limit}")
    List<Certificate> getAdminCertificates(Integer page, Integer limit);
}
